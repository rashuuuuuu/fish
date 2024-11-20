package com.website.customer.service.impl;

import com.website.common.dto.GetAllStatus;
import com.website.common.repo.StatusRepository;
import com.website.customer.service.StatusService;
import com.website.entity.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {
    private final StatusRepository statusRepository;
    @Override
    public List<GetAllStatus> getAllStatus(){
        List<GetAllStatus> getAllStatuses=new ArrayList<>();
        for(Status status :statusRepository.findAll()){
            GetAllStatus getAllStatus = new GetAllStatus();
            getAllStatus.setName(status.getName());
            getAllStatus.setDescription(status.getDescription());
            getAllStatus.setIcon(status.getIcon());
        }
        return getAllStatuses;
    }
}
