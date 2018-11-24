package ua.logos.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import ua.logos.entity.Poster;
@Repository
public interface PosterRepository extends JpaRepository<Poster, Long > {
	List<Poster> findByLocationId(Long id);
	List<Poster> findByRegion(String region);
	List<Poster> findByRealtorsId(Long id);
	List<Poster> findByAgencyName(String agencyName);
	List<Poster> findByType(String type);
	List<Poster> findByAction(String action);
}
