package Tests;

import java.sql.SQLException;
import java.util.Collection;

//import DAO.CompanyDAO;
//import DAO.CouponDAO;
import DAO.CustomerDAO;
//import DBDAO.CompanyDBDAO;
import DBDAO.CustomerDBDAO;
import Exceptions.AlreadyExistException;
import Exceptions.CouponException;
import Exceptions.DoesNotExistException;
import JavaBeans.Coupon;
import JavaBeans.Customer;

public class CustomerTest {

		public static void main(String[] args) throws CouponException, AlreadyExistException, DoesNotExistException, SQLException 
	{
//		CreateCustomerTest();
//		RemoveCustomerTest();
//		updateCustomerByNameTest();
//		updateCustomerByNameTest();
//		getCustomerByIdTest();
//		getCustomerByNameTest();
//		getAllCustomerTest();
//		getCouponsTest();
//		LoginTest();
		PurchaseCustomerCouponByIdTest();
//		PurchaseCustomerCouponTest();
//		RemoveCustomerCouponTest();
		
	}
	
		public static void CreateCustomerTest() throws CouponException, AlreadyExistException, DoesNotExistException
		{
		
		CustomerDAO custDAO= new CustomerDBDAO();
//		Customer cust= new Customer("Custi", "custiPass");
		Customer cust1= new Customer("Cutomer10", "Custielel");
//		Customer cust2= new Customer("Cutomer2", "Custi9876");
		
		try 
			{
//			custDAO.createCustomer(cust);
//			custDAO.createCustomer(cust2);
			custDAO.createCustomer(cust1);

//			System.out.println("Customer " + cust.getCustName() + " was created");
			System.out.println("Customer " + cust1.getCustName() + " was created");
//			System.out.println("Customer " + cust2.getCustName() + " was created");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		public static void RemoveCustomerTest() throws CouponException, AlreadyExistException, DoesNotExistException
		{
			CustomerDAO custDAO= new CustomerDBDAO();
			Customer cust= new Customer("Custi", "custiPass");
			try 
				{
				custDAO.removeCustomer(cust);
				System.out.println("Customer " + cust.getCustName() + " was removed");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		
		public static void updateCustomerTest() throws CouponException, AlreadyExistException, DoesNotExistException
		{
			CustomerDAO custDAO= new CustomerDBDAO();
			Customer cust1= new Customer("Custi", "Custi1234");
			try 
				{
				custDAO.updateCustomer(cust1);
				System.out.println("Customer " + cust1.getCustName() + " was Update");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}

		public static void updateCustomerByNameTest() throws CouponException, AlreadyExistException, DoesNotExistException
		{
			CustomerDAO custDAO= new CustomerDBDAO();
//			Customer cust1= new Customer("Custi", "Custi1234");
			Customer cust2= new Customer("Cutomer2", "Custi9876");
			try 
				{
				custDAO.updateCustomerByName("Customer", cust2.getCustName());
//				System.out.println("Customer " + cust1.getCustName() + " was Update to "+ cust2.getCustName());
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		
		public static void getCustomerByIdTest() throws CouponException, AlreadyExistException, DoesNotExistException
		{
			CustomerDAO custDAO= new CustomerDBDAO();
//			Customer cust1= new Customer("Custi", "Custi1234");
//			Customer cust2= new Customer("Cutomer2", "Custi9876");
			try 
				{
//				custDAO.getCustomerById(1);
				Customer customerID= custDAO.getCustomerById(5);
				System.out.println(customerID);
//				System.out.println("The Customer with the ID "+ cust2.getId()+ " is " + cust2.getCustName());
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		
		public static void getCustomerByNameTest() throws CouponException, AlreadyExistException, DoesNotExistException
		{
			CustomerDAO custDAO= new CustomerDBDAO();
//			Customer cust1= new Customer("Custi", "Custi1234");
//			Customer cust2= new Customer("Cutomer2", "Custi9876");
			try 
				{
				custDAO.getCustomerById(1);
				Customer customerID= custDAO.getCustomer("Castiel", "cust1234");
				System.out.println(customerID);
//				System.out.println("The Customer with the ID "+ cust2.getId()+ " is " + cust2.getCustName());
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		
		public static void getAllCustomerTest() throws CouponException, AlreadyExistException, DoesNotExistException
		{
			CustomerDAO custDAO= new CustomerDBDAO();
//			Customer cust1= new Customer("Custi", "Custi1234");
//			Customer cust2= new Customer("Cutomer2", "Custi9876");
			try 
				{
//				custDAO.getAllCustomer();
				Collection <Customer> customerID= custDAO.getAllCustomers();
				System.out.println(customerID);
//				System.out.println("The Customer with the ID "+ cust2.getId()+ " is " + cust2.getCustName());
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
		public static void getCouponsTest() throws CouponException, AlreadyExistException, DoesNotExistException, SQLException
		{
			CustomerDAO custDAO= new CustomerDBDAO();
//			Customer cust1= new Customer("New");
//			Customer cust2= new Customer("Cutomer2", "Custi9876");
//			
			
			custDAO.getCoupons(5);
			System.out.println(custDAO.getCoupons(4).toString());
			
//			Collection <Coupon> CustomerCouponID= custDAO.getCoupons(cust1.getId());
//			System.out.println(CustomerCouponID);
//			System.out.println("The Customer with the ID "+ cust2.getId()+ " is " + cust2.getCustName());
			
//			try {
//					System.out.println(custDAO.getCoupons(4));
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
////				System.out.println("The Customer with the ID "+ cust2.getId()+ " is " + cust2.getCustName());
//				
				
			}

		public static void LoginTest() throws CouponException, AlreadyExistException, DoesNotExistException
		{
			CustomerDAO custDAO= new CustomerDBDAO();
			Customer cust1= new Customer("Custi", "custiPass");
//			Customer cust2= new Customer("Cutomer2", "Custi9876");
			try 
				{
				custDAO.login(cust1.getCustName(), "custiPass");
//				System.out.println(Customer was successfullyNewC);
//				System.out.println("The Customer with the ID "+ cust2.getId()+ " is " + cust2.getCustName());
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}

		public static void PurchaseCustomerCouponByIdTest() throws CouponException, AlreadyExistException, DoesNotExistException, SQLException
		{
			CustomerDAO custDAO= new CustomerDBDAO();
			
//			Customer cust1= new Customer("Custi", "custiPass");
//			Customer cust2= new Customer("Cutomer2", "Custi9876");		
//			Coupon coup1= new Coupon(8, "New 5 Coupon");
			
//			custDAO.isCouponExist(7);
			System.out.println(custDAO.isCouponExist(38));
//			custDAO.PurchaseCustomerCouponById(4, coup1.getId());
		}
		
		public static void PurchaseCustomerCouponTest() throws CouponException, AlreadyExistException, DoesNotExistException, SQLException
		{
			CustomerDAO custDAO= new CustomerDBDAO();
			
//			Customer cust1= new Customer(4);
//			Customer cust2= new Customer("Cutomer2", "Custi9876");		
			Coupon coup1= new Coupon(5, "Burgers");
			
			custDAO.PurchaseCustomerCoupon(custDAO.getCustomerById(3), coup1);
				

		
//		custDAO.isPurchased(coup1, cust1);
		
		}
		
		public static void RemoveCustomerCouponTest() throws CouponException, AlreadyExistException, DoesNotExistException
		{
			CustomerDAO custDAO= new CustomerDBDAO();
//			Customer cust1= new Customer("Custi", "custiPass");
//			Customer cust2= new Customer("Cutomer2", "Custi9876");
			
				custDAO.removeCustomerCoupons(9);
//				System.out.println(Customer was successfullyNewC);
//				System.out.println("The Customer with the ID "+ cust2.getId()+ " is " + cust2.getCustName());
				
			
			}
		
		
}



		
