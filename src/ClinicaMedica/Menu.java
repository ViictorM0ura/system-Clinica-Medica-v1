package ClinicaMedica;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private Agenda agenda;
    private Scanner scanner;

    public Menu() {
        agenda = new Agenda();
        scanner = new Scanner(System.in);
    }

    // Método para exibir um menu de opções pro usuário //
    public void exibirMenu() {

        int opcao;
        // Inicio do laço do while //
        do {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Agendar nova consulta");
            System.out.println("2. Verificar uma consulta");
            System.out.println("3. Alterar informações de uma consulta");
            System.out.println("4. Excluir consulta");
            System.out.println("5. Exibit lista de consultas");
            System.out.println("6. Lista de Especialidades e médicos");
            System.out.println("0. Sair");

            opcao = scanner.nextInt();

            scanner.nextLine(); // Consumir a quebra de linha restante //

            switch (opcao) {
                case 1:
                    agendarConsulta();
                    break;
                case 2:
                    consultarConsulta();
                    break;
                case 3:
                    alterarConsulta();
                    break;
                case 4:
                    excluirConsulta();
                    break;
                case 5:
                    agenda.listarConsultas();
                    break;
                case 6:
                    listarEspecialidades_E_Medicos();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida, por favor tente outra opção.");
            }
        } while (true); // Fim do laço "do while" //

    }

    private void listarEspecialidades_E_Medicos() {
        int escolhaEspecialidade;
    
        do {
            // Exibe a lista de especialidades disponíveis
            Especialidade.especialidades();
    
            System.out.print("\nEscolha uma especialidade de 1 a 5 ou 0 para voltar: ");
            escolhaEspecialidade = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha restante
    
            if (escolhaEspecialidade == 0) {
                System.out.println("Voltando ao menu principal...");
                break;
            }
    
            List<Medico> medicosDisponiveis = null;
    
            // Determina a lista de médicos com base na especialidade escolhida
            switch (escolhaEspecialidade) {
                case 1:
                    medicosDisponiveis = Cardiologia.getMedicos();
                    break;
                case 2:
                    medicosDisponiveis = Psicologia.getMedicos();
                    break;
                case 3:
                    medicosDisponiveis = Neurologia.getMedicos();
                    break;
                case 4:
                    medicosDisponiveis = Nutricao.getMedicos();
                    break;
                case 5:
                    medicosDisponiveis = Ortopedia.getMedicos();
                    break;
                default:
                    System.out.println("Especialidade inválida! Por favor, tente novamente.");
                    continue;
            }
    
            if (medicosDisponiveis != null && !medicosDisponiveis.isEmpty()) {
                System.out.println("\nMédicos disponíveis na especialidade selecionada:");
                for (int i = 0; i < medicosDisponiveis.size(); i++) {
                    System.out.println((i + 1) + ". " + medicosDisponiveis.get(i).getNome());
                }
    
                System.out.print("\nSelecione um médico pelo número correspondente ou 0 para voltar: ");
                int escolhaMedico = scanner.nextInt();
                scanner.nextLine(); // Consumir quebra de linha restante
    
                if (escolhaMedico == 0) {
                    System.out.println("Voltando ao menu anterior...");
                    continue;
                }
    
                if (escolhaMedico >= 1 && escolhaMedico <= medicosDisponiveis.size()) {
                    Medico medico = medicosDisponiveis.get(escolhaMedico - 1);
    
                    // Exibe os horários disponíveis do médico
                    List<String> horariosDisponiveis = medico.getHorariosDisponiveis();
                    System.out.println("\nHorários disponíveis para " + medico.getNome() + ":");
                    if (horariosDisponiveis.isEmpty()) {
                        System.out.println("Nenhum horário disponível.");
                    } else {
                        for (String horario : horariosDisponiveis) {
                            System.out.println("- " + horario);
                        }
                    }
                } else {
                    System.out.println("Opção inválida! Tente novamente.");
                }
            } else {
                System.out.println("Nenhum médico disponível nessa especialidade.");
            }
        } while (true);
    }
    

    private void agendarConsulta() {
        // Solicitando os dados do paciente
        System.out.print("Nome do paciente: ");
        String pacienteNome = scanner.nextLine();
        System.out.print("CPF do paciente: ");
        String pacienteCpf = scanner.nextLine();
    
        int escolhaEspecialidade;
        List<Medico> medicosDisponiveis = null;
        do {
            Especialidade.especialidades(); // Exibindo as especialidades
    
            System.out.print("Escolha uma especialidade de 1 a 5: ");
            escolhaEspecialidade = Integer.parseInt(scanner.nextLine()); // Captura o número da especialidade
    
            switch (escolhaEspecialidade) {
                case 1:
                    medicosDisponiveis = Cardiologia.getMedicos();
                    break;
                case 2:
                    medicosDisponiveis = Psicologia.getMedicos();
                    break;
                case 3:
                    medicosDisponiveis = Neurologia.getMedicos();
                    break;
                case 4:
                    medicosDisponiveis = Nutricao.getMedicos();
                    break;
                case 5:
                    medicosDisponiveis = Ortopedia.getMedicos();
                    break;
                default:
                    System.out.println("Especialidade inválida! Por favor, tente novamente.");
            }
        } while (escolhaEspecialidade <= 0 || escolhaEspecialidade > 5);
    
        if (medicosDisponiveis != null) {
            System.out.println("\nMédicos disponíveis:");
            for (int i = 0; i < medicosDisponiveis.size(); i++) {
                System.out.println((i + 1) + ". " + medicosDisponiveis.get(i).getNome());
            }
    
            System.out.print("Selecione um médico pelo número correspondente: ");
            int medicoEscolhido = scanner.nextInt() - 1;
    
            if (medicoEscolhido >= 0 && medicoEscolhido < medicosDisponiveis.size()) {
                Medico medico = medicosDisponiveis.get(medicoEscolhido);
    
                // Exibe os horários disponíveis para o médico
                List<String> horariosDisponiveis = medico.getHorariosDisponiveis();
                if (horariosDisponiveis.isEmpty()) {
                    System.out.println("Nenhum horário disponível para este médico.");
                    return;
                }
    
                System.out.println("\nHorários disponíveis para " + medico.getNome() + ":");
                for (int i = 0; i < horariosDisponiveis.size(); i++) {
                    System.out.println((i + 1) + ". " + horariosDisponiveis.get(i));
                }
    
                System.out.print("Selecione um horário pelo número correspondente: ");
                int horarioEscolhido = scanner.nextInt() - 1;
    
                if (horarioEscolhido >= 0 && horarioEscolhido < horariosDisponiveis.size()) {
                    String horario = horariosDisponiveis.get(horarioEscolhido);
    
                    // Criar consulta com o horário escolhido
                    Paciente paciente = new Paciente(pacienteNome, pacienteCpf);
                    Consulta consulta = new Consulta(paciente, medico, horario);
                    agenda.adicionarConsulta(consulta);
    
                    // Remove o horário escolhido da lista de horários disponíveis
                    medico.removerHorario(horario);
    
                    System.out.println("Consulta agendada com sucesso!");
                } else {
                    System.out.println("Escolha de horário inválida!");
                }
            } else {
                System.out.println("Escolha de médico inválida!");
            }
        }
    }
    
    

    private void alterarConsulta() {
        System.out.print("Digite o ID da consulta para alterar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir quebra de linha restante
    
        Consulta consulta = agenda.buscarConsultaPorId(id);
        if (consulta == null) {
            System.out.println("Consulta com ID " + id + " não encontrada.");
            return;
        }
    
        int opcaoAlterar;
        do {
            System.out.println("\nO que você deseja alterar?");
            System.out.println("1. Nome do paciente");
            System.out.println("2. CPF do paciente");
            System.out.println("3. Horário da consulta");
            System.out.println("0. Cancelar Alteração");
            System.out.print("Escolha uma opção: ");
    
            opcaoAlterar = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha restante
    
            switch (opcaoAlterar) {
                case 1:
                    // Alterar Nome do Paciente
                    System.out.print("Digite o novo nome do paciente: ");
                    String novoNome = scanner.nextLine();
                    consulta.getPaciente().setNome(novoNome);
                    System.out.println("Nome do paciente atualizado com sucesso!");
                    break;
    
                case 2:
                    // Alterar CPF do Paciente
                    System.out.print("Digite o novo CPF do paciente: ");
                    String novoCpf = scanner.nextLine();
                    consulta.getPaciente().setCpf(novoCpf);
                    System.out.println("CPF do paciente atualizado com sucesso!");
                    break;
    
                case 3:
                    // Alterar Horário da Consulta
                    alterarHorarioConsulta(consulta);
                    break;
    
                case 0:
                    System.out.println("Alteração cancelada.");
                    break;
    
                default:
                    System.out.println("Opção inválida! Por favor, escolha uma opção válida.");
            }
        } while (opcaoAlterar != 0);
    }

    private void alterarHorarioConsulta(Consulta consulta) {
        Medico medico = consulta.getMedico();
        List<String> horariosDisponiveis = medico.getHorariosDisponiveis();
    
        if (horariosDisponiveis.isEmpty()) {
            System.out.println("Nenhum horário disponível para este médico.");
            return;
        }
    
        System.out.println("\nHorários disponíveis para " + medico.getNome() + ":");
        for (int i = 0; i < horariosDisponiveis.size(); i++) {
            System.out.println((i + 1) + ". " + horariosDisponiveis.get(i));
        }
    
        System.out.print("Selecione um novo horário pelo número correspondente: ");
        int horarioEscolhido = scanner.nextInt() - 1;
        scanner.nextLine(); // Consumir quebra de linha restante
    
        if (horarioEscolhido >= 0 && horarioEscolhido < horariosDisponiveis.size()) {
            String novoHorario = horariosDisponiveis.get(horarioEscolhido);
    
            // Devolve o horário antigo ao pool de horários disponíveis
            String horarioAntigo = consulta.getDataHora();
            medico.adicionarHorario(horarioAntigo);
    
            // Atualiza a consulta com o novo horário
            consulta.setDataHora(novoHorario);
    
            // Remove o novo horário da lista de horários disponíveis
            medico.removerHorario(novoHorario);
    
            System.out.println("Horário da consulta alterado com sucesso!");
        } else {
            System.out.println("Escolha de horário inválida!");
        }
    }
    
    private void excluirConsulta() {
        System.out.print("Digite o ID da consulta para excluir: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir quebra de linha restante
    
        agenda.excluirConsulta(id); // Exclui a consulta pelo ID
    }

    private void consultarConsulta() {
        System.out.print("Digite o ID da consulta: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir quebra de linha restante
    
        agenda.verificarConsulta(id); // Verifica a consulta pelo ID
    }
    

}
