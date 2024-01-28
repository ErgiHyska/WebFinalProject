package com.UniversalRent.UniversalRent.repository;

import com.UniversalRent.UniversalRent.entity.Renter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RenterRepository extends JpaRepository<Renter,Long> {
}
