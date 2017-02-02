package Tests_on_the_fly;

import java.sql.SQLException;
import java.util.Collection;


import DAO.*;
import DBDAO.*;
import Exceptions.*;
import JavaBeans.*;

public class CustomerTest {

		public static void main(String[] args) throws CouponException, AlreadyExistException, DoesNotExistException, SQLException 
		{
//	V	CreateCustomerTest();
//		RemoveCustomerTest();
//			updateCustomerTest();
//	V	updateCustomerByNameTest();
//	V	getCustomerByIdTest();
//	V	getCustomerByNameTest();
		getAllCustomerTest();
//		getCouponsTest();
//	V	LoginTest();
//	V	AddCustomerCouponByIdTest();
//		RemoveCustomerCouponByIdTest();
		}
		public static void CreateCustomerTest() throws CouponException, AlreadyExistException, DoesNotExistException, SQLException
		{
		
		CustomerDAO custDAO= new CustomerDBDAO();
//		Customer cust= new Customer("Custi", "custiPass");
		Customer cust1= new Customer("Customer23", "12345678");
//		Customer cust2= new Customer("Cutomer2", "Custi9876");
		
//		try 
//			{
//			custDAO.createCustomer(cust);
//			custDAO.createCustomer(cust2);
//			System.out.println(custDAO.isCustomerExist("Customer99"));
			custDAO.createCustomer(cust1);

//			System.out.println("Customer " + cust.getCustName() + " was created");
//			System.out.println("Customer " + cust1.getCustName() + " was created");
//			System.out.println("Customer " + cust2.getCustName() + " was created");

//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		}
		
		public static void RemoveCustomerTest() throws CouponException, AlreadyExistException, DoesNotExistException, SQLException
		{
			CustomerDAO custDAO= new CustomerDBDAO();
			Customer cust= custDAO.getCustomerById(25);
			try 
				{
				custDAO.removeCustomerById(cust);
				System.out.println("Customer " + cust.getName() + " was removed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
		
		public static void updateCustomerTest() throws CouponException, AlreadyExistException, DoesNotExistException
		{
			CustomerDAO custDAO= new CustomerDBDAO();
			Customer cust1= new Customer("Customer", "Custi1234");
			try 
				{
				custDAO.updateCustomer(cust1);
				System.out.println("Customer " + "'"+cust1.getName()+"'" + " was Update");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}

		public static void updateCustomerByNameTest() throws CouponException, AlreadyExistException, DoesNotExistException
		{
			CustomerDAO custDAO= new CustomerDBDAO();
//			Customer cust1= new Customer("Custi", "Custi1234");
			Customer cust2= new Customer("Customer77", "Custi9876");
			try 
				{
				System.out.println(cust2.getName());
				cust2.setName("Customer88");
				custDAO.updateCustomer(cust2);
				System.out.println(cust2.getName());

				System.out.println("Customer " + cust2.getName() + " was Updated");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
		
		public static void getCustomerByIdTest() throws CouponException, AlreadyExistException, DoesNotExistException
		{
			CustomerDAO custDAO = new CustomerDBDAO();
//			Customer cust1= new Customer("Custi", "Custi1234");
//			Customer cust2= new Customer("Cutomer2", "Custi9876");
			try 
				{
//				custDAO.getCustomerById(1);
				Customer customerID= custDAO.getCustomerById(5);
				System.out.println(customerID);
//				System.out.println("The Customer with the ID "+ cust2.getId()+ " is " + cust2.getCustName());
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
		
		public static void getCustomerByNameTest() throws CouponException, AlreadyExistException, DoesNotExistException
		{
			CustomerDAO custDAO= new CustomerDBDAO();
//			Customer cust1= new Customer("Custi", "Custi1234");
				try {
					Customer customerID= custDAO.getCustomerByName("Customer23");
					System.out.println(customerID);
				} catch (SQLException e) {
					e.printStackTrace();
				}
//				Customer customerID= custDAO.getCustomer("Customer77", "Custi1234");
			
//				System.out.println("The Customer with the ID "+ cust2.getId()+ " is " + cust2.getCustName());
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
				e.printStackTrace();
			}
			}
			
		public static void getCouponsTest() throws CouponException, AlreadyExistException, DoesNotExistException, SQLException
		{
			CustomerDBDAO custDAO= new CustomerDBDAO();
//			Customer cust1= new Customer("New");
//			Customer cust2= new Customer("Cutomer2", "Custi9876");
//			
			
//			custDAO.getCoupons(5);
			System.out.println(custDAO.getCoupons(4).toString());
			
//			Collection <Coupon> CustomerCouponID= custDAO.getCoupons(cust1.getId());
//			System.out.println(CustomerCouponID);
//			System.out.println("The Customer with the ID "+ cust2.getId()+ " is " + cust2.getCustName());
			
//			try {
//					System.out.println(custDAO.getCoupons(4));
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
////				System.out.println("The Customer with the ID "+ cust2.getId()+ " is " + cust2.getCustName());
//				
				
			}

		public static void LoginTest() throws CouponException, AlreadyExistException, DoesNotExistException
		{
			CustomerDAO custDAO= new CustomerDBDAO();
//			Customer cust1= new Customer("Custi", "custiPass");
//			Customer cust2= new Customer("Cutomer2", "Custi9876");
			try 
				{
				custDAO.login("Cutomer1", "Custi1234");
//				System.out.println(Customer was successfullyNewC);
//				System.out.println("The Customer with the ID "+ cust2.getId()+ " is " + cust2.getCustName());
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}

		public static void AddCustomerCouponByIdTest() throws CouponException, AlreadyExistException, DoesNotExistException, SQLException
		{
			CustomerDAO custDAO= new CustomerDBDAO();
//			CouponDAO coupoDAO= new CouponDBDAO();
//			Customer cust1= new Customer("Custi", "custiPass");
//			Customer cust2= new Customer("Cutomer2", "Custi9876");		
//			Coupon coup1= coupoDAO.getCoupon(39);
			
//			System.out.println(Checks.isCouponExist(77));
//			System.out.println(custDAO.isCouponExist(35));
//			System.out.println("**************");
			custDAO.AddCustomerCouponById (7,39);
//			System.out.println(Checks.isPurchased(3,30));
//			System.out.println("**************");
		
		}
//		
		public static void RemoveCustomerCouponByIdTest() throws CouponException, AlreadyExistException, DoesNotExistException, SQLException
		{
			CustomerDAO custDAO= new CustomerDBDAO();
//			Customer cust1= new Customer("Custi", "custiPass");
//			Customer cust2= new Customer("Cutomer2", "Custi9876");
			
				custDAO.removeCustomerCouponsById(6, 35);
//				System.out.println(Customer was successfullyNewC);
//				System.out.println("The Customer with the ID "+ cust2.getId()+ " is " + cust2.getCustName());
				
			
			}
		}



		
