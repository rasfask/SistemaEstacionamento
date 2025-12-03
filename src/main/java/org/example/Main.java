package org.example;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // ==================================================================================
    // PESSOA 1: ABERTURA E MENU
    // Foco: Explicar o início do programa, o loop principal e a estrutura de decisão.
    // ==================================================================================

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Estacionamento estacionamento = new Estacionamento(10);
        int opcao;

        // PESSOA 1: "Iniciamos o sistema com um loop que mantém o menu ativo."
        do {
            mostrarMenu();
            opcao = lerInteiro(sc, "Escolha uma opção: ");

            // PESSOA 1: "O switch direciona cada opção para a pessoa responsável."
            switch (opcao) {
                case 1:
                    registrarEntrada(sc, estacionamento); // Explicação da Pessoa 1
                    break;
                case 2:
                    registrarSaida(sc, estacionamento); // Pessoa 4
                    break;
                case 3:
                    mostrarVagas(estacionamento); // Pessoa 3
                    break;
                case 4:
                    listarVeiculos(estacionamento); // Pessoa 3
                    break;
                case 5:
                    pesquisarVeiculo(sc, estacionamento); // Pessoa 3
                    break;
                case 6:
                    mostrarFaturamento(estacionamento); // Pessoa 3
                    break;
                case 0:
                    System.out.println("Encerrando o sistema. Obrigado!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
            System.out.println();
        } while (opcao != 0);

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("========== SISTEMA DE ESTACIONAMENTO ==========");
        System.out.println("1. Registrar Entrada");
        System.out.println("2. Registrar Saída");
        System.out.println("3. Ver Vagas Disponíveis");
        System.out.println("4. Listar Veículos Estacionados");
        System.out.println("5. Pesquisar Veículo por Placa");
        System.out.println("6. Relatório de Faturamento");
        System.out.println("0. Sair");
        System.out.println("===============================================");
    }

    // PESSOA 1: "Verifico se o estacionamento está cheio e registro o carro."
    private static void registrarEntrada(Scanner sc, Estacionamento est) {
        if (est.estaCheio()) {
            System.out.println("ERRO: O estacionamento está lotado!");
            return;
        }

        System.out.print("Digite a Placa: ");
        String placa = sc.next().toUpperCase();
        sc.nextLine(); // Limpa buffer

        if (est.buscarPorPlaca(placa) != null) {
            System.out.println("ERRO: Este veículo já está no pátio!");
            return;
        }

        // Chama métodos auxiliares da Pessoa 2
        String tipo = lerTipoVeiculo(sc);

        // Pega sempre a hora atual automaticamente
        LocalDateTime horaEntrada = LocalDateTime.now();

        Veiculo v = new Veiculo(placa, tipo, horaEntrada);
        est.adicionarVeiculo(v);
        System.out.println("✅ Entrada registrada! Hora: " + horaEntrada.toLocalTime());
    }

    // ==================================================================================
    // PESSOA 2: TRATAMENTO DE DADOS E HORÁRIOS
    // Foco: Explicar como o sistema lê e valida as informações (Tipo e Data/Hora).
    // ==================================================================================

    private static String lerTipoVeiculo(Scanner sc) {
        while (true) {
            System.out.println("Tipo do veículo? (1-Carro, 2-Moto): ");
            int op = lerInteiro(sc, "Opção: ");
            if (op == 1) return "CARRO";
            if (op == 2) return "MOTO";
            System.out.println("Opção inválida.");
        }
    }

    // PESSOA 2: "Método auxiliar para evitar que o programa de erro se digitar letra em vez de número."
    private static int lerInteiro(Scanner sc, String msg) {
        System.out.print(msg);
        while (!sc.hasNextInt()) {
            System.out.println("Por favor, digite um número válido.");
            sc.next();
            System.out.print(msg);
        }
        int num = sc.nextInt();
        sc.nextLine();
        return num;
    }

    // ==================================================================================
    // PESSOA 3: RELATÓRIOS E CONSULTAS
    // Foco: Explicar como visualizamos os dados do Estacionamento.
    // ==================================================================================

    private static void mostrarVagas(Estacionamento est) {
        System.out.println("--- Status das Vagas ---");
        System.out.println("Livres: " + est.getQuantidadeDisponivel());
        System.out.println("Ocupadas: " + est.getQuantidadeOcupada());
    }

    private static void listarVeiculos(Estacionamento est) {
        ArrayList<Veiculo> lista = est.getVeiculos();
        if (lista.isEmpty()) {
            System.out.println("O pátio está vazio.");
        } else {
            System.out.println("--- Veículos Estacionados ---");
            for (Veiculo v : lista) {
                System.out.println("[" + v.getTipo() + "] " + v.getPlaca() + " - Chegou às: " + v.getHoraEntrada().toLocalTime());
            }
        }
    }

    private static void pesquisarVeiculo(Scanner sc, Estacionamento est) {
        System.out.print("Digite a Placa para pesquisar: ");
        String placa = sc.next().toUpperCase();
        Veiculo v = est.buscarPorPlaca(placa);
        if (v != null) {
            System.out.println("Veículo Encontrado!");
            System.out.println("Placa: " + v.getPlaca() + " | Tipo: " + v.getTipo());
            System.out.println("Entrada: " + v.getHoraEntrada());
        } else {
            System.out.println("Veículo não está no estacionamento.");
        }
    }

    private static void mostrarFaturamento(Estacionamento est) {
        System.out.printf("💰 Total Arrecadado no dia: R$ %.2f%n", est.getTotalArrecadado());
    }

    // ==================================================================================
    // PESSOA 4 : FLUXO DE SAÍDA E FINANCEIRO
    // Foco: Explicar a lógica de saída, cálculo de tempo e cobrança.
    // ==================================================================================

    private static void registrarSaida(Scanner sc, Estacionamento est) {
        System.out.print("Digite a Placa para saída: ");
        String placa = sc.next().toUpperCase();
        sc.nextLine();

        Veiculo v = est.buscarPorPlaca(placa);

        if (v == null) {
            System.out.println("ERRO: Veículo não encontrado no sistema.");
            return;
        }

        // Pega hora atual automaticamente
        LocalDateTime horaSaida = LocalDateTime.now();

        // Impede que a saída seja registrada antes da entrada.
        if (horaSaida.isBefore(v.getHoraEntrada())) {
            System.out.println("ERRO: A hora de saída não pode ser antes da entrada!");
            return;
        }

        v.setHoraSaida(horaSaida);

        // PESSOA 4: "Aqui calculo a diferença de tempo."
        long minutos = ChronoUnit.MINUTES.between(v.getHoraEntrada(), v.getHoraSaida());

        // PESSOA 4: "Arredondo para cima (teto)."
        double horasCobradas = Math.ceil(minutos / 60.0);
        if (horasCobradas <= 0) horasCobradas = 1;

        // PESSOA 4: "Calculo o valor a pagar."
        double valor = calcularValor(horasCobradas, v.getTipo());

        // PESSOA 4: "Atualizo o sistema."
        est.adicionarArrecadacao(valor);
        est.removerVeiculo(v);

        System.out.println("\n=== RECIBO DE SAÍDA ===");
        System.out.println("Veículo: " + v.getTipo() + " | Placa: " + v.getPlaca());
        System.out.println("Tempo Total: " + minutos + " minutos");
        System.out.println("Horas Cobradas: " + (int)horasCobradas + "h");
        System.out.printf("VALOR A PAGAR: R$ %.2f%n", valor);
        System.out.println("=======================");
    }

    // PESSOA 4: "Método exclusivo para a regra de negócio do preço."
    private static double calcularValor(double horas, String tipo) {
        double primeiraHora, horaAdicional;

        if (tipo.equals("CARRO")) {
            primeiraHora = 12.0;
            horaAdicional = 8.0;
        } else { // MOTO
            primeiraHora = 8.0;
            horaAdicional = 5.0;
        }

        if (horas <= 1) {
            return primeiraHora;
        } else {
            return primeiraHora + (horas - 1) * horaAdicional;
        }
    }
}