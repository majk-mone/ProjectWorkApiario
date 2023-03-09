package apiario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tipo_arnia")
@Data
public class TipoArnia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_arnia")
    private Integer idTipoArnia;

    @Column(name = "descrizione")
    private String descrizione;

    public TipoArnia(String employerTypeName) {
	// TODO Auto-generated constructor stub
    }

    public TipoArnia(Integer idTipoArnia, String descrizione) {
	super();
	this.idTipoArnia = idTipoArnia;
	this.descrizione = descrizione;
    }

    public TipoArnia() {
	super();
    }

}
