package inteca.task.FamilyApp.service.mapper;

import inteca.task.FamilyApp.model.dto.FamilyDto;
import inteca.task.FamilyApp.model.entity.FamilyEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FamilyMapper {

    public FamilyDto entityToDto(FamilyEntity familyEntity) {
        if(familyEntity == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Family is empty");
        }

        return FamilyDto.builder()
                .id(familyEntity.getId())
                .familyName(familyEntity.getFamilyName())
                .nrOfAdults(familyEntity.getNrOfAdults())
                .nrOfChildren(familyEntity.getNrOfChildren())
                .nrOfInfants(familyEntity.getNrOfInfants())
                .build();
    }

    public FamilyEntity dtoToEntity(FamilyDto familyDto) {
        if(familyDto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Family dto is empty");
        }

        return FamilyEntity.builder()
                .familyName(familyDto.getFamilyName())
                .nrOfAdults(familyDto.getNrOfAdults())
                .nrOfChildren(familyDto.getNrOfChildren())
                .nrOfInfants(familyDto.getNrOfInfants())
                .build();
    }
}
