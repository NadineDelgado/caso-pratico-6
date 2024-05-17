package org.example;

import Entidade.Cliente;
import Entidade.ClienteDAO;
import databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static Entidade.ClienteDAO.*;

public class Main {
    public static void main(String[] args) {
        // Conexão com o banco de dados
        String URL = "jdbc:mysql://localhost:3306/empresa";
        String USUARIO = "root";
        String SENHA = "senha";

        ClienteDAO clienteDAO;
        try (Connection connection = DatabaseConnection.getConnection(URL, USUARIO, SENHA)) {
            clienteDAO = new ClienteDAO(connection);
            Scanner scanner = new Scanner(System.in);
            boolean opcao = true;

            // Menu inicial
            while (opcao) {
                System.out.println("\nEscolha uma opção:");
                System.out.println("1. Inserir cliente");
                System.out.println("2. Listar todos os clientes");
                System.out.println("3. Buscar cliente por ID");
                System.out.println("4. Atualizar informações de um cliente");
                System.out.println("5. Excluir cliente");
                System.out.println("6. Sair");
                System.out.print("Escolha um numero: ");
                int a = scanner.nextInt();
                scanner.nextLine();

                switch (a) {
                    case 1:
                        inserirCliente(clienteDAO, scanner);
                        break;
                    case 2:
                        listarClientes(clienteDAO);
                        break;
                    case 3:
                        buscarClientePorId(clienteDAO, scanner);
                        break;
                    case 4:
                        atualizarCliente(clienteDAO, scanner);
                        break;
                    case 5:
                        excluirCliente(clienteDAO, scanner);
                        break;
                    case 6:
                        opcao = false;
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
    // Metodo para inserir clientes
    private static void inserirCliente(ClienteDAO clienteDAO, Scanner scanner) throws SQLException {
        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("E-mail do cliente: ");
        String email = scanner.nextLine();
        System.out.print("Telefone do cliente: ");
        String telefone = scanner.nextLine();
        Cliente cliente = new Cliente(0, nome, email, telefone);
        ClienteDAO.inserirCliente(cliente);
    }
    // Metodo para listar clientes
    private static void listarClientes (ClienteDAO clienteDAO) throws SQLException {
        List<Cliente> clientes = ClienteDAO.listarClientes();
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }
    // Metodo para buscar clientes por id
    private static void buscarClientePorId (ClienteDAO clienteDAO, Scanner scanner) throws SQLException {
        System.out.print("ID do cliente: ");
        int id = scanner.nextInt();
        Cliente cliente = ClienteDAO.buscarClientePorId(id);
        if (cliente != null) {
            System.out.println(cliente);
        } else {
            System.out.println("Cliente não encontrado com o ID fornecido.");
        }
    }
    // Metodo para atualizar clientes
    private static void atualizarCliente (ClienteDAO clienteDAO, Scanner scanner) throws SQLException {
        System.out.print("ID do cliente a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner
        System.out.print("Novo nome do cliente: ");
        String novoNome = scanner.nextLine();
        System.out.print("Novo e-mail do cliente: ");
        String novoEmail = scanner.nextLine();
        System.out.print("Novo telefone do cliente: ");
        String novoTelefone = scanner.nextLine();
        Cliente cliente = new Cliente(id, novoNome, novoEmail, novoTelefone);
        ClienteDAO.atualizarCliente(cliente);
    }
    // Metodo para excluir clientes
    private static void excluirCliente (ClienteDAO clienteDAO, Scanner scanner) throws SQLException {
        System.out.print("ID do cliente a ser excluído: ");
        int id = scanner.nextInt();
        ClienteDAO.excluirCliente(id);
    }
}



