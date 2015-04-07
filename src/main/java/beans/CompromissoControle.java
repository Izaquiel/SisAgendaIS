/**
 *
 */
package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pojos.Compromisso;
import servicos.CompromissoService;
import filter.CompromissoFilter;

/**
 * @author Izaquiel Cruz
 *
 */
@Named
@ViewScoped
public class CompromissoControle {
	
	@Inject
	private CompromissoService service;
	
	private Compromisso compromisso = new Compromisso();
	private List<Compromisso> compromissos = new ArrayList<>();
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
		return service.getComFiltro(filter);
	}
	
	public void listarPorDescricao(){
		CompromissoFilter filter = new CompromissoFilter();
		filter.setDescricao(compromisso.getDescricao());
		compromissos = service.listarComFiltro(filter);
		compromisso = new Compromisso();
	}
	
	public List<Compromisso> listarTodos(){
		return compromissos = service.listarTodos();
	}

	public Compromisso getCompromisso() {
		return compromisso;
	}

	public void setCompromisso(Compromisso compromisso) {
		this.compromisso = compromisso;
	}

	public List<Compromisso> getCompromissos() {
		return compromissos;
	}

	public void setCompromissos(List<Compromisso> compromissos) {
		this.compromissos = compromissos;
	}
	
}
