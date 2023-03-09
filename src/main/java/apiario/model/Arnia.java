package apiario.model;

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
@Table(name = "arnia")
@Data
public class Arnia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_arnia")
    private Integer idArnia;

    @Column(name = "tipo_regina")
    private String tipoRegina;

    @Column(name = "anno_regina")
    private Integer annoRegina;

    @Column(name = "anno_acquisto")
    private Integer annoAcquisto;

    @OneToOne
    @JoinColumn(name = "id_apiario", referencedColumnName = "id_apiario")
    private Apiario apiario;

    @OneToOne
    @JoinColumn(name = "id_tipo_arnia", referencedColumnName = "id_tipo_arnia")
    private TipoArnia tipoArnia;

    public Arnia() {
	super();
    }

}
