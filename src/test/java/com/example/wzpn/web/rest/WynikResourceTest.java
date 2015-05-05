package com.example.wzpn.web.rest;

import com.example.wzpn.Application;
import com.example.wzpn.domain.Wynik;
import com.example.wzpn.repository.WynikRepository;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the WynikResource REST controller.
 *
 * @see WynikResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class WynikResourceTest {

    private static final String DEFAULT_WYNIK = "SAMPLE_TEXT";
    private static final String UPDATED_WYNIK = "UPDATED_TEXT";
    private static final String DEFAULT_BRAMKI_PIERWSZEJ = "SAMPLE_TEXT";
    private static final String UPDATED_BRAMKI_PIERWSZEJ = "UPDATED_TEXT";
    private static final String DEFAULT_BRAMKI_DRUGIEJ = "SAMPLE_TEXT";
    private static final String UPDATED_BRAMKI_DRUGIEJ = "UPDATED_TEXT";
    private static final String DEFAULT_ZOLTE_PIERWSZEJ = "SAMPLE_TEXT";
    private static final String UPDATED_ZOLTE_PIERWSZEJ = "UPDATED_TEXT";
    private static final String DEFAULT_ZOLTE_DRUGIEJ = "SAMPLE_TEXT";
    private static final String UPDATED_ZOLTE_DRUGIEJ = "UPDATED_TEXT";
    private static final String DEFAULT_CZERWONE_PIERWSZEJ = "SAMPLE_TEXT";
    private static final String UPDATED_CZERWONE_PIERWSZEJ = "UPDATED_TEXT";
    private static final String DEFAULT_CZERWONE_DRUGIEJ = "SAMPLE_TEXT";
    private static final String UPDATED_CZERWONE_DRUGIEJ = "UPDATED_TEXT";
    private static final String DEFAULT_UWAGI = "SAMPLE_TEXT";
    private static final String UPDATED_UWAGI = "UPDATED_TEXT";

    @Inject
    private WynikRepository wynikRepository;

    private MockMvc restWynikMockMvc;

    private Wynik wynik;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        WynikResource wynikResource = new WynikResource();
        ReflectionTestUtils.setField(wynikResource, "wynikRepository", wynikRepository);
        this.restWynikMockMvc = MockMvcBuilders.standaloneSetup(wynikResource).build();
    }

    @Before
    public void initTest() {
        wynik = new Wynik();
        wynik.setWynik(DEFAULT_WYNIK);
        wynik.setBramkiPierwszej(DEFAULT_BRAMKI_PIERWSZEJ);
        wynik.setBramkiDrugiej(DEFAULT_BRAMKI_DRUGIEJ);
        wynik.setZoltePierwszej(DEFAULT_ZOLTE_PIERWSZEJ);
        wynik.setZolteDrugiej(DEFAULT_ZOLTE_DRUGIEJ);
        wynik.setCzerwonePierwszej(DEFAULT_CZERWONE_PIERWSZEJ);
        wynik.setCzerwoneDrugiej(DEFAULT_CZERWONE_DRUGIEJ);
        wynik.setUwagi(DEFAULT_UWAGI);
    }

    @Test
    @Transactional
    public void createWynik() throws Exception {
        int databaseSizeBeforeCreate = wynikRepository.findAll().size();

        // Create the Wynik
        restWynikMockMvc.perform(post("/api/wyniks")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(wynik)))
                .andExpect(status().isCreated());

        // Validate the Wynik in the database
        List<Wynik> wyniks = wynikRepository.findAll();
        assertThat(wyniks).hasSize(databaseSizeBeforeCreate + 1);
        Wynik testWynik = wyniks.get(wyniks.size() - 1);
        assertThat(testWynik.getWynik()).isEqualTo(DEFAULT_WYNIK);
        assertThat(testWynik.getBramkiPierwszej()).isEqualTo(DEFAULT_BRAMKI_PIERWSZEJ);
        assertThat(testWynik.getBramkiDrugiej()).isEqualTo(DEFAULT_BRAMKI_DRUGIEJ);
        assertThat(testWynik.getZoltePierwszej()).isEqualTo(DEFAULT_ZOLTE_PIERWSZEJ);
        assertThat(testWynik.getZolteDrugiej()).isEqualTo(DEFAULT_ZOLTE_DRUGIEJ);
        assertThat(testWynik.getCzerwonePierwszej()).isEqualTo(DEFAULT_CZERWONE_PIERWSZEJ);
        assertThat(testWynik.getCzerwoneDrugiej()).isEqualTo(DEFAULT_CZERWONE_DRUGIEJ);
        assertThat(testWynik.getUwagi()).isEqualTo(DEFAULT_UWAGI);
    }

    @Test
    @Transactional
    public void getAllWyniks() throws Exception {
        // Initialize the database
        wynikRepository.saveAndFlush(wynik);

        // Get all the wyniks
        restWynikMockMvc.perform(get("/api/wyniks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(wynik.getId().intValue())))
                .andExpect(jsonPath("$.[*].wynik").value(hasItem(DEFAULT_WYNIK.toString())))
                .andExpect(jsonPath("$.[*].bramkiPierwszej").value(hasItem(DEFAULT_BRAMKI_PIERWSZEJ.toString())))
                .andExpect(jsonPath("$.[*].bramkiDrugiej").value(hasItem(DEFAULT_BRAMKI_DRUGIEJ.toString())))
                .andExpect(jsonPath("$.[*].zoltePierwszej").value(hasItem(DEFAULT_ZOLTE_PIERWSZEJ.toString())))
                .andExpect(jsonPath("$.[*].zolteDrugiej").value(hasItem(DEFAULT_ZOLTE_DRUGIEJ.toString())))
                .andExpect(jsonPath("$.[*].czerwonePierwszej").value(hasItem(DEFAULT_CZERWONE_PIERWSZEJ.toString())))
                .andExpect(jsonPath("$.[*].czerwoneDrugiej").value(hasItem(DEFAULT_CZERWONE_DRUGIEJ.toString())))
                .andExpect(jsonPath("$.[*].uwagi").value(hasItem(DEFAULT_UWAGI.toString())));
    }

    @Test
    @Transactional
    public void getWynik() throws Exception {
        // Initialize the database
        wynikRepository.saveAndFlush(wynik);

        // Get the wynik
        restWynikMockMvc.perform(get("/api/wyniks/{id}", wynik.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(wynik.getId().intValue()))
            .andExpect(jsonPath("$.wynik").value(DEFAULT_WYNIK.toString()))
            .andExpect(jsonPath("$.bramkiPierwszej").value(DEFAULT_BRAMKI_PIERWSZEJ.toString()))
            .andExpect(jsonPath("$.bramkiDrugiej").value(DEFAULT_BRAMKI_DRUGIEJ.toString()))
            .andExpect(jsonPath("$.zoltePierwszej").value(DEFAULT_ZOLTE_PIERWSZEJ.toString()))
            .andExpect(jsonPath("$.zolteDrugiej").value(DEFAULT_ZOLTE_DRUGIEJ.toString()))
            .andExpect(jsonPath("$.czerwonePierwszej").value(DEFAULT_CZERWONE_PIERWSZEJ.toString()))
            .andExpect(jsonPath("$.czerwoneDrugiej").value(DEFAULT_CZERWONE_DRUGIEJ.toString()))
            .andExpect(jsonPath("$.uwagi").value(DEFAULT_UWAGI.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWynik() throws Exception {
        // Get the wynik
        restWynikMockMvc.perform(get("/api/wyniks/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWynik() throws Exception {
        // Initialize the database
        wynikRepository.saveAndFlush(wynik);
		
		int databaseSizeBeforeUpdate = wynikRepository.findAll().size();

        // Update the wynik
        wynik.setWynik(UPDATED_WYNIK);
        wynik.setBramkiPierwszej(UPDATED_BRAMKI_PIERWSZEJ);
        wynik.setBramkiDrugiej(UPDATED_BRAMKI_DRUGIEJ);
        wynik.setZoltePierwszej(UPDATED_ZOLTE_PIERWSZEJ);
        wynik.setZolteDrugiej(UPDATED_ZOLTE_DRUGIEJ);
        wynik.setCzerwonePierwszej(UPDATED_CZERWONE_PIERWSZEJ);
        wynik.setCzerwoneDrugiej(UPDATED_CZERWONE_DRUGIEJ);
        wynik.setUwagi(UPDATED_UWAGI);
        restWynikMockMvc.perform(put("/api/wyniks")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(wynik)))
                .andExpect(status().isOk());

        // Validate the Wynik in the database
        List<Wynik> wyniks = wynikRepository.findAll();
        assertThat(wyniks).hasSize(databaseSizeBeforeUpdate);
        Wynik testWynik = wyniks.get(wyniks.size() - 1);
        assertThat(testWynik.getWynik()).isEqualTo(UPDATED_WYNIK);
        assertThat(testWynik.getBramkiPierwszej()).isEqualTo(UPDATED_BRAMKI_PIERWSZEJ);
        assertThat(testWynik.getBramkiDrugiej()).isEqualTo(UPDATED_BRAMKI_DRUGIEJ);
        assertThat(testWynik.getZoltePierwszej()).isEqualTo(UPDATED_ZOLTE_PIERWSZEJ);
        assertThat(testWynik.getZolteDrugiej()).isEqualTo(UPDATED_ZOLTE_DRUGIEJ);
        assertThat(testWynik.getCzerwonePierwszej()).isEqualTo(UPDATED_CZERWONE_PIERWSZEJ);
        assertThat(testWynik.getCzerwoneDrugiej()).isEqualTo(UPDATED_CZERWONE_DRUGIEJ);
        assertThat(testWynik.getUwagi()).isEqualTo(UPDATED_UWAGI);
    }

    @Test
    @Transactional
    public void deleteWynik() throws Exception {
        // Initialize the database
        wynikRepository.saveAndFlush(wynik);
		
		int databaseSizeBeforeDelete = wynikRepository.findAll().size();

        // Get the wynik
        restWynikMockMvc.perform(delete("/api/wyniks/{id}", wynik.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Wynik> wyniks = wynikRepository.findAll();
        assertThat(wyniks).hasSize(databaseSizeBeforeDelete - 1);
    }
}
