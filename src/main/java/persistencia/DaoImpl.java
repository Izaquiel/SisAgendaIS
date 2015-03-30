/**
 *
 */
package persistencia;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * @author Izaquiel Cruz
 *
 */

public class DaoImpl<T> implements Dao<T> {

	@Inject
	private EntityManager em;

	public Session getSession() {
		Session session = em.unwrap(Session.class);
		return session;
	}

	@Override
	public void salvar(T entity) {
		em.getTransaction().begin();
		em.persist(entity);
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
	
	@Override
	public T getPorId(Long id, Class<?> c) {		
		Criteria cri = getSession().createCriteria(c);
		cri.add(Restrictions.eq("id", id));
		return (T) cri.uniqueResult();
	}

	@Override
	public List<T> listarTodos(Class<?> c) {
		Criteria cri = getSession().createCriteria(c);
		return cri.list();
	}

	@Override
	public T listar(String campo, String value, Class<?> c) {
		Criteria cri = getSession().createCriteria(c);
		cri.add(Restrictions.eq(campo, value));
		return (T) cri.uniqueResult();
	}

}
