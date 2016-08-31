package System;




 import DAO.CompanyDAO;
import DAO.CouponDAO;
import DAO.CustomerDAO;
import DBDAO.CompanyDBDAO;
import DBDAO.ConnectionPool;
import DBDAO.CouponDBDAO;
import DBDAO.CustomerDBDAO;
import Exceptions.CouponException;
 import Exceptions.CustomerException;
 import Exceptions.FacadeException;
 import Exceptions.LoginException;
 import Facades.AdminFacade;
 import Facades.CompanyFacade;
 import Facades.CustomerFacade;
 import Threads.DailyCouponExpirationTask;
 
 public class CouponSystem 
 {
 	//Attributes
 	private static CouponSystem instance = null;
 	private DailyCouponExpirationTask CouponTask= null;
  	private Thread CouponTaskThread;
 	
  	private CompanyDAO compDAO = null;
	private CustomerDAO custDAO = null;
	private CouponDAO couponDAO = null;
  	//Constructors

 	private CouponSystem() 
 	{
 		compDAO = new CompanyDBDAO();
		custDAO = new CustomerDBDAO();
		couponDAO = new CouponDBDAO();
 		
 		
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
	
 	public CompanyFacade CompanyLogin(String compName, String password) throws FacadeException, LoginException, CouponException 
 	{
 
 		
 		return CouponSystem.getInstance().CompanyLogin(compName, password);
 			
 }

 	public CustomerFacade CustomerLogin(String custName, String password) throws FacadeException, LoginException, CouponException, CustomerException 
 	{
 			return CouponSystem.getInstance().CustomerLogin(custName, password);
 			
}
	
 	public static AdminFacade AdminLogin(String name, String password) throws FacadeException, LoginException, CouponException 
 	{
 		
 		CouponSystem.getInstance();
		return CouponSystem.AdminLogin(name, password);
 			
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
 
