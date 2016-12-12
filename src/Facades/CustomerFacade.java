package Facades;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import Exceptions.*;
import JavaBeans.*;
import System.CouponSystem;

public class CustomerFacade implements CouponClientFacade 
{
	//Attributes
	private Customer customer;

	//Constructors
	public CustomerFacade() {}
	
	@Override
	public CustomerFacade login(String custName, String password, ClientType clientType) 
			throws LoginException
	{
			boolean LoginAsCustomer= false;
		try {
			 LoginAsCustomer= CouponSystem.getInstance().getCustDAO().login(custName, password);
			} 
		catch (Exception e) 
			{
			throw new LoginException("Customer failed to login");
			}
			
			if (LoginAsCustomer && clientType.equals(ClientType.CUSTOMER))
					{
					System.out.println("Successful Customer Login");
					try 
						{
						customer= CouponSystem.getInstance().getCustDAO().getCustomerByName(custName);
						} 
					catch (SQLException | DoesNotExistException e) 
						{
						throw new LoginException("Customer "+custName+" does not exist in our DB");
						}
					return this;
					}
				
			else 
				{
 				throw new LoginException("one or more from your details are incorect User: " +custName+" Password: "+password+" or Client type: "+clientType);
				}
		}	
	
	public Coupon purchaseCoupon(long Couponid) 
			throws CustomerFacadeException, CouponException, DoesNotExistException, SQLException, AlreadyExistException
	{
		Coupon coupon = CouponSystem.getInstance().getCouponDAO().getCoupon(Couponid);
			try {
				
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
			finally {
				
			}
		

			
		}
		return coupon;
		
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
