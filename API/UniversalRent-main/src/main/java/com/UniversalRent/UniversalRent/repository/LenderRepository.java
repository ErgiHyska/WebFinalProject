package com.UniversalRent.UniversalRent.repository;

import com.UniversalRent.UniversalRent.entity.Lender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LenderRepository extends JpaRepository<Lender,Long> {
}
