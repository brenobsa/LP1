package Farmacia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PedidoDAO {

    public void salvar(Pedido pedido) {
        String sql = "INSERT INTO pedido (numero, valor, clienteCpf) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pedido.numero);
            stmt.setDouble(2, pedido.valor);
            stmt.setString(3, pedido.clienteCpf);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Alterado o nome do método para sincronizar com o Controller
    public void atualizar(Pedido pedido) {
        String sql = "UPDATE pedido SET valor = ?, clienteCpf = ? WHERE numero = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, pedido.valor);
            stmt.setString(2, pedido.clienteCpf);
            stmt.setInt(3, pedido.numero);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}