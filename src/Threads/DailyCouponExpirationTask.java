package Threads;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import DBDAO.CouponDBDAO;
import Exceptions.CouponException;
import Exceptions.DoesNotExistException;
import JavaBeans.Coupon;

public class DailyCouponExpirationTask implements Runnable 
{
	
	private boolean run=true;
	//	private CustomerDBDAO custDBDAO = new CustomerDBDAO();
	private CouponDBDAO couponDBDAO= new CouponDBDAO();
	
	//Remove Coupon if their end date has already gone
		public void run() 
	{
		while (run)
			{
			try {
//					for (Customer customer: custDBDAO.getAllCustomers())
//					{
						for (Coupon coupon : couponDBDAO.getAllCoupons())
						{
								if (LocalDate.now().isAfter(coupon.getEndDate()))
								{
									couponDBDAO.removeCoupon(coupon);
//									compDBDAO.removeCompanyCouponsById(coupon.getId());
//									custDBDAO.removeCustomerCouponsByCouponId(coupon.getId());
								}
						}
//					} 
				
				}
			catch (CouponException | DoesNotExistException | SQLException e) 
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
			run=false;
		
		}
		public void start() {
			// TODO Auto-generated method stub
			
		}


	}