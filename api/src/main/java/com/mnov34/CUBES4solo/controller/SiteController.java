package com.mnov34.CUBES4solo.controller;

import com.mnov34.CUBES4solo.model.Site;
import com.mnov34.CUBES4solo.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
@RestController
@RequestMapping("/api/sites")
public class SiteController {

    @Autowired
    private final SiteService siteService;

    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @GetMapping
    public List<Site> getAllSites() {
        return siteService.getAllSites();
    }

    @GetMapping("/{id}")
    public Optional<Site> getSiteById(@PathVariable("id") Long id) { return siteService.getSiteById(id); }

    @GetMapping("/{name}")
    public Optional<Site> getSiteByName(@PathVariable("name") String name) { return siteService.getSiteByName(name); }

    @PostMapping
    public Site createSite(@RequestBody Site site) {
        return siteService.createSite(site);
    }

    @PutMapping("/{id}")
    public Site updateSite(@PathVariable Long id, @RequestBody Site site) {
        return siteService.updateSite(id, site);
    }

    @DeleteMapping("/{id}")
    public void deleteSite(@PathVariable Long id) {
        siteService.deleteSite(id);
    }
}
