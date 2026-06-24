package conta_bancaria;

import conta_bancaria.controller.ContaController;
import conta_bancaria.model.Conta;
import conta_bancaria.model.ContaCorrente;
import conta_bancaria.model.ContaPoupanca;
import conta_bancaria.repository.ContaRepository;
import conta_bancaria.util.Cores;

import java.util.Scanner;

public class Menu {

    private static final Scanner sc = new Scanner(System.in);
    private static final ContaController contaController = new ContaController();

    public static void main(String[] args) {

        criarContasTeste();

        int opcao;

        while (true) {

            System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND
                    + "*****************************************************");
            System.out.println("                                                     ");
            System.out.println("                   BANCO PARA TODOS                  ");
            System.out.println("                                                     ");
            System.out.println("*****************************************************");
            System.out.println("                                                     ");
            System.out.println("            1 - Criar Conta                          ");
            System.out.println("            2 - Listar todas as Contas               ");
            System.out.println("            3 - Buscar Conta por Numero              ");
            System.out.println("            4 - Atualizar Dados da Conta             ");
            System.out.println("            5 - Apagar Conta                         ");
            System.out.println("            6 - Sacar                                ");
            System.out.println("            7 - Depositar                            ");
            System.out.println("            8 - Transferir valores entre Contas      ");
            System.out.println("            0 - Sair                                 ");
            System.out.println("                                                     ");
            System.out.println("*****************************************************");
            System.out.println("Entre com a opção desejada:                          ");
            System.out.println("                                                     " + Cores.TEXT_RESET);

            opcao = sc.nextInt();

            if (opcao == 0) {
                System.out.println(Cores.TEXT_WHITE_BOLD + "\nBanco para Todos - Aqui seu dinheiro rende mais!");
                sobre();
                sc.close();
                System.exit(0);
            }

            switch (opcao) {
                case 1:
                    System.out.println(Cores.TEXT_WHITE + "Criar Conta\n\n");
                    cadastrarConta();
                    keyPress();
                    break;
                case 2:
                    System.out.println(Cores.TEXT_WHITE + "Listar todas as Contas\n\n");
                    listarContas();
                    keyPress();
                    break;
                case 3:
                    System.out.println(Cores.TEXT_WHITE + "Consultar dados da Conta - por número\n\n");
                    keyPress();
                    break;
                case 4:
                    System.out.println(Cores.TEXT_WHITE + "Atualizar dados da Conta\n\n");
                    keyPress();
                    break;
                case 5:
                    System.out.println(Cores.TEXT_WHITE + "Apagar a Conta\n\n");
                    keyPress();
                    break;
                case 6:
                    System.out.println(Cores.TEXT_WHITE + "Saque\n\n");
                    keyPress();
                    break;
                case 7:
                    System.out.println(Cores.TEXT_WHITE + "Depósito\n\n");
                    keyPress();
                    break;
                case 8:
                    System.out.println(Cores.TEXT_WHITE + "Transferência entre Contas\n\n");
                    keyPress();
                    break;
                default:
                    System.out.println(Cores.TEXT_RED_BOLD + "\nOpção Inválida!\n" + Cores.TEXT_RESET);
                    keyPress();
                    break;
            }
        }
    }

    public static void sobre() {
        System.out.println("\n*********************************************************");
        System.out.println("Projeto Desenvolvido por: André Nunes ");
        System.out.println("Generation Brasil - Turma 85 Java ");
        System.out.println("github.com/andrel.srn");
        System.out.println("*********************************************************");
    }
    public static void keyPress() {
        System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para continuar...");
        sc.nextLine();
    }

    private static void listarContas(){
        contaController.listarTodas();
    }

    private static void cadastrarConta(){
        System.out.print("Digite o número da Agência: ");
        int agencia = sc.nextInt();

        System.out.print("Digite o nome do titular: ");
        sc.skip("\\R");
        String titular = sc.nextLine();

        System.out.print("Digite o tipo de conta (1- CC | 2- CP): ");
        int tipo = sc.nextInt();

        System.out.print("Digite o Saldo da Conta: ");
        float saldo = sc.nextFloat();

        switch(tipo){
            case 1 -> {
                System.out.print("Digite o limite da conta ");
                float limite = sc.nextFloat();
                contaController.cadastrar(new ContaCorrente(contaController.gerarNumero(), agencia, tipo, titular, saldo, limite));
            }

            case 2 -> {
                System.out.print("Digite o aniversário da conta: ");
                int aniversario = sc.nextInt();
                contaController.cadastrar(new ContaPoupanca(contaController.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
            }

            default -> System.out.println(Cores.TEXT_RED_BOLD + "Tipo de conta inválido!" + Cores.TEXT_RESET);
        }
    }

    private static void criarContasTeste(){
        contaController.cadastrar(
                new ContaCorrente(contaController.gerarNumero(), 123, 1, "André Nunes", 1000.00f, 100.00f));
        contaController.cadastrar(
                new ContaPoupanca(contaController.gerarNumero(), 456, 2, "Maria Silva", 1000.00f, 15));
        contaController.cadastrar(
                new ContaCorrente(contaController.gerarNumero(), 789, 2, "José Santos", 1000.00f, 500.00f));
        contaController.cadastrar(
                new ContaPoupanca(contaController.gerarNumero(), 123, 2, "Ana Souza", 1000.00f, 10));
    }
}