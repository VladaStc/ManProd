package edu.man.prod.web.rest;

import edu.man.prod.ManProdApp;

import edu.man.prod.domain.PomocniMaterijal;
import edu.man.prod.repository.PomocniMaterijalRepository;
import edu.man.prod.service.PomocniMaterijalService;
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

import edu.man.prod.domain.enumeration.JedMere;
/**
 * Test class for the PomocniMaterijalResource REST controller.
 *
 * @see PomocniMaterijalResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManProdApp.class)
public class PomocniMaterijalResourceIntTest {

    private static final String DEFAULT_SIFRA = "AAAAAAAAAA";
    private static final String UPDATED_SIFRA = "BBBBBBBBBB";

    private static final String DEFAULT_NAZIV = "AAAAAAAAAA";
    private static final String UPDATED_NAZIV = "BBBBBBBBBB";

    private static final String DEFAULT_VRSTA = "AAAAAAAAAA";
    private static final String UPDATED_VRSTA = "BBBBBBBBBB";

    private static final JedMere DEFAULT_JED_MERE = JedMere.KOM;
    private static final JedMere UPDATED_JED_MERE = JedMere.KG;

    private static final String DEFAULT_NAMENA = "AAAAAAAAAA";
    private static final String UPDATED_NAMENA = "BBBBBBBBBB";

    private static final Double DEFAULT_NABAVNA_CENA = 1D;
    private static final Double UPDATED_NABAVNA_CENA = 2D;

    private static final String DEFAULT_NAPOMENA = "AAAAAAAAAA";
    private static final String UPDATED_NAPOMENA = "BBBBBBBBBB";

    @Autowired
    private PomocniMaterijalRepository pomocniMaterijalRepository;
    
    @Autowired
    private PomocniMaterijalService pomocniMaterijalService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPomocniMaterijalMockMvc;

    private PomocniMaterijal pomocniMaterijal;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PomocniMaterijalResource pomocniMaterijalResource = new PomocniMaterijalResource(pomocniMaterijalService);
        this.restPomocniMaterijalMockMvc = MockMvcBuilders.standaloneSetup(pomocniMaterijalResource)
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
    public static PomocniMaterijal createEntity(EntityManager em) {
        PomocniMaterijal pomocniMaterijal = new PomocniMaterijal()
            .sifra(DEFAULT_SIFRA)
            .naziv(DEFAULT_NAZIV)
            .vrsta(DEFAULT_VRSTA)
            .jedMere(DEFAULT_JED_MERE)
            .namena(DEFAULT_NAMENA)
            .nabavnaCena(DEFAULT_NABAVNA_CENA)
            .napomena(DEFAULT_NAPOMENA);
        return pomocniMaterijal;
    }

    @Before
    public void initTest() {
        pomocniMaterijal = createEntity(em);
    }

    @Test
    @Transactional
    public void createPomocniMaterijal() throws Exception {
        int databaseSizeBeforeCreate = pomocniMaterijalRepository.findAll().size();

        // Create the PomocniMaterijal
        restPomocniMaterijalMockMvc.perform(post("/api/pomocni-materijals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pomocniMaterijal)))
            .andExpect(status().isCreated());

        // Validate the PomocniMaterijal in the database
        List<PomocniMaterijal> pomocniMaterijalList = pomocniMaterijalRepository.findAll();
        assertThat(pomocniMaterijalList).hasSize(databaseSizeBeforeCreate + 1);
        PomocniMaterijal testPomocniMaterijal = pomocniMaterijalList.get(pomocniMaterijalList.size() - 1);
        assertThat(testPomocniMaterijal.getSifra()).isEqualTo(DEFAULT_SIFRA);
        assertThat(testPomocniMaterijal.getNaziv()).isEqualTo(DEFAULT_NAZIV);
        assertThat(testPomocniMaterijal.getVrsta()).isEqualTo(DEFAULT_VRSTA);
        assertThat(testPomocniMaterijal.getJedMere()).isEqualTo(DEFAULT_JED_MERE);
        assertThat(testPomocniMaterijal.getNamena()).isEqualTo(DEFAULT_NAMENA);
        assertThat(testPomocniMaterijal.getNabavnaCena()).isEqualTo(DEFAULT_NABAVNA_CENA);
        assertThat(testPomocniMaterijal.getNapomena()).isEqualTo(DEFAULT_NAPOMENA);
    }

    @Test
    @Transactional
    public void createPomocniMaterijalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pomocniMaterijalRepository.findAll().size();

        // Create the PomocniMaterijal with an existing ID
        pomocniMaterijal.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPomocniMaterijalMockMvc.perform(post("/api/pomocni-materijals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pomocniMaterijal)))
            .andExpect(status().isBadRequest());

        // Validate the PomocniMaterijal in the database
        List<PomocniMaterijal> pomocniMaterijalList = pomocniMaterijalRepository.findAll();
        assertThat(pomocniMaterijalList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPomocniMaterijals() throws Exception {
        // Initialize the database
        pomocniMaterijalRepository.saveAndFlush(pomocniMaterijal);

        // Get all the pomocniMaterijalList
        restPomocniMaterijalMockMvc.perform(get("/api/pomocni-materijals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pomocniMaterijal.getId().intValue())))
            .andExpect(jsonPath("$.[*].sifra").value(hasItem(DEFAULT_SIFRA.toString())))
            .andExpect(jsonPath("$.[*].naziv").value(hasItem(DEFAULT_NAZIV.toString())))
            .andExpect(jsonPath("$.[*].vrsta").value(hasItem(DEFAULT_VRSTA.toString())))
            .andExpect(jsonPath("$.[*].jedMere").value(hasItem(DEFAULT_JED_MERE.toString())))
            .andExpect(jsonPath("$.[*].namena").value(hasItem(DEFAULT_NAMENA.toString())))
            .andExpect(jsonPath("$.[*].nabavnaCena").value(hasItem(DEFAULT_NABAVNA_CENA.doubleValue())))
            .andExpect(jsonPath("$.[*].napomena").value(hasItem(DEFAULT_NAPOMENA.toString())));
    }
    
    @Test
    @Transactional
    public void getPomocniMaterijal() throws Exception {
        // Initialize the database
        pomocniMaterijalRepository.saveAndFlush(pomocniMaterijal);

        // Get the pomocniMaterijal
        restPomocniMaterijalMockMvc.perform(get("/api/pomocni-materijals/{id}", pomocniMaterijal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pomocniMaterijal.getId().intValue()))
            .andExpect(jsonPath("$.sifra").value(DEFAULT_SIFRA.toString()))
            .andExpect(jsonPath("$.naziv").value(DEFAULT_NAZIV.toString()))
            .andExpect(jsonPath("$.vrsta").value(DEFAULT_VRSTA.toString()))
            .andExpect(jsonPath("$.jedMere").value(DEFAULT_JED_MERE.toString()))
            .andExpect(jsonPath("$.namena").value(DEFAULT_NAMENA.toString()))
            .andExpect(jsonPath("$.nabavnaCena").value(DEFAULT_NABAVNA_CENA.doubleValue()))
            .andExpect(jsonPath("$.napomena").value(DEFAULT_NAPOMENA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPomocniMaterijal() throws Exception {
        // Get the pomocniMaterijal
        restPomocniMaterijalMockMvc.perform(get("/api/pomocni-materijals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePomocniMaterijal() throws Exception {
        // Initialize the database
        pomocniMaterijalService.save(pomocniMaterijal);

        int databaseSizeBeforeUpdate = pomocniMaterijalRepository.findAll().size();

        // Update the pomocniMaterijal
        PomocniMaterijal updatedPomocniMaterijal = pomocniMaterijalRepository.findById(pomocniMaterijal.getId()).get();
        // Disconnect from session so that the updates on updatedPomocniMaterijal are not directly saved in db
        em.detach(updatedPomocniMaterijal);
        updatedPomocniMaterijal
            .sifra(UPDATED_SIFRA)
            .naziv(UPDATED_NAZIV)
            .vrsta(UPDATED_VRSTA)
            .jedMere(UPDATED_JED_MERE)
            .namena(UPDATED_NAMENA)
            .nabavnaCena(UPDATED_NABAVNA_CENA)
            .napomena(UPDATED_NAPOMENA);

        restPomocniMaterijalMockMvc.perform(put("/api/pomocni-materijals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPomocniMaterijal)))
            .andExpect(status().isOk());

        // Validate the PomocniMaterijal in the database
        List<PomocniMaterijal> pomocniMaterijalList = pomocniMaterijalRepository.findAll();
        assertThat(pomocniMaterijalList).hasSize(databaseSizeBeforeUpdate);
        PomocniMaterijal testPomocniMaterijal = pomocniMaterijalList.get(pomocniMaterijalList.size() - 1);
        assertThat(testPomocniMaterijal.getSifra()).isEqualTo(UPDATED_SIFRA);
        assertThat(testPomocniMaterijal.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testPomocniMaterijal.getVrsta()).isEqualTo(UPDATED_VRSTA);
        assertThat(testPomocniMaterijal.getJedMere()).isEqualTo(UPDATED_JED_MERE);
        assertThat(testPomocniMaterijal.getNamena()).isEqualTo(UPDATED_NAMENA);
        assertThat(testPomocniMaterijal.getNabavnaCena()).isEqualTo(UPDATED_NABAVNA_CENA);
        assertThat(testPomocniMaterijal.getNapomena()).isEqualTo(UPDATED_NAPOMENA);
    }

    @Test
    @Transactional
    public void updateNonExistingPomocniMaterijal() throws Exception {
        int databaseSizeBeforeUpdate = pomocniMaterijalRepository.findAll().size();

        // Create the PomocniMaterijal

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPomocniMaterijalMockMvc.perform(put("/api/pomocni-materijals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pomocniMaterijal)))
            .andExpect(status().isBadRequest());

        // Validate the PomocniMaterijal in the database
        List<PomocniMaterijal> pomocniMaterijalList = pomocniMaterijalRepository.findAll();
        assertThat(pomocniMaterijalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePomocniMaterijal() throws Exception {
        // Initialize the database
        pomocniMaterijalService.save(pomocniMaterijal);

        int databaseSizeBeforeDelete = pomocniMaterijalRepository.findAll().size();

        // Get the pomocniMaterijal
        restPomocniMaterijalMockMvc.perform(delete("/api/pomocni-materijals/{id}", pomocniMaterijal.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PomocniMaterijal> pomocniMaterijalList = pomocniMaterijalRepository.findAll();
        assertThat(pomocniMaterijalList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PomocniMaterijal.class);
        PomocniMaterijal pomocniMaterijal1 = new PomocniMaterijal();
        pomocniMaterijal1.setId(1L);
        PomocniMaterijal pomocniMaterijal2 = new PomocniMaterijal();
        pomocniMaterijal2.setId(pomocniMaterijal1.getId());
        assertThat(pomocniMaterijal1).isEqualTo(pomocniMaterijal2);
        pomocniMaterijal2.setId(2L);
        assertThat(pomocniMaterijal1).isNotEqualTo(pomocniMaterijal2);
        pomocniMaterijal1.setId(null);
        assertThat(pomocniMaterijal1).isNotEqualTo(pomocniMaterijal2);
    }
}
