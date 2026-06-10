package Farmacia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicamentoDAO {

    public void salvar(Medicamento med) {
        String sql = "INSERT INTO medicamento (id, nome, preco) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, med.id);
            stmt.setString(2, med.nome);
            stmt.setDouble(3, med.preco);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizar(Medicamento med) {
        String sql = "UPDATE medicamento SET nome = ?, preco = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, med.nome);
            stmt.setDouble(2, med.preco);
            stmt.setInt(3, med.id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Medicamento buscarPorId(int id) {
        String sql = "SELECT * FROM medicamento WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Medicamento(rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}