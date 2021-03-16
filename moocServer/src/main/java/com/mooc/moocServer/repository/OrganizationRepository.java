package com.mooc.moocServer.repository;

import com.mooc.moocServer.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, String> {
}