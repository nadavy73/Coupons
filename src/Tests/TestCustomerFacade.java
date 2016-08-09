package Tests;

import java.sql.SQLException;
//import java.time.DayOfWeek;
//import java.time.LocalDate;
//import java.time.Month;
//import java.time.Year;
import java.time.LocalDate;
import java.time.Month;

import DAO.CouponDAO;
import DBDAO.CouponsDBDAO;
//import DAO.CouponDAO;
//import DAO.CustomerDAO;
//import DBDAO.CouponsDBDAO;
import DBDAO.CustomerDBDAO;
import Exceptions.AlreadyExistException;
import Exceptions.CouponException;
import Exceptions.CustomerException;
import Exceptions.DoesNotExistException;
import Exceptions.FacadeException;
import Exceptions.LoginException;
//import JavaBeans.Coupon;
//import JavaBeans.CouponType;
//import JavaBeans.Customer;
import Facades.ClientType;
import Facades.CustomerFacade;
import JavaBeans.Coupon;
import JavaBeans.CouponType;
import JavaBeans.Customer;


public class TestCustomerFacade {
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
		CustomerDBDAO c = new CustomerDBDAO();
		CouponDAO Coup = new CouponsDBDAO();
		
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
		
		Coupon coupon09 = new Coupon("Coupon new",LocalDate.of(2016, Month.AUGUST, 01),LocalDate.of(2016, Month.AUGUST, 31), 25, CouponType.Camping, "chipiiiiii", 55, "nnn");
		
//		Coup.createCoupon(coupon09);
			
//				c.PurchaseCustomerCoupon(c.getCustomerById(4), Coup.getCoupon(34));
				c.PurchaseCustomerCouponById(1,7);	
//				c.PurchaseCustomerCoupon(c.getCustomer("Custi"), coupon09);
	
}	
	
	
}
