package edu.man.prod.web.rest;

import edu.man.prod.ManProdApp;

import edu.man.prod.domain.Oprema;
import edu.man.prod.repository.OpremaRepository;
import edu.man.prod.service.OpremaService;
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
 * Test class for the OpremaResource REST controller.
 *
 * @see OpremaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManProdApp.class)
public class OpremaResourceIntTest {

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
    private OpremaRepository opremaRepository;
    
    @Autowired
    private OpremaService opremaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOpremaMockMvc;

    private Oprema oprema;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OpremaResource opremaResource = new OpremaResource(opremaService);
        this.restOpremaMockMvc = MockMvcBuilders.standaloneSetup(opremaResource)
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
    public static Oprema createEntity(EntityManager em) {
        Oprema oprema = new Oprema()
            .naziv(DEFAULT_NAZIV)
            .lokacija(DEFAULT_LOKACIJA)
            .struktura(DEFAULT_STRUKTURA)
            .rukovodilac(DEFAULT_RUKOVODILAC)
            .napomena(DEFAULT_NAPOMENA);
        return oprema;
    }

    @Before
    public void initTest() {
        oprema = createEntity(em);
    }

    @Test
    @Transactional
    public void createOprema() throws Exception {
        int databaseSizeBeforeCreate = opremaRepository.findAll().size();

        // Create the Oprema
        restOpremaMockMvc.perform(post("/api/opremas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(oprema)))
            .andExpect(status().isCreated());

        // Validate the Oprema in the database
        List<Oprema> opremaList = opremaRepository.findAll();
        assertThat(opremaList).hasSize(databaseSizeBeforeCreate + 1);
        Oprema testOprema = opremaList.get(opremaList.size() - 1);
        assertThat(testOprema.getNaziv()).isEqualTo(DEFAULT_NAZIV);
        assertThat(testOprema.getLokacija()).isEqualTo(DEFAULT_LOKACIJA);
        assertThat(testOprema.getStruktura()).isEqualTo(DEFAULT_STRUKTURA);
        assertThat(testOprema.getRukovodilac()).isEqualTo(DEFAULT_RUKOVODILAC);
        assertThat(testOprema.getNapomena()).isEqualTo(DEFAULT_NAPOMENA);
    }

    @Test
    @Transactional
    public void createOpremaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = opremaRepository.findAll().size();

        // Create the Oprema with an existing ID
        oprema.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOpremaMockMvc.perform(post("/api/opremas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(oprema)))
            .andExpect(status().isBadRequest());

        // Validate the Oprema in the database
        List<Oprema> opremaList = opremaRepository.findAll();
        assertThat(opremaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOpremas() throws Exception {
        // Initialize the database
        opremaRepository.saveAndFlush(oprema);

        // Get all the opremaList
        restOpremaMockMvc.perform(get("/api/opremas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(oprema.getId().intValue())))
            .andExpect(jsonPath("$.[*].naziv").value(hasItem(DEFAULT_NAZIV.toString())))
            .andExpect(jsonPath("$.[*].lokacija").value(hasItem(DEFAULT_LOKACIJA.toString())))
            .andExpect(jsonPath("$.[*].struktura").value(hasItem(DEFAULT_STRUKTURA.toString())))
            .andExpect(jsonPath("$.[*].rukovodilac").value(hasItem(DEFAULT_RUKOVODILAC.toString())))
            .andExpect(jsonPath("$.[*].napomena").value(hasItem(DEFAULT_NAPOMENA.toString())));
    }
    
    @Test
    @Transactional
    public void getOprema() throws Exception {
        // Initialize the database
        opremaRepository.saveAndFlush(oprema);

        // Get the oprema
        restOpremaMockMvc.perform(get("/api/opremas/{id}", oprema.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(oprema.getId().intValue()))
            .andExpect(jsonPath("$.naziv").value(DEFAULT_NAZIV.toString()))
            .andExpect(jsonPath("$.lokacija").value(DEFAULT_LOKACIJA.toString()))
            .andExpect(jsonPath("$.struktura").value(DEFAULT_STRUKTURA.toString()))
            .andExpect(jsonPath("$.rukovodilac").value(DEFAULT_RUKOVODILAC.toString()))
            .andExpect(jsonPath("$.napomena").value(DEFAULT_NAPOMENA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOprema() throws Exception {
        // Get the oprema
        restOpremaMockMvc.perform(get("/api/opremas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOprema() throws Exception {
        // Initialize the database
        opremaService.save(oprema);

        int databaseSizeBeforeUpdate = opremaRepository.findAll().size();

        // Update the oprema
        Oprema updatedOprema = opremaRepository.findById(oprema.getId()).get();
        // Disconnect from session so that the updates on updatedOprema are not directly saved in db
        em.detach(updatedOprema);
        updatedOprema
            .naziv(UPDATED_NAZIV)
            .lokacija(UPDATED_LOKACIJA)
            .struktura(UPDATED_STRUKTURA)
            .rukovodilac(UPDATED_RUKOVODILAC)
            .napomena(UPDATED_NAPOMENA);

        restOpremaMockMvc.perform(put("/api/opremas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOprema)))
            .andExpect(status().isOk());

        // Validate the Oprema in the database
        List<Oprema> opremaList = opremaRepository.findAll();
        assertThat(opremaList).hasSize(databaseSizeBeforeUpdate);
        Oprema testOprema = opremaList.get(opremaList.size() - 1);
        assertThat(testOprema.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testOprema.getLokacija()).isEqualTo(UPDATED_LOKACIJA);
        assertThat(testOprema.getStruktura()).isEqualTo(UPDATED_STRUKTURA);
        assertThat(testOprema.getRukovodilac()).isEqualTo(UPDATED_RUKOVODILAC);
        assertThat(testOprema.getNapomena()).isEqualTo(UPDATED_NAPOMENA);
    }

    @Test
    @Transactional
    public void updateNonExistingOprema() throws Exception {
        int databaseSizeBeforeUpdate = opremaRepository.findAll().size();

        // Create the Oprema

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOpremaMockMvc.perform(put("/api/opremas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(oprema)))
            .andExpect(status().isBadRequest());

        // Validate the Oprema in the database
        List<Oprema> opremaList = opremaRepository.findAll();
        assertThat(opremaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOprema() throws Exception {
        // Initialize the database
        opremaService.save(oprema);

        int databaseSizeBeforeDelete = opremaRepository.findAll().size();

        // Get the oprema
        restOpremaMockMvc.perform(delete("/api/opremas/{id}", oprema.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Oprema> opremaList = opremaRepository.findAll();
        assertThat(opremaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Oprema.class);
        Oprema oprema1 = new Oprema();
        oprema1.setId(1L);
        Oprema oprema2 = new Oprema();
        oprema2.setId(oprema1.getId());
        assertThat(oprema1).isEqualTo(oprema2);
        oprema2.setId(2L);
        assertThat(oprema1).isNotEqualTo(oprema2);
        oprema1.setId(null);
        assertThat(oprema1).isNotEqualTo(oprema2);
    }
}
