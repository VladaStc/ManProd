package edu.man.prod.web.rest;

import edu.man.prod.ManProdApp;

import edu.man.prod.domain.Radnici;
import edu.man.prod.repository.RadniciRepository;
import edu.man.prod.service.RadniciService;
import edu.man.prod.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static edu.man.prod.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import edu.man.prod.domain.enumeration.TipRadnika;
/**
 * Test class for the RadniciResource REST controller.
 *
 * @see RadniciResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManProdApp.class)
public class RadniciResourceIntTest {

    private static final String DEFAULT_IME = "AAAAAAAAAA";
    private static final String UPDATED_IME = "BBBBBBBBBB";

    private static final String DEFAULT_PREZIME = "AAAAAAAAAA";
    private static final String UPDATED_PREZIME = "BBBBBBBBBB";

    private static final Long DEFAULT_JMBG = 1L;
    private static final Long UPDATED_JMBG = 2L;

    private static final String DEFAULT_KVALIFIKACIJA = "AAAAAAAAAA";
    private static final String UPDATED_KVALIFIKACIJA = "BBBBBBBBBB";

    private static final Double DEFAULT_KOEFICIJENT = 1D;
    private static final Double UPDATED_KOEFICIJENT = 2D;

    private static final String DEFAULT_SERTIFIKAT = "AAAAAAAAAA";
    private static final String UPDATED_SERTIFIKAT = "BBBBBBBBBB";

    private static final String DEFAULT_POL = "AAAAAAAAAA";
    private static final String UPDATED_POL = "BBBBBBBBBB";

    private static final String DEFAULT_NAPOMENA = "AAAAAAAAAA";
    private static final String UPDATED_NAPOMENA = "BBBBBBBBBB";

    private static final TipRadnika DEFAULT_TIP_RADNIKA = TipRadnika.SOPSTVENI;
    private static final TipRadnika UPDATED_TIP_RADNIKA = TipRadnika.ANGAZOVANI;

    @Autowired
    private RadniciRepository radniciRepository;
    
    @Autowired
    private RadniciService radniciService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRadniciMockMvc;

    private Radnici radnici;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RadniciResource radniciResource = new RadniciResource(radniciService);
        this.restRadniciMockMvc = MockMvcBuilders.standaloneSetup(radniciResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Radnici createEntity(EntityManager em) {
        Radnici radnici = new Radnici()
            .ime(DEFAULT_IME)
            .prezime(DEFAULT_PREZIME)
            .jmbg(DEFAULT_JMBG)
            .kvalifikacija(DEFAULT_KVALIFIKACIJA)
            .koeficijent(DEFAULT_KOEFICIJENT)
            .sertifikat(DEFAULT_SERTIFIKAT)
            .pol(DEFAULT_POL)
            .napomena(DEFAULT_NAPOMENA)
            .tipRadnika(DEFAULT_TIP_RADNIKA);
        return radnici;
    }

    @Before
    public void initTest() {
        radnici = createEntity(em);
    }

    @Test
    @Transactional
    public void createRadnici() throws Exception {
        int databaseSizeBeforeCreate = radniciRepository.findAll().size();

        // Create the Radnici
        restRadniciMockMvc.perform(post("/api/radnicis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(radnici)))
            .andExpect(status().isCreated());

        // Validate the Radnici in the database
        List<Radnici> radniciList = radniciRepository.findAll();
        assertThat(radniciList).hasSize(databaseSizeBeforeCreate + 1);
        Radnici testRadnici = radniciList.get(radniciList.size() - 1);
        assertThat(testRadnici.getIme()).isEqualTo(DEFAULT_IME);
        assertThat(testRadnici.getPrezime()).isEqualTo(DEFAULT_PREZIME);
        assertThat(testRadnici.getJmbg()).isEqualTo(DEFAULT_JMBG);
        assertThat(testRadnici.getKvalifikacija()).isEqualTo(DEFAULT_KVALIFIKACIJA);
        assertThat(testRadnici.getKoeficijent()).isEqualTo(DEFAULT_KOEFICIJENT);
        assertThat(testRadnici.getSertifikat()).isEqualTo(DEFAULT_SERTIFIKAT);
        assertThat(testRadnici.getPol()).isEqualTo(DEFAULT_POL);
        assertThat(testRadnici.getNapomena()).isEqualTo(DEFAULT_NAPOMENA);
        assertThat(testRadnici.getTipRadnika()).isEqualTo(DEFAULT_TIP_RADNIKA);
    }

    @Test
    @Transactional
    public void createRadniciWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = radniciRepository.findAll().size();

        // Create the Radnici with an existing ID
        radnici.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRadniciMockMvc.perform(post("/api/radnicis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(radnici)))
            .andExpect(status().isBadRequest());

        // Validate the Radnici in the database
        List<Radnici> radniciList = radniciRepository.findAll();
        assertThat(radniciList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRadnicis() throws Exception {
        // Initialize the database
        radniciRepository.saveAndFlush(radnici);

        // Get all the radniciList
        restRadniciMockMvc.perform(get("/api/radnicis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(radnici.getId().intValue())))
            .andExpect(jsonPath("$.[*].ime").value(hasItem(DEFAULT_IME.toString())))
            .andExpect(jsonPath("$.[*].prezime").value(hasItem(DEFAULT_PREZIME.toString())))
            .andExpect(jsonPath("$.[*].jmbg").value(hasItem(DEFAULT_JMBG.intValue())))
            .andExpect(jsonPath("$.[*].kvalifikacija").value(hasItem(DEFAULT_KVALIFIKACIJA.toString())))
            .andExpect(jsonPath("$.[*].koeficijent").value(hasItem(DEFAULT_KOEFICIJENT.doubleValue())))
            .andExpect(jsonPath("$.[*].sertifikat").value(hasItem(DEFAULT_SERTIFIKAT.toString())))
            .andExpect(jsonPath("$.[*].pol").value(hasItem(DEFAULT_POL.toString())))
            .andExpect(jsonPath("$.[*].napomena").value(hasItem(DEFAULT_NAPOMENA.toString())))
            .andExpect(jsonPath("$.[*].tipRadnika").value(hasItem(DEFAULT_TIP_RADNIKA.toString())));
    }
    
    @Test
    @Transactional
    public void getRadnici() throws Exception {
        // Initialize the database
        radniciRepository.saveAndFlush(radnici);

        // Get the radnici
        restRadniciMockMvc.perform(get("/api/radnicis/{id}", radnici.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(radnici.getId().intValue()))
            .andExpect(jsonPath("$.ime").value(DEFAULT_IME.toString()))
            .andExpect(jsonPath("$.prezime").value(DEFAULT_PREZIME.toString()))
            .andExpect(jsonPath("$.jmbg").value(DEFAULT_JMBG.intValue()))
            .andExpect(jsonPath("$.kvalifikacija").value(DEFAULT_KVALIFIKACIJA.toString()))
            .andExpect(jsonPath("$.koeficijent").value(DEFAULT_KOEFICIJENT.doubleValue()))
            .andExpect(jsonPath("$.sertifikat").value(DEFAULT_SERTIFIKAT.toString()))
            .andExpect(jsonPath("$.pol").value(DEFAULT_POL.toString()))
            .andExpect(jsonPath("$.napomena").value(DEFAULT_NAPOMENA.toString()))
            .andExpect(jsonPath("$.tipRadnika").value(DEFAULT_TIP_RADNIKA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRadnici() throws Exception {
        // Get the radnici
        restRadniciMockMvc.perform(get("/api/radnicis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRadnici() throws Exception {
        // Initialize the database
        radniciService.save(radnici);

        int databaseSizeBeforeUpdate = radniciRepository.findAll().size();

        // Update the radnici
        Radnici updatedRadnici = radniciRepository.findById(radnici.getId()).get();
        // Disconnect from session so that the updates on updatedRadnici are not directly saved in db
        em.detach(updatedRadnici);
        updatedRadnici
            .ime(UPDATED_IME)
            .prezime(UPDATED_PREZIME)
            .jmbg(UPDATED_JMBG)
            .kvalifikacija(UPDATED_KVALIFIKACIJA)
            .koeficijent(UPDATED_KOEFICIJENT)
            .sertifikat(UPDATED_SERTIFIKAT)
            .pol(UPDATED_POL)
            .napomena(UPDATED_NAPOMENA)
            .tipRadnika(UPDATED_TIP_RADNIKA);

        restRadniciMockMvc.perform(put("/api/radnicis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRadnici)))
            .andExpect(status().isOk());

        // Validate the Radnici in the database
        List<Radnici> radniciList = radniciRepository.findAll();
        assertThat(radniciList).hasSize(databaseSizeBeforeUpdate);
        Radnici testRadnici = radniciList.get(radniciList.size() - 1);
        assertThat(testRadnici.getIme()).isEqualTo(UPDATED_IME);
        assertThat(testRadnici.getPrezime()).isEqualTo(UPDATED_PREZIME);
        assertThat(testRadnici.getJmbg()).isEqualTo(UPDATED_JMBG);
        assertThat(testRadnici.getKvalifikacija()).isEqualTo(UPDATED_KVALIFIKACIJA);
        assertThat(testRadnici.getKoeficijent()).isEqualTo(UPDATED_KOEFICIJENT);
        assertThat(testRadnici.getSertifikat()).isEqualTo(UPDATED_SERTIFIKAT);
        assertThat(testRadnici.getPol()).isEqualTo(UPDATED_POL);
        assertThat(testRadnici.getNapomena()).isEqualTo(UPDATED_NAPOMENA);
        assertThat(testRadnici.getTipRadnika()).isEqualTo(UPDATED_TIP_RADNIKA);
    }

    @Test
    @Transactional
    public void updateNonExistingRadnici() throws Exception {
        int databaseSizeBeforeUpdate = radniciRepository.findAll().size();

        // Create the Radnici

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRadniciMockMvc.perform(put("/api/radnicis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(radnici)))
            .andExpect(status().isBadRequest());

        // Validate the Radnici in the database
        List<Radnici> radniciList = radniciRepository.findAll();
        assertThat(radniciList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRadnici() throws Exception {
        // Initialize the database
        radniciService.save(radnici);

        int databaseSizeBeforeDelete = radniciRepository.findAll().size();

        // Get the radnici
        restRadniciMockMvc.perform(delete("/api/radnicis/{id}", radnici.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Radnici> radniciList = radniciRepository.findAll();
        assertThat(radniciList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Radnici.class);
        Radnici radnici1 = new Radnici();
        radnici1.setId(1L);
        Radnici radnici2 = new Radnici();
        radnici2.setId(radnici1.getId());
        assertThat(radnici1).isEqualTo(radnici2);
        radnici2.setId(2L);
        assertThat(radnici1).isNotEqualTo(radnici2);
        radnici1.setId(null);
        assertThat(radnici1).isNotEqualTo(radnici2);
    }
}
