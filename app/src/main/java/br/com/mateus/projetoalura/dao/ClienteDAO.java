package br.com.mateus.projetoalura.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.mateus.projetoalura.model.Cliente;

public class ClienteDAO {

    private final static List<Cliente> clientes = new ArrayList<>();
    private static int contadorDeIds = 1;

    public List<Cliente> todos() {
        return new ArrayList<>(clientes);
    }

    public void salva(Cliente novoCliente) {
        novoCliente.setId(contadorDeIds);
        clientes.add(novoCliente);
        contadorDeIds++;
    }

    public void edita(Cliente cliente) {
        Cliente clienteEncontrado = buscaClientePeloId(cliente);
        if (clienteEncontrado != null) {
            int posicaoCliente = clientes.indexOf(clienteEncontrado);
            clientes.set(posicaoCliente, cliente);
        }
    }

    private Cliente buscaClientePeloId(Cliente cliente) {
        for (Cliente i :
                clientes) {
            if (i.getId() == cliente.getId()) {
                return i;
            }
        }
        return null;
    }

    public void remove(Cliente clienteEscolhido) {
        Cliente clienteDevolvido = buscaClientePeloId(clienteEscolhido);
        if (clienteDevolvido != null) {
            clientes.remove(clienteDevolvido);
        }
    }
}
