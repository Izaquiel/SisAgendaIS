/**
 *
 */
package beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pojos.Agendamento;
import servicos.AgendamentoService;
import enums.Status;

/**
 * @author Izaquiel Cruz
 *
 */
@Named
@RequestScoped
public class AgendamentoControle {

	@Inject
	private AgendamentoService service;
	
	private Agendamento agendamento = new Agendamento();
	
	public AgendamentoControle() {
	}
	
	public void salvar() {
		service.salvar(agendamento);
	}
	
	public void remover(){
		service.remover(agendamento.getId());
	}
	
	public Agendamento getPorId(){
		return service.getPorId(agendamento.getId());
	}
	
	public Agendamento listarPorStatusCumprido(){
		return service.listarPorStatus(Status.CUMPRIDO);
	}
	
	public Agendamento listarPorStatusNaoCumprido(){
		return service.listarPorStatus(Status.NAO_CUMPRIDO);
	}
	
	public List<Agendamento> listarTodos(){
		return service.listarTodos();
	}

	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}
	
}
