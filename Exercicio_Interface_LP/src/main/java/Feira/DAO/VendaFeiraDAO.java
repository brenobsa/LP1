package Feira;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VendaFeiraDAO {

    public void salvar(VendaFeira venda) {
        String sql = "INSERT INTO venda (id, valor, banca_id) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, venda.idVenda);
            stmt.setDouble(2, venda.valorVenda);
            stmt.setInt(3, venda.bancaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar(VendaFeira venda) {
        String sql = "UPDATE venda SET valor = ?, banca_id = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, venda.valorVenda);
            stmt.setInt(2, venda.bancaId);
            stmt.setInt(3, venda.idVenda);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}