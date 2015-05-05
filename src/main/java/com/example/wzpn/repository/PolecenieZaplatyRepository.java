package com.example.wzpn.repository;

import com.example.wzpn.domain.PolecenieZaplaty;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the PolecenieZaplaty entity.
 */
public interface PolecenieZaplatyRepository extends JpaRepository<PolecenieZaplaty,Long> {

}
