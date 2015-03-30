/**
 *
 */
package beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import pojos.Pessoa;

/**
 * @author Izaquiel Cruz
 *
 */
@Named
@SessionScoped
public class LoginControle implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pessoa p = new Pessoa();
	
	public LoginControle() {
	}

	public Pessoa getP() {
		return p;
	}

	public void setP(Pessoa p) {
		this.p = p;
	}
	

}
