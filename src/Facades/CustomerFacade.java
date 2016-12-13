package Facades;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

import Exceptions.*;
import JavaBeans.*;
import System.CouponSystem;

public class CustomerFacade 
{
	//Attributes
	private Customer customer;

	//Constructors
	public CustomerFacade(String username, String password) throws CustomerFacadeException, DoesNotExistException, SQLException {
		customer = CouponSystem.getInstance().getCustDAO().getCustomerByName(username);
	}
	
	public static CustomerFacade login(String username, String password) throws CustomerFacadeException, SQLException, DoesNotExistException {
			// if true - return new CustomerFacade instance with a specific Customer 
			if (CouponSystem.getInstance().getCustDAO().login(username, password)) {
				return new CustomerFacade(username, password);
			} 
			return null;
				}
	
	public void purchaseCoupon(Coupon coupon) 
			throws CustomerFacadeException, CouponException, DoesNotExistException
	{
			try {
				coupon = CouponSystem.getInstance().getCouponDAO().getCoupon(coupon.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			 
		if (LocalDate.now().isAfter(coupon.getEndDate())) 
				{
				throw new CouponException("Coupon Id no."+ coupon.getId() + " (" + coupon.getTitle() 
					+ ") was expired. Coupon End Date:" + coupon.getEndDate());
				}
		if (coupon.getAmount() < 1) 
		{
			throw new DoesNotExistException("Coupon is out of stock");
		}
		
		else {
		
			try 
			{
			CouponSystem.getInstance().getCustDAO().AddCustomerCouponById(customer.getId(), coupon.getId());
			
			CouponSystem.getInstance().getCouponDAO().updateAmountOfCoupon(coupon.getId());

			} 
		catch (AlreadyExistException| SQLException e) 
			{
			throw new CustomerFacadeException("Coupon was Already Purchased for this Cusotmer");
			} 
		}
		
	}
		
	
	public Collection<Coupon> getAllPurchasedCoupons() 
			throws CustomerFacadeException
		{
			
			try {
				return CouponSystem.getInstance().getCustDAO().getCoupons(customer.getId());
				
			} catch (SQLException| DoesNotExistException e) {
				
				throw new CustomerFacadeException("CustomerFacade - getAllPurchasedCoupons"+ e.getMessage());
				
			}
			
		}

	public Collection<Coupon> getAllPurchasedCouponsByType(CouponType CouponType) 
				throws CustomerFacadeException, DoesNotExistException 
	{
		Collection<Coupon> couponsType= new HashSet<>();
		
		try {
			for (Coupon coupon: CouponSystem.getInstance().getCustDAO().getCoupons(customer.getId()))
				{
					if (coupon.getType().equals(CouponType))
					{
						couponsType.add(coupon);
					}
				}
			} 
		catch (SQLException | DoesNotExistException e) 
				{
				throw new CustomerFacadeException("CustomerFacade - getAllPurchasedCouponsByType"+ e.getMessage());
				}
		if(couponsType.isEmpty())
		{
			throw new DoesNotExistException ("this customer does not have any coupons from this type: "+CouponType);
		}
		return couponsType;
	}	
	
	public Collection<Coupon> getAllPurchasedCouponsByMaxPrice(double price) 
			throws CustomerFacadeException, DoesNotExistException
	{
		Collection<Coupon> couponsByPrice= new HashSet<>();
		
		try {
			for (Coupon coupon: CouponSystem.getInstance().getCustDAO().getCoupons(customer.getId())){
				if (coupon.getPrice()<=(price)){
					couponsByPrice.add(coupon);
				}
			}
		} catch (SQLException| DoesNotExistException e) {
			
			throw new CustomerFacadeException("CustomerFacade - getAllPurchasedCouponsByMaxPrice"+ e.getMessage());
		}
		if (couponsByPrice.isEmpty())
			throw new DoesNotExistException("This customer does not have any coupon in price range: 0 to "+price);
		return couponsByPrice;
	}

	@Override
	public String toString() {
		return "CustomerFacade [customer=" + customer + "]";
	}

	
	
	

	
		
	
		

}
