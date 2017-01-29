package DAO;

import java.sql.SQLException;
import java.util.*;

import Exceptions.AlreadyExistException;
import Exceptions.DoesNotExistException;
import JavaBeans.*;

public interface CustomerDAO 
{
	public long createCustomer (Customer customer)
			throws AlreadyExistException, SQLException;
	
	public void removeCustomer (Customer customer)
			throws DoesNotExistException, SQLException;
	
	public void updateCustomerByName (String OldName, String NewName)
			throws DoesNotExistException, SQLException;
	
	public void updateCustomer (Customer customer) 
			throws DoesNotExistException, SQLException;
	
	public Customer getCustomerById (long custId)
			throws DoesNotExistException, SQLException;
	
	public Customer getCustomerByName(String custName) 
			throws DoesNotExistException, SQLException;

	public Collection<Customer> getAllCustomers ()
			throws DoesNotExistException, SQLException;
	
	public Collection<Coupon> getCoupons (long custID)
			throws DoesNotExistException, SQLException;
	
	public boolean login (String username ,String password)
			throws SQLException;

	public void AddCustomerCouponById(long custId, long couponId) 
			throws AlreadyExistException, DoesNotExistException, SQLException;
	
	public void removeCustomerCouponsById(long custId, long couponId) 
			throws DoesNotExistException, SQLException;
	
	public void removeCustomerCouponsByCouponId(long couponId)
			throws DoesNotExistException, SQLException;

	public Collection<Coupon> getAllCouponsThatCanPurches() throws DoesNotExistException,
	SQLException;
}

