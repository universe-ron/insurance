package com.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.model.Insurance;
import com.util.HibernateUtil;


public class InsuranceDao implements IInsuranceDao
{
	private Session session;

	public InsuranceDao() {
	}

	public InsuranceDao(Session session) {
		this.session = session;
		
	}

	@Override
	public Insurance getInsurance(int pid, String iname) 
	{
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		Insurance resultBean = null;
		try {
			session.beginTransaction();
			
			String hql = "from Insurance where pid=:pid and iname=:iname";
			Query<Insurance> query = session.createQuery(hql, Insurance.class);
			query.setParameter("pid", pid);
			query.setParameter("iname", iname);
			
			resultBean = query.uniqueResult();
			if(resultBean!=null) {
				System.out.println("success！ iid："+resultBean.getIid());
			}else {
				System.out.println("no result");
			}

			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		
		return resultBean;
	}
	@Override
	
	
	public List<Insurance> getAllInsurances() {
	SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Insurance";
            Query<Insurance> query = session.createQuery(hql, Insurance.class);
            List<Insurance> insurances = query.getResultList();
            transaction.commit();
            return insurances;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
