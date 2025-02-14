package com.mnov34.CUBES4solo.mapper;

import com.mnov34.CUBES4solo.dto.SiteDto;
import com.mnov34.CUBES4solo.model.Site;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
@Mapper(componentModel = "spring")
public interface SiteMapper {
    SiteMapper INSTANCE = Mappers.getMapper(SiteMapper.class);

    SiteDto toDto(Site site);

    Site toEntity(SiteDto siteDto);
}