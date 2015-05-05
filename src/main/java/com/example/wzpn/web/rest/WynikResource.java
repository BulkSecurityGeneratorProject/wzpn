package com.example.wzpn.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.example.wzpn.domain.Wynik;
import com.example.wzpn.repository.WynikRepository;
import com.example.wzpn.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
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
 * REST controller for managing Wynik.
 */
@RestController
@RequestMapping("/api")
public class WynikResource {

    private final Logger log = LoggerFactory.getLogger(WynikResource.class);

    @Inject
    private WynikRepository wynikRepository;

    /**
     * POST  /wyniks -> Create a new wynik.
     */
    @RequestMapping(value = "/wyniks",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody Wynik wynik) throws URISyntaxException {
        log.debug("REST request to save Wynik : {}", wynik);
        if (wynik.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new wynik cannot already have an ID").build();
        }
        wynikRepository.save(wynik);
        return ResponseEntity.created(new URI("/api/wyniks/" + wynik.getId())).build();
    }

    /**
     * PUT  /wyniks -> Updates an existing wynik.
     */
    @RequestMapping(value = "/wyniks",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody Wynik wynik) throws URISyntaxException {
        log.debug("REST request to update Wynik : {}", wynik);
        if (wynik.getId() == null) {
            return create(wynik);
        }
        wynikRepository.save(wynik);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /wyniks -> get all the wyniks.
     */
    @RequestMapping(value = "/wyniks",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Wynik>> getAll(@RequestParam(value = "page" , required = false) Integer offset,
                                  @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<Wynik> page = wynikRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/wyniks", offset, limit);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /wyniks/:id -> get the "id" wynik.
     */
    @RequestMapping(value = "/wyniks/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Wynik> get(@PathVariable Long id) {
        log.debug("REST request to get Wynik : {}", id);
        return Optional.ofNullable(wynikRepository.findOne(id))
            .map(wynik -> new ResponseEntity<>(
                wynik,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /wyniks/:id -> delete the "id" wynik.
     */
    @RequestMapping(value = "/wyniks/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Wynik : {}", id);
        wynikRepository.delete(id);
    }
}
