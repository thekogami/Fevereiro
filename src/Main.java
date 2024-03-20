import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        System.out.println("Olá JDBC");
        try {
            // Conexão usando try-with-resources
            try (Connection con = DriverManager.getConnection("jdbc:sqlite:bancoJava.sql")) {
                System.out.println("Conectou!");
                criarTabela(con);
                // inserirDados(con);
                // atualizarDados(con);
                // deletarDados(con);
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao conectar ao banco de dados:");
            e.printStackTrace();
        }
    }

    // Método para criar a tabela
    private static void criarTabela(Connection con) throws SQLException {
        String criar_tabela = "create table pessoa(" + "id INTEGER PRIMARY KEY," + "nome text);";
        try (Statement stat = con.createStatement()) {
            stat.execute(criar_tabela);
            System.out.println("Tabela criada com sucesso!");
        }
    }

    // Exemplo de método para inserir dados (com PreparedStatement)
    // private static void inserirDados(Connection con) throws SQLException {
    //     String inserir = "insert into pessoa (nome) values (?)";
    //     try (PreparedStatement ps = con.prepareStatement(inserir)) {
    //         ps.setString(1, "Beltrano");
    //         ps.execute();
    //         System.out.println("Dados inseridos com sucesso!");
    //     }
    // }

    // Exemplo de método para atualizar dados
    // private static void atualizarDados(Connection con) throws SQLException {
    //     String atualizar = "update pessoa set nome = 'Ciclano' where id = 1";
    //     try (Statement stat = con.createStatement()) {
    //         stat.execute(atualizar);
    //         System.out.println("Dados atualizados com sucesso!");
    //     }
    // }

    // Exemplo de método para deletar dados
    // private static void deletarDados(Connection con) throws SQLException {
    //     String deletar = "delete from pessoa where id = 1";
    //     try (Statement stat = con.createStatement()) {
    //         stat.execute(deletar);
    //         System.out.println("Dados deletados com sucesso!");
    //     }
    // }
}