package com.example.wzpn.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.example.wzpn.domain.Druzyna;
import com.example.wzpn.repository.DruzynaRepository;
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
 * REST controller for managing Druzyna.
 */
@RestController
@RequestMapping("/api")
public class DruzynaResource {

    private final Logger log = LoggerFactory.getLogger(DruzynaResource.class);

    @Inject
    private DruzynaRepository druzynaRepository;

    /**
     * POST  /druzynas -> Create a new druzyna.
     */
    @RequestMapping(value = "/druzynas",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody Druzyna druzyna) throws URISyntaxException {
        log.debug("REST request to save Druzyna : {}", druzyna);
        if (druzyna.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new druzyna cannot already have an ID").build();
        }
        druzynaRepository.save(druzyna);
        return ResponseEntity.created(new URI("/api/druzynas/" + druzyna.getId())).build();
    }

    /**
     * PUT  /druzynas -> Updates an existing druzyna.
     */
    @RequestMapping(value = "/druzynas",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody Druzyna druzyna) throws URISyntaxException {
        log.debug("REST request to update Druzyna : {}", druzyna);
        if (druzyna.getId() == null) {
            return create(druzyna);
        }
        druzynaRepository.save(druzyna);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /druzynas -> get all the druzynas.
     */
    @RequestMapping(value = "/druzynas",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Druzyna> getAll() {
        log.debug("REST request to get all Druzynas");
        return druzynaRepository.findAll();
    }

    /**
     * GET  /druzynas/:id -> get the "id" druzyna.
     */
    @RequestMapping(value = "/druzynas/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Druzyna> get(@PathVariable Long id) {
        log.debug("REST request to get Druzyna : {}", id);
        return Optional.ofNullable(druzynaRepository.findOne(id))
            .map(druzyna -> new ResponseEntity<>(
                druzyna,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /druzynas/:id -> delete the "id" druzyna.
     */
    @RequestMapping(value = "/druzynas/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Druzyna : {}", id);
        druzynaRepository.delete(id);
    }
}
