package Tests;


import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;

import DAO.*;
import DBDAO.*;
import Exceptions.*;
import JavaBeans.*;


public class CouponsTest {

	public static void main(String[] args) throws CouponException, AlreadyExistException, DoesNotExistException, SQLException 
	{
		
//		CreateCouponsTest();
//		RemoveCouponsTest();
//		UpdateCouponsTest();
//		getCouponByTitleTest();
//		getCouponTest();
//		getAllCouponsTest();
//		getCouponByTypeTest();
		
	}
	
		public static void CreateCouponsTest() throws CouponException, AlreadyExistException, DoesNotExistException
		{
			CouponDAO coupDao = new CouponsDBDAO();
			
			Coupon coup1 = new Coupon("BBB", LocalDate.now(),LocalDate.of(2016, Month.AUGUST, 27), 50, CouponType.Restaurants, "The Burger", 24.99, "blabka");
			Coupon coup2 = new Coupon("Agadir", LocalDate.now(),LocalDate.of(2016, Month.AUGUST, 27), 50, CouponType.Restaurants, "The Burger", 24.99, "blabka");
			Coupon coup3 = new Coupon("Mosges", LocalDate.now(),LocalDate.of(2016, Month.AUGUST, 27), 50, CouponType.Restaurants, "The Burger", 24.99, "blabka");
			
			coupDao.createCoupon(coup1);
			coupDao.createCoupon(coup2);
			coupDao.createCoupon(coup3);
		}
		
		public static void RemoveCouponsTest() throws CouponException, AlreadyExistException, DoesNotExistException, SQLException
		{
			CouponDAO coupDao = new CouponsDBDAO();
			
			Coupon coup1 = new Coupon("BBB", LocalDate.now(),LocalDate.of(2016, Month.AUGUST, 27), 50, CouponType.Restaurants, "The Burger", 24.99, "blabka");
			Coupon coup2 = new Coupon("Agadir", LocalDate.now(),LocalDate.of(2016, Month.AUGUST, 27), 50, CouponType.Restaurants, "The Burger", 24.99, "blabka");
			Coupon coup3 = new Coupon("Mosges", LocalDate.now(),LocalDate.of(2016, Month.AUGUST, 27), 50, CouponType.Restaurants, "The Burger", 24.99, "blabka");
			
			coupDao.removeCoupon(coup1);
			coupDao.removeCoupon(coup2);
			coupDao.removeCoupon(coup3);
		}
		
		public static void UpdateCouponsTest() throws CouponException, AlreadyExistException, DoesNotExistException, SQLException
		{
			CouponDAO coupDao = new CouponsDBDAO();
			
			Coupon coup1 = new Coupon("BBB", LocalDate.now(),LocalDate.of(2016, Month.AUGUST, 27), 50, CouponType.Restaurants, "The Burger", 50.99, "blabka");
			Coupon coup2 = new Coupon("Agadir", LocalDate.now(),LocalDate.of(2016, Month.AUGUST, 27), 50, CouponType.Restaurants, "The Burger", 50.99, "blabka");
			Coupon coup3 = new Coupon("Mosges", LocalDate.now(),LocalDate.of(2016, Month.AUGUST, 27), 50, CouponType.Restaurants, "The Burger", 50.99, "blabka");
			
			coupDao.updateCoupon(coup1);
			coupDao.updateCoupon(coup2);
			coupDao.updateCoupon(coup3);
		}
		
		public static void getCouponByTitleTest() throws CouponException, AlreadyExistException, DoesNotExistException
		{
			CouponDAO coupDao = new CouponsDBDAO();
			
			//System.out.println(coupDao.getCouponByTitle("BBB"));
			System.out.println(coupDao.getCouponByTitle("Shuki"));
		}
		
		public static void getCouponTest() throws CouponException, AlreadyExistException, DoesNotExistException 
		{
			CouponDAO coupDao = new CouponsDBDAO();
			System.out.println(coupDao.getCoupon(20));
		}
		
		public static void getAllCouponsTest() throws CouponException, DoesNotExistException
		{
			CouponDAO coupDao = new CouponsDBDAO();
			System.out.println(coupDao.getAllCoupons());
		}

		public static void getCouponByTypeTest() throws CouponException, DoesNotExistException
		{
			CouponDAO coupDao = new CouponsDBDAO();
			System.out.println(coupDao.getCouponByType(CouponType.Restaurants));
		}
		
		public static void getCustomersWhoHaveCouponTest() throws CouponException, DoesNotExistException
		{
			CouponDAO coupDao = new CouponsDBDAO();
			coupDao.getCustomersWhoHaveCoupon(couponId)
		}

}



