package Facades;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import Exceptions.AlreadyExistException;
import Exceptions.CouponException;
import Exceptions.CustomerException;
import Exceptions.DoesNotExistException;
import Exceptions.FacadeException;
import Exceptions.LoginException;
import JavaBeans.Coupon;
import JavaBeans.CouponType;
import JavaBeans.Customer;
import System.CouponSystem;

public class CustomerFacade implements CouponClientFacade 
{
	/*
	 * Attributes
	 */
	private Customer customer;

	public CustomerFacade() {}
	
	@Override
	public CustomerFacade login(String custName, String password, ClientType clientType) 
			throws FacadeException, LoginException, CouponException, DoesNotExistException, SQLException, AlreadyExistException 
	{
			boolean LoginAsCustomer= false;
		try {
			 LoginAsCustomer= CouponSystem.getInstance().getCustDAO().login(custName, password);
		} catch (Exception e) {
			throw new LoginException("Customer failed to login");
		}
			
			if (LoginAsCustomer && clientType.equals(ClientType.CUSTOMER))
				{
				System.out.println("Successful Customer Login");
				customer= CouponSystem.getInstance().getCustDAO().getCustomerByName(custName);
				return this;
			
				} 
			else 
			{
				throw new LoginException("Customer Login Failed");
			}
		}	
	
	public void purchaseCoupon(Coupon coupon) 
			throws CouponException, AlreadyExistException, DoesNotExistException, SQLException 
	{
		coupon = CouponSystem.getInstance().getCouponDAO().getCoupon(coupon.getId());
		
		if (LocalDate.now().isAfter(coupon.getEndDate())) {
				throw new CouponException("Coupon Id no."+ coupon.getId() + " (" + coupon.getTitle() 
				+ ") was expired. Coupon End Date:" + coupon.getEndDate());
		}
		if (coupon.getAmount() < 1) 
		{
			throw new DoesNotExistException("Coupon is out of stock");
		}
		
		else {
		
		CouponSystem.getInstance().getCustDAO().AddCustomerCouponById(customer.getId(), coupon.getId());
		CouponSystem.getInstance().getCouponDAO().updateAmountOfCoupon(coupon.getId());
		
		}
		
	}
	
	public Collection<Coupon> getAllPurchasedCoupons() throws CustomerException, SQLException, DoesNotExistException, CouponException, AlreadyExistException 
	{
			Collection<Coupon> coupons = CouponSystem.getInstance().getCustDAO().getCoupons(customer.getId());
			return coupons;
		}

	
	public Collection<Coupon> getAllPurchasedCouponsByType(CouponType CouponType) throws CustomerException, CouponException, SQLException, DoesNotExistException 
	{
		Collection<Coupon> couponsType= new HashSet<>();
		
		try {
			for (Coupon coupon: CouponSystem.getInstance().getCustDAO().getCoupons(customer.getId())){
				if (coupon.getType().equals(CouponType)){
					couponsType.add(coupon);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return couponsType;
	}	
	public Collection<Coupon> getAllPurchasedCouponsByMaxPrice(double price) throws CustomerException, CouponException, SQLException, DoesNotExistException 
	{
		Collection<Coupon> couponsByPrice= new HashSet<>();
		
		try {
			for (Coupon coupon: CouponSystem.getInstance().getCustDAO().getCoupons(customer.getId())){
				if (coupon.getPrice()<=(price)){
					couponsByPrice.add(coupon);
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return couponsByPrice;
	}

	@Override
	public String toString() {
		return "CustomerFacade [customer=" + customer + "]";
	}

	
	
	

	
		
	
		

}
