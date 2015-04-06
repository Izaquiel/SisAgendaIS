/**
 *
 */
package servicos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import persistencia.Dao;
import pojos.Compromisso;
import filter.CompromissoFilter;

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

	public Compromisso getComFiltro(CompromissoFilter filtro) {

		Map<String, Object> map = new HashMap<>();

		if (filtro.getDescricao() != null && filtro.getDescricao() != "") {
			map.put("descricao", filtro.getDescricao());
			return dao.getComFiltro(map, Compromisso.class);
		}

		if (filtro.getId() != null) {
			map.put("id", filtro.getId());
			return dao.getComFiltro(map, Compromisso.class);
		}

		return null;
	}
	
	public List<Compromisso> listarComFiltro(CompromissoFilter filtro) {

		Map<String, Object> map = new HashMap<>();

		if (filtro.getDescricao() != null && filtro.getDescricao() != "") {
			map.put("descricao", filtro.getDescricao());
			return dao.listarComFiltro(map, Compromisso.class);
		}
		return null;
	}

	public void remover(Long id) {
		dao.remover(id);
	}

	public void atualizar(Compromisso c) {
		dao.atualizar(c);

	}

}
