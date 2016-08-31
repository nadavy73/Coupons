package Threads;


import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import DBDAO.CouponDBDAO;
import Exceptions.CouponException;
import Exceptions.DoesNotExistException;
import JavaBeans.Coupon;

public class DailyCouponExpirationTask implements Runnable {

	private volatile boolean run = true;
	// private CustomerDBDAO custDBDAO = new CustomerDBDAO();
	private CouponDBDAO couponDBDAO = new CouponDBDAO();

	// Remove Coupon if their end date has already gone
	public void run() {
		while (run) {
			try {
				for (Coupon coupon : couponDBDAO.getAllCoupons()) {
					if (LocalDate.now().isAfter(coupon.getEndDate())) {
						couponDBDAO.removeCoupon(coupon);
						
					}
				}
			} catch (CouponException | DoesNotExistException e ) {
				e.printStackTrace();
			}
			
			// Take a sleep for a day
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		
	}

	public void stopTask() {
		run = false;
	}

	

}