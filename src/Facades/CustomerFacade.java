package Facades;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

import DAO.CompanyDAO;
import DAO.CouponDAO;
import DAO.CustomerDAO;
import DBDAO.CompanyDBDAO;
import DBDAO.CouponsDBDAO;
import DBDAO.CustomerDBDAO;
import Exceptions.AlreadyExistException;
import Exceptions.CouponException;
import Exceptions.CustomerException;
import Exceptions.DoesNotExistException;
import Exceptions.FacadeException;
import Exceptions.LoginException;
import JavaBeans.Company;
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
	private static CustomerDAO custDAO;
	private CouponDAO coupDAO;
	
	/*
	 * Constructors
	 */
		
	public CustomerFacade() 
	{
		custDAO = new CustomerDBDAO();
		coupDAO= new CouponsDBDAO();
	}	
	
	public CustomerFacade(String custName, String custPassword) throws CustomerException, SQLException, DoesNotExistException {
		
			try {
				customer = custDAO.getCustomerByName(custName);
			
			} 
			catch (CouponException e) {
				throw new CustomerException("CustomerFacadeException - "
						+ "Constructor error", e);
			}
			
		
	}
	
	public static CustomerFacade login(String custName, String password) throws FacadeException, LoginException {
		try {
			if (custDAO.login(custName, password))
				{
					return new CustomerFacade(custName, password);
				}
			} 	
		catch (Exception e) 
			{
			throw new LoginException("Failed to login.");
			}
	
	
	return null;
	}	
	
	public void purchaseCoupon(Coupon coupon) throws CouponException, AlreadyExistException, DoesNotExistException, SQLException 
	{
		if (coupon.getAmount()<1)
		{
			try {
				throw new CouponException("Coupon ID:" + coupon.getId() 
				+ " (" + coupon.getTitle() + ") is not in stock");
			} catch (CouponException e) {
				
			}
		}
		
		if (LocalDate.now().isAfter(coupon.getEndDate()))
		{
			try {
				throw new CouponException("Coupon ID:" + coupon.getId() + " (" + coupon.getTitle() 
				+ ") is expired. EndDate:" + coupon.getEndDate());
			} catch (CouponException e) {
				
			}
		}
		
		if (custDAO.isPurchased(coupon, customer))
		{
			try {
				throw new AlreadyExistException("Coupon was Already purchased");
			} catch (AlreadyExistException e) {
				
			}
		}
	
		custDAO.PurchaseCustomerCouponById(customer.getId(), coupon.getId());
		coupon.setAmount(coupon.getAmount()-1);
		coupDAO.updateCoupon(coupon);
		
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return couponsType;
	}	
	public Collection<Coupon> getAllPurchasedCouponsByType(double price) throws CustomerException, CouponException, SQLException, DoesNotExistException 
	{
		Collection<Coupon> couponsByPrice= new HashSet<>();
		
		try {
			for (Coupon coupon: custDAO.getCoupons(custId)){
				if (coupon.getPrice()<=(price)){
					couponsByPrice.add(coupon);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return couponsByPrice;
	}

	@Override
	public String toString() {
		return "CustomerFacade [customer=" + customer + "]";
	}

	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType)
			throws FacadeException, LoginException, CouponException {
		// TODO Auto-generated method stub
		return null;
	}

	

	
		
	
		

}
