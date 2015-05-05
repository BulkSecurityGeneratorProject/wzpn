package com.example.wzpn.web.rest;

import com.example.wzpn.Application;
import com.example.wzpn.domain.Faktura;
import com.example.wzpn.repository.FakturaRepository;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the FakturaResource REST controller.
 *
 * @see FakturaResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class FakturaResourceTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    private static final String DEFAULT_NAZWA = "SAMPLE_TEXT";
    private static final String UPDATED_NAZWA = "UPDATED_TEXT";

    private static final DateTime DEFAULT_DATA_WYSTAWIENIA = new DateTime(0L, DateTimeZone.UTC);
    private static final DateTime UPDATED_DATA_WYSTAWIENIA = new DateTime(DateTimeZone.UTC).withMillisOfSecond(0);
    private static final String DEFAULT_DATA_WYSTAWIENIA_STR = dateTimeFormatter.print(DEFAULT_DATA_WYSTAWIENIA);

    private static final DateTime DEFAULT_DATA_SPRZEDAZY = new DateTime(0L, DateTimeZone.UTC);
    private static final DateTime UPDATED_DATA_SPRZEDAZY = new DateTime(DateTimeZone.UTC).withMillisOfSecond(0);
    private static final String DEFAULT_DATA_SPRZEDAZY_STR = dateTimeFormatter.print(DEFAULT_DATA_SPRZEDAZY);
    private static final String DEFAULT_MIEJSCOWOSC = "SAMPLE_TEXT";
    private static final String UPDATED_MIEJSCOWOSC = "UPDATED_TEXT";

    @Inject
    private FakturaRepository fakturaRepository;

    private MockMvc restFakturaMockMvc;

    private Faktura faktura;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FakturaResource fakturaResource = new FakturaResource();
        ReflectionTestUtils.setField(fakturaResource, "fakturaRepository", fakturaRepository);
        this.restFakturaMockMvc = MockMvcBuilders.standaloneSetup(fakturaResource).build();
    }

    @Before
    public void initTest() {
        faktura = new Faktura();
        faktura.setNazwa(DEFAULT_NAZWA);
        faktura.setDataWystawienia(DEFAULT_DATA_WYSTAWIENIA);
        faktura.setDataSprzedazy(DEFAULT_DATA_SPRZEDAZY);
        faktura.setMiejscowosc(DEFAULT_MIEJSCOWOSC);
    }

    @Test
    @Transactional
    public void createFaktura() throws Exception {
        int databaseSizeBeforeCreate = fakturaRepository.findAll().size();

        // Create the Faktura
        restFakturaMockMvc.perform(post("/api/fakturas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(faktura)))
                .andExpect(status().isCreated());

        // Validate the Faktura in the database
        List<Faktura> fakturas = fakturaRepository.findAll();
        assertThat(fakturas).hasSize(databaseSizeBeforeCreate + 1);
        Faktura testFaktura = fakturas.get(fakturas.size() - 1);
        assertThat(testFaktura.getNazwa()).isEqualTo(DEFAULT_NAZWA);
        assertThat(testFaktura.getDataWystawienia().toDateTime(DateTimeZone.UTC)).isEqualTo(DEFAULT_DATA_WYSTAWIENIA);
        assertThat(testFaktura.getDataSprzedazy().toDateTime(DateTimeZone.UTC)).isEqualTo(DEFAULT_DATA_SPRZEDAZY);
        assertThat(testFaktura.getMiejscowosc()).isEqualTo(DEFAULT_MIEJSCOWOSC);
    }

    @Test
    @Transactional
    public void getAllFakturas() throws Exception {
        // Initialize the database
        fakturaRepository.saveAndFlush(faktura);

        // Get all the fakturas
        restFakturaMockMvc.perform(get("/api/fakturas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(faktura.getId().intValue())))
                .andExpect(jsonPath("$.[*].nazwa").value(hasItem(DEFAULT_NAZWA.toString())))
                .andExpect(jsonPath("$.[*].dataWystawienia").value(hasItem(DEFAULT_DATA_WYSTAWIENIA_STR)))
                .andExpect(jsonPath("$.[*].dataSprzedazy").value(hasItem(DEFAULT_DATA_SPRZEDAZY_STR)))
                .andExpect(jsonPath("$.[*].miejscowosc").value(hasItem(DEFAULT_MIEJSCOWOSC.toString())));
    }

    @Test
    @Transactional
    public void getFaktura() throws Exception {
        // Initialize the database
        fakturaRepository.saveAndFlush(faktura);

        // Get the faktura
        restFakturaMockMvc.perform(get("/api/fakturas/{id}", faktura.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(faktura.getId().intValue()))
            .andExpect(jsonPath("$.nazwa").value(DEFAULT_NAZWA.toString()))
            .andExpect(jsonPath("$.dataWystawienia").value(DEFAULT_DATA_WYSTAWIENIA_STR))
            .andExpect(jsonPath("$.dataSprzedazy").value(DEFAULT_DATA_SPRZEDAZY_STR))
            .andExpect(jsonPath("$.miejscowosc").value(DEFAULT_MIEJSCOWOSC.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFaktura() throws Exception {
        // Get the faktura
        restFakturaMockMvc.perform(get("/api/fakturas/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFaktura() throws Exception {
        // Initialize the database
        fakturaRepository.saveAndFlush(faktura);
		
		int databaseSizeBeforeUpdate = fakturaRepository.findAll().size();

        // Update the faktura
        faktura.setNazwa(UPDATED_NAZWA);
        faktura.setDataWystawienia(UPDATED_DATA_WYSTAWIENIA);
        faktura.setDataSprzedazy(UPDATED_DATA_SPRZEDAZY);
        faktura.setMiejscowosc(UPDATED_MIEJSCOWOSC);
        restFakturaMockMvc.perform(put("/api/fakturas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(faktura)))
                .andExpect(status().isOk());

        // Validate the Faktura in the database
        List<Faktura> fakturas = fakturaRepository.findAll();
        assertThat(fakturas).hasSize(databaseSizeBeforeUpdate);
        Faktura testFaktura = fakturas.get(fakturas.size() - 1);
        assertThat(testFaktura.getNazwa()).isEqualTo(UPDATED_NAZWA);
        assertThat(testFaktura.getDataWystawienia().toDateTime(DateTimeZone.UTC)).isEqualTo(UPDATED_DATA_WYSTAWIENIA);
        assertThat(testFaktura.getDataSprzedazy().toDateTime(DateTimeZone.UTC)).isEqualTo(UPDATED_DATA_SPRZEDAZY);
        assertThat(testFaktura.getMiejscowosc()).isEqualTo(UPDATED_MIEJSCOWOSC);
    }

    @Test
    @Transactional
    public void deleteFaktura() throws Exception {
        // Initialize the database
        fakturaRepository.saveAndFlush(faktura);
		
		int databaseSizeBeforeDelete = fakturaRepository.findAll().size();

        // Get the faktura
        restFakturaMockMvc.perform(delete("/api/fakturas/{id}", faktura.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Faktura> fakturas = fakturaRepository.findAll();
        assertThat(fakturas).hasSize(databaseSizeBeforeDelete - 1);
    }
}
