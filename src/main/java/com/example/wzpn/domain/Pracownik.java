package com.example.wzpn.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.example.wzpn.domain.util.CustomLocalDateSerializer;
import com.example.wzpn.domain.util.ISO8601LocalDateDeserializer;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A Pracownik.
 */
@Entity
@Table(name = "PRACOWNIK")
public class Pracownik implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "imie")
    private String imie;

    @Column(name = "nazwisko")
    private String nazwisko;

    @Column(name = "pesel")
    private String pesel;

    @Column(name = "nip")
    private String nip;

    @Column(name = "ulica")
    private String ulica;

    @Column(name = "kod_pocztowy")
    private String kodPocztowy;

    @Column(name = "miasto")
    private String miasto;

    @Column(name = "pensja", precision=10, scale=2, nullable = false)
    private BigDecimal pensja;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @JsonDeserialize(using = ISO8601LocalDateDeserializer.class)
    @Column(name = "data_zatrudnienia", nullable = false)
    private LocalDate dataZatrudnienia;

    @Column(name = "sekretariat")
    private Boolean sekretariat;

    @Column(name = "wydzial_gier")
    private Boolean wydzialGier;

    @Column(name = "ksiegowosc")
    private Boolean ksiegowosc;

    @Column(name = "administracja")
    private Boolean administracja;

    @Column(name = "sedzia")
    private Boolean sedzia;

    @Column(name = "opis")
    private String opis;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public BigDecimal getPensja() {
        return pensja;
    }

    public void setPensja(BigDecimal pensja) {
        this.pensja = pensja;
    }

    public LocalDate getDataZatrudnienia() {
        return dataZatrudnienia;
    }

    public void setDataZatrudnienia(LocalDate dataZatrudnienia) {
        this.dataZatrudnienia = dataZatrudnienia;
    }

    public Boolean getSekretariat() {
        return sekretariat;
    }

    public void setSekretariat(Boolean sekretariat) {
        this.sekretariat = sekretariat;
    }

    public Boolean getWydzialGier() {
        return wydzialGier;
    }

    public void setWydzialGier(Boolean wydzialGier) {
        this.wydzialGier = wydzialGier;
    }

    public Boolean getKsiegowosc() {
        return ksiegowosc;
    }

    public void setKsiegowosc(Boolean ksiegowosc) {
        this.ksiegowosc = ksiegowosc;
    }

    public Boolean getAdministracja() {
        return administracja;
    }

    public void setAdministracja(Boolean administracja) {
        this.administracja = administracja;
    }

    public Boolean getSedzia() {
        return sedzia;
    }

    public void setSedzia(Boolean sedzia) {
        this.sedzia = sedzia;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Pracownik pracownik = (Pracownik) o;

        if ( ! Objects.equals(id, pracownik.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Pracownik{" +
                "id=" + id +
                ", login='" + login + "'" +
                ", imie='" + imie + "'" +
                ", nazwisko='" + nazwisko + "'" +
                ", pesel='" + pesel + "'" +
                ", nip='" + nip + "'" +
                ", ulica='" + ulica + "'" +
                ", kodPocztowy='" + kodPocztowy + "'" +
                ", miasto='" + miasto + "'" +
                ", pensja='" + pensja + "'" +
                ", dataZatrudnienia='" + dataZatrudnienia + "'" +
                ", sekretariat='" + sekretariat + "'" +
                ", wydzialGier='" + wydzialGier + "'" +
                ", ksiegowosc='" + ksiegowosc + "'" +
                ", administracja='" + administracja + "'" +
                ", sedzia='" + sedzia + "'" +
                ", opis='" + opis + "'" +
                '}';
    }
}
