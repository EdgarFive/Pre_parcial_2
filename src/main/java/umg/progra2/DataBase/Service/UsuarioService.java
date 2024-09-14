package umg.progra2.DataBase.Service;

import umg.progra2.DataBase.Dao.UsuarioDAO;
import umg.progra2.DataBase.Model.UsuarioModel;

import java.sql.SQLException;
import java.util.List;

public class UsuarioService {
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void agregarUsuario(UsuarioModel usuario) throws SQLException {
        usuarioDAO.crearUsuario(usuario);
    }

    public UsuarioModel obtenerUsuarioPorId(int id) throws SQLException {
        return usuarioDAO.buscarUsuarioPorId(id);
    }

    public void modificarUsuario(UsuarioModel usuario) throws SQLException {
        usuarioDAO.actualizarUsuario(usuario);
    }

    public void eliminarUsuario(int id) throws SQLException {
        usuarioDAO.borrarUsuario(id);
    }

    public List<UsuarioModel> listarUsuarios() throws SQLException {
        return usuarioDAO.listarUsuarios();
    }

    public boolean existecorreo(String correo) throws SQLException {
        return usuarioDAO.existecorreo(correo);
    }


}
