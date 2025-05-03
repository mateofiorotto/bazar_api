package com.mateo.bazar_api.repository;

import com.mateo.bazar_api.model.VentaProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IVentaProductoRepository extends JpaRepository<VentaProducto, Long> {
    Optional<VentaProducto> findTopByOrderByTotalDesc();
}
