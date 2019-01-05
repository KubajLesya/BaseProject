package ua.logos.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import ua.logos.entity.Action;
import ua.logos.entity.Poster;
import ua.logos.entity.Type;
@Repository
public interface PosterRepository extends JpaRepository<Poster, Long >, JpaSpecificationExecutor<Poster> {
	List<Poster> findByLocationId(Long id);
	List<Poster> findByLocationRegion(String region);
	List<Poster> findByRealtorsId(Long id);
	List<Poster> findByRealtorsAgencyName(String agencyName);
	List<Poster> findByRealtyType(Type type);
	List<Poster> findByAction(Action action);
	
	List<Poster> findByLocationCityOrRealtorsFullName(String city, String fullName);
}
