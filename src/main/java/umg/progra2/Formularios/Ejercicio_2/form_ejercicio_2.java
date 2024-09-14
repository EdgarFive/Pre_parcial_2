package umg.progra2.Formularios.Ejercicio_2;

import umg.progra2.DataBase.Model.UsuarioModel;
import umg.progra2.DataBase.Service.DatosService;
import umg.progra2.DataBase.Service.UsuarioService;
import umg.progra2.Formularios.Ejercicio_1.From_Pre_parcial_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class form_ejercicio_2 {
    private JPanel form_ejercicio_2;
    private JLabel lb_idusuario;
    private JTextField textField1_idusuario;
    private JLabel lb_carbe;
    private JTextField textField1_carne;
    private JLabel lb_nombre;
    private JTextField textField1_nombre;
    private JLabel lb_correo;
    private JLabel lb_seccion;
    private JLabel lb_telegramid;
    private JLabel lb_activo;
    private JTextField textField1_correo;
    private JTextField textField1_seccion;
    private JTextField textField1_telegramid;
    private JTextField textField1_activo;
    private JButton button1_Crear;
    private JButton button1_buscar;
    private JButton button1_actualizar;
    private JButton button1_eliminar;

    public static void main(String[] args) {
        JFrame frame = new JFrame("form_ejercicio_2");
        frame.setContentPane(new form_ejercicio_2().form_ejercicio_2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }



    //AREA DE METODOS ================================================================================================================
    private void mmLimpiar(){
        textField1_idusuario.setText("");
        textField1_carne.setText("");
        textField1_nombre.setText("");
        textField1_correo.setText("");
        textField1_seccion.setText("");
        textField1_telegramid.setText("");
        textField1_activo.setText("");
    }

    public form_ejercicio_2() {

        //AREA DE BOTONES =============================================================

        //Boton CREAR ================================================================================================================
        button1_Crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsuarioModel usuario = new UsuarioModel();
                if(textField1_nombre.getText().isEmpty() || textField1_carne.getText().isEmpty() || textField1_telegramid.getText().isEmpty() || textField1_activo.getText().isEmpty() || textField1_seccion.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Además de ID Usuario, ninguno de los campos puede estar vacio");
                    return;
                }

                // Validar que el Telegram ID contenga solo números====
                String telegramIdText = textField1_telegramid.getText();
                if (!telegramIdText.matches("\\d+")) {
                    JOptionPane.showMessageDialog(null, "El Telegram ID debe contener solo números");
                    return;
                }

                //Validar que el correo no esté duplicado ===
                try {
                    if (new UsuarioService().existecorreo(textField1_correo.getText())) {
                        JOptionPane.showMessageDialog(null, "El correo ya está registrado. Por favor, use otro correo.");
                        return;
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al verificar el correo: " + ex.getMessage());
                    return;
                }

                usuario.setCarne(textField1_carne.getText());
                usuario.setNombre(textField1_nombre.getText());
                usuario.setCorreo(textField1_correo.getText());
                usuario.setSeccion(textField1_seccion.getText());
                usuario.setTelegramId(Long.parseLong(textField1_telegramid.getText()));
                usuario.setActivo(textField1_activo.getText());

                try {
                    new UsuarioService().agregarUsuario(usuario);
                    JOptionPane.showMessageDialog(null, "Usuario creado exitosamente");
                    // Limpiar los campos después de crear el usuario
                    mmLimpiar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al crear el usuario: " + ex.getMessage());
                }

            }
        });


        //Boton BUSCAR  ================================================================================================================
        button1_buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idusuario = textField1_idusuario.getText().isEmpty() ? 0 : Integer.parseInt(textField1_idusuario.getText());

                try {
                    UsuarioModel usuarioencontrado = new UsuarioService().obtenerUsuarioPorId(idusuario);
                    if (usuarioencontrado != null) {
                        textField1_carne.setText(usuarioencontrado.getCarne());
                        textField1_nombre.setText(usuarioencontrado.getNombre());
                        textField1_correo.setText(usuarioencontrado.getCorreo());
                        textField1_seccion.setText(usuarioencontrado.getSeccion());
                        textField1_telegramid.setText(Long.toString(usuarioencontrado.getTelegramId()));
                        textField1_activo.setText(usuarioencontrado.getActivo());
                    }else{
                        JOptionPane.showMessageDialog(null, "No se encontro el Dato");
                    }
                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error en la base de Datos");
                }
            }
        });


        //Boton ACTUALIZAR ================================================================================================================
        button1_actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Corroborar que la casilla del codigo no esté vacio :)
                if (textField1_idusuario.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Lo siento; La casilla del ID no puede estar vacia");
                    return;
                }

                try{
                    int idUsuario = Integer.parseInt(textField1_idusuario.getText());
                    UsuarioModel usuarioActual = new UsuarioService().obtenerUsuarioPorId(idUsuario);

                    if (usuarioActual == null) {
                        JOptionPane.showMessageDialog(null, "No se encontró el Usuario.");
                        return;
                    }

                    //Validar que el correo no esté duplicado ===
                    String nuevoCorreo = textField1_correo.getText();
                    if(!nuevoCorreo.equals(usuarioActual.getCorreo())){
                        if (new UsuarioService().existecorreo(textField1_correo.getText())) {
                            JOptionPane.showMessageDialog(null, "El correo ya está registrado. Por favor, use otro correo.");
                            return;
                        }
                    }

                    // Validar que el Telegram ID contenga solo números====
                    String telegramIdText = textField1_telegramid.getText();
                    if (!telegramIdText.matches("\\d+")) {
                        JOptionPane.showMessageDialog(null, "El Telegram ID debe contener solo números");
                        return;
                    }

                    UsuarioModel usuario = new UsuarioModel();

                    usuario.setCarne(textField1_carne.getText());
                    usuario.setNombre(textField1_nombre.getText());
                    usuario.setCorreo(textField1_correo.getText());
                    usuario.setSeccion(textField1_seccion.getText());
                    usuario.setTelegramId(Long.parseLong(textField1_telegramid.getText()));
                    usuario.setActivo(textField1_activo.getText());

                    int eeactualizar = JOptionPane.showConfirmDialog(null, "¿Seguro que de quieres actualizar los datos?");
                    if(eeactualizar == JOptionPane.YES_OPTION){
                        new UsuarioService().modificarUsuario(usuario);
                        JOptionPane.showMessageDialog(null, "Usuario Actualizado Correctamente");
                    }else{
                        return;
                    }
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Error en la base de Datos" +ex);
                }
            }
        });


        //Boton ELIMINAR ================================================================================================================
        button1_eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(textField1_idusuario.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Lo siento; La casilla del ID no puede estar vacia");
                    return;
                }

                int datousuario = Integer.parseInt(textField1_idusuario.getText());

                int eeEliminar = JOptionPane.showConfirmDialog(null, "¿Seguro de Eliminar usuario?");
                if(eeEliminar == JOptionPane.YES_OPTION){
                    try{
                        new DatosService().deleteDatos(datousuario);
                        mmLimpiar();
                        JOptionPane.showMessageDialog(null, "Usuario Eliminado Correctamente");
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(null, "Error en la base de Datos" +ex);
                    }
                }

            }
        });



    }
}
