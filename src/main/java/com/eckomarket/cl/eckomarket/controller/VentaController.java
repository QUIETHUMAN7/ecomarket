package com.eckomarket.cl.eckomarket.controller;

import com.eckomarket.cl.eckomarket.model.Venta;
import com.eckomarket.cl.eckomarket.service.VentaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public List<Venta> listar() {
        return ventaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Venta obtener(@PathVariable Integer id) {
        return ventaService.obtenerPorId(id);
    }

    @PostMapping
    public Venta crear(@RequestBody Venta venta) {
        return ventaService.guardar(venta);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        ventaService.eliminar(id);
    }
}
