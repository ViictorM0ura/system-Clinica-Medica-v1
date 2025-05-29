package ClinicaMedica;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaAgendamentoConsultas {
    private Agenda agenda;

    public TelaAgendamentoConsultas(Agenda agenda) {
        this.agenda = agenda;
    }

    public void exibir() {
        JFrame frame = new JFrame("Agendamento de Consulta");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 500);

        JLabel lblNome = new JLabel("Nome do Paciente:");
        JTextField txtNome = new JTextField(20);

        JLabel lblCpf = new JLabel("CPF do Paciente:");
        JTextField txtCpf = new JTextField(15);

        JLabel lblEspecialidade = new JLabel("Escolha a Especialidade:");
        String[] especialidades = { "Cardiologia", "Psicologia", "Neurologia", "Nutrição", "Ortopedia" };
        JComboBox<String> cbEspecialidades = new JComboBox<>(especialidades);

        JLabel lblMedico = new JLabel("Escolha o Médico:");
        JComboBox<String> cbMedicos = new JComboBox<>();

        JLabel lblHorario = new JLabel("Escolha o Horário:");
        JComboBox<String> cbHorarios = new JComboBox<>();

        cbEspecialidades.addActionListener(_ -> {
            String especialidadeSelecionada = (String) cbEspecialidades.getSelectedItem();
            List<Medico> medicos = obterMedicosPorEspecialidade(especialidadeSelecionada);

            cbMedicos.removeAllItems();
            for (Medico medico : medicos) {
                cbMedicos.addItem(medico.getNome());
            }

            cbHorarios.removeAllItems();
        });

        cbMedicos.addActionListener(_ -> {
            String nomeMedico = (String) cbMedicos.getSelectedItem();
            String especialidadeSelecionada = (String) cbEspecialidades.getSelectedItem();
            List<Medico> medicos = obterMedicosPorEspecialidade(especialidadeSelecionada);

            Medico medicoSelecionado = medicos.stream()
                    .filter(medico -> medico.getNome().equals(nomeMedico))
                    .findFirst()
                    .orElse(null);

            cbHorarios.removeAllItems();
            if (medicoSelecionado != null) {
                for (String horario : medicoSelecionado.getHorariosDisponiveis()) {
                    cbHorarios.addItem(horario);
                }
            }
        });

        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.addActionListener(_ -> {
            String nome = txtNome.getText();
            String cpf = txtCpf.getText();
            String especialidadeSelecionada = (String) cbEspecialidades.getSelectedItem();
            String nomeMedico = (String) cbMedicos.getSelectedItem();
            String horarioSelecionado = (String) cbHorarios.getSelectedItem();

            if (nome.isEmpty() || cpf.isEmpty() || nomeMedico == null || horarioSelecionado == null) {
                JOptionPane.showMessageDialog(frame, "Preencha todos os campos antes de confirmar.");
                return;
            }

            if (!cpf.matches("\\d{3}")) {
                JOptionPane.showMessageDialog(frame, "CPF inválido. Por favor, insira apenas números e 3 dígitos.");
                return;
            }

            List<Medico> medicos = obterMedicosPorEspecialidade(especialidadeSelecionada);
            Medico medicoSelecionado = medicos.stream()
                    .filter(medico -> medico.getNome().equals(nomeMedico))
                    .findFirst()
                    .orElse(null);

            if (medicoSelecionado != null) {

                Paciente paciente = new Paciente(nome, cpf);
                Consulta consulta = new Consulta(paciente, medicoSelecionado, horarioSelecionado);

                agenda.adicionarConsulta(consulta);

                medicoSelecionado.removerHorario(horarioSelecionado);

                JOptionPane.showMessageDialog(frame, "Consulta agendada com sucesso!");
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Erro ao selecionar o médico.");
            }
        });

        JPanel panel = new JPanel(new GridLayout(8, 2));
        panel.add(lblNome);
        panel.add(txtNome);
        panel.add(lblCpf);
        panel.add(txtCpf);
        panel.add(lblEspecialidade);
        panel.add(cbEspecialidades);
        panel.add(lblMedico);
        panel.add(cbMedicos);
        panel.add(lblHorario);
        panel.add(cbHorarios);
        panel.add(new JLabel());
        panel.add(btnConfirmar);

        frame.add(panel);
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
