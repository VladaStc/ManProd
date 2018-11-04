package edu.man.prod.web.rest;

import edu.man.prod.ManProdApp;

import edu.man.prod.domain.Masina;
import edu.man.prod.repository.MasinaRepository;
import edu.man.prod.service.MasinaService;
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

/**
 * Test class for the MasinaResource REST controller.
 *
 * @see MasinaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManProdApp.class)
public class MasinaResourceIntTest {

    private static final String DEFAULT_NAZIV = "AAAAAAAAAA";
    private static final String UPDATED_NAZIV = "BBBBBBBBBB";

    private static final String DEFAULT_LOKACIJA = "AAAAAAAAAA";
    private static final String UPDATED_LOKACIJA = "BBBBBBBBBB";

    private static final String DEFAULT_STRUKTURA = "AAAAAAAAAA";
    private static final String UPDATED_STRUKTURA = "BBBBBBBBBB";

    private static final String DEFAULT_RUKOVODILAC = "AAAAAAAAAA";
    private static final String UPDATED_RUKOVODILAC = "BBBBBBBBBB";

    private static final String DEFAULT_NAPOMENA = "AAAAAAAAAA";
    private static final String UPDATED_NAPOMENA = "BBBBBBBBBB";

    @Autowired
    private MasinaRepository masinaRepository;
    
    @Autowired
    private MasinaService masinaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMasinaMockMvc;

    private Masina masina;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MasinaResource masinaResource = new MasinaResource(masinaService);
        this.restMasinaMockMvc = MockMvcBuilders.standaloneSetup(masinaResource)
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
    public static Masina createEntity(EntityManager em) {
        Masina masina = new Masina()
            .naziv(DEFAULT_NAZIV)
            .lokacija(DEFAULT_LOKACIJA)
            .struktura(DEFAULT_STRUKTURA)
            .rukovodilac(DEFAULT_RUKOVODILAC)
            .napomena(DEFAULT_NAPOMENA);
        return masina;
    }

    @Before
    public void initTest() {
        masina = createEntity(em);
    }

    @Test
    @Transactional
    public void createMasina() throws Exception {
        int databaseSizeBeforeCreate = masinaRepository.findAll().size();

        // Create the Masina
        restMasinaMockMvc.perform(post("/api/masinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(masina)))
            .andExpect(status().isCreated());

        // Validate the Masina in the database
        List<Masina> masinaList = masinaRepository.findAll();
        assertThat(masinaList).hasSize(databaseSizeBeforeCreate + 1);
        Masina testMasina = masinaList.get(masinaList.size() - 1);
        assertThat(testMasina.getNaziv()).isEqualTo(DEFAULT_NAZIV);
        assertThat(testMasina.getLokacija()).isEqualTo(DEFAULT_LOKACIJA);
        assertThat(testMasina.getStruktura()).isEqualTo(DEFAULT_STRUKTURA);
        assertThat(testMasina.getRukovodilac()).isEqualTo(DEFAULT_RUKOVODILAC);
        assertThat(testMasina.getNapomena()).isEqualTo(DEFAULT_NAPOMENA);
    }

    @Test
    @Transactional
    public void createMasinaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = masinaRepository.findAll().size();

        // Create the Masina with an existing ID
        masina.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMasinaMockMvc.perform(post("/api/masinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(masina)))
            .andExpect(status().isBadRequest());

        // Validate the Masina in the database
        List<Masina> masinaList = masinaRepository.findAll();
        assertThat(masinaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMasinas() throws Exception {
        // Initialize the database
        masinaRepository.saveAndFlush(masina);

        // Get all the masinaList
        restMasinaMockMvc.perform(get("/api/masinas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(masina.getId().intValue())))
            .andExpect(jsonPath("$.[*].naziv").value(hasItem(DEFAULT_NAZIV.toString())))
            .andExpect(jsonPath("$.[*].lokacija").value(hasItem(DEFAULT_LOKACIJA.toString())))
            .andExpect(jsonPath("$.[*].struktura").value(hasItem(DEFAULT_STRUKTURA.toString())))
            .andExpect(jsonPath("$.[*].rukovodilac").value(hasItem(DEFAULT_RUKOVODILAC.toString())))
            .andExpect(jsonPath("$.[*].napomena").value(hasItem(DEFAULT_NAPOMENA.toString())));
    }
    
    @Test
    @Transactional
    public void getMasina() throws Exception {
        // Initialize the database
        masinaRepository.saveAndFlush(masina);

        // Get the masina
        restMasinaMockMvc.perform(get("/api/masinas/{id}", masina.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(masina.getId().intValue()))
            .andExpect(jsonPath("$.naziv").value(DEFAULT_NAZIV.toString()))
            .andExpect(jsonPath("$.lokacija").value(DEFAULT_LOKACIJA.toString()))
            .andExpect(jsonPath("$.struktura").value(DEFAULT_STRUKTURA.toString()))
            .andExpect(jsonPath("$.rukovodilac").value(DEFAULT_RUKOVODILAC.toString()))
            .andExpect(jsonPath("$.napomena").value(DEFAULT_NAPOMENA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMasina() throws Exception {
        // Get the masina
        restMasinaMockMvc.perform(get("/api/masinas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMasina() throws Exception {
        // Initialize the database
        masinaService.save(masina);

        int databaseSizeBeforeUpdate = masinaRepository.findAll().size();

        // Update the masina
        Masina updatedMasina = masinaRepository.findById(masina.getId()).get();
        // Disconnect from session so that the updates on updatedMasina are not directly saved in db
        em.detach(updatedMasina);
        updatedMasina
            .naziv(UPDATED_NAZIV)
            .lokacija(UPDATED_LOKACIJA)
            .struktura(UPDATED_STRUKTURA)
            .rukovodilac(UPDATED_RUKOVODILAC)
            .napomena(UPDATED_NAPOMENA);

        restMasinaMockMvc.perform(put("/api/masinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMasina)))
            .andExpect(status().isOk());

        // Validate the Masina in the database
        List<Masina> masinaList = masinaRepository.findAll();
        assertThat(masinaList).hasSize(databaseSizeBeforeUpdate);
        Masina testMasina = masinaList.get(masinaList.size() - 1);
        assertThat(testMasina.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testMasina.getLokacija()).isEqualTo(UPDATED_LOKACIJA);
        assertThat(testMasina.getStruktura()).isEqualTo(UPDATED_STRUKTURA);
        assertThat(testMasina.getRukovodilac()).isEqualTo(UPDATED_RUKOVODILAC);
        assertThat(testMasina.getNapomena()).isEqualTo(UPDATED_NAPOMENA);
    }

    @Test
    @Transactional
    public void updateNonExistingMasina() throws Exception {
        int databaseSizeBeforeUpdate = masinaRepository.findAll().size();

        // Create the Masina

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMasinaMockMvc.perform(put("/api/masinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(masina)))
            .andExpect(status().isBadRequest());

        // Validate the Masina in the database
        List<Masina> masinaList = masinaRepository.findAll();
        assertThat(masinaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMasina() throws Exception {
        // Initialize the database
        masinaService.save(masina);

        int databaseSizeBeforeDelete = masinaRepository.findAll().size();

        // Get the masina
        restMasinaMockMvc.perform(delete("/api/masinas/{id}", masina.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Masina> masinaList = masinaRepository.findAll();
        assertThat(masinaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Masina.class);
        Masina masina1 = new Masina();
        masina1.setId(1L);
        Masina masina2 = new Masina();
        masina2.setId(masina1.getId());
        assertThat(masina1).isEqualTo(masina2);
        masina2.setId(2L);
        assertThat(masina1).isNotEqualTo(masina2);
        masina1.setId(null);
        assertThat(masina1).isNotEqualTo(masina2);
    }
}
