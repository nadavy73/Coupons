package Tests;

import java.sql.SQLException;
import java.util.Collection;

import DAO.CompanyDAO;
import DBDAO.CompanyDBDAO;
import Exceptions.AlreadyExistException;
import Exceptions.CouponException;
import Exceptions.DoesNotExistException;
import JavaBeans.Coupon;
import JavaBeans.Company;

public class CompanyTest {

		public static void main(String[] args) throws CouponException, AlreadyExistException, DoesNotExistException, SQLException 
	{
//			createCompanyTest();
//			removeCompanyTest ();
//			updateCompanyByNameTest();
//			updateCompanyTest ();
//			getCompanyTest();
//			getCompanyByNameTest();
//			getAllCompaniesTest();
//			getCouponsTest();
//			loginTest ();
//			addCompanyCouponTest();
//			addCompanyCouponByIdTest();
//			isCompanyExistTest();
//			removeCompanyCouponsByIdTest();
			
	}
	
		public static void createCompanyTest() throws CouponException, AlreadyExistException, DoesNotExistException
		{
			CompanyDAO compDao = new CompanyDBDAO();
			
			Company comp1 = new Company("Nadav1", "Nadav123", "Nadavy1@gmail.com");
			Company comp2 = new Company("Nadav2", "Nadav123", "Nadavy2@gmail.com");
			Company comp3 = new Company("Nadav3", "Nadav123", "Nadavy3@gmail.com");
			
			compDao.createCompany(comp1);
			compDao.createCompany(comp2);
			compDao.createCompany(comp3);
			
			
			
		}	
		
		public static void removeCompanyTest()throws CouponException,DoesNotExistException, SQLException 
		{
			CompanyDAO compDao = new CompanyDBDAO();
			
			Company comp1 = new Company("Nadav1", "Nadav123", "Nadavy1@gmail.com");
			Company comp2 = new Company("Nadav2", "Nadav123", "Nadavy2@gmail.com");
			Company comp3 = new Company("Nadav3", "Nadav123", "Nadavy3@gmail.com");
			
			compDao.removeCompany(comp1);
			compDao.removeCompany(comp2);
			compDao.removeCompany(comp3);
			
		}
		
		public static void updateCompanyByNameTest()throws CouponException, SQLException 
		{
			
		}
		
		public static void updateCompanyTest ()throws CouponException, SQLException, DoesNotExistException
		{
			
		}
		
		public static void getCompanyTest() throws CouponException, SQLException, DoesNotExistException
		{
			
		}
		
		public static void getCompanyByNameTest() throws CouponException, DoesNotExistException
		{
			
		}
		
		public static void getAllCompaniesTest() throws CouponException, SQLException
		{
			
		}

		public static void getCouponsTest() throws CouponException, SQLException, DoesNotExistException
		{
			
		}
		
		public static void loginTest ()throws CouponException, SQLException
		{
			
		}
		
		public static void addCompanyCouponTest() throws CouponException, SQLException
		{
			
		}
		
		public static void addCompanyCouponByIdTest() throws CouponException
		{
			
		}
		
		public static void isCompanyExistTest() throws CouponException
		{
			
		}
		
		public static void removeCompanyCouponsByIdTest() throws CouponException, DoesNotExistException
		{
			
		}
		
		
		
		



}