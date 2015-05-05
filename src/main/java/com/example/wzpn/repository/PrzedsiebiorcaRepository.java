package com.example.wzpn.repository;

import com.example.wzpn.domain.Przedsiebiorca;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Przedsiebiorca entity.
 */
public interface PrzedsiebiorcaRepository extends JpaRepository<Przedsiebiorca,Long> {

}
