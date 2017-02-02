package Tests_on_the_fly;

import java.sql.SQLException;

import DAO.CompanyDAO;

import DAO.CustomerDAO;
import DBDAO.CompanyDBDAO;

import DBDAO.CustomerDBDAO;
import Exceptions.AdminFacadeException;
import Exceptions.AlreadyExistException;
import Exceptions.CouponException;
import Exceptions.CustomerException;
import Exceptions.DoesNotExistException;
import Exceptions.FacadeException;
import Exceptions.LoginException;
import Facades.AdminFacade;
import Facades.ClientType;
import JavaBeans.Company;
import JavaBeans.Customer;

public class TestAdminFacade 
{

	private static AdminFacade adminFacade = new AdminFacade();

	public static void main(String[] args) throws LoginException, CustomerException, CouponException, SQLException, DoesNotExistException, AlreadyExistException, AdminFacadeException, FacadeException 
	{

//	TestLoginAdmin();

//	V	TestCreateCompany();
//	V	TestRemoveCompany();
//		TestUpdateCompany();
//	V	TestGetCompany();
//	V	TestGetAllCompanies();
//	V	TestCreateCustomer();
//		TestRemoveCustomer();
//		TestUpdateCustomer();
//	V	TestGetCustomer();
		TestGetAllCustomers();
		
		
		
//		TestGetAllPurchasedCoupons();
		
//		TestgetAllPurchasedCouponsByType();
//		TestgetAllPurchasedCouponsByPrice();
	}
		public static void TestLoginAdmin() throws CouponException, AlreadyExistException, DoesNotExistException, SQLException, LoginException, AdminFacadeException, FacadeException
		{
						
		AdminFacade af= adminFacade.login("admin", "1234") ;
		System.out.println("Admin was logged suceessfully");
		// customer logged in successfully
					
		//adminFacade.createCompany(new Company("Altair2","Altair123456" , "Altair@coupons.co.il"));
		
			
		}
		
			public static void TestCreateCompany() 
				throws CouponException, AlreadyExistException, DoesNotExistException, SQLException, LoginException, AdminFacadeException
				{
			
				try {
					
					adminFacade.login("admin", "1234") ;
					System.out.println("Admin was logged suceessfully");
					// customer logged in successfully
					
					adminFacade.createCompany(new Company("Alto","Alto12345" , "Alto@coupons.co.il"));
					System.out.println("Company was added");
				} catch (FacadeException e) {
					
					e.printStackTrace();
				}
		}	
		
		public static void TestRemoveCompany() throws LoginException, CouponException, DoesNotExistException, AdminFacadeException, AlreadyExistException, SQLException 
		{
			CompanyDAO comp = new CompanyDBDAO();					
			try
			{
				adminFacade.login("admin", "1234") ;
				System.out.println("Admin was logged suceessfully");
				// customer logged in successfully
				Company company= comp.getCompanyById(25);
					
				adminFacade.removeCompany(company);
				
				
			} catch (FacadeException e) 
			{
			e.printStackTrace();
			}
			}
			
		
		public static void TestUpdateCompany() throws CouponException, DoesNotExistException, SQLException, FacadeException, LoginException, AlreadyExistException
		{
			adminFacade.login("admin", "1234") ;
			System.out.println("Admin was logged suceessfully");
			// customer logged in successfully
			
			CompanyDAO compDAO= new CompanyDBDAO();
			Company comp= compDAO.getCompanyByName("company 124");
			System.out.println(comp);
			comp.setName("Altozachen");
			comp.setPassword("123456");
			comp.setEmail("Alto@gmail.comm");
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
			System.out.println(adminFacade.getAllCompanies());
		}
		
		public static void TestCreateCustomer() throws DoesNotExistException, CouponException, SQLException, AlreadyExistException, FacadeException, LoginException, AdminFacadeException
		{
			adminFacade.createCustomer(new Customer("Yeela", "Yeelabnbnb"));
		}
		
		public static void TestRemoveCustomer() throws DoesNotExistException, CouponException, SQLException, AlreadyExistException, FacadeException, LoginException, AdminFacadeException
		{
			CustomerDAO cust = new CustomerDBDAO();	

			Customer customer= cust.getCustomerById(24);

			
			adminFacade.RemoveCustomer(customer);
		}
		
		public static void TestUpdateCustomer() throws DoesNotExistException, CouponException, SQLException, AlreadyExistException, AdminFacadeException
		{
			CustomerDAO cust = new CustomerDBDAO();	
			
			adminFacade.UpdateCustomer(cust.getCustomerById(14));
		}
		public static void TestGetCustomer() throws DoesNotExistException, CouponException, SQLException, AlreadyExistException, AdminFacadeException
		{
			adminFacade.GetCustomer(14);
		}
		
		public static void TestGetAllCustomers() throws DoesNotExistException, CouponException, SQLException, AlreadyExistException, AdminFacadeException
		{
			System.out.println(adminFacade.getAllCustomers());
		}
		
}
