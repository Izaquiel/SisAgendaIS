/**
 *
 */
package beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import filter.CompromissoFilter;
import pojos.Compromisso;
import servicos.CompromissoService;

/**
 * @author Izaquiel Cruz
 *
 */
@Named
@RequestScoped
public class CompromissoControle {
	
	@Inject
	private CompromissoService service;
	
	private Compromisso compromisso = new Compromisso();
	
	public CompromissoControle() {
	}
	
	public void salvar() {
		service.salvar(compromisso);
		compromisso = new Compromisso();
	}
	
	public void remover(){
		service.remover(compromisso.getId());
	}
	
	public Compromisso getPorId(){
		CompromissoFilter filter = new CompromissoFilter();
		filter.setId(compromisso.getId());
		return service.listarPorFiltro(filter);
	}
	
	public Compromisso listarPorDescricao(){
		CompromissoFilter filter = new CompromissoFilter();
		filter.setDescricao(compromisso.getDescricao());
		return service.listarPorFiltro(filter);
	}
	
	public List<Compromisso> listarTodos(){
		return service.listarTodos();
	}

	public Compromisso getCompromisso() {
		return compromisso;
	}

	public void setCompromisso(Compromisso compromisso) {
		this.compromisso = compromisso;
	}
	
}
