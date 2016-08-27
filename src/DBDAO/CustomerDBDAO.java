package DBDAO;


//import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.HashSet;
//import java.util.TreeSet;


import Checks.Checks;
import DAO.CustomerDAO;
import Exceptions.*;
//import Facades.ClientType;
import JavaBeans.*;

public class CustomerDBDAO implements CustomerDAO 
{
	//V
	//***************************************************
	//This function gets Customer Object and insert to DB
	//***************************************************
	public void createCustomer (Customer customer) throws  CouponException, AlreadyExistException, DoesNotExistException{

		Connection con = null;
		
		try {
			
		con = ConnectionPool.getInstance().getConnection();
		
		
		
		String sql = "INSERT INTO Customer(CUST_NAME,PASSWORD) VALUES (?,?)";
		PreparedStatement stat = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		stat.setString(1, customer.getCustName());
		stat.setString(2, customer.getPassWord());
		
		if (Checks.isCustomerExistByName(customer.getCustName()))
		{
			throw new AlreadyExistException("Customer Already Exist");
		}
		
		else {
			stat.executeUpdate();
		}
		

	} catch (SQLException e) {

		// Translate exception

		throw new CouponException("Error in connection to DATA BASE", e);

	} // release connection to pool
		finally {
			ConnectionPool.getInstance().free(con);
		}
	}

