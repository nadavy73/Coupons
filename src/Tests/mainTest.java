package Tests;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

import DAO.*;
import DBDAO.*;
import Exceptions.*;
import Facades.*;
import JavaBeans.*;
import System.CouponSystem;

public class mainTest {
	private static AdminFacade adminF = new AdminFacade();
	private static CompanyFacade companyF = new CompanyFacade();
	private static CustomerFacade customerF = new CustomerFacade();
	
	public static void main(String[] args) throws AdminFacadeException, DoesNotExistException, AlreadyExistException, SQLException, CompanyFacadeException, LoginException, CustomerFacadeException, CouponException {
		
		
//**************************************************************************
//**Here you can find tests for all Admin facade that works with COMPANIES**
//**************************************************************************
	
	////////////////////////////	
	//Here we test createCompany
	////////////////////////////
	
//////////////////////////////////////
//Here we create the COMPANIES objects
//////////////////////////////////////
	
//Company SHEKEM_ELECTRIC = new Company("SHEKEM_ELECTRIC", "S123456", "shekem_electric8080@shekem_electric.com");	
//Company Telma = new Company("Telma", "T123456", "telma8080@telma.com");
//Company Osem = new Company("Osem", "O123456", "osem8080@osem.com");
	
//Should be able to create
//adminF.createCompany(Osem);
//already exist in DB
//adminF.createCompany(Telma);

	///////////////////////////	
	//Here we get all COMPANIES
	///////////////////////////
		
//System.out.println(adminF.getAllCompanies());
	
	///////////////////////////////////////////
	//Here we get all Get Company details by id
	///////////////////////////////////////////
	
//Should be able to get Company details by id
//System.out.println(adminF.GetCompany(5));

//Shouldn't be able to get Company details , the id doe's not exist in DB
//System.out.println(adminF.GetCompany(80));
	
	/////////////////////////////////////////
	//Here we get all COMPANY details by NAME
	/////////////////////////////////////////

//Should be able to get Company details by Company Name
//System.out.println(adminF.GetCompanyByName("SAKAL"));

//Shouldn't be able to get Company details , the Company Name doe's not exist in DB
//System.out.println(adminF.GetCompanyByName("SAKAL1"));

	////////////////////////////////
	//Here we remove COMPANY from DB
	////////////////////////////////

//Should be able to remove Company details
//CompanyDAO compDao = new CompanyDBDAO();
//Company SHEKEM_ELECTRIC_TO_REMOVE = compDao.getCompanyByName("SHEKEM_ELECTRIC");
//adminF.removeCompany(SHEKEM_ELECTRIC_TO_REMOVE);

//Shouldn't be able to remove Company details, company does not exist in DB
//CompanyDAO compDao = new CompanyDBDAO();
//Company SHEKEM_ELECTRIC_TO_REMOVE = compDao.getCompanyByName("SHEKEM_ELECTRIC1");
//adminF.removeCompany(SHEKEM_ELECTRIC_TO_REMOVE);
	
	////////////////////////////////
	//Here we update COMPANY details
	////////////////////////////////

//CompanyDAO compDao = new CompanyDBDAO();
//Company SAKAL_UPDATE = compDao.getCompanyByName("SAKAL");
//SAKAL_UPDATE.setCompName("SAKAL_YASHIR");
//SAKAL_UPDATE.setPassWord("sakalya123456789");
//SAKAL_UPDATE.seteMail("sakalyashir@sakaly.com");
//adminF.UpdateCompany(SAKAL_UPDATE);
	
	
//*************************************************************************
//**Here you can find tests for all Admin facade that works with CUSTOMER**
//*************************************************************************
	
/////////////////////////////
//Here we test createCustomer
/////////////////////////////
	
//////////////////////////////////////
//Here we create the CUSTOMERS objects
//////////////////////////////////////

//Customer Kobe_Bryant = new Customer("Kobe_Bryant", "Kobe1234");
//Customer LeBron_James = new Customer("LeBron_James", "LeBron1234");
		
//Should be able to create
//adminF.createCustomer(Kobe_Bryant);
	
//already exist in DB
//adminF.createCustomer(LeBron_James);
	
		///////////////////////////	
		//Here we get all CUSTOMERS
		///////////////////////////
		
//System.out.println(adminF.getAllCustomers());

		////////////////////////////////////////////
		//Here we get all Get Customer details by id
		////////////////////////////////////////////
		
//Should be able to get Customer details by id
//System.out.println(adminF.GetCustomer(5));

//Shouldn't be able to get Customer details , the id doe's not exist in DB
//System.out.println(adminF.GetCustomer(80));
	
//Should be able to remove Customer details		
//CustomerDAO custDao = new CustomerDBDAO();	
//Customer customer= custDao.getCustomerByName("Kobe_Bryant");
//adminF.RemoveCustomer(customer);

//Shouldn't be able to remove Company details, company does not exist in DB
//CustomerDAO custDao = new CustomerDBDAO();	
//Customer customer= custDao.getCustomerByName("Kobe_Bryantt");
//adminF.RemoveCustomer(customer);
	

////////////////////////////////////////////
//Here we get all Get Customer details by id
////////////////////////////////////////////

//Should be able to get Customer details by id
//System.out.println(adminF.GetCustomer(5));

//Shouldn't be able to get Customer details , the id doe's not exist in DB
//System.out.println(adminF.GetCustomer(80));
		
		////////////////////////////////
		//Here we update COMPANY details
		////////////////////////////////

//CustomerDAO custDao = new CustomerDBDAO();
//Customer Wilt_Chamberlain_UPDATE = compDao.getCustomerByName("SAKAL");
//Wilt_Chamberlain_UPDATE.setCustName("Wilt_Chamberlainnnnn");
//SAKAL_UPDATE.setPassWord("Wilt123456789");
//adminF.UpdateCustomer(Wilt_Chamberlain_UPDATE);

		
		
//************************************************************
//**Here you can find tests for all Company facade functions**
//************************************************************

////////////////////////////
//Here we test createCoupon
////////////////////////////

///////////////////////////////////
//Here we create the COUPON objects
///////////////////////////////////
//Coupon BBB = new Coupon("Burgers", LocalDate.now(),LocalDate.of(2016, Month.SEPTEMBER, 10), 50, CouponType.Restaurants, "Try our new Vegan burger", 24.99, "image");
//Coupon Fifty_Shekel_off_for_all_jeans = new Coupon("50_off_jeans", LocalDate.of(2016, Month.SEPTEMBER, 10), LocalDate.of(2016, Month.SEPTEMBER, 12), 50, CouponType.Clothes, "for old collection",100,"image");	
//Coupon Fifty_percent_off_Electronics= new Coupon("HEVER", LocalDate.now(), LocalDate.of(2016, Month.SEPTEMBER, 27), 100 , CouponType.Electronics, "Only for HEVER members", 100, "image");
//Coupon Fifty_percent_off_Home_and_Styling_Garden= new Coupon("HOME", LocalDate.now(), LocalDate.of(2016, Month.SEPTEMBER, 10), 100 , CouponType.Home_and_Styling_Garden, "The chiper store", 100, "image");
//Coupon Fifty_percent_off_Restaurants= new Coupon("Restaurants", LocalDate.of(2016, Month.SEPTEMBER, 10), LocalDate.of(2016, Month.SEPTEMBER, 12), 50, CouponType.Clothes, "for old collection",100,"image");
//Coupon Fifty_percent_off_Clothes= new Coupon("Clothes", LocalDate.of(2016, Month.SEPTEMBER, 10), LocalDate.of(2016, Month.SEPTEMBER, 12), 50, CouponType.Clothes, "for old collection",100,"image");
//Coupon Buy_one_Get_One_Free_Restaurants = new Coupon("1+1 about Cocktail", LocalDate.now(), LocalDate.of(2016, Month.SEPTEMBER, 10), 50, CouponType.Restaurants, "From 01:00 to 04:00",00.00, "image");
//Coupon Buy_one_Get_One_Free_Food = new Coupon("1+1 about all the Pasta",LocalDate.now(), LocalDate.of(2016, Month.SEPTEMBER, 10), 100, CouponType.Food, "doesn't include with gluten", 00.00, "image");
//Coupon Buy_one_Get_One_Free_Clothes = new Coupon("buy on T-shirt get one for free",LocalDate.now(), LocalDate.of(2016, Month.SEPTEMBER, 10), 80, CouponType.Clothes, "For summer collection", 00.00, "image");
//Coupon Buy_3_Get_One_Free_Home_and_Styling_Garden = new Coupon("buy three chairs and get one for free",LocalDate.now(), LocalDate.of(2016, Month.SEPTEMBER, 10), 100, CouponType.Home_and_Styling_Garden, "For ketter collection", 00.00, "image");
//Coupon Buy_3_Get_One_Free_Food = new Coupon("buy three bisly and get one free",LocalDate.now(), LocalDate.of(2016, Month.SEPTEMBER, 10), 1000, CouponType.Food,"only 70gr packes", 00.00, "image");
//Coupon Pay_70_instead_of_100_Home_and_Styling_Garden = new Coupon("Home_and_Styling",LocalDate.now(), LocalDate.of(2016, Month.SEPTEMBER, 10), 200, CouponType.Home_and_Styling_Garden, "On Garden depart", 70, "image");
//Coupon Pay_70_instead_of_100_Restaurants = new Coupon("ZAKAIM",LocalDate.now(), LocalDate.of(2016, Month.SEPTEMBER, 10), 50, CouponType.Restaurants, "Evening Menu", 70, "image");
//Coupon Pay_70_instead_of_100_Electronics = new Coupon("SAKAL",LocalDate.now(), LocalDate.of(2016, Month.SEPTEMBER, 10), 20, CouponType.Electronics,"for all Coffee machines" ,70 , "image");
//Coupon Pay_70_instead_of_100_Clothes = new Coupon("FOX",LocalDate.now(), LocalDate.of(2016, Month.SEPTEMBER, 10), 500, CouponType.Clothes, "about all winter collection", 70, "image");


//companyF.login("ACE", "ACE123456", ClientType.COMPANY) ;
//companyF.createCoupon(Pay_70_instead_of_100_Home_and_Styling_Garden);

//companyF.login("HOME_CENTER", "HOMECENTER123456", ClientType.COMPANY) ;
//companyF.createCoupon(Buy_3_Get_One_Free_Home_and_Styling_Garden);
//companyF.createCoupon(Fifty_percent_off_Home_and_Styling_Garden);


//companyF.login("SHEKEM_ELECTRIC", "S123456", ClientType.COMPANY) ;
//companyF.createCoupon(Pay_70_instead_of_100_Electronics);

//
//companyF.login("SAKAL", "SAKAL123456", ClientType.COMPANY) ;
//companyF.createCoupon(Fifty_percent_off_Electronics);

//companyF.login("BBB", "BBB123456", ClientType.COMPANY) ;
//companyF.createCoupon(BBB);
//companyF.createCoupon(Pay_70_instead_of_100_Restaurants);
//System.out.println(companyF.getAllCoupon());

//companyF.login("ZAKAIM", "ZAKAIM123456", ClientType.COMPANY) ;
//companyF.createCoupon(Fifty_percent_off_Restaurants);
//companyF.createCoupon(Buy_one_Get_One_Free_Restaurants);
//
//
//companyF.login("FOX", "FOX123456", ClientType.COMPANY) ;  
//companyF.createCoupon(Fifty_Shekel_off_for_all_jeans);
//companyF.createCoupon(Buy_one_Get_One_Free_Clothes);
//
//
//companyF.login("TNT", "TNT123456", ClientType.COMPANY) ;
//companyF.createCoupon(Fifty_percent_off_Clothes);
//companyF.createCoupon(Pay_70_instead_of_100_Clothes);
//
//
//companyF.login("Telma", "T123456", ClientType.COMPANY) ;
//companyF.createCoupon(Buy_one_Get_One_Free_Food);
//System.out.println(companyF.getCouponByType(CouponType.Food));

//companyF.login("Osem", "O123456", ClientType.COMPANY) ;
//companyF.createCoupon(Buy_3_Get_One_Free_Food);


//companyF.login("ZAKAIM", "ZAKAIM123456", ClientType.COMPANY) ;
//System.out.println(companyF.getCouponByType(CouponType.Restaurants));

//companyF.login("ZAKAIM", "ZAKAIM123456", ClientType.COMPANY) ;
//System.out.println(companyF.getCouponsByEndDate(LocalDate.of(2016, Month.SEPTEMBER, 10)));

//companyF.login("ZAKAIM", "ZAKAIM123456", ClientType.COMPANY) ;
//System.out.println(companyF.getCouponsByPrice(10));


//companyF.login("ZAKAIM", "ZAKAIM123456", ClientType.COMPANY) ;
//CouponDBDAO coupDao = new CouponDBDAO();
//Coupon coupon_to_remove = coupDao.getCouponByTitle("1+1 about Cocktail");
//companyF.removeCoupon(coupon_to_remove);

//companyF.login("ZAKAIM", "ZAKAIM123456", ClientType.COMPANY) ;
//CouponDAO coupDao = new CouponDBDAO();
//Coupon Buy_one_Get_One_Free_Restaurants_Updated = coupDao.getCouponByTitle("1+1 about Cocktail");
//Buy_one_Get_One_Free_Restaurants_Updated.setMessage("the end date of this coupon updated to end of the yaer");
//Buy_one_Get_One_Free_Restaurants_Updated.setPrice(8.99);
//companyF.updateCoupon(Buy_one_Get_One_Free_Restaurants_Updated);

//customerF.login("Kareem_Abdul_Jabbar", "Kareem1234", ClientType.CUSTOMER);
//CouponDAO coupDao = new CouponDBDAO();
//Coupon Fifty_Shekel_off_for_all_jeans_TO_purches = coupDao.getCouponByTitle("50_off_jeans");
//customerF.purchaseCoupon(Fifty_Shekel_off_for_all_jeans_TO_purches);

//customerF.login("Charles_Barkley", "Charles1234", ClientType.CUSTOMER);
//CouponDAO coupDao = new CouponDBDAO();
//Coupon Fifty_Shekel_off_for_all_jeans_TO_purches = coupDao.getCouponByTitle("50_off_jeans");
//customerF.purchaseCoupon(Fifty_Shekel_off_for_all_jeans_TO_purches);

//customerF.login("Magic_Johnson", "Magic1234", ClientType.CUSTOMER);
//CouponDAO coupDao = new CouponDBDAO();
//Coupon Fifty_Shekel_off_for_all_jeans_TO_purches = coupDao.getCouponByTitle("50_off_jeans");
//customerF.purchaseCoupon(Fifty_Shekel_off_for_all_jeans_TO_purches);
//System.out.println(customerF.getAllPurchasedCoupons());
//System.out.println(customerF.getAllPurchasedCouponsByType(CouponType.Clothes));
//System.out.println(customerF.getAllPurchasedCouponsByMaxPrice(90));


	
	}
	

}
