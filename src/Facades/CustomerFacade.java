package Facades;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import DAO.CouponDAO;
import DAO.CustomerDAO;
import DBDAO.CouponsDBDAO;
import DBDAO.CustomerDBDAO;
import Exceptions.AlreadyExistException;
import Exceptions.CouponException;
import Exceptions.CustomerException;
import Exceptions.DoesNotExistException;
import Exceptions.FacadeException;
import Exceptions.LoginException;
import JavaBeans.Coupon;
import JavaBeans.CouponType;
import JavaBeans.Customer;

public class CustomerFacade implements CouponClientFacade 
{
	/*
	 * Attributes
	 */
	private Customer customer;
	private long custId;
	private String custName;
	private CustomerDAO custDAO = new CustomerDBDAO();
	private CouponDAO couponDAO = new CouponsDBDAO();
	private ClientType clientType;
	
	/*
	 * Constructors
	 */
		
		
	@Override
	public CustomerFacade login(String custName, String password, ClientType clientType) throws FacadeException, LoginException, CouponException, DoesNotExistException {
			boolean LoginAsCustomer= false;
		try {
			 LoginAsCustomer= custDAO.login(custName, password);
		} catch (Exception e) {
			throw new DoesNotExistException("Customer failed to login");
		}
			
			if (LoginAsCustomer && clientType.equals(ClientType.CUSTOMER))
				{
				System.out.println("Successful Customer Login");
				return this;
			
				} 
			else 
			{
				throw new LoginException("Customer Login Failed");
			}
		}	
	
	public void purchaseCoupon(Coupon coupon) throws CouponException, AlreadyExistException, DoesNotExistException, SQLException 
	{
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
	
	public Collection<Coupon> getAllPurchasedCoupons() throws CustomerException, SQLException, DoesNotExistException 
	{
					
		try {
		return custDAO.getCoupons(custId);
	
			}
		catch (CouponException e)
			{
			
			throw new CustomerException("CustomerException - getAllPurchasedCoupons Error");
			}
	}

	
	public Collection<Coupon> getAllPurchasedCouponsByType(CouponType CouponType) throws CustomerException, CouponException, SQLException, DoesNotExistException 
	{
		Collection<Coupon> couponsType= new HashSet<>();
		
		try {
			for (Coupon coupon: custDAO.getCoupons(custId)){
				if (coupon.getType().equals(CouponType)){
					couponsType.add(coupon);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return couponsType;
	}	
	public Collection<Coupon> getAllPurchasedCouponsByPrice(double price) throws CustomerException, CouponException, SQLException, DoesNotExistException 
	{
		Collection<Coupon> couponsByPrice= new HashSet<>();
		
		try {
			for (Coupon coupon: custDAO.getCoupons(custId)){
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
