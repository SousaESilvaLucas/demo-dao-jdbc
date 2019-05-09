package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		List<Seller> sellers = sellerDao.findByDepartment(new Department(2, null));
		System.out.println("=== Test 1: seller findById === ");
		System.out.println(sellerDao.findById(3));
		System.out.println();
		
		System.out.println("=== Test 2: seller findByDepartment === ");
		sellers.forEach(seller -> System.out.println(seller));

	}

}
