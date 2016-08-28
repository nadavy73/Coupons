package Facades;


import java.sql.SQLException;
import java.util.Collection;
import Exceptions.AdminFacadeException;
import Exceptions.AlreadyExistException;
import Exceptions.CouponException;
import Exceptions.DoesNotExistException;
import Exceptions.FacadeException;
import Exceptions.LoginException;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.Customer;
import System.CouponSystem;

public class AdminFacade implements CouponClientFacade
{

	/*
	 * Methods
	 */
	public AdminFacade login(String name, String password, ClientType clientType) throws FacadeException, LoginException{
		if (name.equals("admin") && password.equals("1234")) {
			return new AdminFacade();
		}
			else {
				throw new LoginException ("Incorrect username and/or password");
			}
		
		}

	public void createCompany(Company company) throws AdminFacadeException, LoginException, AlreadyExistException, CouponException, DoesNotExistException, SQLException {
		try {
			// Call the createCompany method from CompanyDBDAO
			CouponSystem.getInstance().getCompDAO().createCompany(company);
			

			// If candidate already exist
		} catch (AlreadyExistException e) {

			// In case of a problem throw new AdminFacadeException
			throw new AdminFacadeException("AdminFacadeException - " 
					+ "createCompany() - Error");
		}
	}
	public void removeCompany(Company company) 
			throws AdminFacadeException, FacadeException, DoesNotExistException, CouponException, AlreadyExistException, SQLException {
		for (Coupon coupon : CouponSystem.getInstance().getCompDAO().getCoupons(company.getId()))
		{
			// Remove coupon from company
			CouponSystem.getInstance().getCompDAO().removeCompanyCouponsById(company.getId(), coupon.getId());				
			//Remove coupon from all customers
			CouponSystem.getInstance().getCustDAO().removeCustomerCouponsByCouponId(coupon.getId());
			// Remove coupons
			CouponSystem.getInstance().getCouponDAO().removeCoupon(coupon);
		}
			// Remove company from DB
			CouponSystem.getInstance().getCompDAO().removeCompany(company);
	}
	
	
	public void UpdateCompany(Company company) throws DoesNotExistException, CouponException, SQLException 
	{
		CouponSystem.getInstance().getCompDAO().updateCompany(company);
	}
	
	public Company GetCompany (long compId)  throws DoesNotExistException, CouponException, SQLException, AlreadyExistException
	{
		return CouponSystem.getInstance().getCompDAO().getCompanyById(compId);
	}
	
	public Company GetCompanyByName (String compName)throws DoesNotExistException, CouponException, SQLException, AlreadyExistException
	{
		return CouponSystem.getInstance().getCompDAO().getCompanyByName(compName);
	}
	
	public Collection<Company> getAllCompanies() throws DoesNotExistException, CouponException, SQLException, AlreadyExistException 
	{
		return CouponSystem.getInstance().getCompDAO().getAllCompanies();
	}
	
	public void createCustomer(Customer customer) throws AdminFacadeException,AlreadyExistException, CouponException, SQLException, DoesNotExistException 
	{
		CouponSystem.getInstance().getCustDAO().createCustomer(customer);

	}
	public void RemoveCustomer (Customer customer) 
			throws DoesNotExistException, CouponException, SQLException, AlreadyExistException
	{
		for (Coupon coupon : CouponSystem.getInstance().getCustDAO().getCoupons(customer.getId())) 
			{
			CouponSystem.getInstance().getCustDAO().removeCustomerCouponsById(customer.getId(), coupon.getId());
			}
		
		CouponSystem.getInstance().getCustDAO().removeCustomer(customer);
	}
	
	public void UpdateCustomer (Customer customer) throws DoesNotExistException, CouponException, SQLException{
		CouponSystem.getInstance().getCustDAO().updateCustomer(customer);
	}
	
	public Customer GetCustomer (long custId) throws DoesNotExistException, CouponException, SQLException, AlreadyExistException{
		return CouponSystem.getInstance().getCustDAO().getCustomerById(custId);
	}
	
	public Collection<Customer> getAllCustomers() throws DoesNotExistException, CouponException, SQLException, AlreadyExistException {
	
		return CouponSystem.getInstance().getCustDAO().getAllCustomers();
}

	
}	
