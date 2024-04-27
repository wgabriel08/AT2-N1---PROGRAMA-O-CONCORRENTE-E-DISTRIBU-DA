class Loja {
    private Conta contaLoja;
    private int salarioFuncionario = 1400;

    public Loja(int saldoInicial) {
        this.contaLoja = new Conta(saldoInicial);
    }

    public synchronized void receberPagamento(int valor) {
        contaLoja.depositar(valor);
    }

    public synchronized boolean pagarFuncionario(Banco banco, Conta contaFuncionario) {
        if (contaLoja.getSaldo() >= salarioFuncionario) {
            return banco.pagarFuncionario(this, contaFuncionario, salarioFuncionario);
        }
        return false;
    }

    public synchronized int getSaldo() {
        return contaLoja.getSaldo();
    }
}