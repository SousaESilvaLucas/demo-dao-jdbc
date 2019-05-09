package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Department dp = new Department(1,"books");
		Seller seller = new Seller(21,"Bob", "bob@gmail.com",new Date(),3000.00,dp);
		SellerDao sellerDao = DaoFactory.createSellerDao();

		System.out.println(dp);
		System.out.println(seller);
		System.out.println("-------------------------");
		System.out.println(sellerDao.findById(3));

	}

}
