package com.example.wzpn.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Druzyna.
 */
@Entity
@Table(name = "DRUZYNA")
public class Druzyna implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nazwa")
    private String nazwa;

    @Column(name = "usunieta")
    private Boolean usunieta;

    @Column(name = "zawieszona")
    private Boolean zawieszona;

    @Column(name = "prezes")
    private String prezes;

    @Column(name = "adres")
    private String adres;

    @Column(name = "telefon")
    private String telefon;

    @Column(name = "email")
    private String email;

    @Column(name = "strona")
    private String strona;

    @Column(name = "trenerzy")
    private String trenerzy;

    @Column(name = "opis")
    private String opis;

    @ManyToOne
    private Liga liga;

    @OneToMany(mappedBy = "druzyna")
    @JsonIgnore
    private Set<Kara> karas = new HashSet<>();

    @OneToMany(mappedBy = "pierwszaDruzyna")
    @JsonIgnore
    private Set<Grafik> pierwszaDruzynas = new HashSet<>();

    @OneToMany(mappedBy = "drugaDruzyna")
    @JsonIgnore
    private Set<Grafik> drugaDruzynas = new HashSet<>();

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

    public Boolean getUsunieta() {
        return usunieta;
    }

    public void setUsunieta(Boolean usunieta) {
        this.usunieta = usunieta;
    }

    public Boolean getZawieszona() {
        return zawieszona;
    }

    public void setZawieszona(Boolean zawieszona) {
        this.zawieszona = zawieszona;
    }

    public String getPrezes() {
        return prezes;
    }

    public void setPrezes(String prezes) {
        this.prezes = prezes;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStrona() {
        return strona;
    }

    public void setStrona(String strona) {
        this.strona = strona;
    }

    public String getTrenerzy() {
        return trenerzy;
    }

    public void setTrenerzy(String trenerzy) {
        this.trenerzy = trenerzy;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }

    public Set<Kara> getKaras() {
        return karas;
    }

    public void setKaras(Set<Kara> karas) {
        this.karas = karas;
    }

    public Set<Grafik> getPierwszaDruzynas() {
        return pierwszaDruzynas;
    }

    public void setPierwszaDruzynas(Set<Grafik> grafiks) {
        this.pierwszaDruzynas = grafiks;
    }

    public Set<Grafik> getDrugaDruzynas() {
        return drugaDruzynas;
    }

    public void setDrugaDruzynas(Set<Grafik> grafiks) {
        this.drugaDruzynas = grafiks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Druzyna druzyna = (Druzyna) o;

        if ( ! Objects.equals(id, druzyna.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Druzyna{" +
                "id=" + id +
                ", nazwa='" + nazwa + "'" +
                ", usunieta='" + usunieta + "'" +
                ", zawieszona='" + zawieszona + "'" +
                ", prezes='" + prezes + "'" +
                ", adres='" + adres + "'" +
                ", telefon='" + telefon + "'" +
                ", email='" + email + "'" +
                ", strona='" + strona + "'" +
                ", trenerzy='" + trenerzy + "'" +
                ", opis='" + opis + "'" +
                '}';
    }
}
