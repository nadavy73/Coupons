package DAO;

import java.sql.SQLException;
import java.util.*;

import Exceptions.AlreadyExistException;
import Exceptions.CouponException;
import Exceptions.DoesNotExistException;
import JavaBeans.*;

public interface CustomerDAO 
{
	public void createCustomer (Customer customer)
			throws CouponException, AlreadyExistException, SQLException,DoesNotExistException;
	
	public void removeCustomer (Customer customer)
			throws CouponException,DoesNotExistException, SQLException, DoesNotExistException;
	
	public void updateCustomerByName (String OldName, String NewName)
			throws CouponException, SQLException, DoesNotExistException;
	
	public void updateCustomer (Customer customer) 
			throws CouponException, SQLException, DoesNotExistException;
	
	public Customer getCustomerById (long custId)
			throws CouponException, SQLException, DoesNotExistException, AlreadyExistException;
	
	public Customer getCustomerByName(String custName) 
			throws CouponException, SQLException, DoesNotExistException, AlreadyExistException;

	public Collection<Customer> getAllCustomers ()
			throws CouponException, SQLException, DoesNotExistException, AlreadyExistException;
	
	public Collection<Coupon> getCoupons (long custID)
			throws CouponException, DoesNotExistException, SQLException, AlreadyExistException;
	
	public boolean login (String custName ,String password)
			throws CouponException, SQLException;

	public void AddCustomerCouponById(long custId, long couponId) 
			throws CouponException, AlreadyExistException, DoesNotExistException, SQLException;
	
	public void removeCustomerCouponsById(long custId, long couponId) 
			throws CouponException, DoesNotExistException, AlreadyExistException, SQLException;
	
	public void removeCustomerCouponsByCouponId(long couponId)
			throws CouponException, DoesNotExistException, AlreadyExistException, SQLException;

}

