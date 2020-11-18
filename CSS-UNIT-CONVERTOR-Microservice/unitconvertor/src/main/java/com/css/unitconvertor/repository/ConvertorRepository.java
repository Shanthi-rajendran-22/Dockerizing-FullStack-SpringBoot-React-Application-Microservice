package com.css.unitconvertor.repository;

import com.css.unitconvertor.model.Convertor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConvertorRepository extends JpaRepository<Convertor,Long> {
    Convertor findByFromAndTo(String from, String to);
}
