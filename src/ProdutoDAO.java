import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Date;


public class ProdutoDAO {
    // Configurações de conexão
    private String url = "jdbc:sqlite:bancoJava.sql";

    // Método para obter uma conexão com o banco de dados
    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(url);
    }

    // Método para adicionar um novo produto
    public void adicionarProduto(Produto produto) {
        String sql = "INSERT INTO produto (nome, valor, estoque, data_lancamento) VALUES (?, ?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, produto.getNome());
            pstmt.setDouble(2, produto.getValor());
            pstmt.setInt(3, produto.getEstoque());
            pstmt.setDate(4, Date.valueOf(produto.getDataLancamento()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar um produto existente
    public void atualizarProduto(Produto produto) {
        String sql = "UPDATE produto SET nome = ?, valor = ?, estoque = ?, data_lancamento = ? WHERE identificador = ?";
        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, produto.getNome());
            pstmt.setDouble(2, produto.getValor());
            pstmt.setInt(3, produto.getEstoque());
            pstmt.setDate(4, Date.valueOf(produto.getDataLancamento()));
            pstmt.setInt(5, produto.getIdentificador());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir um produto
    public void excluirProduto(int identificador) {
        String sql = "DELETE FROM produto WHERE identificador = ?";
        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, identificador);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos os produtos
    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto";
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Produto produto = new Produto(
                        rs.getInt("identificador"),
                        rs.getString("nome"),
                        rs.getDouble("valor"),
                        rs.getInt("estoque"),
                        rs.getDate("data_lancamento").toLocalDate()
                );
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }
}