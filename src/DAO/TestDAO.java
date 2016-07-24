package DAO;

import DBDAO.CompanyDBDAO;
import Exceptions.CouponException;
import Exceptions.DaoException;
import JavaBeans.Company;

import java.sql.SQLException;

import DAO.CompanyDAO;


public class TestDAO 
{
public static void main(String[] args) throws DaoException, SQLException, CouponException
{
	CompanyDAO compDAO= new CompanyDBDAO();
	for (int i = 0; i < 25; i++) {
		// Company instance
		Company company = new Company(
				"company " + (i + 100), 
				"password " + (i + 100),
				(i + 100) + "@coupons.com");
	
//	Company comp= new Company("Nadav","Nadavi", "effffff@hotbail.com");
	
		//create Company
//		compDAO.createCompany(company);
	
		//Update Company name
//		compDAO.updateCompanyName("Ofer", "Oferiki");
		
		//Remove Company From DB
//		compDAO.removeCompany("company " + (i + 700));

		//get Company from DB
//		compDAO.getCompany(2);
		
		//get All Companies
//		compDAO.getAllCompanies();
		
		//get Coupons
//		compDAO.getCoupons(1);
		
		compDAO.login("compName", "password");
}
}

}
