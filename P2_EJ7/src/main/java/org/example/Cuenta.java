package org.example;

public class Cuenta {

    private double saldo;
    private final double maximo;

    public Cuenta(double saldo, double maximo) {
        this.saldo = saldo;
        this.maximo = maximo;
    }

    public synchronized double getSaldo() {
        return saldo;
    }

    public synchronized void depositar(double valor) {
        if (valor <= 0) return;

        while (saldo + valor > maximo) {
            try {
                System.out.println(Thread.currentThread().getName() +
                        " quiere depositar pero la cuenta está llena. Esperando...");

                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        saldo += valor;
        System.out.println(Thread.currentThread().getName() +
                " deposita " + valor + " -> Saldo: " + saldo);

        notifyAll(); // Despierta a los hilos que esperan retirar
    }

    public synchronized void retirar(double valor) {
        if (valor <= 0) return;

        while (saldo < valor) {
            try {
                System.out.println(Thread.currentThread().getName() +
                        " quiere retirar pero no hay saldo. Esperando...");

                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        saldo -= valor;
        System.out.println(Thread.currentThread().getName() +
                " retira " + valor + " -> Saldo: " + saldo);

        notifyAll(); // Despierta a los hilos que esperan depositar
    }
}
