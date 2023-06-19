package Exceptions;

public class ClienteDAO {

    public static void consultarCliente(String codigo) throws ClienteNaoEncontradoException {
        // Buscar no banco de dados
        boolean isCadastrado = false;

        if (!isCadastrado) {
            throw new ClienteNaoEncontradoException("Cliente não foi encontrado");
        }
    }
}
