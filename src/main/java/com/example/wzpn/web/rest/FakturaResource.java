package com.example.wzpn.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.example.wzpn.domain.Faktura;
import com.example.wzpn.repository.FakturaRepository;
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
 * REST controller for managing Faktura.
 */
@RestController
@RequestMapping("/api")
public class FakturaResource {

    private final Logger log = LoggerFactory.getLogger(FakturaResource.class);

    @Inject
    private FakturaRepository fakturaRepository;

    /**
     * POST  /fakturas -> Create a new faktura.
     */
    @RequestMapping(value = "/fakturas",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody Faktura faktura) throws URISyntaxException {
        log.debug("REST request to save Faktura : {}", faktura);
        if (faktura.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new faktura cannot already have an ID").build();
        }
        fakturaRepository.save(faktura);
        return ResponseEntity.created(new URI("/api/fakturas/" + faktura.getId())).build();
    }

    /**
     * PUT  /fakturas -> Updates an existing faktura.
     */
    @RequestMapping(value = "/fakturas",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody Faktura faktura) throws URISyntaxException {
        log.debug("REST request to update Faktura : {}", faktura);
        if (faktura.getId() == null) {
            return create(faktura);
        }
        fakturaRepository.save(faktura);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /fakturas -> get all the fakturas.
     */
    @RequestMapping(value = "/fakturas",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Faktura>> getAll(@RequestParam(value = "page" , required = false) Integer offset,
                                  @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<Faktura> page = fakturaRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/fakturas", offset, limit);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /fakturas/:id -> get the "id" faktura.
     */
    @RequestMapping(value = "/fakturas/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Faktura> get(@PathVariable Long id) {
        log.debug("REST request to get Faktura : {}", id);
        return Optional.ofNullable(fakturaRepository.findOne(id))
            .map(faktura -> new ResponseEntity<>(
                faktura,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /fakturas/:id -> delete the "id" faktura.
     */
    @RequestMapping(value = "/fakturas/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Faktura : {}", id);
        fakturaRepository.delete(id);
    }
}
