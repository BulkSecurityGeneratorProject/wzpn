package com.example.wzpn.web.rest;

import com.example.wzpn.Application;
import com.example.wzpn.domain.Przedsiebiorca;
import com.example.wzpn.repository.PrzedsiebiorcaRepository;

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
 * Test class for the PrzedsiebiorcaResource REST controller.
 *
 * @see PrzedsiebiorcaResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class PrzedsiebiorcaResourceTest {

    private static final String DEFAULT_NAZWA = "SAMPLE_TEXT";
    private static final String UPDATED_NAZWA = "UPDATED_TEXT";
    private static final String DEFAULT_ADRES = "SAMPLE_TEXT";
    private static final String UPDATED_ADRES = "UPDATED_TEXT";
    private static final String DEFAULT_MIEJSCOWOSC = "SAMPLE_TEXT";
    private static final String UPDATED_MIEJSCOWOSC = "UPDATED_TEXT";
    private static final String DEFAULT_KOD_POCZTOWY = "SAMPLE_TEXT";
    private static final String UPDATED_KOD_POCZTOWY = "UPDATED_TEXT";
    private static final String DEFAULT_NIP = "SAMPLE_TEXT";
    private static final String UPDATED_NIP = "UPDATED_TEXT";
    private static final String DEFAULT_EMAIL = "SAMPLE_TEXT";
    private static final String UPDATED_EMAIL = "UPDATED_TEXT";
    private static final String DEFAULT_TELEFON = "SAMPLE_TEXT";
    private static final String UPDATED_TELEFON = "UPDATED_TEXT";

    @Inject
    private PrzedsiebiorcaRepository przedsiebiorcaRepository;

    private MockMvc restPrzedsiebiorcaMockMvc;

    private Przedsiebiorca przedsiebiorca;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PrzedsiebiorcaResource przedsiebiorcaResource = new PrzedsiebiorcaResource();
        ReflectionTestUtils.setField(przedsiebiorcaResource, "przedsiebiorcaRepository", przedsiebiorcaRepository);
        this.restPrzedsiebiorcaMockMvc = MockMvcBuilders.standaloneSetup(przedsiebiorcaResource).build();
    }

    @Before
    public void initTest() {
        przedsiebiorca = new Przedsiebiorca();
        przedsiebiorca.setNazwa(DEFAULT_NAZWA);
        przedsiebiorca.setAdres(DEFAULT_ADRES);
        przedsiebiorca.setMiejscowosc(DEFAULT_MIEJSCOWOSC);
        przedsiebiorca.setKodPocztowy(DEFAULT_KOD_POCZTOWY);
        przedsiebiorca.setNip(DEFAULT_NIP);
        przedsiebiorca.setEmail(DEFAULT_EMAIL);
        przedsiebiorca.setTelefon(DEFAULT_TELEFON);
    }

    @Test
    @Transactional
    public void createPrzedsiebiorca() throws Exception {
        int databaseSizeBeforeCreate = przedsiebiorcaRepository.findAll().size();

        // Create the Przedsiebiorca
        restPrzedsiebiorcaMockMvc.perform(post("/api/przedsiebiorcas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(przedsiebiorca)))
                .andExpect(status().isCreated());

        // Validate the Przedsiebiorca in the database
        List<Przedsiebiorca> przedsiebiorcas = przedsiebiorcaRepository.findAll();
        assertThat(przedsiebiorcas).hasSize(databaseSizeBeforeCreate + 1);
        Przedsiebiorca testPrzedsiebiorca = przedsiebiorcas.get(przedsiebiorcas.size() - 1);
        assertThat(testPrzedsiebiorca.getNazwa()).isEqualTo(DEFAULT_NAZWA);
        assertThat(testPrzedsiebiorca.getAdres()).isEqualTo(DEFAULT_ADRES);
        assertThat(testPrzedsiebiorca.getMiejscowosc()).isEqualTo(DEFAULT_MIEJSCOWOSC);
        assertThat(testPrzedsiebiorca.getKodPocztowy()).isEqualTo(DEFAULT_KOD_POCZTOWY);
        assertThat(testPrzedsiebiorca.getNip()).isEqualTo(DEFAULT_NIP);
        assertThat(testPrzedsiebiorca.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testPrzedsiebiorca.getTelefon()).isEqualTo(DEFAULT_TELEFON);
    }

    @Test
    @Transactional
    public void getAllPrzedsiebiorcas() throws Exception {
        // Initialize the database
        przedsiebiorcaRepository.saveAndFlush(przedsiebiorca);

        // Get all the przedsiebiorcas
        restPrzedsiebiorcaMockMvc.perform(get("/api/przedsiebiorcas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(przedsiebiorca.getId().intValue())))
                .andExpect(jsonPath("$.[*].nazwa").value(hasItem(DEFAULT_NAZWA.toString())))
                .andExpect(jsonPath("$.[*].adres").value(hasItem(DEFAULT_ADRES.toString())))
                .andExpect(jsonPath("$.[*].miejscowosc").value(hasItem(DEFAULT_MIEJSCOWOSC.toString())))
                .andExpect(jsonPath("$.[*].kodPocztowy").value(hasItem(DEFAULT_KOD_POCZTOWY.toString())))
                .andExpect(jsonPath("$.[*].nip").value(hasItem(DEFAULT_NIP.toString())))
                .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
                .andExpect(jsonPath("$.[*].telefon").value(hasItem(DEFAULT_TELEFON.toString())));
    }

    @Test
    @Transactional
    public void getPrzedsiebiorca() throws Exception {
        // Initialize the database
        przedsiebiorcaRepository.saveAndFlush(przedsiebiorca);

        // Get the przedsiebiorca
        restPrzedsiebiorcaMockMvc.perform(get("/api/przedsiebiorcas/{id}", przedsiebiorca.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(przedsiebiorca.getId().intValue()))
            .andExpect(jsonPath("$.nazwa").value(DEFAULT_NAZWA.toString()))
            .andExpect(jsonPath("$.adres").value(DEFAULT_ADRES.toString()))
            .andExpect(jsonPath("$.miejscowosc").value(DEFAULT_MIEJSCOWOSC.toString()))
            .andExpect(jsonPath("$.kodPocztowy").value(DEFAULT_KOD_POCZTOWY.toString()))
            .andExpect(jsonPath("$.nip").value(DEFAULT_NIP.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.telefon").value(DEFAULT_TELEFON.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPrzedsiebiorca() throws Exception {
        // Get the przedsiebiorca
        restPrzedsiebiorcaMockMvc.perform(get("/api/przedsiebiorcas/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePrzedsiebiorca() throws Exception {
        // Initialize the database
        przedsiebiorcaRepository.saveAndFlush(przedsiebiorca);
		
		int databaseSizeBeforeUpdate = przedsiebiorcaRepository.findAll().size();

        // Update the przedsiebiorca
        przedsiebiorca.setNazwa(UPDATED_NAZWA);
        przedsiebiorca.setAdres(UPDATED_ADRES);
        przedsiebiorca.setMiejscowosc(UPDATED_MIEJSCOWOSC);
        przedsiebiorca.setKodPocztowy(UPDATED_KOD_POCZTOWY);
        przedsiebiorca.setNip(UPDATED_NIP);
        przedsiebiorca.setEmail(UPDATED_EMAIL);
        przedsiebiorca.setTelefon(UPDATED_TELEFON);
        restPrzedsiebiorcaMockMvc.perform(put("/api/przedsiebiorcas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(przedsiebiorca)))
                .andExpect(status().isOk());

        // Validate the Przedsiebiorca in the database
        List<Przedsiebiorca> przedsiebiorcas = przedsiebiorcaRepository.findAll();
        assertThat(przedsiebiorcas).hasSize(databaseSizeBeforeUpdate);
        Przedsiebiorca testPrzedsiebiorca = przedsiebiorcas.get(przedsiebiorcas.size() - 1);
        assertThat(testPrzedsiebiorca.getNazwa()).isEqualTo(UPDATED_NAZWA);
        assertThat(testPrzedsiebiorca.getAdres()).isEqualTo(UPDATED_ADRES);
        assertThat(testPrzedsiebiorca.getMiejscowosc()).isEqualTo(UPDATED_MIEJSCOWOSC);
        assertThat(testPrzedsiebiorca.getKodPocztowy()).isEqualTo(UPDATED_KOD_POCZTOWY);
        assertThat(testPrzedsiebiorca.getNip()).isEqualTo(UPDATED_NIP);
        assertThat(testPrzedsiebiorca.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testPrzedsiebiorca.getTelefon()).isEqualTo(UPDATED_TELEFON);
    }

    @Test
    @Transactional
    public void deletePrzedsiebiorca() throws Exception {
        // Initialize the database
        przedsiebiorcaRepository.saveAndFlush(przedsiebiorca);
		
		int databaseSizeBeforeDelete = przedsiebiorcaRepository.findAll().size();

        // Get the przedsiebiorca
        restPrzedsiebiorcaMockMvc.perform(delete("/api/przedsiebiorcas/{id}", przedsiebiorca.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Przedsiebiorca> przedsiebiorcas = przedsiebiorcaRepository.findAll();
        assertThat(przedsiebiorcas).hasSize(databaseSizeBeforeDelete - 1);
    }
}
