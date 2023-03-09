package apiario.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import apiario.model.ProduzioneMiele;

public interface ProduzioneMieleRepository extends JpaRepository<ProduzioneMiele, Integer> {

    @Query(value = "SELECT b.id_arnia, a.id_produzione, c.descrizione, a.data_produzione, d.id_tipo_miele, a.quantita, b.id_apiario, e.indirizzo FROM produzione_miele a, arnia b ,tipo_arnia c, tipo_miele d, apiario e WHERE a.id_arnia = b.id_arnia AND b.id_tipo_arnia= c.id_tipo_arnia AND a.id_tipo_miele = d.id_tipo_miele AND b.id_apiario = e.id_apiario AND a.data_produzione BETWEEN TO_DATE(:dataInizio, 'yyyy/mm/dd') AND TO_DATE(:dataFine, 'yyyy/mm/dd') ORDER BY a.data_produzione,b.id_apiario", nativeQuery = true)
    public List<ProduzioneMiele> getByData(LocalDate dataInizio, LocalDate dataFine);

    @Query(value = "SELECT b.id_arnia, a.id_produzione, c.descrizione, a.data_produzione, d.id_tipo_miele, a.quantita, b.id_apiario, e.indirizzo FROM produzione_miele a, arnia b ,tipo_arnia c, tipo_miele d, apiario e WHERE a.id_arnia = b.id_arnia AND b.id_tipo_arnia= c.id_tipo_arnia AND a.id_tipo_miele = d.id_tipo_miele AND b.id_apiario = e.id_apiario AND a.data_produzione BETWEEN TO_DATE(:dataInizio, 'yyyy/mm/dd') AND TO_DATE(:dataFine, 'yyyy/mm/dd') AND d.id_tipo_miele in (:tipoMiele) AND b.id_apiario in (:idApiario) ORDER BY a.data_produzione,b.id_apiario", nativeQuery = true)
    public List<ProduzioneMiele> getbyDataTipoMieleApiario(LocalDate dataInizio, LocalDate dataFine,
	    List<Integer> tipoMiele, List<Integer> idApiario);

    @Query(value = "SELECT b.id_arnia, a.id_produzione, c.descrizione, a.data_produzione, d.id_tipo_miele, a.quantita, b.id_apiario, e.indirizzo FROM produzione_miele a, arnia b ,tipo_arnia c, tipo_miele d, apiario e WHERE a.id_arnia = b.id_arnia AND b.id_tipo_arnia= c.id_tipo_arnia AND a.id_tipo_miele = d.id_tipo_miele AND b.id_apiario = e.id_apiario AND a.data_produzione BETWEEN TO_DATE(:dataInizio, 'yyyy/mm/dd') AND TO_DATE(:dataFine, 'yyyy/mm/dd') AND d.id_tipo_miele in (:tipoMiele) ORDER BY a.data_produzione,b.id_apiario", nativeQuery = true)
    public List<ProduzioneMiele> getbyDataTipoMiele(LocalDate dataInizio, LocalDate dataFine, List<Integer> tipoMiele);

    @Query(value = "SELECT b.id_arnia, a.id_produzione, c.descrizione, a.data_produzione, d.id_tipo_miele, a.quantita, b.id_apiario, e.indirizzo FROM produzione_miele a, arnia b ,tipo_arnia c, tipo_miele d, apiario e WHERE a.id_arnia = b.id_arnia AND b.id_tipo_arnia= c.id_tipo_arnia AND a.id_tipo_miele = d.id_tipo_miele AND b.id_apiario = e.id_apiario AND a.data_produzione BETWEEN TO_DATE(:dataInizio, 'yyyy/mm/dd') AND TO_DATE(:dataFine, 'yyyy/mm/dd') AND b.id_apiario in (:idApiario) ORDER BY a.data_produzione,b.id_apiario", nativeQuery = true)
    public List<ProduzioneMiele> getbyDataIdApiario(LocalDate dataInizio, LocalDate dataFine, List<Integer> idApiario);

    @Query(value = "SELECT b.id_arnia, a.id_produzione, c.descrizione, a.data_produzione, d.id_tipo_miele, a.quantita, b.id_apiario, e.indirizzo FROM produzione_miele a, arnia b ,tipo_arnia c, tipo_miele d, apiario e WHERE a.id_arnia = b.id_arnia AND b.id_tipo_arnia= c.id_tipo_arnia AND a.id_tipo_miele = d.id_tipo_miele AND b.id_apiario = e.id_apiario AND d.id_tipo_miele in (:tipoMiele) AND b.id_apiario in (:idApiario)", nativeQuery = true)
    public List<ProduzioneMiele> getbyTipoMieleApiario(List<Integer> tipoMiele, List<Integer> idApiario);

    @Query(value = "SELECT b.id_arnia, a.id_produzione, c.descrizione, a.data_produzione, d.id_tipo_miele, a.quantita, b.id_apiario, e.indirizzo FROM produzione_miele a, arnia b ,tipo_arnia c, tipo_miele d, apiario e WHERE a.id_arnia = b.id_arnia AND b.id_tipo_arnia= c.id_tipo_arnia AND a.id_tipo_miele = d.id_tipo_miele AND b.id_apiario = e.id_apiario AND d.id_tipo_miele in (:tipoMiele)", nativeQuery = true)
    public List<ProduzioneMiele> getbyTipoMiele(List<Integer> tipoMiele);

    @Query(value = "SELECT b.id_arnia, a.id_produzione, c.descrizione, a.data_produzione, d.id_tipo_miele, a.quantita, b.id_apiario, e.indirizzo FROM produzione_miele a, arnia b ,tipo_arnia c, tipo_miele d, apiario e WHERE a.id_arnia = b.id_arnia AND b.id_tipo_arnia= c.id_tipo_arnia AND a.id_tipo_miele = d.id_tipo_miele AND b.id_apiario = e.id_apiario AND b.id_apiario in (:idApiario) ORDER BY a.data_produzione,b.id_apiario", nativeQuery = true)
    public List<ProduzioneMiele> getbyApiario(List<Integer> idApiario);
}
