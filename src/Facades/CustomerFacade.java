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
 				throw new LoginException("Customer "+custName+" does not exist in our DB");
				}
		}	
	
	public void purchaseCoupon(Coupon coupon) 
			throws DoesNotExistException, AlreadyExistException, SQLException 
	{
		try {
			coupon = CouponSystem.getInstance().getCouponDAO().getCoupon(coupon.getId());
			} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		if (LocalDate.now().isAfter(coupon.getEndDate())) {
				try {
					throw new CouponException("Coupon Id no."+ coupon.getId() + " (" + coupon.getTitle() 
					+ ") was expired. Coupon End Date:" + coupon.getEndDate());
				} catch (CouponException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		if (coupon.getAmount() < 1) 
		{
			throw new DoesNotExistException("Coupon is out of stock");
		}
		
		else {
		
		try {
			CouponSystem.getInstance().getCustDAO().AddCustomerCouponById(customer.getId(), coupon.getId());
			} 
		catch (AlreadyExistException| SQLException e) 
			{
			throw new AlreadyExistException("Coupon was Already Purchased for this Cusotmer");
			} 
		}
		try {
			CouponSystem.getInstance().getCouponDAO().updateAmountOfCoupon(coupon.getId());
		} catch (SQLException e) {
			
		}
		
		}
		
	

	
	public Collection<Coupon> getAllPurchasedCoupons() 
			throws SQLException, DoesNotExistException
		{
			Collection<Coupon> coupons = CouponSystem.getInstance().getCustDAO().getCoupons(customer.getId());
			return coupons;
		}

	public Collection<Coupon> getAllPurchasedCouponsByType(CouponType CouponType) 
				throws SQLException, DoesNotExistException 
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
	
	public Collection<Coupon> getAllPurchasedCouponsByMaxPrice(double price) 
			throws SQLException, DoesNotExistException 
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
