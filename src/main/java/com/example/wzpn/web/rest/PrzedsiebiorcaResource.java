package com.example.wzpn.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.example.wzpn.domain.Przedsiebiorca;
import com.example.wzpn.repository.PrzedsiebiorcaRepository;
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
 * REST controller for managing Przedsiebiorca.
 */
@RestController
@RequestMapping("/api")
public class PrzedsiebiorcaResource {

    private final Logger log = LoggerFactory.getLogger(PrzedsiebiorcaResource.class);

    @Inject
    private PrzedsiebiorcaRepository przedsiebiorcaRepository;

    /**
     * POST  /przedsiebiorcas -> Create a new przedsiebiorca.
     */
    @RequestMapping(value = "/przedsiebiorcas",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody Przedsiebiorca przedsiebiorca) throws URISyntaxException {
        log.debug("REST request to save Przedsiebiorca : {}", przedsiebiorca);
        if (przedsiebiorca.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new przedsiebiorca cannot already have an ID").build();
        }
        przedsiebiorcaRepository.save(przedsiebiorca);
        return ResponseEntity.created(new URI("/api/przedsiebiorcas/" + przedsiebiorca.getId())).build();
    }

    /**
     * PUT  /przedsiebiorcas -> Updates an existing przedsiebiorca.
     */
    @RequestMapping(value = "/przedsiebiorcas",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody Przedsiebiorca przedsiebiorca) throws URISyntaxException {
        log.debug("REST request to update Przedsiebiorca : {}", przedsiebiorca);
        if (przedsiebiorca.getId() == null) {
            return create(przedsiebiorca);
        }
        przedsiebiorcaRepository.save(przedsiebiorca);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /przedsiebiorcas -> get all the przedsiebiorcas.
     */
    @RequestMapping(value = "/przedsiebiorcas",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Przedsiebiorca> getAll() {
        log.debug("REST request to get all Przedsiebiorcas");
        return przedsiebiorcaRepository.findAll();
    }

    /**
     * GET  /przedsiebiorcas/:id -> get the "id" przedsiebiorca.
     */
    @RequestMapping(value = "/przedsiebiorcas/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Przedsiebiorca> get(@PathVariable Long id) {
        log.debug("REST request to get Przedsiebiorca : {}", id);
        return Optional.ofNullable(przedsiebiorcaRepository.findOne(id))
            .map(przedsiebiorca -> new ResponseEntity<>(
                przedsiebiorca,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /przedsiebiorcas/:id -> delete the "id" przedsiebiorca.
     */
    @RequestMapping(value = "/przedsiebiorcas/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Przedsiebiorca : {}", id);
        przedsiebiorcaRepository.delete(id);
    }
}
