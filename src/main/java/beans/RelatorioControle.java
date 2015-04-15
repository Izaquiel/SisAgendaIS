/**
 *
 */
package beans;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
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

/**
 * @author Izaquiel Cruz
 *
 */
@Named
@RequestScoped
public class RelatorioControle {
	
	JasperPrint jasperPrint;
	
	@Inject
	private Connection con;
	
	public RelatorioControle() {
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void init(Map map)throws JRException{
//		String fonte = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/classes/relatorio/relatorioBD.jrxml");
		InputStream fonte = RelatorioControle.class.getResourceAsStream("/relatorio/relatorioBD.jrxml");
		JasperReport path = JasperCompileManager.compileReport(fonte);
		jasperPrint = JasperFillManager.fillReport(path, map, con);
		System.out.println(con);
		
	}
	
	@SuppressWarnings("rawtypes")
	public void printPDF(Map map) throws JRException, IOException{
		init(map);
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse(); 
		response.addHeader("Content-disposition", "attachment; filename=relatorio.pdf");
		ServletOutputStream outputStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
		FacesContext.getCurrentInstance().responseComplete();
		
	}
	
}
