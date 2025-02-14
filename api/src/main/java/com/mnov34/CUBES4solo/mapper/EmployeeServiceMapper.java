package com.mnov34.CUBES4solo.mapper;

import com.mnov34.CUBES4solo.dto.EmployeeServiceDto;
import com.mnov34.CUBES4solo.model.EmployeeService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
@Mapper(componentModel = "spring")
public interface EmployeeServiceMapper {
    EmployeeServiceMapper INSTANCE = Mappers.getMapper(EmployeeServiceMapper.class);

    EmployeeServiceDto toDto(EmployeeService service);

    EmployeeService toEntity(EmployeeServiceDto serviceDto);
}