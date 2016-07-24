package DBDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import DAO.CouponDAO;
import Exceptions.AlreadyExistException;
import Exceptions.CouponException;
import JavaBeans.Coupon;
import JavaBeans.CouponType;

public class CouponsDBDAO implements CouponDAO
{
	
	@Override
	public void createCoupon(Coupon coupon) throws CouponException, AlreadyExistException
	{
		// TODO - test if the coupon already exist
		Connection con = null;
		ResultSet rs = null;

		try {
			
		Coupon c = getCoupon(coupon.getId());
		if (c != null)
			{
				throw new AlreadyExistException("Coupon ID already exist in DB: " + c.getId());
			}
			
		con = ConnectionPool.getInstance().getConnection();
		
		String sql = "INSERT INTO Coupon(TITLE,START_DATE, END_DATE, AMOUNT, TYPE, MESSAGE, PRICE, IMAGE) VALUES (?,?,?,?,?,?,?,?)";
		PreparedStatement Createstat = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		

		Createstat.setString(1, coupon.getTitle());
		Createstat.setDate(2,Date.valueOf(coupon.getStartDate()));
		Createstat.setDate(3,Date.valueOf(coupon.getEndDate()));
		Createstat.setInt(4, coupon.getAmount());
		Createstat.setString(5, coupon.getType().name());
		Createstat.setString(6, coupon.getMessage());
		Createstat.setDouble(7, coupon.getPrice());
		Createstat.setString(8, coupon.getImage());
 
		Createstat.executeUpdate();
	 
		rs = Createstat.getGeneratedKeys();
	 	rs.next();
	 	coupon.setId(rs.getLong("ID"));
		
			}
			catch (SQLException e) {

				// Translate exception

				throw new CouponException("Error in connection to DATA BASE", e);

			} finally {

		
				// release connection to pool
				
				ConnectionPool.getInstance().free(con);
			}
			System.out.println("Coupon was added to DB");

		}
	 		
	
//	private Coupon getCoupon(long id) {
//		// TODO Auto-generated method stub
//		return null;
//	}


	@Override
	public void removeCoupon(Coupon coupon) throws CouponException, SQLException
	{
		Connection con = null;
		PreparedStatement RemoveStat = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			
			String sql =
					"delete from Coupon "
					+ "where ID = ?";
			RemoveStat = con.prepareStatement(sql);
//			
			RemoveStat.setLong(1, coupon.getId());
						
			int numberOfRowsDeleted = RemoveStat.executeUpdate();
		    if (numberOfRowsDeleted == 0) 
		    {
		    	System.out.println("There is no such Coupons in DB! Didn't remove!");
		    	// No rows deleted.
		    }
		    else {
		    	System.out.println("Successful Removal");
		    }
		 
		
	} finally {

		
		// release connection to pool
		
		ConnectionPool.getInstance().free(con);
	}
}	
	
	@Override
	public void updateCoupon(Coupon coupon) throws CouponException 
	{
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();

			
			String sql =
					"UPDATE Coupon SET TITLE=?, START_DATE=?, END_DATE=?, AMOUNT=?,"
								+ " TYPE=?, MESSAGE=?, PRICE=?, IMAGE=? WHERE ID=?";

			PreparedStatement updadeStat = con.prepareStatement(sql);
			
			updadeStat.setString(1, coupon.getTitle());
			updadeStat.setDate(2, Date.valueOf(coupon.getStartDate()));
			updadeStat.setDate(3, Date.valueOf(coupon.getEndDate()));
			updadeStat.setInt(4, coupon.getAmount());
			updadeStat.setString(5, coupon.getType().toString());
			updadeStat.setString(6, coupon.getMessage());
			updadeStat.setDouble(7, coupon.getPrice());
			updadeStat.setString(8, coupon.getImage());
			updadeStat.setLong(9, coupon.getId());
			updadeStat.executeUpdate();
	        
			
			 con.close();
	    
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		System.out.println("Updated Coupon");
		
		// release connection to pool
		ConnectionPool.getInstance().free(con);
		
	 }
	
	@Override
	public Coupon getCoupon(long CouponId) throws CouponException {
		
		Coupon coupon =null;
		Connection con = null;
		
		try {
			con = ConnectionPool.getInstance().getConnection();

			String sql = "SELECT * FROM Coupon WHERE ID=?";
			PreparedStatement GetCouponstat = con.prepareStatement (sql);
			GetCouponstat.setLong(1, CouponId);
			ResultSet rs = GetCouponstat.executeQuery();
			
			coupon = new Coupon(
					rs.getLong("ID"),
					rs.getString("TITLE"),
					rs.getDate("START_DATE").toLocalDate(),
					rs.getDate("END_DATE").toLocalDate(),
					rs.getInt("AMOUNT"),
					CouponType.valueOf(rs.getString("TYPE")),
					rs.getString("MESSEGE"),
					rs.getDouble("PRICE"),
					rs.getString("IMAGE"));
			
			
			System.out.println(coupon);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		// release connection to pool
		ConnectionPool.getInstance().free(con);
		
		//return statement
		return coupon;
		
		
	}
	
	@Override
	public Collection<Coupon> getAllCoupons() throws CouponException {
		
		Collection<Coupon> AllCoupons= new ArrayList<>();

		Connection con = null;
		ResultSet rs = null;

		try {
			con = ConnectionPool.getInstance().getConnection();

			String sql = "SELECT * FROM Coupon";
			PreparedStatement GetAllCouponsStat = con.prepareStatement(sql);
					
			rs = GetAllCouponsStat.executeQuery();
				
				while(rs.next()) {

					Coupon coupon = new Coupon(
							rs.getLong("ID"),
							rs.getString("TITLE"),
							rs.getDate("START_DATE").toLocalDate(),
							rs.getDate("END_DATE").toLocalDate(),
							rs.getInt("AMOUNT"),
							CouponType.valueOf(rs.getString("TYPE")),
							rs.getString("MESSEGE"),
							rs.getDouble("PRICE"),
							rs.getString("IMAGE"));
					
//					
					System.out.println(coupon.toString());
					System.out.println("******************");
					AllCoupons.add(coupon);
		
				} // while loop

			} catch (SQLException e) {
				e.printStackTrace();
			} // catch
			
			finally {
				try {
					rs.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} // catch
			} // finally
			
		// release connection to pool
		ConnectionPool.getInstance().free(con);
		
			return AllCoupons;
		} // getAllCompanies

	@Override
	public Collection<Coupon> getCouponByType(CouponType coupontype) throws CouponException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Set<Long> getCustomersId(Coupon coupon) {
		
		Connection con = null;
		Set<Long> customers = new HashSet<>();
		
		try {
			con = ConnectionPool.getInstance().getConnection();
		
			String sqlCmdStr = "SELECT CUST_ID FROM Customer_Coupon WHERE COUPON_ID=?";
			PreparedStatement stat = con.prepareStatement(sqlCmdStr);
			stat.setLong(1, coupon.getId());
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				customers.add(rs.getLong(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customers;
	}
	
}
