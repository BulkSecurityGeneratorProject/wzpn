package com.example.wzpn.repository;

import com.example.wzpn.domain.Usluga;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Usluga entity.
 */
public interface UslugaRepository extends JpaRepository<Usluga,Long> {

}
