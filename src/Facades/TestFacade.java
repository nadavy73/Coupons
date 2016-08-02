package Facades;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

import DAO.CustomerDAO;
import DBDAO.CustomerDBDAO;
import Exceptions.AlreadyExistException;
import Exceptions.CouponException;
import Exceptions.CustomerException;
import Exceptions.DoesNotExistException;
import Exceptions.FacadeException;
import Exceptions.LoginException;
import JavaBeans.Coupon;
import JavaBeans.CouponType;


public class TestFacade {
	private static CustomerFacade CustFacade= new CustomerFacade();

public static void main(String[] args) throws LoginException, CustomerException, CouponException, SQLException, DoesNotExistException, AlreadyExistException 
{
//	TestLoginCustomer();
	TestPurchasedCoupon();
}
	public static void TestLoginCustomer() throws CouponException, AlreadyExistException, DoesNotExistException, SQLException, LoginException
	{
		try {
			CustFacade= CustFacade.login("cust","omer", ClientType.CUSTOMER);
			if (CustFacade !=null)
			{
				System.out.println("Customer was Login Succssefuly");
			}
		} catch (FacadeException e) 
		{
			e.toString();
			e.printStackTrace();
		
		}
	}
	
	public static void TestPurchasedCoupon() throws CouponException, AlreadyExistException, DoesNotExistException, SQLException
	{
//		Coupon coupon123= new Coupon(
//					"Coupon12", 
//					LocalDate.now(), 
//					LocalDate.of(2016, Month.SEPTEMBER, 13),
//					3, 
//					CouponType.Garden,
//					"Message",
//					40,
//					"Image1"); 
//				
		Coupon coupon12= new Coupon("New Coupo"); 
			
			try {
				CustFacade.purchaseCoupon(coupon12);
			} catch (AlreadyExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			
			
//			for (Coupon coupon :CustFacade.p(CouponType.Electronics))
//			{
//				System.out.println(coupon);
//			}
			
			
			
	
}	
	
	
}
