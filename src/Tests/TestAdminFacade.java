package Tests;

import java.sql.SQLException;

import DAO.CouponDAO;
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

	public static void main(String[] args) throws LoginException, CustomerException, CouponException, SQLException, DoesNotExistException, AlreadyExistException, AdminFacadeException 
	{
//		TestLoginAdmin();
		TestCreateCompany();
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
					
					adminFacade.createCompany(new Company("Mazdak","Mazdak12345" , "Mazdak@coupons.co.il"));
					System.out.println("Company was added");
				} catch (FacadeException e) {
					
					e.printStackTrace();
				}
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
