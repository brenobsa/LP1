package Feira;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoFeiraDAO {

    public void salvar(ProdutoFeira prod) {
        String sql = "INSERT INTO produto (id, nome, preco_quilo) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, prod.id);
            stmt.setString(2, prod.nome);
            stmt.setDouble(3, prod.precoPorQuilo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar(ProdutoFeira prod) {
        String sql = "UPDATE produto SET nome = ?, preco_quilo = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, prod.nome);
            stmt.setDouble(2, prod.precoPorQuilo);
            stmt.setInt(3, prod.id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ProdutoFeira buscarPorId(int id) {
        String sql = "SELECT * FROM produto WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ProdutoFeira(rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco_quilo"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}