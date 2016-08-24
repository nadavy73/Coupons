package DBDAO;



import java.sql.*;
import java.util.*;

import Checks.Checks;
import DAO.CompanyDAO;
import Exceptions.AlreadyExistException;
import Exceptions.CouponException;
import Exceptions.DoesNotExistException;
import JavaBeans.*;

//***********************************************
//This class implement All Company Dao's function
//***********************************************

public class CompanyDBDAO implements CompanyDAO {
	//V
	//**************************************************
	//This function gets Company Object and insert to DB
	//**************************************************
	public void createCompany(Company company) throws CouponException, AlreadyExistException, DoesNotExistException {
		
		Connection con = null;
		
		try {
			
		con = ConnectionPool.getInstance().getConnection();
		String sql = "INSERT INTO Company(COMP_NAME,PASSWORD,EMAIL) VALUES (?,?,?)";
		PreparedStatement stat = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		stat.setString(1, company.getCompName());
		stat.setString(2, company.getPassWord());
		stat.setString(3, company.geteMail());
			
		if (Checks.isCompanyExistByName(company.getCompName()))
			{
				throw new AlreadyExistException("COMPANY AlreadyExist");
			}
			else
			{
			stat.executeUpdate();
			}
			} catch (SQLException e) {
			
			throw new CouponException("Error in connection to DATA BASE", e);

		} finally {

	
			// release connection to pool
			
			ConnectionPool.getInstance().free(con);
		}
}
	//V
	//********************************************************
	//This function gets Company Object and and remove from DB
	//********************************************************
	public void removeCompany(Company company) throws SQLException, CouponException, DoesNotExistException
		{
			Connection con = null;
			PreparedStatement stat = null;
			try {
				if (!Checks.isCompanyExistByName(company.getCompName()))
				{
					throw new DoesNotExistException("Company Does Not Exist");
					
				}
			
			con = ConnectionPool.getInstance().getConnection();
			
			String sql = "DELETE FROM COMPANY WHERE COMP_NAME = ?";
			stat = con.prepareStatement(sql);
			stat.setString(1, company.getCompName());
			stat.executeUpdate();
			
			} finally {

				
				// release connection to pool
				
				ConnectionPool.getInstance().free(con);
			}
		}
	//V
	//******************************************************
	//This function gets Company Name and remove from DB
	//******************************************************
	public void removeCompanyByName(String compName) throws CouponException, DoesNotExistException, SQLException 
	
