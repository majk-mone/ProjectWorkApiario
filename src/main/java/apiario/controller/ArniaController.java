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

import apiario.model.Arnia;
import apiario.model.TipoArnia;
import apiario.repository.ApiarioRepository;
import apiario.repository.ArniaRepository;
import apiario.repository.TipoArniaRepository;
import apiario.service.ArniaService;
import apiario.utility.ExcelGeneratorArnia;
import apiario.utility.PdfGeneratorArnia;

@RestController
public class ArniaController {

    @Autowired
    ArniaRepository arniaRepo;

    @Autowired
    ArniaService arniaService;

    @Autowired
    TipoArniaRepository tipoRepo;

    @Autowired
    ApiarioRepository apiarioRepo;

    @PostMapping("/arnia")
    public Arnia inserisciArnia(@RequestBody Arnia a) {
	return arniaService.inserisciArnia(a);
    }

    @GetMapping("/arnia")
    public List<Arnia> listaArnie() {
	return arniaRepo.findAll();
    }

    @GetMapping("/tipoarnia")
    public List<TipoArnia> listaTipi() {
	return tipoRepo.findAll();
    }

    @PutMapping("/arnia/{idArnia}")
    public Arnia modificaArnia(@PathVariable("idArnia") Integer idArnia, @RequestBody Arnia a) {
	return arniaService.modificaArnia(idArnia, a);
    }

    @DeleteMapping("/arnia/{idArnia}")
    public void cancellaArnia(@PathVariable("idArnia") Integer idArnia) {
	arniaService.cancellaArnia(idArnia);
    }

    @GetMapping(value = "/download/Arnia.xlsx")
    public ResponseEntity<InputStreamResource> excelArnia() throws IOException {
	List<Arnia> arnia = arniaRepo.findAll();

	ByteArrayInputStream in = ExcelGeneratorArnia.arniaExcel(arnia);

	HttpHeaders headers = new HttpHeaders();
	headers.add("Content-Disposition", "attachment; filename=Arnia.xlsx");

	return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

    }

    @RequestMapping(value = "/download/Arnia.pdf", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> pdfArnia() {
	List<Arnia> arnia = arniaRepo.findAll();
	ByteArrayInputStream bis = PdfGeneratorArnia.arniaReport(arnia);

	HttpHeaders headers = new HttpHeaders();
	headers.add("Content-Disposition", "inline; filename=Arnia.pdf");

	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
		.body(new InputStreamResource(bis));
    }
}
