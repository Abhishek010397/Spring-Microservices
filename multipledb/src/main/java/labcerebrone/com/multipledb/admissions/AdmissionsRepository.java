package labcerebrone.com.multipledb.admissions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmissionsRepository extends JpaRepository<Admissions, Integer> {
}
