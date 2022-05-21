package inteca.task.FamilyApp.service.impl;

import inteca.task.FamilyApp.model.api.Request.FamilyMemberRequest;
import inteca.task.FamilyApp.model.api.Request.FamilyRequest;
import inteca.task.FamilyApp.model.api.Response.FamilyMemberResponse;
import inteca.task.FamilyApp.model.api.Response.FamilyResponse;
import inteca.task.FamilyApp.model.dto.FamilyDto;
import inteca.task.FamilyApp.model.entity.FamilyEntity;
import inteca.task.FamilyApp.repository.FamilyRepository;
import inteca.task.FamilyApp.service.FamilyService;
import inteca.task.FamilyApp.service.mapper.FamilyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FamilyServiceImpl implements FamilyService {

    private final FamilyRepository familyRepository;
    private final FamilyMapper familyMapper;

    /**
     * Function that gets family data and members of given family
     *
     * @param familyId id of family to be returned
     * @return FamilyResponse object
     */
    @Override
    public FamilyResponse getFamily(int familyId) {
        Optional<FamilyEntity> familyEntity = familyRepository.findById(familyId);
        if(familyEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Family with given Id doesn't exists!");
        }
        FamilyDto family = familyMapper.entityToDto(familyEntity.get());


        List<FamilyMemberResponse> familyMembers = getFamilyMembersFromFamilyMemberApp(family.getId());

        return FamilyResponse.builder()
                .id(family.getId())
                .familyName(family.getFamilyName())
                .nrOfInfants(family.getNrOfInfants())
                .nrOfChildren(family.getNrOfChildren())
                .nrOfAdults(family.getNrOfAdults())
                .familyMembers(familyMembers)
                .build();
    }

    /**
     * Function that hits FamilyMemberApp and gets every member of family with given id
     *
     * @param familyId
     * @return members with familyId given in a parameter will be returned
     */
    @Override
    public List<FamilyMemberResponse> getFamilyMembersFromFamilyMemberApp(int familyId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:9110//family_members/get";
        ResponseEntity<FamilyMemberResponse[]> response = restTemplate.getForEntity(
                url+"/"+familyId,
                FamilyMemberResponse[].class);

        FamilyMemberResponse[] familyMemberResponses = response.getBody();

        if(familyMemberResponses == null) {
            return null;
        }
        return Arrays.asList(familyMemberResponses);
    }


    /**
     *  Function used to create new family.
     *
     * @param familyRequest Object containg family data
     * @return id of new created family
     */
    @Override
    @Modifying
    @Transactional
    public int createFamily(FamilyRequest familyRequest) {
        String familyName = familyRequest.getFamilyName();
        boolean isFamilyDataCorrect = validateFamilyData(familyRequest.getFamilyMembers(), familyRequest.getNrOfChildren(),
                familyRequest.getNrOfAdults(), familyRequest.getNrOfInfants());

        if(!isFamilyDataCorrect) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Family members are not given correctly");
        }
        if(familyRepository.existsByFamilyName(familyName).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Family with given name already exists!");
        }
        if(familyName.isEmpty() || familyName.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Family to save has no name!");
        }

        FamilyDto savedEntity = familyMapper.entityToDto(familyRepository.save(FamilyEntity.builder()
                                                                            .familyName(familyName)
                                                                            .nrOfAdults(familyRequest.getNrOfAdults())
                                                                            .nrOfChildren(familyRequest.getNrOfChildren())
                                                                            .nrOfInfants(familyRequest.getNrOfInfants())
                                                                            .build()));

        List<FamilyMemberRequest> familyMemberRequests = familyRequest.getFamilyMembers();

        for(FamilyMemberRequest familyMemberRequest : familyMemberRequests) { //we assume familyName and id are not given
            familyMemberRequest.setFamilyId(savedEntity.getId());
            familyMemberRequest.setFamilyName(familyName);
        }

        postFamilyMembers(familyMemberRequests);
        return savedEntity.getId();
    }


    /**
     * Function calls FamilyMemberApp resulting familyMembers being stored in FamilyMemberDB
     *
     * @param familyMemberRequests familyMembers to be stored
     */
    @Override
    public void postFamilyMembers(List<FamilyMemberRequest> familyMemberRequests) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:9110//family_members/create"; // FamilyMemberApp call
        HttpEntity<List<FamilyMemberRequest>> request = new HttpEntity<>(familyMemberRequests);
        restTemplate.postForObject(url, request, FamilyMemberRequest.class);
    }


    /**
     * Function that checks if given family data is correct.
     *
     * @param familyMembers list of members of family
     * @param nrOfChildren number of people between 4 and 16 y/o
     * @param nrOfAdults number of people above 16 y/o
     * @param nrOfInfants number of people under 4 y/o
     * @return true - if data is correct, false otherwise
     */
    @Override
    public boolean validateFamilyData(List<FamilyMemberRequest> familyMembers, int nrOfChildren, int nrOfAdults, int nrOfInfants) {
        int realNrOfChildren = 0, realNrOfAdults = 0, realNrOfInfants = 0;
        int age;
        for(FamilyMemberRequest member : familyMembers) {
             age = member.getAge();

            if(age < 0) {
                return false;
            } else if (age < 4) {
                realNrOfInfants++;
            } else if (age < 16) {
                realNrOfChildren++;
            } else {
                realNrOfAdults++;
            }
        }

        return realNrOfAdults == nrOfAdults && realNrOfChildren == nrOfChildren && realNrOfInfants == nrOfInfants;
            // returns true if number of every member of family is correct
    }
}
