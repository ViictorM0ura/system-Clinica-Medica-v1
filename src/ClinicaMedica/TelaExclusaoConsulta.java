package ClinicaMedica;

import javax.swing.*;

public class TelaExclusaoConsulta {
    private Agenda agenda;

    public TelaExclusaoConsulta(Agenda agenda) {
        this.agenda = agenda;
    }

    public void exibir() {
        JFrame frame = new JFrame("Excluir Consulta");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);

        JLabel lblId = new JLabel("Digite o ID da Consulta:");
        JTextField txtId = new JTextField(10);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(_ -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                Consulta consulta = agenda.buscarConsultaPorId(id);

                if (consulta == null) {

                    JOptionPane.showMessageDialog(frame, "Consulta com ID " + id + " não encontrada.");
                    return;
                }

                agenda.excluirConsulta(id);
                JOptionPane.showMessageDialog(frame, "Consulta excluída com sucesso! Horário " +
                        consulta.getDataHora() + " liberado para novos agendamentos.");
            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(frame, "Por favor, insira um ID válido.");
            }
        });

        JPanel panel = new JPanel();
        panel.add(lblId);
        panel.add(txtId);
        panel.add(btnExcluir);

        frame.add(panel);
        frame.setVisible(true);
    }
}
