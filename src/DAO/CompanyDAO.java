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
	public Company getCompanyByName(String NAME) throws CouponException, DoesNotExistException;
	
	public void createCompany (Company company) throws CouponException, AlreadyExistException, DoesNotExistException;
	
	public void removeCompany (String compName)throws CouponException,DoesNotExistException, SQLException;
	
	public void updateCompanyName(String OldName, String NewName)throws CouponException, SQLException;
	
	public void updateCompany (Company company)throws CouponException, SQLException, DoesNotExistException;
	
	public Company getCompany(long ID) throws CouponException, SQLException, DoesNotExistException;
	
	public Collection <Company> getAllCompanies() throws CouponException, SQLException;
	
	public Collection<Coupon> getCoupons(long compID) throws CouponException, SQLException, DoesNotExistException;
	
	public boolean login (String compName ,String password)throws CouponException, SQLException;

	public void removeCompanyCoupon(long couponId, long compId) throws CouponException, SQLException;
		
	public void removeCompanyCoupon(Coupon coupon, Company company) throws CouponException, SQLException;

	public void addCompanyCoupon(Company company, Coupon coupon) throws CouponException, SQLException;
	
	public void addCompanyCoupon(long compId, long couponId) throws CouponException;

	public void removeCompany(Company company) throws SQLException, CouponException, DoesNotExistException;

	

	

	
	

	
	
}

