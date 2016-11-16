package Tests_on_the_fly;

import java.sql.SQLException;
import DAO.CouponDAO;
import DBDAO.CouponDBDAO;
import Exceptions.AlreadyExistException;
import Exceptions.CouponException;
import Exceptions.CustomerException;
import Exceptions.DoesNotExistException;
import Exceptions.FacadeException;
import Exceptions.LoginException;
import JavaBeans.Coupon;
import JavaBeans.CouponType;
import JavaBeans.Customer;
import Facades.ClientType;
import Facades.CustomerFacade;



public class TestCustomerFacade {
	
	private static CustomerFacade custFacade = new CustomerFacade();

public static void main(String[] args) throws LoginException, CustomerException, CouponException, SQLException, DoesNotExistException, AlreadyExistException 
{
//	TestLoginCustomer();
//	TestAddCoupon();
	TestGetAllPurchasedCoupons();
//	TestgetAllPurchasedCouponsByType();
//	TestgetAllPurchasedCouponsByPrice();
}
	public static void TestLoginCustomer() throws CouponException, AlreadyExistException, DoesNotExistException, SQLException, LoginException, FacadeException
	{
		Customer customer= new Customer("Customer1020", "Custi9876");
		custFacade.login(customer.getCustName(), customer.getPassWord(), ClientType.CUSTOMER) ;
		// customer logged in successfully
		
	}
	
	public static void TestAddCoupon() 
			throws CouponException, AlreadyExistException, DoesNotExistException, SQLException, LoginException
	{
		
		try {
			CouponDAO Coup = new CouponDBDAO();
			Customer customer= new Customer("Customer23", "Custielel");
			custFacade.login(customer.getCustName(), customer.getPassWord(), ClientType.CUSTOMER) ;
			// customer logged in successfully
			
			Coupon coup1= Coup.getCoupon(41);
			custFacade.purchaseCoupon(coup1);
			
			} catch (FacadeException e) 
				{
				e.printStackTrace();
				}
	}	
	
	public static void  TestGetAllPurchasedCoupons()
			throws CouponException, AlreadyExistException, DoesNotExistException, SQLException, CustomerException, LoginException
	{
		try {
			
			Customer customer= new Customer("Customer99", "BLA");
			custFacade.login(customer.getCustName(), customer.getPassWord(), ClientType.CUSTOMER) ;
			// customer logged in successfully
			
			System.out.println(customer.toString()+ "\n"+ custFacade.getAllPurchasedCoupons());
		} catch (FacadeException e) 
		{
		e.printStackTrace();
		}
	}	
	
	public static void  TestgetAllPurchasedCouponsByType()
			throws CouponException, AlreadyExistException, DoesNotExistException, SQLException, CustomerException, LoginException
	{
		try {
			
			Customer customer= new Customer("Customer23", "Custielel");
			custFacade.login(customer.getCustName(), customer.getPassWord(), ClientType.CUSTOMER) ;
			// customer logged in successfully
			
			System.out.println(custFacade.getAllPurchasedCouponsByType(CouponType.Clothes));
			
			} 
		catch (FacadeException e) 
			{
			e.printStackTrace();
			}
	}

	public static void  TestgetAllPurchasedCouponsByMaxPrice()throws CouponException, AlreadyExistException, DoesNotExistException, SQLException, CustomerException, LoginException
	{
try {
			
			Customer customer= new Customer("Customer23", "Custielel");
			custFacade.login(customer.getCustName(), customer.getPassWord(), ClientType.CUSTOMER) ;
			// customer logged in successfully
			
			System.out.println(custFacade.getAllPurchasedCouponsByMaxPrice(90));
			
			} 
		catch (FacadeException e) 
			{
			e.printStackTrace();
			}
		
		
	}


}
