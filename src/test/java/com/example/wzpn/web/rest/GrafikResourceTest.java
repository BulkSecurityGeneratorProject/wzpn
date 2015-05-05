package com.example.wzpn.web.rest;

import com.example.wzpn.Application;
import com.example.wzpn.domain.Grafik;
import com.example.wzpn.repository.GrafikRepository;

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
 * Test class for the GrafikResource REST controller.
 *
 * @see GrafikResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class GrafikResourceTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    private static final String DEFAULT_NAZWA = "SAMPLE_TEXT";
    private static final String UPDATED_NAZWA = "UPDATED_TEXT";
    private static final String DEFAULT_OPIS = "SAMPLE_TEXT";
    private static final String UPDATED_OPIS = "UPDATED_TEXT";

    private static final DateTime DEFAULT_ROZPOCZECIE = new DateTime(0L, DateTimeZone.UTC);
    private static final DateTime UPDATED_ROZPOCZECIE = new DateTime(DateTimeZone.UTC).withMillisOfSecond(0);
    private static final String DEFAULT_ROZPOCZECIE_STR = dateTimeFormatter.print(DEFAULT_ROZPOCZECIE);

    @Inject
    private GrafikRepository grafikRepository;

    private MockMvc restGrafikMockMvc;

    private Grafik grafik;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        GrafikResource grafikResource = new GrafikResource();
        ReflectionTestUtils.setField(grafikResource, "grafikRepository", grafikRepository);
        this.restGrafikMockMvc = MockMvcBuilders.standaloneSetup(grafikResource).build();
    }

    @Before
    public void initTest() {
        grafik = new Grafik();
        grafik.setNazwa(DEFAULT_NAZWA);
        grafik.setOpis(DEFAULT_OPIS);
        grafik.setRozpoczecie(DEFAULT_ROZPOCZECIE);
    }

    @Test
    @Transactional
    public void createGrafik() throws Exception {
        int databaseSizeBeforeCreate = grafikRepository.findAll().size();

        // Create the Grafik
        restGrafikMockMvc.perform(post("/api/grafiks")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(grafik)))
                .andExpect(status().isCreated());

        // Validate the Grafik in the database
        List<Grafik> grafiks = grafikRepository.findAll();
        assertThat(grafiks).hasSize(databaseSizeBeforeCreate + 1);
        Grafik testGrafik = grafiks.get(grafiks.size() - 1);
        assertThat(testGrafik.getNazwa()).isEqualTo(DEFAULT_NAZWA);
        assertThat(testGrafik.getOpis()).isEqualTo(DEFAULT_OPIS);
        assertThat(testGrafik.getRozpoczecie().toDateTime(DateTimeZone.UTC)).isEqualTo(DEFAULT_ROZPOCZECIE);
    }

    @Test
    @Transactional
    public void getAllGrafiks() throws Exception {
        // Initialize the database
        grafikRepository.saveAndFlush(grafik);

        // Get all the grafiks
        restGrafikMockMvc.perform(get("/api/grafiks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(grafik.getId().intValue())))
                .andExpect(jsonPath("$.[*].nazwa").value(hasItem(DEFAULT_NAZWA.toString())))
                .andExpect(jsonPath("$.[*].opis").value(hasItem(DEFAULT_OPIS.toString())))
                .andExpect(jsonPath("$.[*].rozpoczecie").value(hasItem(DEFAULT_ROZPOCZECIE_STR)));
    }

    @Test
    @Transactional
    public void getGrafik() throws Exception {
        // Initialize the database
        grafikRepository.saveAndFlush(grafik);

        // Get the grafik
        restGrafikMockMvc.perform(get("/api/grafiks/{id}", grafik.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(grafik.getId().intValue()))
            .andExpect(jsonPath("$.nazwa").value(DEFAULT_NAZWA.toString()))
            .andExpect(jsonPath("$.opis").value(DEFAULT_OPIS.toString()))
            .andExpect(jsonPath("$.rozpoczecie").value(DEFAULT_ROZPOCZECIE_STR));
    }

    @Test
    @Transactional
    public void getNonExistingGrafik() throws Exception {
        // Get the grafik
        restGrafikMockMvc.perform(get("/api/grafiks/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGrafik() throws Exception {
        // Initialize the database
        grafikRepository.saveAndFlush(grafik);
		
		int databaseSizeBeforeUpdate = grafikRepository.findAll().size();

        // Update the grafik
        grafik.setNazwa(UPDATED_NAZWA);
        grafik.setOpis(UPDATED_OPIS);
        grafik.setRozpoczecie(UPDATED_ROZPOCZECIE);
        restGrafikMockMvc.perform(put("/api/grafiks")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(grafik)))
                .andExpect(status().isOk());

        // Validate the Grafik in the database
        List<Grafik> grafiks = grafikRepository.findAll();
        assertThat(grafiks).hasSize(databaseSizeBeforeUpdate);
        Grafik testGrafik = grafiks.get(grafiks.size() - 1);
        assertThat(testGrafik.getNazwa()).isEqualTo(UPDATED_NAZWA);
        assertThat(testGrafik.getOpis()).isEqualTo(UPDATED_OPIS);
        assertThat(testGrafik.getRozpoczecie().toDateTime(DateTimeZone.UTC)).isEqualTo(UPDATED_ROZPOCZECIE);
    }

    @Test
    @Transactional
    public void deleteGrafik() throws Exception {
        // Initialize the database
        grafikRepository.saveAndFlush(grafik);
		
		int databaseSizeBeforeDelete = grafikRepository.findAll().size();

        // Get the grafik
        restGrafikMockMvc.perform(delete("/api/grafiks/{id}", grafik.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Grafik> grafiks = grafikRepository.findAll();
        assertThat(grafiks).hasSize(databaseSizeBeforeDelete - 1);
    }
}
