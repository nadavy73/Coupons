package Threads;


import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import Exceptions.DoesNotExistException;
import JavaBeans.Coupon;
import System.CouponSystem;

public class DailyCouponExpirationTask implements Runnable {

	private volatile boolean run = true;
	
	// Remove Coupon if their end date has already gone
	public void run() {
		while (run) {
			try 	
			{
			
			TimeUnit.HOURS.sleep(24);
			} 	
			catch (InterruptedException e) 
			{	
				System.out.println("bye bye");
				System.exit(0);
			}
			
			try 
				{
				for (Coupon coupon : CouponSystem.getInstance().getCouponDAO().getAllCoupons()) 
					{
					if (LocalDate.now().isAfter(coupon.getEndDate())) 
						{
						CouponSystem.getInstance().getCustDAO().removeCustomerCouponsByCouponId(coupon.getId());
						CouponSystem.getInstance().getCompDAO().removeCompanyCouponsById(coupon.getId());
						CouponSystem.getInstance().getCouponDAO().removeCoupon(coupon);
						}
				}	
			
			
			}
			catch (DoesNotExistException | SQLException e) 
				{
				System.out.println(LocalDate.now() + " - ERROR: Failed to auto delete coupon:");
				System.out.println(e.getMessage());
				}
			
			
		}
	}
		
		public void stopTask() 
		{
		run = false;
		}

	

}