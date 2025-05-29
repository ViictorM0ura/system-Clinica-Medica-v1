package ClinicaMedica;

import javax.swing.*;

public class TelaMenuPrincipal {
    public TelaMenuPrincipal() {
        JFrame frame = new JFrame("Menu Principal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        Agenda agenda = new Agenda();

        JButton btnAgendar = new JButton("Agendar Consulta");
        btnAgendar.addActionListener(_ -> new TelaAgendamentoConsultas(agenda).exibir());

        JButton btnVerificar = new JButton("Verificar Consulta");
        btnVerificar.addActionListener(_ -> new TelaVerificacaoConsulta(agenda).exibir());

        JButton btnAlterar = new JButton("Alterar Consulta");
        btnAlterar.addActionListener(_ -> new TelaAlteracaoConsulta(agenda).exibir());

        JButton btnExcluir = new JButton("Excluir Consulta");
        btnExcluir.addActionListener(_ -> new TelaExclusaoConsulta(agenda).exibir());

        JButton btnListarConsultas = new JButton("Listar Consultas");
        btnListarConsultas.addActionListener(_ -> new TelaListaConsultas(agenda).exibir());

        JButton btnEspecialidades = new JButton("Lista de Especialidades e Médicos");
        btnEspecialidades.addActionListener(_ -> new TelaListaEspecialidadesMedicos().exibir());

        JPanel panel = new JPanel();
        panel.add(btnAgendar);
        panel.add(btnVerificar);
        panel.add(btnAlterar);
        panel.add(btnExcluir);
        panel.add(btnListarConsultas);
        panel.add(btnEspecialidades);

        frame.add(panel);
        frame.setVisible(true);
    }
}
