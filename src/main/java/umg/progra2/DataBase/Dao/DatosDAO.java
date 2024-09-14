package umg.progra2.DataBase.Dao;

import umg.progra2.DataBase.Model.Datos;
import umg.progra2.DataBase.db.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatosDAO {

    public void insert(Datos datos) throws SQLException {
        String query = "INSERT INTO tb_datos (nombre, apellido, departamento, fecha_nacimiento) VALUES (?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, datos.getNombre());
            ps.setString(2, datos.getApellido());
            ps.setString(3, datos.getDepartamento());
            ps.setTimestamp(4, datos.getFechaNacimiento());
            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    datos.setCodigo(generatedKeys.getInt(1));
                }
            }
        }
    }

    public Datos get(int codigo) throws SQLException {
        String query = "SELECT * FROM tb_datos WHERE codigo = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Datos(
                            rs.getInt("codigo"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("departamento"),
                            rs.getTimestamp("fecha_nacimiento")
                    );
                }
            }
        }
        return null;
    }

    public List<Datos> getAll() throws SQLException {
        List<Datos> datosList = new ArrayList<>();
        String query = "SELECT * FROM tb_datos";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                datosList.add(new Datos(
                        rs.getInt("codigo"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("departamento"),
                        rs.getTimestamp("fecha_nacimiento")
                ));
            }
        }
        return datosList;
    }

    public void update(Datos datos) throws SQLException {
        String query = "UPDATE tb_datos SET nombre = ?, apellido = ?, departamento = ?, fecha_nacimiento = ? WHERE codigo = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, datos.getNombre());
            ps.setString(2, datos.getApellido());
            ps.setString(3, datos.getDepartamento());
            ps.setTimestamp(4, datos.getFechaNacimiento());
            ps.setInt(5, datos.getCodigo());
            ps.executeUpdate();
        }
    }

    public void delete(int codigo) throws SQLException {
        String query = "DELETE FROM tb_datos WHERE codigo = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, codigo);
            ps.executeUpdate();
        }
    }
}

