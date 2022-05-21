package inteca.task.FamilyMemberApp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FamilyMemberDto {
    int id;
    int familyId;
    int age;
    String familyName;
    String givenName;
}
