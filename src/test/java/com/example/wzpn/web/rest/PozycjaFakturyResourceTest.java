package com.example.wzpn.web.rest;

import com.example.wzpn.Application;
import com.example.wzpn.domain.PozycjaFaktury;
import com.example.wzpn.repository.PozycjaFakturyRepository;

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
 * Test class for the PozycjaFakturyResource REST controller.
 *
 * @see PozycjaFakturyResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class PozycjaFakturyResourceTest {

    private static final String DEFAULT_NAZWA = "SAMPLE_TEXT";
    private static final String UPDATED_NAZWA = "UPDATED_TEXT";

    private static final BigDecimal DEFAULT_BRUTTO = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_BRUTTO = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_ILOSC = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_ILOSC = BigDecimal.ONE;

    private static final Integer DEFAULT_PODATEK = 0;
    private static final Integer UPDATED_PODATEK = 1;

    @Inject
    private PozycjaFakturyRepository pozycjaFakturyRepository;

    private MockMvc restPozycjaFakturyMockMvc;

    private PozycjaFaktury pozycjaFaktury;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PozycjaFakturyResource pozycjaFakturyResource = new PozycjaFakturyResource();
        ReflectionTestUtils.setField(pozycjaFakturyResource, "pozycjaFakturyRepository", pozycjaFakturyRepository);
        this.restPozycjaFakturyMockMvc = MockMvcBuilders.standaloneSetup(pozycjaFakturyResource).build();
    }

    @Before
    public void initTest() {
        pozycjaFaktury = new PozycjaFaktury();
        pozycjaFaktury.setNazwa(DEFAULT_NAZWA);
        pozycjaFaktury.setBrutto(DEFAULT_BRUTTO);
        pozycjaFaktury.setIlosc(DEFAULT_ILOSC);
        pozycjaFaktury.setPodatek(DEFAULT_PODATEK);
    }

    @Test
    @Transactional
    public void createPozycjaFaktury() throws Exception {
        int databaseSizeBeforeCreate = pozycjaFakturyRepository.findAll().size();

        // Create the PozycjaFaktury
        restPozycjaFakturyMockMvc.perform(post("/api/pozycjaFakturys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(pozycjaFaktury)))
                .andExpect(status().isCreated());

        // Validate the PozycjaFaktury in the database
        List<PozycjaFaktury> pozycjaFakturys = pozycjaFakturyRepository.findAll();
        assertThat(pozycjaFakturys).hasSize(databaseSizeBeforeCreate + 1);
        PozycjaFaktury testPozycjaFaktury = pozycjaFakturys.get(pozycjaFakturys.size() - 1);
        assertThat(testPozycjaFaktury.getNazwa()).isEqualTo(DEFAULT_NAZWA);
        assertThat(testPozycjaFaktury.getBrutto()).isEqualTo(DEFAULT_BRUTTO);
        assertThat(testPozycjaFaktury.getIlosc()).isEqualTo(DEFAULT_ILOSC);
        assertThat(testPozycjaFaktury.getPodatek()).isEqualTo(DEFAULT_PODATEK);
    }

    @Test
    @Transactional
    public void getAllPozycjaFakturys() throws Exception {
        // Initialize the database
        pozycjaFakturyRepository.saveAndFlush(pozycjaFaktury);

        // Get all the pozycjaFakturys
        restPozycjaFakturyMockMvc.perform(get("/api/pozycjaFakturys"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(pozycjaFaktury.getId().intValue())))
                .andExpect(jsonPath("$.[*].nazwa").value(hasItem(DEFAULT_NAZWA.toString())))
                .andExpect(jsonPath("$.[*].brutto").value(hasItem(DEFAULT_BRUTTO.intValue())))
                .andExpect(jsonPath("$.[*].ilosc").value(hasItem(DEFAULT_ILOSC.intValue())))
                .andExpect(jsonPath("$.[*].podatek").value(hasItem(DEFAULT_PODATEK)));
    }

    @Test
    @Transactional
    public void getPozycjaFaktury() throws Exception {
        // Initialize the database
        pozycjaFakturyRepository.saveAndFlush(pozycjaFaktury);

        // Get the pozycjaFaktury
        restPozycjaFakturyMockMvc.perform(get("/api/pozycjaFakturys/{id}", pozycjaFaktury.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(pozycjaFaktury.getId().intValue()))
            .andExpect(jsonPath("$.nazwa").value(DEFAULT_NAZWA.toString()))
            .andExpect(jsonPath("$.brutto").value(DEFAULT_BRUTTO.intValue()))
            .andExpect(jsonPath("$.ilosc").value(DEFAULT_ILOSC.intValue()))
            .andExpect(jsonPath("$.podatek").value(DEFAULT_PODATEK));
    }

    @Test
    @Transactional
    public void getNonExistingPozycjaFaktury() throws Exception {
        // Get the pozycjaFaktury
        restPozycjaFakturyMockMvc.perform(get("/api/pozycjaFakturys/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePozycjaFaktury() throws Exception {
        // Initialize the database
        pozycjaFakturyRepository.saveAndFlush(pozycjaFaktury);
		
		int databaseSizeBeforeUpdate = pozycjaFakturyRepository.findAll().size();

        // Update the pozycjaFaktury
        pozycjaFaktury.setNazwa(UPDATED_NAZWA);
        pozycjaFaktury.setBrutto(UPDATED_BRUTTO);
        pozycjaFaktury.setIlosc(UPDATED_ILOSC);
        pozycjaFaktury.setPodatek(UPDATED_PODATEK);
        restPozycjaFakturyMockMvc.perform(put("/api/pozycjaFakturys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(pozycjaFaktury)))
                .andExpect(status().isOk());

        // Validate the PozycjaFaktury in the database
        List<PozycjaFaktury> pozycjaFakturys = pozycjaFakturyRepository.findAll();
        assertThat(pozycjaFakturys).hasSize(databaseSizeBeforeUpdate);
        PozycjaFaktury testPozycjaFaktury = pozycjaFakturys.get(pozycjaFakturys.size() - 1);
        assertThat(testPozycjaFaktury.getNazwa()).isEqualTo(UPDATED_NAZWA);
        assertThat(testPozycjaFaktury.getBrutto()).isEqualTo(UPDATED_BRUTTO);
        assertThat(testPozycjaFaktury.getIlosc()).isEqualTo(UPDATED_ILOSC);
        assertThat(testPozycjaFaktury.getPodatek()).isEqualTo(UPDATED_PODATEK);
    }

    @Test
    @Transactional
    public void deletePozycjaFaktury() throws Exception {
        // Initialize the database
        pozycjaFakturyRepository.saveAndFlush(pozycjaFaktury);
		
		int databaseSizeBeforeDelete = pozycjaFakturyRepository.findAll().size();

        // Get the pozycjaFaktury
        restPozycjaFakturyMockMvc.perform(delete("/api/pozycjaFakturys/{id}", pozycjaFaktury.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<PozycjaFaktury> pozycjaFakturys = pozycjaFakturyRepository.findAll();
        assertThat(pozycjaFakturys).hasSize(databaseSizeBeforeDelete - 1);
    }
}
