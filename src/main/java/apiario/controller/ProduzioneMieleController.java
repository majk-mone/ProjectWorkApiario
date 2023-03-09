package apiario.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import apiario.model.ProduzioneMiele;
import apiario.model.TipoMiele;
import apiario.repository.ProduzioneMieleRepository;
import apiario.repository.TipoMieleRepository;
import apiario.service.ProduzioneMieleService;
import apiario.utility.ExcelGeneratorMiele;
import apiario.utility.PdfGeneratorMiele;

@RestController
public class ProduzioneMieleController {

    @Autowired
    ProduzioneMieleRepository mieleRepo;

    @Autowired
    ProduzioneMieleService mieleService;

    @Autowired
    TipoMieleRepository tipoRepo;

    @PostMapping("/miele")
    public ProduzioneMiele inserisciMiele(@RequestBody ProduzioneMiele p) {
	return mieleService.inserisciMiele(p);
    }

    @GetMapping("/miele")
    public List<ProduzioneMiele> listaMiele() {
	return mieleRepo.findAll();

    }

    @GetMapping("/tipomiele")
    public List<TipoMiele> listaTipo() {
	return tipoRepo.findAll();
    }

    @PutMapping("/miele/{idProduzione}")
    public ProduzioneMiele modificaMiele(@PathVariable("idProduzione") Integer idProduzione,
	    @RequestBody ProduzioneMiele p) {
	return mieleService.modificaMiele(idProduzione, p);
    }

    @DeleteMapping("/miele/{idProduzione}")
    public void eliminaMiele(@PathVariable("idProduzione") Integer idProduzione) {
	mieleService.eliminaMiele(idProduzione);
    }

    @GetMapping(value = "/download/Miele.xlsx")
    public ResponseEntity<InputStreamResource> excelMiele() throws IOException {
	List<ProduzioneMiele> miele = mieleRepo.findAll();

	ByteArrayInputStream in = ExcelGeneratorMiele.mieleExcel(miele);

	HttpHeaders headers = new HttpHeaders();
	headers.add("Content-Disposition", "attachment; filename=Miele.xlsx");

	return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }

    @RequestMapping(value = "/download/Miele.pdf", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> pdfMiele() {
	List<ProduzioneMiele> miele = mieleRepo.findAll();

	ByteArrayInputStream bis = PdfGeneratorMiele.mieleReport(miele);
	HttpHeaders headers = new HttpHeaders();
	headers.add("Content-Disposition", "inline; filename=Apiario.pdf");

	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
		.body(new InputStreamResource(bis));
    }

    @GetMapping("/statistiche")
    public List<ProduzioneMiele> getProduzioneMieleByDataTipoMieleApiario2(
	    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInizio,
	    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFine, @RequestParam @Nullable List<Integer> tipoMiele,
	    @RequestParam @Nullable List<Integer> idApiario) {

	if (dataInizio == null && dataFine == null && idApiario.isEmpty()) {

	    return mieleRepo.getbyTipoMiele(tipoMiele);

	}
	if (idApiario.isEmpty() && tipoMiele.isEmpty()) {
	    return mieleRepo.getByData(dataInizio, dataFine);
	}

	if (dataInizio == null && dataFine == null && tipoMiele.isEmpty()) {

	    return mieleRepo.getbyApiario(idApiario);
	} else if (dataInizio == null && dataFine == null) {

	    return mieleRepo.getbyTipoMieleApiario(tipoMiele, idApiario);

	} else if (idApiario.isEmpty()) {
	    return mieleRepo.getbyDataTipoMiele(dataInizio, dataFine, tipoMiele);

	} else if (tipoMiele.isEmpty()) {
	    return mieleRepo.getbyDataIdApiario(dataInizio, dataFine, idApiario);
	}

	return mieleRepo.getbyDataTipoMieleApiario(dataInizio, dataFine, tipoMiele, idApiario);
    }
}
