package br.com.empresa.service;

import br.com.empresa.UsuarioDetalhes;
import br.com.empresa.model.Usuario;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class UsuarioService {

    @Transactional
    public Usuario create(Usuario usuario) {
        usuario.senha = BcryptUtil.bcryptHash(usuario.senha);
        Usuario.persist(usuario);
        return usuario;
    }

    @Transactional
    public void desativar(Long id) {
        Usuario usuario = Usuario.findById(id);
        usuario.ativo = false;
        usuario.persist();
    }

    @Transactional
    public Usuario editarUsuario(Usuario usuario) {
        Usuario.persist(usuario);
        return usuario;
    }

    public List<Usuario> listarTodos() {
        return Usuario.listAll();
    }

    public UsuarioDetalhes detalhar(Long id) {
        return Usuario.find("id = ?1", id).project(UsuarioDetalhes.class).firstResult();
    }

    public Usuario findByEmail(String email) {
        return Usuario.find("email = ?1", email).firstResult();
    }
}
