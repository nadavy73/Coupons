package Facades;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import DAO.CouponDAO;
import DAO.CustomerDAO;
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
	private CustomerDAO custDAO = null;
	private CouponDAO couponDAO = null;
	
	
	/*
	 * Constructors
	 */
		public CustomerFacade() 
		{
		
//		compDao = CouponSystem.getInstance().getCompDao();
		custDAO = CouponSystem.getInstance().getCustDAO();
		couponDAO = CouponSystem.getInstance().getCouponDAO();
	}
	
	
	@Override
	public CustomerFacade login(String custName, String password, ClientType clientType) 
			throws FacadeException, LoginException, CouponException, DoesNotExistException, SQLException 
	{
			boolean LoginAsCustomer= false;
		try {
			 LoginAsCustomer= custDAO.login(custName, password);
		} catch (Exception e) {
			throw new DoesNotExistException("Customer failed to login");
		}
			
			if (LoginAsCustomer && clientType.equals(ClientType.CUSTOMER))
				{
				System.out.println("Successful Customer Login");
				customer= custDAO.getCustomerByName(custName);
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
		coupon = couponDAO.getCoupon(coupon.getId());
		
		if (LocalDate.now().isAfter(coupon.getEndDate())) {
				throw new CouponException("Coupon Id no."+ coupon.getId() + " (" + coupon.getTitle() 
				+ ") was expired. Coupon End Date:" + coupon.getEndDate());
		}
		if (coupon.getAmount() < 1) 
		{
			throw new DoesNotExistException("Coupon is out of stock");
		}
		
		else {
		
		custDAO.AddCustomerCouponById(customer.getId(), coupon.getId());
		couponDAO.updateAmountOfCoupon(coupon.getId());
		
		
		}
		
	}
	
	public Collection<Coupon> getAllPurchasedCoupons() throws CustomerException, SQLException, DoesNotExistException, CouponException 
	{
				Collection<Coupon> coupons = custDAO.getCoupons(customer.getId());
				return coupons;
		}

	
	public Collection<Coupon> getAllPurchasedCouponsByType(CouponType CouponType) throws CustomerException, CouponException, SQLException, DoesNotExistException 
	{
		Collection<Coupon> couponsType= new HashSet<>();
		
		try {
			for (Coupon coupon: custDAO.getCoupons(customer.getId())){
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
			for (Coupon coupon: custDAO.getCoupons(customer.getId())){
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
