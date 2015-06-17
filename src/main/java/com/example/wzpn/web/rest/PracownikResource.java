package com.example.wzpn.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.example.wzpn.domain.Pracownik;
import com.example.wzpn.domain.User;
import com.example.wzpn.repository.PracownikRepository;
import com.example.wzpn.repository.UserRepository;
import com.example.wzpn.service.UserService;
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
import javax.validation.Valid;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Pracownik.
 */
@RestController
@RequestMapping("/api")
public class PracownikResource {

    private final Logger log = LoggerFactory.getLogger(PracownikResource.class);

    @Inject
    private PracownikRepository pracownikRepository;
    
    @Inject
    private UserService userService;
    
    @Inject
    private UserRepository userRepository;

    /**
     * POST  /pracowniks -> Create a new pracownik.
     */
    @RequestMapping(value = "/pracowniks",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@Valid @RequestBody Pracownik pracownik) throws URISyntaxException {
        log.debug("REST request to save Pracownik : {}", pracownik);
        if (pracownik.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new pracownik cannot already have an ID").build();
        }
        if (userRepository.findOneByLogin(pracownik.getLogin()).isPresent()) {
            return ResponseEntity.badRequest().header("Failure", "Login zajęty!").build();
        }

        userService.createUserInformation(pracownik);
        pracownikRepository.save(pracownik);
        return ResponseEntity.created(new URI("/api/pracowniks/" + pracownik.getId())).build();
    }

    /**
     * PUT  /pracowniks -> Updates an existing pracownik.
     */
    @RequestMapping(value = "/pracowniks",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@Valid @RequestBody Pracownik pracownik) throws URISyntaxException {
        log.debug("REST request to update Pracownik : {}", pracownik);
        if (pracownik.getId() == null) {
            return create(pracownik);
        }
        userService.updateUserInformation(pracownik);
        pracownikRepository.save(pracownik);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /pracowniks -> get all the pracowniks.
     */
    @RequestMapping(value = "/pracowniks",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Pracownik>> getAll(@RequestParam(value = "page" , required = false) Integer offset,
                                  @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<Pracownik> page = pracownikRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pracowniks", offset, limit);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /pracowniks/:id -> get the "id" pracownik.
     */
    @RequestMapping(value = "/pracowniks/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Pracownik> get(@PathVariable Long id) {
        log.debug("REST request to get Pracownik : {}", id);
        return Optional.ofNullable(pracownikRepository.findOne(id))
            .map(pracownik -> new ResponseEntity<>(
                pracownik,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /pracowniks/:id -> delete the "id" pracownik.
     */
    @RequestMapping(value = "/pracowniks/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Pracownik : {}", id);
        Pracownik pracownik = pracownikRepository.findOne(id);
        pracownikRepository.delete(id);
        Optional<User> user = userRepository.findOneByLogin(pracownik.getLogin());
        if (user.isPresent())
        	userRepository.delete(user.get().getId());
    }
}
