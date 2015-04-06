/**
 *
 */
package persistencia;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
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
	public List<T> listarTodos(Class<?> c) {
		Criteria cri = getSession().createCriteria(c);
		List<T> list = cri.list();
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public T getComFiltro(Map params, Class<?> c) {
		
		Criteria cri = getSession().createCriteria(c);

		Set<String> chaves = params.keySet();

		for (String chave : chaves) {
			cri.add(Restrictions.eq(chave, params.get(chave)));
		}

		T result = (T) cri.uniqueResult();
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<T> listarComFiltro(Map params, Class<?> c) {
		
		Criteria cri = getSession().createCriteria(c);
		
		Set<String> chaves = params.keySet();
		
		for (String chave : chaves) {
			cri.add(Restrictions.ilike(chave, (String) params.get(chave), MatchMode.ANYWHERE));
		}
		
		return cri.list();
	}
	
}
