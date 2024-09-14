package umg.progra2.Formularios.Ejercicio_2;

import umg.progra2.DataBase.Model.UsuarioModel;
import umg.progra2.DataBase.Service.DatosService;
import umg.progra2.DataBase.Service.UsuarioService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class form_ejercicio_2 extends JFrame {
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
    private JComboBox comboBox1_seccion;




    //AREA DE METODOS ================================================================================================================
    //Metodo para limpiar las cacillas ====
    private void mmLimpiar(){
        textField1_idusuario.setText("");
        textField1_carne.setText("");
        textField1_nombre.setText("");
        textField1_correo.setText("");
        textField1_seccion.setText("");
        textField1_telegramid.setText("");
        textField1_activo.setText("");
    }

    //Metodo para rellenar el combobox =====
    public void llenarcombo (){
        comboBox1_seccion.addItem("");
        comboBox1_seccion.addItem("A");
        comboBox1_seccion.addItem("B");
    }


    public form_ejercicio_2() {

        // Inicialización de componentes
        form_ejercicio_2 = new JPanel();
        lb_idusuario = new JLabel("ID Usuario:");
        textField1_idusuario = new JTextField(20);

        lb_carbe = new JLabel("Carne:");
        textField1_carne = new JTextField(20);

        lb_nombre = new JLabel("Nombre:");
        textField1_nombre = new JTextField(20);

        lb_correo = new JLabel("Correo:");
        textField1_correo = new JTextField(20);

        lb_seccion = new JLabel("Sección:");
        comboBox1_seccion = new JComboBox<>();
        textField1_seccion = new JTextField(20);

        lb_telegramid = new JLabel("Telegram ID:");
        textField1_telegramid = new JTextField(20);

        lb_activo = new JLabel("Activo:");
        textField1_activo = new JTextField(20);

        button1_Crear = new JButton("Crear");
        button1_buscar = new JButton("Buscar");
        button1_actualizar = new JButton("Actualizar");
        button1_eliminar = new JButton("Eliminar");

        // Configuración del layout del panel
        form_ejercicio_2.setLayout(new GridLayout(8, 2, 5, 5));
        form_ejercicio_2.add(lb_idusuario);
        form_ejercicio_2.add(textField1_idusuario);
        form_ejercicio_2.add(lb_carbe);
        form_ejercicio_2.add(textField1_carne);
        form_ejercicio_2.add(lb_nombre);
        form_ejercicio_2.add(textField1_nombre);
        form_ejercicio_2.add(lb_correo);
        form_ejercicio_2.add(textField1_correo);
        form_ejercicio_2.add(lb_seccion);
        form_ejercicio_2.add(comboBox1_seccion);
        form_ejercicio_2.add(lb_telegramid);
        form_ejercicio_2.add(textField1_telegramid);
        form_ejercicio_2.add(lb_activo);
        form_ejercicio_2.add(textField1_activo);

        // Configuración del panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(button1_Crear);
        buttonPanel.add(button1_buscar);
        buttonPanel.add(button1_actualizar);
        buttonPanel.add(button1_eliminar);

        // Configuración del JFrame
        setTitle("Formulario Ejercicio 2");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(form_ejercicio_2, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);

        llenarcombo();

        comboBox1_seccion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eecombo = comboBox1_seccion.getSelectedItem().toString();
                textField1_seccion.setText(eecombo);
            }
        });

        //AREA DE BOTONES =============================================================

        //Boton CREAR ================================================================================================================
        button1_Crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsuarioModel usuario = new UsuarioModel();
                if(textField1_nombre.getText().isEmpty() || textField1_carne.getText().isEmpty() || textField1_telegramid.getText().isEmpty() || textField1_activo.getText().isEmpty() || textField1_seccion.getText().isEmpty() || textField1_correo.getText().isEmpty()){
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
