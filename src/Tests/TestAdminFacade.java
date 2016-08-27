package Tests;

import java.sql.SQLException;

import DAO.CompanyDAO;
import DAO.CouponDAO;
import DBDAO.CompanyDBDAO;
import DBDAO.CouponDBDAO;
import Exceptions.AdminFacadeException;
import Exceptions.AlreadyExistException;
import Exceptions.CouponException;
import Exceptions.CustomerException;
import Exceptions.DoesNotExistException;
import Exceptions.FacadeException;
import Exceptions.LoginException;
import Facades.AdminFacade;
import Facades.ClientType;
import Facades.CustomerFacade;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.CouponType;
import JavaBeans.Customer;

public class TestAdminFacade {

	private static AdminFacade adminFacade = new AdminFacade();

	public static void main(String[] args) throws LoginException, CustomerException, CouponException, SQLException, DoesNotExistException, AlreadyExistException, AdminFacadeException, FacadeException 
	{
//	V	TestLoginAdmin();
//		TestCreateCompany();
//	V	TestremoveCompany();
//	X	TestUpdateCompany();
		TestGetCompany();
//		TestGetAllCompanies();
//		TestGetAllPurchasedCoupons();
		
//		TestgetAllPurchasedCouponsByType();
//		TestgetAllPurchasedCouponsByPrice();
	}
		public static void TestLoginAdmin() throws CouponException, AlreadyExistException, DoesNotExistException, SQLException, LoginException, AdminFacadeException
		{
				
				try {
					
					AdminFacade af= adminFacade.login("admin", "1234", ClientType.ADMIN) ;
					System.out.println("Admin was logged suceessfully");
					// customer logged in successfully
					
					adminFacade.createCompany(new Company("Altair2","Altair123456" , "Altair@coupons.co.il"));
				} catch (FacadeException e) {
					
					e.printStackTrace();
				}
			
		}
		
			public static void TestCreateCompany() 
				throws CouponException, AlreadyExistException, DoesNotExistException, SQLException, LoginException, AdminFacadeException
				{
			
				try {
					
					adminFacade.login("admin", "1234", ClientType.ADMIN) ;
					System.out.println("Admin was logged suceessfully");
					// customer logged in successfully
					
					adminFacade.createCompany(new Company("Alto","Alto12345" , "Alto@coupons.co.il"));
					System.out.println("Company was added");
				} catch (FacadeException e) {
					
					e.printStackTrace();
				}
		}	
		
		public static void TestremoveCompany() throws LoginException, CouponException, DoesNotExistException, AdminFacadeException, AlreadyExistException, SQLException 
		{
			CompanyDAO comp = new CompanyDBDAO();					
			try
			{
				adminFacade.login("admin", "1234", ClientType.ADMIN) ;
				System.out.println("Admin was logged suceessfully");
				// customer logged in successfully
				Company company= comp.getCompanyById(25);
					
				adminFacade.removeCompany(company);
				
				
			} catch (FacadeException e) 
			{
			e.printStackTrace();
			}
			}
			
		
		public static void TestUpdateCompany() throws CouponException, DoesNotExistException, SQLException, FacadeException, LoginException
		{
			adminFacade.login("admin", "1234", ClientType.ADMIN) ;
			System.out.println("Admin was logged suceessfully");
			// customer logged in successfully
			
			CompanyDAO compDAO= new CompanyDBDAO();
			Company comp= compDAO.getCompanyByName("company 124");
			System.out.println(comp);
			comp.setCompName("Altozachen");
			comp.setPassWord("123456");
			comp.seteMail("Alto@gmail.comm");
			System.out.println(comp+ "******");
			
			adminFacade.UpdateCompany(comp);
//			
		}
		
		public static void TestGetCompany() throws CouponException, DoesNotExistException, SQLException, FacadeException, LoginException, AlreadyExistException
		{
//			adminFacade.login("admin", "1234", ClientType.ADMIN) ;
//			System.out.println("Admin was logged suceessfully");
//			// customer logged in successfully
//			
//			CompanyDAO compDAO= new CompanyDBDAO();
//			Company comp= compDAO.getCompanyByName("company 124");
			
			System.out.println(adminFacade.GetCompanyByName("company 122"));
			System.out.println(adminFacade.GetCompany(6));
		}
		
		
		public static void TestGetAllCompanies() throws DoesNotExistException, CouponException, SQLException, AlreadyExistException, FacadeException, LoginException
		{
			adminFacade.login("admin", "1234", ClientType.ADMIN) ;
			System.out.println("Admin was logged suceessfully");
			// customer logged in successfully
			
//			CompanyDAO compDAO= new CompanyDBDAO();
			System.out.println(adminFacade.getAllCompanies());
		}
		
		
		
		
		
		
		
		
		
		
		
		
		public static void  TestGetAllPurchasedCoupons()
				throws CouponException, AlreadyExistException, DoesNotExistException, SQLException, CustomerException, LoginException
		{
			try {
				
				Customer customer= new Customer("Customer23", "Custielel");
				custFacade.login(customer.getCustName(), customer.getPassWord(), ClientType.CUSTOMER) ;
				// customer logged in successfully
				
				System.out.println(custFacade.getAllPurchasedCoupons());
			} catch (FacadeException e) 
			{
			e.printStackTrace();
			}
		}	
		
		public static void  TestgetAllPurchasedCouponsByType()
				throws CouponException, AlreadyExistException, DoesNotExistException, SQLException, CustomerException, LoginException
		{
			try {
				
				Customer customer= new Customer("Customer23", "Custielel");
				custFacade.login(customer.getCustName(), customer.getPassWord(), ClientType.CUSTOMER) ;
				// customer logged in successfully
				
				System.out.println(custFacade.getAllPurchasedCouponsByType(CouponType.Clothes));
				
				} 
			catch (FacadeException e) 
				{
				e.printStackTrace();
				}
		}

		public static void  TestgetAllPurchasedCouponsByMaxPrice()throws CouponException, AlreadyExistException, DoesNotExistException, SQLException, CustomerException, LoginException
		{
	try {
				
				Customer customer= new Customer("Customer23", "Custielel");
				custFacade.login(customer.getCustName(), customer.getPassWord(), ClientType.CUSTOMER) ;
				// customer logged in successfully
				
				System.out.println(custFacade.getAllPurchasedCouponsByMaxPrice(90));
				
				} 
			catch (FacadeException e) 
				{
				e.printStackTrace();
				}
			
			
		}


}
