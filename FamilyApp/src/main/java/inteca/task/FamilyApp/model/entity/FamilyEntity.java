package inteca.task.FamilyApp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Builder
@Entity
@Data
@Table(name = "Family", schema = "FamilyDB",
        uniqueConstraints = {
            @UniqueConstraint(name = "familyname", columnNames = "familyName")
        })
@NoArgsConstructor
@AllArgsConstructor
public class FamilyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String familyName;

    int nrOfAdults;

    int nrOfChildren;

    int nrOfInfants;
}
