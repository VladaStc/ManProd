package edu.man.prod.web.rest;

import edu.man.prod.ManProdApp;

import edu.man.prod.domain.Magacini;
import edu.man.prod.repository.MagaciniRepository;
import edu.man.prod.service.MagaciniService;
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

import edu.man.prod.domain.enumeration.TipMagacina;
/**
 * Test class for the MagaciniResource REST controller.
 *
 * @see MagaciniResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManProdApp.class)
public class MagaciniResourceIntTest {

    private static final String DEFAULT_NAZIV = "AAAAAAAAAA";
    private static final String UPDATED_NAZIV = "BBBBBBBBBB";

    private static final String DEFAULT_LOKACIJA = "AAAAAAAAAA";
    private static final String UPDATED_LOKACIJA = "BBBBBBBBBB";

    private static final String DEFAULT_NAMENA = "AAAAAAAAAA";
    private static final String UPDATED_NAMENA = "BBBBBBBBBB";

    private static final String DEFAULT_RUKOVODILAC = "AAAAAAAAAA";
    private static final String UPDATED_RUKOVODILAC = "BBBBBBBBBB";

    private static final String DEFAULT_NAPOMENA = "AAAAAAAAAA";
    private static final String UPDATED_NAPOMENA = "BBBBBBBBBB";

    private static final TipMagacina DEFAULT_TIP_MAGACINA = TipMagacina.MAGACIN_SIROVINA;
    private static final TipMagacina UPDATED_TIP_MAGACINA = TipMagacina.MAGACIN_GOTOVIH_PROIZVODA;

    @Autowired
    private MagaciniRepository magaciniRepository;
    
    @Autowired
    private MagaciniService magaciniService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMagaciniMockMvc;

    private Magacini magacini;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MagaciniResource magaciniResource = new MagaciniResource(magaciniService);
        this.restMagaciniMockMvc = MockMvcBuilders.standaloneSetup(magaciniResource)
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
    public static Magacini createEntity(EntityManager em) {
        Magacini magacini = new Magacini()
            .naziv(DEFAULT_NAZIV)
            .lokacija(DEFAULT_LOKACIJA)
            .namena(DEFAULT_NAMENA)
            .rukovodilac(DEFAULT_RUKOVODILAC)
            .napomena(DEFAULT_NAPOMENA)
            .tipMagacina(DEFAULT_TIP_MAGACINA);
        return magacini;
    }

    @Before
    public void initTest() {
        magacini = createEntity(em);
    }

    @Test
    @Transactional
    public void createMagacini() throws Exception {
        int databaseSizeBeforeCreate = magaciniRepository.findAll().size();

        // Create the Magacini
        restMagaciniMockMvc.perform(post("/api/magacinis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(magacini)))
            .andExpect(status().isCreated());

        // Validate the Magacini in the database
        List<Magacini> magaciniList = magaciniRepository.findAll();
        assertThat(magaciniList).hasSize(databaseSizeBeforeCreate + 1);
        Magacini testMagacini = magaciniList.get(magaciniList.size() - 1);
        assertThat(testMagacini.getNaziv()).isEqualTo(DEFAULT_NAZIV);
        assertThat(testMagacini.getLokacija()).isEqualTo(DEFAULT_LOKACIJA);
        assertThat(testMagacini.getNamena()).isEqualTo(DEFAULT_NAMENA);
        assertThat(testMagacini.getRukovodilac()).isEqualTo(DEFAULT_RUKOVODILAC);
        assertThat(testMagacini.getNapomena()).isEqualTo(DEFAULT_NAPOMENA);
        assertThat(testMagacini.getTipMagacina()).isEqualTo(DEFAULT_TIP_MAGACINA);
    }

    @Test
    @Transactional
    public void createMagaciniWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = magaciniRepository.findAll().size();

        // Create the Magacini with an existing ID
        magacini.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMagaciniMockMvc.perform(post("/api/magacinis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(magacini)))
            .andExpect(status().isBadRequest());

        // Validate the Magacini in the database
        List<Magacini> magaciniList = magaciniRepository.findAll();
        assertThat(magaciniList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMagacinis() throws Exception {
        // Initialize the database
        magaciniRepository.saveAndFlush(magacini);

        // Get all the magaciniList
        restMagaciniMockMvc.perform(get("/api/magacinis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(magacini.getId().intValue())))
            .andExpect(jsonPath("$.[*].naziv").value(hasItem(DEFAULT_NAZIV.toString())))
            .andExpect(jsonPath("$.[*].lokacija").value(hasItem(DEFAULT_LOKACIJA.toString())))
            .andExpect(jsonPath("$.[*].namena").value(hasItem(DEFAULT_NAMENA.toString())))
            .andExpect(jsonPath("$.[*].rukovodilac").value(hasItem(DEFAULT_RUKOVODILAC.toString())))
            .andExpect(jsonPath("$.[*].napomena").value(hasItem(DEFAULT_NAPOMENA.toString())))
            .andExpect(jsonPath("$.[*].tipMagacina").value(hasItem(DEFAULT_TIP_MAGACINA.toString())));
    }
    
    @Test
    @Transactional
    public void getMagacini() throws Exception {
        // Initialize the database
        magaciniRepository.saveAndFlush(magacini);

        // Get the magacini
        restMagaciniMockMvc.perform(get("/api/magacinis/{id}", magacini.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(magacini.getId().intValue()))
            .andExpect(jsonPath("$.naziv").value(DEFAULT_NAZIV.toString()))
            .andExpect(jsonPath("$.lokacija").value(DEFAULT_LOKACIJA.toString()))
            .andExpect(jsonPath("$.namena").value(DEFAULT_NAMENA.toString()))
            .andExpect(jsonPath("$.rukovodilac").value(DEFAULT_RUKOVODILAC.toString()))
            .andExpect(jsonPath("$.napomena").value(DEFAULT_NAPOMENA.toString()))
            .andExpect(jsonPath("$.tipMagacina").value(DEFAULT_TIP_MAGACINA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMagacini() throws Exception {
        // Get the magacini
        restMagaciniMockMvc.perform(get("/api/magacinis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMagacini() throws Exception {
        // Initialize the database
        magaciniService.save(magacini);

        int databaseSizeBeforeUpdate = magaciniRepository.findAll().size();

        // Update the magacini
        Magacini updatedMagacini = magaciniRepository.findById(magacini.getId()).get();
        // Disconnect from session so that the updates on updatedMagacini are not directly saved in db
        em.detach(updatedMagacini);
        updatedMagacini
            .naziv(UPDATED_NAZIV)
            .lokacija(UPDATED_LOKACIJA)
            .namena(UPDATED_NAMENA)
            .rukovodilac(UPDATED_RUKOVODILAC)
            .napomena(UPDATED_NAPOMENA)
            .tipMagacina(UPDATED_TIP_MAGACINA);

        restMagaciniMockMvc.perform(put("/api/magacinis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMagacini)))
            .andExpect(status().isOk());

        // Validate the Magacini in the database
        List<Magacini> magaciniList = magaciniRepository.findAll();
        assertThat(magaciniList).hasSize(databaseSizeBeforeUpdate);
        Magacini testMagacini = magaciniList.get(magaciniList.size() - 1);
        assertThat(testMagacini.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testMagacini.getLokacija()).isEqualTo(UPDATED_LOKACIJA);
        assertThat(testMagacini.getNamena()).isEqualTo(UPDATED_NAMENA);
        assertThat(testMagacini.getRukovodilac()).isEqualTo(UPDATED_RUKOVODILAC);
        assertThat(testMagacini.getNapomena()).isEqualTo(UPDATED_NAPOMENA);
        assertThat(testMagacini.getTipMagacina()).isEqualTo(UPDATED_TIP_MAGACINA);
    }

    @Test
    @Transactional
    public void updateNonExistingMagacini() throws Exception {
        int databaseSizeBeforeUpdate = magaciniRepository.findAll().size();

        // Create the Magacini

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMagaciniMockMvc.perform(put("/api/magacinis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(magacini)))
            .andExpect(status().isBadRequest());

        // Validate the Magacini in the database
        List<Magacini> magaciniList = magaciniRepository.findAll();
        assertThat(magaciniList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMagacini() throws Exception {
        // Initialize the database
        magaciniService.save(magacini);

        int databaseSizeBeforeDelete = magaciniRepository.findAll().size();

        // Get the magacini
        restMagaciniMockMvc.perform(delete("/api/magacinis/{id}", magacini.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Magacini> magaciniList = magaciniRepository.findAll();
        assertThat(magaciniList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Magacini.class);
        Magacini magacini1 = new Magacini();
        magacini1.setId(1L);
        Magacini magacini2 = new Magacini();
        magacini2.setId(magacini1.getId());
        assertThat(magacini1).isEqualTo(magacini2);
        magacini2.setId(2L);
        assertThat(magacini1).isNotEqualTo(magacini2);
        magacini1.setId(null);
        assertThat(magacini1).isNotEqualTo(magacini2);
    }
}
