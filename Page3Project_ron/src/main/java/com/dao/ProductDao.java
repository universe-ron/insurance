package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.model.Product;
import com.util.HibernateUtil;

public class ProductDao implements IProductDao
{

	
	@Override
	public int findPid(String pname, String generations, String type) 
	{
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		Product resultBean = null;
		int pid=0;
		try {
			session.beginTransaction();
			
			String hql = "from Product where pname=:pname and generations=:generations and type=:type";
			Query<Product> query = session.createQuery(hql, Product.class);
			query.setParameter("pname", pname);
			query.setParameter("generations", generations);
			query.setParameter("type", type);
			
			resultBean = query.uniqueResult();
			if(resultBean!=null) {
				pid=resultBean.getPid();
				System.out.println("success！ Pid："+pid);
			}else {
				System.out.println("no result");
			}

			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return pid;
	}
	@Override
	public int findPrice(int pid) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		Product resultBean = null;
		int price=0;
		try {
			session.beginTransaction();
			
			String hql = "from Product where pid=:pid";
			Query<Product> query = session.createQuery(hql, Product.class);
			query.setParameter("pid", pid);

			resultBean = query.uniqueResult();
			if(resultBean!=null) {
				price=resultBean.getPrice();
				System.out.println("success！ price："+price);
			}else {
				System.out.println("no result");
			}

			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return price;
	}
	
	@Override
    public void addProduct(Product product) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product getProductById(int pid) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Product product = session.get(Product.class, pid);
            transaction.commit();
            return product;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Product> getAllProducts() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Product";
            Query<Product> query = session.createQuery(hql, Product.class);
            List<Product> products = query.getResultList();
            transaction.commit();
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
 


    @Override
    public void updateProduct(Product product) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(product);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(int pid) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Product product = session.get(Product.class, pid);
            if (product != null) {
                session.delete(product);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
