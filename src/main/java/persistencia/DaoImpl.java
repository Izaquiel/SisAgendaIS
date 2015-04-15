/**
 *
 */
package persistencia;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.jdbc.Work;
import org.hibernate.jpa.internal.EntityManagerImpl;

/**
 * @author Izaquiel Cruz
 *
 */

public class DaoImpl<T> implements Dao<T> {

	@Inject
	private EntityManager em;
	
	private Session session;

	public Session getSession() {
		if (session == null) {
			session = em.unwrap(Session.class);
		}
		return session;
	}
	
	@Produces
	@RequestScoped
	public Connection getConnection() {
        try {
            EntityManagerImpl factory = (EntityManagerImpl) em;
            System.out.println(factory);
            SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) factory.getSession().getSessionFactory();
            System.out.println(sessionFactoryImpl);
            System.out.println(sessionFactoryImpl.getConnectionProvider().getConnection());
            return sessionFactoryImpl.getConnectionProvider().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("nulo como retorno");
        return null;
    }

	@Override
	public void salvar(T entity) {
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
	}

	@Override
	public void atualizar(T entity) {
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();

	}

	@Override
	public void remover(Long id) {
		em.getTransaction().begin();
		em.remove(em.merge(id));
		em.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listarTodos(Class<?> c) {
		Criteria cri = getSession().createCriteria(c);
		List<T> list = cri.list();
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public T getComFiltro(Map params, Class<?> c) {
		try{
		Criteria cri = getSession().createCriteria(c);
		
		Set<String> chaves = params.keySet();

		for (String chave : chaves) {
			cri.add(Restrictions.eq(chave, params.get(chave)));
		}

		T result = (T) cri.uniqueResult();
		return result;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<T> listarComFiltro(Map params, Class<?> c) {

		Criteria cri = getSession().createCriteria(c);

		Set<String> chaves = params.keySet();

		for (String chave : chaves) {
			cri.add(Restrictions.ilike(chave, (String) params.get(chave),
					MatchMode.ANYWHERE));
		}

		return cri.list();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> listarPorStatus(Map params, Class<?> c) {

		Criteria cri = getSession().createCriteria(c);

		Set<String> chaves = params.keySet();

		for (String chave : chaves) {
			cri.add(Restrictions.eq(chave, params.get(chave)));
		}

		return cri.list();
	}
}
