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

	private Pessoa pessoa = new Pessoa();

	public PessoaControle() {
	}

	public void salvar() {
		service.salvar(pessoa);
	}
	
	public void remover(){
		service.remover(pessoa.getId());
	}
	
	public Pessoa getPorId(){
		return service.getPorId(pessoa.getId());
	}
	
	public Pessoa listarPorNome(){
		return service.listarPorNome(pessoa.getNome());
	}
	
	public List<Pessoa> listarTodos(){
		return service.listarTodos();
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
