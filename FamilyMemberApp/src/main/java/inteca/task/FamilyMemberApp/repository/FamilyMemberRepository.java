package inteca.task.FamilyMemberApp.repository;


import inteca.task.FamilyMemberApp.model.entity.FamilyMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyMemberRepository extends JpaRepository<FamilyMemberEntity, Integer> {


    @Query(
            value = "SELECT * FROM familymemberdb.family_member " +
                    "WHERE family_id = ?1",
            nativeQuery = true
    )
    List<FamilyMemberEntity> getFamilyMembersByFamilyId(int familyId);
}
