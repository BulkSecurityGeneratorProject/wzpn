package com.example.wzpn.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.example.wzpn.domain.Usluga;
import com.example.wzpn.repository.UslugaRepository;
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
 * REST controller for managing Usluga.
 */
@RestController
@RequestMapping("/api")
public class UslugaResource {

    private final Logger log = LoggerFactory.getLogger(UslugaResource.class);

    @Inject
    private UslugaRepository uslugaRepository;

    /**
     * POST  /uslugas -> Create a new usluga.
     */
    @RequestMapping(value = "/uslugas",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody Usluga usluga) throws URISyntaxException {
        log.debug("REST request to save Usluga : {}", usluga);
        if (usluga.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new usluga cannot already have an ID").build();
        }
        uslugaRepository.save(usluga);
        return ResponseEntity.created(new URI("/api/uslugas/" + usluga.getId())).build();
    }

    /**
     * PUT  /uslugas -> Updates an existing usluga.
     */
    @RequestMapping(value = "/uslugas",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody Usluga usluga) throws URISyntaxException {
        log.debug("REST request to update Usluga : {}", usluga);
        if (usluga.getId() == null) {
            return create(usluga);
        }
        uslugaRepository.save(usluga);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /uslugas -> get all the uslugas.
     */
    @RequestMapping(value = "/uslugas",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Usluga> getAll() {
        log.debug("REST request to get all Uslugas");
        return uslugaRepository.findAll();
    }

    /**
     * GET  /uslugas/:id -> get the "id" usluga.
     */
    @RequestMapping(value = "/uslugas/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Usluga> get(@PathVariable Long id) {
        log.debug("REST request to get Usluga : {}", id);
        return Optional.ofNullable(uslugaRepository.findOne(id))
            .map(usluga -> new ResponseEntity<>(
                usluga,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /uslugas/:id -> delete the "id" usluga.
     */
    @RequestMapping(value = "/uslugas/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Usluga : {}", id);
        uslugaRepository.delete(id);
    }
}
