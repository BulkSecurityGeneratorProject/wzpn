package com.example.wzpn.repository;

import com.example.wzpn.domain.Pracownik;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Pracownik entity.
 */
public interface PracownikRepository extends JpaRepository<Pracownik,Long> {

}
