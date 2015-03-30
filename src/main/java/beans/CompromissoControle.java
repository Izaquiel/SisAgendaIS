/**
 *
 */
package beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
	}
	
	public void remover(){
		service.remover(compromisso.getId());
	}
	
	public Compromisso getPorId(){
		return service.getPorId(compromisso.getId());
	}
	
	public Compromisso listarPorDescricao(){
		return service.listarPorDescricao(compromisso.getDescricao());
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
