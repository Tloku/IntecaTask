package inteca.task.FamilyMemberApp.service;

import inteca.task.FamilyMemberApp.model.api.request.FamilyMemberRequest;
import inteca.task.FamilyMemberApp.model.api.response.FamilyMemberResponse;

import java.util.List;

public interface FamilyMemberService {
    void createFamilyMember(List<FamilyMemberRequest> familyMembers);
    List<FamilyMemberResponse> searchFamilyMember(int familyId);
}
