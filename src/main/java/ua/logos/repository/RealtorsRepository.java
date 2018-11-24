package ua.logos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.logos.entity.Realtors;

@Repository
public interface RealtorsRepository extends JpaRepository<Realtors, Long>{

}
