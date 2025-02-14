package com.mnov34.CUBES4solo.service.impl;

import com.mnov34.CUBES4solo.dto.SiteDto;
import com.mnov34.CUBES4solo.mapper.SiteMapper;
import com.mnov34.CUBES4solo.model.Site;
import com.mnov34.CUBES4solo.repository.SiteRepository;
import com.mnov34.CUBES4solo.service.SiteService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
public class SiteServiceImpl implements SiteService {

    private final SiteRepository siteRepository;

    public SiteServiceImpl(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    public List<SiteDto> getAllSites() {
        return siteRepository.findAll().stream()
                .map(SiteMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public SiteDto createSite(SiteDto siteDto) {
        Site site = SiteMapper.INSTANCE.toEntity(siteDto);
        return SiteMapper.INSTANCE.toDto(siteRepository.save(site));
    }

    public SiteDto updateSite(Long id, SiteDto siteDto) {
        Site site = siteRepository.findById(id).orElseThrow(() -> new RuntimeException("Site not found"));
        site.setSite(siteDto.getSite());
        return SiteMapper.INSTANCE.toDto(siteRepository.save(site));
    }

    public void deleteSite(Long id) {
        siteRepository.deleteById(id);
    }
}
