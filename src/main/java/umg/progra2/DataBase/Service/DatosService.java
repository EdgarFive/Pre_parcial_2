package umg.progra2.DataBase.Service;

import umg.progra2.DataBase.Dao.DatosDAO;
import umg.progra2.DataBase.Model.Datos;

import java.sql.SQLException;
import java.util.List;

public class DatosService {
    private DatosDAO datosDAO = new DatosDAO();

    public void addDatos(Datos datos) throws SQLException {
        datosDAO.insert(datos);
    }

    public Datos getDatos(int codigo) throws SQLException {
        return datosDAO.get(codigo);
    }

    public List<Datos> getAllDatos() throws SQLException {
        return datosDAO.getAll();
    }

    public void updateDatos(Datos datos) throws SQLException {
        datosDAO.update(datos);
    }

    public void deleteDatos(int codigo) throws SQLException {
        datosDAO.delete(codigo);
    }
}

