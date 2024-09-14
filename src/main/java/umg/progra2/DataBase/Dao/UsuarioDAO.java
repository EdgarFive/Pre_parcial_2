package umg.progra2.DataBase.Dao;

import umg.progra2.DataBase.Model.UsuarioModel;
import umg.progra2.DataBase.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public void crearUsuario(UsuarioModel usuario) throws SQLException {
        String query = "INSERT INTO tb_usuarios (carne, nombre, correo, seccion, telegramid, activo) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, usuario.getCarne());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getCorreo());
            stmt.setString(4, usuario.getSeccion());
            stmt.setLong(5, usuario.getTelegramId());
            stmt.setString(6, usuario.getActivo());

            stmt.executeUpdate();
        }
    }

    public UsuarioModel buscarUsuarioPorId(int id) throws SQLException {
        String query = "SELECT * FROM tb_usuarios WHERE idusuario = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                UsuarioModel usuario = new UsuarioModel();
                usuario.setIdUsuario(rs.getInt("idusuario"));
                usuario.setCarne(rs.getString("carne"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setSeccion(rs.getString("seccion"));
                usuario.setTelegramId(rs.getLong("telegramid"));
                usuario.setActivo(rs.getString("activo"));
                return usuario;
            }
        }
        return null;
    }

    public void actualizarUsuario(UsuarioModel usuario) throws SQLException {
        String query = "UPDATE tb_usuarios SET carne = ?, nombre = ?, correo = ?, seccion = ?, telegramid = ?, activo = ? WHERE idusuario = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, usuario.getCarne());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getCorreo());
            stmt.setString(4, usuario.getSeccion());
            stmt.setLong(5, usuario.getTelegramId());
            stmt.setString(6, usuario.getActivo());
            stmt.setInt(7, usuario.getIdUsuario());

            stmt.executeUpdate();
        }
    }

    public void borrarUsuario(int id) throws SQLException {
        String query = "DELETE FROM tb_usuarios WHERE idusuario = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<UsuarioModel> listarUsuarios() throws SQLException {
        String query = "SELECT * FROM tb_usuarios";
        List<UsuarioModel> usuarios = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                UsuarioModel usuario = new UsuarioModel();
                usuario.setIdUsuario(rs.getInt("idusuario"));
                usuario.setCarne(rs.getString("carne"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setSeccion(rs.getString("seccion"));
                usuario.setTelegramId(rs.getLong("telegramid"));
                usuario.setActivo(rs.getString("activo"));
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    public boolean existecorreo (String correo) throws SQLException {
        String sql = "SELECT COUNT(*) FROM tb_usuarios WHERE correo = ?";
        try (Connection conn = DatabaseConnection.getConnection();){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, correo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}
