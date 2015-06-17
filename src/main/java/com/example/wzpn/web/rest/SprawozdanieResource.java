package com.example.wzpn.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.example.wzpn.domain.Sprawozdanie;
import com.example.wzpn.repository.SprawozdanieRepository;
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
 * REST controller for managing Sprawozdanie.
 */
@RestController
@RequestMapping("/api")
public class SprawozdanieResource {

    private final Logger log = LoggerFactory.getLogger(SprawozdanieResource.class);

    @Inject
    private SprawozdanieRepository sprawozdanieRepository;

    /**
     * POST  /sprawozdanies -> Create a new sprawozdanie.
     */
    @RequestMapping(value = "/sprawozdanies",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody Sprawozdanie sprawozdanie) throws URISyntaxException {
        log.debug("REST request to save Sprawozdanie : {}", sprawozdanie);
        if (sprawozdanie.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new sprawozdanie cannot already have an ID").build();
        }
        sprawozdanieRepository.save(sprawozdanie);
        return ResponseEntity.created(new URI("/api/sprawozdanies/" + sprawozdanie.getId())).build();
    }

    /**
     * PUT  /sprawozdanies -> Updates an existing sprawozdanie.
     */
    @RequestMapping(value = "/sprawozdanies",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody Sprawozdanie sprawozdanie) throws URISyntaxException {
        log.debug("REST request to update Sprawozdanie : {}", sprawozdanie);
        if (sprawozdanie.getId() == null) {
            return create(sprawozdanie);
        }
        sprawozdanieRepository.save(sprawozdanie);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /sprawozdanies -> get all the sprawozdanies.
     */
    @RequestMapping(value = "/sprawozdanies",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Sprawozdanie> getAll() {
        log.debug("REST request to get all Sprawozdanies");
        return sprawozdanieRepository.findAll();
    }

    /**
     * GET  /sprawozdanies/:id -> get the "id" sprawozdanie.
     */
    @RequestMapping(value = "/sprawozdanies/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Sprawozdanie> get(@PathVariable Long id) {
        log.debug("REST request to get Sprawozdanie : {}", id);
        return Optional.ofNullable(sprawozdanieRepository.findOne(id))
            .map(sprawozdanie -> new ResponseEntity<>(
                sprawozdanie,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /sprawozdanies/:id -> delete the "id" sprawozdanie.
     */
    @RequestMapping(value = "/sprawozdanies/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Sprawozdanie : {}", id);
        sprawozdanieRepository.delete(id);
    }
}
