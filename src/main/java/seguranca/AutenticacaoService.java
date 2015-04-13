package seguranca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import persistencia.Dao;
import persistencia.DaoImpl;
import pojos.Autorizacao;
import pojos.Pessoa;

/**
 * @author Izaquiel Cruz
 *
 */
public class AutenticacaoService implements UserDetailsService {

	@SuppressWarnings("unchecked")
	private Dao<Pessoa> dao = CDIServiceLocator.getBean(DaoImpl.class);

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		Pessoa pessoa = null;

		Map<String, Object> map = new HashMap<>();
		map.put("username", username);

		pessoa = dao.getComFiltro(map, Pessoa.class);

		UsuarioSistema usuario = null;

		if (pessoa != null) {
			usuario = new UsuarioSistema(pessoa, getAutorizacoes(pessoa));
		}

		return usuario;
	}

	private Collection<? extends GrantedAuthority> getAutorizacoes(Pessoa p) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (Autorizacao aut : p.getAutorizacoes()) {
			authorities.add(new SimpleGrantedAuthority(aut.getNome()));
		}

		return authorities;
	}
}
