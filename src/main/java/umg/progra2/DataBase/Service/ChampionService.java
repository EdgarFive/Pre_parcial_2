package umg.progra2.DataBase.Service;

import umg.progra2.DataBase.Dao.ChampionDAO;
import umg.progra2.DataBase.Model.ChampionModel;

import java.sql.SQLException;

public class ChampionService {
    private ChampionDAO championDAO;

    public ChampionService() {
        this.championDAO = new ChampionDAO();
    }

    public void crearEquipo(ChampionModel champion) throws SQLException {
        championDAO.crear(champion);
    }

    public ChampionModel buscarEquipoPorId(int id) throws SQLException {
        return championDAO.buscarPorId(id);
    }

    public void actualizarEquipo(ChampionModel champion) throws SQLException {
        championDAO.actualizar(champion);
    }

    public void borrarEquipo(int id) throws SQLException {
        championDAO.borrar(id);
    }

}
