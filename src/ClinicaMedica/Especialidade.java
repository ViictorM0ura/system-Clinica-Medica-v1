package ClinicaMedica;

public abstract class Especialidade {
    protected String nome;
    
    public Especialidade(String nome) {
        this.nome = nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    // Método que exibe as especialidades disponíveis para escolha //
    public static void especialidades() {
        System.out.println("\n --- Especialidades ---");
        System.out.println("1. Cardiologia");
        System.out.println("2. Psicologia");
        System.out.println("3. Neurologia");
        System.out.println("4. Nutrição");
        System.out.println("5. Ortopedia");
    }

    // Método para calcular o valor da consulta //
    public abstract double calcularValor_Consulta();

}
