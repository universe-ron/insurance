package com.controller;

import com.model.Insurance;

public interface IPage3Dao 
{
	int findPid(String pname,String generations,String type);
	int findPrice(int pid);
	Insurance findInsurance(int pid,String name);
	
	
	
	
}
