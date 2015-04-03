/**
 *
 */
package persistencia;

import java.util.List;
import java.util.Map;

/**
 * @author Izaquiel Cruz
 *
 */
public interface Dao<T> {
	
	public void salvar(T entity);
	
	public List<T> listarTodos(Class<?> c);
	
	@SuppressWarnings("rawtypes")
	public T getComFiltro(Map params,  Class<?> c);
	
	@SuppressWarnings("rawtypes")
	public List<T> listarComFiltro(Map params,  Class<?> c);
	
	public void remover(Long id);
	
	public void atualizar(T entity);
	
}
