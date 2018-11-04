package edu.man.prod.web.rest;

import edu.man.prod.ManProdApp;

import edu.man.prod.domain.OstaliMaterijali;
import edu.man.prod.repository.OstaliMaterijaliRepository;
import edu.man.prod.service.OstaliMaterijaliService;
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
 * Test class for the OstaliMaterijaliResource REST controller.
 *
 * @see OstaliMaterijaliResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManProdApp.class)
public class OstaliMaterijaliResourceIntTest {

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
    private OstaliMaterijaliRepository ostaliMaterijaliRepository;
    
    @Autowired
    private OstaliMaterijaliService ostaliMaterijaliService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOstaliMaterijaliMockMvc;

    private OstaliMaterijali ostaliMaterijali;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OstaliMaterijaliResource ostaliMaterijaliResource = new OstaliMaterijaliResource(ostaliMaterijaliService);
        this.restOstaliMaterijaliMockMvc = MockMvcBuilders.standaloneSetup(ostaliMaterijaliResource)
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
    public static OstaliMaterijali createEntity(EntityManager em) {
        OstaliMaterijali ostaliMaterijali = new OstaliMaterijali()
            .sifra(DEFAULT_SIFRA)
            .naziv(DEFAULT_NAZIV)
            .vrsta(DEFAULT_VRSTA)
            .jedMere(DEFAULT_JED_MERE)
            .namena(DEFAULT_NAMENA)
            .nabavnaCena(DEFAULT_NABAVNA_CENA)
            .napomena(DEFAULT_NAPOMENA);
        return ostaliMaterijali;
    }

    @Before
    public void initTest() {
        ostaliMaterijali = createEntity(em);
    }

