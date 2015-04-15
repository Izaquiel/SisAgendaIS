/**
 *
 */
package beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pojos.Agendamento;
import servicos.AgendamentoService;
import enums.Status;
import filter.AgendamentoFilter;

/**
 * @author Izaquiel Cruz
 *
 */
@Named
@ViewScoped
public class AgendamentoControle {

	@Inject
	private AgendamentoService service;
	RelatorioControle controle = new RelatorioControle();
	private Agendamento agendamento = new Agendamento();
	private Date data = new Date();
	private List<Agendamento> agendamentos = new ArrayList<>();
	
	public AgendamentoControle() {
	}
	
	public void salvar() {
		agendamento.setDataCadastro(new Date());
		service.salvar(agendamento);
		agendamento = new Agendamento();
	}
	
	public void remover(){
		service.remover(agendamento.getId());
	}
	
	public Agendamento getPorId(){
		AgendamentoFilter filter = new AgendamentoFilter();
		filter.setId(agendamento.getId());
		return service.getComFiltro(filter);
	}
	
	public void listarPorStatus(){
		AgendamentoFilter filter = new AgendamentoFilter();
		filter.setStatus(agendamento.getStatus());
		agendamentos = service.listarPorFiltro(filter);
		agendamento = new Agendamento();
	}
	
//	public void pdf() throws JRException{
//		Map<String, Object> map = new HashMap<>();
//		map.put("status", Status.NAO_CUMPRIDO.toString());
//		
//		RelatorioControle rel = new RelatorioControle();
//		try {
//			rel.printPDF(map);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
//		InputStream fonte = AgendamentoControle.class.getResourceAsStream("/relatorio/relatorioBD.jrxml");
//		JasperReport pathjrxml = JasperCompileManager.compileReport(fonte);
//		JasperPrint printReport = JasperFillManager.fillReport(pathjrxml, map);
//		JasperViewer.viewReport(printReport, false);
//		JasperExportManager.exportReportToPdfFile(printReport, "reportex.pdf");
//	}
	
	public void listarTodos(){
		agendamentos = service.listarTodos();
	}

	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}

	public List<SelectItem> Status(){
		List<SelectItem> lista = new ArrayList<>();
		for (Status es : Status.values()) {
			lista.add(new SelectItem(es, es.name()));
		}
		return lista;
	}
}
