package DBDAO;

import java.sql.*;
import java.time.ZoneId;
import java.util.*;
import DAO.CompanyDAO;
import Exceptions.AlreadyExistException;
import Exceptions.CouponException;
import Exceptions.DoesNotExistException;
import JavaBeans.*;

//***********************************************
//This class implement All Company Dao's function
//***********************************************

public class CompanyDBDAO implements CompanyDAO {

	//**************************************************
	//This function gets Company Object and insert to DB
	//**************************************************

	public void createCompany(Company company) throws CouponException, AlreadyExistException {
		
		Connection con = null;
		ResultSet rs = null;

		try {
			Company c = getCompanyByName(company.getCompName());
			if (c != null)
			{

				throw new AlreadyExistException("Coupon ID already exist in DB: " + company.getCompName());
			
			}
			
			con = ConnectionPool.getInstance().getConnection();
			String sql = "INSERT INTO Company(COMP_NAME,PASSWORD,EMAIL) VALUES (?,?,?)";
			PreparedStatement stat = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stat.setString(1, company.getCompName());
			stat.setString(2, company.getPassWord());
			stat.setString(3, company.geteMail());

			stat.executeUpdate();

			rs = stat.getGeneratedKeys();
			rs.next();
			company.setId(rs.getLong(1));

		} catch (SQLException e) {

			// Translate exception

			throw new CouponException("Error in connection to DATA BASE", e);

		} finally {

	
			// release connection to pool
			
			ConnectionPool.getInstance().free(con);
		}
}
	
