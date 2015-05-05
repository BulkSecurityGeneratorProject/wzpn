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
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Kara.
 */
@Entity
@Table(name = "KARA")
public class Kara implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Column(name = "data_otrzymania")
    private DateTime dataOtrzymania;

    @Column(name = "powod")
    private String powod;

    @Column(name = "kwota", precision=10, scale=2)
    private BigDecimal kwota;

    @ManyToOne
    private Druzyna druzyna;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getDataOtrzymania() {
        return dataOtrzymania;
    }

    public void setDataOtrzymania(DateTime dataOtrzymania) {
        this.dataOtrzymania = dataOtrzymania;
    }

    public String getPowod() {
        return powod;
    }

    public void setPowod(String powod) {
        this.powod = powod;
    }

    public BigDecimal getKwota() {
        return kwota;
    }

    public void setKwota(BigDecimal kwota) {
        this.kwota = kwota;
    }

    public Druzyna getDruzyna() {
        return druzyna;
    }

    public void setDruzyna(Druzyna druzyna) {
        this.druzyna = druzyna;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Kara kara = (Kara) o;

        if ( ! Objects.equals(id, kara.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Kara{" +
                "id=" + id +
                ", dataOtrzymania='" + dataOtrzymania + "'" +
                ", powod='" + powod + "'" +
                ", kwota='" + kwota + "'" +
                '}';
    }
}
