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
	public void createCompany (Company company) 
			throws CouponException, AlreadyExistException, DoesNotExistException, SQLException;
	
	public void removeCompany(Company company) 
			throws SQLException, CouponException, DoesNotExistException;
	
	public void removeCompanyByName (String compName)
			throws CouponException,DoesNotExistException, SQLException;
	
	public void updateCompanyByName(String OldName, String NewName)
			throws CouponException, SQLException, DoesNotExistException;
	
	public void updateCompany (Company company)
			throws CouponException, SQLException, DoesNotExistException;
	
	public Company getCompanyById(long ID) 
			throws CouponException, SQLException, DoesNotExistException, AlreadyExistException;
	
	public Company getCompanyByName(String compName) 
			throws CouponException, DoesNotExistException, SQLException, AlreadyExistException;

	public Collection <Company> getAllCompanies() 
			throws CouponException, SQLException, DoesNotExistException, AlreadyExistException;
	
	public Collection<Coupon> getCoupons(long compID) 
			throws CouponException, SQLException, DoesNotExistException, AlreadyExistException;
	
	public boolean login (String compName ,String password)
			throws CouponException, SQLException;
	
	public void addCompanyCouponById(long compId, long couponId) 
			throws CouponException, DoesNotExistException, AlreadyExistException, SQLException;
	
	public void removeCompanyCouponsById(long couponId) 
			throws CouponException, DoesNotExistException, SQLException;

	public void removeCompanyCouponsById(long compId, long couponId) 
			throws CouponException, DoesNotExistException, AlreadyExistException, SQLException;
	
}

