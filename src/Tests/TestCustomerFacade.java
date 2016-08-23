package Tests;

import java.sql.SQLException;
//import java.time.LocalDate;

import DAO.CouponDAO;
import DBDAO.CouponDBDAO;

//import java.time.DayOfWeek;
//import java.time.LocalDate;
//import java.time.Month;
//import java.time.Year;

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
//import JavaBeans.CouponType;
import JavaBeans.Customer;


public class TestCustomerFacade {
	
	private static CustomerFacade custFacade = new CustomerFacade();

public static void main(String[] args) throws LoginException, CustomerException, CouponException, SQLException, DoesNotExistException, AlreadyExistException 
{
		TestLoginCustomer();
//		TestAddCoupon();
//	V	TestupdateAmount();
//	TestGetAllPurchasedCoupons();
//	X	TestgetAllPurchasedCouponsByType();
//	X	TestgetAllPurchasedCouponsByPrice();
}
	public static void TestLoginCustomer() throws CouponException, AlreadyExistException, DoesNotExistException, SQLException, LoginException
	{
			
			try {
				Customer customer= new Customer("Cutomer4", "Custi9876");
				custFacade.login(customer.getCustName(), customer.getPassWord(), ClientType.CUSTOMER) ;
				// customer logged in successfully
				
				custFacade.purchaseCoupon(38);
				
			} catch (FacadeException e) {
				
				e.printStackTrace();
			}
		
	}
	
	public static void TestAddCoupon() throws 
	CouponException, AlreadyExistException, DoesNotExistException, SQLException, LoginException
	{
		
//		CustomerDBDAO c = new CustomerDBDAO();
		CouponDAO Coup = new CouponDBDAO();
		
		try {
			custFacade.login("Cutomer4","Custi9876", ClientType.CUSTOMER);
		
		} catch (FacadeException e) {
			
			e.printStackTrace();
		}
		
		Coupon coup1= Coup.getCoupon(40);
	
		
//		Coupon coup2= new Coupon("Coupon2",
//				LocalDate.now(), 
//				LocalDate.of(2016, 04, 15),
//				3,CouponType.Camping, "What", 100, "Photo");
//		
		
		
//		Coup.createCoupon(coup2);
		//custFacade.purchaseCoupon(coup1);
//		System.out.println(coup1);
}	
	
	
	public static void  TestUpdateAmount()throws CouponException, AlreadyExistException, DoesNotExistException, SQLException{
		
	}
	
	public static void  TestGetAllPurchasedCoupons()throws CouponException, AlreadyExistException, DoesNotExistException, SQLException, CustomerException
	{
	
		System.out.println(custFacade.getAllPurchasedCoupons());
	}
	
	
	public static void  TestgetAllPurchasedCouponsByType()throws CouponException, AlreadyExistException, DoesNotExistException, SQLException, CustomerException
	{
//		custFacade.getAllPurchasedCouponsByType(CouponType.Camping);
		System.out.println(custFacade.getAllPurchasedCouponsByType(CouponType.Camping));

	}

	public static void  TestgetAllPurchasedCouponsByPrice()throws CouponException, AlreadyExistException, DoesNotExistException, SQLException, CustomerException
	{
		System.out.println(custFacade.getAllPurchasedCouponsByPrice(55));
	}


}
