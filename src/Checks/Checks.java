package Checks;

import java.sql.*;
import DBDAO.ConnectionPool;
import Exceptions.*;
import JavaBeans.*;


public class Checks {

	
	public static boolean isCustomerExistByName(String name) 
	{
		ResultSet rs=null;
		
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{
			String sql = "SELECT * FROM Customer WHERE CUST_NAME=?;"; 
			
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setString(1, name);			
			rs = stat.executeQuery();
			
			// If there is even one line in the response - it means the customer exists
			
			return (rs.next());
			} 
		catch (SQLException e) 
			{
			e.printStackTrace();
			} 
		
	finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;

	}
	
	public static boolean isCompanyExistByName(String name)
	{

		ResultSet rs=null;
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{
			String sql = "SELECT * FROM Company WHERE COMP_NAME=?;"; 
			
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setString(1, name);			
			rs = stat.executeQuery();
			
			// If there is even one line in the response - it means the company exists
			
			return (rs.next());
			} 
		catch (SQLException e) 
			{
			e.printStackTrace();
			} 
		
		finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;

	}
	
	public static boolean isCustomerExistById(long custId)
	{
		Connection con= null;	
		ResultSet rs=null;
		
		try
		{
		con=ConnectionPool.getInstance().getConnection();

			{
			String sql = "SELECT * FROM Customer WHERE ID=?;"; 
			
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setLong(1, custId);			
			rs = stat.executeQuery();
			
			return (rs.next());
			} 
		}
		catch (SQLException e) 
			{
			e.printStackTrace();
			} 
		
	finally {
			try 
				{
				rs.close();
				} 
			catch (SQLException e) 
				{
				e.printStackTrace();
				}
			}
	return false;

}
	
	public static boolean isCompanyExistById(long compId) 
	{
		ResultSet rs=null;
		Connection con= null;	
		
			try
			{
			con=ConnectionPool.getInstance().getConnection();

			
			String sql = "SELECT * FROM Company WHERE ID=?;"; 
			
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setLong(1, compId);			
			rs = stat.executeQuery();
			
			return (rs.next());
			} 
		catch (SQLException e) 
			{
			e.printStackTrace();
			} 
		
		finally 
			{
					try {
						rs.close();
						ConnectionPool.getInstance().free(con);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					} 
				
	return false;

}
	
	public static boolean isCouponExistById(long couponId) 
	{
		ResultSet rs=null;
		Connection con= null;	
		
		try
		{
		con=ConnectionPool.getInstance().getConnection();
			String sql = "SELECT * FROM Coupon WHERE ID= ?;"; 
			
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setLong(1, couponId);			
			rs = stat.executeQuery();
			
			return (rs.next());
			} 
		catch (SQLException e) 
			{
			e.printStackTrace();
			} 
	
	finally {
			try {
				rs.close();
				ConnectionPool.getInstance().free(con);
				} 
			catch (SQLException e) 
				{
				e.printStackTrace();
				}
			}	
	return false;

}
	
	public static boolean isCouponExistByName(String couponTitle)
	{
		ResultSet rs=null;
		Connection con= null;
		
		try {
			con=ConnectionPool.getInstance().getConnection();
			String sql = "SELECT * FROM Coupon WHERE TITLE= ?;"; 
					
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setString(1, couponTitle);			
			rs = stat.executeQuery();
			
			return (rs.next());
			} 
		catch (SQLException e) 
			{
			e.printStackTrace();
			}
		
		finally {
				try {
					rs.close();
					ConnectionPool.getInstance().free(con);
					} 
				catch (SQLException e) 
					{
					e.printStackTrace();
					}
				}
	return false;

}
	
	public static boolean isPurchased(long custId, long couponId) 
			
	{
		ResultSet rs = null;
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{
				String sql =
						"SELECT * FROM Customer_Coupon WHERE CUST_ID= ? and COUPON_ID=?;"; 
				PreparedStatement stat = con.prepareStatement(sql);
					stat.setLong(1, custId);
					stat.setLong(2, couponId);
					rs= stat.executeQuery();
			
					return rs.next();
			
			} 
		catch (SQLException e) 
			{
			e.printStackTrace();
			} 
		
		finally {
				try 
					{
					rs.close();
					} 
				catch (SQLException e) 
					{
					e.printStackTrace();
					}
				}
	return false;

}
	
	public static boolean isPurchased(Customer customer, Coupon coupon) 
			throws AlreadyExistException
	{
		return isPurchased(customer.getId(), coupon.getId());
	}
	
	public static boolean isCompanyPurchased(long compId, long couponId) 
			
	{
		ResultSet rs = null;
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{
				String sql =
						"SELECT * FROM Company_Coupon WHERE COMP_ID= ? and COUPON_ID=?;"; 
				PreparedStatement stat = con.prepareStatement(sql);
				stat.setLong(1, compId);
				stat.setLong(2, couponId);
				rs= stat.executeQuery();
			
				return (rs.next());
			
			} 
		catch (SQLException e) 
			{
			e.printStackTrace();
			} 
		
		finally 
			{
				try 
					{
					rs.close();
					} 
				catch (SQLException e) 
					{
					e.printStackTrace();
					}
			}
		return false;

	}

	public static boolean isCompanyPurchased(Company company, Coupon coupon) 
			throws AlreadyExistException, SQLException  
	{
		return isCompanyPurchased(company.getId(), coupon.getId());
	}


}