	{
		Connection con = null;
		if (!Checks.isCompanyExistByName(compName))
		{
			throw new DoesNotExistException("Customer Does Not Exist");
			
		}
		
		try {
			con = ConnectionPool.getInstance().getConnection();

			String sql = "DELETE FROM COMPANY WHERE COMP_NAME = ?";
			PreparedStatement stat= con.prepareStatement(sql);
			stat = con.prepareStatement(sql);
			
			
			stat.setString(1, compName);
			stat.executeUpdate();
			
		}
	    catch (Exception exc)
	    {
			
		} finally {

			
			// release connection to pool
			
			ConnectionPool.getInstance().free(con);
		}

	}
	//V
	//*****************************************************************
	//This function gets Company Name and replace with new Company name.
	//*****************************************************************
	public void updateCompanyByName(String OldName, String NewName) throws CouponException, SQLException, DoesNotExistException {

		if (!Checks.isCompanyExistByName(OldName))
		{
			throw new DoesNotExistException("Company Does Not Exist");
			
		}
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();

			// Create a statement for retrieving and updating data
			String sql = "UPDATE Company SET COMP_NAME = ? WHERE COMP_NAME = ?";
			PreparedStatement stmt = con.prepareStatement(sql);

			// Change the name values
			stmt.setString(1, NewName);
			stmt.setString(2, OldName);
			stmt.executeUpdate();

		}	finally {

				
				// release connection to pool
				
				ConnectionPool.getInstance().free(con);
			}

	}
	
	//V
	//****************************************************************
	//This function gets Company Object and update the Company details
	//****************************************************************
	public void updateCompany(Company company) throws DoesNotExistException, CouponException {
		Connection con = null;
		
		
		if (!Checks.isCompanyExistByName(company.getCompName()))
		{
			throw new DoesNotExistException("Company Does Not Exist");
			
		}
		
		try {
			con = ConnectionPool.getInstance().getConnection();
			{
				String sql = "UPDATE Company SET COMP_NAME=?, PASSWORD=?, EMAIL=? WHERE COMP_NAME=?";
				
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, company.getCompName());
				stmt.setString(2, company.getPassWord());
				stmt.setString(3, company.geteMail());
				stmt.setString(4, company.getCompName());
				stmt.executeUpdate();
			}
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			finally {

				
				// release connection to pool
				
				ConnectionPool.getInstance().free(con);
			}

		}
	//V
	//********************************************************************
	//This function gets Company ID and return Company all company details
	//********************************************************************
	@Override
	public Company getCompanyById(long ID) throws CouponException, DoesNotExistException {

		Connection con = null;
		String compName, eMail,Password;
		Company company=new Company();
		
		try {
			con = ConnectionPool.getInstance().getConnection();
			
			String sql = "SELECT * FROM Company WHERE ID=?";
			PreparedStatement stat = con.prepareStatement(sql);
			
			stat.setLong(1, ID);
			ResultSet rs = stat.executeQuery();
			
			
			if (!rs.next())
			{
				throw new DoesNotExistException("The Company does not found");
			}
			
			ID = rs.getInt(1);
			compName = rs.getString(2);
			Password = rs.getString(3);
			eMail = rs.getString(4);
			
			company = new Company(ID, compName, Password, eMail);
		} catch (SQLException e) {
 
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().free(con);
		}

		return company;

	}
	//V
	//*******************************************************************************
	//This function gets Company Name (String) and return Company all company details
	//*******************************************************************************
	public Company getCompanyByName(String compName) throws CouponException, DoesNotExistException {

	String eMail,Password;
	long Id;
	Connection con = null;
	Company company=null;

	try {
		con = ConnectionPool.getInstance().getConnection();
		String sql = "SELECT * FROM Company WHERE COMP_NAME=?";
		PreparedStatement stat = con.prepareStatement(sql);
		stat.setString(1, compName);
		ResultSet rs = stat.executeQuery();
		if (!rs.next())
		{
		throw new DoesNotExistException("The company does not exist in db");
		}
		Id = rs.getLong(1);
		compName = rs.getString(2);
		Password = rs.getString(3);
		eMail = rs.getString(4);
		
		company = new Company(Id, compName, Password, eMail);
		
	} catch (SQLException e) {

		e.printStackTrace();
	} finally {

		
		// release connection to pool
		
		ConnectionPool.getInstance().free(con);
	}

	return company;

}
	//*******************************************************************************************************
	//This function gets Company Name (String) and return boolian value if this company is exist in DB or NOT
	//*******************************************************************************************************
