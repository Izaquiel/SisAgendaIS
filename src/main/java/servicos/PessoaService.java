/**
 *
 */
package servicos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import persistencia.Dao;
import pojos.Pessoa;
import filter.PessoaFilter;

/**
 * @author Izaquiel Cruz
 *
 */
public class PessoaService {

	@Inject
	private Dao<Pessoa> dao;

	public PessoaService() {

	}

	public void salvar(Pessoa p) {
		dao.salvar(p);
	}

	public List<Pessoa> listarTodos() {
		return dao.listarTodos(Pessoa.class);
	}

	public Pessoa GetComFiltro(PessoaFilter filtro) {

		Map<String, Object> map = new HashMap<>();

		if (filtro.getNome() != null) {
			map.put("nome", filtro.getNome());
			return dao.getComFiltro(map, Pessoa.class);
		}

		if (filtro.getId() != null) {
			map.put("id", filtro.getId());
			return dao.getComFiltro(map, Pessoa.class);
		}

		if (filtro.getUsername() != null && filtro.getPassword() != null) {
			map.put("username", filtro.getUsername());
			map.put("password", filtro.getPassword());
			return dao.getComFiltro(map, Pessoa.class);
		}

		return null;
	}

	public List<Pessoa> listaComFiltro(PessoaFilter filtro) {

		Map<String, Object> map = new HashMap<>();

		if (filtro.getNome() != null && filtro.getNome() != "") {
			map.put("nome", filtro.getNome());
			return dao.listarComFiltro(map, Pessoa.class);
		}else
		
		if (filtro.getUsername() != null && filtro.getUsername() != "") {
			map.put("username", filtro.getUsername());
			return dao.listarComFiltro(map, Pessoa.class);
		}
		return null;
	}

	public void remover(Long id) {
		dao.remover(id);
	}

	public void atualizar(Pessoa p) {
		dao.atualizar(p);

	}

}
