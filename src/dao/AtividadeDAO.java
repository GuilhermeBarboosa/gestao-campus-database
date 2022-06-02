/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Atividade;

/**
 *
 * @author Gui
 */
public class AtividadeDAO implements Default{

    public void create(Atividade atividade) throws Exception{
        String sql = "INSERT INTO atividades (atividade, servidor, horas_semanais, dt_inicio, dt_termino,"
                + " cadastrado) VALUES (?,?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setString(1, atividade.getDescricao());
            pstm.setInt(2, atividade.getId_servidor());
            pstm.setDouble(3, atividade.getHorasSemanais());

            Date date = Date.valueOf(atividade.getDtInicio());
            pstm.setDate(4, date);

            date = Date.valueOf(atividade.getDtTermino());
            pstm.setDate(5, date);

            date = Date.valueOf(atividade.getDtCriacao());
            pstm.setDate(6, date);

            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> read() throws Exception {

        String sql = "SELECT *, s.nome FROM atividades as a INNER JOIN servidores AS s ON a.servidor = s.id;";

        List<String> vetResult = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                vetResult.add("Id: " + rset.getString("a.id") + "\n"
                        + "Atividade: " + rset.getString("a.atividade") + "\n"
                        + "Servidor: " + rset.getString("s.nome") + "\n"
                        + "Horas: " + rset.getString("a.horas_semanais") + "\n"
                        + "Data de inicio: " + rset.getString("a.dt_inicio") + "\n"
                        + "Data de termino: " + rset.getString("a.dt_termino") + "\n"
                        + "Cadastrado: " + rset.getString("a.cadastrado") + "\n"
                        + "Modificado: " + rset.getString("a.modificado") + "\n"
                        + "--------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rset != null) {
                rset.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return vetResult;
    }

    public List<String> readId() throws Exception {

        String sql = "SELECT *, s.nome FROM atividades as a INNER JOIN servidores AS s ON a.servidor = s.id;";

        List<String> vetResult = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                vetResult.add("=========================\n"
                        + "Id: " + rset.getString("a.id") + "\n"
                        + "Atividade: " + rset.getString("a.atividade") + "\n"
                        + "Servidor: " + rset.getString("s.nome") + "\n"
                        + "=========================" + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rset != null) {
                rset.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return vetResult;
    }

    public void update(Atividade altAtividade) throws Exception {;
        String sql = "UPDATE atividades SET atividade=?, servidor=?, horas_semanais=?, "
                + "dt_inicio=?, dt_termino=?, modificado=?"
                + "where id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setString(1, altAtividade.getDescricao());
            pstm.setInt(2, altAtividade.getId_servidor());
            pstm.setDouble(3, altAtividade.getHorasSemanais());

            Date date = Date.valueOf(altAtividade.getDtInicio());
            pstm.setDate(4, date);

            date = Date.valueOf(altAtividade.getDtTermino());
            pstm.setDate(5, date);

            date = Date.valueOf(altAtividade.getDtModificacao());
            pstm.setDate(6, date);

            pstm.setInt(7, altAtividade.getId());

            pstm.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void delete(int idAtividade) throws Exception {
        String sql = "DELETE FROM atividades WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, idAtividade);

            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Atividade find(int idAtividades) throws Exception {
        String sql = "SELECT * FROM atividades WHERE id = '" + idAtividades + "'";

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                Atividade atividade = new Atividade();

                atividade.setId(rset.getInt("id"));
                atividade.setDescricao(rset.getString("atividade"));
                atividade.setId_servidor(rset.getInt("servidor"));
                atividade.setHorasSemanais(rset.getDouble("horas_semanais"));

                Date date = rset.getDate("dt_inicio");
                LocalDate dataAtualizada = date.toLocalDate();
                atividade.setDtInicio(dataAtualizada);

                
                date = rset.getDate("dt_termino");
                dataAtualizada = date.toLocalDate();
                atividade.setDtTermino(dataAtualizada);

                date = rset.getDate("cadastrado");
                dataAtualizada = date.toLocalDate();
                atividade.setDtCriacao(dataAtualizada);

                date = rset.getDate("modificado");
                if (date != null) {
                    dataAtualizada = date.toLocalDate();
                    atividade.setDtModificacao(dataAtualizada);
                }

                return atividade;

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rset != null) {
                rset.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return null;
    }

}