	//********************************************************
		//This function gets Company Object and and remove from DB
		//********************************************************
		public void removeCompany(Company company) throws SQLException, CouponException, DoesNotExistException
		{
			Connection con = null;
			PreparedStatement stat = null;
			try {
			Company c = getCompanyByName(company.getCompName());
			
			if (c == null)
			{
				throw new DoesNotExistException("COMPANY Does Not Exist in DB");
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

	
	
	
	//******************************************************
	//This function gets Company Name and remove from DB
	//******************************************************
	
	public void removeCompany(String compName) throws CouponException, DoesNotExistException, SQLException 
	
	{
		Connection con = null;
		PreparedStatement stat = null;
		try {
			
			con = ConnectionPool.getInstance().getConnection();

			String sql = "DELETE FROM COMPANY WHERE COMP_NAME = ?";
			stat = con.prepareStatement(sql);
			stat.setString(1, compName);
			int numberOfRowsDeleted = stat.executeUpdate();
			
			if (numberOfRowsDeleted == 0) {
				throw new DoesNotExistException("Company dosen't Exist");
			}

		} finally {

			
			// release connection to pool
			
			ConnectionPool.getInstance().free(con);
		}

	}
	
	//*****************************************************************
	//This function gets Company Name and replace with new Company name.
	//*****************************************************************
	public void updateCompanyName(String OldName, String NewName) throws CouponException, SQLException {

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
	
	//****************************************************************
	//This function gets Company Object and update the Company details
	//****************************************************************
	
	public void updateCompany(Company company) throws DoesNotExistException, CouponException {
		Connection con = null;
		
		Company c = getCompanyByName(company.getCompName());
		
		//Check if Company is exist in DB
		if (c == null)
		{
			throw new DoesNotExistException("The Company Does not exist in DB");
		}
		
		try {
			con = ConnectionPool.getInstance().getConnection();
			
			
			{	
				String sql = "UPDATE Company SET COMP_NAME=?, PASSWORD=?, EMAIL=? WHERE COMP_NAME=?";
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, company.getCompName());
				stmt.setString(2, company.getPassWord());
				stmt.setString(3, company.geteMail());
				stmt.setString(4, c.getCompName());
				stmt.executeUpdate();
			}
			} catch (SQLException e) {
				 
				e.printStackTrace();
			} finally {

				
				// release connection to pool
				
				ConnectionPool.getInstance().free(con);
			}

		}
	
	//********************************************************************
	//This function gets Company ID and return Company all company details
	//********************************************************************
	
@Override
	public Company getCompany(long ID) throws CouponException {

		String compName, eMail,Password;
		Connection con = null;
		Company company=null;

		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "SELECT * FROM Company WHERE ID=?";
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setLong(1, ID);
			ResultSet rs = stat.executeQuery();
			rs.next();
			ID = rs.getInt(1);
			compName = rs.getString(2);
			Password = rs.getString(3);
			eMail = rs.getString(4);
			company = new Company(ID, compName, Password, eMail);
		} catch (SQLException e) {
 
			e.printStackTrace();
		} finally {

			
			// release connection to pool
			
			ConnectionPool.getInstance().free(con);
		}

		return company;

	}

	//*******************************************************************************
	//This function gets Company Name (String) and return Company all company details
	//*******************************************************************************

public Company getCompanyByName(String NAME) throws CouponException {

	String compName, eMail,Password;
	long Id;
	Connection con = null;
	Company company=null;

	try {
		con = ConnectionPool.getInstance().getConnection();
		String sql = "SELECT * FROM Company WHERE COMP_NAME=?";
		PreparedStatement stat = con.prepareStatement(sql);
		stat.setString(1, NAME);
		ResultSet rs = stat.executeQuery();
		if (!rs.next())
		{
			return null;
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

public boolean isCompanyExist(String Name) throws CouponException {

	Connection con = null;

	try {
		con = ConnectionPool.getInstance().getConnection();
		String sql = "SELECT * FROM Company WHERE COMP_NAME=" + Name;
		Statement stat = con.createStatement();
		ResultSet rs = stat.executeQuery(sql);
		
		// If there is even one line in the response - it means the company exists
		return (rs.next());
	} catch (SQLException e) {

		e.printStackTrace();
	} finally {

		
		// release connection to pool
		
		ConnectionPool.getInstance().free(con);
	}

	return false;

}

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
//				System.out.println("Company Details: \nID: " + ID + "\nComapny Name: " + compName
//						+ "\nPassword: *********" + "\nEmail: " + eMail);
//				System.out.println("******************");
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
	public Collection<Coupon> getCoupons(long compID) throws CouponException {

		Connection con = null;
		ResultSet rs = null;
		Collection<Coupon> coupons = new ArrayList<>();
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "SELECT * FROM Coupons " + 
						 "JOIN Company_Coupon " +
						 "ON Coupons.ID = Company_Coupon.COUPON_ID "+
						 "WHERE Company_Coupon.COMP_ID = " + compID;

			PreparedStatement stat = con.prepareStatement(sql);

			rs = stat.executeQuery();

			while (rs.next()) {
				// Generating Coupon
				Coupon coupon = new Coupon(rs.getLong("ID"), rs.getString("TITLE"),
						// converting sql.Date to LocalDate
						rs.getDate("START_DATE").toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
						// converting sql.Date to LocalDate
						rs.getDate("END_DATE").toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
						rs.getInt("AMOUNT"), CouponType.valueOf(rs.getString("TYPE")), rs.getString("MESSAGE"),
						rs.getDouble("PRICE"), rs.getString("IMAGE"));

				System.out.println("******************");
				coupons.add(coupon);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			try {
				rs.close();
				// release connection to pool
				
				ConnectionPool.getInstance().free(con);
			}

			catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return coupons;
	}
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

	@Override
	public void removeCompanyCoupon(Coupon coupon, Company company) throws CouponException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeCompanyCoupon(long couponId, long compId) throws CouponException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCompanyCoupon(Company company, Coupon coupon) throws CouponException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCompanyCoupon(Company company, long couponId) throws CouponException, SQLException {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void updateCompanyName(long Id, Company company) throws CouponException, SQLException {
//		// TODO Auto-generated method stub
//		
//	}

}