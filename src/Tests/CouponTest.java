package Tests;

import java.sql.SQLException;
import java.time.LocalDate;

import DAO.CouponDAO;
import DBDAO.CouponsDBDAO;
import Exceptions.*;
import JavaBeans.Coupon;
import JavaBeans.CouponType;


public class CouponTest {

	public static void main(String[] args) throws CouponException, AlreadyExistException 
	{
		
		CreateCoupon();
//		RemoveCoupons ();
//		UpdateCoupons();
//		getCoupons();
	}
	
		public static void CreateCoupon() throws CouponException, AlreadyExistException
		{
		CouponDAO couponDAO= new CouponsDBDAO();
			for (int i = 0; i < 10; i++) {
			// Company instance
				Coupon coupon = new Coupon(
						"Title "+ (i+30), 
						LocalDate.now(),
						LocalDate.of(2016, 7, 10),
						((int)Math.random()*5),
						CouponType.Camping,
						"message" + (i+1),
						(i+20.99),
						"image" + (i+1));	
				
					}
		}

		public static void RemoveCoupons ()
		{
			CouponDAO couponDAO= new CouponsDBDAO();
			for (int i = 0; i < 10; i++) {
			// Company instance
				Coupon coupon = new Coupon("Title 30");
						
				
					try {
						couponDAO.removeCoupon(coupon);
					} catch (DaoException e) {
						// TODO Auto-generated catch block
						System.out.println("Error");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		}
		}
		public static void UpdateCoupons()
		{
			CouponDAO couponDAO= new CouponsDBDAO();
			
				Coupon coup = new Coupon();
				coup.setAmount(100);
				
				try{
					couponDAO.updateCoupon(coup);
					} catch (DaoException e) {
						// TODO Auto-generated catch block
						System.out.println("Error");
					}
		}

		public static void getCoupons() throws CouponException
		{
			CouponDAO couponDAO= new CouponsDBDAO();
			Coupon coup = new Coupon();	
				try{
					couponDAO.getCoupon(1);
					} catch (CouponException e) {
						// TODO Auto-generated catch block
						System.out.println("Error");
					}
		}

}



