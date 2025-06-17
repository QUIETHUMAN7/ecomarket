package com.eckomarket.cl.eckomarket;



import com.eckomarket.cl.eckomarket.model.DetalleVenta;
import com.eckomarket.cl.eckomarket.model.Producto;
import com.eckomarket.cl.eckomarket.model.Usuario;
import com.eckomarket.cl.eckomarket.model.Venta;
import com.eckomarket.cl.eckomarket.repository.ProductoRepository;
import com.eckomarket.cl.eckomarket.repository.UsuarioRepository;
import com.eckomarket.cl.eckomarket.repository.VentaRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


import java.util.*;


@Profile("default")
@Component
public class DataLoader implements CommandLineRunner {


    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private ProductoRepository productoRepository;


    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(new Locale("es"));
        Random random = new Random();


        // Generar productos
        for (int i = 0; i < 10; i++) {
            Producto producto = new Producto();
            producto.setNombre(faker.commerce().productName());
            producto.setPrecio(faker.number().randomDouble(2, 1000, 100000)); // Entre 1.000 y 100.000
            producto.setStock(faker.number().numberBetween(1, 100));
            producto.setDescripcion(faker.commerce().material() + " - " + faker.commerce().productName());


            productoRepository.save(producto);
        }


        // Generar usuarios
        for (int i = 0; i < 50; i++) {
            Usuario usuario = new Usuario();
            usuario.setRun(faker.idNumber().valid());
            usuario.setNombres(faker.name().firstName());
            usuario.setApellidos(faker.name().lastName());
            usuario.setFechaNacimiento(new Date()); // Entre 18 y 65 años
            usuario.setCorreo(faker.internet().emailAddress());


            usuarioRepository.save(usuario);
        }


        List<Usuario> usuarios = usuarioRepository.findAll();
        List<Producto> productos = productoRepository.findAll();


        for (int i = 0; i < 20; i++) {
            Venta venta = new Venta();
            venta.setFecha(new Date());


            Usuario usuario = usuarios.get(random.nextInt(usuarios.size()));
            venta.setClienteId(usuario.getId());


            int cantidadDetalles = faker.number().numberBetween(1, 3); // 1 a 3 productos por venta
            List<DetalleVenta> detalles = new ArrayList<>();
            double total = 0.0;


            for (int j = 0; j < cantidadDetalles; j++) {
                Producto producto = productos.get(random.nextInt(productos.size()));
                int cantidad = faker.number().numberBetween(1, 5);
                double subtotal = producto.getPrecio() * cantidad;
                total += subtotal;


                DetalleVenta detalle = new DetalleVenta();
                detalle.setProductoId(producto.getId());
                detalle.setCantidad(cantidad);
                detalle.setPrecioUnitario(producto.getPrecio());
                detalle.setVenta(venta);


                detalles.add(detalle);
            }


            venta.setTotal(total);
            venta.setDetalles(detalles);


            ventaRepository.save(venta);
        }
    }
}



