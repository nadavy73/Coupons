package Facades;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

import DAO.CompanyDAO;
import DAO.CouponDAO;
import DAO.CustomerDAO;
import DBDAO.CompanyDBDAO;
import DBDAO.CouponDBDAO;
import DBDAO.CustomerDBDAO;
import Exceptions.AlreadyExistException;
import Exceptions.CompanyFacadeException;
import Exceptions.CouponException;
import Exceptions.DoesNotExistException;
import Exceptions.FacadeException;
import Exceptions.LoginException;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.CouponType;
import System.CouponSystem;

public class CompanyFacade implements CouponClientFacade
{
	
 	/*
 	 * Attributes
 	 */
 	private Company company;
 	private long compId;
 	private CustomerDAO custDAO = null;
 	private CompanyDAO compDAO = null;
 	private CouponDAO coupDAO = null;
	
 	/*
 	 * Constructors
 	 */

public CompanyFacade() 
 	{
 		compDAO = CouponSystem.getInstance().getCompDAO();
 		custDAO = CouponSystem.getInstance().getCustDAO();
 		coupDAO = CouponSystem.getInstance().getCouponDAO();
 		
 	
 	}
 	
// 	public CompanyFacade (String compName, String password) throws CouponException, DoesNotExistException
// 	{
// 		compName= compDAO.getCompanyByName(compName).getCompName();
// 		password= compDAO.getCompanyByName(compName).getPassWord();
// 	}


 	
 	//V
 	
 	//
 	@Override
 	public CompanyFacade login(String compName, String password, ClientType clientType) throws FacadeException, LoginException, CouponException, DoesNotExistException 
 	{boolean LoginAsCompany= false;
 		try {
 			LoginAsCompany=compDAO.login(compName, password);
 		} catch (Exception e) {
			throw new DoesNotExistException("Company failed to login");
		}
 			if (LoginAsCompany && clientType.equals(ClientType.COMPANY))
 				{
 				System.out.println("Successful Company Login");
 				company= compDAO.getCompanyByName(compName);
 				return this;
	
		} 
	else 
	{
		throw new LoginException("Customer Login Failed");
	}
}

 	
 	

 
 	public void createCoupon(Coupon coupon) throws CouponException, AlreadyExistException, CompanyFacadeException, SQLException, DoesNotExistException
 	{
 		try {
 			coupDAO.createCoupon(coupon);
 			}
 		catch (CouponException e)
 			{
 			throw new CompanyFacadeException("CompanyFacadeException   "
 					+ "createCoupon Error", e);
 			}

 	compDAO.addCompanyCouponById(company.getId(), coupon.getId());
 			
 			
 	}

 	public void removeCoupon(Coupon coupon) throws CouponException, DoesNotExistException, SQLException
 	{
 		//Remove Coupon		
 		try {
 			coupDAO.removeCoupon(coupon);
 		} catch (CouponException e) {
 			throw new DoesNotExistException("CompanyFacadeException   "
 					+ "getCoupon Error", e);
}
	
 		//Remove Coupons from Company
 	
 		try {
 			compDAO.removeCompanyCouponsById(coupon.getId(), compId);
 		} catch (CouponException e) {
 			throw new DoesNotExistException("CompanyFacadeException   "
 					+ "getCoupon Error", e);
 		}
 	
 		//Remove Coupon from Customer
 	
 			for (long custId: coupDAO.getCustomersWhoHaveCoupon(coupon.getId()))
 			{
	try {
		  				custDAO.removeCustomerCouponsById(custId, coupon.getId());
		  			} catch (CouponException e) {
		  				throw new DoesNotExistException("CompanyFacadeException   "
		  						+ "getCoupon Error", e);
		  			}
		  			}
		  	}
		  	
		  	
		  	public void updateCoupon(Coupon coupon) throws CouponException, SQLException, DoesNotExistException {
		  		coupDAO.updateCoupon(coupon);
		  	}
		  
		  	public Coupon getCoupon (long coupId)throws CompanyFacadeException, SQLException, AlreadyExistException, DoesNotExistException 
		  	{
		  		try {
		  			return coupDAO.getCoupon(coupId);
			}
		  		catch (CouponException e) 
		  		{
		  			throw new CompanyFacadeException("CompanyFacadeException   "
		  					+ "getCoupon Error", e);
		  		}
		  	}
		  	
		  	public Collection <Coupon> getAllCoupon() throws CompanyFacadeException, CouponException, SQLException, DoesNotExistException
		  	{ 
		  	
		  	try {
		  		return compDAO.getCoupons(company.getId());
		  		}
		  		catch (CouponException e) 
		  		{
		  			throw new CompanyFacadeException("CompanyFacadeException   "
		  					+ "getAllCoupons Error", e);
		  		}
		  	
		  	}
		  	
		  	public Collection <Coupon> getCouponByType (CouponType couponType) throws CompanyFacadeException, CouponException, SQLException, DoesNotExistException
		  	{
		  	
//		  		Collection<Coupon> Allcoupons=new HashSet<>();
//		  		
//		  		try {
//		  			Allcoupons = compDAO.getCoupons(company.getId());
//		  		} catch (CouponException e) {
//		  			throw new CompanyFacadeException("CompanyFacadeException   "
//		  					+ "getAllCoupons Error", e);
		 
		  		Collection<Coupon> couponsByType = new HashSet<>();
		  		
		  		for (Coupon coupon : compDAO.getCoupons(company.getId()))
		  		{
		  			if (coupon.getType().equals(couponType))
		  			{
		  				couponsByType.add(coupon);
		  			}
		  			
		  		}
		  		return couponsByType;
		  	}
		  	
		  	public Collection <Coupon> getCouponsByEndDate(LocalDate Date) throws CompanyFacadeException, CouponException, SQLException, DoesNotExistException{
		  		
		  		Collection <Coupon> Allcoupons = new HashSet<>();

		  		try {
		  			for (Coupon coupon : compDAO.getCoupons(company.getId())) {
		  				
		  				if (coupon.getStartDate().isBefore(Date) || coupon.getEndDate().equals(Date))  
		  				{
		  					Allcoupons.add(coupon);
		  				}
		 }
		  		} catch (CouponException e) {
		  			throw new CompanyFacadeException("CompanyFacadeException   "
		  					+ "getAllCoupons Error", e);
		  				}
		  		return Allcoupons;
		  	}
		  	
		  public Collection <Coupon> getCouponsByPrice(double price) throws CompanyFacadeException, CouponException, SQLException, DoesNotExistException{
			 Collection <Coupon> Allcoupons = new HashSet<>();

			  		try {
			  			for (Coupon coupon : compDAO.getCoupons(company.getId())) {
			  				
			  				if (coupon.getPrice() <= price){
			  					Allcoupons.add(coupon);
			 }
			  				{
			  					Allcoupons.add(coupon);
			 }
			 }
				
			  			
			  		} catch (CouponException e) {
			  			throw new CompanyFacadeException("CompanyFacadeException   "
			  					+ "getAllCoupons Error", e);
			 }
			  		return Allcoupons;
			  	}
			  	
			  	
			  	
			  	
			  	
			  	
			  	
			  	
			  

			
		 }

	
	
	
