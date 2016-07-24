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
	private CustomerDAO custDAO;
	private CouponDAO coupDAO;
	
	/*
	 * Constructors
	 */
		
	public CustomerFacade() 
	{
		custDAO = new CustomerDBDAO();
		coupDAO= new CouponsDBDAO();
	}	
	
	public CustomerFacade(String custName, String custPassword) throws CustomerException, SQLException {
		try {
			customer = custDAO.getCustomerByName(custName);
			custPassword= custDAO.getCustomerByName(custName).getPassWord();
		} catch (CouponException e){
			// In case of a problem throw new CustomerFacadeException  
			throw new CustomerException("CustomerFacadeException - "
					+ "Constructor error", e);
		}
	}
	public CustomerFacade login(String custName, String password, ClientType clientType)
			throws FacadeException, LoginException, CouponException 
	{
		try {
			if (custDAO.login(custName, password)&& clientType.equals(ClientType.COMPANY))
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


	
	public void purchaseCoupon(Coupon coupon) throws CouponException 
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
		
		if (CustomerDBDAO.isPurchased(coupon, customer))
		{
			try {
				throw new AlreadyExistException("Coupon was Already purchased");
			} catch (AlreadyExistException e) {
				
			}
		}
	
		custDAO.PurchaseCoupon(customer.getId(), coupon.getId());		
		coupon.setAmount(coupon.getAmount()-1);
		coupDAO.updateCoupon(coupon);
		
	}
	
//	public void purchaseCouponById (long coupId) throws CustomerException, SQLException, CouponException
//	{
//		for (Coupon coupon : getAllPurchasedCoupons()) 
//		{
//			if (coupon.getId() == coupId) 
//			{
//				purchaseCoupon(coupon);
//			}	
//	}
//}
	

	public Collection<Coupon> getAllPurchasedCoupons() throws CustomerException, SQLException 
	{
					
		try {
		return custDAO.getCoupons(custId);
	
			}
		catch (CouponException e)
			{
			
			throw new CustomerException("CustomerException - getAllPurchasedCoupons Error");
			}
	}

	
	public Collection<Coupon> getAllPurchasedCouponsByType(CouponType CouponType) throws CustomerException, CouponException, SQLException 
	{
		Collection<Coupon> couponsType= new HashSet<>();
		
		for (Coupon coupon: custDAO.getCoupons(custId)){
			if (coupon.getType().equals(CouponType)){
				couponsType.add(coupon);
			}
		}
		return couponsType;
	}	
	public Collection<Coupon> getAllPurchasedCouponsByType(double price) throws CustomerException, CouponException, SQLException 
	{
		Collection<Coupon> couponsByPrice= new HashSet<>();
		
		for (Coupon coupon: custDAO.getCoupons(custId)){
			if (coupon.getPrice()<=(price)){
				couponsByPrice.add(coupon);
			}
		}
		return couponsByPrice;
	}

	@Override
	public String toString() {
		return "CustomerFacade [customer=" + customer + "]";
	}		
		
	
		

}
