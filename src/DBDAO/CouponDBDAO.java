package DBDAO;

import java.sql.*;
import java.sql.Date;
import java.time.*;
import java.util.*;
import Checks.*;
import DAO.*;
import Exceptions.*;
import JavaBeans.*;

public class CouponDBDAO implements CouponDAO
{
		//V
		//*************************************************
		//This function gets Coupon Object and INSERT to DB
		//*************************************************
		public void createCoupon(Coupon coupon) throws CouponException, AlreadyExistException, DoesNotExistException {
			
			Connection con = null;
			
			try {
				
				con = ConnectionPool.getInstance().getConnection();
				
				
				
				String sql = "INSERT INTO Coupon (TITLE, START_DATE, END_DATE, AMOUNT,"+ " TYPE, MESSAGE, PRICE, IMAGE) VALUES(?,?,?,?,?,?,?,?)";
				PreparedStatement stat = con.prepareStatement (sql);
				
				stat.setString(1, coupon.getTitle());
				stat.setDate(2, Date.valueOf(coupon.getStartDate()));
				stat.setDate(3, Date.valueOf(coupon.getEndDate()));
				stat.setInt(4, coupon.getAmount());
				stat.setString(5, coupon.getType().toString());
				stat.setString(6, coupon.getMessage());
				stat.setDouble(7, coupon.getPrice());
				stat.setString(8, coupon.getImage());
				
				if (Checks.isCouponExistByName(coupon.getTitle()))
				{
					throw new AlreadyExistException("Coupon Already Exist");
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
		//***************************************************
		//This function gets Coupon Object and REMOVE from DB
		//***************************************************		
		public void removeCoupon(Coupon coupon) throws CouponException, SQLException, DoesNotExistException
	{
		Connection con = null;
		PreparedStatement stat = null;
		
			if (!Checks.isCouponExistByName(coupon.getTitle()))
			{
				throw new DoesNotExistException("Coupon Does Not Exist");
			}
			
			try {
			
			con = ConnectionPool.getInstance().getConnection();
			
			String sql = "DELETE FROM COUPON WHERE TITLE = ?";
			stat = con.prepareStatement(sql);
			stat.setString(1, coupon.getTitle());
			stat.executeUpdate();			
			
			} catch (SQLException e) {
				 
				e.printStackTrace();
			}
			
		// release connection to pool
		finally {
			ConnectionPool.getInstance().free(con);
		}
	}
		//V	
		//****************************************************************
		//This function gets Coupon Object and Update all parameters in DB
		//****************************************************************
		public void updateCoupon(Coupon coupon) throws CouponException, DoesNotExistException, SQLException {
		
		Connection con = null;
		
		if (!Checks.isCouponExistByName(coupon.getTitle()))
		{
			throw new DoesNotExistException("Customer Does Not Exist");
			
		}
		
		try {
			con = ConnectionPool.getInstance().getConnection();
			{
				String sql = "UPDATE Coupon SET TITLE=?, START_DATE=?, END_DATE=?, AMOUNT=?,"+ " TYPE=?, MESSAGE=?, PRICE=?, IMAGE=? WHERE TITLE=?";
			
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
			}
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}
		finally {
			ConnectionPool.getInstance().free(con);
				}
	}
	//V
	//**********************************************************************************
	//This function gets Coupon Object and Update his amount in DB after purchase coupon
	//**********************************************************************************
	public void updateAmountOfCoupon(long couponId) throws CouponException, AlreadyExistException, DoesNotExistException, SQLException 
		{	
		Connection con = null;
		
		if (!Checks.isCouponExistById(couponId))
		{
			throw new DoesNotExistException("Coupon Does not exist");
		}
	int amount = getCoupon(couponId).getAmount();

	if(!(amount>0))
		throw new DoesNotExistException("Coupon was sold out");
		{
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql =
					"UPDATE Coupon SET AMOUNT= AMOUNT +(-1) WHERE ID=?";
			PreparedStatement stmt = con.prepareStatement(sql);
		
			stmt.setLong(1, couponId);
			stmt.executeUpdate();
		}
		catch (SQLException e) 
		{
		throw new CouponException();
		}
		ConnectionPool.getInstance().free(con);
		}
		}

		
		
		
		
		//V	
		//**********************************************************************************
		//This function gets Coupon Title and Returns coupon Object that contains this title
		//**********************************************************************************
		public Coupon getCouponByTitle (String TITLE) throws CouponException, DoesNotExistException{
		Coupon coupon = new Coupon();
		Connection con = null;
		ResultSet rs;
		
		try {
			
			con = ConnectionPool.getInstance().getConnection();
			String sql = "SELECT * FROM Coupon WHERE TITLE=?";
			PreparedStatement stat = con.prepareStatement (sql);
			stat.setString(1, TITLE);
			rs = stat.executeQuery();
			
			if (!rs.next())
			{
				throw new DoesNotExistException("Coupon Does Not Exist");
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
		//V	
		//****************************************************************************
		//This function gets Coupon Id and Returns coupon Object that contains this Id
		//****************************************************************************
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
		//V
		//*****************************************
		//This function Returns All coupons from DB
		//*****************************************
		public Collection<Coupon> getAllCoupons() throws CouponException, DoesNotExistException {
		
		Collection<Coupon> AllCoupons= new ArrayList<>();
		
		
		Long ID;
		String TITLE,MESSAGE,IMAGE;
		LocalDate START_DATE,END_DATE;
		int AMOUNT;
		CouponType TYPE;
		double PRICE;
		Coupon coupon = null;
		Connection con = null;
		ResultSet rs = null;
			
			try {
			con = ConnectionPool.getInstance().getConnection();

			String sql = "SELECT * FROM Coupon";
			PreparedStatement stat = con.prepareStatement (sql);
			
			rs = stat.executeQuery();
			
			while(rs.next())
			{
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
				AllCoupons.add(coupon);
			
			}// while loop
			
		} catch (SQLException e) {
				e.printStackTrace();
			
		} 
			// release connection to pool
			finally {
				ConnectionPool.getInstance().free(con);
			} 
			return AllCoupons;
	} 
		
		
			
		//V
		//*****************************************
		//This function Returns All coupons BY Type
		//*****************************************
		public Collection<Coupon> getCouponByType(CouponType coupontype) throws CouponException, DoesNotExistException {
			Collection <Coupon> Coupons= new ArrayList<>();
			Connection con = null;
			Long ID;
			String TITLE,MESSAGE,IMAGE;
			LocalDate START_DATE,END_DATE;
			int AMOUNT;
			CouponType TYPE;
			double PRICE;
			try {
				con = ConnectionPool.getInstance().getConnection();
				
				String sql = "SELECT * FROM Coupon WHERE TYPE=?";
				PreparedStatement stat = con.prepareStatement (sql);
				stat.setString(1, coupontype.name());
				ResultSet rs = stat.executeQuery();
				
				while(rs.next()) 
				{
					
					ID = rs.getLong(1);
					TITLE = rs.getString(2);
					START_DATE = rs.getDate(3).toLocalDate();
					END_DATE = rs.getDate(4).toLocalDate();
					AMOUNT = rs.getInt(5);
					TYPE = CouponType.valueOf(rs.getString(6));
					MESSAGE = rs.getString(7);
					PRICE = rs.getDouble(8);
					IMAGE = rs.getString(9);
				
					Coupon coupon = new Coupon(ID, TITLE, START_DATE, END_DATE, AMOUNT, TYPE, MESSAGE, PRICE, IMAGE);
					Coupons.add(coupon);
				
				}
				if(Coupons.isEmpty())
				{
					throw new DoesNotExistException("We don't have this couponType");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			
			}
			// release connection to pool
			finally {
				ConnectionPool.getInstance().free(con);
			}
			return Coupons;
		}
		//V
		//*******************************************************
		//This function Returns All customer that have the coupon
		//*******************************************************
		public Collection<Customer> getCustomersWhoHaveCoupon(long couponId) throws CouponException, AlreadyExistException, DoesNotExistException {
		
		Connection con = null;
//		Set<Long> customers = new HashSet<>();
		ResultSet rs;
		Collection <Customer> custCoupons = new ArrayList<>();
		try {
			con = ConnectionPool.getInstance().getConnection();
		
			String sql = "SELECT * FROM Customer "
					+ "JOIN Customer_Coupon "
					+ "ON Customer.ID = Customer_Coupon.CustId "
					+ "WHERE Customer_Coupon.CouponId = ?";
			PreparedStatement stat = con.prepareStatement(sql);
			
			stat.setLong(1,couponId);
			rs = stat.executeQuery();
			 if (!Checks.isCouponExistById(couponId))
			 {
				 throw new DoesNotExistException("Coupon Does not exist");
			 }
			 {
				 while(rs.next())
				 {
						Customer customer = new Customer(
						 rs.getLong("ID"),
						 rs.getString("CUST_NAME"),
						 rs.getString("PASSWORD"));
			
			custCoupons.add(customer);
			}
			while(rs.next());
			 }
			
		} catch (SQLException e) {
			e.printStackTrace();
			

			}
			// release connection to pool
			finally {
				ConnectionPool.getInstance().free(con);
				}
			return custCoupons; 
			
		}
		
		
	
	
}
