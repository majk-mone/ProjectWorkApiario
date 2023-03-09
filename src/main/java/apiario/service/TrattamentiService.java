package apiario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apiario.model.Trattamento;
import apiario.repository.TrattamentoRepository;

@Service
public class TrattamentiService {

    @Autowired
    TrattamentoRepository trattamentiRepo;

    public Trattamento inserisciTrattamento(Trattamento t) {

	return trattamentiRepo.save(t);

    }

    public Trattamento modificaTrattamenti(Integer idTrattamento, Trattamento t) {

	Trattamento trattamentoDaModificare = trattamentiRepo.getReferenceById(idTrattamento);

	trattamentoDaModificare.setArnia(t.getArnia());
	trattamentoDaModificare.setDescrizione(t.getDescrizione());
	trattamentoDaModificare.setDataTrattamento(t.getDataTrattamento());

	return trattamentiRepo.save(trattamentoDaModificare);
    }

    public void cancellaTrattamento(Integer idTrattamento) {
	Trattamento trattamentoDaCancellare = trattamentiRepo.getReferenceById(idTrattamento);
	trattamentiRepo.delete(trattamentoDaCancellare);
    }
}
