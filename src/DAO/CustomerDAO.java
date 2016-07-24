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
	
	public void removeCustomer (String customer)throws CouponException, DoesNotExistException, SQLException;
	
	public void removeCustomer (Customer customer)throws CouponException,DoesNotExistException, SQLException;
	
	public void updateCustomer (String OldName, String NewName)throws CouponException, SQLException;
	
	public void updateCustomer (Customer customer) throws CouponException, SQLException;
	
	public Customer getCustomerById (long custId)throws CouponException, SQLException;
	
	public Customer getCustomerByName(String custName) throws CouponException, SQLException;

	public Collection<Customer> getAllCustomer ()throws CouponException, SQLException;
	
	public Collection<Coupon> getCoupons (long custID)throws CouponException, SQLException;
	
	public boolean login (String custName ,String password)throws CouponException, SQLException;

	public void removeCoupon(Customer customer, Coupon coupon) throws CouponException, SQLException;
	
	public void removeCoupon(long custId, long couponId) throws CouponException, SQLException;

	public void PurchaseCoupon(Customer customer, Coupon coupon) throws  CouponException;

	public void PurchaseCoupon(long custId, long couponId) throws CouponException;

}

