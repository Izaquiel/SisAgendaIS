/**
 *
 */
package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pojos.Pessoa;
import servicos.PessoaService;
import enums.Estado;
import filter.PessoaFilter;

/**
 * @author Izaquiel Cruz
 *
 */
@Named
@ViewScoped
public class PessoaControle{
	
	@Inject
	private PessoaService service;

	private Pessoa pessoa = new Pessoa();
	private List<Pessoa> pessoas = new ArrayList<>();
	
	public PessoaControle() {
	}

	public void salvar() {
		pessoa.setEnable(true);
		service.salvar(pessoa);
		pessoa = new Pessoa();
	}
	
	public void remover(){
		service.remover(pessoa.getId());
	}
	
	public Pessoa getPorId(){
		PessoaFilter filter = new PessoaFilter();
		filter.setId(pessoa.getId());
		return service.GetComFiltro(filter);
		
	}
	
	public void listaPorFiltro(){
		PessoaFilter filter = new PessoaFilter();
		filter.setUsername(pessoa.getUsername());
		filter.setNome(pessoa.getNome());
		pessoas = service.listaComFiltro(filter);
		pessoa = new Pessoa();

	}
	
	public void listarTodos(){
		pessoas = service.listarTodos();
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<SelectItem> Estados(){
		List<SelectItem> lista = new ArrayList<>();
		for (Estado es : Estado.values()) {
			lista.add(new SelectItem(es, es.name()));
		}
		return lista;
	}
}
