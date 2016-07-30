package DBDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import DAO.CouponDAO;
import Exceptions.AlreadyExistException;
import Exceptions.CouponException;
import Exceptions.DoesNotExistException;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.CouponType;

public class CouponsDBDAO implements CouponDAO
{
	
		//*************************************************
		//This function gets Coupon Object and insert to DB
		//*************************************************

		@Override
		public void createCoupon(Coupon coupon) throws CouponException, AlreadyExistException, DoesNotExistException {
			Connection con = null;
			

			try {
				Coupon c = getCouponByTitle(coupon.getTitle());
				if (c != null)
				{
					throw new AlreadyExistException("Coupon ID already exist in DB");
				}
				
				con = ConnectionPool.getInstance().getConnection();
				String sql = "INSERT INTO Coupon (TITLE, START_DATE, END_DATE, AMOUNT,"
									+ " TYPE, MESSAGE, PRICE, IMAGE) VALUES(?,?,?,?,?,?,?,?)";
				PreparedStatement stat = con.prepareStatement (sql);
				stat.setString(1, coupon.getTitle());
				stat.setDate(2, Date.valueOf(coupon.getStartDate()));
				stat.setDate(3, Date.valueOf(coupon.getEndDate()));
				stat.setInt(4, coupon.getAmount());
				stat.setString(5, coupon.getType().toString());
				stat.setString(6, coupon.getMessage());
				stat.setDouble(7, coupon.getPrice());
				stat.setString(8, coupon.getImage());
				stat.executeUpdate();
				// Result set retrieves the SQL auto-generated ID
				ResultSet rs = stat.getGeneratedKeys();
				rs.next();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//***************************************************
		//This function gets Coupon Object and REMOVE from DB
		//***************************************************		
	
		@Override
	public void removeCoupon(Coupon coupon) throws CouponException, SQLException, DoesNotExistException
	{
		Connection con = null;
		PreparedStatement stat = null;
		try {
			Coupon c = getCouponByTitle(coupon.getTitle());
			
			if (c == null)
			{
				throw new DoesNotExistException("COUPON Does Not Exist in DB");
			}
			
			con = ConnectionPool.getInstance().getConnection();
			
			String sql = "DELETE FROM COUPON WHERE TITLE = ?";
			stat = con.prepareStatement(sql);
			stat.setString(1, coupon.getTitle());
			stat.executeUpdate();			
			
		} finally {

			
			// release connection to pool
			
			ConnectionPool.getInstance().free(con);
		}
	}
	
	@Override
	public void updateCoupon(Coupon coupon) throws CouponException, DoesNotExistException {
		Connection con = null;
		
		Coupon c = getCouponByTitle(coupon.getTitle());
		
		
		if (c == null)
		{
			throw new DoesNotExistException("The Coupon Does not exist in DB");
		}
		
		try {
			con = ConnectionPool.getInstance().getConnection();

			
			String sql = "UPDATE Coupon SET TITLE=?, START_DATE=?, END_DATE=?, AMOUNT=?,"
								+ " TYPE=?, MESSAGE=?, PRICE=?, IMAGE=? WHERE TITLE=?";
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setString(1, coupon.getTitle());
			stat.setDate(2, Date.valueOf(coupon.getStartDate()));
			stat.setDate(3, Date.valueOf(coupon.getEndDate()));
			stat.setInt(4, coupon.getAmount());
			stat.setString(5, coupon.getType().toString());
			stat.setString(6, coupon.getMessage());
			stat.setDouble(7, coupon.getPrice());
			stat.setString(8, coupon.getImage());
			stat.setString(9, coupon.getTitle());
			stat.executeUpdate();
	
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {

		
		// release connection to pool
		
		ConnectionPool.getInstance().free(con);
	}
}

	public Coupon getCouponByTitle (String TITLE) throws CouponException{
		Coupon coupon =null;
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "SELECT * FROM Coupon WHERE TITLE=?";
			PreparedStatement stat = con.prepareStatement (sql);
			stat.setString(1, TITLE);
			ResultSet rs = stat.executeQuery();
			if (!rs.next())
			{
				return null;
			}
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
			
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		// release connection to pool
		ConnectionPool.getInstance().free(con);
		
		//return statement
		return coupon;
		
		
	}
	
	
	@Override
	public Coupon getCoupon(long CouponId) throws CouponException, AlreadyExistException, DoesNotExistException {
		
		Long ID;
		String TITLE,MESSAGE,IMAGE;
		LocalDate START_DATE,END_DATE;
		int AMOUNT;
		CouponType TYPE;
		double PRICE;
		Coupon coupon = null;
		
		Connection con = null;
		
		try {
		
			con = ConnectionPool.getInstance().getConnection();

			String sql = "SELECT * FROM Coupon WHERE ID=?";
			PreparedStatement stat = con.prepareStatement (sql);
			stat.setLong(1, CouponId);
			ResultSet rs = stat.executeQuery();
			if (!rs.next())
			{
				throw new DoesNotExistException("The Coupon does not found");
			}
			
			ID = rs.getLong(1);
			TITLE = rs.getString(2);
			START_DATE = rs.getDate(3).toLocalDate();
			END_DATE = rs.getDate(4).toLocalDate();
			AMOUNT = rs.getInt(5);
			TYPE = CouponType.valueOf(rs.getString(6));
			MESSAGE = rs.getString(7);
			PRICE = rs.getDouble(8);
			IMAGE = rs.getString(9);
			
			coupon = new Coupon(ID, TITLE, START_DATE, END_DATE, AMOUNT, TYPE, MESSAGE, PRICE, IMAGE);
			
					
		} catch (SQLException e) {
			
			e.printStackTrace();
	} finally {
		ConnectionPool.getInstance().free(con);
	}	
		
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
							rs.getString("MESSAGE"),
							rs.getDouble("PRICE"),
							rs.getString("IMAGE"));
					
//					
					
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
