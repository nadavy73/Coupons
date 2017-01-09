package Facades;

import java.sql.SQLException;
import java.util.Collection;


import Exceptions.*;
import JavaBeans.*;
import System.CouponSystem;

public class AdminFacade
{

	
	/*
	 * Methods
	 */
	public static AdminFacade login(String username, String password) 
			throws LoginException
	{
		if (username.equals("admin") && password.equals("1234"))
			{
			System.out.println("Admin logged successfully");
			return new AdminFacade();
			}
		else {
			throw new LoginException("Admin Login Failed");
		}
			
	}


	//
	// Update Company
	//
	public void UpdateCompany(Company company) 
			throws AdminFacadeException 
	{
		try {
			CouponSystem.getInstance().getCompDAO().updateCompany(company);
			} 
		catch (DoesNotExistException | SQLException  e) 
			{
				throw new AdminFacadeException("AdminFacadeException - " 
					+ "updateCompany()");
			} 
	}
	
	
	//
	// Remove Company
	//
	public void removeCompany(Company company) 
			throws AdminFacadeException
	{
		try {
				for (Coupon coupon : CouponSystem.getInstance().getCompDAO().getCoupons(company.getId()))
				{
				// Remove coupon from company
				CouponSystem.getInstance().getCompDAO().removeCompanyCouponsById(company.getId(), coupon.getId());				
				//Remove coupon from all customers
				CouponSystem.getInstance().getCustDAO().removeCustomerCouponsByCouponId(coupon.getId());
				// Remove coupons
				CouponSystem.getInstance().getCouponDAO().removeCoupon(coupon);
				}
			} 
		catch (DoesNotExistException |SQLException e) 
			{
			e.printStackTrace();
			} 
			
				// Remove company from DB
		try {
			CouponSystem.getInstance().getCompDAO().removeCompanyById(company);
			} 
		catch (DoesNotExistException |SQLException e) 
			{
			e.printStackTrace();
			} 
	}
	
	
	//
	// Get Company
	//
	public Company GetCompany (long compId)  
			throws AdminFacadeException, DoesNotExistException
	{
		try {
			return CouponSystem.getInstance().getCompDAO().getCompanyById(compId);
			} 
		catch (SQLException e) {
			throw new AdminFacadeException("AdminFacadeException - " 
					+ "GetCompany()");
			}
	}
	
	//
	//Get Company By Name
	//
	public Company GetCompanyByName (String compName)
			throws AdminFacadeException, DoesNotExistException
	{
		try {
			return CouponSystem.getInstance().getCompDAO().getCompanyByName(compName);
			} 
		catch (SQLException e) 
			{
			throw new AdminFacadeException("AdminFacadeException - " 
					+ "GetCompanyByName()");
			}
	}
	
	//
	//Get All Companies
	//
	public Collection<Company> getAllCompanies() 
			throws AdminFacadeException, DoesNotExistException
	{
		try {
			return CouponSystem.getInstance().getCompDAO().getAllCompanies();
			} 
		catch (SQLException e) 
			{
				throw new AdminFacadeException("AdminFacadeException - " 
					+ "GetCompanyByName()");
			}
	}
	

	
	//
	//Create Company
	//
	public long createCompany(Company company) 
			throws AdminFacadeException, AlreadyExistException 
	{
		try {
			long companyId = -1;
			// Call the createCompany method from CompanyDBDAO
			try {
				companyId =  CouponSystem.getInstance().getCompDAO().createCompany(company);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return companyId;
			// If candidate already exist
		} 
		catch (AlreadyExistException e) 
		{
			throw new AlreadyExistException("AlreadyExist - " 
					+ "createCompany()");
		}
	};
	
	
	//
	//Create Customer
	//
	public long createCustomer(Customer customer) 
			throws AdminFacadeException,AlreadyExistException 
	{
		try {
			long customerId = -1;
			// Call the createCustomer method from CustomerDBDAO
			try {
				customerId =  CouponSystem.getInstance().getCustDAO().createCustomer(customer);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return customerId;
			// If candidate already exist
		} 
		catch (AlreadyExistException e) 
		{
			throw new AlreadyExistException("AlreadyExist - " 
					+ "createCustomer()");
		}
	};
	
	
	
	//
	// Remove Customer
	//

	public void RemoveCustomer (Customer customer) 
			throws AdminFacadeException, DoesNotExistException
	{

		try 
			{
			for (Coupon coupon : CouponSystem.getInstance().getCustDAO().getCoupons(customer.getId())) 
				{

				CouponSystem.getInstance().getCustDAO().removeCustomerCouponsByCouponId(coupon.getId());
				}
			CouponSystem.getInstance().getCustDAO().removeCustomer(customer);

			}
		catch (SQLException e) 
			{
				throw new AdminFacadeException("AdminFacadeException - " 
					+ "RemoveCustomer()");
			}
	}
	
	//
	// Update Customer
	//
	public void UpdateCustomer (Customer customer) 
			throws AdminFacadeException, DoesNotExistException
	{
		try {
			CouponSystem.getInstance().getCustDAO().updateCustomer(customer);
			} 
		catch (SQLException e) 
			{
				throw new AdminFacadeException("AdminFacadeException - " 
					+ "UpdateCustomer()");
			}
	}
	
	
	//
	// Get Customer By Id
	//
	public Customer GetCustomer (long custId) 
			throws AdminFacadeException, DoesNotExistException
	{
		try {
			return CouponSystem.getInstance().getCustDAO().getCustomerById(custId);
			} 
			catch (SQLException e) 
			{
				throw new AdminFacadeException("AdminFacadeException - " 
					+ "GetCustomer()");
			}
	}
	
	
	//
	// Get All Customers
	//
	public Collection<Customer> getAllCustomers() 
			throws AdminFacadeException, DoesNotExistException 
	{
	
		try {
			return CouponSystem.getInstance().getCustDAO().getAllCustomers();
			} 
		catch (SQLException e) 
			{
				throw new AdminFacadeException("AdminFacadeException - " 
					+ "getAllCustomers()");
			}
	}

	
	
}
	