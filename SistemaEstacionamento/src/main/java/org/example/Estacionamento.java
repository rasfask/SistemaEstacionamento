package org.example;

import java.util.ArrayList;

//  PESSOA 3: Assume a explicação aqui.
// "A classe Estacionamento é quem gerencia a lista e o dinheiro."
public class Estacionamento {
    private int capacidade;

    //  PESSOA 3: "Usamos ArrayList porque a lista cresce e diminui dinamicamente."
    private ArrayList<Veiculo> veiculos;
    private double totalArrecadado;

    public Estacionamento(int capacidade) {
        this.capacidade = capacidade;
        this.veiculos = new ArrayList<>();
        this.totalArrecadado = 0.0;
    }

    //  PESSOA 3: "Métodos de controle de vaga..."
    public boolean estaCheio() {
        return veiculos.size() >= capacidade;
    }

    public boolean adicionarVeiculo(Veiculo v) {
        if (estaCheio()) {
            return false;
        }
        veiculos.add(v); // "Adiciona na lista."
        return true;
    }

    public boolean removerVeiculo(Veiculo v) {
        return veiculos.remove(v); // "Remove da lista."
    }

    // PESSOA 3: "Este método busca o carro na lista pela placa."
    public Veiculo buscarPorPlaca(String placa) {
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                return v;
            }
        }
        return null;
    }

    public void adicionarArrecadacao(double valor) {
        this.totalArrecadado += valor;
    }

    //  PESSOA 3: "Getters usados para gerar os relatórios."
    public int getQuantidadeOcupada() {
        return veiculos.size();
    }

    public int getQuantidadeDisponivel() {
        return capacidade - veiculos.size();
    }

    public double getTotalArrecadado() {
        return totalArrecadado;
    }

    public ArrayList<Veiculo> getVeiculos() {
        return veiculos;
    }

    public int getCapacidade() {
        return capacidade;
    }
}