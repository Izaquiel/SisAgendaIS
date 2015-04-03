/**
 *
 */
package beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import enums.Estado;
import filter.PessoaFilter;
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
	
	public Pessoa getPorNome(){
		PessoaFilter filter = new PessoaFilter();
		filter.setNome(pessoa.getNome());
		return service.GetComFiltro(filter);
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
	
	public List<SelectItem> Estados(){
		List<SelectItem> lista = new ArrayList<>();
		for (Estado es : Estado.values()) {
			lista.add(new SelectItem(es, es.name()));
		}
		return lista;
	}
}
