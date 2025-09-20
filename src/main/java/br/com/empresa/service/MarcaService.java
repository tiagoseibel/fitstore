package br.com.empresa.service;

import br.com.empresa.model.Marca;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class MarcaService {

    public List<Marca> listarTodas() {
        return Marca.listAll();
    }
}
