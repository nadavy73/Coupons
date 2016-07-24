package Threads;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import DAO.CouponDAO;
import Exceptions.CouponException;
import JavaBeans.Coupon;

public class DailyCouponExpirationTask implements Runnable 
{
	
	private boolean quit=false;
	private CouponDAO coupDAO;
	
	//Remove Coupon if their end date has already gone
	public void run() 
{
		while (!quit)
		{
			try {
				for (Coupon coupon : coupDAO.getAllCoupons())
						{
							if (LocalDate.now().isAfter(coupon.getEndDate()))
							{
								coupDAO.removeCoupon(coupon);
							}

							
						}
				} 
			catch (CouponException e) 
				{
				e.printStackTrace();
				}
		}
		
			//Take a sleep for a day
			try {
				TimeUnit.HOURS.sleep(24);
				} 
			catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
}	
	public void stopTask()
	{
		quit=true;
	
	
	}


}