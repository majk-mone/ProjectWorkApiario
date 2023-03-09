package apiario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apiario.model.Arnia;
import apiario.repository.ArniaRepository;

@Service
public class ArniaService {

    @Autowired
    ArniaRepository arniaRepo;

    public Arnia inserisciArnia(Arnia a) {

	return arniaRepo.save(a);
    }

    public Arnia modificaArnia(Integer id, Arnia a) {
	Arnia arniaDaModificare = arniaRepo.getReferenceById(id);
	arniaDaModificare.setTipoRegina(a.getTipoRegina());
	arniaDaModificare.setAnnoRegina(a.getAnnoRegina());
	arniaDaModificare.setAnnoAcquisto(a.getAnnoAcquisto());
	arniaDaModificare.setApiario(a.getApiario());
	arniaDaModificare.setTipoArnia(a.getTipoArnia());
	return arniaRepo.save(arniaDaModificare);
    }

    public void cancellaArnia(Integer id) {
	Arnia arniaDaCancellare = arniaRepo.getReferenceById(id);
	arniaRepo.delete(arniaDaCancellare);
    }
}