//	public boolean isCompanyExist(String compName) throws CouponException 
//	{
//
//		Connection con = null;
//		ResultSet rs=null;
//		try {
//			con = ConnectionPool.getInstance().getConnection();
//			String sql = "SELECT * FROM Company WHERE COMP_NAME=?;"; 
//			
//			PreparedStatement stat = con.prepareStatement(sql);
//			stat.setString(1, compName);			
//			rs = stat.executeQuery();
//			
//			// If there is even one line in the response - it means the coupon exists
//			
//			return (rs.next());
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//		} finally {
//
//			
//			// release connection to pool
//			
//			ConnectionPool.getInstance().free(con);
//		}
//
//		return false;
//
//	}
	
	//V
	//*********************************************
	//This function return ALL Companies in our DB.
	//*********************************************
	public Collection<Company> getAllCompanies() throws CouponException {
		Collection<Company> companies = new HashSet<>();
		Connection con = null;
		ResultSet rs = null;
		String compName = null, eMail = null, passWord = null;
		Long ID = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "SELECT * FROM company";
			PreparedStatement stat = con.prepareStatement(sql);

			rs = stat.executeQuery();

			while (rs.next()) {

				ID = rs.getLong("ID");
				compName = rs.getString("COMP_NAME");
				passWord = rs.getString("PASSWORD");
				eMail = rs.getString("EMAIL");
				Company company = new Company(ID, compName, passWord, eMail);
				companies.add(company);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

finally {

			
			// release connection to pool
			
			ConnectionPool.getInstance().free(con);
		}

		return companies;
	}
	//V
	//*********************************************
	//This function return ALL Companies in our DB.
	//*********************************************
	public Collection<Coupon> getCoupons(long compID) throws CouponException, DoesNotExistException, SQLException
	{	Coupon coupon = new Coupon();
		ResultSet rs=null;
		Connection con = null;
		Collection<Coupon> coupons = new ArrayList<>();
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "SELECT * FROM Coupon "
					+ "JOIN Company_Coupon "
					+ "ON Coupon.ID = Company_Coupon.COUPON_ID "
					+ "WHERE Company_Coupon.COMP_ID = ?";
			
			//statement - going to Company_Coupon table and getting the list of the coupons that relates to a company.
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setLong(1, compID);
			// Execute and get a resultSet
			rs = stat.executeQuery();
			
			if (!Checks.isCompanyExistById(compID))
			{
				throw new DoesNotExistException("Company Does Not Exist");	
			}
			if (!rs.next())
			{
				throw new DoesNotExistException("There are no Coupons for this Company");
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
				while (rs.next());	
			}
			 
		
		} catch (SQLException e) {
		e.printStackTrace();
		
		
		} 
		
	finally {
			ConnectionPool.getInstance().free(con);
			rs.close();
	}
		return coupons; 
	} 
			
			
	//V
	//**************
	//Login function 
	//**************
	public boolean login(String compName, String password) throws CouponException {

		Connection con = null;
		ResultSet rs = null;
		boolean hasRows = false;

		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "SELECT Comp_name, password FROM company WHERE " + "Comp_name= '" + compName + "'" + " AND "
					+ "password= '" + password + "'";

			// Select prepared statement
			PreparedStatement loginStat = con.prepareStatement(sql);
			rs = loginStat.executeQuery();

			rs.next();

			if (rs.getRow() != 0) {
				hasRows = true;
			} else {
				hasRows = false;
			}

			System.out.println(hasRows);

		} catch (SQLException e) {
			e.printStackTrace();

		}

		finally {
			ConnectionPool.getInstance().free(con);
		}

		return hasRows;
	}
	public void addCompanyCouponById(long compId, long couponId) throws CouponException, DoesNotExistException, AlreadyExistException {
		
		Connection con=null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			
			if (!Checks.isCouponExistById(couponId))
			{
				throw new DoesNotExistException
				("Coupon Does Not Exist"); 
			}
			
			if (!Checks.isCompanyExistById(compId))
			{
				throw new DoesNotExistException
				("Company Does Not Exist");
			}
			
			if (Checks.isPurchased(compId, couponId))
			{
				throw new AlreadyExistException
				("Coupon was already purchased for this Company");
			}
			
			else
			{
				String sql =
						"INSERT INTO Company_Coupon (COMP_ID, COUPON_ID) values (?,?);";
								
				PreparedStatement stat = con.prepareStatement(sql);
				stat.setLong(1, compId);
				stat.setLong(2, couponId);
				
				stat.executeUpdate();
				System.out.println("Coupon no." + couponId+  " was added to Company "+  "no."+ compId);
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
	
	public void removeCompanyCouponsById(long compId, long couponId) throws CouponException, DoesNotExistException, AlreadyExistException, SQLException {
		Connection con = null;
		
		if (!Checks.isCompanyPurchased(compId, couponId))
		{
			throw new DoesNotExistException("This Coupon is not purchased for this Company");
		}
		
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql =
					"DELETE FROM Company_Coupon WHERE COMP_ID=? AND COUPON_ID = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
		
			//Delete coupons
			stmt.setLong(1, compId);
			stmt.setLong(2, couponId);
			stmt.executeUpdate();
			
			} catch (SQLException e) 
			{
			throw new CouponException("CouponException", e);
			}
			
			System.out.println("Coupon no." + couponId+ "  was removed from Company no." +compId);
			
			 {
				ConnectionPool.getInstance().free(con);
			 }
		}

	public void removeCompanyCouponsById(long couponId) throws CouponException, DoesNotExistException, SQLException {
			Connection con = null;

				if (!Checks.isCouponExistById(couponId))
				{
					throw new DoesNotExistException("This Coupon is not purchased for this Customer");
				}
		
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql =
					"DELETE FROM Company_Coupon WHERE CouponId = ?;";
			PreparedStatement stmt = con.prepareStatement(sql);
		
			//Delete coupons
			stmt.setLong(1, couponId);
			stmt.executeUpdate();
			
			} catch (SQLException e) 
			{
			throw new CouponException("CouponException", e);
			}
			
			System.out.println("Company Coupon no." + couponId+ "  was removed");
			{
			ConnectionPool.getInstance().free(con);
			}


	
	}
}