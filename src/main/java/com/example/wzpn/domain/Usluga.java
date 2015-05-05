package com.example.wzpn.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A Usluga.
 */
@Entity
@Table(name = "USLUGA")
public class Usluga implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nazwa")
    private String nazwa;

    @Column(name = "cena", precision=10, scale=2)
    private BigDecimal cena;

    @Column(name = "podatek")
    private Integer podatek;

    @Column(name = "usunieta")
    private Boolean usunieta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    public Integer getPodatek() {
        return podatek;
    }

    public void setPodatek(Integer podatek) {
        this.podatek = podatek;
    }

    public Boolean getUsunieta() {
        return usunieta;
    }

    public void setUsunieta(Boolean usunieta) {
        this.usunieta = usunieta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Usluga usluga = (Usluga) o;

        if ( ! Objects.equals(id, usluga.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Usluga{" +
                "id=" + id +
                ", nazwa='" + nazwa + "'" +
                ", cena='" + cena + "'" +
                ", podatek='" + podatek + "'" +
                ", usunieta='" + usunieta + "'" +
                '}';
    }
}
