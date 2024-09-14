package umg.progra2.Formularios.Ejercicio_1;

import umg.progra2.DataBase.Model.Datos;
import umg.progra2.DataBase.Service.DatosService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class From_Pre_parcial_2 extends JFrame {
    private JPanel From_base;
    private JLabel lb_codigo;
    private JLabel lb_titulo;
    private JTextField textField1_codigo;
    private JLabel lb_nombre;
    private JTextField textField1_nombre;
    private JLabel lb_apellido;
    private JTextField textField1_apellido;
    private JLabel lb_departamento;
    private JTextField textField1_departamento;
    private JComboBox comboBox1_departamento;
    private JLabel lb_fecha_nacimiento;
    private JButton button1_buscar;
    private JButton button1_actualizar;
    private JButton button1_eliminar;
    private JButton button1_agregar;
    private JTextField textField2_fecha_nacimiento;


    public static void main(String[] args) {
        JFrame frame = new JFrame("From_Pre_parcial_2");
        frame.setContentPane(new From_Pre_parcial_2().From_base);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }

public void rellenar_combo (){
    //Agregar los departamentos al combobox ==
    String[] departamentos = { "","Guatemala", "Alta Verapaz", "Baja Verapaz", "Chimaltenango", "Chiquimula", "El Progreso", "Escuintla", "Huehuetenango", "Izabal", "Jalapa", "Jutiapa", "Petén", "Quetzaltenango", "Quiché", "Retalhuleu", "Sacatepéquez", "San Marcos", "Santa Rosa", "Solalá", "Suchitepéquez", "Totonicapán", "Zacapa"};

    for (String dep : departamentos){ //Para recorrer todos los departanentos ==
        comboBox1_departamento.addItem(dep);
    }
}

    //Ejercicio 1
    public From_Pre_parcial_2() {

        rellenar_combo();

        //AREA DE BOTONES =================================================================


        //Boton BUSCAR ==============
        button1_buscar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int idCodigo = textField1_codigo.getText().isEmpty() ? 0 : Integer.parseInt(textField1_codigo.getText());

                try {
                     Datos datoencontrado = new DatosService().getDatos(idCodigo);
                     if(datoencontrado != null){
                         textField1_nombre.setText(datoencontrado.getNombre());
                         textField1_apellido.setText(datoencontrado.getApellido());
                         comboBox1_departamento.setSelectedItem(datoencontrado.getDepartamento());
                         textField2_fecha_nacimiento.setText(datoencontrado.getFechaNacimiento().toString());

                     }else{
                         JOptionPane.showMessageDialog(null, "No se encontro el Dato");
                     }
                 }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Error en la base de Datos");
                 }
            }
        });


        //Boton de AGREGAR uno nuevo ===
        button1_agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Datos datos = new Datos();
                datos.setNombre(textField1_nombre.getText());
                datos.setApellido(textField1_apellido.getText());
                datos.setDepartamento(comboBox1_departamento.getSelectedItem().toString());
                if (textField1_nombre.getText().trim().isEmpty() && textField1_apellido.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Tanto el nombre como el apellido no pueden estar vacíos.");
                    return;
                }

                try {
                    Date parsedDate = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss").parse(textField2_fecha_nacimiento.getText());
                    Timestamp timestamp = new Timestamp(parsedDate.getTime());
                    datos.setFechaNacimiento(timestamp);

                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Formato de fecha no válido, se configuro a la fecha actual");
                    datos.setFechaNacimiento(new Timestamp(System.currentTimeMillis()));;
                }

                try {
                    new DatosService().addDatos(datos);
                    JOptionPane.showMessageDialog(null, "Datos agregados exitosamente");

                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Error al guardar los Datos: "+ex.getMessage());
                }
            }
        });

        //Boton para ACTUALIZAR Datos ==================================================================================================================
        button1_actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Corroborar que la casilla del codigo no esté vacio :)
                if (textField1_codigo.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Lo siento; La casilla de codigo no puede estar vacia");
                    return;
                }

                Datos datos = new Datos();
                datos.setNombre(textField1_nombre.getText());
                datos.setApellido(textField1_apellido.getText());
                datos.setDepartamento(comboBox1_departamento.getSelectedItem().toString());

                try {
                    Date parsedDate = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss").parse(textField2_fecha_nacimiento.getText());
                    Timestamp timestamp = new Timestamp(parsedDate.getTime());
                    datos.setFechaNacimiento(timestamp);

                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Formato de fecha no válido, se configuro a la fecha actual");
                    datos.setFechaNacimiento(new Timestamp(System.currentTimeMillis()));;
                }

                //Vamos a preguntar antes de actualizar la información.
                int eeactualizar = JOptionPane.showConfirmDialog(null, "¿Seguro de actualizar los datos?");
                if (eeactualizar == JOptionPane.YES_OPTION){
                    try {
                        new DatosService().updateDatos(datos);
                        JOptionPane.showMessageDialog(null, "Datos actualizados exitosamente");
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(null, "Error al actualizar los Datos: "+ex.getMessage());
                    }
                }
            }
        });


        //Boton ELIMINAR ===============================================================================================================
        button1_eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Corroborar que la casilla del codigo no esté vacio :)
                if (textField1_codigo.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Lo siento; La casilla de codigo no puede estar vacia");
                    return;
                }
                int datocodigo = Integer.parseInt(textField1_codigo.getText());

                int eeactualizar = JOptionPane.showConfirmDialog(null, "¿Seguro de eliminar los datos?");
                if (eeactualizar == JOptionPane.YES_OPTION){
                    try {
                        new DatosService().deleteDatos(datocodigo);
                        JOptionPane.showMessageDialog(null, "Datos actualizados exitosamente");
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(null, "Error al actualizar los Datos: "+ex.getMessage());
                    }
                }

            }
        });

        //============================================================================
        // INFORMACIÓN QUE NO VOY A USAR =============================================
        //============================================================================

        lb_titulo.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
    }


}
