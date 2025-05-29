package ClinicaMedica;

import java.util.ArrayList;

public class Agenda {

    private ArrayList<Consulta> consultas = new ArrayList<>();

    public void adicionarConsulta(Consulta consulta) {
        consultas.add(consulta);
    }

    public void listarConsultas() {
        System.out.println("\n--- Consultas Agendadas ---");
        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta agendada.");
            return;
        }
        for (int i = 0; i < consultas.size(); i++) {
            System.out.println("===================================");
            consultas.get(i).exibirConsulta();
            System.out.println("===================================");
        }
    }

    public Consulta buscarConsultaPorId(int id) {
        for (int i = 0; i < consultas.size(); i++) {
            if (consultas.get(i).getId() == id) {
                return consultas.get(i);
            }
        }
        return null;
    }

    public void verificarConsulta(int id) {
        Consulta consulta = buscarConsultaPorId(id);
        if (consulta != null) {
            consulta.exibirConsulta();
        } else {
            System.out.println("Consulta com ID " + id + " não encontrada.");
        }
    }

    public void excluirConsulta(int id) {
        Consulta consulta = buscarConsultaPorId(id);
        if (consulta != null) {
            Medico medico = consulta.getMedico();
            String horario = consulta.getDataHora();
            medico.removerHorario(horario);
            medico.adicionarHorario(horario);
            consultas.remove(consulta);
            System.out.println("Consulta com ID " + id + " excluída com sucesso!");
        } else {
            System.out.println("Consulta com ID " + id + " não encontrada.");
        }
    }

    public ArrayList<Consulta> getConsultas() {
        return consultas;
    }
}
