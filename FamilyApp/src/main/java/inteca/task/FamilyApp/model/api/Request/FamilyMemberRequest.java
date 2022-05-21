package inteca.task.FamilyApp.model.api.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FamilyMemberRequest {
    int id;
    int familyId;
    int age;
    String familyName;
    String givenName;
}