	//V
	//******************************************************
	//This function gets Customer Name and and remove from DB
	//******************************************************
	public void removeCustomer(Customer customer) throws CouponException, DoesNotExistException, SQLException
	{
		Connection con=null;
		PreparedStatement stat;
		try {
		
		if (!Checks.isCustomerExistByName(customer.getCustName()))
		{
			throw new DoesNotExistException("Customer Does Not Exist");
			
		}
		
		con = ConnectionPool.getInstance().getConnection();
		
		String sql = "DELETE FROM CUSTOMER WHERE CUST_NAME = ?";
		stat = con.prepareStatement(sql);
		stat.setString(1, customer.getCustName());
		
		stat.executeUpdate();
		} // release connection to pool
		finally {
			ConnectionPool.getInstance().free(con);
		}
	}
	
	
	//V	
	//*****************************************************************
	//This function gets Customer Name and replace with new Company name.
	//*****************************************************************
	@Override
	public void updateCustomerByName(String OldName, String NewName) throws CouponException, SQLException, DoesNotExistException {
		Connection con = null;
		
			
		//Check if Company is exist in DB
		if (!Checks.isCustomerExistByName(OldName))
		{
			throw new DoesNotExistException("Customer Does Not Exist");
			
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
		// release connection to pool
		finally {
		ConnectionPool.getInstance().free(con);
		}
}
	
	//V
	@Override
	public void updateCustomer(Customer customer) throws CouponException, SQLException, DoesNotExistException {
		
		Connection con = null;
			
		//Check if Company is exist in DB
		if (!Checks.isCustomerExistByName(customer.getCustName()))
		{
			throw new DoesNotExistException("Customer Does Not Exist");
			
		}
		
		try {
			con = ConnectionPool.getInstance().getConnection();
			{	
				String sql = "UPDATE Customer SET CUST_NAME=?, PASSWORD=? WHERE CUST_NAME=?";
				
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, customer.getCustName());
				stmt.setString(2, customer.getPassWord());
				stmt.setString(3, customer.getCustName());
				stmt.executeUpdate();
			}
			} catch (SQLException e) {
				 
				e.printStackTrace();
			}
			finally {
				ConnectionPool.getInstance().free(con);
					}
		}
	//V
	public Customer getCustomerById (long custId)throws CouponException, SQLException, DoesNotExistException
	{
		Connection con = null;
		String custName, password;
		Customer customer =new Customer();
		
		try {
			con = ConnectionPool.getInstance().getConnection();
			
			String sql = "SELECT * FROM Customer WHERE ID=?";
			PreparedStatement stat = con.prepareStatement(sql);
			
			stat.setLong(1, custId);
			ResultSet rs = stat.executeQuery();
			
			//Check if Company is exist in DB
			if (!rs.next())
			{
				throw new DoesNotExistException("Customer Does Not Exist");
			}
						
			custId = rs.getInt(1);
			custName = rs.getString(2);
			password = rs.getString(3);
			
			customer = new Customer(custId, custName, password);
		}
			catch (SQLException e) {
			
			e.printStackTrace();
			} finally 
			{
				ConnectionPool.getInstance().free(con);
			}

		return customer;

	}
	//V
	public Customer getCustomerByName(String custName) throws CouponException, SQLException, DoesNotExistException{
		Connection con = null;
		Customer customer = null;
		ResultSet rs;
		String password;
		Long Id;
		try {
	
			con = ConnectionPool.getInstance().getConnection();
			String sql = "SELECT * FROM Customer WHERE CUST_NAME=?;";
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setString(1, custName);
			rs = stat.executeQuery();			
			
			if (!rs.next())
			{
				throw new DoesNotExistException("Customer Does Not Exist");
			}
			
			 {
				Id= rs.getLong(1);
				custName = rs.getString(2);
				password = rs.getString(3);
				customer = new Customer(Id,custName,password);
			}
			
			
		} catch (SQLException e) {

		e.printStackTrace();
		}
			finally {
				ConnectionPool.getInstance().free(con);
					}
		return customer;
		
	}
	//V
	@Override
	public Collection<Customer> getAllCustomers() throws CouponException {
		Collection <Customer> customers = new  ArrayList<>();
		Connection con = null;
		String password;
		String custName;
			try {
			con = ConnectionPool.getInstance().getConnection();

			String sql = "SELECT * FROM Customer";
			PreparedStatement stat = con.prepareStatement(sql);
					
			ResultSet rs = stat.executeQuery();
				
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
				
			}
			// release connection to pool
			finally {
				ConnectionPool.getInstance().free(con);
			}
			return customers;
	}
	//V
	@Override
	public Collection<Coupon> getCoupons(long custId) throws CouponException, DoesNotExistException, SQLException 
	{
		Coupon coupon=new Coupon();
		Connection con = null;
		ResultSet rs=null;
		Collection<Coupon> coupons = new ArrayList<>();
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "SELECT * FROM Coupon "
					+ "JOIN Customer_Coupon "
					+ "ON Coupon.ID = Customer_Coupon.CouponId "
					+ "WHERE Customer_Coupon.CustId = ?";
			
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setLong(1, custId);
			// Execute and get a resultSet
			rs = stat.executeQuery();
			
			// Check customer by id
			if (!Checks.isCustomerExistById(custId))
			{
				throw new DoesNotExistException("Customer Does Not Exist");	
			}
			
			if (!rs.next())
			{
				throw new DoesNotExistException("There are no Coupons for this Customer");
			}
			{
				do {
				// Generating Coupon
				coupon = new Coupon(
						rs.getLong("ID"),
						rs.getString("TITLE"), 
						rs.getDate("START_DATE").toLocalDate(),
						rs.getDate("END_DATE").toLocalDate(), 
						rs.getInt("AMOUNT"), 
						CouponType.valueOf(rs.getString("TYPE")),
						rs.getString("MESSAGE"), 
						rs.getDouble("PRICE"), 
						rs.getString("IMAGE"));
				
				coupons.add(coupon);
				}
				while(rs.next());
			}
				
		
		} catch (SQLException e) {
		e.printStackTrace();
		

		}
		// release connection to pool
		finally {
			ConnectionPool.getInstance().free(con);
			rs.close();
			}
		return coupons; 
		
	}
	//V
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
						
					
			// catch
		} catch (SQLException e) {
			e.printStackTrace();

		}

		finally {
			ConnectionPool.getInstance().free(con);
		}

		return hasRows;
	}
	//V
	public void AddCustomerCouponById(long custId, long couponId) throws CouponException, AlreadyExistException, DoesNotExistException, SQLException
	{
		Connection con=null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			
			if (!Checks.isCouponExistById(couponId))
			{
				throw new DoesNotExistException
				("Coupon Does Not Exist"); 
			}
			
			if (!Checks.isCustomerExistById(custId))
			{
				throw new DoesNotExistException
				("Customer Does Not Exist");
			}
			
			if (Checks.isPurchased(custId, couponId))
			{
				throw new AlreadyExistException
				("Coupon was already purchased for this Customer");
			}
			
			else
			{
				String sql =
						"INSERT INTO Customer_Coupon (CustId, CouponId) values (?,?);";
								
				PreparedStatement stat = con.prepareStatement(sql);
				stat.setLong(1, custId);
				stat.setLong(2, couponId);
				
				stat.executeUpdate();
				System.out.println("Coupon no." + couponId+  " was added to Customer "+  "no."+ custId);
			}
		}
		catch (SQLException e) 
			{
			throw new CouponException("Error in connection to DATA BASE", e);
			}
			
		finally {
				ConnectionPool.getInstance().free(con);
			}
	}
	

	//V
	@Override
	public void removeCustomerCouponsById(long custId, long couponId) throws CouponException, DoesNotExistException, AlreadyExistException, SQLException {
		Connection con = null;
		
		if (!Checks.isPurchased(custId, couponId))
		{
			throw new DoesNotExistException("This Coupon is not purchased for this Customer");
		}
		
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql =
					"DELETE FROM Customer_Coupon WHERE CustId=? AND CouponId = ?;";
			PreparedStatement stmt = con.prepareStatement(sql);
		
			//Delete coupons
			stmt.setLong(1, custId);
			stmt.setLong(2, couponId);
			stmt.executeUpdate();
			
			} catch (SQLException e) 
			{
			throw new CouponException("CouponException", e);
			}
			
			System.out.println("Coupon no." + couponId+ "  was removed from Customer no." +custId);
			
			 {
				ConnectionPool.getInstance().free(con);
			 }
		}
	
		//V//
		@Override
		public void removeCustomerCouponsByCouponId(long couponId) throws CouponException, DoesNotExistException, AlreadyExistException, SQLException {
			Connection con = null;
					
				if (!Checks.isCouponExistById(couponId))
				{
					throw new DoesNotExistException("This Coupon is not purchased for this Customer");
				}
					
				try {
					con = ConnectionPool.getInstance().getConnection();
					String sql =
						"DELETE FROM Customer_Coupon WHERE CouponId = ?;";
					PreparedStatement stmt = con.prepareStatement(sql);
					
					//Delete coupons
					stmt.setLong(1, couponId);
					stmt.executeUpdate();
						
					} catch (SQLException e) 
					{
					throw new CouponException("CouponException", e);
					}
						
					System.out.println("Coupon no." + couponId+ "  was removed from All Customers");
						
					 {
						ConnectionPool.getInstance().free(con);
					}		 
	}

}


