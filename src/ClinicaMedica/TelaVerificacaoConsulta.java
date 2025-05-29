package ClinicaMedica;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaVerificacaoConsulta {
    private Agenda agenda;

    public TelaVerificacaoConsulta(Agenda agenda) {
        this.agenda = agenda;
    }

    public void exibir() {
        JFrame frame = new JFrame("Verificar Consulta");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);

        JLabel lblId = new JLabel("Digite o ID da Consulta:");
        JTextField txtId = new JTextField(10);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(_ -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                Consulta consulta = agenda.buscarConsultaPorId(id);

                if (consulta != null) {
                    JOptionPane.showMessageDialog(frame, consulta.toString());
                } else {
                    JOptionPane.showMessageDialog(frame, "Consulta com ID " + id + " não encontrada.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "ID inválido. Por favor, insira um número.");
            }
        });

        JPanel panel = new JPanel();
        panel.add(lblId);
        panel.add(txtId);
        panel.add(btnBuscar);

        frame.add(panel);
        frame.setVisible(true);
    }
}
