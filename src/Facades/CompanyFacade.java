package Facades;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import Exceptions.*;
import JavaBeans.*;
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
 					} 
 				catch (SQLException | DoesNotExistException e) 
 					{
					throw new LoginException("Company "+compName+" does not exist in DB");
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
 	public long createCoupon(Coupon coupon) 
 			throws CompanyFacadeException, AlreadyExistException
 		{
 		
 		long couponId = -1;
 		
 		//Create coupon
 		try {
			couponId = CouponSystem.getInstance().getCouponDAO().createCoupon(coupon);
		} catch (SQLException e) {
			e.printStackTrace();
		}
 		
 		//Get the Coupon's Id from Coupon Table
 		try {
			coupon = CouponSystem.getInstance().getCouponDAO().getCoupon(coupon.getId());
		} catch (DoesNotExistException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
 		//Add This coupon to the Company
 		try {
			CouponSystem.getInstance().getCompDAO().addCompanyCoupon(company, coupon);
		} catch (DoesNotExistException | SQLException e) {
			e.printStackTrace();
		}
 		
 		return couponId;
 		}
		
 	
 	public void removeCoupon(Coupon coupon) 
 			throws CompanyFacadeException, DoesNotExistException
 	{
 			try {
				CouponSystem.getInstance().getCompDAO().removeCompanyCouponsById(coupon.getId());
				CouponSystem.getInstance().getCustDAO().removeCustomerCouponsByCouponId(coupon.getId());
	 			CouponSystem.getInstance().getCouponDAO().removeCoupon(coupon);
	 	
 			} catch (SQLException e) {
				
				e.printStackTrace();
			}
 			}
		  	
	public void updateCoupon(Coupon coupon) 
			throws CompanyFacadeException, DoesNotExistException 
		{
		  try 	{
				CouponSystem.getInstance().getCouponDAO().updateCoupon(coupon);
		  		}	
		  		
		  catch (SQLException e) 
		  		{
		  			throw new CompanyFacadeException
		  				("CompanyFacade - updateCoupon"+ e.getMessage());
		  		}
	}
		  
	public Coupon getCoupon (long coupId) 
			throws CompanyFacadeException, DoesNotExistException
		{
		  	try {
					return CouponSystem.getInstance().getCouponDAO().getCoupon(coupId);
				} 
		  	catch (SQLException e) 
		  		{
		  			throw new CompanyFacadeException
		  			("CompanyFacade - getCoupon"+ e.getMessage());
		  		}
		 }
	
	//getAllCoupon
	public Collection <Coupon> getAllCoupon() 
			throws CompanyFacadeException, DoesNotExistException
		{ 
		  	try {
					return CouponSystem.getInstance().getCompDAO().getCoupons(company.getId());
		  		} 	
		  	catch (SQLException e) 
		  		{
		  			throw new CompanyFacadeException
					("CompanyFacade -  getAllCoupon"+ e.getMessage());
		  		}
		}
		  	
	//getCouponByType
	public Collection <Coupon> getCouponByType (CouponType couponType) 
			throws CompanyFacadeException, DoesNotExistException
	{
			Collection<Coupon> couponsByType = new HashSet<>();  		
		
		try
			{
			Collection <Coupon>  Allcoupons = CouponSystem.getInstance().getCompDAO().getCoupons(company.getId());
		  		
		  		for (Coupon coupon : Allcoupons)
		  			{
		  				if (coupon.getType().equals(couponType))
		  				{
		  				couponsByType.add(coupon);
		  				}
		  			
		  			}
			}
		catch (SQLException | DoesNotExistException e)
			{
			throw new CompanyFacadeException
			("CompanyFacade -  getCouponByType"+ e.getMessage());
			}
		if (couponsByType.isEmpty())
			throw new DoesNotExistException("This company doesn't have this coupon type "+couponType);
		return couponsByType;
		  		
	}
	
	
	//getCouponsByEndDate
	public Collection <Coupon> getCouponsByEndDate(LocalDate Date) 
			throws CompanyFacadeException,DoesNotExistException
	{
		  	Collection <Coupon> Allcoupons = new HashSet<>();
		 try
			{
		  		for (Coupon coupon : CouponSystem.getInstance().getCompDAO().getCoupons(company.getId())) 
		  			{
		  				if (/*coupon.getStartDate().isBefore(Date) || */coupon.getEndDate().equals(Date))  
		  				{
						Allcoupons.add(coupon);
		  				}
		  			}
			}
		catch (SQLException e)
			{
				throw new CompanyFacadeException("CompanyFacade -  getCouponsByEndDate"+ e.getMessage());
			}
		 if (Allcoupons.isEmpty())
			 throw new DoesNotExistException("we dont have any coupon with this end date "+Date);
	return Allcoupons;
 	}
	
	//getCouponsByStartDate
	public Collection <Coupon> getCouponsByStartDate(LocalDate Date) 
			throws CompanyFacadeException,DoesNotExistException
	{
		  	Collection <Coupon> Allcoupons = new HashSet<>();
		 try
			{
		  		for (Coupon coupon : CouponSystem.getInstance().getCompDAO().getCoupons(company.getId())) 
		  			{
		  				if (/*coupon.getStartDate().isBefore(Date) || */coupon.getStartDate().equals(Date))  
		  				{
						Allcoupons.add(coupon);
		  				}
		  			}
			}
		catch (SQLException e)
			{
				throw new CompanyFacadeException("CompanyFacade -  getCouponsByEndDate"+ e.getMessage());
			}
		 if (Allcoupons.isEmpty())
			 throw new DoesNotExistException("we dont have any coupon with this end date "+Date);
	return Allcoupons;
 	}
	
	//getCouponsByPrice
	public Collection <Coupon> getCouponsByPrice(double price) 
				  throws CompanyFacadeException, DoesNotExistException
	{
		Collection <Coupon> Allcoupons = new HashSet<>();
			 
		try
			{
			  	for (Coupon coupon : CouponSystem.getInstance().getCompDAO().getCoupons(company.getId())) 
			  		{
			  			if (coupon.getPrice() <= price)
			  				{
			  					Allcoupons.add(coupon);
			  				}
			  				
			  		} 			
			 }
		catch (SQLException e)
			{
				throw new CompanyFacadeException("CompanyFacade -  getCouponsByEndDate"+ e.getMessage());
			}	
		if (Allcoupons.isEmpty())
			throw new DoesNotExistException("we don't have coupons with this price "+price);
		
	return Allcoupons;
	}

	
	
	
			 			
}
