package Entidade;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private static Connection connection;
    // Construtor
    public ClienteDAO(Connection connection){
        this.connection = connection; //Parâmetro
    }
    //Metodo para inserir um cliente no bd
    public static void inserirCliente(Cliente cliente) throws SQLException{
        String sql = "INSERT INTO clientes (nome, email) VALUES (?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, cliente.getNome());
                stmt.setString(2, cliente.getEmail());
                stmt.setString(3, cliente.getTelefone());
                stmt.executeUpdate();
                System.out.println("Cliente inserido com sucesso.");
        }
    }
    ////Metodo para listar todos os cliente do bd
    public static List<Cliente> listarClientes() throws SQLException{
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");
                Cliente cliente = new Cliente(id, nome, email, telefone);
                clientes.add(cliente);
            }
        }
        return clientes;
    }
    ////Metodo para buscar um cliente por id no bd
    public static Cliente buscarClientePorId(int id) throws SQLException{
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    String email = rs.getString("email");
                    String telefone = rs.getString("telefone");
                    return new Cliente(id, nome, email, telefone);
                }
            }
        }
        return null;
    }
    ////Metodo para atualizar as informações de um cliente no bd
    public static void atualizarCliente(Cliente cliente) throws SQLException{
        String sql = "UPDATE clientes SET nome = ?, email = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefone());
            stmt.setInt(4, cliente.getId());
            stmt.executeUpdate();
            System.out.println("Cliente atualizado com sucesso.");
        }
    }
    ////Metodo para excluir um cliente do bd
    public static void excluirCliente(int id) throws SQLException{
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Cliente excluído com sucesso.");
        }
    }
}
