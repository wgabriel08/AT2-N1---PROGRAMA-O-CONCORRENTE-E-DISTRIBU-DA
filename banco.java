class Banco {
    private Conta contaBanco;

    public Banco(int saldoInicial) {
        this.contaBanco = new Conta(saldoInicial);
    }

    public synchronized void transferir(Conta origem, Conta destino, int valor) {
        if (origem.sacar(valor)) {
            destino.depositar(valor);
            System.out.println("Transferência de R$" + valor + " realizada de " + origem + " para " + destino);
        } else {
            System.out.println("Transferência de R$" + valor + " falhou de " + origem + " para " + destino);
        }
    }

    public synchronized int getSaldoBanco() {
        return contaBanco.getSaldo();
    }

    public synchronized void exibirSaldoFinal() {
        System.out.println("Saldo final do banco: R$" + getSaldoBanco());
    }

    public synchronized boolean pagarFuncionario(Loja loja, Conta contaFuncionario, int salario) {
        if (contaBanco.getSaldo() >= salario) {
            contaBanco.sacar(salario);
            loja.receberPagamento(salario);
            contaFuncionario.depositar(salario);
            System.out.println("Pagamento de salário de R$" + salario + " efetuado para funcionário " + contaFuncionario);
            return true;
        }
        return false;
    }
}