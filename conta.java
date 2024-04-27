class Conta {
    private int saldo;

    public Conta(int saldoInicial) {
        this.saldo = saldoInicial;
    }

    public synchronized int getSaldo() {
        return saldo;
    }

    public synchronized void depositar(int valor) {
        saldo += valor;
    }

    public synchronized boolean sacar(int valor) {
        if (saldo >= valor) {
            saldo -= valor;
            return true;
        }
        return false;
    }
}