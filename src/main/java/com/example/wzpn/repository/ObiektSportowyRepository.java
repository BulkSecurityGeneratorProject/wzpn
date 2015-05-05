package com.example.wzpn.repository;

import com.example.wzpn.domain.ObiektSportowy;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ObiektSportowy entity.
 */
public interface ObiektSportowyRepository extends JpaRepository<ObiektSportowy,Long> {

}
