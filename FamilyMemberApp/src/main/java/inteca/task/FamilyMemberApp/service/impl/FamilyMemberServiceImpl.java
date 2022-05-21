package inteca.task.FamilyMemberApp.service.impl;

import inteca.task.FamilyMemberApp.model.api.request.FamilyMemberRequest;
import inteca.task.FamilyMemberApp.model.api.response.FamilyMemberResponse;
import inteca.task.FamilyMemberApp.model.entity.FamilyMemberEntity;
import inteca.task.FamilyMemberApp.repository.FamilyMemberRepository;
import inteca.task.FamilyMemberApp.service.FamilyMemberService;
import inteca.task.FamilyMemberApp.service.mapper.FamilyMemberRequestMapper;
import inteca.task.FamilyMemberApp.service.mapper.FamilyMemberResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FamilyMemberServiceImpl implements FamilyMemberService {

    private final FamilyMemberRepository familyMemberRepository;
    private final FamilyMemberResponseMapper familyMemberResponseMapper;
    private final FamilyMemberRequestMapper familyMemberRequestMapper;

    /**
     * Function that saves every familyMember to database.
     * Two last params are used to set correct familyName and id to every member of family
     *
     * @param familyMembers list of family members
     */
    @Override
    public void createFamilyMember(List<FamilyMemberRequest> familyMembers) {
        if(familyMembers.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Given family has no members");
        }
        familyMemberRepository.saveAll(familyMembers
                .stream()
                .map(familyMemberRequestMapper::requestToEntity)
                .collect(Collectors.toList()));
    }


    /**
     * Function returns every member of family with given id.
     *
     * @param familyId id of family
     * @return List of family members with familyId equal to the one passed in parameter
     */
    @Override
    public List<FamilyMemberResponse> searchFamilyMember(int familyId) {
        List<FamilyMemberEntity> familyMemberEntities = familyMemberRepository.getFamilyMembersByFamilyId(familyId);

        if(familyMemberEntities.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Given family has no members");
        }

        return familyMemberEntities.stream()
                .map(familyMemberResponseMapper::entityToResponse)
                .collect(Collectors.toList());
    }
}
