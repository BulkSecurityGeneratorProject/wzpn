package com.example.wzpn.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.example.wzpn.domain.util.CustomDateTimeDeserializer;
import com.example.wzpn.domain.util.CustomDateTimeSerializer;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A Sprawozdanie.
 */
@Entity
@Table(name = "SPRAWOZDANIE")
public class Sprawozdanie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "a1", precision=10, scale=2)
    private BigDecimal a1;

    @Column(name = "a2", precision=10, scale=2)
    private BigDecimal a2;

    @Column(name = "a3", precision=10, scale=2)
    private BigDecimal a3;

    @Column(name = "a4", precision=10, scale=2)
    private BigDecimal a4;

    @Column(name = "a5", precision=10, scale=2)
    private BigDecimal a5;

    @Column(name = "a6", precision=10, scale=2)
    private BigDecimal a6;

    @Column(name = "a7", precision=10, scale=2)
    private BigDecimal a7;

    @Column(name = "a8", precision=10, scale=2)
    private BigDecimal a8;

    @Column(name = "a9", precision=10, scale=2)
    private BigDecimal a9;

    @Column(name = "a10", precision=10, scale=2)
    private BigDecimal a10;

    @Column(name = "p1", precision=10, scale=2)
    private BigDecimal p1;

    @Column(name = "p2", precision=10, scale=2)
    private BigDecimal p2;

    @Column(name = "p3", precision=10, scale=2)
    private BigDecimal p3;

    @Column(name = "p4", precision=10, scale=2)
    private BigDecimal p4;

    @Column(name = "p5", precision=10, scale=2)
    private BigDecimal p5;

    @Column(name = "p6", precision=10, scale=2)
    private BigDecimal p6;

    @Column(name = "p7", precision=10, scale=2)
    private BigDecimal p7;

    @Column(name = "p8", precision=10, scale=2)
    private BigDecimal p8;

    @Column(name = "p9", precision=10, scale=2)
    private BigDecimal p9;

    @Column(name = "p10", precision=10, scale=2)
    private BigDecimal p10;

    @Column(name = "p11", precision=10, scale=2)
    private BigDecimal p11;

    @Column(name = "p12", precision=10, scale=2)
    private BigDecimal p12;

    @Column(name = "p13", precision=10, scale=2)
    private BigDecimal p13;

    @Column(name = "p14", precision=10, scale=2)
    private BigDecimal p14;

    @Column(name = "p15", precision=10, scale=2)
    private BigDecimal p15;

    @Column(name = "rok")
    private Integer rok;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Column(name = "dzien_sporzadzenia")
    private DateTime dzienSporzadzenia;

    @Column(name = "w1", precision=10, scale=2)
    private BigDecimal w1;

    @Column(name = "w2", precision=10, scale=2)
    private BigDecimal w2;

    @Column(name = "w3", precision=10, scale=2)
    private BigDecimal w3;

    @Column(name = "w4", precision=10, scale=2)
    private BigDecimal w4;

    @Column(name = "w5", precision=10, scale=2)
    private BigDecimal w5;

    @Column(name = "w6", precision=10, scale=2)
    private BigDecimal w6;

    @Column(name = "w7", precision=10, scale=2)
    private BigDecimal w7;

    @Column(name = "w8", precision=10, scale=2)
    private BigDecimal w8;

    @Column(name = "w9", precision=10, scale=2)
    private BigDecimal w9;

    @Column(name = "w10", precision=10, scale=2)
    private BigDecimal w10;

    @Column(name = "w11", precision=10, scale=2)
    private BigDecimal w11;

    @Column(name = "w12", precision=10, scale=2)
    private BigDecimal w12;

    @Column(name = "w13", precision=10, scale=2)
    private BigDecimal w13;

    @Column(name = "w14", precision=10, scale=2)
    private BigDecimal w14;

    @Column(name = "w15", precision=10, scale=2)
    private BigDecimal w15;

    @Column(name = "w16", precision=10, scale=2)
    private BigDecimal w16;

    @Column(name = "w17", precision=10, scale=2)
    private BigDecimal w17;

    @Column(name = "w18", precision=10, scale=2)
    private BigDecimal w18;

    @Column(name = "w19", precision=10, scale=2)
    private BigDecimal w19;

    @Column(name = "w20", precision=10, scale=2)
    private BigDecimal w20;

    @Column(name = "w21", precision=10, scale=2)
    private BigDecimal w21;

    @Column(name = "w22", precision=10, scale=2)
    private BigDecimal w22;

    @Column(name = "w23", precision=10, scale=2)
    private BigDecimal w23;

    @Column(name = "w24", precision=10, scale=2)
    private BigDecimal w24;

    @Column(name = "w25", precision=10, scale=2)
    private BigDecimal w25;

    @Column(name = "w26", precision=10, scale=2)
    private BigDecimal w26;

    @Column(name = "w27", precision=10, scale=2)
    private BigDecimal w27;

    @Column(name = "w28", precision=10, scale=2)
    private BigDecimal w28;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal geta1() {
        return a1;
    }

    public void seta1(BigDecimal a1) {
        this.a1 = a1;
    }

    public BigDecimal geta2() {
        return a2;
    }

    public void seta2(BigDecimal a2) {
        this.a2 = a2;
    }

    public BigDecimal geta3() {
        return a3;
    }

    public void seta3(BigDecimal a3) {
        this.a3 = a3;
    }

    public BigDecimal geta4() {
        return a4;
    }

    public void seta4(BigDecimal a4) {
        this.a4 = a4;
    }

    public BigDecimal geta5() {
        return a5;
    }

    public void seta5(BigDecimal a5) {
        this.a5 = a5;
    }

    public BigDecimal geta6() {
        return a6;
    }

    public void seta6(BigDecimal a6) {
        this.a6 = a6;
    }

    public BigDecimal geta7() {
        return a7;
    }

    public void seta7(BigDecimal a7) {
        this.a7 = a7;
    }

    public BigDecimal geta8() {
        return a8;
    }

    public void seta8(BigDecimal a8) {
        this.a8 = a8;
    }

    public BigDecimal geta9() {
        return a9;
    }

    public void seta9(BigDecimal a9) {
        this.a9 = a9;
    }

    public BigDecimal geta10() {
        return a10;
    }

    public void seta10(BigDecimal a10) {
        this.a10 = a10;
    }

    public BigDecimal getp1() {
        return p1;
    }

    public void setp1(BigDecimal p1) {
        this.p1 = p1;
    }

    public BigDecimal getp2() {
        return p2;
    }

    public void setp2(BigDecimal p2) {
        this.p2 = p2;
    }

    public BigDecimal getp3() {
        return p3;
    }

    public void setp3(BigDecimal p3) {
        this.p3 = p3;
    }

    public BigDecimal getp4() {
        return p4;
    }

    public void setp4(BigDecimal p4) {
        this.p4 = p4;
    }

    public BigDecimal getp5() {
        return p5;
    }

    public void setp5(BigDecimal p5) {
        this.p5 = p5;
    }

    public BigDecimal getp6() {
        return p6;
    }

    public void setp6(BigDecimal p6) {
        this.p6 = p6;
    }

    public BigDecimal getp7() {
        return p7;
    }

    public void setp7(BigDecimal p7) {
        this.p7 = p7;
    }

    public BigDecimal getp8() {
        return p8;
    }

    public void setp8(BigDecimal p8) {
        this.p8 = p8;
    }

    public BigDecimal getp9() {
        return p9;
    }

    public void setp9(BigDecimal p9) {
        this.p9 = p9;
    }

    public BigDecimal getp10() {
        return p10;
    }

    public void setp10(BigDecimal p10) {
        this.p10 = p10;
    }

    public BigDecimal getp11() {
        return p11;
    }

    public void setp11(BigDecimal p11) {
        this.p11 = p11;
    }

    public BigDecimal getp12() {
        return p12;
    }

    public void setp12(BigDecimal p12) {
        this.p12 = p12;
    }

    public BigDecimal getp13() {
        return p13;
    }

    public void setp13(BigDecimal p13) {
        this.p13 = p13;
    }

    public BigDecimal getp14() {
        return p14;
    }

    public void setp14(BigDecimal p14) {
        this.p14 = p14;
    }

    public BigDecimal getp15() {
        return p15;
    }

    public void setp15(BigDecimal p15) {
        this.p15 = p15;
    }

    public Integer getRok() {
        return rok;
    }

    public void setRok(Integer rok) {
        this.rok = rok;
    }

    public DateTime getDzienSporzadzenia() {
        return dzienSporzadzenia;
    }

    public void setDzienSporzadzenia(DateTime dzienSporzadzenia) {
        this.dzienSporzadzenia = dzienSporzadzenia;
    }

    public BigDecimal getw1() {
        return w1;
    }

    public void setw1(BigDecimal w1) {
        this.w1 = w1;
    }

    public BigDecimal getw2() {
        return w2;
    }

    public void setw2(BigDecimal w2) {
        this.w2 = w2;
    }

    public BigDecimal getw3() {
        return w3;
    }

    public void setw3(BigDecimal w3) {
        this.w3 = w3;
    }

    public BigDecimal getw4() {
        return w4;
    }

    public void setw4(BigDecimal w4) {
        this.w4 = w4;
    }

    public BigDecimal getw5() {
        return w5;
    }

    public void setw5(BigDecimal w5) {
        this.w5 = w5;
    }

    public BigDecimal getw6() {
        return w6;
    }

    public void setw6(BigDecimal w6) {
        this.w6 = w6;
    }

    public BigDecimal getw7() {
        return w7;
    }

    public void setw7(BigDecimal w7) {
        this.w7 = w7;
    }

    public BigDecimal getw8() {
        return w8;
    }

    public void setw8(BigDecimal w8) {
        this.w8 = w8;
    }

    public BigDecimal getw9() {
        return w9;
    }

    public void setw9(BigDecimal w9) {
        this.w9 = w9;
    }

    public BigDecimal getw10() {
        return w10;
    }

    public void setw10(BigDecimal w10) {
        this.w10 = w10;
    }

    public BigDecimal getw11() {
        return w11;
    }

    public void setw11(BigDecimal w11) {
        this.w11 = w11;
    }

    public BigDecimal getw12() {
        return w12;
    }

    public void setw12(BigDecimal w12) {
        this.w12 = w12;
    }

    public BigDecimal getw13() {
        return w13;
    }

    public void setw13(BigDecimal w13) {
        this.w13 = w13;
    }

    public BigDecimal getw14() {
        return w14;
    }

    public void setw14(BigDecimal w14) {
        this.w14 = w14;
    }

    public BigDecimal getw15() {
        return w15;
    }

    public void setw15(BigDecimal w15) {
        this.w15 = w15;
    }

    public BigDecimal getw16() {
        return w16;
    }

    public void setw16(BigDecimal w16) {
        this.w16 = w16;
    }

    public BigDecimal getw17() {
        return w17;
    }

    public void setw17(BigDecimal w17) {
        this.w17 = w17;
    }

    public BigDecimal getw18() {
        return w18;
    }

    public void setw18(BigDecimal w18) {
        this.w18 = w18;
    }

    public BigDecimal getw19() {
        return w19;
    }

    public void setw19(BigDecimal w19) {
        this.w19 = w19;
    }

    public BigDecimal getw20() {
        return w20;
    }

    public void setw20(BigDecimal w20) {
        this.w20 = w20;
    }

    public BigDecimal getw21() {
        return w21;
    }

    public void setw21(BigDecimal w21) {
        this.w21 = w21;
    }

    public BigDecimal getw22() {
        return w22;
    }

    public void setw22(BigDecimal w22) {
        this.w22 = w22;
    }

    public BigDecimal getw23() {
        return w23;
    }

    public void setw23(BigDecimal w23) {
        this.w23 = w23;
    }

    public BigDecimal getw24() {
        return w24;
    }

    public void setw24(BigDecimal w24) {
        this.w24 = w24;
    }

    public BigDecimal getw25() {
        return w25;
    }

    public void setw25(BigDecimal w25) {
        this.w25 = w25;
    }

    public BigDecimal getw26() {
        return w26;
    }

    public void setw26(BigDecimal w26) {
        this.w26 = w26;
    }

    public BigDecimal getw27() {
        return w27;
    }

    public void setw27(BigDecimal w27) {
        this.w27 = w27;
    }

    public BigDecimal getw28() {
        return w28;
    }

    public void setw28(BigDecimal w28) {
        this.w28 = w28;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Sprawozdanie sprawozdanie = (Sprawozdanie) o;

        if ( ! Objects.equals(id, sprawozdanie.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Sprawozdanie{" +
                "id=" + id +
                ", a1='" + a1 + "'" +
                ", a2='" + a2 + "'" +
                ", a3='" + a3 + "'" +
                ", a4='" + a4 + "'" +
                ", a5='" + a5 + "'" +
                ", a6='" + a6 + "'" +
                ", a7='" + a7 + "'" +
                ", a8='" + a8 + "'" +
                ", a9='" + a9 + "'" +
                ", a10='" + a10 + "'" +
                ", p1='" + p1 + "'" +
                ", p2='" + p2 + "'" +
                ", p3='" + p3 + "'" +
                ", p4='" + p4 + "'" +
                ", p5='" + p5 + "'" +
                ", p6='" + p6 + "'" +
                ", p7='" + p7 + "'" +
                ", p8='" + p8 + "'" +
                ", p9='" + p9 + "'" +
                ", p10='" + p10 + "'" +
                ", p11='" + p11 + "'" +
                ", p12='" + p12 + "'" +
                ", p13='" + p13 + "'" +
                ", p14='" + p14 + "'" +
                ", p15='" + p15 + "'" +
                ", rok='" + rok + "'" +
                ", dzienSporzadzenia='" + dzienSporzadzenia + "'" +
                ", w1='" + w1 + "'" +
                ", w2='" + w2 + "'" +
                ", w3='" + w3 + "'" +
                ", w4='" + w4 + "'" +
                ", w5='" + w5 + "'" +
                ", w6='" + w6 + "'" +
                ", w7='" + w7 + "'" +
                ", w8='" + w8 + "'" +
                ", w9='" + w9 + "'" +
                ", w10='" + w10 + "'" +
                ", w11='" + w11 + "'" +
                ", w12='" + w12 + "'" +
                ", w13='" + w13 + "'" +
                ", w14='" + w14 + "'" +
                ", w15='" + w15 + "'" +
                ", w16='" + w16 + "'" +
                ", w17='" + w17 + "'" +
                ", w18='" + w18 + "'" +
                ", w19='" + w19 + "'" +
                ", w20='" + w20 + "'" +
                ", w21='" + w21 + "'" +
                ", w22='" + w22 + "'" +
                ", w23='" + w23 + "'" +
                ", w24='" + w24 + "'" +
                ", w25='" + w25 + "'" +
                ", w26='" + w26 + "'" +
                ", w27='" + w27 + "'" +
                ", w28='" + w28 + "'" +
                '}';
    }
}
