package edu.man.prod.web.rest;

import edu.man.prod.ManProdApp;

import edu.man.prod.domain.KonstruktivnaSastavnica;
import edu.man.prod.repository.KonstruktivnaSastavnicaRepository;
import edu.man.prod.service.KonstruktivnaSastavnicaService;
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
 * Test class for the KonstruktivnaSastavnicaResource REST controller.
 *
 * @see KonstruktivnaSastavnicaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManProdApp.class)
public class KonstruktivnaSastavnicaResourceIntTest {

    private static final String DEFAULT_SIFRA_DELA = "AAAAAAAAAA";
    private static final String UPDATED_SIFRA_DELA = "BBBBBBBBBB";

    private static final String DEFAULT_NAZIV_DELA = "AAAAAAAAAA";
    private static final String UPDATED_NAZIV_DELA = "BBBBBBBBBB";

    private static final Double DEFAULT_KOLICINA = 1D;
    private static final Double UPDATED_KOLICINA = 2D;

    @Autowired
    private KonstruktivnaSastavnicaRepository konstruktivnaSastavnicaRepository;
    
    @Autowired
    private KonstruktivnaSastavnicaService konstruktivnaSastavnicaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restKonstruktivnaSastavnicaMockMvc;

    private KonstruktivnaSastavnica konstruktivnaSastavnica;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final KonstruktivnaSastavnicaResource konstruktivnaSastavnicaResource = new KonstruktivnaSastavnicaResource(konstruktivnaSastavnicaService);
        this.restKonstruktivnaSastavnicaMockMvc = MockMvcBuilders.standaloneSetup(konstruktivnaSastavnicaResource)
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
    public static KonstruktivnaSastavnica createEntity(EntityManager em) {
        KonstruktivnaSastavnica konstruktivnaSastavnica = new KonstruktivnaSastavnica()
            .sifraDela(DEFAULT_SIFRA_DELA)
            .nazivDela(DEFAULT_NAZIV_DELA)
            .kolicina(DEFAULT_KOLICINA);
        return konstruktivnaSastavnica;
    }

    @Before
    public void initTest() {
        konstruktivnaSastavnica = createEntity(em);
    }

