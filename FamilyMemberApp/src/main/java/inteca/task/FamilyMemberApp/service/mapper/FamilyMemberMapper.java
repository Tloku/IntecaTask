package inteca.task.FamilyMemberApp.service.mapper;

import inteca.task.FamilyMemberApp.model.dto.FamilyMemberDto;
import inteca.task.FamilyMemberApp.model.entity.FamilyMemberEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FamilyMemberMapper {


    public FamilyMemberDto entityToDto(FamilyMemberEntity familyMember) {
        if(familyMember == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Family is empty");
        }

        return FamilyMemberDto.builder()
                .id(familyMember.getId())
                .familyName(familyMember.getFamilyName())
                .givenName(familyMember.getGivenName())
                .age(familyMember.getAge())
                .familyId(familyMember.getFamilyId())
                .build();
    }

    public FamilyMemberEntity dtoToEntity(FamilyMemberDto familyMemberDto) {
        if(familyMemberDto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Family Member dto is empty");
        }

        return FamilyMemberEntity.builder()
                .familyName(familyMemberDto.getFamilyName())
                .age(familyMemberDto.getAge())
                .givenName(familyMemberDto.getGivenName())
                .familyId(familyMemberDto.getFamilyId())
                .build();
    }
}
