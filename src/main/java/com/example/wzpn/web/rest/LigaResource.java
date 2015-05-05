package com.example.wzpn.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.example.wzpn.domain.Liga;
import com.example.wzpn.repository.LigaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Liga.
 */
@RestController
@RequestMapping("/api")
public class LigaResource {

    private final Logger log = LoggerFactory.getLogger(LigaResource.class);

    @Inject
    private LigaRepository ligaRepository;

    /**
     * POST  /ligas -> Create a new liga.
     */
    @RequestMapping(value = "/ligas",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody Liga liga) throws URISyntaxException {
        log.debug("REST request to save Liga : {}", liga);
        if (liga.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new liga cannot already have an ID").build();
        }
        ligaRepository.save(liga);
        return ResponseEntity.created(new URI("/api/ligas/" + liga.getId())).build();
    }

    /**
     * PUT  /ligas -> Updates an existing liga.
     */
    @RequestMapping(value = "/ligas",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody Liga liga) throws URISyntaxException {
        log.debug("REST request to update Liga : {}", liga);
        if (liga.getId() == null) {
            return create(liga);
        }
        ligaRepository.save(liga);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /ligas -> get all the ligas.
     */
    @RequestMapping(value = "/ligas",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Liga> getAll() {
        log.debug("REST request to get all Ligas");
        return ligaRepository.findAll();
    }

    /**
     * GET  /ligas/:id -> get the "id" liga.
     */
    @RequestMapping(value = "/ligas/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Liga> get(@PathVariable Long id) {
        log.debug("REST request to get Liga : {}", id);
        return Optional.ofNullable(ligaRepository.findOne(id))
            .map(liga -> new ResponseEntity<>(
                liga,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /ligas/:id -> delete the "id" liga.
     */
    @RequestMapping(value = "/ligas/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Liga : {}", id);
        ligaRepository.delete(id);
    }
}