    @Test
    @Transactional
    public void createKonstruktivnaSastavnica() throws Exception {
        int databaseSizeBeforeCreate = konstruktivnaSastavnicaRepository.findAll().size();

        // Create the KonstruktivnaSastavnica
        restKonstruktivnaSastavnicaMockMvc.perform(post("/api/konstruktivna-sastavnicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(konstruktivnaSastavnica)))
            .andExpect(status().isCreated());

        // Validate the KonstruktivnaSastavnica in the database
        List<KonstruktivnaSastavnica> konstruktivnaSastavnicaList = konstruktivnaSastavnicaRepository.findAll();
        assertThat(konstruktivnaSastavnicaList).hasSize(databaseSizeBeforeCreate + 1);
        KonstruktivnaSastavnica testKonstruktivnaSastavnica = konstruktivnaSastavnicaList.get(konstruktivnaSastavnicaList.size() - 1);
        assertThat(testKonstruktivnaSastavnica.getSifraDela()).isEqualTo(DEFAULT_SIFRA_DELA);
        assertThat(testKonstruktivnaSastavnica.getNazivDela()).isEqualTo(DEFAULT_NAZIV_DELA);
        assertThat(testKonstruktivnaSastavnica.getKolicina()).isEqualTo(DEFAULT_KOLICINA);
    }

    @Test
    @Transactional
    public void createKonstruktivnaSastavnicaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = konstruktivnaSastavnicaRepository.findAll().size();

        // Create the KonstruktivnaSastavnica with an existing ID
        konstruktivnaSastavnica.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKonstruktivnaSastavnicaMockMvc.perform(post("/api/konstruktivna-sastavnicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(konstruktivnaSastavnica)))
            .andExpect(status().isBadRequest());

        // Validate the KonstruktivnaSastavnica in the database
        List<KonstruktivnaSastavnica> konstruktivnaSastavnicaList = konstruktivnaSastavnicaRepository.findAll();
        assertThat(konstruktivnaSastavnicaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllKonstruktivnaSastavnicas() throws Exception {
        // Initialize the database
        konstruktivnaSastavnicaRepository.saveAndFlush(konstruktivnaSastavnica);

        // Get all the konstruktivnaSastavnicaList
        restKonstruktivnaSastavnicaMockMvc.perform(get("/api/konstruktivna-sastavnicas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(konstruktivnaSastavnica.getId().intValue())))
            .andExpect(jsonPath("$.[*].sifraDela").value(hasItem(DEFAULT_SIFRA_DELA.toString())))
            .andExpect(jsonPath("$.[*].nazivDela").value(hasItem(DEFAULT_NAZIV_DELA.toString())))
            .andExpect(jsonPath("$.[*].kolicina").value(hasItem(DEFAULT_KOLICINA.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getKonstruktivnaSastavnica() throws Exception {
        // Initialize the database
        konstruktivnaSastavnicaRepository.saveAndFlush(konstruktivnaSastavnica);

        // Get the konstruktivnaSastavnica
        restKonstruktivnaSastavnicaMockMvc.perform(get("/api/konstruktivna-sastavnicas/{id}", konstruktivnaSastavnica.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(konstruktivnaSastavnica.getId().intValue()))
            .andExpect(jsonPath("$.sifraDela").value(DEFAULT_SIFRA_DELA.toString()))
            .andExpect(jsonPath("$.nazivDela").value(DEFAULT_NAZIV_DELA.toString()))
            .andExpect(jsonPath("$.kolicina").value(DEFAULT_KOLICINA.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingKonstruktivnaSastavnica() throws Exception {
        // Get the konstruktivnaSastavnica
        restKonstruktivnaSastavnicaMockMvc.perform(get("/api/konstruktivna-sastavnicas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKonstruktivnaSastavnica() throws Exception {
        // Initialize the database
        konstruktivnaSastavnicaService.save(konstruktivnaSastavnica);

        int databaseSizeBeforeUpdate = konstruktivnaSastavnicaRepository.findAll().size();

        // Update the konstruktivnaSastavnica
        KonstruktivnaSastavnica updatedKonstruktivnaSastavnica = konstruktivnaSastavnicaRepository.findById(konstruktivnaSastavnica.getId()).get();
        // Disconnect from session so that the updates on updatedKonstruktivnaSastavnica are not directly saved in db
        em.detach(updatedKonstruktivnaSastavnica);
        updatedKonstruktivnaSastavnica
            .sifraDela(UPDATED_SIFRA_DELA)
            .nazivDela(UPDATED_NAZIV_DELA)
            .kolicina(UPDATED_KOLICINA);

        restKonstruktivnaSastavnicaMockMvc.perform(put("/api/konstruktivna-sastavnicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedKonstruktivnaSastavnica)))
            .andExpect(status().isOk());

        // Validate the KonstruktivnaSastavnica in the database
        List<KonstruktivnaSastavnica> konstruktivnaSastavnicaList = konstruktivnaSastavnicaRepository.findAll();
        assertThat(konstruktivnaSastavnicaList).hasSize(databaseSizeBeforeUpdate);
        KonstruktivnaSastavnica testKonstruktivnaSastavnica = konstruktivnaSastavnicaList.get(konstruktivnaSastavnicaList.size() - 1);
        assertThat(testKonstruktivnaSastavnica.getSifraDela()).isEqualTo(UPDATED_SIFRA_DELA);
        assertThat(testKonstruktivnaSastavnica.getNazivDela()).isEqualTo(UPDATED_NAZIV_DELA);
        assertThat(testKonstruktivnaSastavnica.getKolicina()).isEqualTo(UPDATED_KOLICINA);
    }

    @Test
    @Transactional
    public void updateNonExistingKonstruktivnaSastavnica() throws Exception {
        int databaseSizeBeforeUpdate = konstruktivnaSastavnicaRepository.findAll().size();

        // Create the KonstruktivnaSastavnica

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKonstruktivnaSastavnicaMockMvc.perform(put("/api/konstruktivna-sastavnicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(konstruktivnaSastavnica)))
            .andExpect(status().isBadRequest());

        // Validate the KonstruktivnaSastavnica in the database
        List<KonstruktivnaSastavnica> konstruktivnaSastavnicaList = konstruktivnaSastavnicaRepository.findAll();
        assertThat(konstruktivnaSastavnicaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKonstruktivnaSastavnica() throws Exception {
        // Initialize the database
        konstruktivnaSastavnicaService.save(konstruktivnaSastavnica);

        int databaseSizeBeforeDelete = konstruktivnaSastavnicaRepository.findAll().size();

        // Get the konstruktivnaSastavnica
        restKonstruktivnaSastavnicaMockMvc.perform(delete("/api/konstruktivna-sastavnicas/{id}", konstruktivnaSastavnica.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<KonstruktivnaSastavnica> konstruktivnaSastavnicaList = konstruktivnaSastavnicaRepository.findAll();
        assertThat(konstruktivnaSastavnicaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(KonstruktivnaSastavnica.class);
        KonstruktivnaSastavnica konstruktivnaSastavnica1 = new KonstruktivnaSastavnica();
        konstruktivnaSastavnica1.setId(1L);
        KonstruktivnaSastavnica konstruktivnaSastavnica2 = new KonstruktivnaSastavnica();
        konstruktivnaSastavnica2.setId(konstruktivnaSastavnica1.getId());
        assertThat(konstruktivnaSastavnica1).isEqualTo(konstruktivnaSastavnica2);
        konstruktivnaSastavnica2.setId(2L);
        assertThat(konstruktivnaSastavnica1).isNotEqualTo(konstruktivnaSastavnica2);
        konstruktivnaSastavnica1.setId(null);
        assertThat(konstruktivnaSastavnica1).isNotEqualTo(konstruktivnaSastavnica2);
    }
}
