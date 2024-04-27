class Funcionario implements Runnable {
    private Conta contaSalario;
    private Conta contaInvestimento;
    private Loja loja;
    private Banco banco;
    private String identificador;

    public Funcionario(String identificador, Conta contaSalario, Conta contaInvestimento, Loja loja, Banco banco) {
        this.identificador = identificador;
        this.contaSalario = contaSalario;
        this.contaInvestimento = contaInvestimento;
        this.loja = loja;
        this.banco = banco;
    }

    @Override
    public void run() {
        boolean salarioRecebido = false;
        while (!salarioRecebido) {
            salarioRecebido = loja.pagarFuncionario(banco, contaSalario);
            if (!salarioRecebido) {
                try {
                    Thread.sleep(1000); // Espera um pouco antes de tentar novamente
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        int valorInvestimento = (int) (contaSalario.getSaldo() * 0.2);
        contaInvestimento.depositar(valorInvestimento);
        System.out.println("Investimento de R$" + valorInvestimento + " realizado para funcionário " + identificador);
    }
}