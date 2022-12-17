package main;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.procedure.ProcedureCall;

import jakarta.persistence.StoredProcedureQuery;

public class StoredProcedureCall {

	public void run(){
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

		Session session = sessionFactory.openSession();
		
		try {
			
			StoredProcedureQuery query = session.createStoredProcedureQuery("GEA_SEL_PRUEBA");
			
			query.execute();
			
		
		} catch (Exception e) {

			e.printStackTrace();
			
		} finally {
			
			session.close();
			
			sessionFactory.close();
		}
	}

}
