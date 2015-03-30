/**
 *
 */
package servicos;

import java.util.List;

import javax.inject.Inject;

import persistencia.Dao;
import pojos.Pessoa;

/**
 * @author Izaquiel Cruz
 *
 */
public class PessoaService{
	
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

	public Pessoa listarPorNome(String value) {
		return dao.listar("nome", value, Pessoa.class);
	}


	public void remover(Long id) {
		dao.remover(id);
	}

	public void atualizar(Pessoa p) {
		dao.atualizar(p);
		
	}

	public Pessoa getPorId(Long id) {
		return dao.getPorId(id, Pessoa.class);
	}
	

}