    @Test
    @Transactional
    public void createOstaliMaterijali() throws Exception {
        int databaseSizeBeforeCreate = ostaliMaterijaliRepository.findAll().size();

        // Create the OstaliMaterijali
        restOstaliMaterijaliMockMvc.perform(post("/api/ostali-materijalis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ostaliMaterijali)))
            .andExpect(status().isCreated());

        // Validate the OstaliMaterijali in the database
        List<OstaliMaterijali> ostaliMaterijaliList = ostaliMaterijaliRepository.findAll();
        assertThat(ostaliMaterijaliList).hasSize(databaseSizeBeforeCreate + 1);
        OstaliMaterijali testOstaliMaterijali = ostaliMaterijaliList.get(ostaliMaterijaliList.size() - 1);
        assertThat(testOstaliMaterijali.getSifra()).isEqualTo(DEFAULT_SIFRA);
        assertThat(testOstaliMaterijali.getNaziv()).isEqualTo(DEFAULT_NAZIV);
        assertThat(testOstaliMaterijali.getVrsta()).isEqualTo(DEFAULT_VRSTA);
        assertThat(testOstaliMaterijali.getJedMere()).isEqualTo(DEFAULT_JED_MERE);
        assertThat(testOstaliMaterijali.getNamena()).isEqualTo(DEFAULT_NAMENA);
        assertThat(testOstaliMaterijali.getNabavnaCena()).isEqualTo(DEFAULT_NABAVNA_CENA);
        assertThat(testOstaliMaterijali.getNapomena()).isEqualTo(DEFAULT_NAPOMENA);
    }

    @Test
    @Transactional
    public void createOstaliMaterijaliWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ostaliMaterijaliRepository.findAll().size();

        // Create the OstaliMaterijali with an existing ID
        ostaliMaterijali.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOstaliMaterijaliMockMvc.perform(post("/api/ostali-materijalis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ostaliMaterijali)))
            .andExpect(status().isBadRequest());

        // Validate the OstaliMaterijali in the database
        List<OstaliMaterijali> ostaliMaterijaliList = ostaliMaterijaliRepository.findAll();
        assertThat(ostaliMaterijaliList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOstaliMaterijalis() throws Exception {
        // Initialize the database
        ostaliMaterijaliRepository.saveAndFlush(ostaliMaterijali);

        // Get all the ostaliMaterijaliList
        restOstaliMaterijaliMockMvc.perform(get("/api/ostali-materijalis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ostaliMaterijali.getId().intValue())))
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
    public void getOstaliMaterijali() throws Exception {
        // Initialize the database
        ostaliMaterijaliRepository.saveAndFlush(ostaliMaterijali);

        // Get the ostaliMaterijali
        restOstaliMaterijaliMockMvc.perform(get("/api/ostali-materijalis/{id}", ostaliMaterijali.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ostaliMaterijali.getId().intValue()))
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
    public void getNonExistingOstaliMaterijali() throws Exception {
        // Get the ostaliMaterijali
        restOstaliMaterijaliMockMvc.perform(get("/api/ostali-materijalis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOstaliMaterijali() throws Exception {
        // Initialize the database
        ostaliMaterijaliService.save(ostaliMaterijali);

        int databaseSizeBeforeUpdate = ostaliMaterijaliRepository.findAll().size();

        // Update the ostaliMaterijali
        OstaliMaterijali updatedOstaliMaterijali = ostaliMaterijaliRepository.findById(ostaliMaterijali.getId()).get();
        // Disconnect from session so that the updates on updatedOstaliMaterijali are not directly saved in db
        em.detach(updatedOstaliMaterijali);
        updatedOstaliMaterijali
            .sifra(UPDATED_SIFRA)
            .naziv(UPDATED_NAZIV)
            .vrsta(UPDATED_VRSTA)
            .jedMere(UPDATED_JED_MERE)
            .namena(UPDATED_NAMENA)
            .nabavnaCena(UPDATED_NABAVNA_CENA)
            .napomena(UPDATED_NAPOMENA);

        restOstaliMaterijaliMockMvc.perform(put("/api/ostali-materijalis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOstaliMaterijali)))
            .andExpect(status().isOk());

        // Validate the OstaliMaterijali in the database
        List<OstaliMaterijali> ostaliMaterijaliList = ostaliMaterijaliRepository.findAll();
        assertThat(ostaliMaterijaliList).hasSize(databaseSizeBeforeUpdate);
        OstaliMaterijali testOstaliMaterijali = ostaliMaterijaliList.get(ostaliMaterijaliList.size() - 1);
        assertThat(testOstaliMaterijali.getSifra()).isEqualTo(UPDATED_SIFRA);
        assertThat(testOstaliMaterijali.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testOstaliMaterijali.getVrsta()).isEqualTo(UPDATED_VRSTA);
        assertThat(testOstaliMaterijali.getJedMere()).isEqualTo(UPDATED_JED_MERE);
        assertThat(testOstaliMaterijali.getNamena()).isEqualTo(UPDATED_NAMENA);
        assertThat(testOstaliMaterijali.getNabavnaCena()).isEqualTo(UPDATED_NABAVNA_CENA);
        assertThat(testOstaliMaterijali.getNapomena()).isEqualTo(UPDATED_NAPOMENA);
    }

    @Test
    @Transactional
    public void updateNonExistingOstaliMaterijali() throws Exception {
        int databaseSizeBeforeUpdate = ostaliMaterijaliRepository.findAll().size();

        // Create the OstaliMaterijali

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOstaliMaterijaliMockMvc.perform(put("/api/ostali-materijalis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ostaliMaterijali)))
            .andExpect(status().isBadRequest());

        // Validate the OstaliMaterijali in the database
        List<OstaliMaterijali> ostaliMaterijaliList = ostaliMaterijaliRepository.findAll();
        assertThat(ostaliMaterijaliList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOstaliMaterijali() throws Exception {
        // Initialize the database
        ostaliMaterijaliService.save(ostaliMaterijali);

        int databaseSizeBeforeDelete = ostaliMaterijaliRepository.findAll().size();

        // Get the ostaliMaterijali
        restOstaliMaterijaliMockMvc.perform(delete("/api/ostali-materijalis/{id}", ostaliMaterijali.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<OstaliMaterijali> ostaliMaterijaliList = ostaliMaterijaliRepository.findAll();
        assertThat(ostaliMaterijaliList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OstaliMaterijali.class);
        OstaliMaterijali ostaliMaterijali1 = new OstaliMaterijali();
        ostaliMaterijali1.setId(1L);
        OstaliMaterijali ostaliMaterijali2 = new OstaliMaterijali();
        ostaliMaterijali2.setId(ostaliMaterijali1.getId());
        assertThat(ostaliMaterijali1).isEqualTo(ostaliMaterijali2);
        ostaliMaterijali2.setId(2L);
        assertThat(ostaliMaterijali1).isNotEqualTo(ostaliMaterijali2);
        ostaliMaterijali1.setId(null);
        assertThat(ostaliMaterijali1).isNotEqualTo(ostaliMaterijali2);
    }
}
