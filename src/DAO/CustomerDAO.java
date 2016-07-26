package DAO;

import java.sql.SQLException;
import java.util.*;

import Exceptions.AlreadyExistException;
import Exceptions.CouponException;
import Exceptions.DoesNotExistException;
import JavaBeans.*;

public interface CustomerDAO 
{
	public void createCustomer (Customer customer)throws CouponException, AlreadyExistException, SQLException;
	
	public void removeCustomer (Customer customer)throws CouponException,DoesNotExistException, SQLException;
	
	public void updateCustomerByName (String OldName, String NewName)throws CouponException, SQLException;
	
	public void updateCustomer (Customer customer) throws CouponException, SQLException, DoesNotExistException;
	
	public Customer getCustomerById (long custId)throws CouponException, SQLException;
	
	public Customer getCustomerByName(String custName) throws CouponException, SQLException;

	public Collection<Customer> getAllCustomer ()throws CouponException, SQLException;
	
	public Collection<Coupon> getCoupons (long custID)throws CouponException, SQLException;
	
	public boolean login (String custName ,String password)throws CouponException, SQLException;

	public void removeCustomerCoupons(long couponId) throws CouponException;
	
	public void PurchaseCustomerCoupon(Customer customer, Coupon coupon) throws  CouponException;

	public void PurchaseCustomerCouponById(long custId, long couponId) throws CouponException;

	public boolean isPurchased(Coupon coupon, Customer customer) throws CouponException; 
		

}

