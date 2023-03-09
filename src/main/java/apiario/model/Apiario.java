package apiario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "apiario")
@Data
public class Apiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_apiario")
    private Integer idApiario;

    @Column(name = "indirizzo")
    private String indirizzo;

    @Column(name = "longitudine")
    private Float longitudine;

    @Column(name = "latitudine")
    private Float latitudine;

    public Apiario() {
	super();
    }

    public Apiario(Integer idApiario, String indirizzo, Float longitudine, Float latitudine) {
	super();
	this.idApiario = idApiario;
	this.indirizzo = indirizzo;
	this.longitudine = longitudine;
	this.latitudine = latitudine;
    }

    public Apiario(Integer idApiario) {
	super();
	this.idApiario = idApiario;
    }

    public Apiario(String employerTypeName) {
	// TODO Auto-generated constructor stub
    }
}
