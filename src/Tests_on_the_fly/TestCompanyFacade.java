package Tests_on_the_fly;


import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;

import DAO.*;
import DBDAO.*;
import Exceptions.*;
import Facades.*;
import JavaBeans.*;
import System.CouponSystem;
import Threads.DailyCouponExpirationTask;

public class TestCompanyFacade {
	
	private static CompanyFacade compFacade = new CompanyFacade();
	private static DailyCouponExpirationTask daily= new DailyCouponExpirationTask();

	public static void main(String[] args) 
			throws CouponException, AlreadyExistException, DoesNotExistException, SQLException, LoginException, FacadeException, CompanyFacadeException 
	{
	TestLoginCompany();
//		TestCreateCoupon();
//	V	TestRemoveCoupon();
//	V	TestUpdateCoupon();
//	V	TestGetCoupon();
//	V	TestGetAllCoupon();
//	V	TestGetCouponByType();
//	V	TestGetCouponsByEndDate();
//		TestGetCouponsByPrice();
		
	}

	public static void TestLoginCompany() 
			throws LoginException
		{
			Company company= new Company("Alto", "Alto12345");
				
				compFacade.login(company.getCompName(), company.getPassWord(), ClientType.COMPANY) ;
			// company logged in successfully
		}
	
	public static void TestCreateCoupon() throws LoginException, CompanyFacadeException, AlreadyExistException, DoesNotExistException, SQLException
			
			{
				Company company= new Company("Alto", "Alto12345");
				compFacade.login(company.getCompName(), company.getPassWord(), ClientType.COMPANY) ;
				// company logged in successfully
				
				Coupon coup4 = new Coupon("ZARA", LocalDate.now(),LocalDate.of(2016, Month.JULY, 27), 50, CouponType.Restaurants, "Fire", 24.99, "T-shirt.jpg");
				compFacade.createCoupon(coup4);
				
			}
	
	public static void TestRemoveCoupon() throws LoginException, DoesNotExistException,CompanyFacadeException 
	{
		Company company= new Company("Alto", "Alto12345");
		compFacade.login(company.getCompName(), company.getPassWord(), ClientType.COMPANY) ;
		// company logged in successfully
		
		CouponDAO coupDAO= new CouponDBDAO();
		 
		try {
			Coupon coupon1 = coupDAO.getCouponByTitle("Nautica");
			compFacade.removeCoupon(coupon1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void TestUpdateCoupon() throws FacadeException, LoginException, CouponException, DoesNotExistException, SQLException, AlreadyExistException 
			
	{
		Company company= new Company("Alto", "Alto12345");
		compFacade.login(company.getCompName(), company.getPassWord(), ClientType.COMPANY) ;
		// company logged in successfully
		
		CouponDAO coupDAO= new CouponDBDAO();

		Coupon coup= coupDAO.getCouponByTitle("BBB");
		System.out.println(coup);
		coup.setPrice(50);
		compFacade.updateCoupon(coup);
		
		coup= coupDAO.getCouponByTitle("BBB");

		System.out.println(coup);
	}
	
	public static void TestGetCoupon() 
			throws FacadeException, LoginException, CouponException, DoesNotExistException, SQLException, AlreadyExistException, CompanyFacadeException
	{
		Company company= new Company("Alto", "Alto12345");
		compFacade.login(company.getCompName(), company.getPassWord(), ClientType.COMPANY) ;
		// company logged in successfully
		
		System.out.println(compFacade.getCoupon(5));
	}
	
	public static void TestGetAllCoupon()
			throws FacadeException, LoginException, CouponException, DoesNotExistException, SQLException, AlreadyExistException, CompanyFacadeException
	{
		Company company= new Company("Alto", "Alto12345");
		compFacade.login(company.getCompName(), company.getPassWord(), ClientType.COMPANY) ;
		// company logged in successfullyGetAllCoupon()
	
		System.out.println(compFacade.getAllCoupon());
	}
	
	public static void TestGetCouponByType()
			throws FacadeException, LoginException, CouponException, DoesNotExistException, SQLException, AlreadyExistException, CompanyFacadeException
	{
		Company company= new Company("Alto", "Alto12345");
		compFacade.login(company.getCompName(), company.getPassWord(), ClientType.COMPANY) ;
		// company logged in successfullyGetAllCoupon()
	
		System.out.println(compFacade.getCouponByType(CouponType.Restaurants));
		
	}


	public static void TestGetCouponsByEndDate()
			throws FacadeException, LoginException, CouponException, DoesNotExistException, SQLException, AlreadyExistException, CompanyFacadeException
	{
		Company company= new Company("Alto", "Alto12345");
		compFacade.login(company.getCompName(), company.getPassWord(), ClientType.COMPANY) ;
		// company logged in successfullyGetAllCoupon()
	
		System.out.println(compFacade.getCouponsByEndDate(LocalDate.of(2016, Month.AUGUST, 31)));
		
	}

	public static void TestGetCouponsByPrice()
			throws FacadeException, LoginException, CouponException, DoesNotExistException, SQLException, AlreadyExistException, CompanyFacadeException
	{
//		Company company= new Company("Alto", "Alto12345");
//		compFacade.login(company.getCompName(), company.getPassWord(), ClientType.COMPANY) ;
//		// company logged in successfullyGetAllCoupon()
//	
//		System.out.println(compFacade.getCouponsByPrice(50));
		
		
		
	}
}






