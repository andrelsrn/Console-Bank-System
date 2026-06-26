package conta_bancaria.controller;

import conta_bancaria.model.Conta;
import conta_bancaria.repository.ContaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContaController implements ContaRepository {

    private List<Conta> listaContas = new ArrayList<Conta>();
    int numero = 0;


    @Override
    public void cadastrar(Conta conta) {
        listaContas.add(conta);
        System.out.printf("Conta número %d cadastrada com sucesso!%n", conta.getNumero());

    }

    @Override
    public void procurarPorNumero(int numero) {
        Optional<Conta> conta = buscarNaCollection(numero);

        if (conta.isPresent())
            conta.get().visualizar();
        else
            System.out.printf("\nA conta número %d não foi encontrada!", numero);

    }


    @Override
    public void atualizar(Conta conta) {
        Optional<Conta> buscaConta = buscarNaCollection(conta.getNumero());

        if (buscaConta.isPresent()) {
            listaContas.set(listaContas.indexOf(buscaConta.get()), conta);
            System.out.printf("\nA conta número %d foi atualizada com sucesso!", conta.getNumero());

        }else
            System.out.printf("\nA conta número %d não foi encontrada!", conta.getNumero());
    }

    @Override
    public void deletar(int numero) {
        Optional<Conta> conta = buscarNaCollection(numero);

        if (conta.isPresent()) {
            if (listaContas.remove(conta.get()))
                System.out.printf("\nA conta número %d foi excluída com sucesso!", numero);

        }else
            System.out.printf("\nA conta número %d não foi encontrada!", numero);
    }

    @Override
    public void sacar(int numero, float valor) {

    }

    @Override
    public void depositar(int numero, float valor) {

    }

    @Override
    public void transferir(int numeroOrigem, int numeroDestino, float valor) {

    }

    public int gerarNumero() {
        return ++numero;
    }

    @Override
    public void listarTodas() {
        for (var conta : listaContas) {
            conta.visualizar();
        }
    }

    public static void cadastrarConta() {

        System.out.println("Cadastro de Contas");
    }

    public Optional<Conta> buscarNaCollection(int numero){
        for (var conta : listaContas) {
            if (conta.getNumero() == numero)
                return Optional.of(conta);
        }
        return Optional.empty();
    }
}
