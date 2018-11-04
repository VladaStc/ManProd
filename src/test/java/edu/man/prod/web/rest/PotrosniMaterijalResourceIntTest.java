package edu.man.prod.web.rest;

import edu.man.prod.ManProdApp;

import edu.man.prod.domain.PotrosniMaterijal;
import edu.man.prod.repository.PotrosniMaterijalRepository;
import edu.man.prod.service.PotrosniMaterijalService;
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
 * Test class for the PotrosniMaterijalResource REST controller.
 *
 * @see PotrosniMaterijalResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManProdApp.class)
public class PotrosniMaterijalResourceIntTest {

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
    private PotrosniMaterijalRepository potrosniMaterijalRepository;
    
    @Autowired
    private PotrosniMaterijalService potrosniMaterijalService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPotrosniMaterijalMockMvc;

    private PotrosniMaterijal potrosniMaterijal;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PotrosniMaterijalResource potrosniMaterijalResource = new PotrosniMaterijalResource(potrosniMaterijalService);
        this.restPotrosniMaterijalMockMvc = MockMvcBuilders.standaloneSetup(potrosniMaterijalResource)
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
    public static PotrosniMaterijal createEntity(EntityManager em) {
        PotrosniMaterijal potrosniMaterijal = new PotrosniMaterijal()
            .sifra(DEFAULT_SIFRA)
            .naziv(DEFAULT_NAZIV)
            .vrsta(DEFAULT_VRSTA)
            .jedMere(DEFAULT_JED_MERE)
            .namena(DEFAULT_NAMENA)
            .nabavnaCena(DEFAULT_NABAVNA_CENA)
            .napomena(DEFAULT_NAPOMENA);
        return potrosniMaterijal;
    }

    @Before
    public void initTest() {
        potrosniMaterijal = createEntity(em);
    }

