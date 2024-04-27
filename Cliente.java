class Cliente implements Runnable {
    private String identificador;
    private Conta contaCliente;
    private Loja[] lojas;

    public Cliente(String identificador, Conta contaCliente, Loja[] lojas) {
        this.identificador = identificador;
        this.contaCliente = contaCliente;
        this.lojas = lojas;
    }

    @Override
    public void run() {
        while (true) {
            int valorCompra = (int) (Math.random() * 101) + 100; // Valor entre 100 e 200
            Loja lojaSelecionada = lojas[(int) (Math.random() * lojas.length)];
            synchronized (lojaSelecionada) {
                synchronized (contaCliente) {
                    if (contaCliente.getSaldo() >= valorCompra) {
                        lojaSelecionada.receberPagamento(valorCompra);
                        contaCliente.sacar(valorCompra);
                        System.out.println("Cliente " + identificador + " realizou compra de R$" + valorCompra + " na loja " + lojaSelecionada);
                    } else {
                        break; // Se o saldo for insuficiente, o cliente para as compras
                    }
                }
            }
        }
    }
}