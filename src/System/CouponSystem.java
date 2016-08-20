package System;




 import DBDAO.ConnectionPool;
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
 	private static CouponSystem instance = new CouponSystem();
 	private DailyCouponExpirationTask CouponTask= null;
 //	private CompanyDBDAO compDAO= null;
 //	private CustomerDBDAO custDAO;
 //	private CouponsDBDAO coupDAO;
 	private Thread CouponTaskThread;
 	//Constructors

 	private CouponSystem() 
 	{
 		CouponTask = new DailyCouponExpirationTask();
 		CouponTaskThread = new Thread(CouponTask);
 //		custDAO = new CustomerDBDAO();
 //		compDAO = new CompanyDBDAO();
 //		coupDAO= new CouponsDBDAO();
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
}
