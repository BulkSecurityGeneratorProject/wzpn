package com.example.wzpn.web.rest;

import com.example.wzpn.Application;
import com.example.wzpn.domain.Pracownik;
import com.example.wzpn.repository.PracownikRepository;

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
import org.joda.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PracownikResource REST controller.
 *
 * @see PracownikResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class PracownikResourceTest {

    private static final String DEFAULT_LOGIN = "SAMPLE_TEXT";
    private static final String UPDATED_LOGIN = "UPDATED_TEXT";
    private static final String DEFAULT_IMIE = "SAMPLE_TEXT";
    private static final String UPDATED_IMIE = "UPDATED_TEXT";
    private static final String DEFAULT_NAZWISKO = "SAMPLE_TEXT";
    private static final String UPDATED_NAZWISKO = "UPDATED_TEXT";
    private static final String DEFAULT_PESEL = "SAMPLE_TEXT";
    private static final String UPDATED_PESEL = "UPDATED_TEXT";
    private static final String DEFAULT_NIP = "SAMPLE_TEXT";
    private static final String UPDATED_NIP = "UPDATED_TEXT";
    private static final String DEFAULT_ULICA = "SAMPLE_TEXT";
    private static final String UPDATED_ULICA = "UPDATED_TEXT";
    private static final String DEFAULT_KOD_POCZTOWY = "SAMPLE_TEXT";
    private static final String UPDATED_KOD_POCZTOWY = "UPDATED_TEXT";
    private static final String DEFAULT_MIASTO = "SAMPLE_TEXT";
    private static final String UPDATED_MIASTO = "UPDATED_TEXT";

    private static final BigDecimal DEFAULT_PENSJA = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_PENSJA = BigDecimal.ONE;

    private static final LocalDate DEFAULT_DATA_ZATRUDNIENIA = new LocalDate(0L);
    private static final LocalDate UPDATED_DATA_ZATRUDNIENIA = new LocalDate();

    private static final Boolean DEFAULT_SEKRETARIAT = false;
    private static final Boolean UPDATED_SEKRETARIAT = true;

    private static final Boolean DEFAULT_WYDZIAL_GIER = false;
    private static final Boolean UPDATED_WYDZIAL_GIER = true;

    private static final Boolean DEFAULT_KSIEGOWOSC = false;
    private static final Boolean UPDATED_KSIEGOWOSC = true;

    private static final Boolean DEFAULT_ADMINISTRACJA = false;
    private static final Boolean UPDATED_ADMINISTRACJA = true;

    private static final Boolean DEFAULT_SEDZIA = false;
    private static final Boolean UPDATED_SEDZIA = true;
    private static final String DEFAULT_OPIS = "SAMPLE_TEXT";
    private static final String UPDATED_OPIS = "UPDATED_TEXT";

    @Inject
    private PracownikRepository pracownikRepository;

    private MockMvc restPracownikMockMvc;

    private Pracownik pracownik;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PracownikResource pracownikResource = new PracownikResource();
        ReflectionTestUtils.setField(pracownikResource, "pracownikRepository", pracownikRepository);
        this.restPracownikMockMvc = MockMvcBuilders.standaloneSetup(pracownikResource).build();
    }

    @Before
    public void initTest() {
        pracownik = new Pracownik();
        pracownik.setLogin(DEFAULT_LOGIN);
        pracownik.setImie(DEFAULT_IMIE);
        pracownik.setNazwisko(DEFAULT_NAZWISKO);
        pracownik.setPesel(DEFAULT_PESEL);
        pracownik.setNip(DEFAULT_NIP);
        pracownik.setUlica(DEFAULT_ULICA);
        pracownik.setKodPocztowy(DEFAULT_KOD_POCZTOWY);
        pracownik.setMiasto(DEFAULT_MIASTO);
        pracownik.setPensja(DEFAULT_PENSJA);
        pracownik.setDataZatrudnienia(DEFAULT_DATA_ZATRUDNIENIA);
        pracownik.setSekretariat(DEFAULT_SEKRETARIAT);
        pracownik.setWydzialGier(DEFAULT_WYDZIAL_GIER);
        pracownik.setKsiegowosc(DEFAULT_KSIEGOWOSC);
        pracownik.setAdministracja(DEFAULT_ADMINISTRACJA);
        pracownik.setSedzia(DEFAULT_SEDZIA);
        pracownik.setOpis(DEFAULT_OPIS);
    }

    @Test
    @Transactional
    public void createPracownik() throws Exception {
        int databaseSizeBeforeCreate = pracownikRepository.findAll().size();

        // Create the Pracownik
        restPracownikMockMvc.perform(post("/api/pracowniks")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(pracownik)))
                .andExpect(status().isCreated());

        // Validate the Pracownik in the database
        List<Pracownik> pracowniks = pracownikRepository.findAll();
        assertThat(pracowniks).hasSize(databaseSizeBeforeCreate + 1);
        Pracownik testPracownik = pracowniks.get(pracowniks.size() - 1);
        assertThat(testPracownik.getLogin()).isEqualTo(DEFAULT_LOGIN);
        assertThat(testPracownik.getImie()).isEqualTo(DEFAULT_IMIE);
        assertThat(testPracownik.getNazwisko()).isEqualTo(DEFAULT_NAZWISKO);
        assertThat(testPracownik.getPesel()).isEqualTo(DEFAULT_PESEL);
        assertThat(testPracownik.getNip()).isEqualTo(DEFAULT_NIP);
        assertThat(testPracownik.getUlica()).isEqualTo(DEFAULT_ULICA);
        assertThat(testPracownik.getKodPocztowy()).isEqualTo(DEFAULT_KOD_POCZTOWY);
        assertThat(testPracownik.getMiasto()).isEqualTo(DEFAULT_MIASTO);
        assertThat(testPracownik.getPensja()).isEqualTo(DEFAULT_PENSJA);
        assertThat(testPracownik.getDataZatrudnienia()).isEqualTo(DEFAULT_DATA_ZATRUDNIENIA);
        assertThat(testPracownik.getSekretariat()).isEqualTo(DEFAULT_SEKRETARIAT);
        assertThat(testPracownik.getWydzialGier()).isEqualTo(DEFAULT_WYDZIAL_GIER);
        assertThat(testPracownik.getKsiegowosc()).isEqualTo(DEFAULT_KSIEGOWOSC);
        assertThat(testPracownik.getAdministracja()).isEqualTo(DEFAULT_ADMINISTRACJA);
        assertThat(testPracownik.getSedzia()).isEqualTo(DEFAULT_SEDZIA);
        assertThat(testPracownik.getOpis()).isEqualTo(DEFAULT_OPIS);
    }

    @Test
    @Transactional
    public void checkLoginIsRequired() throws Exception {
        // Validate the database is empty
        assertThat(pracownikRepository.findAll()).hasSize(0);
        // set the field null
        pracownik.setLogin(null);

        // Create the Pracownik, which fails.
        restPracownikMockMvc.perform(post("/api/pracowniks")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(pracownik)))
                .andExpect(status().isBadRequest());

        // Validate the database is still empty
        List<Pracownik> pracowniks = pracownikRepository.findAll();
        assertThat(pracowniks).hasSize(0);
    }

    @Test
    @Transactional
    public void getAllPracowniks() throws Exception {
        // Initialize the database
        pracownikRepository.saveAndFlush(pracownik);

        // Get all the pracowniks
        restPracownikMockMvc.perform(get("/api/pracowniks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(pracownik.getId().intValue())))
                .andExpect(jsonPath("$.[*].login").value(hasItem(DEFAULT_LOGIN.toString())))
                .andExpect(jsonPath("$.[*].imie").value(hasItem(DEFAULT_IMIE.toString())))
                .andExpect(jsonPath("$.[*].nazwisko").value(hasItem(DEFAULT_NAZWISKO.toString())))
                .andExpect(jsonPath("$.[*].pesel").value(hasItem(DEFAULT_PESEL.toString())))
                .andExpect(jsonPath("$.[*].nip").value(hasItem(DEFAULT_NIP.toString())))
                .andExpect(jsonPath("$.[*].ulica").value(hasItem(DEFAULT_ULICA.toString())))
                .andExpect(jsonPath("$.[*].kodPocztowy").value(hasItem(DEFAULT_KOD_POCZTOWY.toString())))
                .andExpect(jsonPath("$.[*].miasto").value(hasItem(DEFAULT_MIASTO.toString())))
                .andExpect(jsonPath("$.[*].pensja").value(hasItem(DEFAULT_PENSJA.intValue())))
                .andExpect(jsonPath("$.[*].dataZatrudnienia").value(hasItem(DEFAULT_DATA_ZATRUDNIENIA.toString())))
                .andExpect(jsonPath("$.[*].sekretariat").value(hasItem(DEFAULT_SEKRETARIAT.booleanValue())))
                .andExpect(jsonPath("$.[*].wydzialGier").value(hasItem(DEFAULT_WYDZIAL_GIER.booleanValue())))
                .andExpect(jsonPath("$.[*].ksiegowosc").value(hasItem(DEFAULT_KSIEGOWOSC.booleanValue())))
                .andExpect(jsonPath("$.[*].administracja").value(hasItem(DEFAULT_ADMINISTRACJA.booleanValue())))
                .andExpect(jsonPath("$.[*].sedzia").value(hasItem(DEFAULT_SEDZIA.booleanValue())))
                .andExpect(jsonPath("$.[*].opis").value(hasItem(DEFAULT_OPIS.toString())));
    }

    @Test
    @Transactional
    public void getPracownik() throws Exception {
        // Initialize the database
        pracownikRepository.saveAndFlush(pracownik);

        // Get the pracownik
        restPracownikMockMvc.perform(get("/api/pracowniks/{id}", pracownik.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(pracownik.getId().intValue()))
            .andExpect(jsonPath("$.login").value(DEFAULT_LOGIN.toString()))
            .andExpect(jsonPath("$.imie").value(DEFAULT_IMIE.toString()))
            .andExpect(jsonPath("$.nazwisko").value(DEFAULT_NAZWISKO.toString()))
            .andExpect(jsonPath("$.pesel").value(DEFAULT_PESEL.toString()))
            .andExpect(jsonPath("$.nip").value(DEFAULT_NIP.toString()))
            .andExpect(jsonPath("$.ulica").value(DEFAULT_ULICA.toString()))
            .andExpect(jsonPath("$.kodPocztowy").value(DEFAULT_KOD_POCZTOWY.toString()))
            .andExpect(jsonPath("$.miasto").value(DEFAULT_MIASTO.toString()))
            .andExpect(jsonPath("$.pensja").value(DEFAULT_PENSJA.intValue()))
            .andExpect(jsonPath("$.dataZatrudnienia").value(DEFAULT_DATA_ZATRUDNIENIA.toString()))
            .andExpect(jsonPath("$.sekretariat").value(DEFAULT_SEKRETARIAT.booleanValue()))
            .andExpect(jsonPath("$.wydzialGier").value(DEFAULT_WYDZIAL_GIER.booleanValue()))
            .andExpect(jsonPath("$.ksiegowosc").value(DEFAULT_KSIEGOWOSC.booleanValue()))
            .andExpect(jsonPath("$.administracja").value(DEFAULT_ADMINISTRACJA.booleanValue()))
            .andExpect(jsonPath("$.sedzia").value(DEFAULT_SEDZIA.booleanValue()))
            .andExpect(jsonPath("$.opis").value(DEFAULT_OPIS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPracownik() throws Exception {
        // Get the pracownik
        restPracownikMockMvc.perform(get("/api/pracowniks/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePracownik() throws Exception {
        // Initialize the database
        pracownikRepository.saveAndFlush(pracownik);
		
		int databaseSizeBeforeUpdate = pracownikRepository.findAll().size();

        // Update the pracownik
        pracownik.setLogin(UPDATED_LOGIN);
        pracownik.setImie(UPDATED_IMIE);
        pracownik.setNazwisko(UPDATED_NAZWISKO);
        pracownik.setPesel(UPDATED_PESEL);
        pracownik.setNip(UPDATED_NIP);
        pracownik.setUlica(UPDATED_ULICA);
        pracownik.setKodPocztowy(UPDATED_KOD_POCZTOWY);
        pracownik.setMiasto(UPDATED_MIASTO);
        pracownik.setPensja(UPDATED_PENSJA);
        pracownik.setDataZatrudnienia(UPDATED_DATA_ZATRUDNIENIA);
        pracownik.setSekretariat(UPDATED_SEKRETARIAT);
        pracownik.setWydzialGier(UPDATED_WYDZIAL_GIER);
        pracownik.setKsiegowosc(UPDATED_KSIEGOWOSC);
        pracownik.setAdministracja(UPDATED_ADMINISTRACJA);
        pracownik.setSedzia(UPDATED_SEDZIA);
        pracownik.setOpis(UPDATED_OPIS);
        restPracownikMockMvc.perform(put("/api/pracowniks")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(pracownik)))
                .andExpect(status().isOk());

        // Validate the Pracownik in the database
        List<Pracownik> pracowniks = pracownikRepository.findAll();
        assertThat(pracowniks).hasSize(databaseSizeBeforeUpdate);
        Pracownik testPracownik = pracowniks.get(pracowniks.size() - 1);
        assertThat(testPracownik.getLogin()).isEqualTo(UPDATED_LOGIN);
        assertThat(testPracownik.getImie()).isEqualTo(UPDATED_IMIE);
        assertThat(testPracownik.getNazwisko()).isEqualTo(UPDATED_NAZWISKO);
        assertThat(testPracownik.getPesel()).isEqualTo(UPDATED_PESEL);
        assertThat(testPracownik.getNip()).isEqualTo(UPDATED_NIP);
        assertThat(testPracownik.getUlica()).isEqualTo(UPDATED_ULICA);
        assertThat(testPracownik.getKodPocztowy()).isEqualTo(UPDATED_KOD_POCZTOWY);
        assertThat(testPracownik.getMiasto()).isEqualTo(UPDATED_MIASTO);
        assertThat(testPracownik.getPensja()).isEqualTo(UPDATED_PENSJA);
        assertThat(testPracownik.getDataZatrudnienia()).isEqualTo(UPDATED_DATA_ZATRUDNIENIA);
        assertThat(testPracownik.getSekretariat()).isEqualTo(UPDATED_SEKRETARIAT);
        assertThat(testPracownik.getWydzialGier()).isEqualTo(UPDATED_WYDZIAL_GIER);
        assertThat(testPracownik.getKsiegowosc()).isEqualTo(UPDATED_KSIEGOWOSC);
        assertThat(testPracownik.getAdministracja()).isEqualTo(UPDATED_ADMINISTRACJA);
        assertThat(testPracownik.getSedzia()).isEqualTo(UPDATED_SEDZIA);
        assertThat(testPracownik.getOpis()).isEqualTo(UPDATED_OPIS);
    }

    @Test
    @Transactional
    public void deletePracownik() throws Exception {
        // Initialize the database
        pracownikRepository.saveAndFlush(pracownik);
		
		int databaseSizeBeforeDelete = pracownikRepository.findAll().size();

        // Get the pracownik
        restPracownikMockMvc.perform(delete("/api/pracowniks/{id}", pracownik.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Pracownik> pracowniks = pracownikRepository.findAll();
        assertThat(pracowniks).hasSize(databaseSizeBeforeDelete - 1);
    }
}
