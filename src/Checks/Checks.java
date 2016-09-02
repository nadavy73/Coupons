package Checks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DBDAO.ConnectionPool;
import Exceptions.AlreadyExistException;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.Customer;


public class Checks {

	
	public static boolean isCustomerExistByName(String custName) 
	{

		Connection con = null;
		ResultSet rs=null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			
			String sql = "SELECT * FROM Customer WHERE CUST_NAME=?;"; 
			
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setString(1, custName);			
			rs = stat.executeQuery();
			
			// If there is even one line in the response - it means the coupon exists
			
			return (rs.next());
		} catch (SQLException e) {

			e.printStackTrace();
		} 
		
		// release connection to pool
		finally {
			ConnectionPool.getInstance().free(con);
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;

	}
	
	public static boolean isCompanyExistByName(String compName)
	{

		Connection con = null;
		ResultSet rs=null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			
			String sql = "SELECT * FROM Company WHERE COMP_NAME=?;"; 
			
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setString(1, compName);			
			rs = stat.executeQuery();
			
			// If there is even one line in the response - it means the coupon exists
			
			return (rs.next());
		} catch (SQLException e) {

			e.printStackTrace();
		} 
		
		// release connection to pool
		finally {
			ConnectionPool.getInstance().free(con);
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;

	}
	public static boolean isCustomerExistById(Long custId)
	{
		ResultSet rs=null;
		Connection con = null;
		
		try {
			con = ConnectionPool.getInstance().getConnection();
			
			String sql = "SELECT * FROM Customer WHERE ID=?;"; 
			
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setLong(1, custId);			
			rs = stat.executeQuery();
			
			// If there is even one line in the response - it means the coupon exists
			
			return (rs.next());
		} catch (SQLException e) {

			e.printStackTrace();
		} 
		
		// release connection to pool
		finally {
			ConnectionPool.getInstance().free(con);
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;

	}
	
	public static boolean isCompanyExistById(Long compId) throws SQLException
	{
		ResultSet rs=null;
		Connection con = null;
		
		try {
			con = ConnectionPool.getInstance().getConnection();
			
			String sql = "SELECT * FROM Company WHERE ID=?;"; 
			
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setLong(1, compId);			
			rs = stat.executeQuery();
			
			// If there is even one line in the response - it means the coupon exists
			
			return (rs.next());
		} catch (SQLException e) {

			e.printStackTrace();
		} 
		
		// release connection to pool
		finally {
			ConnectionPool.getInstance().free(con);
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;

	}
	
	public static boolean isCouponExistById(long couponId) throws SQLException 
	{
		Connection con = null;
		ResultSet rs=null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "SELECT * FROM Coupon WHERE ID= ?;"; 
			
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setLong(1, couponId);			
			rs = stat.executeQuery();
			
			// If there is even one line in the response - it means the coupon exists
			
			return (rs.next());
		} catch (SQLException e) {

			e.printStackTrace();
		} // release connection to pool
		finally {
			ConnectionPool.getInstance().free(con);
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;

	}
	public static boolean isCouponExistByName(String TITLE)
	{
		Connection con = null;
		ResultSet rs=null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "SELECT * FROM Coupon WHERE TITLE= ?;"; 
			
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setString(1, TITLE);			
			rs = stat.executeQuery();
			
			// If there is even one line in the response - it means the coupon exists
			
			return (rs.next());
		} catch (SQLException e) {

			e.printStackTrace();
		} // release connection to pool
		
		finally {
			ConnectionPool.getInstance().free(con);
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;

	}
	public static boolean isPurchased(long custId, long couponId) 
			
	{
		Connection con = null;
		ResultSet rs = null;
		try {
			con = ConnectionPool.getInstance().getConnection();

			String sql =
				"SELECT * FROM Customer_Coupon WHERE CustId= ? and CouponId=?;"; 
				PreparedStatement stat = con.prepareStatement(sql);
	
				
			stat.setLong(1, custId);
			stat.setLong(2, couponId);
			rs= stat.executeQuery();
			
			return (rs.next());
			
		} catch (SQLException e) {

			e.printStackTrace();
		} 
		
		finally {
			ConnectionPool.getInstance().free(con);
			try {
				rs.close();
			} catch (SQLException e) {
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
		Connection con = null;
		ResultSet rs = null;
		try{
			con = ConnectionPool.getInstance().getConnection();

			String sql =
				"SELECT * FROM Company_Coupon WHERE COMP_ID= ? and COUPON_ID=?;"; 
				PreparedStatement stat = con.prepareStatement(sql);
	
				
			stat.setLong(1, compId);
			stat.setLong(2, couponId);
			rs= stat.executeQuery();
			
			return (rs.next());
			
		} catch (SQLException e) {

			e.printStackTrace();
		} 
		
		finally {
			ConnectionPool.getInstance().free(con);
			try {
				rs.close();
			} catch (SQLException e) {
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

