package com.example.wzpn.web.rest;

import com.example.wzpn.Application;
import com.example.wzpn.domain.Druzyna;
import com.example.wzpn.repository.DruzynaRepository;

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
 * Test class for the DruzynaResource REST controller.
 *
 * @see DruzynaResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class DruzynaResourceTest {

    private static final String DEFAULT_NAZWA = "SAMPLE_TEXT";
    private static final String UPDATED_NAZWA = "UPDATED_TEXT";

    private static final Boolean DEFAULT_USUNIETA = false;
    private static final Boolean UPDATED_USUNIETA = true;

    private static final Boolean DEFAULT_ZAWIESZONA = false;
    private static final Boolean UPDATED_ZAWIESZONA = true;
    private static final String DEFAULT_PREZES = "SAMPLE_TEXT";
    private static final String UPDATED_PREZES = "UPDATED_TEXT";
    private static final String DEFAULT_ADRES = "SAMPLE_TEXT";
    private static final String UPDATED_ADRES = "UPDATED_TEXT";
    private static final String DEFAULT_TELEFON = "SAMPLE_TEXT";
    private static final String UPDATED_TELEFON = "UPDATED_TEXT";
    private static final String DEFAULT_EMAIL = "SAMPLE_TEXT";
    private static final String UPDATED_EMAIL = "UPDATED_TEXT";
    private static final String DEFAULT_STRONA = "SAMPLE_TEXT";
    private static final String UPDATED_STRONA = "UPDATED_TEXT";
    private static final String DEFAULT_TRENERZY = "SAMPLE_TEXT";
    private static final String UPDATED_TRENERZY = "UPDATED_TEXT";
    private static final String DEFAULT_OPIS = "SAMPLE_TEXT";
    private static final String UPDATED_OPIS = "UPDATED_TEXT";

    @Inject
    private DruzynaRepository druzynaRepository;

    private MockMvc restDruzynaMockMvc;

    private Druzyna druzyna;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DruzynaResource druzynaResource = new DruzynaResource();
        ReflectionTestUtils.setField(druzynaResource, "druzynaRepository", druzynaRepository);
        this.restDruzynaMockMvc = MockMvcBuilders.standaloneSetup(druzynaResource).build();
    }

    @Before
    public void initTest() {
        druzyna = new Druzyna();
        druzyna.setNazwa(DEFAULT_NAZWA);
        druzyna.setUsunieta(DEFAULT_USUNIETA);
        druzyna.setZawieszona(DEFAULT_ZAWIESZONA);
        druzyna.setPrezes(DEFAULT_PREZES);
        druzyna.setAdres(DEFAULT_ADRES);
        druzyna.setTelefon(DEFAULT_TELEFON);
        druzyna.setEmail(DEFAULT_EMAIL);
        druzyna.setStrona(DEFAULT_STRONA);
        druzyna.setTrenerzy(DEFAULT_TRENERZY);
        druzyna.setOpis(DEFAULT_OPIS);
    }

    @Test
    @Transactional
    public void createDruzyna() throws Exception {
        int databaseSizeBeforeCreate = druzynaRepository.findAll().size();

        // Create the Druzyna
        restDruzynaMockMvc.perform(post("/api/druzynas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(druzyna)))
                .andExpect(status().isCreated());

        // Validate the Druzyna in the database
        List<Druzyna> druzynas = druzynaRepository.findAll();
        assertThat(druzynas).hasSize(databaseSizeBeforeCreate + 1);
        Druzyna testDruzyna = druzynas.get(druzynas.size() - 1);
        assertThat(testDruzyna.getNazwa()).isEqualTo(DEFAULT_NAZWA);
        assertThat(testDruzyna.getUsunieta()).isEqualTo(DEFAULT_USUNIETA);
        assertThat(testDruzyna.getZawieszona()).isEqualTo(DEFAULT_ZAWIESZONA);
        assertThat(testDruzyna.getPrezes()).isEqualTo(DEFAULT_PREZES);
        assertThat(testDruzyna.getAdres()).isEqualTo(DEFAULT_ADRES);
        assertThat(testDruzyna.getTelefon()).isEqualTo(DEFAULT_TELEFON);
        assertThat(testDruzyna.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testDruzyna.getStrona()).isEqualTo(DEFAULT_STRONA);
        assertThat(testDruzyna.getTrenerzy()).isEqualTo(DEFAULT_TRENERZY);
        assertThat(testDruzyna.getOpis()).isEqualTo(DEFAULT_OPIS);
    }

    @Test
    @Transactional
    public void getAllDruzynas() throws Exception {
        // Initialize the database
        druzynaRepository.saveAndFlush(druzyna);

        // Get all the druzynas
        restDruzynaMockMvc.perform(get("/api/druzynas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(druzyna.getId().intValue())))
                .andExpect(jsonPath("$.[*].nazwa").value(hasItem(DEFAULT_NAZWA.toString())))
                .andExpect(jsonPath("$.[*].usunieta").value(hasItem(DEFAULT_USUNIETA.booleanValue())))
                .andExpect(jsonPath("$.[*].zawieszona").value(hasItem(DEFAULT_ZAWIESZONA.booleanValue())))
                .andExpect(jsonPath("$.[*].prezes").value(hasItem(DEFAULT_PREZES.toString())))
                .andExpect(jsonPath("$.[*].adres").value(hasItem(DEFAULT_ADRES.toString())))
                .andExpect(jsonPath("$.[*].telefon").value(hasItem(DEFAULT_TELEFON.toString())))
                .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
                .andExpect(jsonPath("$.[*].strona").value(hasItem(DEFAULT_STRONA.toString())))
                .andExpect(jsonPath("$.[*].trenerzy").value(hasItem(DEFAULT_TRENERZY.toString())))
                .andExpect(jsonPath("$.[*].opis").value(hasItem(DEFAULT_OPIS.toString())));
    }

    @Test
    @Transactional
    public void getDruzyna() throws Exception {
        // Initialize the database
        druzynaRepository.saveAndFlush(druzyna);

        // Get the druzyna
        restDruzynaMockMvc.perform(get("/api/druzynas/{id}", druzyna.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(druzyna.getId().intValue()))
            .andExpect(jsonPath("$.nazwa").value(DEFAULT_NAZWA.toString()))
            .andExpect(jsonPath("$.usunieta").value(DEFAULT_USUNIETA.booleanValue()))
            .andExpect(jsonPath("$.zawieszona").value(DEFAULT_ZAWIESZONA.booleanValue()))
            .andExpect(jsonPath("$.prezes").value(DEFAULT_PREZES.toString()))
            .andExpect(jsonPath("$.adres").value(DEFAULT_ADRES.toString()))
            .andExpect(jsonPath("$.telefon").value(DEFAULT_TELEFON.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.strona").value(DEFAULT_STRONA.toString()))
            .andExpect(jsonPath("$.trenerzy").value(DEFAULT_TRENERZY.toString()))
            .andExpect(jsonPath("$.opis").value(DEFAULT_OPIS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDruzyna() throws Exception {
        // Get the druzyna
        restDruzynaMockMvc.perform(get("/api/druzynas/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDruzyna() throws Exception {
        // Initialize the database
        druzynaRepository.saveAndFlush(druzyna);
		
		int databaseSizeBeforeUpdate = druzynaRepository.findAll().size();

        // Update the druzyna
        druzyna.setNazwa(UPDATED_NAZWA);
        druzyna.setUsunieta(UPDATED_USUNIETA);
        druzyna.setZawieszona(UPDATED_ZAWIESZONA);
        druzyna.setPrezes(UPDATED_PREZES);
        druzyna.setAdres(UPDATED_ADRES);
        druzyna.setTelefon(UPDATED_TELEFON);
        druzyna.setEmail(UPDATED_EMAIL);
        druzyna.setStrona(UPDATED_STRONA);
        druzyna.setTrenerzy(UPDATED_TRENERZY);
        druzyna.setOpis(UPDATED_OPIS);
        restDruzynaMockMvc.perform(put("/api/druzynas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(druzyna)))
                .andExpect(status().isOk());

        // Validate the Druzyna in the database
        List<Druzyna> druzynas = druzynaRepository.findAll();
        assertThat(druzynas).hasSize(databaseSizeBeforeUpdate);
        Druzyna testDruzyna = druzynas.get(druzynas.size() - 1);
        assertThat(testDruzyna.getNazwa()).isEqualTo(UPDATED_NAZWA);
        assertThat(testDruzyna.getUsunieta()).isEqualTo(UPDATED_USUNIETA);
        assertThat(testDruzyna.getZawieszona()).isEqualTo(UPDATED_ZAWIESZONA);
        assertThat(testDruzyna.getPrezes()).isEqualTo(UPDATED_PREZES);
        assertThat(testDruzyna.getAdres()).isEqualTo(UPDATED_ADRES);
        assertThat(testDruzyna.getTelefon()).isEqualTo(UPDATED_TELEFON);
        assertThat(testDruzyna.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testDruzyna.getStrona()).isEqualTo(UPDATED_STRONA);
        assertThat(testDruzyna.getTrenerzy()).isEqualTo(UPDATED_TRENERZY);
        assertThat(testDruzyna.getOpis()).isEqualTo(UPDATED_OPIS);
    }

    @Test
    @Transactional
    public void deleteDruzyna() throws Exception {
        // Initialize the database
        druzynaRepository.saveAndFlush(druzyna);
		
		int databaseSizeBeforeDelete = druzynaRepository.findAll().size();

        // Get the druzyna
        restDruzynaMockMvc.perform(delete("/api/druzynas/{id}", druzyna.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Druzyna> druzynas = druzynaRepository.findAll();
        assertThat(druzynas).hasSize(databaseSizeBeforeDelete - 1);
    }
}
