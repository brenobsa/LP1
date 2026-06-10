package Salao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServicoDAO {

    public void salvar(Servico servico) {
        String sql = "INSERT INTO servico (id, descricao, valor) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, servico.id);
            stmt.setString(2, servico.descricao);
            stmt.setDouble(3, servico.valor);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar(Servico servico) {
        String sql = "UPDATE servico SET descricao = ?, valor = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, servico.descricao);
            stmt.setDouble(2, servico.valor);
            stmt.setInt(3, servico.id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Servico buscarPorId(int id) {
        String sql = "SELECT * FROM servico WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Servico(rs.getInt("id"), rs.getString("descricao"), rs.getDouble("valor"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}