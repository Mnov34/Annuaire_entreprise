package com.mnov34.CUBES4solo.controller;

import com.mnov34.CUBES4solo.dto.SiteDto;
import com.mnov34.CUBES4solo.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<SiteDto> getAllSites() {
        return siteService.getAllSites();
    }

    @PostMapping
    public SiteDto createSite(@RequestBody SiteDto siteDto) {
        return siteService.createSite(siteDto);
    }

    @PutMapping("/{id}")
    public SiteDto updateSite(@PathVariable Long id, @RequestBody SiteDto siteDto) {
        return siteService.updateSite(id, siteDto);
    }

    @DeleteMapping("/{id}")
    public void deleteSite(@PathVariable Long id) {
        siteService.deleteSite(id);
    }
}
