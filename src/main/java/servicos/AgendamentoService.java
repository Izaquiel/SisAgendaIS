/**
 *
 */
package servicos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import persistencia.Dao;
import pojos.Agendamento;
import pojos.Pessoa;
import filter.AgendamentoFilter;

/**
 * @author Izaquiel Cruz
 *
 */
public class AgendamentoService {

	@Inject
	private Dao<Agendamento> dao;

	public AgendamentoService() {
	}

	public void salvar(Agendamento a) {
		dao.salvar(a);
	}

	public List<Agendamento> listarTodos() {
		return dao.listarTodos(Agendamento.class);
	}

	public Agendamento getComFiltro(AgendamentoFilter filtro) {

		Map<String, Object> map = new HashMap<>();

		if (filtro.getId() != null) {
			map.put("id", filtro.getId());
			return dao.getComFiltro(map, Pessoa.class);
		}

		return null;
	}
	
	public List<Agendamento> listarPorFiltro(AgendamentoFilter filtro){
		
		Map<String, Object> map = new HashMap<>();
		
		if (filtro.getStatus() != null) {
			map.put("status", filtro.getStatus());
			return dao.listarComFiltro(map, Pessoa.class);
		}
		
		return null;
	}

	public void remover(Long id) {
		dao.remover(id);
	}

	public void atualizar(Agendamento a) {
		dao.atualizar(a);

	}

}
