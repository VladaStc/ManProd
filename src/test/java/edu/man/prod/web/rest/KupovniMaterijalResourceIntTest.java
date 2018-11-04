package edu.man.prod.web.rest;

import edu.man.prod.ManProdApp;

import edu.man.prod.domain.KupovniMaterijal;
import edu.man.prod.repository.KupovniMaterijalRepository;
import edu.man.prod.service.KupovniMaterijalService;
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
 * Test class for the KupovniMaterijalResource REST controller.
 *
 * @see KupovniMaterijalResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManProdApp.class)
public class KupovniMaterijalResourceIntTest {

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
    private KupovniMaterijalRepository kupovniMaterijalRepository;
    
    @Autowired
    private KupovniMaterijalService kupovniMaterijalService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restKupovniMaterijalMockMvc;

    private KupovniMaterijal kupovniMaterijal;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final KupovniMaterijalResource kupovniMaterijalResource = new KupovniMaterijalResource(kupovniMaterijalService);
        this.restKupovniMaterijalMockMvc = MockMvcBuilders.standaloneSetup(kupovniMaterijalResource)
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
    public static KupovniMaterijal createEntity(EntityManager em) {
        KupovniMaterijal kupovniMaterijal = new KupovniMaterijal()
            .sifra(DEFAULT_SIFRA)
            .naziv(DEFAULT_NAZIV)
            .vrsta(DEFAULT_VRSTA)
            .jedMere(DEFAULT_JED_MERE)
            .namena(DEFAULT_NAMENA)
            .nabavnaCena(DEFAULT_NABAVNA_CENA)
            .napomena(DEFAULT_NAPOMENA);
        return kupovniMaterijal;
    }

    @Before
    public void initTest() {
        kupovniMaterijal = createEntity(em);
    }

