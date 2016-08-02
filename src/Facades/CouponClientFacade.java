package Facades;

import java.sql.SQLException;

import Exceptions.CouponException;
import Exceptions.DoesNotExistException;
import Exceptions.FacadeException;
import Exceptions.LoginException;

public interface CouponClientFacade 
{
	public CouponClientFacade login(String name, String password, ClientType clientType) throws FacadeException, LoginException, CouponException, SQLException, DoesNotExistException;
}
