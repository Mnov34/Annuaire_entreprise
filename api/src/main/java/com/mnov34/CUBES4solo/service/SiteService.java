package com.mnov34.CUBES4solo.service;

import com.mnov34.CUBES4solo.model.Site;

import java.util.List;
import java.util.Optional;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
public interface SiteService {
    List<Site> getAllSites();

    Optional<Site> getSiteById(Long id);

    Optional<Site> getSiteByName(String name);

    Site createSite(Site Site);

    Site updateSite(Long id, Site Site);

    void deleteSite(Long id);
}
