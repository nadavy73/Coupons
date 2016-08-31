package Tests;


import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;

import DAO.*;
import DBDAO.*;
import Exceptions.*;
import Facades.*;
import JavaBeans.*;

public class TestCompanyFacade {
	
	private static CompanyFacade compFacade = new CompanyFacade();

	public static void main(String[] args) 
			throws CouponException, AlreadyExistException, DoesNotExistException, SQLException, LoginException, FacadeException, CompanyFacadeException 
	{
//	V	TestLoginCompany();
//	V	TestCreateCoupon();
//	V	TestRemoveCoupon();
		TestUpdateCoupon();
//	V	TestGetCoupon();
//	V	TestGetAllCoupon();
//	V	TestGetCouponByType();
//	V	TestGetCouponsByEndDate();
//	V	TestGetCouponsByPrice();
		


	}

	public static void TestLoginCompany() 
			throws CouponException, AlreadyExistException, DoesNotExistException, SQLException, LoginException, FacadeException
		{
			Company company= new Company("Alto", "Alto12345");
				
				compFacade.login(company.getCompName(), company.getPassWord(), ClientType.COMPANY) ;
			// company logged in successfully
		}
	
	public static void TestCreateCoupon() 
			throws CouponException, AlreadyExistException, DoesNotExistException, SQLException, LoginException, FacadeException, CompanyFacadeException
			{
				Company company= new Company("Alto", "Alto12345");
				compFacade.login(company.getCompName(), company.getPassWord(), ClientType.COMPANY) ;
				// company logged in successfully
				
				Coupon coup4 = new Coupon(7,"Nautica", LocalDate.now(),LocalDate.of(2016, Month.SEPTEMBER, 27), 50, CouponType.Clothes, "Fire", 24.99, "T-shirt.jpg");
				compFacade.createCoupon(coup4);
				
			}
	
	public static void TestRemoveCoupon() 
			throws FacadeException, LoginException, CouponException, DoesNotExistException, SQLException, AlreadyExistException
	{
		Company company= new Company("Alto", "Alto12345");
		compFacade.login(company.getCompName(), company.getPassWord(), ClientType.COMPANY) ;
		// company logged in successfully
		
		CouponDAO coupDAO= new CouponDBDAO();
		Coupon coupon1= coupDAO.getCouponByTitle("Nautica");
		compFacade.removeCoupon(coupon1);
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
		Company company= new Company("Alto", "Alto12345");
		compFacade.login(company.getCompName(), company.getPassWord(), ClientType.COMPANY) ;
		// company logged in successfullyGetAllCoupon()
	
		System.out.println(compFacade.getCouponsByPrice(50));
		
	}
}






