package com.example.wzpn.repository;

import com.example.wzpn.domain.Faktura;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Faktura entity.
 */
public interface FakturaRepository extends JpaRepository<Faktura,Long> {

}
