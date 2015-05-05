package com.example.wzpn.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Wynik.
 */
@Entity
@Table(name = "WYNIK")
public class Wynik implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "wynik")
    private String wynik;

    @Column(name = "bramki_pierwszej")
    private String bramkiPierwszej;

    @Column(name = "bramki_drugiej")
    private String bramkiDrugiej;

    @Column(name = "zolte_pierwszej")
    private String zoltePierwszej;

    @Column(name = "zolte_drugiej")
    private String zolteDrugiej;

    @Column(name = "czerwone_pierwszej")
    private String czerwonePierwszej;

    @Column(name = "czerwone_drugiej")
    private String czerwoneDrugiej;

    @Column(name = "uwagi")
    private String uwagi;

    @OneToOne
    private Grafik grafik;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWynik() {
        return wynik;
    }

    public void setWynik(String wynik) {
        this.wynik = wynik;
    }

    public String getBramkiPierwszej() {
        return bramkiPierwszej;
    }

    public void setBramkiPierwszej(String bramkiPierwszej) {
        this.bramkiPierwszej = bramkiPierwszej;
    }

    public String getBramkiDrugiej() {
        return bramkiDrugiej;
    }

    public void setBramkiDrugiej(String bramkiDrugiej) {
        this.bramkiDrugiej = bramkiDrugiej;
    }

    public String getZoltePierwszej() {
        return zoltePierwszej;
    }

    public void setZoltePierwszej(String zoltePierwszej) {
        this.zoltePierwszej = zoltePierwszej;
    }

    public String getZolteDrugiej() {
        return zolteDrugiej;
    }

    public void setZolteDrugiej(String zolteDrugiej) {
        this.zolteDrugiej = zolteDrugiej;
    }

    public String getCzerwonePierwszej() {
        return czerwonePierwszej;
    }

    public void setCzerwonePierwszej(String czerwonePierwszej) {
        this.czerwonePierwszej = czerwonePierwszej;
    }

    public String getCzerwoneDrugiej() {
        return czerwoneDrugiej;
    }

    public void setCzerwoneDrugiej(String czerwoneDrugiej) {
        this.czerwoneDrugiej = czerwoneDrugiej;
    }

    public String getUwagi() {
        return uwagi;
    }

    public void setUwagi(String uwagi) {
        this.uwagi = uwagi;
    }

    public Grafik getGrafik() {
        return grafik;
    }

    public void setGrafik(Grafik grafik) {
        this.grafik = grafik;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Wynik wynik = (Wynik) o;

        if ( ! Objects.equals(id, wynik.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Wynik{" +
                "id=" + id +
                ", wynik='" + wynik + "'" +
                ", bramkiPierwszej='" + bramkiPierwszej + "'" +
                ", bramkiDrugiej='" + bramkiDrugiej + "'" +
                ", zoltePierwszej='" + zoltePierwszej + "'" +
                ", zolteDrugiej='" + zolteDrugiej + "'" +
                ", czerwonePierwszej='" + czerwonePierwszej + "'" +
                ", czerwoneDrugiej='" + czerwoneDrugiej + "'" +
                ", uwagi='" + uwagi + "'" +
                '}';
    }
}
