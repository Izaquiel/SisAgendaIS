/**
 *
 */
package pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Izaquiel Cruz
 *
 */
@Entity
public class Autorizacao implements Serializable{

	private static final long serialVersionUID = 1L;

	private String nome;
	
	public Autorizacao() {
	}
	
	@Id
	@Column(name="nome", length=10)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
