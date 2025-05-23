package com.mateo.bazar_api.repository;

import com.mateo.bazar_api.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface IVentaRepository extends JpaRepository<Venta, Long> {
}
