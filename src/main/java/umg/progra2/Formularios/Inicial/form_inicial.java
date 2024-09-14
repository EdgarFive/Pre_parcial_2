package umg.progra2.Formularios.Inicial;

import umg.progra2.Formularios.Ejercicio_1.From_Pre_parcial_2;
import umg.progra2.Formularios.Ejercicio_2.form_ejercicio_2;
import umg.progra2.Formularios.Ejercicio_3.form_champion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class form_inicial extends JFrame {
    private JPanel form_inicial;
    private JLabel lb_titulo;
    private JButton button1_ejercicio1;
    private JButton button2_ejercicio_2;
    private JButton button3_ejercicio_3;

    public static void main(String[] args) {

        JFrame frame = new JFrame("form_inicial");
        frame.setContentPane(new form_inicial().form_inicial);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public form_inicial() {
        button1_ejercicio1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                From_Pre_parcial_2 form1 = new From_Pre_parcial_2();
                form1.setVisible(true);
                //dispose();
            }
        });

        button2_ejercicio_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                form_ejercicio_2 form2 = new form_ejercicio_2();
                form2.setVisible(true);
                //dispose();
            }
        });
        button3_ejercicio_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                form_champion form3 = new form_champion();
                form3.setVisible(true);
                //dispose();
            }
        });
    }
}
