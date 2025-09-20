package br.com.empresa.service;

import br.com.empresa.model.Categoria;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CategoriaService {

    public List<Categoria> listarTodas() {
        return Categoria.listAll();
    }
}
