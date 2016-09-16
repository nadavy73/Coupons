package Tests;


import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;

import DAO.*;
import DBDAO.*;
import Exceptions.*;
import JavaBeans.*;
import System.CouponSystem;


public class CouponsTest {

	public static void main(String[] args) throws CouponException, AlreadyExistException, DoesNotExistException, SQLException 
	{
		
		CreateCouponsTest();
//V		RemoveCouponsTest();
//V		UpdateCouponsTest();
//V		updateAmountOfCouponTest();
//V		getCouponByTitleTest();
//V		getCouponTest();
//V		getAllCouponsTest();
//V		getCouponByTypeTest();
//V		getCustomersWhoHaveCouponTest();

	}
	
		public static void CreateCouponsTest() 
				throws CouponException, AlreadyExistException, DoesNotExistException, SQLException
		{
			
			Coupon coup = CouponSystem.getInstance().getCouponDAO().getCouponByTitle("BBB");
			System.out.println(coup);
//			
//			Coupon coup1 = new Coupon("BBB2", LocalDate.now(),LocalDate.of(2016, Month.SEPTEMBER, 1), 50, CouponType.Restaurants, "The Burger", 24.99, "blabka");
//			Coupon coup2 = new Coupon("Agadir2", LocalDate.now(),LocalDate.of(2016, Month.AUGUST, 28), 50, CouponType.Restaurants, "The Burger", 24.99, "blabka");
//			Coupon coup3 = new Coupon("Moses2", LocalDate.now(),LocalDate.of(2016, Month.AUGUST, 29), 50, CouponType.Restaurants, "The Burger", 24.99, "blabka");
//			Coupon coup4 = new Coupon("T-SHIRT2", LocalDate.now(),LocalDate.of(2016, Month.AUGUST, 30), 50, CouponType.Clothes, "Fire", 24.99, "T-shirt.jpg");
//			CouponSystem.getInstance().getCouponDAO().createCoupon(coup1);
//			CouponSystem.getInstance().getCouponDAO().createCoupon(coup2);
//			CouponSystem.getInstance().getCouponDAO().createCoupon(coup3);
//			CouponSystem.getInstance().getCouponDAO().createCoupon(coup4);
		}
		
		public static void RemoveCouponsTest() throws CouponException, AlreadyExistException, DoesNotExistException, SQLException
		{
			CouponDAO coupDao = new CouponDBDAO();
			
			Coupon coup1 = new Coupon("BBB", LocalDate.now(),LocalDate.of(2016, Month.AUGUST, 27), 50, CouponType.Restaurants, "The Burger", 24.99, "blabka");
			Coupon coup2 = new Coupon("Agadir", LocalDate.now(),LocalDate.of(2016, Month.AUGUST, 27), 50, CouponType.Restaurants, "The Burger", 24.99, "blabka");
			Coupon coup3 = new Coupon("Mosges", LocalDate.now(),LocalDate.of(2016, Month.AUGUST, 27), 50, CouponType.Restaurants, "The Burger", 24.99, "blabka");
			
			coupDao.removeCoupon(coup1);
			coupDao.removeCoupon(coup2);
			coupDao.removeCoupon(coup3);
		}
		
		public static void UpdateCouponsTest() 
				throws CouponException, AlreadyExistException, DoesNotExistException, SQLException
		{
			CouponDAO coupDao = new CouponDBDAO();
			
			Coupon coup1 = new Coupon("BBB", LocalDate.now(),LocalDate.of(2016, Month.AUGUST, 27), 50, CouponType.Restaurants, "The Burger", 50.99, "blabka");
			Coupon coup2 = new Coupon("Agadir", LocalDate.now(),LocalDate.of(2016, Month.AUGUST, 27), 50, CouponType.Restaurants, "The Burger", 50.99, "blabka");
			Coupon coup3 = new Coupon("Mosges", LocalDate.now(),LocalDate.of(2016, Month.AUGUST, 27), 50, CouponType.Restaurants, "The Burger", 50.99, "blabka");
			
			coupDao.updateCoupon(coup1);
			coupDao.updateCoupon(coup2);
			coupDao.updateCoupon(coup3);
		}
		
		public static void updateAmountOfCouponTest() 
				throws CouponException, AlreadyExistException, DoesNotExistException, SQLException
		{	
			CouponDAO coupDao = new CouponDBDAO();
//			for (int i=0;i<50;i++)
//				{
				coupDao.updateAmountOfCoupon(38);
//				}
		}
		
		public static void getCouponByTitleTest() 
				throws CouponException, AlreadyExistException, DoesNotExistException, SQLException
		{
			CouponDAO coupDao = new CouponDBDAO();
			
			System.out.println(coupDao.getCouponByTitle("BBB1"));
//			System.out.println(coupDao.getCouponByTitle("Shuki"));
		}
		
		public static void getCouponTest() 
				throws CouponException, AlreadyExistException, DoesNotExistException, SQLException 
		{
			CouponDAO coupDao = new CouponDBDAO();
			System.out.println(coupDao.getCoupon(20));
		}
		
		public static void getAllCouponsTest() 
				throws CouponException, DoesNotExistException, SQLException
		{
			CouponDAO coupDao = new CouponDBDAO();
			System.out.println(coupDao.getAllCoupons());
		}

		public static void getCouponByTypeTest() 
				throws CouponException, DoesNotExistException
		{
			CouponDAO coupDao = new CouponDBDAO();
			try {
				System.out.println(coupDao.getCouponByType(CouponType.Camping_and_Travelling));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static void getCustomersWhoHaveCouponTest() 
				throws CouponException, DoesNotExistException, AlreadyExistException, SQLException
		{
			CouponDAO coupDao = new CouponDBDAO();
			System.out.println(coupDao.getCustomersWhoHaveCoupon(3));
			
		}

}
