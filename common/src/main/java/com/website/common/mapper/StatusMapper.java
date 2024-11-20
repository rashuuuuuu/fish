package com.website.common.mapper;

import com.website.common.dto.StatusDto;
import com.website.entity.Status;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class StatusMapper {

    public abstract StatusDto entityToStatusDto(Status status);

}
