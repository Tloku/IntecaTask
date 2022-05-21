package inteca.task.FamilyMemberApp.service.mapper;

import inteca.task.FamilyMemberApp.model.api.request.FamilyMemberRequest;
import inteca.task.FamilyMemberApp.model.entity.FamilyMemberEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FamilyMemberRequestMapper {
    public FamilyMemberRequest entityToRequest(FamilyMemberEntity familyMember) {
        if(familyMember == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Family member to map is null");
        }


        return FamilyMemberRequest.builder()
                .id(familyMember.getId())
                .age(familyMember.getAge())
                .familyName(familyMember.getFamilyName())
                .familyId(familyMember.getFamilyId())
                .givenName(familyMember.getGivenName())
                .build();
    }

    public FamilyMemberEntity requestToEntity(FamilyMemberRequest familyMemberRequest) {
        if(familyMemberRequest == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Family member from response to map is null");
        }

        return FamilyMemberEntity.builder()
                .age(familyMemberRequest.getAge())
                .familyId(familyMemberRequest.getFamilyId())
                .familyName(familyMemberRequest.getFamilyName())
                .givenName(familyMemberRequest.getGivenName())
                .build();
    }

}
