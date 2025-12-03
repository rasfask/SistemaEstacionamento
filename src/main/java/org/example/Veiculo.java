package org.example;

import java.time.LocalDateTime;

// PESSOA 2: Começa explicando esta classe.
// "A classe Veiculo é o nosso molde. Ela guarda os dados de cada carro ou moto."
public class Veiculo {

    // PESSOA 2: "Estes são os atributos encapsulados (private)."
    private String placa;
    private String tipo; // "CARRO" ou "MOTO"
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;

    // PESSOA 2: "O construtor obriga a ter placa e hora de entrada ao criar."
    public Veiculo(String placa, String tipo, LocalDateTime horaEntrada) {
        this.placa = placa;
        this.tipo = tipo;
        this.horaEntrada = horaEntrada;
        this.horaSaida = null; // "Começa nulo porque o carro ainda está no pátio."
    }

    //  PESSOA 2: Getters para ler os dados
    public String getPlaca() {
        return placa;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }

    //  PESSOA 2: O Setter apenas para a hora de saída, que muda depois.
    public void setHoraSaida(LocalDateTime horaSaida) {
        this.horaSaida = horaSaida;
    }
}