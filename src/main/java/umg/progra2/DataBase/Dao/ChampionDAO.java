package umg.progra2.DataBase.Dao;

import umg.progra2.DataBase.Model.ChampionModel;
import umg.progra2.DataBase.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChampionDAO {

    public void crear(ChampionModel champion) throws SQLException {
        String sql = "INSERT INTO equipos_champions (nombre, pais, ciudad, estadio, fundacion, entrenador, web_oficial, facebook, twitter, instagram, patrocinador_principal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, champion.getNombre());
            pstmt.setString(2, champion.getPais());
            pstmt.setString(3, champion.getCiudad());
            pstmt.setString(4, champion.getEstadio());
            pstmt.setInt(5, champion.getFundacion());
            pstmt.setString(6, champion.getEntrenador());
            pstmt.setString(7, champion.getWebOficial());
            pstmt.setString(8, champion.getFacebook());
            pstmt.setString(9, champion.getTwitter());
            pstmt.setString(10, champion.getInstagram());
            pstmt.setString(11, champion.getPatrocinadorPrincipal());

            pstmt.executeUpdate();
        }
    }

    public ChampionModel buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM equipos_champions WHERE id_equipo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extraerChampionDelResultSet(rs);
                }
            }
        }
        return null;
    }

    public void actualizar(ChampionModel champion) throws SQLException {
        String sql = "UPDATE equipos_champions SET nombre = ?, pais = ?, ciudad = ?, estadio = ?, fundacion = ?, entrenador = ?, web_oficial = ?, facebook = ?, twitter = ?, instagram = ?, patrocinador_principal = ? WHERE id_equipo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, champion.getNombre());
            pstmt.setString(2, champion.getPais());
            pstmt.setString(3, champion.getCiudad());
            pstmt.setString(4, champion.getEstadio());
            pstmt.setInt(5, champion.getFundacion());
            pstmt.setString(6, champion.getEntrenador());
            pstmt.setString(7, champion.getWebOficial());
            pstmt.setString(8, champion.getFacebook());
            pstmt.setString(9, champion.getTwitter());
            pstmt.setString(10, champion.getInstagram());
            pstmt.setString(11, champion.getPatrocinadorPrincipal());
            pstmt.setInt(12, champion.getIdEquipo());

            pstmt.executeUpdate();
        }
    }

    public void borrar(int id) throws SQLException {
        String sql = "DELETE FROM equipos_champions WHERE id_equipo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    private ChampionModel extraerChampionDelResultSet(ResultSet rs) throws SQLException {
        ChampionModel champion = new ChampionModel();
        champion.setIdEquipo(rs.getInt("id_equipo"));
        champion.setNombre(rs.getString("nombre"));
        champion.setPais(rs.getString("pais"));
        champion.setCiudad(rs.getString("ciudad"));
        champion.setEstadio(rs.getString("estadio"));
        champion.setFundacion(rs.getInt("fundacion"));
        champion.setEntrenador(rs.getString("entrenador"));
        champion.setWebOficial(rs.getString("web_oficial"));
        champion.setFacebook(rs.getString("facebook"));
        champion.setTwitter(rs.getString("twitter"));
        champion.setInstagram(rs.getString("instagram"));
        champion.setPatrocinadorPrincipal(rs.getString("patrocinador_principal"));
        champion.setCreadoEn(rs.getTimestamp("creado_en"));
        return champion;
    }
}
