package Salao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AgendamentoDAO {

    public void salvar(Agendamento agendamento) {
        String sql = "INSERT INTO agendamento (id, data_hora, forma_pagamento) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, agendamento.id);
            stmt.setString(2, agendamento.dataHora);
            stmt.setString(3, agendamento.formaPagamento);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar(Agendamento agendamento) {
        String sql = "UPDATE agendamento SET data_hora = ?, forma_pagamento = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, agendamento.dataHora);
            stmt.setString(2, agendamento.formaPagamento);
            stmt.setInt(3, agendamento.id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}