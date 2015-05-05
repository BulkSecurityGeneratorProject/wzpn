package com.example.wzpn.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A PozycjaFaktury.
 */
@Entity
@Table(name = "POZYCJAFAKTURY")
public class PozycjaFaktury implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nazwa")
    private String nazwa;

    @Column(name = "brutto", precision=10, scale=2)
    private BigDecimal brutto;

    @Column(name = "ilosc", precision=10, scale=2)
    private BigDecimal ilosc;

    @Column(name = "podatek")
    private Integer podatek;

    @ManyToOne
    private Faktura faktura;

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

    public BigDecimal getBrutto() {
        return brutto;
    }

    public void setBrutto(BigDecimal brutto) {
        this.brutto = brutto;
    }

    public BigDecimal getIlosc() {
        return ilosc;
    }

    public void setIlosc(BigDecimal ilosc) {
        this.ilosc = ilosc;
    }

    public Integer getPodatek() {
        return podatek;
    }

    public void setPodatek(Integer podatek) {
        this.podatek = podatek;
    }

    public Faktura getFaktura() {
        return faktura;
    }

    public void setFaktura(Faktura faktura) {
        this.faktura = faktura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PozycjaFaktury pozycjaFaktury = (PozycjaFaktury) o;

        if ( ! Objects.equals(id, pozycjaFaktury.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PozycjaFaktury{" +
                "id=" + id +
                ", nazwa='" + nazwa + "'" +
                ", brutto='" + brutto + "'" +
                ", ilosc='" + ilosc + "'" +
                ", podatek='" + podatek + "'" +
                '}';
    }
}
