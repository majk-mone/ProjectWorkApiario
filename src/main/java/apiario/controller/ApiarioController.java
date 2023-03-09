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

import apiario.model.Apiario;
import apiario.repository.ApiarioRepository;
import apiario.service.ApiarioService;
import apiario.utility.ExcelGeneratorApiario;
import apiario.utility.PdfGeneratorApiario;

@RestController
public class ApiarioController {

    @Autowired
    ApiarioRepository apiarioRepo;

    @Autowired
    ApiarioService apiarioService;

    @PostMapping("/apiario")
    public Apiario inserisciApiario(@RequestBody Apiario a) {
	return apiarioService.inserisciApiario(a);
    }

    @GetMapping("/apiario")
    public List<Apiario> listaApiari() {

	return apiarioRepo.findAll();

    }

    @PutMapping("/apiario/{idApiario}")
    public Apiario modificaApiario(@PathVariable("idApiario") Integer idArpiario, @RequestBody Apiario a) {
	return apiarioService.modificaApiario(idArpiario, a);

    }

    @DeleteMapping("/apiario/{idApiario}")
    public void cancellaApiario(@PathVariable("idApiario") Integer idApiario) {
	apiarioService.cancellaApiario(idApiario);
    }

    @GetMapping(value = "/download/Apiario.xlsx")
    public ResponseEntity<InputStreamResource> excelApiario() throws IOException {

	List<Apiario> apiario = apiarioRepo.findAllByOrderByIdApiarioDesc();
	ByteArrayInputStream in = ExcelGeneratorApiario.apiarioToExcel(apiario);

	HttpHeaders headers = new HttpHeaders();
	headers.add("Content-Disposition", "attachment; filename=Apiario.xlsx");

	return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }

    @RequestMapping(value = "/download/Apiario.pdf", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> pdfApiario() {

	List<Apiario> apiario = apiarioRepo.findAllByOrderByIdApiarioDesc();

	ByteArrayInputStream bis = PdfGeneratorApiario.apiarioReport(apiario);

	HttpHeaders headers = new HttpHeaders();
	headers.add("Content-Disposition", "inline; filename=Apiario.pdf");

	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
		.body(new InputStreamResource(bis));
    }
}
