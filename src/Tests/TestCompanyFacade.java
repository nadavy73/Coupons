package Tests;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;

import DAO.CompanyDAO;
import DAO.CouponDAO;
import DBDAO.CompanyDBDAO;
import DBDAO.CouponDBDAO;
import Exceptions.AlreadyExistException;
import Exceptions.CompanyFacadeException;
import Exceptions.CouponException;
import Exceptions.DoesNotExistException;
import Exceptions.FacadeException;
import Exceptions.LoginException;
import Facades.ClientType;
import Facades.CompanyFacade;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.CouponType;

public class TestCompanyFacade {
	private static CompanyFacade compFacade = new CompanyFacade();
	
	
	public static void main(String[] args) throws CouponException, AlreadyExistException, CompanyFacadeException, SQLException, DoesNotExistException, FacadeException, LoginException {
	
	
	CouponDAO coupDao= new CouponDBDAO(); 
	
//	Coupon coup = new Coupon("SHIRT", LocalDate.now(),LocalDate.of(2016, Month.AUGUST, 27), 50, CouponType.Clothes, "Fire", 24.99, "T-shirt.jpg");
	
	compFacade.login("Altair","Altair12345", ClientType.COMPANY);
	Coupon coup = coupDao.getCoupon(38);
	//compFacade.getAllCoupon();
	//System.out.println(compFacade.getCoupon(23));
	//System.out.println(compFacade.getCouponByType(CouponType.Restaurants));
	compFacade.removeCoupon(coup);
		
	}
	
	
	
}
