package inteca.task.FamilyApp.model.api.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FamilyMemberResponse {
    int id;
    int familyId;
    int age;
    String familyName;
    String givenName;
}

