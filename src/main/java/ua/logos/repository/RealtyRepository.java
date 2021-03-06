package ua.logos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.logos.entity.Realty;
@Repository
public interface RealtyRepository extends JpaRepository<Realty, Long> {

}
