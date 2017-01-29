package DBDAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import DBDAO.CouponDBDAO;

import Checks.Checks;
import DAO.CouponDAO;
import DAO.CustomerDAO;
import Exceptions.*;
import JavaBeans.*;

public class CustomerDBDAO implements CustomerDAO 
{
	
	//V
	//***************************************************
	//This function gets Customer Object and insert to DB
	//***************************************************
	@Override
	public long createCustomer (Customer customer) 
			throws  AlreadyExistException, SQLException
	{
		if (Checks.isCustomerExistByName(customer.getCustName()))
			{
			throw new AlreadyExistException
			("Customer Already Exist");
			}
		
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{
				String sql = 
						"INSERT INTO Customer(CUST_NAME,PASSWORD) VALUES (?,?)";
				PreparedStatement stat = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
				stat.setString(1, customer.getCustName());
				stat.setString(2, customer.getPassWord());
				stat.executeUpdate();
				
				long generatedId = -1;
				ResultSet rs = stat.getGeneratedKeys();
				if (rs.next()) {
					generatedId = rs.getLong(1);
				} else {
				    // Error...
				}
				return generatedId;
				
			} 
		catch (SQLException e) 
			{
			throw new SQLException
			("Error in connection to DATA BASE", e);
			} 
	}

	
	//V
	//******************************************************
	//This function gets Customer Name and and remove from DB
	//******************************************************
	@Override
	public void removeCustomer(Customer customer) 
			throws DoesNotExistException
	{
		if (!Checks.isCustomerExistById(customer.getId()))
		{
			throw new DoesNotExistException
			("Customer Does Not Exist");
		}
		
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{
			String sql = 
					"DELETE FROM CUSTOMER WHERE CUST_NAME = ?";
					PreparedStatement stat = con.prepareStatement(sql);
					stat.setString(1, customer.getCustName());
					stat.executeUpdate();
			}
		catch (SQLException e) 
			{
			e.printStackTrace();
			
			}
	}
	
	
	//V	
	//*****************************************************************
	//This function gets Customer Name and replace with new Company name.
	//*****************************************************************
	@Override
	public void updateCustomerByName(String OldName, String NewName) 
			throws DoesNotExistException, SQLException  
	{
		
		//Check if Customer exists in DB
		if (!Checks.isCustomerExistByName(OldName))
		{
			throw new DoesNotExistException
			("Customer Does Not Exist");
		}
		
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{
				String sql =
						"UPDATE Customer SET CUST_NAME = ? WHERE CUST_NAME = ?";
					PreparedStatement stmt = con.prepareStatement(sql);
			
					// Change the name values
					stmt.setString(1, NewName);
					stmt.setString(2, OldName);
					stmt.executeUpdate();
			}
	    catch (SQLException e)
			{
	    		throw new SQLException
	    		("Error in connection to DATA BASE", e);
			}
		
	}
	
	
	//V
	//*****************************************************************
	//This function gets Customer and replace with new Company details
	//*****************************************************************
	@Override
	public void updateCustomer(Customer customer) 
			throws DoesNotExistException, SQLException
	{
		if (!Checks.isCustomerExistById(customer.getId()))
		{
			throw new DoesNotExistException
			("Customer Does Not Exist");
			
		}
		
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{
				String sql = 
						"UPDATE Customer SET CUST_NAME=?, PASSWORD=? WHERE ID=?";
				
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, customer.getCustName());
					stmt.setString(2, customer.getPassWord());
					stmt.setLong(3, customer.getId());
					stmt.executeUpdate();
			
			} 
		catch (SQLException e) 
			{
			throw new SQLException
			("Error in connection to DATA BASE", e);
			}
			
		}
	
	
	//V
	//*****************************************************************
	//This function returns Customer by its Id from DB
	//*****************************************************************
	@Override
	public Customer getCustomerById (long custId)
			throws DoesNotExistException, SQLException
	{
		//Check if Customer exists in DB
		if (!Checks.isCustomerExistById(custId))
			{
				throw new DoesNotExistException
				("Customer Does Not Exist");					
			}
		
		String custName, password;
		Customer customer =new Customer();
		Collection<Coupon> coupons = null;

		try(Connection con=ConnectionPool.getInstance().getConnection())
			{
				String sql = 
						"SELECT * FROM Customer WHERE ID=?";
				PreparedStatement stat = con.prepareStatement(sql);
			
				stat.setLong(1, custId);
				ResultSet rs = stat.executeQuery();
			
			custId = rs.getInt(1);
			custName = rs.getString(2);
			password = rs.getString(3);
			coupons= getCoupons(custId);
			customer = new Customer(custId, custName, password, coupons);
			}
		catch (SQLException e) 
			{
			throw new SQLException
			("Error in connection to DATA BASE", e);
			} 
	return customer;

	}
	
	
	//V
	//*****************************************************************
	//This function returns Customer by its name from DB
	//*****************************************************************
	@Override
	public Customer getCustomerByName(String custName) 
			throws DoesNotExistException, SQLException
	{
		
		if (!Checks.isCustomerExistByName(custName))
		{
			throw new DoesNotExistException
			("Customer Does Not Exist");
			
		}
			Customer customer = null;
			Collection <Coupon> coupons = null;
			String password;
			Long custId;
		
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{
			String sql = 
					"SELECT * FROM Customer WHERE CUST_NAME=?;";
				PreparedStatement stat = con.prepareStatement(sql);
				stat.setString(1, custName);
				ResultSet rs = stat.executeQuery();			
			
			rs.next();
				custId= rs.getLong(1);
				custName = rs.getString(2);
				password = rs.getString(3);
				coupons = getCoupons(custId);
				customer = new Customer(custId,custName,password, coupons);
			
			} 
		catch (SQLException e) 
			{
			throw new SQLException
			("Error in connection to Data Base", e);
			}
			
	return customer;
		
	}
	
	
	//V
	//*****************************************************************
	//This function returns All Customers from DB
	//*****************************************************************
	@Override
	public Collection<Customer> getAllCustomers() 
			throws SQLException,DoesNotExistException 
	{
		Collection <Customer> customers = new  HashSet<>();
		Customer customer= null;
		Collection <Coupon> coupons= null;
		ResultSet rs = null;
		String password=null;
		String custName=null;
		Long ID = null;
		
		
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{
				String sql = 
						"SELECT * FROM Customer";
					PreparedStatement stat = con.prepareStatement(sql);
					rs = stat.executeQuery();
				
				while(rs.next()) 
					{
					ID = rs.getLong("ID");
					custName = rs.getString("CUST_NAME");
					password = rs.getString("PASSWORD");
					coupons= getCoupons(ID);
					customer = new Customer(ID, custName,password, coupons);
					customers.add(customer);
					} 
			} catch (SQLException e) 
		{
		throw new SQLException
		("Error in connection to DATA BASE", e);
		}

	finally 
		{
		if(rs!=null)
				
				rs.close();
		}

return customers;
}
	
	
	//V
	//*****************************************************************
	//This function returns all coupons of some customer
	//*****************************************************************
	@Override
	public Collection<Coupon> getCoupons(long custId) 
			throws DoesNotExistException, SQLException
	
			
	{
		ResultSet rs=null;
		Collection<Coupon> coupons = new ArrayList<>();
		CouponDBDAO couponDB = new CouponDBDAO();
			// Check customer by id
			if (!Checks.isCustomerExistById(custId))
				{
				throw new DoesNotExistException
				("Customer Does Not Exist");	
				}
					
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{
				String sql = 
						"SELECT * FROM Coupon "
							+ "JOIN Customer_Coupon "
							+ "ON Coupon.ID = Customer_Coupon.CouponId "
							+ "WHERE Customer_Coupon.CustId = ?";
			
					PreparedStatement stat = con.prepareStatement(sql);
					stat.setLong(1, custId);
					rs = stat.executeQuery();
			
				while(rs.next())
					{	
					// Generating Coupon
					coupons.add(couponDB.getCouponById(rs.getLong("CouponId")));
					}
			} 
		catch (SQLException e) 
			{
			throw new SQLException
			("Error in connection to Data Base", e);
			}
	finally 
		{
			rs.close();
			}
		
	return coupons; 
		
	}
	
	
	//V
	//********************************************************************************
	//This function returns boolean (False/True) if Customers succeeded login to System
	//********************************************************************************
	@Override
	public boolean login(String custName, String password) 
			throws SQLException 
	{
		ResultSet rs = null;
		boolean hasRows = false;
		
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{
		
			String sql = 
					"SELECT Cust_name, password FROM customer WHERE "
					+ "Cust_name= '" + custName + "'" + " AND " + "password= '" 
					+ password + "'";
			
				PreparedStatement loginStat = con.prepareStatement(sql);
				rs= loginStat.executeQuery();
					
			rs.next();
			
			if (rs.getRow() != 0) {
				hasRows = true;
			}	
				else {
				hasRows=false;
				}
			
			} 
		catch (SQLException e) 
			{
			throw new SQLException
			("Error in connection to Data Base", e);
			}

		finally {
			rs.close();
				}
	return hasRows;
	}
	
	
	//V
	//**********************************************************************************************
	//This function adds coupon to some customer. 
	//Changes can be reviewed on Customer_Coupon table in DB
	//**********************************************************************************************
	@Override
	public void AddCustomerCouponById(long custId, long couponId) 
			throws AlreadyExistException, DoesNotExistException, SQLException
	{
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
			
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{
				String sql =
						"INSERT INTO Customer_Coupon (CustId, CouponId) values (?,?);";
								
				PreparedStatement stat = con.prepareStatement(sql);
				stat.setLong(1, custId);
				stat.setLong(2, couponId);
				
					stat.executeUpdate();
			}
		catch (SQLException e) 
			{
			throw new SQLException
			("Error in connection to DATA BASE", e);
			}
	System.out.println("Coupon no." + couponId+  " was added to Customer "+  "no."+ custId);

	}
	
	
	//V
	//**********************************************************************************************
	//This function removes coupon from some customer by customer Id & coupon Id 
	//Changes can be reviewed on Customer_Coupon table in DB
	//**********************************************************************************************
	@Override
	public void removeCustomerCouponsById(long custId, long couponId) 
			throws DoesNotExistException, SQLException 
	{
		if (!Checks.isPurchased(custId, couponId))
		{
			throw new DoesNotExistException
			("This Coupon is not purchased for this Customer");
		}
		
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{
			String sql =
					"DELETE FROM Customer_Coupon WHERE CustId=?";
				
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setLong(1, custId);
				stmt.executeUpdate();
			}
		catch (SQLException e) 
			{
			throw new SQLException
			("Error in connection to DATA BASE", e);
			}
	System.out.println("Coupon no." + couponId+ "  was removed from Customer no." +custId);
	}
	
	
	//V
	//**********************************************************************************************
	//This function removes coupon from some customer by coupon Id only 
	//Changes can be reviewed on Customer_Coupon table in DB
	//**********************************************************************************************
	@Override
	public void removeCustomerCouponsByCouponId(long couponId) 
				throws DoesNotExistException, SQLException 
	{
		System.out.println("***************removeCustomerCouponsByCouponId************************");
		Connection con=null;
	
		if (!Checks.isCouponExistById(couponId))
				{
					throw new DoesNotExistException
					("This Coupon is not purchased for this Customer");
				}
					
//		try(Connection con=ConnectionPool.getInstance().getConnection())
		try
			{
				con=ConnectionPool.getInstance().getConnection();

				String sql =
						"DELETE FROM Customer_Coupon WHERE CouponId = ?;";
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setLong(1, couponId);
					stmt.executeUpdate();
						
			} 
		catch (SQLException e) 
			{throw new SQLException
			("Error in connection to DATA BASE", e);}
		
		finally
		{ConnectionPool.getInstance().free(con);}	
		System.out.println("Coupon no." + couponId+ "  was removed from All Customers");
		
	}
	


	public Collection<Coupon> getAllCouponsThatCanPurches() throws DoesNotExistException, SQLException
	{
		
		Collection<Coupon> canPurches = new CouponDBDAO().getAllCoupons();
		return canPurches;
	}
	
	
	
	
}


