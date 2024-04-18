package com.dao;

import java.util.List;

import com.model.Insurance;

public interface IInsuranceDao 
{
	Insurance getInsurance(int pid,String iname);
	List<Insurance> getAllInsurances();
}
