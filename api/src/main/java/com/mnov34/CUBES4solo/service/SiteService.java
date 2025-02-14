package com.mnov34.CUBES4solo.service;

import com.mnov34.CUBES4solo.dto.SiteDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
@Service
public interface SiteService {

    List<SiteDto> getAllSites();

    SiteDto createSite(SiteDto siteDto);

    SiteDto updateSite(Long id, SiteDto siteDto);

    void deleteSite(Long id);
}
