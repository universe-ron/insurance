package com.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.model.Insurance;

import com.util.JdbcConnUtil;


public class Page3Dao implements IPage3Dao
{

	@Override
	public int findPid(String pname, String generations, String type) {
		JdbcConnUtil connUtil = new JdbcConnUtil();
		Connection conn;
		int pid=0;
		try 
		{
			conn = connUtil.createConn();
			if(conn!=null) 
			{	
		        String sqlstr = "SELECT pid FROM Product WHERE pname=? AND generations=? AND type=?";
				PreparedStatement state = conn.prepareStatement(sqlstr);
				state.setString(1, pname);
				state.setString(2, generations);
				state.setString(3, type);
				ResultSet result = state.executeQuery();
					
				
				while(result.next())
				{
					pid = result.getInt("pid");
				}
				state.close();
				result.close();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return pid;
	}

	@Override
	public Insurance findInsurance(int pid, String name) 
	{
		JdbcConnUtil connUtil = new JdbcConnUtil();
		Connection conn;
		Insurance insurance = new Insurance();
		try 
		{
			conn = connUtil.createConn();
			if(conn!=null) 
			{	
		        String sqlstr = "SELECT * FROM Insurance WHERE pid=? AND iname=?";
				PreparedStatement state = conn.prepareStatement(sqlstr);
				state.setInt(1, pid);
				state.setString(2, name);
				ResultSet result = state.executeQuery();

				while(result.next())
				{
					insurance.setIid(result.getInt("iid"));
					insurance.setPid(result.getInt("pid"));
					insurance.setIname(result.getString("iname"));
					insurance.setContent(result.getString("content"));
				}
				state.close();
				result.close();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return insurance;
	}

	@Override
	public int findPrice(int pid) 
	{
		JdbcConnUtil connUtil = new JdbcConnUtil();
		Connection conn;
		int price=0;
		try 
		{
			conn = connUtil.createConn();
			if(conn!=null) 
			{	
		        String sqlstr = "SELECT price FROM Product WHERE pid=?";
				PreparedStatement state = conn.prepareStatement(sqlstr);
				state.setInt(1, pid);
				
				ResultSet result = state.executeQuery();
					
				
				while(result.next())
				{
					price = result.getInt("price");
				}
				state.close();
				result.close();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return price;
	}

}
