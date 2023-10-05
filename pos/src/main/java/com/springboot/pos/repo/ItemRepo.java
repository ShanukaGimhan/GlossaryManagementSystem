package com.springboot.pos.repo;

import com.springboot.pos.entity.Item;
import com.springboot.pos.entity.enums.MeasuringUnitType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface ItemRepo extends JpaRepository<Item,Integer> {

    // create method find data using status and type using ResponseEntity
    List<Item> findByActiveStateAndMeasuringUnitTypeEquals(boolean status, MeasuringUnitType type);

    //create method find data using status by Pagination
    Page<Item> findAllByActiveStateEquals(boolean activeStatus, Pageable pageable);

    long countAllByActiveStateEquals(boolean activeStatus);

    Page<Item> findAllByItemNameEqualsIgnoreCase(String name, Pageable pageable);

    long countAllByItemNameEqualsIgnoreCase(String name);

    Page<Item> findAllBySellingPriceEquals(double sellingPrice, Pageable pageable);

    long countAllBySellingPriceEquals(double sellingPrice);
}
