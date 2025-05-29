package ClinicaMedica;

public class Consulta {

    private static int contadorId = 1;
    private int id;
    private Paciente paciente;
    private Medico medico;
    private String dataHora;

    public Consulta(Paciente paciente, Medico medicoEscolhido, String dataHora) {
        this.id = contadorId++;
        this.paciente = paciente;
        this.medico = medicoEscolhido;
        this.dataHora = dataHora;
    }

    public int getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public void exibirConsulta() {
        System.out.println("===================================");
        System.out.println("ID: " + id);
        System.out.println("Paciente: " + paciente.getNome() + " (" + paciente.getCpf() + ")");
        System.out.println("Médico: " + medico.getNome() + " - " + medico.getEspecialidade().getNome());
        System.out.println("Horário: " + dataHora);
        System.out.printf("Valor da consulta R$: %.2f%n", medico.getEspecialidade().calcularValor_Consulta());
        System.out.println("===================================");
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "Paciente: " + paciente.getNome() + " (" + paciente.getCpf() + ")\n" +
                "Médico: " + medico.getNome() + " - " + medico.getEspecialidade().getNome() + "\n" +
                "Horário: " + dataHora + "\n" +
                String.format("Valor da consulta R$: %.2f", medico.getEspecialidade().calcularValor_Consulta());
    }
}
