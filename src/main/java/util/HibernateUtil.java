/**
 *
 */
package util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.jpa.internal.EntityManagerImpl;

/**
 * @author Izaquiel Cruz
 *
 */
public class HibernateUtil {

	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("SisAgendaPU");

	public HibernateUtil() {
	}	
	
	@Produces
	@RequestScoped
	public EntityManager getEntityManager(){
		return factory.createEntityManager();
	}
	
	@Produces
	@RequestScoped
	public Connection getConnection() {
        try {
            EntityManagerImpl factory = (EntityManagerImpl) getEntityManager();
            SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) factory.getSession().getSessionFactory();
            return sessionFactoryImpl.getConnectionProvider().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
	
}
