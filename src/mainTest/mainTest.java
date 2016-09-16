package mainTest;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.concurrent.TimeUnit;

import DAO.*;
import DBDAO.*;
import Exceptions.*;
import Facades.*;
import JavaBeans.*;
import System.CouponSystem;
import Threads.DailyCouponExpirationTask;

public class mainTest {
	private static AdminFacade adminF = new AdminFacade();
	private static CompanyFacade companyF = new CompanyFacade();
	private static CustomerFacade customerF = new CustomerFacade();
	private static DailyCouponExpirationTask daily = new DailyCouponExpirationTask();

	
	public static void main(String[] args) throws LoginException, AdminFacadeException, AlreadyExistException, DoesNotExistException, SQLException, CompanyFacadeException, CustomerFacadeException, CouponException, InterruptedException {
		
		//AAAAAAAAAAAADDDDDDDDDDDDMMMMMMMMMMMMIIIIIIIIIIIINNNNNNNNNNNN
		//************************************************************
		//***ADMIN FACADE***ADMIN FACADE***ADMIN FACADE***ADMIN FACADE
		//************************************************************
		//AAAAAAAAAAAADDDDDDDDDDDDMMMMMMMMMMMMIIIIIIIIIIIINNNNNNNNNNNN
		
		
		//************************************************V
		//*The Admin User doing login and get back Facade*V
		//************************************************V
		
		
//		adminF.login("admin", "1234", ClientType.ADMIN);
		
		

//*************************************************************************************************************************		
//*************************************COMPANIES***************************************************************************
//*************************************************************************************************************************

		
		//******************V
		//*Create Companies*V
		//******************V
		

//		Company SHEKEM_ELECTRIC = new Company("SHEKEM_ELECTRIC", "S123456", "shekem_electric8080@shekem_electric.com");	
//		adminF.createCompany(SHEKEM_ELECTRIC);
//		Company Telma = new Company("Telma", "T123456", "telma8080@telma.com");
//		adminF.createCompany(Telma);
//		Company TNT = new Company("TNT", "TNT123456", "tnt8080@tnt.com");
//		adminF.createCompany(TNT);
//		Company ACE = new Company("ACE", "ACE123456", "ace8080@ace.com");
//		adminF.createCompany(ACE);
//		Company ZAKAIM = new Company("ZAKAIM", "ZAKAIM123456", "zakaim8080@zakaim.com");
//		adminF.createCompany(ZAKAIM);
		
		
		//************************************V
		//*This Function return all companies*V
		//************************************V
		

//		adminF.login("admin", "1234", ClientType.ADMIN);
//		System.out.println(adminF.getAllCompanies());
		
		
		//************************************V
		//*This Function return company BY ID*V
		//************************************V
		

//		adminF.login("admin", "1234", ClientType.ADMIN);
//		System.out.println(adminF.GetCompany(1));
		
		
		//**************************************V
		//*This Function return company BY NAME*V
		//**************************************V
		
		
//		adminF.login("admin", "1234", ClientType.ADMIN);
//		System.out.println(adminF.GetCompanyByName("Telma"));
		
		
		//**************************************V
		//*This Function update company Details*V
		//**************************************V
		
		
//		adminF.login("admin", "1234", ClientType.ADMIN);
//		CompanyDAO compDao = new CompanyDBDAO();
//		Company SHEKEM_ELECTRIC_UPDATE = compDao.getCompanyByName("SHEKEM_ELECTRIC");
//		System.out.println();
//		System.out.println("Before changes");
//		System.out.println(adminF.GetCompanyByName("SHEKEM_ELECTRIC"));
//		
//		SHEKEM_ELECTRIC_UPDATE.setPassWord("ShekemE123");
//		SHEKEM_ELECTRIC_UPDATE.seteMail("ShekemShekemShekem@shekem.com");
//		
//		adminF.UpdateCompany(SHEKEM_ELECTRIC_UPDATE);
//		System.out.println();
//		System.out.println("After changes");
//		System.out.println(adminF.GetCompanyByName("SHEKEM_ELECTRIC"));
		
		
		//**************************************V
		//*This Function remove company from DB*V
		//**************************************V
		

//		adminF.login("admin", "1234", ClientType.ADMIN);
//		
//		System.out.println(adminF.getAllCompanies());
//		
//		CompanyDAO compDao = new CompanyDBDAO();
//		Company SHEKEM_E_REMOVE = compDao.getCompanyByName("SHEKEM_ELECTRIC");
//		adminF.removeCompany(SHEKEM_E_REMOVE);
//		
//		System.out.println(adminF.getAllCompanies());
		

//*************************************************************************************************************************		
//*************************************CUSTOMERS***************************************************************************
//*************************************************************************************************************************
		
		
		
		//******************V
		//*Create Customers*V
		//******************V
		

//		adminF.login("admin", "1234", ClientType.ADMIN);
//		Customer Kobe_Bryant = new Customer("Kobe_Bryant", "Kobe1234");
//		adminF.createCustomer(Kobe_Bryant);
//		Customer LeBron_James = new Customer("LeBron_James", "LeBron1234");
//		adminF.createCustomer(LeBron_James);
//		Customer Kareem_Abdul_Jabbar = new Customer("Kareem_Abdul_Jabbar", "Kareem1234");
//		adminF.createCustomer(Kareem_Abdul_Jabbar);
//		Customer Shaquille_ONeal = new Customer("Shaquille_ONeal", "Shaquille1234");
//		adminF.createCustomer(Shaquille_ONeal);
//		Customer Michael_Jordan = new Customer("Michael_Jordan", "Michael1234");
//		adminF.createCustomer(Michael_Jordan);
		
		
		//************************************V
		//*This Function return all Customers*V
		//************************************V
		
		
//		adminF.login("admin", "1234", ClientType.ADMIN);
//		System.out.println(adminF.getAllCustomers());
		
		
		//*************************************V
		//*This Function return customer BY ID*V
		//*************************************V
		
		
//		adminF.login("admin", "1234", ClientType.ADMIN);
//		System.out.println(adminF.GetCustomer(1));
		
		
		//***************************************V
		//*This Function return customer BY NAME*V
		//***************************************V
		
		
//		adminF.login("admin", "1234", ClientType.ADMIN);
//		CustomerDAO custDao = new CustomerDBDAO();
//		System.out.println(custDao.getCustomerByName("Kobe_Bryant"));
		
		
		//***************************************V
		//*This Function update customer Details*V
		//***************************************V
		
		
//		adminF.login("admin", "1234", ClientType.ADMIN) ;
//		
//		CustomerDAO custDao = new CustomerDBDAO();
//		Customer Kobe_Bryant_UPDATE = custDao.getCustomerByName("Kobe_Bryant");
//		System.out.println("The Customer before the changes");
//		System.out.println(Kobe_Bryant_UPDATE.toString());
//		
//		Kobe_Bryant_UPDATE.setCustPassword("Kobe123456789");
//		adminF.UpdateCustomer(Kobe_Bryant_UPDATE);
//		
//		System.out.println("The Customer after the changes");
//		System.out.println(Kobe_Bryant_UPDATE.toString());
		
		
		//***************************************V
		//*This Function remove customer from DB*V
		//***************************************V
		
		
//		adminF.login("admin", "1234", ClientType.ADMIN);
//		
//		System.out.println("All customer before REMOVE function");
//		System.out.println(adminF.getAllCustomers());
//		
//		CustomerDAO custDao = new CustomerDBDAO();
//		Customer Kobe_Bryant_REMOVE = custDao.getCustomerByName("Kobe_Bryant");
//		adminF.RemoveCustomer(Kobe_Bryant_REMOVE);
//		
//		System.out.println();
//		System.out.println("All customer after REMOVE function");
//		System.out.println(adminF.getAllCustomers());
		
		
		//CCCCCCCCCOOOOOOOOOMMMMMMMMMPPPPPPPPPAAAAAAAAANNNNNNNNNYYYYYYYYY
		//***************************************************************
		//*******COMPANY FACADE***COMPANY FACADE***COMPANY FACADE********
		//***************************************************************
		//CCCCCCCCCOOOOOOOOOMMMMMMMMMPPPPPPPPPAAAAAAAAANNNNNNNNNYYYYYYYYY
		
		
		//***************************************************V
		//*The Company Telma doing login and get back Facade*V
		//***************************************************V
		
		
//		companyF.login("Telma", "T123456", ClientType.COMPANY);
		
		
		//***********************************V
		//*Create A Coupon for Telma Company*V
		//***********************************V

//		companyF.login("Telma", "T123456", ClientType.COMPANY);
//		Coupon Buy_one_Get_One_Free_Food = new Coupon("1+1 about all the Pasta",LocalDate.now(), LocalDate.of(2016, Month.OCTOBER, 14), 100, CouponType.Food, "doesn't include with gluten", 00.00, "image");
//		companyF.createCoupon(Buy_one_Get_One_Free_Food);
		
				
		//****************************************************V
		//*This Function return all coupons for Telma Company*V
		//****************************************************V
		
		
//		companyF.login("Telma", "T123456", ClientType.COMPANY);
//		System.out.println(companyF.getAllCoupon());
		
		
		//*********************************************************************V
		//*This Function return all coupons from chosen Type for Telma Company*V
		//*********************************************************************V
		
		
//		companyF.login("Telma", "T123456", ClientType.COMPANY);
//		System.out.println(companyF.getCouponByType(CouponType.Food));
		
		
		//*************************************************************************V
		//*This Function return all coupons Until chosen EndDate for Telma Company*V
		//*************************************************************************V
		
		
//		companyF.login("Telma", "T123456", ClientType.COMPANY);
//		System.out.println(companyF.getCouponsByEndDate(LocalDate.of(2016, Month.OCTOBER, 14)));
		
		
		//*****************************************************************************V
		//*This Function return all coupons Until chosen price range for Telma Company*V
		//*****************************************************************************V
		
		
//		companyF.login("Telma", "T123456", ClientType.COMPANY);
//		System.out.println(companyF.getCouponsByPrice(50));
		
		
		//*************************************V
		//*This Function update Coupon Details*V
		//*************************************V
		
		
//		companyF.login("Telma", "T123456", ClientType.COMPANY);
//		CouponDAO coupDao = new CouponDBDAO();
//		Coupon Buy_one_Get_One_Free_Food_Updated = coupDao.getCouponByTitle("1+1 about all the Pasta");
//		System.out.println();
//		System.out.println("coupon before update");
//		System.out.println();
//		System.out.println(companyF.getCoupon(1));
//		
//		Buy_one_Get_One_Free_Food_Updated.setAmount(90);
//		Buy_one_Get_One_Free_Food_Updated.setEndDate(LocalDate.of(2017, Month.APRIL, 27));
//		Buy_one_Get_One_Free_Food_Updated.setType(CouponType.Home_and_Styling_Garden);
//		
//		companyF.updateCoupon(Buy_one_Get_One_Free_Food_Updated);
//		
//		System.out.println();
//		System.out.println("coupon after update");
//		System.out.println();
//		System.out.println(companyF.getCoupon(1));
		
		
		//*************************************V
		//*This Function remove Coupon Details*V
		//*************************************V
		
		
//		CouponDBDAO coupDao = new CouponDBDAO();
//		Coupon coupon_to_remove = coupDao.getCouponByTitle("1+1 about all the Pasta");
//		companyF.removeCoupon(coupon_to_remove);
		
		
		
		//CCCCCCCCUUUUUUUUSSSSSSSSTTTTTTTTOOOOOOOOMMMMMMMMEEEEEEEERRRRR
		//*************************************************************
		//*****CUSTOMER FACADE***CUSTOMER FACADE***CUSTOMER FACADE*****
		//*************************************************************
		//CCCCCCCCUUUUUUUUSSSSSSSSTTTTTTTTOOOOOOOOMMMMMMMMEEEEEEEERRRRR
		
		
		//*************************************************************V
		//*The Customer Michael_Jordan doing login and get back Facade*V
		//*************************************************************V
		
		
//		customerF.login("Michael_Jordan", "Michael1234" , ClientType.CUSTOMER);
		
		
		//**********************************************************************V
		//*The Customer Michael_Jordan purchase a new coupon Congratulations!!!*V
		//**********************************************************************V
		
		
//		companyF.login("Telma", "T123456", ClientType.COMPANY);
//		Coupon Buy_one_Get_One_Free_Food = new Coupon("1+1 about all the Pasta",LocalDate.now(), LocalDate.of(2016, Month.OCTOBER, 14), 100, CouponType.Food, "doesn't include with gluten", 00.00, "image");
//		companyF.createCoupon(Buy_one_Get_One_Free_Food);
//		
//		customerF.login("Michael_Jordan", "Michael1234" , ClientType.CUSTOMER);
//		Coupon Michael_Jordan_Coupon = CouponSystem.getInstance().getCouponDAO().getCouponByTitle("1+1 about all the Pasta");
//		customerF.purchaseCoupon(Michael_Jordan_Coupon);
		
		
		//*************************************************V
		//*This Function return all Michael_Jordan coupons*V
		//*************************************************V
		
		
//		customerF.login("Michael_Jordan", "Michael1234" , ClientType.CUSTOMER);
//		System.out.println(customerF.getAllPurchasedCoupons());
		
		
		//***********************************************************************V
		//*This Function return all Michael_Jordan coupons with chosen MAX price*V
		//***********************************************************************V
		
		
//		customerF.login("Michael_Jordan", "Michael1234" , ClientType.CUSTOMER);
//		System.out.println(customerF.getAllPurchasedCouponsByMaxPrice(100));
		
		
		//******************************************************************V
		//*This Function return all Michael_Jordan coupons from chosen Type*V
		//******************************************************************V
		
		
//		customerF.login("Michael_Jordan", "Michael1234" , ClientType.CUSTOMER);
//		System.out.println(customerF.getAllPurchasedCouponsByType(CouponType.Food));
		
		
		
//		customerF.login("LeBron_James", "LeBron1234", ClientType.CUSTOMER);
//		
//		Coupon Buy_one_Get_One_Free_Food11 = CouponSystem.getInstance().getCouponDAO().getCouponByTitle("1+1 about all the Pasta1");
//		Coupon Buy_one_Get_One_Free_Food22 = CouponSystem.getInstance().getCouponDAO().getCouponByTitle("1+1 about all the Pasta2");
//		Coupon Buy_one_Get_One_Free_Food33 =CouponSystem.getInstance().getCouponDAO().getCouponByTitle("1+1 about all the Pasta3");
//		customerF.purchaseCoupon(Buy_one_Get_One_Free_Food11);
//		customerF.purchaseCoupon(Buy_one_Get_One_Free_Food22);
//		customerF.purchaseCoupon(Buy_one_Get_One_Free_Food33);
//		

		
		
		
//		daily.run();
	}

}
