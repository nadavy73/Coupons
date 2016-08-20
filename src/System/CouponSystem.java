package System;

import java.beans.PropertyVetoException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

import DBDAO.CompanyDBDAO;
import DBDAO.ConnectionPool;
import DBDAO.CouponsDBDAO;
import DBDAO.CustomerDBDAO;
import Exceptions.CompanyFacadeException;
import Exceptions.FacadeException;
import Facades.AdminFacade;
import Facades.CompanyFacade;
import Facades.CustomerFacade;
import Threads.DailyCouponExpirationTask;

public class CouponSystem {
	// **********
	// Attributes
	// **********
	/**
	 * Holds the CouponSystem single instance
	 */
	private static CouponSystem instance;
	/**
	 * Holds the single CompanyDBDAO instance  
	 */
	private CompanyDBDAO companyDbdao = new CompanyDBDAO();
	/**
	 * Holds the single CustomerDBDAO instance  
	 */
	private CustomerDBDAO customerDbdao = new CustomerDBDAO();
	/**
	 * Holds the single CouponDBDAO instance  
	 */
	private CouponsDBDAO couponDbdao = new CouponsDBDAO();
	/**
	 * Holds the dailyExportationTask thread
	 */
	private DailyCouponExpirationTask dailyExportationTask = new DailyCouponExpirationTask();
	
	
	// ***********
	// constructor
	// ***********
	/**
	 * Construct the CouponSystem singleton. Upon construction a dailyExportationTask thread is being started.
	 */
	private CouponSystem(){
		dailyExportationTask.start();
	}
	

	//***************
	//*****Methods***
	//***************
	
	public static CouponSystem getInstance(){
		if (instance == null){
			instance = new CouponSystem();
		}
		return instance;
	}
	
	/**
	 * Returns the single CompanyDBDAO instance
	 * @return CompanyDBDAO instance
	 */
	public CompanyDBDAO getCompanyDBDAO(){
		return companyDbdao;
	}
	
	/**
	 * Returns the single CustomerDBDAO instance
	 * @return CustomerDBDAO instance
	 */
	public CustomerDBDAO getCustomerDBDAO(){
		return customerDbdao;
	}
	
	/**
	 * Returns the single CouponDBDAO instance
	 * @return CouponDBDAO instance
	 */
	public CouponsDBDAO getCouponDBDAO(){
		return couponDbdao;
	}

	/**
	 * Shut Down of the Coupon System. 
	 * <p>The dailyExpirationTask is being stopped.</p>
	 * <p>The connection to the data base is being closed.</p>
	 */
	public void shutDown(){
		try {
			dailyExportationTask.stopTask();
			ConnectionPool.getInstance().shutDown();
		} catch (IOException | SQLException e) {
			try (FileWriter fstream = new FileWriter("/logs/DailyCouponExpirationTaskLOG.txt");){
				BufferedWriter out = new BufferedWriter(fstream);
				out.write(e.toString());
				out.close();
			} catch (IOException e1) {}
		} finally {
		} 
	}
	
	// ******************
	// ****Login Methods*
	// ******************
	/**
	 * Returns CustomerFacade following successful login of Customer type client or null for unsuccessful login. 
	 * @param name Customer's User Name
	 * @param password Customer's Password
	 * @return CustomerFscade instance
	 */
	public CustomerFacade loginAsCustomer(String name, String password) {
		try {
			return CustomerFacade.login(name, password);
		} catch (FacadeException e) {
			return null; 	
		}
	}
	
	/**
	 * Returns CompanyFacade following successful login of Company type client or null for unsuccessful login.
	 * @param name Company's User Name
	 * @param password Company's Password
	 * @return CompanyFacade instance or null
	 */
	public CompanyFacade loginAsCompany(String name, String password){
		try {
			return CompanyFacade.login(name, password);
		} catch (CompanyFacadeException e) {
			return null;
		} 
	}
	
	/**
	 * Returns AdminFacade following successful login of Admin type client or null for unsuccessful login.
	 * @param name Admin's User Name
	 * @param password Admin's Password
	 * @return AdminFacade instance or null
	 */
	public AdminFacade loginAsAdmin(String name, String password){
			return AdminFacade.login(name, password);
	}
}