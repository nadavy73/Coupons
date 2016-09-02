package Threads;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;
import Exceptions.DoesNotExistException;
import JavaBeans.Coupon;
import System.CouponSystem;

public class DailyCouponExpirationTask implements Runnable {

	private volatile boolean run = true;
	// private CustomerDBDAO custDBDAO = new CustomerDBDAO();
	

	// Remove Coupon if their end date has already gone
	public void run() {
		while (run) {
			try {
				for (Coupon coupon : CouponSystem.getInstance().getCouponDAO().getAllCoupons()) 
				{
					if (LocalDate.now().isAfter(coupon.getEndDate())) 
					{
						CouponSystem.getInstance().getCouponDAO().removeCoupon(coupon);
						CouponSystem.getInstance().getCompDAO().removeCompanyCouponsById(coupon.getId());
						CouponSystem.getInstance().getCustDAO().removeCustomerCouponsByCouponId(coupon.getId());
					}
				}
					// Take a sleep for a day
						try {
							TimeUnit.SECONDS.sleep(10);
				
						} 	catch (InterruptedException e) 
							{
							e.printStackTrace();
							}
			} 
				catch (DoesNotExistException e ) 
				{
				e.printStackTrace();
				} 
				catch (SQLException e) 
				{
				
				e.printStackTrace();
			}
			
			
		}

		
	}

	public void stopTask() {
		run = false;
	}

	

}