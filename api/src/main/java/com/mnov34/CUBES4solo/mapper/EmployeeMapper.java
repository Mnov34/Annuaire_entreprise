package com.mnov34.CUBES4solo.mapper;

import com.mnov34.CUBES4solo.dto.EmployeeDto;
import com.mnov34.CUBES4solo.model.Employee;
import com.mnov34.CUBES4solo.model.EmployeeService;
import com.mnov34.CUBES4solo.model.Site;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(source = "service.id", target = "serviceId")
    @Mapping(source = "site.id", target = "siteId")
    EmployeeDto toDto(Employee employee);

    @Mapping(target = "service", source = "serviceId")
    @Mapping(target = "site", source = "siteId")
    Employee toEntity(EmployeeDto employeeDto);

    default EmployeeService mapServiceIdToService(Long id) {
        if (id == null) {
            return null;
        }
        EmployeeService service = new EmployeeService();
        service.setId(id);
        return service;
    }

    default Long mapServiceToId(EmployeeService service) {
        return (service != null) ? service.getId() : null;
    }

    default Site mapSiteIdToSite(Long id) {
        if (id == null) {
            return null;
        }
        Site site = new Site();
        site.setId(id);
        return site;
    }

    default Long mapSiteToId(Site site) {
        return (site != null) ? site.getId() : null;
    }
}