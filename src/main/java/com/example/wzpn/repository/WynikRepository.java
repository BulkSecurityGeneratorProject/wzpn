package com.example.wzpn.repository;

import com.example.wzpn.domain.Wynik;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Wynik entity.
 */
public interface WynikRepository extends JpaRepository<Wynik,Long> {

}
