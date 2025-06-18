package com.eckomarket.cl.eckomarket;

import com.eckomarket.cl.eckomarket.model.Venta;
import com.eckomarket.cl.eckomarket.repository.VentaRepository;
import com.eckomarket.cl.eckomarket.service.VentaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class VentaServiceTest {

    @Autowired
    private VentaService ventaService;

    @MockitoBean
    private VentaRepository ventaRepository;

    @Test
    public void testFindAll() {
        Venta venta = new Venta(1, 1, new Date(), 15000.0, null);

        when(ventaRepository.findAll()).thenReturn(List.of(venta));

        List<Venta> ventas = ventaService.obtenerTodas();

        assertNotNull(ventas);
        assertEquals(1, ventas.size());
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Venta venta = new Venta(id, 1, new Date(), 15000.0, null);

        when(ventaRepository.findById(id)).thenReturn(Optional.of(venta));

        Venta found = ventaService.obtenerPorId(id);

        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testSave() {
        Venta venta = new Venta(1, 1, new Date(), 20000.0, null);

        when(ventaRepository.save(venta)).thenReturn(venta);

        Venta saved = ventaService.guardar(venta);

        assertNotNull(saved);
        assertEquals(20000.0, saved.getTotal());
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;

        doNothing().when(ventaRepository).deleteById(id);

        ventaService.eliminar(id);

        verify(ventaRepository, times(1)).deleteById(id);
    }
}
