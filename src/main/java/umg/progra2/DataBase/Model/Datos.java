package umg.progra2.DataBase.Model;

import java.sql.Date;
import java.sql.Timestamp;

public class Datos {
    private int codigo;
    private String nombre;
    private String apellido;
    private String departamento;
    private Timestamp fechaNacimiento;

    public Datos(){};

    public Datos(int codigo, String nombre, String apellido, String departamento, Timestamp fechaNacimiento) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.departamento = departamento;
        this.fechaNacimiento = fechaNacimiento;
    }

    // Getters and Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Timestamp getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Timestamp fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
