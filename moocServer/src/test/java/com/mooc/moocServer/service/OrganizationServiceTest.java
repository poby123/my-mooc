package com.mooc.moocServer.service;

import com.mooc.moocServer.domain.Organization;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizationServiceTest {

    @Autowired
    private OrganizationService organizationService;

    @Test
    public void addTest() {
        //given
        String organizationId = "or1";

        //when
        String result = organizationService.addOrganization(organizationId);

        //then
        assertEquals(organizationId, result);
    }

    @Test(expected = IllegalStateException.class)
    public void duplicateTest() {
        //given
        String id = "or1";
        organizationService.addOrganization(id);

        //when
        organizationService.addOrganization(id);

        //then
        fail("duplicate 오류가 발생해야합니다.");
    }

    @Test
    public void getAllOrganizationsTest() {
        //given
        String[] ids = {"or1", "or2", "or3"};
        for (String id : ids) {
            organizationService.addOrganization(id);
        }

        //when
        List<Organization> organizationList = organizationService.getAllOrganizations();

        //then
        for (int i = 0; i < ids.length; i++) {
            Organization o = organizationService.getOrganization(ids[i]);
            assertEquals(o.getId(), organizationList.get(i).getId());
        }
    }
}