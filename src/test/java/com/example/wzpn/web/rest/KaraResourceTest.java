package com.example.wzpn.web.rest;

import com.example.wzpn.Application;
import com.example.wzpn.domain.Kara;
import com.example.wzpn.repository.KaraRepository;

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
 * Test class for the KaraResource REST controller.
 *
 * @see KaraResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class KaraResourceTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");


    private static final DateTime DEFAULT_DATA_OTRZYMANIA = new DateTime(0L, DateTimeZone.UTC);
    private static final DateTime UPDATED_DATA_OTRZYMANIA = new DateTime(DateTimeZone.UTC).withMillisOfSecond(0);
    private static final String DEFAULT_DATA_OTRZYMANIA_STR = dateTimeFormatter.print(DEFAULT_DATA_OTRZYMANIA);
    private static final String DEFAULT_POWOD = "SAMPLE_TEXT";
    private static final String UPDATED_POWOD = "UPDATED_TEXT";

    private static final BigDecimal DEFAULT_KWOTA = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_KWOTA = BigDecimal.ONE;

    @Inject
    private KaraRepository karaRepository;

    private MockMvc restKaraMockMvc;

    private Kara kara;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        KaraResource karaResource = new KaraResource();
        ReflectionTestUtils.setField(karaResource, "karaRepository", karaRepository);
        this.restKaraMockMvc = MockMvcBuilders.standaloneSetup(karaResource).build();
    }

    @Before
    public void initTest() {
        kara = new Kara();
        kara.setDataOtrzymania(DEFAULT_DATA_OTRZYMANIA);
        kara.setPowod(DEFAULT_POWOD);
        kara.setKwota(DEFAULT_KWOTA);
    }

    @Test
    @Transactional
    public void createKara() throws Exception {
        int databaseSizeBeforeCreate = karaRepository.findAll().size();

        // Create the Kara
        restKaraMockMvc.perform(post("/api/karas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(kara)))
                .andExpect(status().isCreated());

        // Validate the Kara in the database
        List<Kara> karas = karaRepository.findAll();
        assertThat(karas).hasSize(databaseSizeBeforeCreate + 1);
        Kara testKara = karas.get(karas.size() - 1);
        assertThat(testKara.getDataOtrzymania().toDateTime(DateTimeZone.UTC)).isEqualTo(DEFAULT_DATA_OTRZYMANIA);
        assertThat(testKara.getPowod()).isEqualTo(DEFAULT_POWOD);
        assertThat(testKara.getKwota()).isEqualTo(DEFAULT_KWOTA);
    }

    @Test
    @Transactional
    public void getAllKaras() throws Exception {
        // Initialize the database
        karaRepository.saveAndFlush(kara);

        // Get all the karas
        restKaraMockMvc.perform(get("/api/karas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(kara.getId().intValue())))
                .andExpect(jsonPath("$.[*].dataOtrzymania").value(hasItem(DEFAULT_DATA_OTRZYMANIA_STR)))
                .andExpect(jsonPath("$.[*].powod").value(hasItem(DEFAULT_POWOD.toString())))
                .andExpect(jsonPath("$.[*].kwota").value(hasItem(DEFAULT_KWOTA.intValue())));
    }

    @Test
    @Transactional
    public void getKara() throws Exception {
        // Initialize the database
        karaRepository.saveAndFlush(kara);

        // Get the kara
        restKaraMockMvc.perform(get("/api/karas/{id}", kara.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(kara.getId().intValue()))
            .andExpect(jsonPath("$.dataOtrzymania").value(DEFAULT_DATA_OTRZYMANIA_STR))
            .andExpect(jsonPath("$.powod").value(DEFAULT_POWOD.toString()))
            .andExpect(jsonPath("$.kwota").value(DEFAULT_KWOTA.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingKara() throws Exception {
        // Get the kara
        restKaraMockMvc.perform(get("/api/karas/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKara() throws Exception {
        // Initialize the database
        karaRepository.saveAndFlush(kara);
		
		int databaseSizeBeforeUpdate = karaRepository.findAll().size();

        // Update the kara
        kara.setDataOtrzymania(UPDATED_DATA_OTRZYMANIA);
        kara.setPowod(UPDATED_POWOD);
        kara.setKwota(UPDATED_KWOTA);
        restKaraMockMvc.perform(put("/api/karas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(kara)))
                .andExpect(status().isOk());

        // Validate the Kara in the database
        List<Kara> karas = karaRepository.findAll();
        assertThat(karas).hasSize(databaseSizeBeforeUpdate);
        Kara testKara = karas.get(karas.size() - 1);
        assertThat(testKara.getDataOtrzymania().toDateTime(DateTimeZone.UTC)).isEqualTo(UPDATED_DATA_OTRZYMANIA);
        assertThat(testKara.getPowod()).isEqualTo(UPDATED_POWOD);
        assertThat(testKara.getKwota()).isEqualTo(UPDATED_KWOTA);
    }

    @Test
    @Transactional
    public void deleteKara() throws Exception {
        // Initialize the database
        karaRepository.saveAndFlush(kara);
		
		int databaseSizeBeforeDelete = karaRepository.findAll().size();

        // Get the kara
        restKaraMockMvc.perform(delete("/api/karas/{id}", kara.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Kara> karas = karaRepository.findAll();
        assertThat(karas).hasSize(databaseSizeBeforeDelete - 1);
    }
}
