package com.example.wzpn.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ObiektSportowy.
 */
@Entity
@Table(name = "OBIEKTSPORTOWY")
public class ObiektSportowy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nazwa")
    private String nazwa;

    @Column(name = "aktywny")
    private Boolean aktywny;

    @Column(name = "miasto")
    private String miasto;

    @Column(name = "kod_pocztowy")
    private String kodPocztowy;

    @Column(name = "ulica")
    private String ulica;

    @Column(name = "telefon")
    private String telefon;

    @Column(name = "prezes")
    private String prezes;

    @Column(name = "strona")
    private String strona;

    @Column(name = "miejsca_siedzace")
    private Integer miejscaSiedzace;

    @Column(name = "miejsca_stojace")
    private Integer miejscaStojace;

    @Column(name = "kryty")
    private Boolean kryty;

    @Column(name = "oswietlenie")
    private Boolean oswietlenie;

    @Column(name = "wymiary")
    private String wymiary;

    @OneToMany(mappedBy = "obiektSportowy")
    @JsonIgnore
    private Set<Grafik> grafiks = new HashSet<>();

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

    public Boolean getAktywny() {
        return aktywny;
    }

    public void setAktywny(Boolean aktywny) {
        this.aktywny = aktywny;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getPrezes() {
        return prezes;
    }

    public void setPrezes(String prezes) {
        this.prezes = prezes;
    }

    public String getStrona() {
        return strona;
    }

    public void setStrona(String strona) {
        this.strona = strona;
    }

    public Integer getMiejscaSiedzace() {
        return miejscaSiedzace;
    }

    public void setMiejscaSiedzace(Integer miejscaSiedzace) {
        this.miejscaSiedzace = miejscaSiedzace;
    }

    public Integer getMiejscaStojace() {
        return miejscaStojace;
    }

    public void setMiejscaStojace(Integer miejscaStojace) {
        this.miejscaStojace = miejscaStojace;
    }

    public Boolean getKryty() {
        return kryty;
    }

    public void setKryty(Boolean kryty) {
        this.kryty = kryty;
    }

    public Boolean getOswietlenie() {
        return oswietlenie;
    }

    public void setOswietlenie(Boolean oswietlenie) {
        this.oswietlenie = oswietlenie;
    }

    public String getWymiary() {
        return wymiary;
    }

    public void setWymiary(String wymiary) {
        this.wymiary = wymiary;
    }

    public Set<Grafik> getGrafiks() {
        return grafiks;
    }

    public void setGrafiks(Set<Grafik> grafiks) {
        this.grafiks = grafiks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ObiektSportowy obiektSportowy = (ObiektSportowy) o;

        if ( ! Objects.equals(id, obiektSportowy.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ObiektSportowy{" +
                "id=" + id +
                ", nazwa='" + nazwa + "'" +
                ", aktywny='" + aktywny + "'" +
                ", miasto='" + miasto + "'" +
                ", kodPocztowy='" + kodPocztowy + "'" +
                ", ulica='" + ulica + "'" +
                ", telefon='" + telefon + "'" +
                ", prezes='" + prezes + "'" +
                ", strona='" + strona + "'" +
                ", miejscaSiedzace='" + miejscaSiedzace + "'" +
                ", miejscaStojace='" + miejscaStojace + "'" +
                ", kryty='" + kryty + "'" +
                ", oswietlenie='" + oswietlenie + "'" +
                ", wymiary='" + wymiary + "'" +
                '}';
    }
}
