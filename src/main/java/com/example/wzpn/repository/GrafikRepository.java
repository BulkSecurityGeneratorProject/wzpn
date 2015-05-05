package com.example.wzpn.repository;

import com.example.wzpn.domain.Grafik;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Grafik entity.
 */
public interface GrafikRepository extends JpaRepository<Grafik,Long> {

}
