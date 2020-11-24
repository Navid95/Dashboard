package mtni.its.dashboard.repository;

import mtni.its.dashboard.domain.ENRSH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ENRSHRepo extends JpaRepository<ENRSH, Integer> {
}
