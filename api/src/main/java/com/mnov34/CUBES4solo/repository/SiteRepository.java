package com.mnov34.CUBES4solo.repository;

import com.mnov34.CUBES4solo.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
public interface SiteRepository extends JpaRepository<Site, Long> {
    Optional<Site> findBySite(String city);
}
