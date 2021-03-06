package com.mooc.moocServer.controller;

import com.mooc.moocServer.dto.OrganizationDto;
import com.mooc.moocServer.entity.Organization;
import com.mooc.moocServer.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrganizationController {

    private final OrganizationService organizationService;

    @GetMapping("/organization")
    public List<OrganizationDto> get() {
        List<Organization> organizations = organizationService.getAllOrganizations();
        List<OrganizationDto> organizationDto = new ArrayList<>();
        for (Organization o : organizations) {
            organizationDto.add(OrganizationDto.entityToDto(o));
        }
        return organizationDto;
    }

    @GetMapping("/organization/{organizationId}")
    public OrganizationDto get(@PathVariable("organizationId") String organizationId) {
        Organization organization = organizationService.getOrganization(organizationId);
        return OrganizationDto.entityToDto(organization);
    }

    @PostMapping(value = "/organization", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> add(@RequestBody OrganizationDto m) {
        String s = organizationService.addOrganization(m.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(new OrganizationDto(s));
    }
}
