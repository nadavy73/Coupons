package Facades;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
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
 	
	
 	/*
 	 * Constructors
 	 */

public CompanyFacade() {}
 		
 	//Company Facade - Login
	//Ofer//
 	public CompanyFacade login(String compName, String password, ClientType clientType) 
 			throws LoginException
 	{
 		boolean LoginAsCompany= false;
 	try 	{
 			LoginAsCompany=CouponSystem.getInstance().getCompDAO().login(compName, password);
 			} 
 	catch (Exception e) 
 			{
			throw new LoginException("Company failed to login");
 			}
 		
 		if (LoginAsCompany && clientType.equals(ClientType.COMPANY))
 				{
 				System.out.println("Successful Company Login");
 				try {
					company= CouponSystem.getInstance().getCompDAO().getCompanyByName(compName);
				} catch (CouponException | DoesNotExistException e) {
					throw new LoginException("Company "+compName+" does not exist in our DB");
				}
 				return this;
 				} 
 		else 
 				{
 				throw new LoginException("Company "+compName+" does not exist in our DB");
 				}
 	}
 	//V
 	//Company Facade - create Coupon
 	//Ofer
 	public void createCoupon(Coupon coupon) 
 			throws CouponException, AlreadyExistException, CompanyFacadeException, SQLException, DoesNotExistException
 	{
 		//Create coupon
 		CouponSystem.getInstance().getCouponDAO().createCoupon(coupon);
 		
 		//add this coupon to the the company
 		CouponSystem.getInstance().getCompDAO().addCompanyCoupon(company, coupon);
 	}	
		
 	//Ofer
 	public void removeCoupon(Coupon coupon) 
 			throws CouponException, DoesNotExistException, SQLException, AlreadyExistException
 	{
 			CouponSystem.getInstance().getCompDAO().removeCompanyCouponsById(coupon.getId());
 			CouponSystem.getInstance().getCustDAO().removeCustomerCouponsByCouponId(coupon.getId());
 			CouponSystem.getInstance().getCouponDAO().removeCoupon(coupon);
 	}
		  	
	public void updateCoupon(Coupon coupon) 
			throws CouponException, SQLException, DoesNotExistException 
				{
		  		CouponSystem.getInstance().getCouponDAO().updateCoupon(coupon);
		  		}
		  
	public Coupon getCoupon (long coupId)
			throws CompanyFacadeException, SQLException, AlreadyExistException, DoesNotExistException, CouponException 
		  		{
		  		return CouponSystem.getInstance().getCouponDAO().getCoupon(coupId);
		  		}
		  		
	public Collection <Coupon> getAllCoupon() 
			throws CompanyFacadeException, CouponException, SQLException, DoesNotExistException, AlreadyExistException
		  		{ 
		  		return CouponSystem.getInstance().getCompDAO().getCoupons(company.getId());
		  		}
		  	
	public Collection <Coupon> getCouponByType (CouponType couponType) 
			throws CompanyFacadeException, CouponException, SQLException, DoesNotExistException, AlreadyExistException
		 {
		  	
			Collection<Coupon> couponsByType = new HashSet<>();  		
			Collection <Coupon>  Allcoupons = CouponSystem.getInstance().getCompDAO().getCoupons(company.getId());
		  		
		  		
		  		for (Coupon coupon : Allcoupons)
		  		{
		  			if (coupon.getType().equals(couponType))
		  			{
		  				couponsByType.add(coupon);
		  			}
		  			
		  		}
		  		return couponsByType;
		  		
		  }
		  	
	public Collection <Coupon> getCouponsByEndDate(LocalDate Date) 
			throws CompanyFacadeException, CouponException, SQLException, DoesNotExistException, AlreadyExistException
				{
		  			Collection <Coupon> Allcoupons = new HashSet<>();

		  		try {
		  			for (Coupon coupon : CouponSystem.getInstance().getCompDAO().getCoupons(company.getId())) {
		  				
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
		  	
	public Collection <Coupon> getCouponsByPrice(double price) 
				  throws CompanyFacadeException, CouponException, SQLException, DoesNotExistException, AlreadyExistException
		  {
			 Collection <Coupon> Allcoupons = new HashSet<>();

			  		for (Coupon coupon : CouponSystem.getInstance().getCompDAO().getCoupons(company.getId())) 
			  			{
			  				
			  				if (coupon.getPrice() <= price){
			  					Allcoupons.add(coupon);
			  				}
			  				
			  			}
				
			  			return Allcoupons;
		}
			  	
			 			
}

