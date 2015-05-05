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
 * A Faktura.
 */
@Entity
@Table(name = "FAKTURA")
public class Faktura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nazwa")
    private String nazwa;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Column(name = "data_wystawienia")
    private DateTime dataWystawienia;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Column(name = "data_sprzedazy")
    private DateTime dataSprzedazy;

    @Column(name = "miejscowosc")
    private String miejscowosc;

    @ManyToOne
    private Przedsiebiorca sprzedawca;

    @ManyToOne
    private Przedsiebiorca nabywca;

    @OneToMany(mappedBy = "faktura")
    @JsonIgnore
    private Set<PozycjaFaktury> pozycjaFakturys = new HashSet<>();

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

    public DateTime getDataWystawienia() {
        return dataWystawienia;
    }

    public void setDataWystawienia(DateTime dataWystawienia) {
        this.dataWystawienia = dataWystawienia;
    }

    public DateTime getDataSprzedazy() {
        return dataSprzedazy;
    }

    public void setDataSprzedazy(DateTime dataSprzedazy) {
        this.dataSprzedazy = dataSprzedazy;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public Przedsiebiorca getSprzedawca() {
        return sprzedawca;
    }

    public void setSprzedawca(Przedsiebiorca przedsiebiorca) {
        this.sprzedawca = przedsiebiorca;
    }

    public Przedsiebiorca getNabywca() {
        return nabywca;
    }

    public void setNabywca(Przedsiebiorca przedsiebiorca) {
        this.nabywca = przedsiebiorca;
    }

    public Set<PozycjaFaktury> getPozycjaFakturys() {
        return pozycjaFakturys;
    }

    public void setPozycjaFakturys(Set<PozycjaFaktury> pozycjaFakturys) {
        this.pozycjaFakturys = pozycjaFakturys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Faktura faktura = (Faktura) o;

        if ( ! Objects.equals(id, faktura.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Faktura{" +
                "id=" + id +
                ", nazwa='" + nazwa + "'" +
                ", dataWystawienia='" + dataWystawienia + "'" +
                ", dataSprzedazy='" + dataSprzedazy + "'" +
                ", miejscowosc='" + miejscowosc + "'" +
                '}';
    }
}
