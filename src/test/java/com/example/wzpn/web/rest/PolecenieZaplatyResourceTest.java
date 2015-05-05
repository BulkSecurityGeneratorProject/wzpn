package com.example.wzpn.web.rest;

import com.example.wzpn.Application;
import com.example.wzpn.domain.PolecenieZaplaty;
import com.example.wzpn.repository.PolecenieZaplatyRepository;

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
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PolecenieZaplatyResource REST controller.
 *
 * @see PolecenieZaplatyResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class PolecenieZaplatyResourceTest {

    private static final String DEFAULT_NAZWA_ODBIORCY = "SAMPLE_TEXT";
    private static final String UPDATED_NAZWA_ODBIORCY = "UPDATED_TEXT";
    private static final String DEFAULT_NAZWA_ZLECENIODAWCY = "SAMPLE_TEXT";
    private static final String UPDATED_NAZWA_ZLECENIODAWCY = "UPDATED_TEXT";
    private static final String DEFAULT_RACHUNEK = "SAMPLE_TEXT";
    private static final String UPDATED_RACHUNEK = "UPDATED_TEXT";
    private static final String DEFAULT_TYTUL = "SAMPLE_TEXT";
    private static final String UPDATED_TYTUL = "UPDATED_TEXT";

    private static final BigDecimal DEFAULT_KWOTA = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_KWOTA = BigDecimal.ONE;

    @Inject
    private PolecenieZaplatyRepository polecenieZaplatyRepository;

    private MockMvc restPolecenieZaplatyMockMvc;

    private PolecenieZaplaty polecenieZaplaty;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PolecenieZaplatyResource polecenieZaplatyResource = new PolecenieZaplatyResource();
        ReflectionTestUtils.setField(polecenieZaplatyResource, "polecenieZaplatyRepository", polecenieZaplatyRepository);
        this.restPolecenieZaplatyMockMvc = MockMvcBuilders.standaloneSetup(polecenieZaplatyResource).build();
    }

    @Before
    public void initTest() {
        polecenieZaplaty = new PolecenieZaplaty();
        polecenieZaplaty.setNazwaOdbiorcy(DEFAULT_NAZWA_ODBIORCY);
        polecenieZaplaty.setNazwaZleceniodawcy(DEFAULT_NAZWA_ZLECENIODAWCY);
        polecenieZaplaty.setRachunek(DEFAULT_RACHUNEK);
        polecenieZaplaty.setTytul(DEFAULT_TYTUL);
        polecenieZaplaty.setKwota(DEFAULT_KWOTA);
    }

    @Test
    @Transactional
    public void createPolecenieZaplaty() throws Exception {
        int databaseSizeBeforeCreate = polecenieZaplatyRepository.findAll().size();

        // Create the PolecenieZaplaty
        restPolecenieZaplatyMockMvc.perform(post("/api/polecenieZaplatys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(polecenieZaplaty)))
                .andExpect(status().isCreated());

        // Validate the PolecenieZaplaty in the database
        List<PolecenieZaplaty> polecenieZaplatys = polecenieZaplatyRepository.findAll();
        assertThat(polecenieZaplatys).hasSize(databaseSizeBeforeCreate + 1);
        PolecenieZaplaty testPolecenieZaplaty = polecenieZaplatys.get(polecenieZaplatys.size() - 1);
        assertThat(testPolecenieZaplaty.getNazwaOdbiorcy()).isEqualTo(DEFAULT_NAZWA_ODBIORCY);
        assertThat(testPolecenieZaplaty.getNazwaZleceniodawcy()).isEqualTo(DEFAULT_NAZWA_ZLECENIODAWCY);
        assertThat(testPolecenieZaplaty.getRachunek()).isEqualTo(DEFAULT_RACHUNEK);
        assertThat(testPolecenieZaplaty.getTytul()).isEqualTo(DEFAULT_TYTUL);
        assertThat(testPolecenieZaplaty.getKwota()).isEqualTo(DEFAULT_KWOTA);
    }

    @Test
    @Transactional
    public void getAllPolecenieZaplatys() throws Exception {
        // Initialize the database
        polecenieZaplatyRepository.saveAndFlush(polecenieZaplaty);

        // Get all the polecenieZaplatys
        restPolecenieZaplatyMockMvc.perform(get("/api/polecenieZaplatys"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(polecenieZaplaty.getId().intValue())))
                .andExpect(jsonPath("$.[*].nazwaOdbiorcy").value(hasItem(DEFAULT_NAZWA_ODBIORCY.toString())))
                .andExpect(jsonPath("$.[*].nazwaZleceniodawcy").value(hasItem(DEFAULT_NAZWA_ZLECENIODAWCY.toString())))
                .andExpect(jsonPath("$.[*].rachunek").value(hasItem(DEFAULT_RACHUNEK.toString())))
                .andExpect(jsonPath("$.[*].tytul").value(hasItem(DEFAULT_TYTUL.toString())))
                .andExpect(jsonPath("$.[*].kwota").value(hasItem(DEFAULT_KWOTA.intValue())));
    }

    @Test
    @Transactional
    public void getPolecenieZaplaty() throws Exception {
        // Initialize the database
        polecenieZaplatyRepository.saveAndFlush(polecenieZaplaty);

        // Get the polecenieZaplaty
        restPolecenieZaplatyMockMvc.perform(get("/api/polecenieZaplatys/{id}", polecenieZaplaty.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(polecenieZaplaty.getId().intValue()))
            .andExpect(jsonPath("$.nazwaOdbiorcy").value(DEFAULT_NAZWA_ODBIORCY.toString()))
            .andExpect(jsonPath("$.nazwaZleceniodawcy").value(DEFAULT_NAZWA_ZLECENIODAWCY.toString()))
            .andExpect(jsonPath("$.rachunek").value(DEFAULT_RACHUNEK.toString()))
            .andExpect(jsonPath("$.tytul").value(DEFAULT_TYTUL.toString()))
            .andExpect(jsonPath("$.kwota").value(DEFAULT_KWOTA.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPolecenieZaplaty() throws Exception {
        // Get the polecenieZaplaty
        restPolecenieZaplatyMockMvc.perform(get("/api/polecenieZaplatys/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePolecenieZaplaty() throws Exception {
        // Initialize the database
        polecenieZaplatyRepository.saveAndFlush(polecenieZaplaty);
		
		int databaseSizeBeforeUpdate = polecenieZaplatyRepository.findAll().size();

        // Update the polecenieZaplaty
        polecenieZaplaty.setNazwaOdbiorcy(UPDATED_NAZWA_ODBIORCY);
        polecenieZaplaty.setNazwaZleceniodawcy(UPDATED_NAZWA_ZLECENIODAWCY);
        polecenieZaplaty.setRachunek(UPDATED_RACHUNEK);
        polecenieZaplaty.setTytul(UPDATED_TYTUL);
        polecenieZaplaty.setKwota(UPDATED_KWOTA);
        restPolecenieZaplatyMockMvc.perform(put("/api/polecenieZaplatys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(polecenieZaplaty)))
                .andExpect(status().isOk());

        // Validate the PolecenieZaplaty in the database
        List<PolecenieZaplaty> polecenieZaplatys = polecenieZaplatyRepository.findAll();
        assertThat(polecenieZaplatys).hasSize(databaseSizeBeforeUpdate);
        PolecenieZaplaty testPolecenieZaplaty = polecenieZaplatys.get(polecenieZaplatys.size() - 1);
        assertThat(testPolecenieZaplaty.getNazwaOdbiorcy()).isEqualTo(UPDATED_NAZWA_ODBIORCY);
        assertThat(testPolecenieZaplaty.getNazwaZleceniodawcy()).isEqualTo(UPDATED_NAZWA_ZLECENIODAWCY);
        assertThat(testPolecenieZaplaty.getRachunek()).isEqualTo(UPDATED_RACHUNEK);
        assertThat(testPolecenieZaplaty.getTytul()).isEqualTo(UPDATED_TYTUL);
        assertThat(testPolecenieZaplaty.getKwota()).isEqualTo(UPDATED_KWOTA);
    }

    @Test
    @Transactional
    public void deletePolecenieZaplaty() throws Exception {
        // Initialize the database
        polecenieZaplatyRepository.saveAndFlush(polecenieZaplaty);
		
		int databaseSizeBeforeDelete = polecenieZaplatyRepository.findAll().size();

        // Get the polecenieZaplaty
        restPolecenieZaplatyMockMvc.perform(delete("/api/polecenieZaplatys/{id}", polecenieZaplaty.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<PolecenieZaplaty> polecenieZaplatys = polecenieZaplatyRepository.findAll();
        assertThat(polecenieZaplatys).hasSize(databaseSizeBeforeDelete - 1);
    }
}
