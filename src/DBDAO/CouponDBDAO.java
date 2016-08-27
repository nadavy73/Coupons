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
	public void createCoupon(Coupon coupon) 
				throws CouponException, AlreadyExistException, DoesNotExistException, SQLException 
	{
			if (Checks.isCouponExistByName(coupon.getTitle()))
			{
				throw new AlreadyExistException
				("Coupon Already Exist");
			}
			
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{
			String sql = 
					"INSERT INTO Coupon (TITLE, START_DATE, END_DATE, AMOUNT,"+ " TYPE, MESSAGE, PRICE, IMAGE) VALUES(?,?,?,?,?,?,?,?)";
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
			}
				
		catch (SQLException e) 
			{
			throw new CouponException("Error in connection to DATA BASE", e);
			} 
				
	}
		//V	
	//***************************************************
	//This function gets Coupon Object and REMOVE from DB
	//***************************************************		
	public void removeCoupon(Coupon coupon) 
			throws CouponException, SQLException, DoesNotExistException
	{
			if (!Checks.isCouponExistByName(coupon.getTitle()))
				{
				throw new DoesNotExistException
				("Coupon Does Not Exist");
				}
			
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{
			String sql = 
						"DELETE FROM COUPON WHERE TITLE = ?";
				PreparedStatement stat = con.prepareStatement(sql);
				stat.setString(1, coupon.getTitle());
				stat.executeUpdate();			
			
			} 
		catch (SQLException e) 
			{
			throw new CouponException
			("Error in connection to DATA BASE", e);
			}	
	}
	//V	
	//****************************************************************
	//This function gets Coupon Object and Update all parameters in DB
	//****************************************************************
	public void updateCoupon(Coupon coupon) 
				throws CouponException, DoesNotExistException, SQLException 
	{
		if (!Checks.isCouponExistByName(coupon.getTitle()))
			{
			throw new DoesNotExistException
			("Customer Does Not Exist");
			}	
		
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{
				String sql = 
						"UPDATE Coupon SET TITLE=?, START_DATE=?, END_DATE=?, AMOUNT=?,"+ " TYPE=?, MESSAGE=?, PRICE=?, IMAGE=? WHERE TITLE=?";
			
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
		 catch (SQLException e) 
			{
			throw new CouponException
			("Error in connection to DATA BASE", e);
			}
		
	}
	//V
	//**********************************************************************************
	//This function gets Coupon Object and Update his amount in DB after purchase coupon
	//**********************************************************************************
	public void updateAmountOfCoupon(long couponId) 
			throws CouponException, AlreadyExistException, DoesNotExistException, SQLException 
	{	
		if (!Checks.isCouponExistById(couponId))
			{
			throw new DoesNotExistException("Coupon Does not exist");
			}
			
		int amount = getCoupon(couponId).getAmount();
			if(!(amount>0))
			{
			throw new DoesNotExistException("Coupon was sold out");
			}
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{
				String sql =
						"UPDATE Coupon SET AMOUNT= AMOUNT +(-1) WHERE ID=?";
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setLong(1, couponId);
					stmt.executeUpdate();
			}
		catch (SQLException e) 
			{
			throw new CouponException
			("Error in connection to DATA BASE", e);
			}
	}
	//V	
	//**********************************************************************************
	//This function gets Coupon Title and Returns coupon Object that contains this title		
	//**********************************************************************************
	public Coupon getCouponByTitle (String TITLE) 
			throws CouponException, DoesNotExistException, SQLException
	{
		Coupon coupon = new Coupon();
		ResultSet rs;
		
			if (!Checks.isCouponExistByName(TITLE))
				{
				throw new DoesNotExistException("Coupon Does Not Exist");
				}
		
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{
				String sql = 
						"SELECT * FROM Coupon WHERE TITLE=?";
				PreparedStatement stat = con.prepareStatement (sql);
				stat.setString(1, TITLE);
				rs = stat.executeQuery();
			
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
			} 
		catch (SQLException e) 
			{
			throw new CouponException
			("Error in connection to DATA BASE", e);
			}
		
	return coupon;
		
	}
		//V	
	//****************************************************************************
	//This function gets Coupon Id and Returns coupon Object that contains this Id
	//****************************************************************************
	public Coupon getCoupon(long couponId) 
				throws CouponException, AlreadyExistException, DoesNotExistException, SQLException 
		{
		Coupon coupon = null;
		Long ID;
		String TITLE,MESSAGE,IMAGE;
		LocalDate START_DATE,END_DATE;
		int AMOUNT;
		CouponType TYPE;
		double PRICE;
		
		if (!Checks.isCouponExistById(couponId))
			{
			throw new DoesNotExistException
			("The Coupon does not found");
			}
		
		try (Connection con=ConnectionPool.getInstance().getConnection()) 
				{
				String sql = 
						"SELECT * FROM Coupon WHERE ID=?";
					PreparedStatement stat = con.prepareStatement (sql);
					stat.setLong(1, couponId);
					ResultSet rs = stat.executeQuery();
			
				rs.next();
					ID = rs.getLong(1);
					TITLE = rs.getString("TITLE");
					START_DATE = rs.getDate("START_DATE").toLocalDate();
					END_DATE = rs.getDate("END_DATE").toLocalDate();
					AMOUNT = rs.getInt("AMOUNT");
					TYPE = CouponType.valueOf(rs.getString("TYPE"));
					MESSAGE = rs.getString("MESSAGE");
					PRICE = rs.getDouble("PRICE");
					IMAGE = rs.getString("IMAGE");
			
				coupon = new Coupon(ID, TITLE, START_DATE, END_DATE, AMOUNT, TYPE, MESSAGE, PRICE, IMAGE);
			} 
		catch (SQLException e) 
			{
			throw new CouponException
			("Error in connection to DATA BASE", e);
			}		
	return coupon;
		
	}
		//V
	//*****************************************
	//This function Returns All coupons from DB
	//*****************************************
		public Collection<Coupon> getAllCoupons() 
				throws CouponException, DoesNotExistException, SQLException 
		{
		
		Collection<Coupon> AllCoupons= new ArrayList<>();
		
		Long ID;
		String TITLE,MESSAGE,IMAGE;
		LocalDate START_DATE,END_DATE;
		int AMOUNT;
		CouponType TYPE;
		double PRICE;
		Coupon coupon = null;
		ResultSet rs = null;
			
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{
				String sql = 
						"SELECT * FROM Coupon";
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
				}
			
			} 
		catch (SQLException e) 
			{
			throw new CouponException
			("Error in connection to DATA BASE", e);
			} 
			
		finally 
			{
			rs.close();
			} 
	return AllCoupons;
	} 
		
		
			
		//V
	//*****************************************
	//This function Returns All coupons BY Type
	//*****************************************
	public Collection<Coupon> getCouponByType(CouponType coupontype) 
			throws CouponException, DoesNotExistException 
	{
		Collection <Coupon> Coupons= new ArrayList<>();
		Long ID;
		String TITLE,MESSAGE,IMAGE;
		LocalDate START_DATE,END_DATE;
		int AMOUNT;
		CouponType TYPE;
		double PRICE;
		
		if(Coupons.isEmpty())
		{
			throw new DoesNotExistException("We are sorry! \nWe don't have this Coupon Type. \nPlease try different type");
		}
		
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{
				String sql = 
						"SELECT * FROM Coupon WHERE TYPE=?";
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
			} 
		catch (SQLException e) 
			{
			throw new CouponException
			("Error in connection to DATA BASE", e);
			}
			
	return Coupons;
		}
	//V
	//*******************************************************
	//This function Returns All customer that have the coupon
	//*******************************************************
	public Collection<Customer> getCustomersWhoHaveCoupon(long couponId) 
			throws CouponException, AlreadyExistException, DoesNotExistException, SQLException 
		{
		ResultSet rs;
		Collection <Customer> custCoupons = new ArrayList<>();
		
		if (!Checks.isCouponExistById(couponId))
		 	{
			 throw new DoesNotExistException("Coupon Does not exist");
		 	}
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{
				String sql = 
						"SELECT * FROM Customer "
						+ "JOIN Customer_Coupon "
						+ "ON Customer.ID = Customer_Coupon.CustId "
						+ "WHERE Customer_Coupon.CouponId = ?";
					PreparedStatement stat = con.prepareStatement(sql);
					stat.setLong(1,couponId);
					rs = stat.executeQuery();
			 
					while(rs.next())
						{
						Customer customer = new Customer(
						 rs.getLong("ID"),
						 rs.getString("CUST_NAME"),
						 rs.getString("PASSWORD"));
						custCoupons.add(customer);
						}
			} 
		catch (SQLException e) 
			{
			throw new CouponException
			("Error in connection to DATA BASE", e);
			}
						
	return custCoupons; 
			
	}
		
		
	
	
}
