package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.model.Totalbean;
import com.model.UserInsurance;

public interface IUserInsuranceDao 
{

	boolean insertUserInsurance(UserInsurance ui);
	List<UserInsurance> getAllUserInsurances();
	List<Totalbean> getUserInsuranceByUid(int uid) throws SQLException;
	List<Totalbean> getAllUserInsurance();
}
