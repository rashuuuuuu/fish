package com.website.admin.user.profile.mapper;
import com.website.admin.user.entity.Admin;
import com.website.admin.user.profile.model.EditProfileRequest;
import com.website.admin.user.profile.model.LoggedInUserDetailDto;
import com.website.admin.user.repo.AdminRepository;
import com.website.common.mapper.StatusMapper;
import com.website.common.repo.StatusRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ProfileMapper {
    @Autowired
    protected StatusRepository statusRepository;
    @Autowired
    protected StatusMapper statusMapper;
    @Autowired
    protected AdminRepository adminRepository;

    public abstract LoggedInUserDetailDto getLoggedInUserDetailDto(Admin admin);

    public Admin mapToUpdateEntity(Admin admin, EditProfileRequest editProfileRequest, MultipartFile image) throws IOException {
        if ( editProfileRequest == null ) {
            return null;
        }
        admin.setName(editProfileRequest.getName());
        admin.setMobileNumber(editProfileRequest.getMobileNumber());
        admin.setAddress(editProfileRequest.getAddress());
        admin.setUpdatedAt(new Date());
        return admin;
    }


}