    @Test
    @Transactional
    public void createKupovniMaterijal() throws Exception {
        int databaseSizeBeforeCreate = kupovniMaterijalRepository.findAll().size();

        // Create the KupovniMaterijal
        restKupovniMaterijalMockMvc.perform(post("/api/kupovni-materijals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kupovniMaterijal)))
            .andExpect(status().isCreated());

        // Validate the KupovniMaterijal in the database
        List<KupovniMaterijal> kupovniMaterijalList = kupovniMaterijalRepository.findAll();
        assertThat(kupovniMaterijalList).hasSize(databaseSizeBeforeCreate + 1);
        KupovniMaterijal testKupovniMaterijal = kupovniMaterijalList.get(kupovniMaterijalList.size() - 1);
        assertThat(testKupovniMaterijal.getSifra()).isEqualTo(DEFAULT_SIFRA);
        assertThat(testKupovniMaterijal.getNaziv()).isEqualTo(DEFAULT_NAZIV);
        assertThat(testKupovniMaterijal.getVrsta()).isEqualTo(DEFAULT_VRSTA);
        assertThat(testKupovniMaterijal.getJedMere()).isEqualTo(DEFAULT_JED_MERE);
        assertThat(testKupovniMaterijal.getNamena()).isEqualTo(DEFAULT_NAMENA);
        assertThat(testKupovniMaterijal.getNabavnaCena()).isEqualTo(DEFAULT_NABAVNA_CENA);
        assertThat(testKupovniMaterijal.getNapomena()).isEqualTo(DEFAULT_NAPOMENA);
    }

    @Test
    @Transactional
    public void createKupovniMaterijalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = kupovniMaterijalRepository.findAll().size();

        // Create the KupovniMaterijal with an existing ID
        kupovniMaterijal.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKupovniMaterijalMockMvc.perform(post("/api/kupovni-materijals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kupovniMaterijal)))
            .andExpect(status().isBadRequest());

        // Validate the KupovniMaterijal in the database
        List<KupovniMaterijal> kupovniMaterijalList = kupovniMaterijalRepository.findAll();
        assertThat(kupovniMaterijalList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllKupovniMaterijals() throws Exception {
        // Initialize the database
        kupovniMaterijalRepository.saveAndFlush(kupovniMaterijal);

        // Get all the kupovniMaterijalList
        restKupovniMaterijalMockMvc.perform(get("/api/kupovni-materijals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kupovniMaterijal.getId().intValue())))
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
    public void getKupovniMaterijal() throws Exception {
        // Initialize the database
        kupovniMaterijalRepository.saveAndFlush(kupovniMaterijal);

        // Get the kupovniMaterijal
        restKupovniMaterijalMockMvc.perform(get("/api/kupovni-materijals/{id}", kupovniMaterijal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(kupovniMaterijal.getId().intValue()))
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
    public void getNonExistingKupovniMaterijal() throws Exception {
        // Get the kupovniMaterijal
        restKupovniMaterijalMockMvc.perform(get("/api/kupovni-materijals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKupovniMaterijal() throws Exception {
        // Initialize the database
        kupovniMaterijalService.save(kupovniMaterijal);

        int databaseSizeBeforeUpdate = kupovniMaterijalRepository.findAll().size();

        // Update the kupovniMaterijal
        KupovniMaterijal updatedKupovniMaterijal = kupovniMaterijalRepository.findById(kupovniMaterijal.getId()).get();
        // Disconnect from session so that the updates on updatedKupovniMaterijal are not directly saved in db
        em.detach(updatedKupovniMaterijal);
        updatedKupovniMaterijal
            .sifra(UPDATED_SIFRA)
            .naziv(UPDATED_NAZIV)
            .vrsta(UPDATED_VRSTA)
            .jedMere(UPDATED_JED_MERE)
            .namena(UPDATED_NAMENA)
            .nabavnaCena(UPDATED_NABAVNA_CENA)
            .napomena(UPDATED_NAPOMENA);

        restKupovniMaterijalMockMvc.perform(put("/api/kupovni-materijals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedKupovniMaterijal)))
            .andExpect(status().isOk());

        // Validate the KupovniMaterijal in the database
        List<KupovniMaterijal> kupovniMaterijalList = kupovniMaterijalRepository.findAll();
        assertThat(kupovniMaterijalList).hasSize(databaseSizeBeforeUpdate);
        KupovniMaterijal testKupovniMaterijal = kupovniMaterijalList.get(kupovniMaterijalList.size() - 1);
        assertThat(testKupovniMaterijal.getSifra()).isEqualTo(UPDATED_SIFRA);
        assertThat(testKupovniMaterijal.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testKupovniMaterijal.getVrsta()).isEqualTo(UPDATED_VRSTA);
        assertThat(testKupovniMaterijal.getJedMere()).isEqualTo(UPDATED_JED_MERE);
        assertThat(testKupovniMaterijal.getNamena()).isEqualTo(UPDATED_NAMENA);
        assertThat(testKupovniMaterijal.getNabavnaCena()).isEqualTo(UPDATED_NABAVNA_CENA);
        assertThat(testKupovniMaterijal.getNapomena()).isEqualTo(UPDATED_NAPOMENA);
    }

    @Test
    @Transactional
    public void updateNonExistingKupovniMaterijal() throws Exception {
        int databaseSizeBeforeUpdate = kupovniMaterijalRepository.findAll().size();

        // Create the KupovniMaterijal

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKupovniMaterijalMockMvc.perform(put("/api/kupovni-materijals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kupovniMaterijal)))
            .andExpect(status().isBadRequest());

        // Validate the KupovniMaterijal in the database
        List<KupovniMaterijal> kupovniMaterijalList = kupovniMaterijalRepository.findAll();
        assertThat(kupovniMaterijalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKupovniMaterijal() throws Exception {
        // Initialize the database
        kupovniMaterijalService.save(kupovniMaterijal);

        int databaseSizeBeforeDelete = kupovniMaterijalRepository.findAll().size();

        // Get the kupovniMaterijal
        restKupovniMaterijalMockMvc.perform(delete("/api/kupovni-materijals/{id}", kupovniMaterijal.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<KupovniMaterijal> kupovniMaterijalList = kupovniMaterijalRepository.findAll();
        assertThat(kupovniMaterijalList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(KupovniMaterijal.class);
        KupovniMaterijal kupovniMaterijal1 = new KupovniMaterijal();
        kupovniMaterijal1.setId(1L);
        KupovniMaterijal kupovniMaterijal2 = new KupovniMaterijal();
        kupovniMaterijal2.setId(kupovniMaterijal1.getId());
        assertThat(kupovniMaterijal1).isEqualTo(kupovniMaterijal2);
        kupovniMaterijal2.setId(2L);
        assertThat(kupovniMaterijal1).isNotEqualTo(kupovniMaterijal2);
        kupovniMaterijal1.setId(null);
        assertThat(kupovniMaterijal1).isNotEqualTo(kupovniMaterijal2);
    }
}
