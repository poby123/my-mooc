package com.mooc.moocServer.controller;

import com.mooc.moocServer.dto.OrganizationDto;
import com.mooc.moocServer.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrganizationController {

    private final OrganizationService organizationService;

    @GetMapping("/organization")
    public ResponseEntity<?> get() {
        List<OrganizationDto.Response> res = organizationService.getAllOrganizations();
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("/organization/{organizationId}")
    public ResponseEntity<?> get(@PathVariable("organizationId") String id) {
        log.info("The id is : " + id);
        OrganizationDto.Response res = organizationService.getOrganization(id);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping("/organization")
    public ResponseEntity<?> add(@RequestBody HashMap<String, String> req) {
        OrganizationDto.Response res = organizationService.addOrganization(req.get("id"));
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
}
