package umg.progra2.Formularios.Ejercicio_3;

import umg.progra2.DataBase.Model.ChampionModel;
import umg.progra2.DataBase.Service.ChampionService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class form_champion {
    private JPanel form_base_champion;
    private JLabel lb_id_equipo;
    private JLabel lb_nombre;
    private JLabel lb_pais;
    private JLabel lb_ciudad;
    private JLabel lb_estadio;
    private JLabel lb_fundacion;
    private JLabel lb_entrenador;
    private JLabel lb_web_oficial;
    private JLabel lb_facebook;
    private JLabel lb_twitter;
    private JLabel lb_instagram;
    private JLabel lb_patrocinador_principal;
    private JLabel lb_creado_en;
    private JTextField textField1_id_equipo;
    private JTextField textField1_nombre;
    private JTextField textField1_pais;
    private JTextField textField1_ciudad;
    private JTextField textField1_estadio;
    private JTextField textField1_fundacion;
    private JTextField textField1_entrenador;
    private JTextField textField1_web;
    private JTextField textField1_facebook;
    private JTextField textField1_twitter;
    private JTextField textField1_instagram;
    private JTextField textField1_patrocinador;
    private JTextField textField1_creado_en;
    private JButton button1_crear;
    private JButton button1_buscar;
    private JButton button1_actualizar;
    private JButton button1_eliminar;

    //Main
    public static void main(String[] args) {
        JFrame frame = new JFrame("form_champion");
        frame.setContentPane(new form_champion().form_base_champion);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    //AREA DE METODOS ================================================================================================================
    private void mmLimpiar() {
        JTextField[] camposTexto = {
                textField1_id_equipo,
                textField1_nombre,
                textField1_pais,
                textField1_ciudad,
                textField1_estadio,
                textField1_fundacion,
                textField1_entrenador,
                textField1_web,
                textField1_facebook,
                textField1_twitter,
                textField1_instagram,
                textField1_patrocinador,
                textField1_creado_en
        };

        for (JTextField campo : camposTexto) {
            campo.setText("");  // Limpia cada campo de texto
        }
    }


    public form_champion() {

        //Boton CREAR ================================================================================================================
        button1_crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChampionModel cham = new ChampionModel();

                if(textField1_id_equipo.getText().isEmpty() || textField1_nombre.getText().isEmpty() || textField1_pais.getText().isEmpty() || textField1_ciudad.getText().isEmpty() || textField1_estadio.getText().isEmpty() || textField1_fundacion.getText().isEmpty() || textField1_entrenador.getText().isEmpty() || textField1_web.getText().isEmpty() || textField1_facebook.getText().isEmpty() || textField1_twitter.getText().isEmpty() || textField1_instagram.getText().isEmpty() || textField1_patrocinador.getText().isEmpty() || textField1_creado_en.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "No se puede dejar un campo vacio.");
                    return;
                }

                String eeano = textField1_fundacion.getText();
                if (!eeano.matches("\\d+")) {
                    JOptionPane.showMessageDialog(null, "El año debe contener solo números");
                    return;
                }else{
                    int eeano2 = Integer.parseInt(eeano);
                    if(eeano2<1901 || eeano2>2155){
                        JOptionPane.showMessageDialog(null, "El año debe estar entre 1901 y 2155");
                        return;
                    }
                }

                cham.setNombre(textField1_nombre.getText());
                cham.setPais(textField1_pais.getText());
                cham.setCiudad(textField1_ciudad.getText());
                cham.setEstadio(textField1_estadio.getText());
                cham.setFundacion(Integer.parseInt(textField1_fundacion.getText()));
                cham.setEntrenador(textField1_entrenador.getText());
                cham.setWebOficial(textField1_web.getText());
                cham.setFacebook(textField1_facebook.getText());
                cham.setTwitter(textField1_twitter.getText());
                cham.setInstagram(textField1_instagram.getText());
                cham.setPatrocinadorPrincipal(textField1_patrocinador.getText());

                try{
                    new ChampionService().crearEquipo(cham);
                    JOptionPane.showMessageDialog(null, "Equipo creado exitosamente");
                    mmLimpiar();
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Error al crear el Equipo: " + ex.getMessage());
                }
            }
        });

        //Boton BUSCAR  ================================================================================================================
        button1_buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validar que el campo ID del equipo no esté vacío
                int idEquipo = textField1_id_equipo.getText().isEmpty() ? 0 : Integer.parseInt(textField1_id_equipo.getText());

                if (idEquipo == 0) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un ID de equipo válido.");
                    return;
                }

                try {
                    // Llamar al servicio para obtener los datos del equipo por su ID
                    ChampionModel equipoEncontrado = new ChampionService().buscarEquipoPorId(idEquipo);

                    // Verificar si se encontró el equipo
                    if (equipoEncontrado != null) {
                        // Llenar los campos de texto con los datos encontrados
                        textField1_nombre.setText(equipoEncontrado.getNombre());
                        textField1_pais.setText(equipoEncontrado.getPais());
                        textField1_ciudad.setText(equipoEncontrado.getCiudad());
                        textField1_estadio.setText(equipoEncontrado.getEstadio());
                        textField1_fundacion.setText(Integer.toString(equipoEncontrado.getFundacion()));
                        textField1_entrenador.setText(equipoEncontrado.getEntrenador());
                        textField1_web.setText(equipoEncontrado.getWebOficial());
                        textField1_facebook.setText(equipoEncontrado.getFacebook());
                        textField1_twitter.setText(equipoEncontrado.getTwitter());
                        textField1_instagram.setText(equipoEncontrado.getInstagram());
                        textField1_patrocinador.setText(equipoEncontrado.getPatrocinadorPrincipal());
                        textField1_creado_en.setText(equipoEncontrado.getCreadoEn().toString()); // Suponiendo que es de tipo `Date`
                    } else {
                        // Si no se encuentra el equipo, mostrar un mensaje
                        JOptionPane.showMessageDialog(null, "No se encontró un equipo con el ID especificado.");
                    }
                } catch (Exception ex) {
                    // Mostrar mensaje en caso de error de base de datos
                    JOptionPane.showMessageDialog(null, "Error al consultar la base de datos: " + ex.getMessage());
                }
            }
        });

        button1_actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Corroborar que la casilla del ID no esté vacía
                if (textField1_id_equipo.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Lo siento; La casilla del ID no puede estar vacía");
                    return;
                }

                try {
                    int idEquipo = Integer.parseInt(textField1_id_equipo.getText());
                    ChampionModel equipoActual = new ChampionService().buscarEquipoPorId(idEquipo);

                    if (equipoActual == null) {
                        JOptionPane.showMessageDialog(null, "No se encontró el equipo.");
                        return;
                    }

                    // Validar que el año de fundación sea válido
                    String nuevoAnoFundacion = textField1_fundacion.getText();
                    if (!nuevoAnoFundacion.matches("\\d+")) {
                        JOptionPane.showMessageDialog(null, "El año debe contener solo números.");
                        return;
                    } else {
                        int anoFundacion = Integer.parseInt(nuevoAnoFundacion);
                        if (anoFundacion < 1901 || anoFundacion > 2155) {
                            JOptionPane.showMessageDialog(null, "El año debe estar entre 1901 y 2155.");
                            return;
                        }
                    }

                    // Actualizar los datos del equipo
                    equipoActual.setNombre(textField1_nombre.getText());
                    equipoActual.setPais(textField1_pais.getText());
                    equipoActual.setCiudad(textField1_ciudad.getText());
                    equipoActual.setEstadio(textField1_estadio.getText());
                    equipoActual.setFundacion(Integer.parseInt(textField1_fundacion.getText()));
                    equipoActual.setEntrenador(textField1_entrenador.getText());
                    equipoActual.setWebOficial(textField1_web.getText());
                    equipoActual.setFacebook(textField1_facebook.getText());
                    equipoActual.setTwitter(textField1_twitter.getText());
                    equipoActual.setInstagram(textField1_instagram.getText());
                    equipoActual.setPatrocinadorPrincipal(textField1_patrocinador.getText());

                    try {
                        new ChampionService().actualizarEquipo(equipoActual);
                        JOptionPane.showMessageDialog(null, "Equipo actualizado exitosamente");
                        mmLimpiar();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error al actualizar el equipo: " + ex.getMessage());
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID de equipo inválido. Debe ser un número entero.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al obtener el equipo: " + ex.getMessage());
                }

            }
        });

        button1_eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (textField1_id_equipo.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Lo siento; La casilla del ID no puede estar vacía");
                    return;
                }

                int idEquipo = Integer.parseInt(textField1_id_equipo.getText());

                int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar el equipo?");
                if (confirmacion == JOptionPane.YES_OPTION) {
                    try {
                        new ChampionService().borrarEquipo(idEquipo);
                        mmLimpiar();
                        JOptionPane.showMessageDialog(null, "Equipo eliminado correctamente");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error en la base de datos: " + ex.getMessage());
                    }
                }

            }
        });
    }
}
