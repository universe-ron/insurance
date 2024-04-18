package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.model.Totalbean;
import com.model.UserInsurance;
import com.util.HibernateUtil;
import com.util.JdbcConnUtil;

public class UserInsuranceDao implements IUserInsuranceDao
{

	@Override
	public boolean insertUserInsurance(UserInsurance ui) 
	{
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		UserInsurance resultBean = null;
		try {
			session.beginTransaction();
			
			session.save(ui);

			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}


	@Override
	 public List<UserInsurance> getAllUserInsurances() {
		 SessionFactory factory = HibernateUtil.getSessionFactory();
			
	        try (Session session = factory.getCurrentSession()) {
	            Transaction transaction = session.beginTransaction();
	            String hql = "FROM UserInsurance";
	            Query<UserInsurance> query = session.createQuery(hql, UserInsurance.class);
	            List<UserInsurance> userInsurances = query.getResultList();
	            transaction.commit();
	            return userInsurances;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	
	@Override
	public List<Totalbean> getUserInsuranceByUid(int uid) {
	    List<Totalbean> totalbeans = new ArrayList<>();
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	   JdbcConnUtil conn = new JdbcConnUtil();

	    try {
	        // 創建數據庫連接
	    	connection =conn.createConn();
	          // 準備 SQL 查詢
	        String sql = "SELECT " +
	                "p.pname, " +
	                "p.generations, " +
	                "p.type, " +
	                "p.price, " +
	                "i.iname, " +
	                "ui.idcode, " +
	                "ui.startDate, " +
	                "ui.endDate, " +
	                "ui.state, " +
	                "ui.id " +
	                "FROM " +
	                "UserInsurance ui " +
	                "JOIN " +
	                "Insurance i ON ui.iid = i.iid " +
	                "JOIN " +
	                "Product p ON i.pid = p.pid " +
	                "WHERE " +
	                "ui.uid = ?";

	        // 創建 PreparedStatement 對象並設置參數
	        preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setInt(1, uid);

	        // 執行查詢
	        resultSet = preparedStatement.executeQuery();

	        // 處理查詢結果
	        while (resultSet.next()) {
	            Totalbean totalbean = new Totalbean();
	            totalbean.setPname(resultSet.getString(1));
	            totalbean.setGenerations(resultSet.getString(2));
	            totalbean.setType(resultSet.getString(3));
	            totalbean.setPrice(resultSet.getInt(4));
	            totalbean.setIname(resultSet.getString(5));
	            totalbean.setIdcode(resultSet.getString(6));
	            totalbean.setStartDate(resultSet.getDate(7));
	            totalbean.setEndDate(resultSet.getDate(8));
	            totalbean.setState(resultSet.getInt(9));
	            totalbean.setId(resultSet.getInt(10));

	            totalbeans.add(totalbean);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // 關閉資源
	        try {
	            if (resultSet != null) {
	                resultSet.close();
	            }
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return totalbeans;
	}
	@Override
	public List<Totalbean> getAllUserInsurance() {
	    List<Totalbean> totalbeans = new ArrayList<>();
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	   JdbcConnUtil conn = new JdbcConnUtil();

	    try {
	        // 創建數據庫連接
	    	connection =conn.createConn();
	          // 準備 SQL 查詢
	        String sql = "SELECT " +
	                "p.pname, " +
	                "p.generations, " +
	                "p.type, " +
	                "p.price, " +
	                "i.iname, " +
	                "ui.idcode, " +
	                "ui.startDate, " +
	                "ui.endDate, " +
	                "ui.state, " +
	                "ui.id " +
	                "FROM " +
	                "UserInsurance ui " +
	                "JOIN " +
	                "Insurance i ON ui.iid = i.iid " +
	                "JOIN " +
	                "Product p ON i.pid = p.pid " ;
	               

	        // 創建 PreparedStatement 對象並設置參數
	      Statement statement = connection.createStatement();
	    

	        // 執行查詢
	        resultSet = statement.executeQuery(sql);

	        // 處理查詢結果
	        while (resultSet.next()) {
	            Totalbean totalbean = new Totalbean();
	            totalbean.setPname(resultSet.getString(1));
	            totalbean.setGenerations(resultSet.getString(2));
	            totalbean.setType(resultSet.getString(3));
	            totalbean.setPrice(resultSet.getInt(4));
	            totalbean.setIname(resultSet.getString(5));
	            totalbean.setIdcode(resultSet.getString(6));
	            totalbean.setStartDate(resultSet.getDate(7));
	            totalbean.setEndDate(resultSet.getDate(8));
	            totalbean.setState(resultSet.getInt(9));
	            totalbean.setId(resultSet.getInt(10));

	            totalbeans.add(totalbean);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // 關閉資源
	        try {
	            if (resultSet != null) {
	                resultSet.close();
	            }
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return totalbeans;
	}



	 public static void updateStateById(int id, int State) {
	        SessionFactory factory = HibernateUtil.getSessionFactory();
	        try (Session session = factory.getCurrentSession()) {
	            // 开始事务
	            Transaction transaction = session.beginTransaction();

	            // 通过ID加载实体对象
	            UserInsurance userInsurance = session.get(UserInsurance.class, id);
	            if (userInsurance != null) {
	                // 更新状态
	            	userInsurance.setState(State);

	                // 提交事务
	                transaction.commit();
	                 // 返回更新成功
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    
	    }
	
}
	

	

