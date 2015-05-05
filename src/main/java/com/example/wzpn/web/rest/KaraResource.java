package com.example.wzpn.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.example.wzpn.domain.Kara;
import com.example.wzpn.repository.KaraRepository;
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
 * REST controller for managing Kara.
 */
@RestController
@RequestMapping("/api")
public class KaraResource {

    private final Logger log = LoggerFactory.getLogger(KaraResource.class);

    @Inject
    private KaraRepository karaRepository;

    /**
     * POST  /karas -> Create a new kara.
     */
    @RequestMapping(value = "/karas",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody Kara kara) throws URISyntaxException {
        log.debug("REST request to save Kara : {}", kara);
        if (kara.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new kara cannot already have an ID").build();
        }
        karaRepository.save(kara);
        return ResponseEntity.created(new URI("/api/karas/" + kara.getId())).build();
    }

    /**
     * PUT  /karas -> Updates an existing kara.
     */
    @RequestMapping(value = "/karas",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody Kara kara) throws URISyntaxException {
        log.debug("REST request to update Kara : {}", kara);
        if (kara.getId() == null) {
            return create(kara);
        }
        karaRepository.save(kara);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /karas -> get all the karas.
     */
    @RequestMapping(value = "/karas",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Kara>> getAll(@RequestParam(value = "page" , required = false) Integer offset,
                                  @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<Kara> page = karaRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/karas", offset, limit);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /karas/:id -> get the "id" kara.
     */
    @RequestMapping(value = "/karas/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Kara> get(@PathVariable Long id) {
        log.debug("REST request to get Kara : {}", id);
        return Optional.ofNullable(karaRepository.findOne(id))
            .map(kara -> new ResponseEntity<>(
                kara,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /karas/:id -> delete the "id" kara.
     */
    @RequestMapping(value = "/karas/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Kara : {}", id);
        karaRepository.delete(id);
    }
}
