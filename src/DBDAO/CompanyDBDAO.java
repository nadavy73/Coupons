package DBDAO;


import java.sql.*;
import java.util.*;
import Checks.Checks;
import DAO.CompanyDAO;
import Exceptions.*;
import JavaBeans.*;

//***********************************************
//This class implement All Company Dao's function
//***********************************************

public class CompanyDBDAO implements CompanyDAO {
	
		
	
	//**************************************************
	//This function gets Company Object and insert to DB
	//**************************************************
	public void createCompany(Company company) 
			throws AlreadyExistException, SQLException
		{
			if (Checks.isCompanyExistByName(company.getCompName()))
				{
				throw new AlreadyExistException
				("Company Already Exist");
				}
		
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{
		
			String sql = 
					"INSERT INTO Company(COMP_NAME,PASSWORD,EMAIL) VALUES (?,?,?)";
				PreparedStatement stat = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				stat.setString(1, company.getCompName());
				stat.setString(2, company.getPassWord());
				stat.setString(3, company.geteMail());
				stat.executeUpdate();
			}
		catch (SQLException e) 
			{
			throw new SQLException
			("Error in connection to DATA BASE", e);
			}
}
	
	
	
	//********************************************************
	//This function gets Company Object and and remove 
	//from DB
	//********************************************************
	public void removeCompany(Company company) 
			throws DoesNotExistException, SQLException
		{
			if (!Checks.isCompanyExistByName(company.getCompName()))
			{
			throw new DoesNotExistException
			("Company Does Not Exist");	
			}
			
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{
			
			String sql = 
					"DELETE FROM COMPANY WHERE COMP_NAME = ?";
				PreparedStatement stat = con.prepareStatement(sql);
				stat.setString(1, company.getCompName());					
				stat.executeUpdate();
			}
		catch (SQLException e) 
			{
			throw new SQLException
			("Error in connection to DATA BASE", e);
			}
		}
	
	
	
	//******************************************************
	//This function gets Company Name and remove from DB
	//******************************************************
	public void removeCompanyByName(String compName) 
			throws DoesNotExistException, SQLException 
	
