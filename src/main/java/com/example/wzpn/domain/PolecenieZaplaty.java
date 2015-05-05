package com.example.wzpn.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A PolecenieZaplaty.
 */
@Entity
@Table(name = "POLECENIEZAPLATY")
public class PolecenieZaplaty implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nazwa_odbiorcy")
    private String nazwaOdbiorcy;

    @Column(name = "nazwa_zleceniodawcy")
    private String nazwaZleceniodawcy;

    @Column(name = "rachunek")
    private String rachunek;

    @Column(name = "tytul")
    private String tytul;

    @Column(name = "kwota", precision=10, scale=2)
    private BigDecimal kwota;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazwaOdbiorcy() {
        return nazwaOdbiorcy;
    }

    public void setNazwaOdbiorcy(String nazwaOdbiorcy) {
        this.nazwaOdbiorcy = nazwaOdbiorcy;
    }

    public String getNazwaZleceniodawcy() {
        return nazwaZleceniodawcy;
    }

    public void setNazwaZleceniodawcy(String nazwaZleceniodawcy) {
        this.nazwaZleceniodawcy = nazwaZleceniodawcy;
    }

    public String getRachunek() {
        return rachunek;
    }

    public void setRachunek(String rachunek) {
        this.rachunek = rachunek;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public BigDecimal getKwota() {
        return kwota;
    }

    public void setKwota(BigDecimal kwota) {
        this.kwota = kwota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PolecenieZaplaty polecenieZaplaty = (PolecenieZaplaty) o;

        if ( ! Objects.equals(id, polecenieZaplaty.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PolecenieZaplaty{" +
                "id=" + id +
                ", nazwaOdbiorcy='" + nazwaOdbiorcy + "'" +
                ", nazwaZleceniodawcy='" + nazwaZleceniodawcy + "'" +
                ", rachunek='" + rachunek + "'" +
                ", tytul='" + tytul + "'" +
                ", kwota='" + kwota + "'" +
                '}';
    }
}
