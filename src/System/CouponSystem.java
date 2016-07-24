package System;

import java.sql.SQLException;

import DAO.CompanyDAO;
import DAO.CouponDAO;
import DAO.CustomerDAO;
import DBDAO.CompanyDBDAO;
import DBDAO.ConnectionPool;
import DBDAO.CouponsDBDAO;
import DBDAO.CustomerDBDAO;
import Exceptions.AdminFacadeException;
import Exceptions.CompanyFacadeException;
import Exceptions.CouponException;
import Exceptions.FacadeException;
import Exceptions.LoginException;
import Facades.AdminFacade;
import Facades.ClientType;
import Facades.CompanyFacade;
import Facades.CouponClientFacade;
import Facades.CustomerFacade;
import JavaBeans.Company;
import Threads.DailyCouponExpirationTask;

public class CouponSystem 
{
	//Attributes
	private static CouponSystem instance = new CouponSystem();
	private DailyCouponExpirationTask CouponTask= null;
	private CompanyDBDAO compDAO= null;
	private CustomerDBDAO custDAO;
	private CouponsDBDAO coupDAO;
	private Thread CouponTaskThread;
	//Constructors
	
	private CouponSystem() 
	{
		CouponTask = new DailyCouponExpirationTask();
		CouponTaskThread = new Thread(CouponTask);
		custDAO = new CustomerDBDAO();
		compDAO = new CompanyDBDAO();
		coupDAO= new CouponsDBDAO();
		CouponTaskThread.start();
		
	}
	
	public static CouponSystem getInstance()
	{
		if (instance == null)
		{
			instance = new CouponSystem();
			
		} 
		return instance;
	}
	
	public CompanyFacade CompanyLogin(String compName, String password) throws FacadeException, LoginException, CouponException 
	{
		
			return compDAO;
		
			
	}
	
	public CustomerFacade CustomerLogin(String custName, String password) throws FacadeException, LoginException, CouponException 
	{
		
			return CustomerFacade.login(custName, password, ClientType.CUSTOMER);
		
			
	}
	
	public AdminFacade AdminLogin(String name, String password) throws FacadeException, LoginException, CouponException 
	{
		
			return AdminFacade.login(name, password,ClientType.ADMIN);
		
			
	}
	// Shut down
		public void shutDown(){
			
				ConnectionPool.getInstance().closeAllConnections();
				CouponTask.stopTask();
				CouponTaskThread.interrupt();
			}
	
		@Override
		protected void finalize() throws Throwable {
			numOfInstances--;
		}
	
	
	
	
}
