package com.example.wzpn.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.example.wzpn.domain.util.CustomDateTimeDeserializer;
import com.example.wzpn.domain.util.CustomDateTimeSerializer;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Grafik.
 */
@Entity
@Table(name = "GRAFIK")
public class Grafik implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nazwa")
    private String nazwa;

    @Column(name = "opis")
    private String opis;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Column(name = "rozpoczecie")
    private DateTime rozpoczecie;

    @ManyToOne
    private Druzyna pierwszaDruzyna;

    @ManyToOne
    private Druzyna drugaDruzyna;

    @ManyToOne
    private ObiektSportowy obiektSportowy;

    @OneToOne(mappedBy = "grafik")
    private Wynik wynik;

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

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public DateTime getRozpoczecie() {
        return rozpoczecie;
    }

    public void setRozpoczecie(DateTime rozpoczecie) {
        this.rozpoczecie = rozpoczecie;
    }

    public Druzyna getPierwszaDruzyna() {
        return pierwszaDruzyna;
    }

    public void setPierwszaDruzyna(Druzyna druzyna) {
        this.pierwszaDruzyna = druzyna;
    }

    public Druzyna getDrugaDruzyna() {
        return drugaDruzyna;
    }

    public void setDrugaDruzyna(Druzyna druzyna) {
        this.drugaDruzyna = druzyna;
    }

    public ObiektSportowy getObiektSportowy() {
        return obiektSportowy;
    }

    public void setObiektSportowy(ObiektSportowy obiektSportowy) {
        this.obiektSportowy = obiektSportowy;
    }

    public Wynik getWynik() {
        return wynik;
    }

    public void setWynik(Wynik wynik) {
        this.wynik = wynik;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Grafik grafik = (Grafik) o;

        if ( ! Objects.equals(id, grafik.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Grafik{" +
                "id=" + id +
                ", nazwa='" + nazwa + "'" +
                ", opis='" + opis + "'" +
                ", rozpoczecie='" + rozpoczecie + "'" +
                '}';
    }
}
