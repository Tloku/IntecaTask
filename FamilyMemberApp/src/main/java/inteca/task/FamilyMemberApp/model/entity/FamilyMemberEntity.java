package inteca.task.FamilyMemberApp.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Builder
@Entity
@Data
@Table(name = "FamilyMember", schema = "FamilymemberDB")
@NoArgsConstructor
@AllArgsConstructor
public class FamilyMemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    int familyId;

    int age;

    String familyName;

    String givenName;
}
