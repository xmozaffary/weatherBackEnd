package se.ali.weatherbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ali.weatherbackend.entity.CoordinatesEntity;

@Repository
public interface CoordinatesRepository extends JpaRepository<CoordinatesEntity, Long> {
}
