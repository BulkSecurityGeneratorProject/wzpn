package com.example.wzpn.repository;

import com.example.wzpn.domain.Sprawozdanie;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Sprawozdanie entity.
 */
public interface SprawozdanieRepository extends JpaRepository<Sprawozdanie,Long> {

}
