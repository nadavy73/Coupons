package Tests;

import DBDAO.*;
import Exceptions.*;
import JavaBeans.*;

import java.sql.*;

import DAO.*;


public class CompanyTest 
{
public static void main(String[] args) throws DaoException, SQLException, CouponException, AlreadyExistException, DoesNotExistException
{
	CompanyDAO compDAO= new CompanyDBDAO();
	Company comp = new Company("Effo", "blablab", "eMail");
	compDAO.createCompany(comp);
//	CustomerDAO custDAO= new CustomerDBDAO();
//	Customer cust = new Customer("Micro", "1234");
//	custDAO.removeCustomer(cust);
	
//	custDAO.createCustomer(cust);
	
	
//	compDAO.updateCompany(comp);
	
	//System.out.println(compDAO.getCoupons(1));
	
	
	//compDAO.updateCompany(comp);
//	for (int i = 0; i < 25; i++) {
//		// Company instance
//		Company company = new Company(
//				"company " + (i + 100), 
//				"password " + (i + 100),
//				(i + 100) + "@company.com");
//	

		//create Company
//compDAO.createCompany(comp);
	
		//Update Company name
		//compDAO.updateCompanyName("Ofer", "Oferiki");
		
		//Remove Company From DB
//		compDAO.removeCompany("company " + (i + 700));

		//get Company from DB
//		compDAO.getCompany(1);
//		System.out.println(compDAO.getCompany(1));
	//	System.out.println(compDAO.getCompanyByName("Assaf"));
		//compDAO.removeCompany("Assaf");
		//get All Companies
//		compDAO.getAllCompanies();
		
		//get Coupons
//		compDAO.getCoupons(1);
		
//		compDAO.login("Nadav", "Nadavi");
}

}


