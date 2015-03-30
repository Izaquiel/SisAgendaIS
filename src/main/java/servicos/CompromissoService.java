/**
 *
 */
package servicos;

import java.util.List;

import javax.inject.Inject;

import persistencia.Dao;
import pojos.Compromisso;

/**
 * @author Izaquiel Cruz
 *
 */
public class CompromissoService {

	@Inject
	private Dao<Compromisso> dao;
	
	public CompromissoService() {
	}
	
	public void salvar(Compromisso c) {
		dao.salvar(c);	
	}

	public List<Compromisso> listarTodos() {
		return dao.listarTodos(Compromisso.class);
	}

	public Compromisso listarPorDescricao(Object value) {
		return dao.listar("descricao", value, Compromisso.class);
	}

	public void remover(Long id) {
		dao.remover(id);
	}

	public void atualizar(Compromisso c) {
		dao.atualizar(c);
		
	}

	public Compromisso getPorId(Long id) {
		return dao.getPorId(id, Compromisso.class);
	}
}
