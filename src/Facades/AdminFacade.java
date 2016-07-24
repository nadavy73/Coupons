package Facades;

import java.awt.Window.Type;
import java.sql.SQLException;
import java.util.Collection;

import DAO.CompanyDAO;
import DAO.CouponDAO;
import DAO.CustomerDAO;
import DBDAO.CompanyDBDAO;
import DBDAO.CouponsDBDAO;
import DBDAO.CustomerDBDAO;
import Exceptions.AdminFacadeException;
import Exceptions.AlreadyExistException;
import Exceptions.CouponException;
import Exceptions.DoesNotExistException;
import Exceptions.FacadeException;
import Exceptions.LoginException;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.Customer;

public class AdminFacade implements CouponClientFacade
{

	/*
	 * Attributes
	 */
	private CustomerDAO custDAO;
	private CompanyDBDAO compDAO;
	private CouponDAO coupDAO;
	
	/*
	 * Constructors
	 */
		
	public AdminFacade() 
	{
		custDAO = new CustomerDBDAO();
		compDAO = new CompanyDBDAO();
		coupDAO= new CouponsDBDAO();
	}
	
	
	/*
	 * Functions
	 */
	public AdminFacade login(String name, String password, ClientType clientType) throws FacadeException{
		if (name.equals("admin") && password.equals("1234") && clientType.equals(ClientType.ADMIN)) 
		{
			return new AdminFacade();
		
		}
	
		return null;
	
}

	public void createCompany(Company company) throws AdminFacadeException, LoginException, AlreadyExistException, CouponException {
		try {
			// Call the createCompany method from CompanyDBDAO
			compDAO.createCompany(company);

			// If candidate already exist
		} catch (AlreadyExistException e) {

			// In case of a problem throw new AdminFacadeException
			throw new AdminFacadeException("AdminFacadeException - " 
					+ "createCompany() - Error", e);
		}
	}
//TODO:
	public void removeCompany (Company company) throws AdminFacadeException, FacadeException, DoesNotExistException {
		for (Coupon coupon : company.getCoupons()) 
		{
			// Remove coupon from company
			compDBDAO.removeCompanyCoupon(coupon.getId(), company.getId());
				
			//Remove coupon from all customers
			for (long custId : coupDBDAO.getCustomersId(coupon))
			{
				
			}
			// Remove coupons
			coupDBDAO.removeCoupon(coupon);
			}
			// Remove company
			compDAO.
	}
	
	public void UpdateCompany(Company company) throws DoesNotExistException, CouponException, SQLException 
	{
		compDAO.updateCompany(company);
	}
	
	public Company GetCompany (long compId)  throws DoesNotExistException, CouponException, SQLException
	{
		return compDAO.getCompany(compId);
	}
	
	public Company GetCompanyByName (String compName)throws DoesNotExistException, CouponException
	{
		return compDAO.getCompanyByName(compName);
	}
	
	public Collection<Company> getAllCompanies() throws DoesNotExistException, CouponException, SQLException 
	{
		return compDAO.getAllCompanies();
	}
	
	public void createCustomer(Customer customer) throws AdminFacadeException,AlreadyExistException, CouponException, SQLException 
	{
		custDAO.createCustomer(customer);

	}
	public void RemoveCustomer (Customer customer) throws DoesNotExistException, CouponException, SQLException{
		for (Coupon coupon : customer.getCoupons()) {
			custDAO.removeCustomer(customer);
		}
		
		custDAO.removeCustomer(customer);
	}
	
	public void UpdateCustomer (Customer customer) throws DoesNotExistException, CouponException, SQLException{
		custDAO.updateCustomer(customer);
	}
	
	public Customer GetCustomer (long custId) throws DoesNotExistException, CouponException, SQLException{
		return custDAO.getCustomerById(custId);
	}
	
	public Collection<Customer> getAllCustomer() throws DoesNotExistException, CouponException, SQLException {
	
		return custDAO.getAllCustomer();
}

	@Override
	public String toString() {
		return "AdminFacade [custDAO=" + custDAO + ", compDAO=" + compDAO + ", coupDAO=" + coupDAO + "]";
	}

	
	
				
				
			
		





		
		

	
}	
