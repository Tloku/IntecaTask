package inteca.task.FamilyApp.repository;

import inteca.task.FamilyApp.model.entity.FamilyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FamilyRepository extends JpaRepository<FamilyEntity, Integer> {


    @Query(
            value = "SELECT * FROM \"familydb\".family " +
                    "WHERE family_name = ?1",
            nativeQuery = true
    )
    Optional<FamilyEntity> existsByFamilyName(String familyName);
}
