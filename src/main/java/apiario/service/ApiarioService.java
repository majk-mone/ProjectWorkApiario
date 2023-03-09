package apiario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apiario.model.Apiario;
import apiario.repository.ApiarioRepository;

@Service
public class ApiarioService {

    @Autowired
    ApiarioRepository apiarioRepo;

    public Apiario inserisciApiario(Apiario a) {
	return apiarioRepo.save(a);

    }

    public Apiario modificaApiario(Integer id, Apiario a) {
	Apiario apiarioDaModificare = apiarioRepo.getReferenceById(id);
	apiarioDaModificare.setIndirizzo(a.getIndirizzo());
	apiarioDaModificare.setLongitudine(a.getLongitudine());
	apiarioDaModificare.setLatitudine(a.getLatitudine());

	return apiarioRepo.save(apiarioDaModificare);
    }

    public void cancellaApiario(Integer id) {
	Apiario apiarioDaCancellare = apiarioRepo.getReferenceById(id);
	apiarioRepo.delete(apiarioDaCancellare);
    }

}