    @Test
    @Transactional
    public void createPotrosniMaterijal() throws Exception {
        int databaseSizeBeforeCreate = potrosniMaterijalRepository.findAll().size();

        // Create the PotrosniMaterijal
        restPotrosniMaterijalMockMvc.perform(post("/api/potrosni-materijals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(potrosniMaterijal)))
            .andExpect(status().isCreated());

        // Validate the PotrosniMaterijal in the database
        List<PotrosniMaterijal> potrosniMaterijalList = potrosniMaterijalRepository.findAll();
        assertThat(potrosniMaterijalList).hasSize(databaseSizeBeforeCreate + 1);
        PotrosniMaterijal testPotrosniMaterijal = potrosniMaterijalList.get(potrosniMaterijalList.size() - 1);
        assertThat(testPotrosniMaterijal.getSifra()).isEqualTo(DEFAULT_SIFRA);
        assertThat(testPotrosniMaterijal.getNaziv()).isEqualTo(DEFAULT_NAZIV);
        assertThat(testPotrosniMaterijal.getVrsta()).isEqualTo(DEFAULT_VRSTA);
        assertThat(testPotrosniMaterijal.getJedMere()).isEqualTo(DEFAULT_JED_MERE);
        assertThat(testPotrosniMaterijal.getNamena()).isEqualTo(DEFAULT_NAMENA);
        assertThat(testPotrosniMaterijal.getNabavnaCena()).isEqualTo(DEFAULT_NABAVNA_CENA);
        assertThat(testPotrosniMaterijal.getNapomena()).isEqualTo(DEFAULT_NAPOMENA);
    }

    @Test
    @Transactional
    public void createPotrosniMaterijalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = potrosniMaterijalRepository.findAll().size();

        // Create the PotrosniMaterijal with an existing ID
        potrosniMaterijal.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPotrosniMaterijalMockMvc.perform(post("/api/potrosni-materijals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(potrosniMaterijal)))
            .andExpect(status().isBadRequest());

        // Validate the PotrosniMaterijal in the database
        List<PotrosniMaterijal> potrosniMaterijalList = potrosniMaterijalRepository.findAll();
        assertThat(potrosniMaterijalList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPotrosniMaterijals() throws Exception {
        // Initialize the database
        potrosniMaterijalRepository.saveAndFlush(potrosniMaterijal);

        // Get all the potrosniMaterijalList
        restPotrosniMaterijalMockMvc.perform(get("/api/potrosni-materijals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(potrosniMaterijal.getId().intValue())))
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
    public void getPotrosniMaterijal() throws Exception {
        // Initialize the database
        potrosniMaterijalRepository.saveAndFlush(potrosniMaterijal);

        // Get the potrosniMaterijal
        restPotrosniMaterijalMockMvc.perform(get("/api/potrosni-materijals/{id}", potrosniMaterijal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(potrosniMaterijal.getId().intValue()))
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
    public void getNonExistingPotrosniMaterijal() throws Exception {
        // Get the potrosniMaterijal
        restPotrosniMaterijalMockMvc.perform(get("/api/potrosni-materijals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePotrosniMaterijal() throws Exception {
        // Initialize the database
        potrosniMaterijalService.save(potrosniMaterijal);

        int databaseSizeBeforeUpdate = potrosniMaterijalRepository.findAll().size();

        // Update the potrosniMaterijal
        PotrosniMaterijal updatedPotrosniMaterijal = potrosniMaterijalRepository.findById(potrosniMaterijal.getId()).get();
        // Disconnect from session so that the updates on updatedPotrosniMaterijal are not directly saved in db
        em.detach(updatedPotrosniMaterijal);
        updatedPotrosniMaterijal
            .sifra(UPDATED_SIFRA)
            .naziv(UPDATED_NAZIV)
            .vrsta(UPDATED_VRSTA)
            .jedMere(UPDATED_JED_MERE)
            .namena(UPDATED_NAMENA)
            .nabavnaCena(UPDATED_NABAVNA_CENA)
            .napomena(UPDATED_NAPOMENA);

        restPotrosniMaterijalMockMvc.perform(put("/api/potrosni-materijals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPotrosniMaterijal)))
            .andExpect(status().isOk());

        // Validate the PotrosniMaterijal in the database
        List<PotrosniMaterijal> potrosniMaterijalList = potrosniMaterijalRepository.findAll();
        assertThat(potrosniMaterijalList).hasSize(databaseSizeBeforeUpdate);
        PotrosniMaterijal testPotrosniMaterijal = potrosniMaterijalList.get(potrosniMaterijalList.size() - 1);
        assertThat(testPotrosniMaterijal.getSifra()).isEqualTo(UPDATED_SIFRA);
        assertThat(testPotrosniMaterijal.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testPotrosniMaterijal.getVrsta()).isEqualTo(UPDATED_VRSTA);
        assertThat(testPotrosniMaterijal.getJedMere()).isEqualTo(UPDATED_JED_MERE);
        assertThat(testPotrosniMaterijal.getNamena()).isEqualTo(UPDATED_NAMENA);
        assertThat(testPotrosniMaterijal.getNabavnaCena()).isEqualTo(UPDATED_NABAVNA_CENA);
        assertThat(testPotrosniMaterijal.getNapomena()).isEqualTo(UPDATED_NAPOMENA);
    }

    @Test
    @Transactional
    public void updateNonExistingPotrosniMaterijal() throws Exception {
        int databaseSizeBeforeUpdate = potrosniMaterijalRepository.findAll().size();

        // Create the PotrosniMaterijal

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPotrosniMaterijalMockMvc.perform(put("/api/potrosni-materijals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(potrosniMaterijal)))
            .andExpect(status().isBadRequest());

        // Validate the PotrosniMaterijal in the database
        List<PotrosniMaterijal> potrosniMaterijalList = potrosniMaterijalRepository.findAll();
        assertThat(potrosniMaterijalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePotrosniMaterijal() throws Exception {
        // Initialize the database
        potrosniMaterijalService.save(potrosniMaterijal);

        int databaseSizeBeforeDelete = potrosniMaterijalRepository.findAll().size();

        // Get the potrosniMaterijal
        restPotrosniMaterijalMockMvc.perform(delete("/api/potrosni-materijals/{id}", potrosniMaterijal.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PotrosniMaterijal> potrosniMaterijalList = potrosniMaterijalRepository.findAll();
        assertThat(potrosniMaterijalList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PotrosniMaterijal.class);
        PotrosniMaterijal potrosniMaterijal1 = new PotrosniMaterijal();
        potrosniMaterijal1.setId(1L);
        PotrosniMaterijal potrosniMaterijal2 = new PotrosniMaterijal();
        potrosniMaterijal2.setId(potrosniMaterijal1.getId());
        assertThat(potrosniMaterijal1).isEqualTo(potrosniMaterijal2);
        potrosniMaterijal2.setId(2L);
        assertThat(potrosniMaterijal1).isNotEqualTo(potrosniMaterijal2);
        potrosniMaterijal1.setId(null);
        assertThat(potrosniMaterijal1).isNotEqualTo(potrosniMaterijal2);
    }
}
