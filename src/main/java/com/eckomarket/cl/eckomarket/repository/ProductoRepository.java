package com.eckomarket.cl.eckomarket.repository;

import com.eckomarket.cl.eckomarket.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
