package inteca.task.FamilyMemberApp.controller;

import inteca.task.FamilyMemberApp.model.api.request.FamilyMemberRequest;
import inteca.task.FamilyMemberApp.model.api.response.FamilyMemberResponse;
import inteca.task.FamilyMemberApp.service.FamilyMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/family_members")
public class FamilyMemberController {
    private final FamilyMemberService familyMemberService;

    @GetMapping(value = "/get/{familyId}")
    public List<FamilyMemberResponse> searchFamilyMember(@PathVariable int familyId) {
        return familyMemberService.searchFamilyMember(familyId);
    }

    @PostMapping(value = "/create")
    public void createFamilyMembers(@RequestBody List<FamilyMemberRequest> familyMembers) {
        familyMemberService.createFamilyMember(familyMembers);
    }

}
