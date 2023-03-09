package apiario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apiario.model.ProduzioneMiele;
import apiario.repository.ProduzioneMieleRepository;

@Service
public class ProduzioneMieleService {

    @Autowired
    ProduzioneMieleRepository produzioneMieleRepo;

    public ProduzioneMiele inserisciMiele(ProduzioneMiele p) {
	return produzioneMieleRepo.save(p);
    }

    public ProduzioneMiele modificaMiele(Integer idProduzione, ProduzioneMiele p) {
	ProduzioneMiele mieleDaModificare = produzioneMieleRepo.getReferenceById(idProduzione);

	mieleDaModificare.setDataProduzione(p.getDataProduzione());
	mieleDaModificare.setArnia(p.getArnia());
	mieleDaModificare.setTipoMiele(p.getTipoMiele());
	mieleDaModificare.setQuantita(p.getQuantita());

	return produzioneMieleRepo.save(mieleDaModificare);
    }

    public void eliminaMiele(Integer idProduzione) {
	ProduzioneMiele mieleDaCancellare = produzioneMieleRepo.getReferenceById(idProduzione);
	produzioneMieleRepo.delete(mieleDaCancellare);
    }

}
