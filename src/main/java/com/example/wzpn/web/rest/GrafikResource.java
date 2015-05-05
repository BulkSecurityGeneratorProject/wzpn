package com.example.wzpn.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.example.wzpn.domain.Grafik;
import com.example.wzpn.repository.GrafikRepository;
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
 * REST controller for managing Grafik.
 */
@RestController
@RequestMapping("/api")
public class GrafikResource {

    private final Logger log = LoggerFactory.getLogger(GrafikResource.class);

    @Inject
    private GrafikRepository grafikRepository;

    /**
     * POST  /grafiks -> Create a new grafik.
     */
    @RequestMapping(value = "/grafiks",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody Grafik grafik) throws URISyntaxException {
        log.debug("REST request to save Grafik : {}", grafik);
        if (grafik.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new grafik cannot already have an ID").build();
        }
        grafikRepository.save(grafik);
        return ResponseEntity.created(new URI("/api/grafiks/" + grafik.getId())).build();
    }

    /**
     * PUT  /grafiks -> Updates an existing grafik.
     */
    @RequestMapping(value = "/grafiks",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody Grafik grafik) throws URISyntaxException {
        log.debug("REST request to update Grafik : {}", grafik);
        if (grafik.getId() == null) {
            return create(grafik);
        }
        grafikRepository.save(grafik);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /grafiks -> get all the grafiks.
     */
    @RequestMapping(value = "/grafiks",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Grafik>> getAll(@RequestParam(value = "page" , required = false) Integer offset,
                                  @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<Grafik> page = grafikRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/grafiks", offset, limit);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /grafiks/:id -> get the "id" grafik.
     */
    @RequestMapping(value = "/grafiks/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Grafik> get(@PathVariable Long id) {
        log.debug("REST request to get Grafik : {}", id);
        return Optional.ofNullable(grafikRepository.findOne(id))
            .map(grafik -> new ResponseEntity<>(
                grafik,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /grafiks/:id -> delete the "id" grafik.
     */
    @RequestMapping(value = "/grafiks/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Grafik : {}", id);
        grafikRepository.delete(id);
    }
}
