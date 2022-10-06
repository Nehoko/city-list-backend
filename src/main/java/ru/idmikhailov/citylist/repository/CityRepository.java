package ru.idmikhailov.citylist.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.idmikhailov.citylist.entity.City;

public interface CityRepository extends JpaRepository<City, Long> {

    // TODO: replace with full-text search (ts_query in postgreSQL)
    Page<City> findAllByNameIsLike(String search, Pageable pageable);
}
