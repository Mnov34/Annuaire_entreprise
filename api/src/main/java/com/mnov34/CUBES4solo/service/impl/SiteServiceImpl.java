package com.mnov34.CUBES4solo.service.impl;

import com.mnov34.CUBES4solo.exception.OperationNotAllowedException;
import com.mnov34.CUBES4solo.model.Site;
import com.mnov34.CUBES4solo.repository.EmployeeRepository;
import com.mnov34.CUBES4solo.repository.SiteRepository;
import com.mnov34.CUBES4solo.service.SiteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
@Service
public class SiteServiceImpl implements SiteService {

    private final SiteRepository siteRepository;
    private final EmployeeRepository employeeRepository;

    public SiteServiceImpl(SiteRepository siteRepository, EmployeeRepository employeeRepository) {
        this.siteRepository = siteRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Site> getAllSites() {
        return siteRepository.findAll();
    }

    public Optional<Site> getSiteById(Long id) {
        return siteRepository.findById(id);
    }

    public Optional<Site> getSiteByName(String name) {
        return siteRepository.findBySiteContainingIgnoreCase(name);
    }

    public Site createSite(Site site) {
        return siteRepository.saveAndFlush(site);
    }

    public Site updateSite(Long id, Site site) {
        Site existingSite = siteRepository.findById(id).orElseThrow(() -> new RuntimeException("Site not found"));
        existingSite.setSite(site.getSite());
        return siteRepository.saveAndFlush(existingSite);
    }

    public void deleteSite(Long id) {
        Site site = siteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Site not found with id: " + id));

        if (employeeRepository.existsBySite(site)) {
            throw new OperationNotAllowedException(
                    "Cannot delete site '" + site.getSite() + "' - it has associated employees"
            );
        }

        siteRepository.deleteById(id);
    }
}
