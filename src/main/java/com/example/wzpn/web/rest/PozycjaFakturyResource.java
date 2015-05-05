package com.example.wzpn.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.example.wzpn.domain.PozycjaFaktury;
import com.example.wzpn.repository.PozycjaFakturyRepository;
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
 * REST controller for managing PozycjaFaktury.
 */
@RestController
@RequestMapping("/api")
public class PozycjaFakturyResource {

    private final Logger log = LoggerFactory.getLogger(PozycjaFakturyResource.class);

    @Inject
    private PozycjaFakturyRepository pozycjaFakturyRepository;

    /**
     * POST  /pozycjaFakturys -> Create a new pozycjaFaktury.
     */
    @RequestMapping(value = "/pozycjaFakturys",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody PozycjaFaktury pozycjaFaktury) throws URISyntaxException {
        log.debug("REST request to save PozycjaFaktury : {}", pozycjaFaktury);
        if (pozycjaFaktury.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new pozycjaFaktury cannot already have an ID").build();
        }
        pozycjaFakturyRepository.save(pozycjaFaktury);
        return ResponseEntity.created(new URI("/api/pozycjaFakturys/" + pozycjaFaktury.getId())).build();
    }

    /**
     * PUT  /pozycjaFakturys -> Updates an existing pozycjaFaktury.
     */
    @RequestMapping(value = "/pozycjaFakturys",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody PozycjaFaktury pozycjaFaktury) throws URISyntaxException {
        log.debug("REST request to update PozycjaFaktury : {}", pozycjaFaktury);
        if (pozycjaFaktury.getId() == null) {
            return create(pozycjaFaktury);
        }
        pozycjaFakturyRepository.save(pozycjaFaktury);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /pozycjaFakturys -> get all the pozycjaFakturys.
     */
    @RequestMapping(value = "/pozycjaFakturys",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<PozycjaFaktury> getAll() {
        log.debug("REST request to get all PozycjaFakturys");
        return pozycjaFakturyRepository.findAll();
    }

    /**
     * GET  /pozycjaFakturys/:id -> get the "id" pozycjaFaktury.
     */
    @RequestMapping(value = "/pozycjaFakturys/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PozycjaFaktury> get(@PathVariable Long id) {
        log.debug("REST request to get PozycjaFaktury : {}", id);
        return Optional.ofNullable(pozycjaFakturyRepository.findOne(id))
            .map(pozycjaFaktury -> new ResponseEntity<>(
                pozycjaFaktury,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /pozycjaFakturys/:id -> delete the "id" pozycjaFaktury.
     */
    @RequestMapping(value = "/pozycjaFakturys/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete PozycjaFaktury : {}", id);
        pozycjaFakturyRepository.delete(id);
    }
}
