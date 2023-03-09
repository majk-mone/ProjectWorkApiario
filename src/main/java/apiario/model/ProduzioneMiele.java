package apiario.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "produzione_miele")
@Data
public class ProduzioneMiele {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produzione")
    private Integer idProduzione;

    @Column(name = "data_produzione")
    private LocalDate dataProduzione;

    @OneToOne
    @JoinColumn(name = "id_arnia", referencedColumnName = "id_arnia")
    private Arnia arnia;

    @OneToOne
    @JoinColumn(name = "id_tipo_miele", referencedColumnName = "id_tipo_miele")
    private TipoMiele tipoMiele;

    @Column(name = "quantita")
    private Integer quantita;

    public ProduzioneMiele() {
	super();
    }

}
