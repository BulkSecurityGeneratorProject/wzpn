package com.example.wzpn.repository;

import com.example.wzpn.domain.Kara;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Kara entity.
 */
public interface KaraRepository extends JpaRepository<Kara,Long> {

}
