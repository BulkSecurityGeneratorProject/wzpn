package com.example.wzpn.repository;

import com.example.wzpn.domain.Druzyna;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Druzyna entity.
 */
public interface DruzynaRepository extends JpaRepository<Druzyna,Long> {

}
