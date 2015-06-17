package com.example.wzpn.web.rest;

import com.example.wzpn.Application;
import com.example.wzpn.domain.Sprawozdanie;
import com.example.wzpn.repository.SprawozdanieRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the SprawozdanieResource REST controller.
 *
 * @see SprawozdanieResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class SprawozdanieResourceTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");


    private static final BigDecimal DEFAULT_A1 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_A1 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_A2 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_A2 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_A3 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_A3 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_A4 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_A4 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_A5 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_A5 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_A6 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_A6 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_A7 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_A7 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_A8 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_A8 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_A9 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_A9 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_A10 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_A10 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_P1 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_P1 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_P2 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_P2 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_P3 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_P3 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_P4 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_P4 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_P5 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_P5 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_P6 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_P6 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_P7 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_P7 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_P8 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_P8 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_P9 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_P9 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_P10 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_P10 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_P11 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_P11 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_P12 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_P12 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_P13 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_P13 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_P14 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_P14 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_P15 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_P15 = BigDecimal.ONE;

    private static final Integer DEFAULT_ROK = 0;
    private static final Integer UPDATED_ROK = 1;

    private static final DateTime DEFAULT_DZIEN_SPORZADZENIA = new DateTime(0L, DateTimeZone.UTC);
    private static final DateTime UPDATED_DZIEN_SPORZADZENIA = new DateTime(DateTimeZone.UTC).withMillisOfSecond(0);
    private static final String DEFAULT_DZIEN_SPORZADZENIA_STR = dateTimeFormatter.print(DEFAULT_DZIEN_SPORZADZENIA);

    private static final BigDecimal DEFAULT_W1 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_W1 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_W2 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_W2 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_W3 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_W3 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_W4 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_W4 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_W5 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_W5 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_W6 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_W6 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_W7 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_W7 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_W8 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_W8 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_W9 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_W9 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_W10 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_W10 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_W11 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_W11 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_W12 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_W12 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_W13 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_W13 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_W14 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_W14 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_W15 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_W15 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_W16 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_W16 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_W17 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_W17 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_W18 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_W18 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_W19 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_W19 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_W20 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_W20 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_W21 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_W21 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_W22 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_W22 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_W23 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_W23 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_W24 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_W24 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_W25 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_W25 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_W26 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_W26 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_W27 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_W27 = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_W28 = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_W28 = BigDecimal.ONE;

    @Inject
    private SprawozdanieRepository sprawozdanieRepository;

    private MockMvc restSprawozdanieMockMvc;

    private Sprawozdanie sprawozdanie;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SprawozdanieResource sprawozdanieResource = new SprawozdanieResource();
        ReflectionTestUtils.setField(sprawozdanieResource, "sprawozdanieRepository", sprawozdanieRepository);
        this.restSprawozdanieMockMvc = MockMvcBuilders.standaloneSetup(sprawozdanieResource).build();
    }

    @Before
    public void initTest() {
        sprawozdanie = new Sprawozdanie();
        sprawozdanie.seta1(DEFAULT_A1);
        sprawozdanie.seta2(DEFAULT_A2);
        sprawozdanie.seta3(DEFAULT_A3);
        sprawozdanie.seta4(DEFAULT_A4);
        sprawozdanie.seta5(DEFAULT_A5);
        sprawozdanie.seta6(DEFAULT_A6);
        sprawozdanie.seta7(DEFAULT_A7);
        sprawozdanie.seta8(DEFAULT_A8);
        sprawozdanie.seta9(DEFAULT_A9);
        sprawozdanie.seta10(DEFAULT_A10);
        sprawozdanie.setp1(DEFAULT_P1);
        sprawozdanie.setp2(DEFAULT_P2);
        sprawozdanie.setp3(DEFAULT_P3);
        sprawozdanie.setp4(DEFAULT_P4);
        sprawozdanie.setp5(DEFAULT_P5);
        sprawozdanie.setp6(DEFAULT_P6);
        sprawozdanie.setp7(DEFAULT_P7);
        sprawozdanie.setp8(DEFAULT_P8);
        sprawozdanie.setp9(DEFAULT_P9);
        sprawozdanie.setp10(DEFAULT_P10);
        sprawozdanie.setp11(DEFAULT_P11);
        sprawozdanie.setp12(DEFAULT_P12);
        sprawozdanie.setp13(DEFAULT_P13);
        sprawozdanie.setp14(DEFAULT_P14);
        sprawozdanie.setp15(DEFAULT_P15);
        sprawozdanie.setRok(DEFAULT_ROK);
        sprawozdanie.setDzienSporzadzenia(DEFAULT_DZIEN_SPORZADZENIA);
        sprawozdanie.setw1(DEFAULT_W1);
        sprawozdanie.setw2(DEFAULT_W2);
        sprawozdanie.setw3(DEFAULT_W3);
        sprawozdanie.setw4(DEFAULT_W4);
        sprawozdanie.setw5(DEFAULT_W5);
        sprawozdanie.setw6(DEFAULT_W6);
        sprawozdanie.setw7(DEFAULT_W7);
        sprawozdanie.setw8(DEFAULT_W8);
        sprawozdanie.setw9(DEFAULT_W9);
        sprawozdanie.setw10(DEFAULT_W10);
        sprawozdanie.setw11(DEFAULT_W11);
        sprawozdanie.setw12(DEFAULT_W12);
        sprawozdanie.setw13(DEFAULT_W13);
        sprawozdanie.setw14(DEFAULT_W14);
        sprawozdanie.setw15(DEFAULT_W15);
        sprawozdanie.setw16(DEFAULT_W16);
        sprawozdanie.setw17(DEFAULT_W17);
        sprawozdanie.setw18(DEFAULT_W18);
        sprawozdanie.setw19(DEFAULT_W19);
        sprawozdanie.setw20(DEFAULT_W20);
        sprawozdanie.setw21(DEFAULT_W21);
        sprawozdanie.setw22(DEFAULT_W22);
        sprawozdanie.setw23(DEFAULT_W23);
        sprawozdanie.setw24(DEFAULT_W24);
        sprawozdanie.setw25(DEFAULT_W25);
        sprawozdanie.setw26(DEFAULT_W26);
        sprawozdanie.setw27(DEFAULT_W27);
        sprawozdanie.setw28(DEFAULT_W28);
    }

    @Test
    @Transactional
    public void createSprawozdanie() throws Exception {
        int databaseSizeBeforeCreate = sprawozdanieRepository.findAll().size();

        // Create the Sprawozdanie
        restSprawozdanieMockMvc.perform(post("/api/sprawozdanies")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(sprawozdanie)))
                .andExpect(status().isCreated());

        // Validate the Sprawozdanie in the database
        List<Sprawozdanie> sprawozdanies = sprawozdanieRepository.findAll();
        assertThat(sprawozdanies).hasSize(databaseSizeBeforeCreate + 1);
        Sprawozdanie testSprawozdanie = sprawozdanies.get(sprawozdanies.size() - 1);
        assertThat(testSprawozdanie.geta1()).isEqualTo(DEFAULT_A1);
        assertThat(testSprawozdanie.geta2()).isEqualTo(DEFAULT_A2);
        assertThat(testSprawozdanie.geta3()).isEqualTo(DEFAULT_A3);
        assertThat(testSprawozdanie.geta4()).isEqualTo(DEFAULT_A4);
        assertThat(testSprawozdanie.geta5()).isEqualTo(DEFAULT_A5);
        assertThat(testSprawozdanie.geta6()).isEqualTo(DEFAULT_A6);
        assertThat(testSprawozdanie.geta7()).isEqualTo(DEFAULT_A7);
        assertThat(testSprawozdanie.geta8()).isEqualTo(DEFAULT_A8);
        assertThat(testSprawozdanie.geta9()).isEqualTo(DEFAULT_A9);
        assertThat(testSprawozdanie.geta10()).isEqualTo(DEFAULT_A10);
        assertThat(testSprawozdanie.getp1()).isEqualTo(DEFAULT_P1);
        assertThat(testSprawozdanie.getp2()).isEqualTo(DEFAULT_P2);
        assertThat(testSprawozdanie.getp3()).isEqualTo(DEFAULT_P3);
        assertThat(testSprawozdanie.getp4()).isEqualTo(DEFAULT_P4);
        assertThat(testSprawozdanie.getp5()).isEqualTo(DEFAULT_P5);
        assertThat(testSprawozdanie.getp6()).isEqualTo(DEFAULT_P6);
        assertThat(testSprawozdanie.getp7()).isEqualTo(DEFAULT_P7);
        assertThat(testSprawozdanie.getp8()).isEqualTo(DEFAULT_P8);
        assertThat(testSprawozdanie.getp9()).isEqualTo(DEFAULT_P9);
        assertThat(testSprawozdanie.getp10()).isEqualTo(DEFAULT_P10);
        assertThat(testSprawozdanie.getp11()).isEqualTo(DEFAULT_P11);
        assertThat(testSprawozdanie.getp12()).isEqualTo(DEFAULT_P12);
        assertThat(testSprawozdanie.getp13()).isEqualTo(DEFAULT_P13);
        assertThat(testSprawozdanie.getp14()).isEqualTo(DEFAULT_P14);
        assertThat(testSprawozdanie.getp15()).isEqualTo(DEFAULT_P15);
        assertThat(testSprawozdanie.getRok()).isEqualTo(DEFAULT_ROK);
        assertThat(testSprawozdanie.getDzienSporzadzenia().toDateTime(DateTimeZone.UTC)).isEqualTo(DEFAULT_DZIEN_SPORZADZENIA);
        assertThat(testSprawozdanie.getw1()).isEqualTo(DEFAULT_W1);
        assertThat(testSprawozdanie.getw2()).isEqualTo(DEFAULT_W2);
        assertThat(testSprawozdanie.getw3()).isEqualTo(DEFAULT_W3);
        assertThat(testSprawozdanie.getw4()).isEqualTo(DEFAULT_W4);
        assertThat(testSprawozdanie.getw5()).isEqualTo(DEFAULT_W5);
        assertThat(testSprawozdanie.getw6()).isEqualTo(DEFAULT_W6);
        assertThat(testSprawozdanie.getw7()).isEqualTo(DEFAULT_W7);
        assertThat(testSprawozdanie.getw8()).isEqualTo(DEFAULT_W8);
        assertThat(testSprawozdanie.getw9()).isEqualTo(DEFAULT_W9);
        assertThat(testSprawozdanie.getw10()).isEqualTo(DEFAULT_W10);
        assertThat(testSprawozdanie.getw11()).isEqualTo(DEFAULT_W11);
        assertThat(testSprawozdanie.getw12()).isEqualTo(DEFAULT_W12);
        assertThat(testSprawozdanie.getw13()).isEqualTo(DEFAULT_W13);
        assertThat(testSprawozdanie.getw14()).isEqualTo(DEFAULT_W14);
        assertThat(testSprawozdanie.getw15()).isEqualTo(DEFAULT_W15);
        assertThat(testSprawozdanie.getw16()).isEqualTo(DEFAULT_W16);
        assertThat(testSprawozdanie.getw17()).isEqualTo(DEFAULT_W17);
        assertThat(testSprawozdanie.getw18()).isEqualTo(DEFAULT_W18);
        assertThat(testSprawozdanie.getw19()).isEqualTo(DEFAULT_W19);
        assertThat(testSprawozdanie.getw20()).isEqualTo(DEFAULT_W20);
        assertThat(testSprawozdanie.getw21()).isEqualTo(DEFAULT_W21);
        assertThat(testSprawozdanie.getw22()).isEqualTo(DEFAULT_W22);
        assertThat(testSprawozdanie.getw23()).isEqualTo(DEFAULT_W23);
        assertThat(testSprawozdanie.getw24()).isEqualTo(DEFAULT_W24);
        assertThat(testSprawozdanie.getw25()).isEqualTo(DEFAULT_W25);
        assertThat(testSprawozdanie.getw26()).isEqualTo(DEFAULT_W26);
        assertThat(testSprawozdanie.getw27()).isEqualTo(DEFAULT_W27);
        assertThat(testSprawozdanie.getw28()).isEqualTo(DEFAULT_W28);
    }

    @Test
    @Transactional
    public void getAllSprawozdanies() throws Exception {
        // Initialize the database
        sprawozdanieRepository.saveAndFlush(sprawozdanie);

        // Get all the sprawozdanies
        restSprawozdanieMockMvc.perform(get("/api/sprawozdanies"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(sprawozdanie.getId().intValue())))
                .andExpect(jsonPath("$.[*].a1").value(hasItem(DEFAULT_A1.intValue())))
                .andExpect(jsonPath("$.[*].a2").value(hasItem(DEFAULT_A2.intValue())))
                .andExpect(jsonPath("$.[*].a3").value(hasItem(DEFAULT_A3.intValue())))
                .andExpect(jsonPath("$.[*].a4").value(hasItem(DEFAULT_A4.intValue())))
                .andExpect(jsonPath("$.[*].a5").value(hasItem(DEFAULT_A5.intValue())))
                .andExpect(jsonPath("$.[*].a6").value(hasItem(DEFAULT_A6.intValue())))
                .andExpect(jsonPath("$.[*].a7").value(hasItem(DEFAULT_A7.intValue())))
                .andExpect(jsonPath("$.[*].a8").value(hasItem(DEFAULT_A8.intValue())))
                .andExpect(jsonPath("$.[*].a9").value(hasItem(DEFAULT_A9.intValue())))
                .andExpect(jsonPath("$.[*].a10").value(hasItem(DEFAULT_A10.intValue())))
                .andExpect(jsonPath("$.[*].p1").value(hasItem(DEFAULT_P1.intValue())))
                .andExpect(jsonPath("$.[*].p2").value(hasItem(DEFAULT_P2.intValue())))
                .andExpect(jsonPath("$.[*].p3").value(hasItem(DEFAULT_P3.intValue())))
                .andExpect(jsonPath("$.[*].p4").value(hasItem(DEFAULT_P4.intValue())))
                .andExpect(jsonPath("$.[*].p5").value(hasItem(DEFAULT_P5.intValue())))
                .andExpect(jsonPath("$.[*].p6").value(hasItem(DEFAULT_P6.intValue())))
                .andExpect(jsonPath("$.[*].p7").value(hasItem(DEFAULT_P7.intValue())))
                .andExpect(jsonPath("$.[*].p8").value(hasItem(DEFAULT_P8.intValue())))
                .andExpect(jsonPath("$.[*].p9").value(hasItem(DEFAULT_P9.intValue())))
                .andExpect(jsonPath("$.[*].p10").value(hasItem(DEFAULT_P10.intValue())))
                .andExpect(jsonPath("$.[*].p11").value(hasItem(DEFAULT_P11.intValue())))
                .andExpect(jsonPath("$.[*].p12").value(hasItem(DEFAULT_P12.intValue())))
                .andExpect(jsonPath("$.[*].p13").value(hasItem(DEFAULT_P13.intValue())))
                .andExpect(jsonPath("$.[*].p14").value(hasItem(DEFAULT_P14.intValue())))
                .andExpect(jsonPath("$.[*].p15").value(hasItem(DEFAULT_P15.intValue())))
                .andExpect(jsonPath("$.[*].rok").value(hasItem(DEFAULT_ROK)))
                .andExpect(jsonPath("$.[*].dzienSporzadzenia").value(hasItem(DEFAULT_DZIEN_SPORZADZENIA_STR)))
                .andExpect(jsonPath("$.[*].w1").value(hasItem(DEFAULT_W1.intValue())))
                .andExpect(jsonPath("$.[*].w2").value(hasItem(DEFAULT_W2.intValue())))
                .andExpect(jsonPath("$.[*].w3").value(hasItem(DEFAULT_W3.intValue())))
                .andExpect(jsonPath("$.[*].w4").value(hasItem(DEFAULT_W4.intValue())))
                .andExpect(jsonPath("$.[*].w5").value(hasItem(DEFAULT_W5.intValue())))
                .andExpect(jsonPath("$.[*].w6").value(hasItem(DEFAULT_W6.intValue())))
                .andExpect(jsonPath("$.[*].w7").value(hasItem(DEFAULT_W7.intValue())))
                .andExpect(jsonPath("$.[*].w8").value(hasItem(DEFAULT_W8.intValue())))
                .andExpect(jsonPath("$.[*].w9").value(hasItem(DEFAULT_W9.intValue())))
                .andExpect(jsonPath("$.[*].w10").value(hasItem(DEFAULT_W10.intValue())))
                .andExpect(jsonPath("$.[*].w11").value(hasItem(DEFAULT_W11.intValue())))
                .andExpect(jsonPath("$.[*].w12").value(hasItem(DEFAULT_W12.intValue())))
                .andExpect(jsonPath("$.[*].w13").value(hasItem(DEFAULT_W13.intValue())))
                .andExpect(jsonPath("$.[*].w14").value(hasItem(DEFAULT_W14.intValue())))
                .andExpect(jsonPath("$.[*].w15").value(hasItem(DEFAULT_W15.intValue())))
                .andExpect(jsonPath("$.[*].w16").value(hasItem(DEFAULT_W16.intValue())))
                .andExpect(jsonPath("$.[*].w17").value(hasItem(DEFAULT_W17.intValue())))
                .andExpect(jsonPath("$.[*].w18").value(hasItem(DEFAULT_W18.intValue())))
                .andExpect(jsonPath("$.[*].w19").value(hasItem(DEFAULT_W19.intValue())))
                .andExpect(jsonPath("$.[*].w20").value(hasItem(DEFAULT_W20.intValue())))
                .andExpect(jsonPath("$.[*].w21").value(hasItem(DEFAULT_W21.intValue())))
                .andExpect(jsonPath("$.[*].w22").value(hasItem(DEFAULT_W22.intValue())))
                .andExpect(jsonPath("$.[*].w23").value(hasItem(DEFAULT_W23.intValue())))
                .andExpect(jsonPath("$.[*].w24").value(hasItem(DEFAULT_W24.intValue())))
                .andExpect(jsonPath("$.[*].w25").value(hasItem(DEFAULT_W25.intValue())))
                .andExpect(jsonPath("$.[*].w26").value(hasItem(DEFAULT_W26.intValue())))
                .andExpect(jsonPath("$.[*].w27").value(hasItem(DEFAULT_W27.intValue())))
                .andExpect(jsonPath("$.[*].w28").value(hasItem(DEFAULT_W28.intValue())));
    }

    @Test
    @Transactional
    public void getSprawozdanie() throws Exception {
        // Initialize the database
        sprawozdanieRepository.saveAndFlush(sprawozdanie);

        // Get the sprawozdanie
        restSprawozdanieMockMvc.perform(get("/api/sprawozdanies/{id}", sprawozdanie.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(sprawozdanie.getId().intValue()))
            .andExpect(jsonPath("$.a1").value(DEFAULT_A1.intValue()))
            .andExpect(jsonPath("$.a2").value(DEFAULT_A2.intValue()))
            .andExpect(jsonPath("$.a3").value(DEFAULT_A3.intValue()))
            .andExpect(jsonPath("$.a4").value(DEFAULT_A4.intValue()))
            .andExpect(jsonPath("$.a5").value(DEFAULT_A5.intValue()))
            .andExpect(jsonPath("$.a6").value(DEFAULT_A6.intValue()))
            .andExpect(jsonPath("$.a7").value(DEFAULT_A7.intValue()))
            .andExpect(jsonPath("$.a8").value(DEFAULT_A8.intValue()))
            .andExpect(jsonPath("$.a9").value(DEFAULT_A9.intValue()))
            .andExpect(jsonPath("$.a10").value(DEFAULT_A10.intValue()))
            .andExpect(jsonPath("$.p1").value(DEFAULT_P1.intValue()))
            .andExpect(jsonPath("$.p2").value(DEFAULT_P2.intValue()))
            .andExpect(jsonPath("$.p3").value(DEFAULT_P3.intValue()))
            .andExpect(jsonPath("$.p4").value(DEFAULT_P4.intValue()))
            .andExpect(jsonPath("$.p5").value(DEFAULT_P5.intValue()))
            .andExpect(jsonPath("$.p6").value(DEFAULT_P6.intValue()))
            .andExpect(jsonPath("$.p7").value(DEFAULT_P7.intValue()))
            .andExpect(jsonPath("$.p8").value(DEFAULT_P8.intValue()))
            .andExpect(jsonPath("$.p9").value(DEFAULT_P9.intValue()))
            .andExpect(jsonPath("$.p10").value(DEFAULT_P10.intValue()))
            .andExpect(jsonPath("$.p11").value(DEFAULT_P11.intValue()))
            .andExpect(jsonPath("$.p12").value(DEFAULT_P12.intValue()))
            .andExpect(jsonPath("$.p13").value(DEFAULT_P13.intValue()))
            .andExpect(jsonPath("$.p14").value(DEFAULT_P14.intValue()))
            .andExpect(jsonPath("$.p15").value(DEFAULT_P15.intValue()))
            .andExpect(jsonPath("$.rok").value(DEFAULT_ROK))
            .andExpect(jsonPath("$.dzienSporzadzenia").value(DEFAULT_DZIEN_SPORZADZENIA_STR))
            .andExpect(jsonPath("$.w1").value(DEFAULT_W1.intValue()))
            .andExpect(jsonPath("$.w2").value(DEFAULT_W2.intValue()))
            .andExpect(jsonPath("$.w3").value(DEFAULT_W3.intValue()))
            .andExpect(jsonPath("$.w4").value(DEFAULT_W4.intValue()))
            .andExpect(jsonPath("$.w5").value(DEFAULT_W5.intValue()))
            .andExpect(jsonPath("$.w6").value(DEFAULT_W6.intValue()))
            .andExpect(jsonPath("$.w7").value(DEFAULT_W7.intValue()))
            .andExpect(jsonPath("$.w8").value(DEFAULT_W8.intValue()))
            .andExpect(jsonPath("$.w9").value(DEFAULT_W9.intValue()))
            .andExpect(jsonPath("$.w10").value(DEFAULT_W10.intValue()))
            .andExpect(jsonPath("$.w11").value(DEFAULT_W11.intValue()))
            .andExpect(jsonPath("$.w12").value(DEFAULT_W12.intValue()))
            .andExpect(jsonPath("$.w13").value(DEFAULT_W13.intValue()))
            .andExpect(jsonPath("$.w14").value(DEFAULT_W14.intValue()))
            .andExpect(jsonPath("$.w15").value(DEFAULT_W15.intValue()))
            .andExpect(jsonPath("$.w16").value(DEFAULT_W16.intValue()))
            .andExpect(jsonPath("$.w17").value(DEFAULT_W17.intValue()))
            .andExpect(jsonPath("$.w18").value(DEFAULT_W18.intValue()))
            .andExpect(jsonPath("$.w19").value(DEFAULT_W19.intValue()))
            .andExpect(jsonPath("$.w20").value(DEFAULT_W20.intValue()))
            .andExpect(jsonPath("$.w21").value(DEFAULT_W21.intValue()))
            .andExpect(jsonPath("$.w22").value(DEFAULT_W22.intValue()))
            .andExpect(jsonPath("$.w23").value(DEFAULT_W23.intValue()))
            .andExpect(jsonPath("$.w24").value(DEFAULT_W24.intValue()))
            .andExpect(jsonPath("$.w25").value(DEFAULT_W25.intValue()))
            .andExpect(jsonPath("$.w26").value(DEFAULT_W26.intValue()))
            .andExpect(jsonPath("$.w27").value(DEFAULT_W27.intValue()))
            .andExpect(jsonPath("$.w28").value(DEFAULT_W28.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSprawozdanie() throws Exception {
        // Get the sprawozdanie
        restSprawozdanieMockMvc.perform(get("/api/sprawozdanies/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSprawozdanie() throws Exception {
        // Initialize the database
        sprawozdanieRepository.saveAndFlush(sprawozdanie);
		
		int databaseSizeBeforeUpdate = sprawozdanieRepository.findAll().size();

        // Update the sprawozdanie
        sprawozdanie.seta1(UPDATED_A1);
        sprawozdanie.seta2(UPDATED_A2);
        sprawozdanie.seta3(UPDATED_A3);
        sprawozdanie.seta4(UPDATED_A4);
        sprawozdanie.seta5(UPDATED_A5);
        sprawozdanie.seta6(UPDATED_A6);
        sprawozdanie.seta7(UPDATED_A7);
        sprawozdanie.seta8(UPDATED_A8);
        sprawozdanie.seta9(UPDATED_A9);
        sprawozdanie.seta10(UPDATED_A10);
        sprawozdanie.setp1(UPDATED_P1);
        sprawozdanie.setp2(UPDATED_P2);
        sprawozdanie.setp3(UPDATED_P3);
        sprawozdanie.setp4(UPDATED_P4);
        sprawozdanie.setp5(UPDATED_P5);
        sprawozdanie.setp6(UPDATED_P6);
        sprawozdanie.setp7(UPDATED_P7);
        sprawozdanie.setp8(UPDATED_P8);
        sprawozdanie.setp9(UPDATED_P9);
        sprawozdanie.setp10(UPDATED_P10);
        sprawozdanie.setp11(UPDATED_P11);
        sprawozdanie.setp12(UPDATED_P12);
        sprawozdanie.setp13(UPDATED_P13);
        sprawozdanie.setp14(UPDATED_P14);
        sprawozdanie.setp15(UPDATED_P15);
        sprawozdanie.setRok(UPDATED_ROK);
        sprawozdanie.setDzienSporzadzenia(UPDATED_DZIEN_SPORZADZENIA);
        sprawozdanie.setw1(UPDATED_W1);
        sprawozdanie.setw2(UPDATED_W2);
        sprawozdanie.setw3(UPDATED_W3);
        sprawozdanie.setw4(UPDATED_W4);
        sprawozdanie.setw5(UPDATED_W5);
        sprawozdanie.setw6(UPDATED_W6);
        sprawozdanie.setw7(UPDATED_W7);
        sprawozdanie.setw8(UPDATED_W8);
        sprawozdanie.setw9(UPDATED_W9);
        sprawozdanie.setw10(UPDATED_W10);
        sprawozdanie.setw11(UPDATED_W11);
        sprawozdanie.setw12(UPDATED_W12);
        sprawozdanie.setw13(UPDATED_W13);
        sprawozdanie.setw14(UPDATED_W14);
        sprawozdanie.setw15(UPDATED_W15);
        sprawozdanie.setw16(UPDATED_W16);
        sprawozdanie.setw17(UPDATED_W17);
        sprawozdanie.setw18(UPDATED_W18);
        sprawozdanie.setw19(UPDATED_W19);
        sprawozdanie.setw20(UPDATED_W20);
        sprawozdanie.setw21(UPDATED_W21);
        sprawozdanie.setw22(UPDATED_W22);
        sprawozdanie.setw23(UPDATED_W23);
        sprawozdanie.setw24(UPDATED_W24);
        sprawozdanie.setw25(UPDATED_W25);
        sprawozdanie.setw26(UPDATED_W26);
        sprawozdanie.setw27(UPDATED_W27);
        sprawozdanie.setw28(UPDATED_W28);
        restSprawozdanieMockMvc.perform(put("/api/sprawozdanies")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(sprawozdanie)))
                .andExpect(status().isOk());

        // Validate the Sprawozdanie in the database
        List<Sprawozdanie> sprawozdanies = sprawozdanieRepository.findAll();
        assertThat(sprawozdanies).hasSize(databaseSizeBeforeUpdate);
        Sprawozdanie testSprawozdanie = sprawozdanies.get(sprawozdanies.size() - 1);
        assertThat(testSprawozdanie.geta1()).isEqualTo(UPDATED_A1);
        assertThat(testSprawozdanie.geta2()).isEqualTo(UPDATED_A2);
        assertThat(testSprawozdanie.geta3()).isEqualTo(UPDATED_A3);
        assertThat(testSprawozdanie.geta4()).isEqualTo(UPDATED_A4);
        assertThat(testSprawozdanie.geta5()).isEqualTo(UPDATED_A5);
        assertThat(testSprawozdanie.geta6()).isEqualTo(UPDATED_A6);
        assertThat(testSprawozdanie.geta7()).isEqualTo(UPDATED_A7);
        assertThat(testSprawozdanie.geta8()).isEqualTo(UPDATED_A8);
        assertThat(testSprawozdanie.geta9()).isEqualTo(UPDATED_A9);
        assertThat(testSprawozdanie.geta10()).isEqualTo(UPDATED_A10);
        assertThat(testSprawozdanie.getp1()).isEqualTo(UPDATED_P1);
        assertThat(testSprawozdanie.getp2()).isEqualTo(UPDATED_P2);
        assertThat(testSprawozdanie.getp3()).isEqualTo(UPDATED_P3);
        assertThat(testSprawozdanie.getp4()).isEqualTo(UPDATED_P4);
        assertThat(testSprawozdanie.getp5()).isEqualTo(UPDATED_P5);
        assertThat(testSprawozdanie.getp6()).isEqualTo(UPDATED_P6);
        assertThat(testSprawozdanie.getp7()).isEqualTo(UPDATED_P7);
        assertThat(testSprawozdanie.getp8()).isEqualTo(UPDATED_P8);
        assertThat(testSprawozdanie.getp9()).isEqualTo(UPDATED_P9);
        assertThat(testSprawozdanie.getp10()).isEqualTo(UPDATED_P10);
        assertThat(testSprawozdanie.getp11()).isEqualTo(UPDATED_P11);
        assertThat(testSprawozdanie.getp12()).isEqualTo(UPDATED_P12);
        assertThat(testSprawozdanie.getp13()).isEqualTo(UPDATED_P13);
        assertThat(testSprawozdanie.getp14()).isEqualTo(UPDATED_P14);
        assertThat(testSprawozdanie.getp15()).isEqualTo(UPDATED_P15);
        assertThat(testSprawozdanie.getRok()).isEqualTo(UPDATED_ROK);
        assertThat(testSprawozdanie.getDzienSporzadzenia().toDateTime(DateTimeZone.UTC)).isEqualTo(UPDATED_DZIEN_SPORZADZENIA);
        assertThat(testSprawozdanie.getw1()).isEqualTo(UPDATED_W1);
        assertThat(testSprawozdanie.getw2()).isEqualTo(UPDATED_W2);
        assertThat(testSprawozdanie.getw3()).isEqualTo(UPDATED_W3);
        assertThat(testSprawozdanie.getw4()).isEqualTo(UPDATED_W4);
        assertThat(testSprawozdanie.getw5()).isEqualTo(UPDATED_W5);
        assertThat(testSprawozdanie.getw6()).isEqualTo(UPDATED_W6);
        assertThat(testSprawozdanie.getw7()).isEqualTo(UPDATED_W7);
        assertThat(testSprawozdanie.getw8()).isEqualTo(UPDATED_W8);
        assertThat(testSprawozdanie.getw9()).isEqualTo(UPDATED_W9);
        assertThat(testSprawozdanie.getw10()).isEqualTo(UPDATED_W10);
        assertThat(testSprawozdanie.getw11()).isEqualTo(UPDATED_W11);
        assertThat(testSprawozdanie.getw12()).isEqualTo(UPDATED_W12);
        assertThat(testSprawozdanie.getw13()).isEqualTo(UPDATED_W13);
        assertThat(testSprawozdanie.getw14()).isEqualTo(UPDATED_W14);
        assertThat(testSprawozdanie.getw15()).isEqualTo(UPDATED_W15);
        assertThat(testSprawozdanie.getw16()).isEqualTo(UPDATED_W16);
        assertThat(testSprawozdanie.getw17()).isEqualTo(UPDATED_W17);
        assertThat(testSprawozdanie.getw18()).isEqualTo(UPDATED_W18);
        assertThat(testSprawozdanie.getw19()).isEqualTo(UPDATED_W19);
        assertThat(testSprawozdanie.getw20()).isEqualTo(UPDATED_W20);
        assertThat(testSprawozdanie.getw21()).isEqualTo(UPDATED_W21);
        assertThat(testSprawozdanie.getw22()).isEqualTo(UPDATED_W22);
        assertThat(testSprawozdanie.getw23()).isEqualTo(UPDATED_W23);
        assertThat(testSprawozdanie.getw24()).isEqualTo(UPDATED_W24);
        assertThat(testSprawozdanie.getw25()).isEqualTo(UPDATED_W25);
        assertThat(testSprawozdanie.getw26()).isEqualTo(UPDATED_W26);
        assertThat(testSprawozdanie.getw27()).isEqualTo(UPDATED_W27);
        assertThat(testSprawozdanie.getw28()).isEqualTo(UPDATED_W28);
    }

    @Test
    @Transactional
    public void deleteSprawozdanie() throws Exception {
        // Initialize the database
        sprawozdanieRepository.saveAndFlush(sprawozdanie);
		
		int databaseSizeBeforeDelete = sprawozdanieRepository.findAll().size();

        // Get the sprawozdanie
        restSprawozdanieMockMvc.perform(delete("/api/sprawozdanies/{id}", sprawozdanie.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Sprawozdanie> sprawozdanies = sprawozdanieRepository.findAll();
        assertThat(sprawozdanies).hasSize(databaseSizeBeforeDelete - 1);
    }
}
