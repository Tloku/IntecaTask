package inteca.task.service.impl;

import inteca.task.FamilyMemberApp.model.api.request.FamilyMemberRequest;
import inteca.task.FamilyMemberApp.model.api.response.FamilyMemberResponse;
import inteca.task.FamilyMemberApp.repository.FamilyMemberRepository;
import inteca.task.FamilyMemberApp.service.FamilyMemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class FamilyMemberServiceImplTest {

    @Autowired
    FamilyMemberService familyMemberService;

    @Autowired
    FamilyMemberRepository familyMemberRepository;

    @Test
    void createFamilyMemberTest() {
        List<FamilyMemberRequest> familyMembers = new ArrayList<>();
        String familyName = "testFamilia2";
        int familyId = 6;

        familyMembers.add(FamilyMemberRequest.builder()
                .age(2)
                .givenName("testName")
                .familyId(familyId)
                .familyName(familyName)
                .build());
        familyMembers.add(FamilyMemberRequest.builder()
                .age(15)
                .givenName("testName2")
                .familyId(familyId)
                .familyName(familyName)
                .build());
        familyMembers.add(FamilyMemberRequest.builder()
                .age(3)
                .givenName("testName3")
                .familyId(familyId)
                .familyName(familyName)
                .build());

        familyMemberService.createFamilyMember(familyMembers);

        Assertions.assertEquals(3, familyMemberRepository
                .findAll()
                .stream()
                .filter(familyMemberEntity -> familyMemberEntity.getFamilyId() == familyId)
                .toList()
                .size());
    }


    @Test
    void searchFamilyMemberTest() {
        List<FamilyMemberRequest> familyMembers = new ArrayList<>();
        String familyName = "testFamilia3";
        int familyId = 7;

        familyMembers.add(FamilyMemberRequest.builder()
                .age(2)
                .givenName("testName")
                .familyId(familyId)
                .familyName(familyName)
                .build());
        familyMembers.add(FamilyMemberRequest.builder()
                .age(15)
                .givenName("testName2")
                .familyId(familyId)
                .familyName(familyName)
                .build());
        familyMembers.add(FamilyMemberRequest.builder()
                .age(3)
                .givenName("testName3")
                .familyId(familyId)
                .familyName(familyName)
                .build());

        familyMemberService.createFamilyMember(familyMembers);

        List<FamilyMemberResponse> response = familyMemberService.searchFamilyMember(familyId);
        Assertions.assertEquals(3, response.size());
    }
}