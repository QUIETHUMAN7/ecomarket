package com.eckomarket.cl.eckomarket.service;

import com.eckomarket.cl.eckomarket.model.Usuario;
import com.eckomarket.cl.eckomarket.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Integer id) {
        return usuarioRepository.findById(id).get();

    }

    public  Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);

    }

    public void delete(Integer id) {
        usuarioRepository.deleteById(id);

    }
}