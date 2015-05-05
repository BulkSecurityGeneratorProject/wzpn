package com.example.wzpn.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.example.wzpn.domain.ObiektSportowy;
import com.example.wzpn.repository.ObiektSportowyRepository;
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
 * REST controller for managing ObiektSportowy.
 */
@RestController
@RequestMapping("/api")
public class ObiektSportowyResource {

    private final Logger log = LoggerFactory.getLogger(ObiektSportowyResource.class);

    @Inject
    private ObiektSportowyRepository obiektSportowyRepository;

    /**
     * POST  /obiektSportowys -> Create a new obiektSportowy.
     */
    @RequestMapping(value = "/obiektSportowys",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody ObiektSportowy obiektSportowy) throws URISyntaxException {
        log.debug("REST request to save ObiektSportowy : {}", obiektSportowy);
        if (obiektSportowy.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new obiektSportowy cannot already have an ID").build();
        }
        obiektSportowyRepository.save(obiektSportowy);
        return ResponseEntity.created(new URI("/api/obiektSportowys/" + obiektSportowy.getId())).build();
    }

    /**
     * PUT  /obiektSportowys -> Updates an existing obiektSportowy.
     */
    @RequestMapping(value = "/obiektSportowys",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody ObiektSportowy obiektSportowy) throws URISyntaxException {
        log.debug("REST request to update ObiektSportowy : {}", obiektSportowy);
        if (obiektSportowy.getId() == null) {
            return create(obiektSportowy);
        }
        obiektSportowyRepository.save(obiektSportowy);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /obiektSportowys -> get all the obiektSportowys.
     */
    @RequestMapping(value = "/obiektSportowys",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<ObiektSportowy> getAll() {
        log.debug("REST request to get all ObiektSportowys");
        return obiektSportowyRepository.findAll();
    }

    /**
     * GET  /obiektSportowys/:id -> get the "id" obiektSportowy.
     */
    @RequestMapping(value = "/obiektSportowys/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ObiektSportowy> get(@PathVariable Long id) {
        log.debug("REST request to get ObiektSportowy : {}", id);
        return Optional.ofNullable(obiektSportowyRepository.findOne(id))
            .map(obiektSportowy -> new ResponseEntity<>(
                obiektSportowy,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /obiektSportowys/:id -> delete the "id" obiektSportowy.
     */
    @RequestMapping(value = "/obiektSportowys/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete ObiektSportowy : {}", id);
        obiektSportowyRepository.delete(id);
    }
}
