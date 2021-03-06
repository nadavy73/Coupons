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
	
	//*************************************************
	//This function gets Coupon Object and INSERT to DB
	//*************************************************
	@Override
	public long createCoupon(Coupon coupon) 
				throws AlreadyExistException, SQLException, CouponException 
	{
		
		if (Checks.isCouponExistByName(coupon.getTitle()))
				{
				throw new AlreadyExistException
				("Coupon Already Exist");
				}
			
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{
				String sql = 
					"INSERT INTO Coupon (TITLE, START_DATE, END_DATE, AMOUNT,TYPE, MESSAGE, PRICE, IMAGE) VALUES(?,?,?,?,?,?,?,?);";
				PreparedStatement stat = con.prepareStatement (sql, Statement.RETURN_GENERATED_KEYS);
				stat.setString(1, coupon.getTitle());
				stat.setDate(2, Date.valueOf(coupon.getStartDate()));
				stat.setDate(3, Date.valueOf(coupon.getEndDate()));
				stat.setInt(4, coupon.getAmount());
				stat.setString(5, coupon.getType().name());
				stat.setString(6, coupon.getMessage());
				stat.setDouble(7, coupon.getPrice());
				stat.setString(8, coupon.getImage());
				stat.executeUpdate();
				
				long generatedId = -1;
				ResultSet rs = stat.getGeneratedKeys();
				
				if (rs.next()) {
					generatedId = rs.getLong(1);
					coupon.setId(generatedId);
				} else {
					throw new CouponException("Error creating Coupon");
				}
				return generatedId;	
			}
				catch (SQLException e) 
				{
				throw new SQLException
				("Error in connection to DATA BASE", e);
				} 
		
		
	}		

	
	//***************************************************
	//This function gets Coupon Object and REMOVE from DB
	//***************************************************
	@Override
	public void removeCoupon(Coupon coupon) 
			throws DoesNotExistException, SQLException
	{
		if (!Checks.isCouponExistById(coupon.getId()))
		{
		throw new DoesNotExistException("Coupon Does Not Exist");
		}
			
		try(Connection con=ConnectionPool.getInstance().getConnection())
		
			{
			String sql = 
						"DELETE FROM COUPON WHERE ID = ?";
				PreparedStatement stat = con.prepareStatement(sql);
				stat.setLong(1, coupon.getId());
				stat.executeUpdate();			
			
			} 
		catch (SQLException e) 
			{
			throw new SQLException
			("Error in connection to DATA BASE", e.getMessage());
			}	
	}
	
	
	//****************************************************************
	//This function gets Coupon Object and Update all parameters in DB
	//****************************************************************
	@Override
	public void updateCoupon(Coupon coupon) 
				throws DoesNotExistException
	{
		if (!Checks.isCouponExistById(coupon.getId()))
		{
		throw new DoesNotExistException ("Coupon Does Not Exist");
		}	
		
		else {
			try(Connection con=ConnectionPool.getInstance().getConnection())
			{
				String sql = 
						"UPDATE Coupon SET TITLE=?, START_DATE=?, END_DATE=?, AMOUNT=?, TYPE=?, MESSAGE=?, PRICE=?, IMAGE=? WHERE ID=?;";
			
				PreparedStatement stat = con.prepareStatement(sql);
				stat.setString(1, coupon.getTitle());
				stat.setDate(2, Date.valueOf(coupon.getStartDate()));
				stat.setDate(3, Date.valueOf(coupon.getEndDate()));
				stat.setInt(4, coupon.getAmount());
				stat.setString(5, coupon.getType().toString());
				stat.setString(6, coupon.getMessage());
				stat.setDouble(7, coupon.getPrice());
				stat.setString(8, coupon.getImage());
				stat.setLong(9, coupon.getId());
				stat.executeUpdate();
			}
		 catch (SQLException e) 
			{
			 new SQLException 
			 ("Error in connection to DATA BASE", e);			
			 }
		}
	}
	
	
	//**********************************************************************************
	//This function gets Coupon Object and Update his amount in DB after purchase coupon
	//**********************************************************************************
	@Override
	public void updateAmountOfCoupon(long couponId) 
			throws DoesNotExistException, SQLException 
	{	
		if (!Checks.isCouponExistById(couponId))
			{
			throw new DoesNotExistException("Coupon Does not exist");
			}
			
			int amount = getCouponById(couponId).getAmount();
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
				throw new SQLException
				("Error in connection to DATA BASE", e);
				}
	}
	
	
	//**********************************************************************************
	//This function gets Coupon Title and Returns coupon Object that contains this title		
	//**********************************************************************************
	@Override
	public Coupon getCouponByTitle (String CouponTitle) 
			throws DoesNotExistException, SQLException
	{
		Coupon coupon = new Coupon();
		ResultSet rs;
		
			if (!Checks.isCouponExistByName(CouponTitle))
				{
				throw new DoesNotExistException("Coupon Does Not Exist");
				}
		
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{
				String sql = 
						"SELECT * FROM Coupon WHERE TITLE=?";
				PreparedStatement stat = con.prepareStatement (sql);
				stat.setString(1, CouponTitle);
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
			throw new SQLException
			("Error in connection to DATA BASE", e);
			}
		
	return coupon;
		
	}
	
	
	//****************************************************************************
	//This function gets Coupon Id and Returns coupon Object that contains this Id
	//****************************************************************************
	@Override
	public Coupon getCouponById(long couponId) 
				throws DoesNotExistException, SQLException 
		{
		Coupon coupon = null;
		Long ID;
		String TITLE,MESSAGE,IMAGE;
		LocalDate START_DATE,END_DATE;
		int AMOUNT;
		CouponType TYPE;
		double PRICE;
		Connection con= null;	
		ResultSet rs=null;

		if (!Checks.isCouponExistById(couponId))
			{
			throw new DoesNotExistException
			("Coupon Does Not Exist");
			}
		
//		try (Connection con=ConnectionPool.getInstance().getConnection()) 
//				{
			try
			{
			con=ConnectionPool.getInstance().getConnection();
					
			
			String sql = 
						"SELECT * FROM Coupon WHERE ID=?";
					PreparedStatement stat = con.prepareStatement (sql);
					stat.setLong(1, couponId);
					rs = stat.executeQuery();
			
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
			throw new SQLException
			("Error in connection to DATA BASE", e);
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
			return coupon;
		
	}
	
	
	//*****************************************
	//This function Returns All coupons from DB
	//*****************************************
	@Override
	public Collection<Coupon> getAllCoupons() 
				throws DoesNotExistException, SQLException
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
			throw new SQLException
			("Error in connection to DATA BASE", e);
			} 
			
		finally 
			{
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			} 
	return AllCoupons;
	} 
		
				
	//*****************************************
	//This function Returns All coupons BY Type
	//*****************************************
	@Override
	public Collection<Coupon> getCouponByType(CouponType coupontype) 
			throws DoesNotExistException, SQLException 
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
						"SELECT ID FROM Coupon WHERE TYPE=?";
					PreparedStatement stat = con.prepareStatement (sql);
					stat.setString(1, coupontype.toString());
					ResultSet rs = stat.executeQuery();
				
				while(rs.next()) 
				{
//					ID = rs.getLong(1);
//					TITLE = rs.getString(2);
//					START_DATE = rs.getDate(3).toLocalDate();
//					END_DATE = rs.getDate(4).toLocalDate();
//					AMOUNT = rs.getInt(5);
//					TYPE = CouponType.valueOf(rs.getString(6));
//					MESSAGE = rs.getString(7);
//					PRICE = rs.getDouble(8);
//					IMAGE = rs.getString(9);
				
//					Coupon coupon = new Coupon(ID, TITLE, START_DATE, END_DATE, AMOUNT, TYPE, MESSAGE, PRICE, IMAGE);
					Coupons.add(getCouponById(rs.getLong(1)));
				}
			} 
			catch (SQLException e) {
				throw new SQLException ("Error in connection to DATA BASE", e);
			}
			
	return Coupons;
		}
	
	//*******************************************************
	//This function Returns All customer that have the coupon
	//*******************************************************
	@Override
	public Collection<Customer> getCustomersWhoHaveCoupon(long couponId) 
			throws DoesNotExistException, SQLException
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
			throw new SQLException
			("Error in connection to DATA BASE", e);
			}
						
	return custCoupons; 
			
	}
	
		
}
