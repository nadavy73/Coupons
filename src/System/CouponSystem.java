package System;




 import java.sql.SQLException;

import DAO.CompanyDAO;
import DAO.CouponDAO;
import DAO.CustomerDAO;
import DBDAO.CompanyDBDAO;
import DBDAO.ConnectionPool;
import DBDAO.CouponDBDAO;
import DBDAO.CustomerDBDAO;
import Exceptions.CompanyFacadeException;
import Exceptions.CouponException;
 import Exceptions.CustomerException;
import Exceptions.DoesNotExistException;
import Exceptions.FacadeException;
 import Exceptions.LoginException;
 import Facades.AdminFacade;
import Facades.ClientType;
import Facades.CompanyFacade;
 import Facades.CustomerFacade;
 import Threads.DailyCouponExpirationTask;
 
 public class CouponSystem 
 {
 	//Attributes
 	private static CouponSystem instance = null;
 	private DailyCouponExpirationTask CouponTask= null;
  	private Thread CouponTaskThread;
 	
  	private CompanyDAO compDAO = new CompanyDBDAO();
	private CustomerDAO custDAO = new CustomerDBDAO();
	private CouponDAO couponDAO = new CouponDBDAO();
	
	
	//Constructors

 	private CouponSystem() 
 	{
 		CouponTask = new DailyCouponExpirationTask();
 		CouponTaskThread = new Thread(CouponTask);
 		CouponTaskThread.setDaemon(true);
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
	
 	public CompanyFacade CompanyLogin(String username, String password) throws CompanyFacadeException, LoginException, SQLException, DoesNotExistException 
 	{
 		return CompanyFacade.login(username, password); 			
 }

 	public CustomerFacade CustomerLogin(String username, String password) throws FacadeException, LoginException, CouponException, CustomerException, SQLException, DoesNotExistException 
 	{
 			return CustomerFacade.login(username, password);
 					
 	}				
 	public static AdminFacade AdminLogin(String username, String password) throws FacadeException, LoginException, CouponException 
 	{
 		
 		return AdminFacade.login(username, password);
 			
}
 		// Shut down
 		public void shutDown(){
 			
 				ConnectionPool.getInstance().closeAllConnections();
 				CouponTask.stopTask();
 				CouponTaskThread.interrupt();
 			}

 		public CompanyDAO getCompDAO() {
			return compDAO;
		} // getCompDao
	
		public CustomerDAO getCustDAO() {
			return custDAO;
		} // getCustDao
	
		public CouponDAO getCouponDAO() {
			return couponDAO;
		} // getCouponDao
		
		
 
 
 }
 
