package com.example.wzpn.web.rest;

import com.example.wzpn.Application;
import com.example.wzpn.domain.ObiektSportowy;
import com.example.wzpn.repository.ObiektSportowyRepository;

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
 * Test class for the ObiektSportowyResource REST controller.
 *
 * @see ObiektSportowyResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ObiektSportowyResourceTest {

    private static final String DEFAULT_NAZWA = "SAMPLE_TEXT";
    private static final String UPDATED_NAZWA = "UPDATED_TEXT";

    private static final Boolean DEFAULT_AKTYWNY = false;
    private static final Boolean UPDATED_AKTYWNY = true;
    private static final String DEFAULT_MIASTO = "SAMPLE_TEXT";
    private static final String UPDATED_MIASTO = "UPDATED_TEXT";
    private static final String DEFAULT_KOD_POCZTOWY = "SAMPLE_TEXT";
    private static final String UPDATED_KOD_POCZTOWY = "UPDATED_TEXT";
    private static final String DEFAULT_ULICA = "SAMPLE_TEXT";
    private static final String UPDATED_ULICA = "UPDATED_TEXT";
    private static final String DEFAULT_TELEFON = "SAMPLE_TEXT";
    private static final String UPDATED_TELEFON = "UPDATED_TEXT";
    private static final String DEFAULT_PREZES = "SAMPLE_TEXT";
    private static final String UPDATED_PREZES = "UPDATED_TEXT";
    private static final String DEFAULT_STRONA = "SAMPLE_TEXT";
    private static final String UPDATED_STRONA = "UPDATED_TEXT";

    private static final Integer DEFAULT_MIEJSCA_SIEDZACE = 0;
    private static final Integer UPDATED_MIEJSCA_SIEDZACE = 1;

    private static final Integer DEFAULT_MIEJSCA_STOJACE = 0;
    private static final Integer UPDATED_MIEJSCA_STOJACE = 1;

    private static final Boolean DEFAULT_KRYTY = false;
    private static final Boolean UPDATED_KRYTY = true;

    private static final Boolean DEFAULT_OSWIETLENIE = false;
    private static final Boolean UPDATED_OSWIETLENIE = true;
    private static final String DEFAULT_WYMIARY = "SAMPLE_TEXT";
    private static final String UPDATED_WYMIARY = "UPDATED_TEXT";

    @Inject
    private ObiektSportowyRepository obiektSportowyRepository;

    private MockMvc restObiektSportowyMockMvc;

    private ObiektSportowy obiektSportowy;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ObiektSportowyResource obiektSportowyResource = new ObiektSportowyResource();
        ReflectionTestUtils.setField(obiektSportowyResource, "obiektSportowyRepository", obiektSportowyRepository);
        this.restObiektSportowyMockMvc = MockMvcBuilders.standaloneSetup(obiektSportowyResource).build();
    }

    @Before
    public void initTest() {
        obiektSportowy = new ObiektSportowy();
        obiektSportowy.setNazwa(DEFAULT_NAZWA);
        obiektSportowy.setAktywny(DEFAULT_AKTYWNY);
        obiektSportowy.setMiasto(DEFAULT_MIASTO);
        obiektSportowy.setKodPocztowy(DEFAULT_KOD_POCZTOWY);
        obiektSportowy.setUlica(DEFAULT_ULICA);
        obiektSportowy.setTelefon(DEFAULT_TELEFON);
        obiektSportowy.setPrezes(DEFAULT_PREZES);
        obiektSportowy.setStrona(DEFAULT_STRONA);
        obiektSportowy.setMiejscaSiedzace(DEFAULT_MIEJSCA_SIEDZACE);
        obiektSportowy.setMiejscaStojace(DEFAULT_MIEJSCA_STOJACE);
        obiektSportowy.setKryty(DEFAULT_KRYTY);
        obiektSportowy.setOswietlenie(DEFAULT_OSWIETLENIE);
        obiektSportowy.setWymiary(DEFAULT_WYMIARY);
    }

    @Test
    @Transactional
    public void createObiektSportowy() throws Exception {
        int databaseSizeBeforeCreate = obiektSportowyRepository.findAll().size();

        // Create the ObiektSportowy
        restObiektSportowyMockMvc.perform(post("/api/obiektSportowys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(obiektSportowy)))
                .andExpect(status().isCreated());

        // Validate the ObiektSportowy in the database
        List<ObiektSportowy> obiektSportowys = obiektSportowyRepository.findAll();
        assertThat(obiektSportowys).hasSize(databaseSizeBeforeCreate + 1);
        ObiektSportowy testObiektSportowy = obiektSportowys.get(obiektSportowys.size() - 1);
        assertThat(testObiektSportowy.getNazwa()).isEqualTo(DEFAULT_NAZWA);
        assertThat(testObiektSportowy.getAktywny()).isEqualTo(DEFAULT_AKTYWNY);
        assertThat(testObiektSportowy.getMiasto()).isEqualTo(DEFAULT_MIASTO);
        assertThat(testObiektSportowy.getKodPocztowy()).isEqualTo(DEFAULT_KOD_POCZTOWY);
        assertThat(testObiektSportowy.getUlica()).isEqualTo(DEFAULT_ULICA);
        assertThat(testObiektSportowy.getTelefon()).isEqualTo(DEFAULT_TELEFON);
        assertThat(testObiektSportowy.getPrezes()).isEqualTo(DEFAULT_PREZES);
        assertThat(testObiektSportowy.getStrona()).isEqualTo(DEFAULT_STRONA);
        assertThat(testObiektSportowy.getMiejscaSiedzace()).isEqualTo(DEFAULT_MIEJSCA_SIEDZACE);
        assertThat(testObiektSportowy.getMiejscaStojace()).isEqualTo(DEFAULT_MIEJSCA_STOJACE);
        assertThat(testObiektSportowy.getKryty()).isEqualTo(DEFAULT_KRYTY);
        assertThat(testObiektSportowy.getOswietlenie()).isEqualTo(DEFAULT_OSWIETLENIE);
        assertThat(testObiektSportowy.getWymiary()).isEqualTo(DEFAULT_WYMIARY);
    }

    @Test
    @Transactional
    public void getAllObiektSportowys() throws Exception {
        // Initialize the database
        obiektSportowyRepository.saveAndFlush(obiektSportowy);

        // Get all the obiektSportowys
        restObiektSportowyMockMvc.perform(get("/api/obiektSportowys"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(obiektSportowy.getId().intValue())))
                .andExpect(jsonPath("$.[*].nazwa").value(hasItem(DEFAULT_NAZWA.toString())))
                .andExpect(jsonPath("$.[*].aktywny").value(hasItem(DEFAULT_AKTYWNY.booleanValue())))
                .andExpect(jsonPath("$.[*].miasto").value(hasItem(DEFAULT_MIASTO.toString())))
                .andExpect(jsonPath("$.[*].kodPocztowy").value(hasItem(DEFAULT_KOD_POCZTOWY.toString())))
                .andExpect(jsonPath("$.[*].ulica").value(hasItem(DEFAULT_ULICA.toString())))
                .andExpect(jsonPath("$.[*].telefon").value(hasItem(DEFAULT_TELEFON.toString())))
                .andExpect(jsonPath("$.[*].prezes").value(hasItem(DEFAULT_PREZES.toString())))
                .andExpect(jsonPath("$.[*].strona").value(hasItem(DEFAULT_STRONA.toString())))
                .andExpect(jsonPath("$.[*].miejscaSiedzace").value(hasItem(DEFAULT_MIEJSCA_SIEDZACE)))
                .andExpect(jsonPath("$.[*].miejscaStojace").value(hasItem(DEFAULT_MIEJSCA_STOJACE)))
                .andExpect(jsonPath("$.[*].kryty").value(hasItem(DEFAULT_KRYTY.booleanValue())))
                .andExpect(jsonPath("$.[*].oswietlenie").value(hasItem(DEFAULT_OSWIETLENIE.booleanValue())))
                .andExpect(jsonPath("$.[*].wymiary").value(hasItem(DEFAULT_WYMIARY.toString())));
    }

    @Test
    @Transactional
    public void getObiektSportowy() throws Exception {
        // Initialize the database
        obiektSportowyRepository.saveAndFlush(obiektSportowy);

        // Get the obiektSportowy
        restObiektSportowyMockMvc.perform(get("/api/obiektSportowys/{id}", obiektSportowy.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(obiektSportowy.getId().intValue()))
            .andExpect(jsonPath("$.nazwa").value(DEFAULT_NAZWA.toString()))
            .andExpect(jsonPath("$.aktywny").value(DEFAULT_AKTYWNY.booleanValue()))
            .andExpect(jsonPath("$.miasto").value(DEFAULT_MIASTO.toString()))
            .andExpect(jsonPath("$.kodPocztowy").value(DEFAULT_KOD_POCZTOWY.toString()))
            .andExpect(jsonPath("$.ulica").value(DEFAULT_ULICA.toString()))
            .andExpect(jsonPath("$.telefon").value(DEFAULT_TELEFON.toString()))
            .andExpect(jsonPath("$.prezes").value(DEFAULT_PREZES.toString()))
            .andExpect(jsonPath("$.strona").value(DEFAULT_STRONA.toString()))
            .andExpect(jsonPath("$.miejscaSiedzace").value(DEFAULT_MIEJSCA_SIEDZACE))
            .andExpect(jsonPath("$.miejscaStojace").value(DEFAULT_MIEJSCA_STOJACE))
            .andExpect(jsonPath("$.kryty").value(DEFAULT_KRYTY.booleanValue()))
            .andExpect(jsonPath("$.oswietlenie").value(DEFAULT_OSWIETLENIE.booleanValue()))
            .andExpect(jsonPath("$.wymiary").value(DEFAULT_WYMIARY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingObiektSportowy() throws Exception {
        // Get the obiektSportowy
        restObiektSportowyMockMvc.perform(get("/api/obiektSportowys/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateObiektSportowy() throws Exception {
        // Initialize the database
        obiektSportowyRepository.saveAndFlush(obiektSportowy);
		
		int databaseSizeBeforeUpdate = obiektSportowyRepository.findAll().size();

        // Update the obiektSportowy
        obiektSportowy.setNazwa(UPDATED_NAZWA);
        obiektSportowy.setAktywny(UPDATED_AKTYWNY);
        obiektSportowy.setMiasto(UPDATED_MIASTO);
        obiektSportowy.setKodPocztowy(UPDATED_KOD_POCZTOWY);
        obiektSportowy.setUlica(UPDATED_ULICA);
        obiektSportowy.setTelefon(UPDATED_TELEFON);
        obiektSportowy.setPrezes(UPDATED_PREZES);
        obiektSportowy.setStrona(UPDATED_STRONA);
        obiektSportowy.setMiejscaSiedzace(UPDATED_MIEJSCA_SIEDZACE);
        obiektSportowy.setMiejscaStojace(UPDATED_MIEJSCA_STOJACE);
        obiektSportowy.setKryty(UPDATED_KRYTY);
        obiektSportowy.setOswietlenie(UPDATED_OSWIETLENIE);
        obiektSportowy.setWymiary(UPDATED_WYMIARY);
        restObiektSportowyMockMvc.perform(put("/api/obiektSportowys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(obiektSportowy)))
                .andExpect(status().isOk());

        // Validate the ObiektSportowy in the database
        List<ObiektSportowy> obiektSportowys = obiektSportowyRepository.findAll();
        assertThat(obiektSportowys).hasSize(databaseSizeBeforeUpdate);
        ObiektSportowy testObiektSportowy = obiektSportowys.get(obiektSportowys.size() - 1);
        assertThat(testObiektSportowy.getNazwa()).isEqualTo(UPDATED_NAZWA);
        assertThat(testObiektSportowy.getAktywny()).isEqualTo(UPDATED_AKTYWNY);
        assertThat(testObiektSportowy.getMiasto()).isEqualTo(UPDATED_MIASTO);
        assertThat(testObiektSportowy.getKodPocztowy()).isEqualTo(UPDATED_KOD_POCZTOWY);
        assertThat(testObiektSportowy.getUlica()).isEqualTo(UPDATED_ULICA);
        assertThat(testObiektSportowy.getTelefon()).isEqualTo(UPDATED_TELEFON);
        assertThat(testObiektSportowy.getPrezes()).isEqualTo(UPDATED_PREZES);
        assertThat(testObiektSportowy.getStrona()).isEqualTo(UPDATED_STRONA);
        assertThat(testObiektSportowy.getMiejscaSiedzace()).isEqualTo(UPDATED_MIEJSCA_SIEDZACE);
        assertThat(testObiektSportowy.getMiejscaStojace()).isEqualTo(UPDATED_MIEJSCA_STOJACE);
        assertThat(testObiektSportowy.getKryty()).isEqualTo(UPDATED_KRYTY);
        assertThat(testObiektSportowy.getOswietlenie()).isEqualTo(UPDATED_OSWIETLENIE);
        assertThat(testObiektSportowy.getWymiary()).isEqualTo(UPDATED_WYMIARY);
    }

    @Test
    @Transactional
    public void deleteObiektSportowy() throws Exception {
        // Initialize the database
        obiektSportowyRepository.saveAndFlush(obiektSportowy);
		
		int databaseSizeBeforeDelete = obiektSportowyRepository.findAll().size();

        // Get the obiektSportowy
        restObiektSportowyMockMvc.perform(delete("/api/obiektSportowys/{id}", obiektSportowy.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ObiektSportowy> obiektSportowys = obiektSportowyRepository.findAll();
        assertThat(obiektSportowys).hasSize(databaseSizeBeforeDelete - 1);
    }
}
