package com.app.sip.repositories;

import com.app.sip.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandsRepository extends JpaRepository<Brand, Long> {
}
