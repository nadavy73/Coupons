package DAO;

import java.sql.SQLException;
import java.util.*;
import Exceptions.AlreadyExistException;
import Exceptions.CouponException;
import JavaBeans.*;

public interface CouponDAO
{

	public void createCoupon(Coupon coupon)throws CouponException, AlreadyExistException;

	public void removeCoupon(Coupon coupon)throws CouponException;
	
	public void updateCoupon(Coupon coupon)throws CouponException;
	
	public Coupon getCoupon (long id)throws CouponException;
	
	public Collection<Coupon> getAllCoupons ()throws CouponException;
	
	public Collection<Coupon> getCouponByType (CouponType couponType)throws CouponException;

	public Set<Long> getCustomersId(Coupon coupon);
}
