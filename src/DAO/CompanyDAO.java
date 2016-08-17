package DAO;



import java.sql.SQLException;
import java.util.Collection;
import Exceptions.AlreadyExistException;
import Exceptions.CouponException;
import Exceptions.DoesNotExistException;
import JavaBeans.Company;
import JavaBeans.Coupon;

public interface CompanyDAO
{
	public void createCompany (Company company) throws CouponException, AlreadyExistException, DoesNotExistException;
	
	public void removeCompanyByName (String compName)throws CouponException,DoesNotExistException, SQLException;
	
	public void updateCompanyByName(String OldName, String NewName)throws CouponException, SQLException, DoesNotExistException;
	
	public void updateCompany (Company company)throws CouponException, SQLException, DoesNotExistException;
	
	public Company getCompanyById(long ID) throws CouponException, SQLException, DoesNotExistException;
	
	public Company getCompanyByName(String compName) throws CouponException, DoesNotExistException;
	
	public Collection <Company> getAllCompanies() throws CouponException, SQLException;
	
	public Collection<Coupon> getCoupons(long compID) throws CouponException, SQLException, DoesNotExistException;
	
	public boolean login (String compName ,String password)throws CouponException, SQLException;
	
	public void addCompanyCoupon(Company company, Coupon coupon) throws CouponException, SQLException;
	
	public void addCompanyCouponById(long compId, long couponId) throws CouponException;
	
//	public boolean isCompanyExist(String compName) throws CouponException;
	
	public void removeCompanyCouponsById(long couponId) throws CouponException, DoesNotExistException;
		
	//public void removeCompanyCoupon(Coupon coupon, Company company) throws CouponException, SQLException;

	public void removeCompany(Company company) throws SQLException, CouponException, DoesNotExistException;
}

