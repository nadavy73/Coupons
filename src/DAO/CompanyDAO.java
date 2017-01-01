package DAO;



import java.sql.SQLException;
import java.util.Collection;
import Exceptions.*;
import JavaBeans.*;

public interface CompanyDAO
{
	public int createCompany (Company company) 
			throws AlreadyExistException, SQLException;
	
	public void removeCompanyById(Company company) 
			throws DoesNotExistException, SQLException;
	
	public void removeCompanyByName (String compName)
			throws DoesNotExistException, SQLException;
	
//	public void updateCompanyByName(String OldName, String NewName)
//			throws DoesNotExistException, SQLException;
	
	public void updateCompany (Company company)
			throws DoesNotExistException, SQLException;
	
	public Company getCompanyById(long ID) 
			throws DoesNotExistException, SQLException;
	
	public Company getCompanyByName(String compName) 
			throws DoesNotExistException, SQLException;

	public Collection <Company> getAllCompanies() 
			throws DoesNotExistException, SQLException;
	
	public Collection<Coupon> getCoupons(long compID) 
			throws DoesNotExistException, SQLException;
	
	public boolean login (String compName ,String password)
			throws SQLException;
	
	public void addCompanyCouponById(long compId, long couponId) 
			throws DoesNotExistException, AlreadyExistException, SQLException;
	
	public void addCompanyCoupon(Company company, Coupon coupon) 
			throws DoesNotExistException, AlreadyExistException, SQLException;
	
	public void removeCompanyCouponsById(long couponId) 
			throws DoesNotExistException, SQLException;

	public void removeCompanyCouponsById(long compId, long couponId) 
			throws DoesNotExistException, SQLException;
	
}

