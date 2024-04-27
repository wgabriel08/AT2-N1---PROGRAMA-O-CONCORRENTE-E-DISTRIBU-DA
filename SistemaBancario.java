public class SistemaBancario {
    public static void main(String[] args) {
        Banco banco = new Banco(100000); // Saldo inicial do banco: R$100.000,00

        Loja loja1 = new Loja(0);
        Loja loja2 = new Loja(0);
        Loja[] lojas = {loja1, loja2};

        Conta[] contasClientes = new Conta[5];
        Thread[] clientes = new Thread[5];
        for (int i = 0; i < 5; i++) {
            contasClientes[i] = new Conta(1000); // Saldo inicial do cliente: R$1.000,00
            clientes[i] = new Thread(new Cliente("0" + (i+1), contasClientes[i], lojas));
            clientes[i].start();
        }

        Conta[] contasFuncionariosLoja1 = {new Conta(0), new Conta(0)};
        Conta[] contasFuncionariosLoja2 = {new Conta(0), new Conta(0)};
        Thread[] funcionariosLoja1 = {
                new Thread(new Funcionario("01", contasFuncionariosLoja1[0], new Conta(0), loja1, banco)),
                new Thread(new Funcionario("02", contasFuncionariosLoja1[1], new Conta(0), loja1, banco))
        };
        Thread[] funcionariosLoja2 = {
                new Thread(new Funcionario("03", contasFuncionariosLoja2[0], new Conta(0), loja2, banco)),
                new Thread(new Funcionario("04", contasFuncionariosLoja2[1], new Conta(0), loja2, banco))
        };
        for (Thread funcionario : funcionariosLoja1) {
            funcionario.start();
        }
        for (Thread funcionario : funcionariosLoja2) {
            funcionario.start();
        }

        // Espera todas as threads de clientes terminarem
        for (Thread cliente : clientes) {
            try {
                cliente.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Exibe o saldo final de cada conta
        System.out.println("Saldo final das contas:");
        System.out.println("Banco: R$" + banco.getSaldoBanco());
        System.out.println("Loja 1: R$" + loja1.getSaldo());
        System.out.println("Loja 2: R$" + loja2.getSaldo());
        for (Conta contaCliente : contasClientes) {
            System.out.println("Cliente: R$" + contaCliente.getSaldo());
        }
    }
}