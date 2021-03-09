//package com.mooc.moocServer.repository;
//
//import com.mooc.moocServer.entity.Organization;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.Assert.assertEquals;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//public class OrganizationRepositoryTest {
//    @Autowired
//    OrganizationRepository organizationRepository;
//
//    @Test
//    public void saveTest() {
//        Organization organization = new Organization("Test Organization");
//        organizationRepository.save(organization);
//        Organization one = organizationRepository.findOne("Test Organization");
//
//        assertEquals("Test Organization", one.getId());
//    }
//}