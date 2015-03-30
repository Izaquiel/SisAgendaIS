/**
 *
 */
package beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pojos.Pessoa;
import servicos.PessoaService;

/**
 * @author Izaquiel Cruz
 *
 */
@Named
@RequestScoped
public class PessoaControle{
	
	@Inject
	private PessoaService service;

	private Pessoa p = new Pessoa();

	public PessoaControle() {
	}

	public void salvar() {
		service.salvar(p);
	}
	
	public void remover(){
		service.remover(p.getId());
	}
	
	public Pessoa getPorId(Long id){
		return service.getPorId(id);
	}
	
	public Pessoa listarPorNome(String nome){
		return service.listarPorNome(nome);
	}
	
	public List<Pessoa> listarTodos(){
		return service.listarTodos();
	}

	public Pessoa getP() {
		return p;
	}

	public void setP(Pessoa p) {
		this.p = p;
	}

}
