package DBDAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import DAO.CustomerDAO;
import Exceptions.*;
import JavaBeans.*;

public class CustomerDBDAO implements CustomerDAO 
{
	
	
	//***************************************************
	//This function gets Customer Object and insert to DB
	//***************************************************
	public void createCustomer (Customer customer) throws  CouponException, AlreadyExistException, DoesNotExistException{

		Connection con = null;
		ResultSet rs = null;
		
		try {
			
		Customer c = getCustomer(customer.getCustName(), customer.getPassWord());
		if (c != null)
		{
			throw new AlreadyExistException("CUSTOMER AlreadyExist");
		}
		
		con = ConnectionPool.getInstance().getConnection();
		String sql = "INSERT INTO Customer(CUST_NAME,PASSWORD) VALUES (?,?)";
		PreparedStatement stat = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		stat.setString(1, customer.getCustName());
		stat.setString(2, customer.getPassWord());
		
		stat.executeUpdate();
	    
	    rs = stat.getGeneratedKeys();
		rs.next();
		customer.setId(rs.getLong(1));

	} catch (SQLException e) {

		// Translate exception

		throw new CouponException("Error in connection to DATA BASE", e);

	} finally {


		// release connection to pool
		
		ConnectionPool.getInstance().free(con);
	}
}
	//******************************************************
	//This function gets Company Name and and remove from DB
	//******************************************************
	
	public void removeCustomer(Customer customer) throws CouponException, DoesNotExistException, SQLException
	{
		Connection con = null;
		PreparedStatement stat = null;
		try {
		
		Customer c = getCustomer(customer.getCustName(), customer.getPassWord());
		if (c == null)
		{
			throw new DoesNotExistException("CUSTOMER Does Not Exist");
			
		}
		
		con = ConnectionPool.getInstance().getConnection();
		
		String sql = "DELETE FROM CUSTOMER WHERE CUST_NAME = ?";
		stat = con.prepareStatement(sql);
		stat.setString(1, customer.getCustName());
		stat.executeUpdate();
			
//			
			
//			int numberOfRowsDeleted = stat.executeUpdate();
//		    
//			if (numberOfRowsDeleted == 0) {
//				throw new DoesNotExistException("Customer dosen't Exist");
//		    }
		
		} finally {

			
			// release connection to pool
			
			ConnectionPool.getInstance().free(con);
		}
	}

	//*****************************************************************
	//This function gets Customer Name and replace with new Company name.
	//*****************************************************************
	
	@Override
	public void updateCustomerByName(String OldName, String NewName) throws CouponException, SQLException, DoesNotExistException {
Connection con = null;
		
		Customer c = getCustomer(OldName, NewName);
		
		//Check if Company is exist in DB
		if (c == null)
		{
			throw new DoesNotExistException("The Customer Does not exist in DB");
		}
		try {
			con = ConnectionPool.getInstance().getConnection();

			
			// Create a statement for retrieving and updating data
			String sql =
					"UPDATE Customer SET CUST_NAME = ? WHERE CUST_NAME = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			
			// Change the name values
	        stmt.setString(1, NewName);
	        stmt.setString(2, OldName);
	        stmt.executeUpdate();
	        
	       
	    }
	    catch (Exception exc)
	    {
	      exc.printStackTrace();
	    }
		
		ConnectionPool.getInstance().free(con);	 

	 }

