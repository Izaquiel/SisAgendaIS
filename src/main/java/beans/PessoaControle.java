/**
 *
 */
package beans;

import java.io.Serializable;
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
public class PessoaControle implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private PessoaService service;

	private Pessoa p = new Pessoa();

	public PessoaControle() {
	}

	public void salvar() {
		service.salvar(p);
	}
	
	public void atualizar(){
		service.atualizar(p);
	}
	
	public void remover(){
		service.remover(p.getId());
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