	{
		if (!Checks.isCompanyExistByName(compName))
		{
			throw new DoesNotExistException
			("Customer Does Not Exist");
		}
		
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{
			String sql = 
					"DELETE FROM COMPANY WHERE COMP_NAME = ?";
				PreparedStatement stat= con.prepareStatement(sql);
				stat = con.prepareStatement(sql);
				stat.setString(1, compName);
				stat.executeUpdate();
			
			}
		catch (SQLException e) 
			{
			throw new SQLException
			("Error in connection to DATA BASE", e);
			}
	}
	
	
	//*****************************************************************
	//This function gets Company Name and replace with 
	//new Company name.
	//*****************************************************************
	public void updateCompanyByName(String OldName, String NewName) 
			throws DoesNotExistException, SQLException 
	{
		if (!Checks.isCompanyExistByName(OldName))
		{
			throw new DoesNotExistException
			("Company Does Not Exist");
			
		}
		
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{
			// Create a statement for retrieving and updating data
			String sql = 
					"UPDATE Company SET COMP_NAME = ? WHERE COMP_NAME = ?";
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
	
	
	//****************************************************************
	//This function gets Company Object and update the 
	//Company details
	//****************************************************************
	public void updateCompany(Company company) 
			throws DoesNotExistException, SQLException 
	{
		if (!Checks.isCompanyExistByName(company.getCompName()))
		{
			throw new DoesNotExistException
			("Company Does Not Exist");
			
		}
		
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{
			String sql = 
					"UPDATE Company SET COMP_NAME=?, PASSWORD=?, EMAIL=? WHERE COMP_NAME=?";
				
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, company.getCompName());
				stmt.setString(2, company.getPassWord());
				stmt.setString(3, company.geteMail());
				stmt.setString(4, company.getCompName());
				stmt.executeUpdate();
			
			} 
		catch (SQLException e) 
			{
			throw new SQLException
			("Error in connection to DATA BASE", e);
			} 
			
	}
	
	
	//********************************************************************
	//This function gets Company ID and return 
	//Company all company details
	//********************************************************************
	@Override
	public Company getCompanyById(long ID) 
			throws DoesNotExistException, SQLException 
	{
		if (!Checks.isCompanyExistById(ID))
			{
			throw new DoesNotExistException
			("The Company does not found");
			}
		
		Company company=null;
		String compName, eMail,Password;
		Collection<Coupon> coupons = null;
		ResultSet rs=null;
		
		try (Connection con= ConnectionPool.getInstance().getConnection()) 
			{

			String sql = 
					"SELECT * FROM Company WHERE ID=?";
				PreparedStatement stat = con.prepareStatement(sql);
				stat.setLong(1, ID);
				rs = stat.executeQuery();
			
			rs.next();
						
			compName = rs.getString("COMP_NAME");
			Password = rs.getString("PASSWORD");
			eMail = rs.getString("EMAIL");
			coupons= getCoupons(ID);
			company = new Company(ID, compName, Password, eMail, coupons);
			} 
		catch (SQLException e) 
			{
			throw new SQLException
			("Error in connection to DATA BASE", e);
			} 
	
		finally 
			{
			rs.close();
			}

	return company;

	}
	
	
	//*******************************************************************************
	//This function gets Company Name (String) and return 
	//Company all company details
	//*******************************************************************************
	@Override
	public Company getCompanyByName(String compName) 
			throws DoesNotExistException, SQLException
	{
		if (!Checks.isCompanyExistByName(compName))
			{
			throw new DoesNotExistException
			("The company does not exist in db");
			}
		
		String eMail,Password;
		long Id;
		Collection <Coupon> coupons = null;
		Company company=null;
		ResultSet rs=null;
	
		try (Connection con= ConnectionPool.getInstance().getConnection()) 
			{
				String sql = 
						"SELECT * FROM Company WHERE COMP_NAME=?";
					PreparedStatement stat = con.prepareStatement(sql);
					stat.setString(1, compName);
					rs = stat.executeQuery();
		
			rs.next();
			Id = rs.getLong(1);
			compName = rs.getString(2);
			Password = rs.getString(3);
			eMail = rs.getString(4);
			coupons = getCoupons(Id);
			company = new Company(Id, compName, Password, eMail, coupons);
		
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
			} 
		catch (SQLException e) 
			{
			e.printStackTrace();
			}	
		}	
		return company;
}
	
	
	//*********************************************
	//This function return ALL Companies in our DB.
	//*********************************************
	@Override
	public Collection<Company> getAllCompanies() 
			throws DoesNotExistException,SQLException 
	{
		Collection<Company> companies = new HashSet<>();
		Company company= null;
		Collection <Coupon> coupons= null;
		ResultSet rs = null;
		String compName = null, eMail = null, password = null;
		Long ID = null;
		
		try (Connection con= ConnectionPool.getInstance().getConnection()) 
			{
			String sql = 
					"SELECT * FROM company";
				PreparedStatement stat = con.prepareStatement(sql);
				rs = stat.executeQuery();

			while (rs.next()) 
				{
				ID = rs.getLong("ID");
				compName = rs.getString("COMP_NAME");
				password = rs.getString("PASSWORD");
				eMail = rs.getString("EMAIL");
				coupons= getCoupons(ID);
				company = new Company(ID, compName, password, eMail, coupons);
				companies.add(company);
				}
			} 
		catch (SQLException e) 
			{
			throw new SQLException
			("Error in connection to DATA BASE", e);
			}

		finally 
			{
			rs.close();
			}

	return companies;
	}
	
	
	//*********************************************
	//This function return ALL Companies in our DB.
	//*********************************************
	@Override
	public Collection<Coupon> getCoupons(long compID) 
			throws DoesNotExistException, SQLException
	{	
		if (!Checks.isCompanyExistById(compID))
			{
			throw new DoesNotExistException
			("Company Does Not Exist");	
			}
		
		Collection<Coupon> coupons = new HashSet<>();
		CouponDBDAO couponDB = new CouponDBDAO();
		ResultSet rs=null;

		try(Connection con=ConnectionPool.getInstance().getConnection()) 
			{
			String sql = 
					"SELECT * FROM Coupon "
					+ "JOIN Company_Coupon "
					+ "ON Coupon.ID = Company_Coupon.COUPON_ID "
					+ "WHERE Company_Coupon.COMP_ID = ?";
			
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setLong(1, compID);
			rs = stat.executeQuery();
			
			while (rs.next())
				{
				coupons.add(couponDB.getCoupon(rs.getLong("COUPON_ID")));
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

	return coupons; 
	} 
	
	
	//****************************************
	//Login function 
	//****************************************
	@Override
	public boolean login(String compName, String password) 
			throws SQLException {

		ResultSet rs = null;
		boolean hasRows = false;

		try(Connection con=ConnectionPool.getInstance().getConnection())
			{	
				String sql = 
					"SELECT Comp_name, password FROM company WHERE " + "Comp_name= '" + compName + "'" + " AND "
					+ "password= '" + password + "'";

				PreparedStatement loginStat = con.prepareStatement(sql);
				rs = loginStat.executeQuery();

				rs.next();
				if (rs.getRow() != 0) 
					{
				hasRows = true;
					} 
				else 
					{
				hasRows = false;
					}
			} 
		catch (SQLException e) 
			{
			throw new SQLException
			("Error in connection to DATA BASE", e);
			}

		finally 
			{
			rs.close();
			}
	return hasRows;
		
	}
	
	
	//*********************************************
	//This function Adds Coupon to the selected company 
	//by typing both Company Id & Coupon ID 
	//*********************************************
	@Override
	public void addCompanyCouponById(long compId, long couponId) 
			throws DoesNotExistException, AlreadyExistException, SQLException 
	{
		if (!Checks.isCompanyExistById(compId))
		{
			throw new DoesNotExistException
			("Company Does Not Exist");
		}
		
		if (!Checks.isCouponExistById(couponId))
		{
		throw new DoesNotExistException
		("Coupon Does Not Exist"); 
		}
		
		if (Checks.isCompanyPurchased(compId, couponId))
		{
			throw new AlreadyExistException
			("Coupon was already purchased for this Company");
		}
		
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{	
				String sql =
					"INSERT INTO Company_Coupon (COMP_ID, COUPON_ID) values (?,?);";
								
				PreparedStatement stat = con.prepareStatement(sql);
				stat.setLong(1, compId);
				stat.setLong(2, couponId);
				
				stat.executeUpdate();
			}
		catch (SQLException e) 
			{
			throw new SQLException
			("Error in connection to DATA BASE", e);
			}
	System.out.println("Coupon no." + couponId+  " was added to Company "+  "no."+ compId);

	}
	
	
	//*********************************************
	//This function Adds Coupon to the selected company 
	//by typing both Company & Coupon 
	//*********************************************
	@Override
	public void addCompanyCoupon(Company company, Coupon coupon) 
			throws DoesNotExistException, AlreadyExistException, SQLException 
	{
		if (!Checks.isCompanyExistById(company.getId()))
		{
			throw new DoesNotExistException
			("Company Does Not Exist");
		}
		
		if (!Checks.isCouponExistByName(coupon.getTitle()))
		{
		throw new DoesNotExistException
		("Coupon Does Not Exist"); 
		}
		
		if (Checks.isCompanyPurchased(company, coupon))
		{
			throw new AlreadyExistException
			("Coupon was already purchased for this Company");
		}
		
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{	
				String sql =
					"INSERT INTO Company_Coupon (COMP_ID, COUPON_ID) values (?,?);";
								
				PreparedStatement stat = con.prepareStatement(sql);
				stat.setLong(1, company.getId());
				stat.setLong(2, coupon.getId());
				
				stat.executeUpdate();
			}
		catch (SQLException e) 
			{
			throw new SQLException
			("Error in connection to DATA BASE", e);
			}
	System.out.println("Coupon " + coupon.getTitle()+  " was added to Company "+  "no."+ company.getId());

	}
	
	
	//*********************************************
	//This function removes Coupon from the selected 
	//company by typing both Company Id & Coupon ID 
	//*********************************************
	@Override
	public void removeCompanyCouponsById(long compId, long couponId) 
			throws DoesNotExistException, SQLException  
		{
				
		if (!Checks.isCompanyPurchased(compId, couponId))
			{
			throw new DoesNotExistException
			("This Company doesn't have Coupon ID "+ couponId);
			}
		
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{
				String sql =
					"DELETE FROM Company_Coupon WHERE COMP_ID=? AND COUPON_ID = ?";
				PreparedStatement stmt = con.prepareStatement(sql);
		
				stmt.setLong(1, compId);
				stmt.setLong(2, couponId);
				stmt.executeUpdate();
			
			} 
		catch (SQLException e) 
			{
				throw new SQLException
				("Error in connection to DATA BASE", e);
			}
			
		System.out.println("Coupon no." + couponId+ "  was removed from Company no." +compId);
	}

	
	//*********************************************
	//This function removes Coupon from the selected 
	//company by typing both Company & Coupon 
	//*********************************************
		@Override
	public void removeCompanyCouponsById(long couponId) 
			throws DoesNotExistException, SQLException 
	{
		if (!Checks.isCouponExistById(couponId))
			{
			throw new DoesNotExistException
			("This Company does not exist");
			}
		
		try(Connection con=ConnectionPool.getInstance().getConnection())
			{	
				String sql =
					"DELETE FROM Company_Coupon WHERE COUPON_ID = ?;";
				PreparedStatement stmt = con.prepareStatement(sql);
		
				stmt.setLong(1, couponId);
				stmt.executeUpdate();
			
			} 
		catch (SQLException e) 
			{
			throw new SQLException
			("Error in connection to DATA BASE", e);
			}
			
		System.out.println("Company Coupon no." + couponId+ "  was removed");
			
			
				
			


	
	}
}