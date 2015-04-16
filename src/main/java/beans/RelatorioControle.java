/**
 *
 */
package beans;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import enums.Status;

/**
 * @author Izaquiel Cruz
 *
 */
@Named
@RequestScoped
public class RelatorioControle {
	
	private JasperPrint jasperPrint;
	
	@Inject
	private Connection connection;
	
	private Status statusSelecionado;
	private Date inicio;
	private Date fim;
	
	public RelatorioControle() {
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void init(Map map)throws JRException{
//		String fonte = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/classes/relatorio/relatorioBD.jrxml");
		InputStream fonte = RelatorioControle.class.getResourceAsStream("/relatorio/relatorioBD.jrxml");
		JasperReport path = JasperCompileManager.compileReport(fonte);
		jasperPrint = JasperFillManager.fillReport(path, map, connection);
		
	}
	
	public void printPDF() throws JRException, IOException{
		Map<String, Object> map = new HashMap<>();
		map.put("status", statusSelecionado.toString());
		map.put("inicio", inicio);
		map.put("fim", fim);
		init(map);
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse(); 
		response.addHeader("Content-disposition", "attachment; filename=relatorio.pdf");
		ServletOutputStream outputStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
		FacesContext.getCurrentInstance().responseComplete();
		
	}
	
	public void visualizaPdf() throws JRException{
		Map<String, Object> map = new HashMap<>();
		map.put("status", statusSelecionado.toString());
		map.put("inicio", inicio);
		map.put("fim", fim);
		init(map);
		JasperViewer.viewReport(jasperPrint, false);
	}

	public Status getStatusSelecionado() {
		return statusSelecionado;
	}

	public void setStatusSelecionado(Status statusSelecionado) {
		this.statusSelecionado = statusSelecionado;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}
	
	public List<SelectItem> Status(){
		List<SelectItem> lista = new ArrayList<>();
		for (Status es : Status.values()) {
			lista.add(new SelectItem(es, es.name()));
		}
		return lista;
	}
}
