package ClinicaMedica;

import javax.swing.*;
import java.util.List;

public class TelaListaEspecialidadesMedicos {
    public void exibir() {
        JFrame frame = new JFrame("Lista de Especialidades e Médicos");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);

        String[] especialidades = { "Cardiologia", "Psicologia", "Neurologia", "Nutrição", "Ortopedia" };
        JComboBox<String> cbEspecialidades = new JComboBox<>(especialidades);

        JTextArea txtMedicos = new JTextArea(15, 40);
        txtMedicos.setEditable(false);

        JButton btnVerMedicos = new JButton("Ver Médicos");
        btnVerMedicos.addActionListener(_ -> {
            String especialidadeSelecionada = (String) cbEspecialidades.getSelectedItem();
            List<Medico> medicos = obterMedicosPorEspecialidade(especialidadeSelecionada);

            if (medicos.isEmpty()) {
                txtMedicos.setText("Nenhum médico disponível para essa especialidade.");
                return;
            }

            StringBuilder builder = new StringBuilder("Médicos de " + especialidadeSelecionada + ":\n");
            for (Medico medico : medicos) {
                builder.append("- ").append(medico.getNome()).append("\n");
                builder.append("  Horários disponíveis:\n");
                if (medico.getHorariosDisponiveis().isEmpty()) {
                    builder.append("    Nenhum horário disponível\n");
                } else {
                    for (String horario : medico.getHorariosDisponiveis()) {
                        builder.append("    - ").append(horario).append("\n");
                    }
                }
                builder.append("\n");
            }

            txtMedicos.setText(builder.toString());
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Selecione uma Especialidade:"));
        panel.add(cbEspecialidades);
        panel.add(btnVerMedicos);

        frame.add(panel, "North");
        frame.add(new JScrollPane(txtMedicos), "Center");
        frame.setVisible(true);
    }

    private List<Medico> obterMedicosPorEspecialidade(String especialidade) {
        switch (especialidade) {
            case "Cardiologia":
                return Cardiologia.getMedicos();
            case "Psicologia":
                return Psicologia.getMedicos();
            case "Neurologia":
                return Neurologia.getMedicos();
            case "Nutrição":
                return Nutricao.getMedicos();
            case "Ortopedia":
                return Ortopedia.getMedicos();
            default:
                return List.of();
        }
    }
}
