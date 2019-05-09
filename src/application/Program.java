package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		/*System.out.println("=== Test 1: seller findById === ");
		System.out.println(sellerDao.findById(3));
		
		System.out.println("/n=== Test 2: seller findByDepartment === ");
		List<Seller> sellers = sellerDao.findByDepartment(new Department(2, null));
		sellers.forEach(seller -> System.out.println(seller));
		
		System.out.println("/n=== Test 3: seller findAll === ");
		List<Seller> allSellers = sellerDao.findAll();
		allSellers.forEach(seller -> System.out.println(seller));*/
		
		System.out.println("/n=== Test 4: seller insert  === ");
		Seller sellerObj1 = new Seller(null,
				"Daniel White", 
				"danielwhite@gmail.com", 
				new java.sql.Date(sdf.parse("31/10/1990").getTime()), 
				997.00, 
				new Department(2, null));
		
		sellerDao.insert(sellerObj1);
		System.out.println("Done! New Id " + sellerObj1.getId());
		
		System.out.println("/n=== Test 5: seller update  === ");
		Seller sellerObj2 = new Seller(25,
				"Octavious Black", 
				"octaviousblack@gmail.com", 
				new java.sql.Date(sdf.parse("13/01/1962").getTime()), 
				17000.00, 
				new Department(3, null));
		sellerDao.update(sellerObj2);
	}
}
