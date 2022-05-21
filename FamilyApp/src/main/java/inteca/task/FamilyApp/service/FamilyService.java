package inteca.task.FamilyApp.service;

import inteca.task.FamilyApp.model.api.Request.FamilyMemberRequest;
import inteca.task.FamilyApp.model.api.Request.FamilyRequest;
import inteca.task.FamilyApp.model.api.Response.FamilyMemberResponse;
import inteca.task.FamilyApp.model.api.Response.FamilyResponse;
import java.util.List;

public interface FamilyService {

    FamilyResponse getFamily(int familyId);
    void postFamilyMembers(List<FamilyMemberRequest> familyMemberRequests);
    int createFamily(FamilyRequest familyRequest);
    boolean validateFamilyData(List<FamilyMemberRequest> familyMembers, int nrOfChildren, int nrOfAdults, int nrOfInfants);
    List<FamilyMemberResponse> getFamilyMembersFromFamilyMemberApp(int familyId);
}
