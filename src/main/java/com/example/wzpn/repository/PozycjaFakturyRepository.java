package com.example.wzpn.repository;

import com.example.wzpn.domain.PozycjaFaktury;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the PozycjaFaktury entity.
 */
public interface PozycjaFakturyRepository extends JpaRepository<PozycjaFaktury,Long> {

}
