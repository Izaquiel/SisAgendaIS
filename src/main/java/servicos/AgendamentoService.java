/**
 *
 */
package servicos;

import java.util.List;

import javax.inject.Inject;

import persistencia.Dao;
import pojos.Agendamento;
import enums.Status;

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

	public Agendamento listarPorStatus(Status status) {
		return dao.listar("status", status, Agendamento.class);
	}


	public void remover(Long id) {
		dao.remover(id);
	}

	public void atualizar(Agendamento a) {
		dao.atualizar(a);
		
	}

	public Agendamento getPorId(Long id) {
		return dao.getPorId(id, Agendamento.class);
	}
}
