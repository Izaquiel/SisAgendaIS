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
	private Session session;

	public Session getSession() {
		if (session == null) {
			session = em.unwrap(Session.class);
		}
		return session;
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
	public T getPorId(Long id, Class<?> c) {
		Criteria cri = getSession().createCriteria(c);
		cri.add(Restrictions.eq("id", id));
		T result = (T) cri.uniqueResult();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listarTodos(Class<?> c) {
		Criteria cri = getSession().createCriteria(c);
		List<T> list = cri.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T listar(String campo, Object value, Class<?> c) {
		Criteria cri = getSession().createCriteria(c);
		cri.add(Restrictions.eq(campo, value));
		T result = (T) cri.uniqueResult();
		return result;
	}

}
