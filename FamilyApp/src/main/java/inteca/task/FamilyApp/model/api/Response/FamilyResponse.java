package inteca.task.FamilyApp.model.api.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FamilyResponse {
    int id;
    String familyName;
    int nrOfAdults;
    int nrOfChildren;
    int nrOfInfants;
    List<FamilyMemberResponse> familyMembers;
}
