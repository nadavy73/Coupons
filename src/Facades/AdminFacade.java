package Facades;


import java.sql.SQLException;
import java.util.Collection;
import Exceptions.*;
import JavaBeans.*;
import System.CouponSystem;

public class AdminFacade implements CouponClientFacade
{

	/*
	 * Methods
	 */
	public AdminFacade login(String name, String password, ClientType clientType) 
			throws LoginException
	{
		if (name.equals("admin") && password.equals("1234")) 
			{
			return new AdminFacade();
			}
		else 
			{
				throw new LoginException ("Incorrect username and/or password");
			}
		
	}

	public void createCompany(Company company) 
			throws AdminFacadeException, AlreadyExistException 
	{
		try {
			// Call the createCompany method from CompanyDBDAO
			try {
				CouponSystem.getInstance().getCompDAO().createCompany(company);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			// If candidate already exist
			} 
		catch (AlreadyExistException e) 
			{
			throw new AlreadyExistException("AlreadyExist - " 
					+ "createCompany()");
			}
	}
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
			CouponSystem.getInstance().getCompDAO().removeCompany(company);
			} 
		catch (DoesNotExistException |SQLException e) 
			{
			e.printStackTrace();
			} 
	}
	
	
	public void UpdateCompany(Company company) 
			throws AdminFacadeException 
	{
		try {
			CouponSystem.getInstance().getCompDAO().updateCompany(company);
			} 
		catch (DoesNotExistException | SQLException  e) 
			{
			throw new AdminFacadeException("AdminFacadeException - " 
					+ "createCompany()");
			} 
	}
	
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
	
	public void createCustomer(Customer customer) 
			throws AdminFacadeException,AlreadyExistException 
	{
		try {
			CouponSystem.getInstance().getCustDAO().createCustomer(customer);
			} 
		catch (SQLException e) 
			{
				throw new AdminFacadeException("AdminFacadeException - " 
					+ "createCustomer()");
			}

	}
	public void RemoveCustomer (Customer customer) 
			throws AdminFacadeException, DoesNotExistException
	{
		try 
			{
			for (Coupon coupon : CouponSystem.getInstance().getCustDAO().getCoupons(customer.getId())) 
				{
				CouponSystem.getInstance().getCustDAO().removeCustomerCouponsById(customer.getId(), coupon.getId());
				}
		
				CouponSystem.getInstance().getCustDAO().removeCustomer(customer);
			}
		catch (SQLException e) 
			{
				throw new AdminFacadeException("AdminFacadeException - " 
					+ "RemoveCustomer()");
			}
	}
	
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
	