/**
 *
 */
package beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import pojos.Agendamento;
import servicos.AgendamentoService;
import enums.Status;
import filter.AgendamentoFilter;

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
	private Date data = new Date();
	
	public AgendamentoControle() {
	}
	
	public void salvar() {
		agendamento.setDataCadastro(new Date());
		service.salvar(agendamento);
		agendamento = new Agendamento();
	}
	
	public void remover(){
		service.remover(agendamento.getId());
	}
	
	public Agendamento getPorId(){
		AgendamentoFilter filter = new AgendamentoFilter();
		filter.setId(agendamento.getId());
		return service.getComFiltro(filter);
	}
	
	public List<Agendamento> listarPorStatusCumprido(){
		AgendamentoFilter filter = new AgendamentoFilter();
		filter.setStatus(Status.CUMPRIDO);
		return service.listarPorFiltro(filter);
	}
	
	public List<Agendamento> listarPorStatusNaoCumprido(){
		AgendamentoFilter filter = new AgendamentoFilter();
		filter.setStatus(Status.NAO_CUMPRIDO);
		return service.listarPorFiltro(filter);
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public List<SelectItem> Status(){
		List<SelectItem> lista = new ArrayList<>();
		for (Status es : Status.values()) {
			lista.add(new SelectItem(es, es.name()));
		}
		return lista;
	}
}
