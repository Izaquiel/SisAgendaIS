/**
 *
 */
package persistencia;

import java.util.List;

/**
 * @author Izaquiel Cruz
 *
 */
public interface Dao<T> {
	
	public void salvar(T entity);
	
	public List<T> listarTodos(Class<?> c);
	
	public T listar(String campo, String value, Class<?> c);

	public void remover(Long id);
	
	public void atualizar(T entity);
	
	public T getPorId(Long id, Class<?> c);
}
