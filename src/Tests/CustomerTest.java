package Tests;

import java.sql.SQLException;
import java.util.Collection;

import DAO.CompanyDAO;
import DAO.CustomerDAO;
import DBDAO.CompanyDBDAO;
import DBDAO.CustomerDBDAO;
import Exceptions.AlreadyExistException;
import Exceptions.CouponException;
import Exceptions.DoesNotExistException;
import JavaBeans.Coupon;
import JavaBeans.Customer;

public class CustomerTest {

	public static void main(String[] args) throws CouponException, AlreadyExistException, DoesNotExistException 
	{
//		CreateCustomerTest();
//		RemoveCustomerTest();
//		updateCustomerByNameTest();
//		updateCustomerByNameTest();
//		getCustomerByIdTest();
//		getCustomerByNameTest();
//		getAllCustomerTest();
		getCouponsTest();
	}
	
		
		public static void CreateCustomerTest() throws CouponException, AlreadyExistException, DoesNotExistException
		{
		
		CustomerDAO custDAO= new CustomerDBDAO();
//		Customer cust= new Customer("Custi", "custiPass");
		Customer cust1= new Customer("Cutomer3", "Custi1234");
		Customer cust2= new Customer("Cutomer2", "Custi9876");
		
		try 
			{
//			custDAO.createCustomer(cust);
			custDAO.createCustomer(cust2);
			custDAO.createCustomer(cust1);

//			System.out.println("Customer " + cust.getCustName() + " was created");
			System.out.println("Customer " + cust1.getCustName() + " was created");
			System.out.println("Customer " + cust2.getCustName() + " was created");

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
			Customer cust1= new Customer("Custi", "Custi1234");
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
			Customer cust2= new Customer("Cutomer2", "Custi9876");
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
			Customer cust2= new Customer("Cutomer2", "Custi9876");
			try 
				{
				custDAO.getCustomerById(1);
				Customer customerID= custDAO.getCustomerByName("Castiel");
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
				Collection <Customer> customerID= custDAO.getAllCustomer();
				System.out.println(customerID);
//				System.out.println("The Customer with the ID "+ cust2.getId()+ " is " + cust2.getCustName());
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
		public static void getCouponsTest() throws CouponException, AlreadyExistException, DoesNotExistException
		{
			CustomerDAO custDAO= new CustomerDBDAO();
			Customer cust1= new Customer("Custi", "Custi1234");
//			Customer cust2= new Customer("Cutomer2", "Custi9876");
			try 
				{
//				custDAO.getAllCustomer();
				Collection <Coupon> CustomerCouponID= custDAO.getCoupons(cust1.getId());
				System.out.println(CustomerCouponID);
//				System.out.println("The Customer with the ID "+ cust2.getId()+ " is " + cust2.getCustName());
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}

			

}



		
