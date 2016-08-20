package Tests;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;

import DAO.CompanyDAO;
import DBDAO.CompanyDBDAO;
import Exceptions.AlreadyExistException;
import Exceptions.CouponException;
import Exceptions.DoesNotExistException;
import JavaBeans.Coupon;
import JavaBeans.CouponType;
import JavaBeans.Company;

public class CompanyTest {

		public static void main(String[] args) throws CouponException, AlreadyExistException, DoesNotExistException, SQLException 
	{
//V			createCompanyTest();
//V			removeCompanyTest ();
//V			removeCompanyByNameTest();
//V			updateCompanyByNameTest();
//V			updateCompanyTest ();
//V			getCompanyByIdTest();
//V			getCompanyByNameTest();
//V 		getAllCompaniesTest();
//V 		getCouponsTest();
//V			loginTest ();
//V			addCompanyCouponByIdTest();
			removeCompanyCouponsByIdTest();
			
			
	}
	
		public static void createCompanyTest() throws CouponException, AlreadyExistException, DoesNotExistException
		{
			CompanyDAO compDao = new CompanyDBDAO();
			
//			Company comp1 = new Company("Nadav1", "Nadav123", "Nadavy1@gmail.com");
//			Company comp2 = new Company("Nadav2", "Nadav123", "Nadavy2@gmail.com");
			Company comp3 = new Company("Nadav3", "Nadav123", "Nadavy3@gmail.com");
			
//			compDao.createCompany(comp1);
//			compDao.createCompany(comp2);
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
		
		public static void removeCompanyByNameTest() throws CouponException, DoesNotExistException, SQLException
		{
			CompanyDAO compDao = new CompanyDBDAO();
			compDao.removeCompanyByName("Nadav3");
		}
		
		public static void updateCompanyByNameTest()throws CouponException, SQLException, DoesNotExistException 
		{
			CompanyDAO compDao = new CompanyDBDAO();
			compDao.updateCompanyByName("Nadav1", "Ofer1");
		}
		
		public static void updateCompanyTest ()throws CouponException, SQLException, DoesNotExistException
		{
			CompanyDAO compDao = new CompanyDBDAO();
			
			Company comp1 = new Company("Nadav1", "nadav123456", "nadavy73@gmail.com");
			
			compDao.updateCompany(comp1);
			
		}
		
		public static void getCompanyByIdTest() throws CouponException, SQLException, DoesNotExistException
		{
			CompanyDAO compDao = new CompanyDBDAO();
			System.out.println(compDao.getCompanyById(4));
		}
		
		public static void getCompanyByNameTest() throws CouponException, DoesNotExistException
		{
			CompanyDAO compDao = new CompanyDBDAO();
			System.out.println(compDao.getCompanyByName("Nadav1"));
		}
		
		public static void getAllCompaniesTest() throws CouponException, SQLException
		{
			CompanyDAO compDao = new CompanyDBDAO();
			System.out.println(compDao.getAllCompanies());
		}

		public static void getCouponsTest() throws CouponException, SQLException, DoesNotExistException
		{
			CompanyDAO compDao = new CompanyDBDAO();
			System.out.println(compDao.getCoupons(3));
		}
		
		public static void loginTest ()throws CouponException, SQLException
		{
			CompanyDAO compDao= new CompanyDBDAO();
			compDao.login("Nadav1", "nadav123456");
		}
		
		
		public static void addCompanyCouponByIdTest() throws CouponException, DoesNotExistException, AlreadyExistException
		{
			CompanyDAO compDao= new CompanyDBDAO();
			compDao.addCompanyCouponById(3, 25);
		}
		
		
		public static void removeCompanyCouponsByIdTest() throws CouponException, DoesNotExistException, AlreadyExistException, SQLException
		{
			CompanyDAO compDao= new CompanyDBDAO();
			compDao.removeCompanyCouponsById(3, 25);
		}
		
		
		
		



}