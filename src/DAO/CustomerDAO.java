package DAO;

import java.sql.SQLException;
import java.util.*;

import Exceptions.AlreadyExistException;
import Exceptions.CouponException;
import Exceptions.DoesNotExistException;
import JavaBeans.*;

public interface CustomerDAO 
{
	public void createCustomer (Customer customer)throws CouponException, AlreadyExistException, SQLException,DoesNotExistException;
	
	public void removeCustomer (Customer customer)throws CouponException,DoesNotExistException, SQLException, DoesNotExistException;
	
	public void updateCustomerByName (String OldName, String NewName)throws CouponException, SQLException, DoesNotExistException;
	
	public void updateCustomer (Customer customer) throws CouponException, SQLException, DoesNotExistException;
	
	public Customer getCustomerById (long custId)throws CouponException, SQLException, DoesNotExistException;
	
	public Customer getCustomer(String custName, String password) throws CouponException, SQLException, DoesNotExistException;

	public Collection<Customer> getAllCustomers ()throws CouponException, SQLException;
	
	public Collection<Coupon> getCoupons (long custID)throws CouponException, SQLException, DoesNotExistException;
	
	public boolean login (String custName ,String password)throws CouponException, SQLException;

	public void removeCustomerCoupons(long couponId) throws CouponException, DoesNotExistException;
	
	public void PurchaseCustomerCoupon(Customer customer, Coupon coupon) throws  CouponException, AlreadyExistException, DoesNotExistException, SQLException;

	public void PurchaseCustomerCouponById(long custId, long couponId) throws CouponException, AlreadyExistException, DoesNotExistException, SQLException;

	public boolean isPurchased(Coupon coupon, Customer customer) throws CouponException, AlreadyExistException; 
		

}

