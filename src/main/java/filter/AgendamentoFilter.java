/**
 *
 */
package filter;

import enums.Status;

/**
 * @author Izaquiel Cruz
 *
 */
public class AgendamentoFilter {

	private Long id;
	private Status status;

	public AgendamentoFilter() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
}
