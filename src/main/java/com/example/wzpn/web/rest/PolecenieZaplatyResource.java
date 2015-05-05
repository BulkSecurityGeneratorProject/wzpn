package com.example.wzpn.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.example.wzpn.domain.PolecenieZaplaty;
import com.example.wzpn.repository.PolecenieZaplatyRepository;
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
 * REST controller for managing PolecenieZaplaty.
 */
@RestController
@RequestMapping("/api")
public class PolecenieZaplatyResource {

    private final Logger log = LoggerFactory.getLogger(PolecenieZaplatyResource.class);

    @Inject
    private PolecenieZaplatyRepository polecenieZaplatyRepository;

    /**
     * POST  /polecenieZaplatys -> Create a new polecenieZaplaty.
     */
    @RequestMapping(value = "/polecenieZaplatys",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody PolecenieZaplaty polecenieZaplaty) throws URISyntaxException {
        log.debug("REST request to save PolecenieZaplaty : {}", polecenieZaplaty);
        if (polecenieZaplaty.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new polecenieZaplaty cannot already have an ID").build();
        }
        polecenieZaplatyRepository.save(polecenieZaplaty);
        return ResponseEntity.created(new URI("/api/polecenieZaplatys/" + polecenieZaplaty.getId())).build();
    }

    /**
     * PUT  /polecenieZaplatys -> Updates an existing polecenieZaplaty.
     */
    @RequestMapping(value = "/polecenieZaplatys",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody PolecenieZaplaty polecenieZaplaty) throws URISyntaxException {
        log.debug("REST request to update PolecenieZaplaty : {}", polecenieZaplaty);
        if (polecenieZaplaty.getId() == null) {
            return create(polecenieZaplaty);
        }
        polecenieZaplatyRepository.save(polecenieZaplaty);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /polecenieZaplatys -> get all the polecenieZaplatys.
     */
    @RequestMapping(value = "/polecenieZaplatys",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<PolecenieZaplaty>> getAll(@RequestParam(value = "page" , required = false) Integer offset,
                                  @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<PolecenieZaplaty> page = polecenieZaplatyRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/polecenieZaplatys", offset, limit);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /polecenieZaplatys/:id -> get the "id" polecenieZaplaty.
     */
    @RequestMapping(value = "/polecenieZaplatys/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PolecenieZaplaty> get(@PathVariable Long id) {
        log.debug("REST request to get PolecenieZaplaty : {}", id);
        return Optional.ofNullable(polecenieZaplatyRepository.findOne(id))
            .map(polecenieZaplaty -> new ResponseEntity<>(
                polecenieZaplaty,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /polecenieZaplatys/:id -> delete the "id" polecenieZaplaty.
     */
    @RequestMapping(value = "/polecenieZaplatys/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete PolecenieZaplaty : {}", id);
        polecenieZaplatyRepository.delete(id);
    }
}
