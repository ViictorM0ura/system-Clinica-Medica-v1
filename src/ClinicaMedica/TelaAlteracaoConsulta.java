package ClinicaMedica;

import javax.swing.*;
import java.util.List;

public class TelaAlteracaoConsulta {
    private Agenda agenda;

    public TelaAlteracaoConsulta(Agenda agenda) {
        this.agenda = agenda;
    }

    public void exibir() {
        JFrame frame = new JFrame("Alterar Consulta");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 300);

        JLabel lblId = new JLabel("Digite o ID da Consulta:");
        JTextField txtId = new JTextField(10);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(_ -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                Consulta consulta = agenda.buscarConsultaPorId(id);

                if (consulta == null) {
                    JOptionPane.showMessageDialog(frame, "Consulta com ID " + id + " não encontrada.");
                    return;
                }

                Object[] options = { "Horário", "Nome do Paciente", "CPF do Paciente" };
                String escolha = (String) JOptionPane.showInputDialog(
                        frame,
                        "O que você deseja alterar?",
                        "Alterar Consulta",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);

                if (escolha == null) {
                    JOptionPane.showMessageDialog(frame, "Operação cancelada.");
                    return;
                }

                switch (escolha) {
                    case "Horário":
                        alterarHorarioConsulta(frame, consulta);
                        break;
                    case "Nome do Paciente":
                        alterarNomePaciente(frame, consulta);
                        break;
                    case "CPF do Paciente":
                        alterarCpfPaciente(frame, consulta);
                        break;
                    default:
                        JOptionPane.showMessageDialog(frame, "Opção inválida.");
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

    private void alterarHorarioConsulta(JFrame frame, Consulta consulta) {
        Medico medico = consulta.getMedico();
        List<String> horariosDisponiveis = medico.getHorariosDisponiveis();

        if (horariosDisponiveis.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Nenhum horário disponível para este médico.");
            return;
        }

        String novoHorario = (String) JOptionPane.showInputDialog(
                frame,
                "Selecione um novo horário:",
                "Alterar Horário",
                JOptionPane.QUESTION_MESSAGE,
                null,
                horariosDisponiveis.toArray(),
                horariosDisponiveis.get(0));

        if (novoHorario != null) {

            String horarioAntigo = consulta.getDataHora();
            medico.adicionarHorario(horarioAntigo);
            consulta.setDataHora(novoHorario);
            medico.removerHorario(novoHorario);

            JOptionPane.showMessageDialog(frame, "Horário alterado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(frame, "Operação cancelada.");
        }
    }

    private void alterarNomePaciente(JFrame frame, Consulta consulta) {
        String novoNome = JOptionPane.showInputDialog(
                frame,
                "Digite o novo nome do paciente:",
                consulta.getPaciente().getNome());

        if (novoNome != null && !novoNome.trim().isEmpty()) {
            consulta.getPaciente().setNome(novoNome);
            JOptionPane.showMessageDialog(frame, "Nome do paciente alterado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(frame, "Operação cancelada ou nome inválido.");
        }
    }

    private void alterarCpfPaciente(JFrame frame, Consulta consulta) {
        String novoCpf = JOptionPane.showInputDialog(
                frame,
                "Digite o novo CPF do paciente:",
                consulta.getPaciente().getCpf());

        if (novoCpf != null && !novoCpf.trim().isEmpty()) {
            consulta.getPaciente().setCpf(novoCpf);
            JOptionPane.showMessageDialog(frame, "CPF do paciente alterado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(frame, "Operação cancelada ou CPF inválido.");
        }
    }
}
