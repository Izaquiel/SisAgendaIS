/**
 *
 */
package util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
		System.out.println("Pegando Entity Manager!");
		return factory.createEntityManager();
	}
	
}
