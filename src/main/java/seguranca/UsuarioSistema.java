/**
 *
 */
package seguranca;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import pojos.Pessoa;

/**
 * @author Izaquiel Cruz
 *
 */
public class UsuarioSistema extends User{

	private static final long serialVersionUID = 1L;
	
	private Pessoa pessoa;
	
	public UsuarioSistema(Pessoa pessoa, Collection<? extends GrantedAuthority> authorities){
		super(pessoa.getUsername(), pessoa.getPassword(), authorities);
		this.pessoa = pessoa;
	}
	
	public Pessoa getPessoa(){
		return pessoa;
	}
}
