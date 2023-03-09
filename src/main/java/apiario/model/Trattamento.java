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
@Table(name = "trattamenti")
@Data
public class Trattamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trattamento")
    private Integer idTrattamento;

    @OneToOne
    @JoinColumn(name = "id_arnia", referencedColumnName = "id_arnia")
    private Arnia arnia;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "data_trattamento")
    private LocalDate dataTrattamento;
}
