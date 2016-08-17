package DAO;

import java.sql.SQLException;
import java.util.*;
import Exceptions.AlreadyExistException;
import Exceptions.CouponException;
import Exceptions.DoesNotExistException;
import JavaBeans.*;

public interface CouponDAO
{

	public void createCoupon(Coupon coupon)throws CouponException, AlreadyExistException, DoesNotExistException;

	public void removeCoupon(Coupon coupon)throws CouponException, SQLException, DoesNotExistException;
	
	public void updateCoupon(Coupon coupon)throws CouponException, DoesNotExistException, SQLException;
	
	public Coupon getCoupon (long id)throws CouponException, AlreadyExistException, DoesNotExistException;
	
	public Collection<Coupon> getAllCoupons ()throws CouponException, DoesNotExistException;
	
	public Collection<Coupon> getCouponByType (CouponType couponType)throws CouponException, DoesNotExistException;

	public Collection<Customer> getCustomersWhoHaveCoupon(long couponId) throws CouponException, AlreadyExistException, DoesNotExistException;

	public Coupon getCouponByTitle (String TITLE) throws CouponException, DoesNotExistException;
}
