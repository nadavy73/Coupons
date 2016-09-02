package DAO;

import java.sql.SQLException;
import java.util.*;
import Exceptions.*;
import JavaBeans.*;

public interface CouponDAO
{

	public void createCoupon(Coupon coupon)
			throws AlreadyExistException,SQLException;

	public void removeCoupon(Coupon coupon)
			throws DoesNotExistException, SQLException;
	
	public void updateCoupon(Coupon coupon)
			throws DoesNotExistException, SQLException;
	
	public void updateAmountOfCoupon(long couponId) 
			throws DoesNotExistException, SQLException; 

	public Coupon getCouponByTitle (String TITLE) 
			throws DoesNotExistException, SQLException;
	
	public Coupon getCoupon (long id)
			throws DoesNotExistException, SQLException;
	
	public Collection<Coupon> getAllCoupons ()
			throws DoesNotExistException, SQLException;
	
	public Collection<Coupon> getCouponByType (CouponType couponType)
			throws DoesNotExistException, SQLException;

	public Collection<Customer> getCustomersWhoHaveCoupon(long couponId) 
			throws DoesNotExistException, SQLException;

	
}
