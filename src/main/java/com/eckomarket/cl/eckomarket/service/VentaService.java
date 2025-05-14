package com.eckomarket.cl.eckomarket.service;

import com.eckomarket.cl.eckomarket.model.Venta;
import com.eckomarket.cl.eckomarket.repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;

    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public List<Venta> obtenerTodas() {
        return ventaRepository.findAll();
    }

    public Venta guardar(Venta venta) {
        return ventaRepository.save(venta);
    }

    public Venta obtenerPorId(Integer id) {
        return ventaRepository.findById(id).orElse(null);
    }

    public void eliminar(Integer id) {
        ventaRepository.deleteById(id);
    }
}
