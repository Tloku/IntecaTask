package inteca.task.FamilyApp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FamilyDto {
    int id;
    String familyName;
    int nrOfAdults;
    int nrOfChildren;
    int nrOfInfants;
}
