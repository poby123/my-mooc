package com.mooc.moocServer.controller;

import com.mooc.moocServer.dto.OrganizationDto;
import com.mooc.moocServer.entity.Organization;
import com.mooc.moocServer.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @GetMapping("/organization")
    public List<OrganizationDto> get(){
        List<Organization> organizations = organizationService.getAllOrganizations();
        List<OrganizationDto> organizationDto = new ArrayList<>();
        for (Organization o : organizations){
            organizationDto.add(OrganizationDto.entityToDto(o));
        }
        return organizationDto;
    }

    @GetMapping("/organization/{organizationId}")
    public OrganizationDto get(@PathVariable("organizationId") String organizationId){
        Organization organization = organizationService.getOrganization(organizationId);
        return OrganizationDto.entityToDto(organization);
    }

    @PostMapping("/organization")
    public OrganizationDto add(@RequestBody OrganizationDto dto){
        String s = organizationService.addOrganization(dto.getId());
        return new OrganizationDto(s);
    }

}
