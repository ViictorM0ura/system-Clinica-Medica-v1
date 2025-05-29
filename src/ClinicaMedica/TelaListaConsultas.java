package ClinicaMedica;

import javax.swing.*;
import java.util.List;

public class TelaListaConsultas {
    private Agenda agenda;

    public TelaListaConsultas(Agenda agenda) {
        this.agenda = agenda;
    }

    public void exibir() {
        JFrame frame = new JFrame("Lista de Consultas");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);

        JTextArea textArea = new JTextArea(20, 40);
        textArea.setEditable(false);

        List<Consulta> consultas = agenda.getConsultas();
        if (consultas.isEmpty()) {
            textArea.setText("Nenhuma consulta cadastrada.");
        } else {
            StringBuilder builder = new StringBuilder();
            for (Consulta consulta : consultas) {
                builder.append("ID: ").append(consulta.getId()).append("\n")
                        .append("Paciente: ").append(consulta.getPaciente().getNome()).append("\n")
                        .append("Médico: ").append(consulta.getMedico().getNome()).append("\n")
                        .append("Horário: ").append(consulta.getDataHora()).append("\n")
                        .append("-------------------------------\n");
            }
            textArea.setText(builder.toString());
        }

        frame.add(new JScrollPane(textArea));
        frame.setVisible(true);
    }
}
