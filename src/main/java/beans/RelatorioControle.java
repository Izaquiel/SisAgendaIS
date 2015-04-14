/**
 *
 */
package beans;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import pojos.Agendamento;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Izaquiel Cruz
 *
 */
@Named
@RequestScoped
public class RelatorioControle {
	
	JasperPrint jasperPrint;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void init(List<Agendamento> lista)throws JRException{
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(lista);
		String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("relatorio.jasper");
		jasperPrint = JasperFillManager.fillReport(path, new HashMap(), beanCollectionDataSource);
	}
	
	public void printPDF(List<Agendamento> lista) throws JRException, IOException{
		init(lista);
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse(); 
		response.addHeader("Content-disposition", "attachment; filename=report.pdf");
		ServletOutputStream outputStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
		FacesContext.getCurrentInstance().responseComplete();
	}
	
}
