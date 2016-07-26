package DBDAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import javax.swing.JOptionPane;
import DAO.CustomerDAO;
import Exceptions.*;
import JavaBeans.*;

public class CustomerDBDAO implements CustomerDAO 
{
	
	
	//***************************************************
	//This function gets Customer Object and insert to DB
	//***************************************************
	public void createCustomer (Customer customer) throws  CouponException, AlreadyExistException{

		Connection con = null;
		ResultSet rs = null;
		
		try {
			
		Customer c = getCustomerByName(customer.getCustName());
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
		Customer c = getCustomerByName(customer.getCustName());
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
	public void updateCustomerByName(String OldName, String NewName) throws CouponException {
		Connection con = null;
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
	        
	        con.close();
	    }
	    catch (Exception exc)
	    {
	      exc.printStackTrace();
	    }
		System.out.println("Updated Customer name");
		ConnectionPool.getInstance().free(con);	 

	 }

	@Override
	public void updateCustomer(Customer customer) throws CouponException, SQLException, DoesNotExistException {
		
		Connection con = null;
		
		Customer c = getCustomerByName(customer.getCustName());
		
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

	public Customer getCustomerById (long custId)throws CouponException, SQLException
	{
		Customer customer = null;
		String custName, eMail;
		Connection con = null;
		
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "SELECT * FROM Customer WHERE ID=?";
			PreparedStatement stat = con.prepareStatement (sql);
			stat.setLong(1, custId);
			ResultSet rs = stat.executeQuery();
			rs.next();
			custId = rs.getInt("ID");
			custName = rs.getString(2);
			eMail = rs.getString(4);
			
			
			JOptionPane.showMessageDialog(null, "Customer Details: \nID: "+ custId+ "\nCustomer Name: "+ custName + "\nEmail: "+ eMail);
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
		}
		
		ConnectionPool.getInstance().free(con);
		return customer;
		
	}

	@Override
	public Customer getCustomerByName(String custName) throws CouponException, SQLException{
		custName=null;
		
		Customer customer = null;
		Connection con = null;
		
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "SELECT * FROM Customer WHERE CUST_NAME=?";
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setString(1, custName);
			ResultSet rs = stat.executeQuery();
			if (!rs.next())
			{
				return null;
			}
			long custId = rs.getLong(1);
			custName = rs.getString(2);
			String password = rs.getString(3);
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
	public Collection<Customer> getAllCustomer() throws CouponException {
		Collection<Customer> customers = new HashSet<>();
		Connection con = null;
		ResultSet rs = null;
		String custName=null,passWord = null;
		Long ID=null;
		try {
			con = ConnectionPool.getInstance().getConnection();

			String sql = "SELECT * FROM customer";
			PreparedStatement stat = con.prepareStatement(sql);
					
			rs = stat.executeQuery();
				
				while(rs.next()) {

					ID = rs.getLong("ID");
					custName = rs.getString("CUST_NAME");
					passWord = rs.getString("PASSWORD");
					
					
					Customer customer = new Customer(ID, custName,passWord, null);
					

					System.out.println("Customer Details: \nID: "+ ID+ "\nCustomer Name: "+ custName);
					System.out.println("******************");
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
	public Collection<Coupon> getCoupons(long custID) throws CouponException {
		Connection con = null;
		ResultSet rs = null;
		Collection<Coupon> coupons = new ArrayList<>();
		try {
			con = ConnectionPool.getInstance().getConnection();

			String sql = "SELECT * FROM Coupons "
					+ "JOIN Customer_Coupon "
					+ "ON Coupons.ID = Customer_Coupon.COUPON_ID "
					+ "WHERE Customer_Coupon.CUST_ID = " + custID;
			
			//statement - going to Company_Coupon table and getting the list of the coupons that relates to a company.
			PreparedStatement stat = con.prepareStatement(sql);
			
			// Execute and get a resultSet
			rs = stat.executeQuery();
			
			
			
			while (rs.next()) {
				// Generating Coupon
				Coupon coupon = new Coupon(
						rs.getLong("ID"),
						rs.getString("TITLE"), 
						// converting sql.Date to LocalDate
						rs.getDate("START_DATE").toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
						// converting sql.Date to LocalDate
						rs.getDate("END_DATE").toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 
						rs.getInt("AMOUNT"), 
						CouponType.valueOf(rs.getString("TYPE")),
						rs.getString("MESSAGE"), 
						rs.getDouble("PRICE"), 
						rs.getString("IMAGE"));
				
				
				
				System.out.println("******************");
				coupons.add(coupon);
				
			}
		
		} catch (SQLException e) {
		e.printStackTrace();
		ConnectionPool.getInstance().free(con);
		} 
		
	// finally
	finally {
		try {
			rs.close();
			con.close();
		} 
		// catch
		catch (SQLException e) {
			e.printStackTrace();
		} 
	} 
			
			
		// Return coupon
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
					+ "Comp_name= '" + custName + "'" + " AND " + "password= '" 
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
						
				
				System.out.println(hasRows);
				// catch
		} catch (SQLException e) {
	        e.printStackTrace();
	        
            } 
		// finally
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				
			}
		}

	return hasRows;
	}	
	
	@Override
	public void removeCustomerCoupons(long couponId) throws CouponException {
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql =
					"DELETE * FROM Customer_Coupon WHERE CouponId = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
		
			//Delete coupons
			stmt.setLong(1, couponId);
			stmt.executeUpdate();
		        
		        con.close();
		    	
		} catch (SQLException e) {
			throw new CouponException("CouponException", e);
		}
			System.out.println("Customer Coupon no." + couponId+ "  was removed");
			
		 }
		
	public boolean isPurchased(Coupon coupon, Customer customer) throws CouponException {
		
		
		// Get all customers coupons 
		Collection<Coupon> purchasedCoupons = customer.getCoupons(customer.getId());
		boolean result = false;
		
		for (Coupon Coupon : purchasedCoupons){
			if (Coupon.equals(coupon)){
				result = true;
			}
		}
		return result;
	}

	@Override
	public void PurchaseCustomerCouponById(long custId, long couponId) throws  CouponException{
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql =
					"INSERT INTO Customer_Coupon (CustId, CouponId) values (?,?);"; 
			PreparedStatement InsertStat = con.prepareStatement(sql);
		
			//Delete coupons
			InsertStat.setLong(1, custId);
			InsertStat.setLong(2, couponId);
			InsertStat.executeUpdate();
		        
		        con.close();
		    	
		} catch (SQLException e) {
			throw new CouponException("CouponException", e);
		}
			System.out.println("Customer Coupon no." + couponId+ "  was added to Customer no."+ custId);
			
		 }	

	public void PurchaseCustomerCoupon(Customer customer, Coupon coupon) throws CouponException{
		PurchaseCustomerCouponById(customer.getId(), coupon.getId());
	}

	



	}

	


