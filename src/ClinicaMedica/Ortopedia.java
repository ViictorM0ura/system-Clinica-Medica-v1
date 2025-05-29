package ClinicaMedica;

import java.util.ArrayList;
import java.util.List;

public class Ortopedia extends Especialidade {
    // declaração de uma constante para garantir que não sofra alteração desde
    // o início até o final da execução do código //
    private static final ArrayList<Medico> medicos = new ArrayList<>();

    public Ortopedia() {
        super("Ortopedia");
    }

    // Bloco estático que vai ser inicializado toda vez que o código for executado,
    // ele vai garantir que essa especialidade tenha sempre
    // os mesmos médicos por padrão
    static {
        medicos.add(new Medico("Dr. Enzo", new Ortopedia()));
        medicos.add(new Medico("Dr. Fabricio", new Ortopedia()));
        medicos.add(new Medico("Dr. João Victor", new Ortopedia()));

    }

    public static List<Medico> getMedicos() {
        return medicos;
    }

    public static void nome_Medicos() {
        System.out.println(" ---- Médicos Associados ----");
        for (int i = 0; i < medicos.size(); i++) {
            System.out.println((i + 1) + ". " + medicos.get(i).getNome());
        }
    }

    @Override
    public double calcularValor_Consulta() {
        return 660.00;
    }

}
