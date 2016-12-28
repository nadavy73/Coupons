package Tests_on_the_fly;

import java.sql.SQLException;



import DAO.*;
import DBDAO.*;
import Exceptions.*;
import JavaBeans.*;

public class CompanyTest {

		public static void main(String[] args) throws CouponException, AlreadyExistException, DoesNotExistException, SQLException 
	{
//V			createCompanyTest();
//V			removeCompanyTest ();
//V			removeCompanyByNameTest();
//V			updateCompanyByNameTest();
	updateCompanyTest ();
//		getCompanyByIdTest();
//V			getCompanyByNameTest();
//V 		getAllCompaniesTest();
//V 		getCouponsTest();
//V			loginTest ();
//V			addCompanyCouponByIdTest();
//			removeCompanyCouponsByIdTest2PARAMS();
//V			removeCompanyCouponsByIdTest1PARAM();
			
			
	}
	
		public static void createCompanyTest() throws CouponException, AlreadyExistException, DoesNotExistException, SQLException
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
			
			compDao.removeCompanyById(comp1);
			compDao.removeCompanyById(comp2);
			compDao.removeCompanyById(comp3);
			
		}
		
		public static void removeCompanyByNameTest() throws CouponException, DoesNotExistException, SQLException
		{
			CompanyDAO compDao = new CompanyDBDAO();
			compDao.removeCompanyByName("Nadav3");
		}
		
//		public static void updateCompanyByNameTest()throws CouponException, SQLException, DoesNotExistException 
//		{
//			CompanyDAO compDao = new CompanyDBDAO();
//			compDao.updateCompanyByName("Nadav1", "Ofer1");
//		}
		
		public static void updateCompanyTest ()throws CouponException, SQLException, DoesNotExistException
		{
			CompanyDAO compDao = new CompanyDBDAO();
			
			Company comp1 = new Company(37, "test", "test1234", "test1234@gmail.com");
			
			compDao.updateCompany(comp1);
			
		}
		
		public static void getCompanyByIdTest() 
				throws CouponException, SQLException, DoesNotExistException, AlreadyExistException
		{
			CompanyDAO compDao = new CompanyDBDAO();
			System.out.println(compDao.getCompanyById(9));
		}
		
		public static void getCompanyByNameTest() 
				throws CouponException, DoesNotExistException, SQLException, AlreadyExistException
		{
			CompanyDAO compDao = new CompanyDBDAO();
			System.out.println(compDao.getCompanyByName("Nadav1"));
		}
		
		public static void getAllCompaniesTest() 
				throws CouponException, SQLException, DoesNotExistException, AlreadyExistException
		{
			CompanyDAO compDao = new CompanyDBDAO();
			System.out.println(compDao.getAllCompanies());
		}

		public static void getCouponsTest() throws CouponException, SQLException, DoesNotExistException, AlreadyExistException
		{
			CompanyDAO compDao = new CompanyDBDAO();
			System.out.println(compDao.getCoupons(3));
		}
		
		public static void loginTest()
				throws CouponException, SQLException
		{
			CompanyDAO compDao= new CompanyDBDAO();
			compDao.login("Nadav1", "nadav123456");
		}
		
		
		public static void addCompanyCouponByIdTest() 
				throws CouponException, DoesNotExistException, AlreadyExistException, SQLException
		{
			CompanyDAO compDao= new CompanyDBDAO();
			compDao.addCompanyCouponById(30,1);
		}
		
		
		public static void removeCompanyCouponsByIdTest2PARAMS() 
				throws CouponException, DoesNotExistException, AlreadyExistException, SQLException
		{
			CompanyDAO compDao= new CompanyDBDAO();
			compDao.removeCompanyCouponsById(1, 41);
		}
		
		public static void removeCompanyCouponsByIdTest1PARAM() 
				throws CouponException, DoesNotExistException, AlreadyExistException, SQLException
		{
			CompanyDAO compDao= new CompanyDBDAO();
			compDao.removeCompanyCouponsById(25);
		}
		
		
		



}