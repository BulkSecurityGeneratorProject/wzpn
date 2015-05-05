package com.example.wzpn.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Przedsiebiorca.
 */
@Entity
@Table(name = "PRZEDSIEBIORCA")
public class Przedsiebiorca implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nazwa")
    private String nazwa;

    @Column(name = "adres")
    private String adres;

    @Column(name = "miejscowosc")
    private String miejscowosc;

    @Column(name = "kod_pocztowy")
    private String kodPocztowy;

    @Column(name = "nip")
    private String nip;

    @Column(name = "email")
    private String email;

    @Column(name = "telefon")
    private String telefon;

    @OneToMany(mappedBy = "sprzedawca")
    @JsonIgnore
    private Set<Faktura> sprzedawcas = new HashSet<>();

    @OneToMany(mappedBy = "nabywca")
    @JsonIgnore
    private Set<Faktura> nabywcas = new HashSet<>();

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

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Set<Faktura> getSprzedawcas() {
        return sprzedawcas;
    }

    public void setSprzedawcas(Set<Faktura> fakturas) {
        this.sprzedawcas = fakturas;
    }

    public Set<Faktura> getNabywcas() {
        return nabywcas;
    }

    public void setNabywcas(Set<Faktura> fakturas) {
        this.nabywcas = fakturas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Przedsiebiorca przedsiebiorca = (Przedsiebiorca) o;

        if ( ! Objects.equals(id, przedsiebiorca.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Przedsiebiorca{" +
                "id=" + id +
                ", nazwa='" + nazwa + "'" +
                ", adres='" + adres + "'" +
                ", miejscowosc='" + miejscowosc + "'" +
                ", kodPocztowy='" + kodPocztowy + "'" +
                ", nip='" + nip + "'" +
                ", email='" + email + "'" +
                ", telefon='" + telefon + "'" +
                '}';
    }
}
