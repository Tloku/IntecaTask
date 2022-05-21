package inteca.task.FamilyMemberApp.service.mapper;

import inteca.task.FamilyMemberApp.model.api.response.FamilyMemberResponse;
import inteca.task.FamilyMemberApp.model.entity.FamilyMemberEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FamilyMemberResponseMapper {
    public FamilyMemberResponse entityToResponse(FamilyMemberEntity familyMember) {
        if(familyMember == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Family member to map is null");
        }


        return FamilyMemberResponse.builder()
                .id(familyMember.getId())
                .age(familyMember.getAge())
                .familyName(familyMember.getFamilyName())
                .familyId(familyMember.getFamilyId())
                .givenName(familyMember.getGivenName())
                .build();
    }

    public FamilyMemberEntity responseToEntity(FamilyMemberResponse familyMemberResponse) {
        if(familyMemberResponse == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Family member from response to map is null");
        }

        return FamilyMemberEntity.builder()
                .age(familyMemberResponse.getAge())
                .familyId(familyMemberResponse.getFamilyId())
                .familyName(familyMemberResponse.getFamilyName())
                .givenName(familyMemberResponse.getGivenName())
                .build();
    }
}
