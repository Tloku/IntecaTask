package inteca.task.FamilyApp.service.impl;

import inteca.task.FamilyApp.model.api.Request.FamilyMemberRequest;
import inteca.task.FamilyApp.model.api.Request.FamilyRequest;
import inteca.task.FamilyApp.model.api.Response.FamilyResponse;
import inteca.task.FamilyApp.service.FamilyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;

@SpringBootTest
class FamilyServiceImplTest {

    @Autowired
    FamilyService familyService;

    @Test
    void getFamilyTest() {
        ArrayList<FamilyMemberRequest> familyMembers = new ArrayList<>();
        int nrOfChildren = 1;
        int nrOfAdults = 0;
        int nrOfInfants = 0;

        familyMembers.add(FamilyMemberRequest.builder()
                        .givenName("child")
                        .age(14)
                        .build());

        int family_id = familyService.createFamily(FamilyRequest.builder()
                        .familyMembers(familyMembers)
                        .nrOfInfants(nrOfInfants)
                        .nrOfChildren(nrOfChildren)
                        .nrOfAdults(nrOfAdults)
                        .familyName("TEST")
                        .build());

        FamilyResponse familyResponse = familyService.getFamily(family_id);
        Assertions.assertEquals("TEST", familyResponse.getFamilyName());
    }

    @Test
    void createFamilyTest() {
        ArrayList<FamilyMemberRequest> familyMembers = new ArrayList<>();
        int nrOfChildren = 2;
        int nrOfAdults = 2;
        int nrOfInfants = 1;

        familyMembers.add(FamilyMemberRequest.builder()
                .age(5)
                .givenName("child")
                .build());
        familyMembers.add(FamilyMemberRequest.builder()
                .age(12)
                .givenName("child")
                .build());
        familyMembers.add(FamilyMemberRequest.builder()
                .age(25)
                .givenName("adult")
                .build());
        familyMembers.add(FamilyMemberRequest.builder()
                .age(32)
                .givenName("adult")
                .build());
        familyMembers.add(FamilyMemberRequest.builder()
                .age(2)
                .givenName("infant")
                .build());

        FamilyRequest familyRequest = FamilyRequest.builder()
                .familyName("test")
                .nrOfAdults(nrOfAdults)
                .nrOfChildren(nrOfChildren)
                .nrOfInfants(nrOfInfants)
                .familyMembers(familyMembers)
                .build();
        Integer id = null;

        Assertions.assertNotEquals(id, familyService.createFamily(familyRequest));
    }

    @Test
    void validateIncorrectFamilyDataTest() {
        ArrayList<FamilyMemberRequest> familyMembers = new ArrayList<>();
        int nrOfChildren = 2;
        int nrOfAdults = 2;
        int nrOfInfants = 2;

        familyMembers.add(FamilyMemberRequest.builder()
                .age(5)
                .givenName("child")
                .build());
        familyMembers.add(FamilyMemberRequest.builder()
                .age(12)
                .givenName("child")
                .build());
        familyMembers.add(FamilyMemberRequest.builder()
                .age(25)
                .givenName("adult")
                .build());
        familyMembers.add(FamilyMemberRequest.builder()
                .age(32)
                .givenName("adult")
                .build());
        familyMembers.add(FamilyMemberRequest.builder()
                .age(2)
                .givenName("infant")
                .build());

        Assertions.assertFalse(familyService.validateFamilyData(familyMembers, nrOfChildren, nrOfAdults, nrOfInfants));
    }

    @Test
    void validateCorrectFamilyDataTest() {
        ArrayList<FamilyMemberRequest> familyMembers = new ArrayList<>();
        int nrOfChildren = 2;
        int nrOfAdults = 2;
        int nrOfInfants = 1;

        familyMembers.add(FamilyMemberRequest.builder()
                .age(5)
                .givenName("child")
                .build());
        familyMembers.add(FamilyMemberRequest.builder()
                .age(12)
                .givenName("child")
                .build());
        familyMembers.add(FamilyMemberRequest.builder()
                .age(25)
                .givenName("adult")
                .build());
        familyMembers.add(FamilyMemberRequest.builder()
                .age(32)
                .givenName("adult")
                .build());
        familyMembers.add(FamilyMemberRequest.builder()
                .age(2)
                .givenName("infant")
                .build());

        Assertions.assertTrue(familyService.validateFamilyData(familyMembers, nrOfChildren, nrOfAdults, nrOfInfants));
    }
}