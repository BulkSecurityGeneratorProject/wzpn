package com.example.wzpn.web.rest;

import com.example.wzpn.Application;
import com.example.wzpn.domain.Usluga;
import com.example.wzpn.repository.UslugaRepository;

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
 * Test class for the UslugaResource REST controller.
 *
 * @see UslugaResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class UslugaResourceTest {

    private static final String DEFAULT_NAZWA = "SAMPLE_TEXT";
    private static final String UPDATED_NAZWA = "UPDATED_TEXT";

    private static final BigDecimal DEFAULT_CENA = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_CENA = BigDecimal.ONE;

    private static final Integer DEFAULT_PODATEK = 0;
    private static final Integer UPDATED_PODATEK = 1;

    private static final Boolean DEFAULT_USUNIETA = false;
    private static final Boolean UPDATED_USUNIETA = true;

    @Inject
    private UslugaRepository uslugaRepository;

    private MockMvc restUslugaMockMvc;

    private Usluga usluga;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        UslugaResource uslugaResource = new UslugaResource();
        ReflectionTestUtils.setField(uslugaResource, "uslugaRepository", uslugaRepository);
        this.restUslugaMockMvc = MockMvcBuilders.standaloneSetup(uslugaResource).build();
    }

    @Before
    public void initTest() {
        usluga = new Usluga();
        usluga.setNazwa(DEFAULT_NAZWA);
        usluga.setCena(DEFAULT_CENA);
        usluga.setPodatek(DEFAULT_PODATEK);
        usluga.setUsunieta(DEFAULT_USUNIETA);
    }

    @Test
    @Transactional
    public void createUsluga() throws Exception {
        int databaseSizeBeforeCreate = uslugaRepository.findAll().size();

        // Create the Usluga
        restUslugaMockMvc.perform(post("/api/uslugas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usluga)))
                .andExpect(status().isCreated());

        // Validate the Usluga in the database
        List<Usluga> uslugas = uslugaRepository.findAll();
        assertThat(uslugas).hasSize(databaseSizeBeforeCreate + 1);
        Usluga testUsluga = uslugas.get(uslugas.size() - 1);
        assertThat(testUsluga.getNazwa()).isEqualTo(DEFAULT_NAZWA);
        assertThat(testUsluga.getCena()).isEqualTo(DEFAULT_CENA);
        assertThat(testUsluga.getPodatek()).isEqualTo(DEFAULT_PODATEK);
        assertThat(testUsluga.getUsunieta()).isEqualTo(DEFAULT_USUNIETA);
    }

    @Test
    @Transactional
    public void getAllUslugas() throws Exception {
        // Initialize the database
        uslugaRepository.saveAndFlush(usluga);

        // Get all the uslugas
        restUslugaMockMvc.perform(get("/api/uslugas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(usluga.getId().intValue())))
                .andExpect(jsonPath("$.[*].nazwa").value(hasItem(DEFAULT_NAZWA.toString())))
                .andExpect(jsonPath("$.[*].cena").value(hasItem(DEFAULT_CENA.intValue())))
                .andExpect(jsonPath("$.[*].podatek").value(hasItem(DEFAULT_PODATEK)))
                .andExpect(jsonPath("$.[*].usunieta").value(hasItem(DEFAULT_USUNIETA.booleanValue())));
    }

    @Test
    @Transactional
    public void getUsluga() throws Exception {
        // Initialize the database
        uslugaRepository.saveAndFlush(usluga);

        // Get the usluga
        restUslugaMockMvc.perform(get("/api/uslugas/{id}", usluga.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(usluga.getId().intValue()))
            .andExpect(jsonPath("$.nazwa").value(DEFAULT_NAZWA.toString()))
            .andExpect(jsonPath("$.cena").value(DEFAULT_CENA.intValue()))
            .andExpect(jsonPath("$.podatek").value(DEFAULT_PODATEK))
            .andExpect(jsonPath("$.usunieta").value(DEFAULT_USUNIETA.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingUsluga() throws Exception {
        // Get the usluga
        restUslugaMockMvc.perform(get("/api/uslugas/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUsluga() throws Exception {
        // Initialize the database
        uslugaRepository.saveAndFlush(usluga);
		
		int databaseSizeBeforeUpdate = uslugaRepository.findAll().size();

        // Update the usluga
        usluga.setNazwa(UPDATED_NAZWA);
        usluga.setCena(UPDATED_CENA);
        usluga.setPodatek(UPDATED_PODATEK);
        usluga.setUsunieta(UPDATED_USUNIETA);
        restUslugaMockMvc.perform(put("/api/uslugas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usluga)))
                .andExpect(status().isOk());

        // Validate the Usluga in the database
        List<Usluga> uslugas = uslugaRepository.findAll();
        assertThat(uslugas).hasSize(databaseSizeBeforeUpdate);
        Usluga testUsluga = uslugas.get(uslugas.size() - 1);
        assertThat(testUsluga.getNazwa()).isEqualTo(UPDATED_NAZWA);
        assertThat(testUsluga.getCena()).isEqualTo(UPDATED_CENA);
        assertThat(testUsluga.getPodatek()).isEqualTo(UPDATED_PODATEK);
        assertThat(testUsluga.getUsunieta()).isEqualTo(UPDATED_USUNIETA);
    }

    @Test
    @Transactional
    public void deleteUsluga() throws Exception {
        // Initialize the database
        uslugaRepository.saveAndFlush(usluga);
		
		int databaseSizeBeforeDelete = uslugaRepository.findAll().size();

        // Get the usluga
        restUslugaMockMvc.perform(delete("/api/uslugas/{id}", usluga.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Usluga> uslugas = uslugaRepository.findAll();
        assertThat(uslugas).hasSize(databaseSizeBeforeDelete - 1);
    }
}
