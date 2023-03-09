package apiario.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import apiario.model.Trattamento;
import apiario.repository.TrattamentoRepository;
import apiario.service.TrattamentiService;
import apiario.utility.ExcelGeneratorTrattamenti;
import apiario.utility.PdfGeneratorTrattamenti;

@RestController
public class TrattamentiController {

    @Autowired
    TrattamentoRepository trattamentiRepo;

    @Autowired
    TrattamentiService trattamentiService;

    @PostMapping("/trattamento")
    public Trattamento inserisciTrattamenti(@RequestBody Trattamento t) {

	return trattamentiService.inserisciTrattamento(t);
    }

    @GetMapping("/trattamento")
    public List<Trattamento> listaTrattamenti() {
	return trattamentiRepo.findAll();
    }

    @PutMapping("/trattamento/{idTrattamento}")
    public Trattamento modificaTrattamento(@PathVariable("idTrattamento") Integer idTrattamento,
	    @RequestBody Trattamento t) {
	return trattamentiService.modificaTrattamenti(idTrattamento, t);
    }

    @DeleteMapping("/trattamento/{idTrattamento}")
    public void cancellaTrattamento(@PathVariable("idTrattamento") Integer idTrattamento) {
	trattamentiService.cancellaTrattamento(idTrattamento);
    }

    @GetMapping(value = "/download/Trattamenti.xlsx")
    public ResponseEntity<InputStreamResource> excelTrattamenti() throws IOException {
	List<Trattamento> trattamento = trattamentiRepo.findAll();

	ByteArrayInputStream in = ExcelGeneratorTrattamenti.trattamentiExcel(trattamento);

	HttpHeaders headers = new HttpHeaders();
	headers.add("Content-Disposition", "attachment; filename=Trattamenti.xlsx");

	return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

    }

    @RequestMapping(value = "/download/Trattamenti.pdf", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> pdfTrattamenti() {

	List<Trattamento> trattamento = trattamentiRepo.findAll();

	ByteArrayInputStream bis = PdfGeneratorTrattamenti.trattamentiReport(trattamento);

	HttpHeaders headers = new HttpHeaders();
	headers.add("Content-Disposition", "inline; filename=Trattamenti.pdf");

	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
		.body(new InputStreamResource(bis));
    }
}
