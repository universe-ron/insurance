package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.model.User;
import com.util.HibernateUtil;



public class UserDao implements IUserDao
{
	private Session session;

	public UserDao() {
	}

	public UserDao(Session session) {
		this.session = session;
	}
	
	@Override
	public User findUserById(int uid) 
	{
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		User resultBean = null;
		try {
			session.beginTransaction();
			
			String hql = "from [User] where uid=:uid";
			Query<User> query = session.createQuery(hql, User.class);
			query.setParameter("uid", uid);
		
			resultBean = query.uniqueResult();
			if(resultBean!=null) {
				System.out.println("success！ uid："+resultBean.getUid());
			}else {
				System.out.println("no user result");
			}

			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		
		HibernateUtil.closeSessionFactory();
		return resultBean;
	}
	
	@Override
	public int findUid(String account, String password) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		int uid = 1;
	    try (Session session = factory.getCurrentSession()) {
	        Transaction transaction = session.beginTransaction();

	        String sql = "SELECT * FROM [dbo].[User] WHERE [account] = :account AND [password] = :password";
	        
	        NativeQuery<User> query = session.createNativeQuery(sql, User.class);
	        query.setParameter("account", account);
	        query.setParameter("password", password);

	        User user = query.uniqueResult();
	        uid=user.getUid();
	        transaction.commit();
	        return uid;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return uid;
	}
	@Override
    public List<User> getAllUsers() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            
            // 使用原生 SQL 查询
            String sqlQuery = "SELECT * FROM [dbo].[User]";
            NativeQuery<User> query = session.createNativeQuery(sqlQuery, User.class);
            
            List<User> users = query.list();
            
            transaction.commit();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
  }


@Override
public int addUser(User user) {
	SessionFactory factory = HibernateUtil.getSessionFactory();
	try (Session session = factory.getCurrentSession()) {
		Transaction transaction = session.beginTransaction();
		int userId = (int) session.save(user);
		transaction.commit();
		return userId;
	} catch (Exception e) {
		e.printStackTrace();
		return -1;
	}
}

@Override
public User getUserById(int uid) {
    SessionFactory factory = HibernateUtil.getSessionFactory();
    try (Session session = factory.getCurrentSession()) {
        Transaction transaction = session.beginTransaction();

        // 使用原生 SQL 查询，将表名用反引号括起来
        String sql = "SELECT * FROM [dbo].[User] WHERE [uid] = :uid";
        
        NativeQuery<User> query = session.createNativeQuery(sql, User.class);
        query.setParameter("uid", uid);

        User user = query.uniqueResult();
        
        transaction.commit();
        return user;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

@Override
public void updateUser(User user) {
    SessionFactory factory = HibernateUtil.getSessionFactory();
    try (Session session = factory.getCurrentSession()) {
        Transaction transaction = session.beginTransaction();

        String sql = "UPDATE [User] SET account = :account, password = :password, mail = :mail, " +
                     "name = :name, birthDate = :birthDate, gender = :gender, address = :address, " +
                     "phone = :phone, phone2 = :phone2, permission = :permission WHERE uid = :uid";

        Query query = session.createNativeQuery(sql);
        query.setParameter("account", user.getAccount());
        query.setParameter("password", user.getPassword());
        query.setParameter("mail", user.getMail());
        query.setParameter("name", user.getName());
        query.setParameter("birthDate", user.getBirthDate());
        query.setParameter("gender", user.getGender());
        query.setParameter("address", user.getAddress());
        query.setParameter("phone", user.getPhone());
        query.setParameter("phone2", user.getPhone2());
        query.setParameter("permission", user.getPermission());
        query.setParameter("uid", user.getUid());

        int rowCount = query.executeUpdate();
        transaction.commit();
        System.out.println("Rows affected: " + rowCount);
    } catch (Exception e) {
        e.printStackTrace();
    }
}





@Override
public void deleteUser(int uid) {
    SessionFactory factory = HibernateUtil.getSessionFactory();
    try (Session session = factory.getCurrentSession()) {
        Transaction transaction = session.beginTransaction();
        // 构建 SQL 查询语句
        String sql = "DELETE FROM [dbo].[User] WHERE uid = :uid";
        Query query = session.createNativeQuery(sql);
        // 设置参数
        query.setParameter("uid", uid);
        // 执行 SQL 语句
        int rowsAffected = query.executeUpdate();
        transaction.commit();
        System.out.println("Rows affected: " + rowsAffected);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
