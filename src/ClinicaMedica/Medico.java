package ClinicaMedica;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class Medico {

    private String nome;
    private Especialidade especialidade;
    private List<String> horariosDisponiveis;
    private List<String> horariosReservados;

    public Medico(String nome, Especialidade especialidade) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.horariosDisponiveis = new ArrayList<>();
        this.horariosReservados = new ArrayList<>();
        predefinirHorarios();
    }

    private void predefinirHorarios() {
        horariosDisponiveis.add("07:30");
        horariosDisponiveis.add("08:00");
        horariosDisponiveis.add("08:30");
        horariosDisponiveis.add("09:00");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public List<String> getHorariosDisponiveis() {
        return horariosDisponiveis;
    }

    public List<String> getHorariosReservados() {
        return horariosReservados;
    }

    public void exibirHorarios() {
        System.out.println("Horários disponíveis para " + nome + ":");
        for (int i = 0; i < horariosDisponiveis.size(); i++) {
            String horario = horariosDisponiveis.get(i);
            System.out.println("- " + horario);
        }
    }

    public boolean reservarHorario(String horario) {
        if (horariosReservados.contains(horario)) {
            System.out.println("Erro: O horário " + horario + " já foi reservado para o médico " + nome + ".");
            return false;
        }
        if (horariosDisponiveis.contains(horario)) {
            horariosDisponiveis.remove(horario);
            horariosReservados.add(horario);
            System.out.println("Horário " + horario + " reservado com sucesso para o médico " + nome + ".");
            return true;
        }
        System.out.println("Erro: O horário " + horario + " não está disponível.");
        return false;
    }

    public void adicionarHorario(String horario) {
        if (!horariosDisponiveis.contains(horario) && !horariosReservados.contains(horario)) {
            horariosDisponiveis.add(horario);
            Collections.sort(horariosDisponiveis, new Comparator<String>() {
                @Override
                public int compare(String h1, String h2) {
                    return h1.compareTo(h2);
                }
            });
            System.out.println("Horário " + horario + " adicionado com sucesso e lista reordenada.");
        } else {
            System.out.println("Erro: O horário " + horario + " já está disponível ou reservado.");
        }
    }

    public void removerHorario(String horario) {
        if (horariosDisponiveis.contains(horario)) {
            horariosDisponiveis.remove(horario);
            System.out.println("Horário " + horario + " removido com sucesso.");
        } else {
            System.out.println("Erro: O horário " + horario + " não está disponível.");
        }
    }
}
