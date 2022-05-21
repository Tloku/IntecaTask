package inteca.task.FamilyApp.model.api.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FamilyRequest {
    int id;
    String familyName;
    int nrOfAdults;
    int nrOfChildren;
    int nrOfInfants;
    List<FamilyMemberRequest> familyMembers;
}