	@Override
	public void updateCustomer(Customer customer) throws CouponException, SQLException, DoesNotExistException {
		
		Connection con = null;
		
		Customer c = getCustomer(customer.getCustName(), customer.getPassWord());
		
		//Check if Company is exist in DB
		if (c == null)
		{
			throw new DoesNotExistException("The Customer Does not exist in DB");
		}
		
		try {
			con = ConnectionPool.getInstance().getConnection();
			
			
			{	
				String sql = "UPDATE Customer SET CUST_NAME=?, PASSWORD=? WHERE CUST_NAME=?";
				
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, customer.getCustName());
				stmt.setString(2, customer.getPassWord());
				stmt.setString(3, c.getCustName());
				stmt.executeUpdate();
			}
			} catch (SQLException e) {
				 
				e.printStackTrace();
			} finally {

				
				// release connection to pool
				
				ConnectionPool.getInstance().free(con);
			}

		}

	public Customer getCustomerById (long custId)throws CouponException, SQLException, DoesNotExistException
	{
		Connection con = null;
		PreparedStatement getCustStat = null;
		String custName, password;
		Customer customer=null;
		
		try {

		con = ConnectionPool.getInstance().getConnection();
			String sql = "SELECT * FROM Customer WHERE ID=?";
			getCustStat = con.prepareStatement (sql);
			getCustStat.setLong(1, custId);
			ResultSet rs = getCustStat.executeQuery();
			if (!rs.next())
			{
				throw new DoesNotExistException("Customer does't exist");
			}
						
			custId = rs.getInt(1);
			custName = rs.getString(2);
			password = rs.getString(3);
			
			customer = new Customer(custId, custName, password);
		}
		catch (SQLException e) {
			
			e.printStackTrace();
} finally {

			
			// release connection to pool
			
			ConnectionPool.getInstance().free(con);
		}

		return customer;

	}
	
	@Override
	public Customer getCustomer(String custName, String password) throws CouponException, SQLException, DoesNotExistException{
		Connection con = null;
		PreparedStatement getCustStat=null;
		Customer customer = null;
		Coupon coupon= new Coupon();
		try {
	
			con = ConnectionPool.getInstance().getConnection();
			String sql = "SELECT * FROM Customer WHERE CUST_NAME=?";
			getCustStat = con.prepareStatement(sql);
			getCustStat.setString(1, custName);
			ResultSet rs = getCustStat.executeQuery();
			if (rs.next())
			{
				throw new DoesNotExistException("There is no such customer in DB");
			}
			
			if (coupon.getAmount()<1)
			{
				try {
					throw new CouponException("Coupon ID:" + coupon.getId() 
					+ " (" + coupon.getTitle() + ") is not in stock");
				} catch (CouponException e) {
					
				}
			}
			long custId = rs.getLong(1);
			custName = rs.getString(2);
			password = rs.getString(3);
			customer = new Customer(custId, custName, password);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			
			// release connection to pool
			
			ConnectionPool.getInstance().free(con);
		}
		return customer;
		
	}

	@Override
	public Collection<Customer> getAllCustomers() throws CouponException {
		Collection<Customer> customers = new HashSet<>();
		Connection con = null;
		ResultSet rs = null;
		String password;
		String custName;
			try {
			con = ConnectionPool.getInstance().getConnection();

			String sql = "SELECT * FROM customer";
			PreparedStatement stat = con.prepareStatement(sql);
					
			rs = stat.executeQuery();
				
				while(rs.next()) 
				{

					long custId = rs.getLong(1);
					custName = rs.getString(2);
					password = rs.getString(3);
					
					
					Customer customer = new Customer(custId, custName,password);
					customers.add(customer);
		
				} // while loop

			} catch (SQLException e) {
				e.printStackTrace();
				throw new CouponException();
			} // catch
			
			finally {
				try {
					rs.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} // catch
			} // finally
			
			ConnectionPool.getInstance().free(con);
			return customers;
	}

	@Override
	public Collection<Coupon> getCoupons(long custId) throws CouponException, DoesNotExistException {
		
		CouponsDBDAO couponDB = new CouponsDBDAO();
		Connection con = null;
		ResultSet rs = null;
		Collection<Coupon> coupons = new ArrayList<>();
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "SELECT CouponId FROM Customer_Coupon WHERE CustId=?";

			PreparedStatement stat = con.prepareStatement(sql);

			stat.setLong(1, custId);
			rs = stat.executeQuery();
			
			if (!rs.next())
			{
				throw new DoesNotExistException("There are no Coupons for this Customer");
			}
			
			while (rs.next()) {
			
				System.out.println("*************");
				
				try {
					coupons.add(couponDB.getCoupon(rs.getLong("CouponId")));
				} catch (AlreadyExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
		
		} catch (SQLException e) {
		
			e.printStackTrace();
			}	
			ConnectionPool.getInstance().free(con);
		
		return coupons;
	}	
	
	@Override
	public boolean login(String custName, String password) throws CouponException {
		Connection con = null;
		ResultSet rs = null;
		boolean hasRows = false;
		
		try {
			con = ConnectionPool.getInstance().getConnection();
		
			String sql = "SELECT Cust_name, password FROM customer WHERE "
					+ "Cust_name= '" + custName + "'" + " AND " + "password= '" 
					+ password + "'";
			
			// Select prepared statement
			PreparedStatement loginStat = con.prepareStatement(sql);
			rs= loginStat.executeQuery();
					
			rs.next();
			
			if (rs.getRow() != 0) {
				hasRows = true;
			}	
				else {
				hasRows=false;
				}
						
				
//			String message = (hasRows) ? "Successful Customer Login ": "Failed to login as Customer ";
			
//			System.out.println(message);
			
			// catch
		} catch (SQLException e) {
			e.printStackTrace();

		}

		finally {
			ConnectionPool.getInstance().free(con);
		}

		return hasRows;
	}
	
	@Override
	public void removeCustomerCoupons(long couponId) throws CouponException, DoesNotExistException {
		Connection con = null;
		
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql =
					"DELETE FROM Customer_Coupon WHERE CouponId = ?;";
			PreparedStatement stmt = con.prepareStatement(sql);
		
			//Delete coupons
			stmt.setLong(1, couponId);
			stmt.executeUpdate();
			
				
			
		   
			
		} catch (SQLException e) {
			throw new CouponException("CouponException", e);
		}
			System.out.println("Customer Coupon no." + couponId+ "  was removed");
			ConnectionPool.getInstance().free(con);
		 }
		
	public boolean isPurchased(Coupon coupon, Customer customer) throws CouponException, AlreadyExistException 
	{
		boolean result = false;
		ResultSet rs=null;
		Connection con = null;
		
		try{
			con = ConnectionPool.getInstance().getConnection();

			String sql =
				"SELECT * FROM Customer_Coupon WHERE CustId= ? and CouponId=? ;";
//				+ "ON DUPLICATE KEY UPDATE CustId = CustId AND CouponId = CouponId ;"; 
				PreparedStatement stat = con.prepareStatement(sql);
	
				
//			stat.setLong(1, customer.getId());
			stat.setLong(2, coupon.getId());
			rs= stat.executeQuery();
			
			while (rs.next())
			{            
		     
				throw new AlreadyExistException("Coupon was already purchased for this Customer");

			}
		}
		catch (SQLException e) 
	{
		throw new CouponException("Coupon Exception", e);
	}
		ConnectionPool.getInstance().free(con);
	
		return result;

}

	@Override
	public void PurchaseCustomerCoupon(Customer customer, Coupon coupon) throws CouponException, AlreadyExistException, DoesNotExistException, SQLException
	{
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();

			String sql =
				"INSERT INTO Customer_Coupon (CustId, CouponId) values (?,?);";
//				+ "ON DUPLICATE KEY UPDATE CustId = CustId AND CouponId = CouponId ;"; 
				PreparedStatement stat = con.prepareStatement(sql);
	
				
			stat.setLong(1, customer.getId());
			stat.setLong(2, coupon.getId());
			
			if (isPurchased(coupon, customer))
			{
				throw new AlreadyExistException("Coupon was already purchased for this Customer");

			}
			else
			{
				stat.executeUpdate();
//				System.out.println("Coupon no." + coupon.getId()+ "(" + coupon.getTitle()+ ")" + " was added to Customer "+ customer.getCustName() + "(no."+ customer.getId()+ ")");
			}
		}
			catch (SQLException e) 
		{
			throw new CouponException("Coupon Exception", e);
		}
			ConnectionPool.getInstance().free(con);
		
		}

	
	
	public void PurchaseCustomerCouponById(long custId, long couponId) throws CouponException, AlreadyExistException, DoesNotExistException, SQLException
	{
//		PurchaseCustomerCouponById(customer.getId(), coupon.getId());
	}













}
