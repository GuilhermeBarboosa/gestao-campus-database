/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.PreparedStatement;
import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Orientacao;
import model.Servidor;
import view.OrientacaoView;

/**
 *
 * @author Gui
 */
public class OrientacaoDAO {

    public void create(Orientacao orientacao) throws Exception {
        String sql = "INSERT INTO orientacoes (tipo, servidor, aluno, horas_semanais, dt_inicio, dt_termino, cadastrado) VALUES (?,?,?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setString(1, orientacao.getTipo());
            pstm.setInt(2, orientacao.getId_servidor());
            pstm.setString(3, orientacao.getNomeAluno());
            pstm.setDouble(4, orientacao.getHorasSemanais());

            Date date = Date.valueOf(orientacao.getDtInicio());
            pstm.setDate(5, date);

            date = Date.valueOf(orientacao.getDtTermino());
            pstm.setDate(6, date);

            date = Date.valueOf(orientacao.getDtCriacao());
            pstm.setDate(7, date);

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

        String sql = "SELECT *, s.nome FROM orientacoes AS o INNER JOIN servidores AS s ON o.servidor = s.id";

        List<String> vetResult = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                vetResult.add("Id: \t" + rset.getString(1) + "\n"
                        + "Tipo: \t" + rset.getString(2) + "\n"
                        + "Servidor: \t" + rset.getString(11) + "\n"
                        + "Aluno: \t" + rset.getString(4) + "\n"
                        + "Horas Semanais: \t" + rset.getString(5) + "\n"
                        + "Data de Inicio: \t" + rset.getString(6) + "\n"
                        + "Data de Termino: \t" + rset.getString(7) + "\n"
                        + "Cadastrado: \t" + rset.getString(8) + "\n"
                        + "Modificado: \t" + rset.getString(9) + "\n"
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

        String sql = "SELECT *, s.nome FROM orientacoes AS o INNER JOIN servidores AS s ON o.servidor = s.id";

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
                        + "Id: " + rset.getString(1) + "\n"
                        + "Tipo: " + rset.getString(2) + "\n"
                        + "Servidor: " + rset.getString(11) + "\n"
                        + "Aluno: " + rset.getString(4) + "\n"
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

    public void update(Orientacao altOrientacao) throws Exception {;
        String sql = "UPDATE orientacoes SET tipo=?, servidor=?, aluno=?, "
                + "horas_semanais=?, dt_inicio=?, dt_termino=?, modificado=?"
                + "where id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setString(1, altOrientacao.getTipo());
            pstm.setInt(2, altOrientacao.getId_servidor());
            pstm.setString(3, altOrientacao.getNomeAluno());
            pstm.setDouble(4, altOrientacao.getHorasSemanais());

            Date date = Date.valueOf(altOrientacao.getDtInicio());
            pstm.setDate(5, date);

            date = Date.valueOf(altOrientacao.getDtTermino());
            pstm.setDate(6, date);

            date = Date.valueOf(altOrientacao.getDtModificacao());
            pstm.setDate(7, date);

            pstm.setInt(8, altOrientacao.getId());

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

    public void delete(int idOrientacao) throws Exception {
        String sql = "DELETE FROM orientacoes WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, idOrientacao);

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

    public Orientacao find(int idOrientacao) throws Exception {
        String sql = "SELECT * FROM orientacoes WHERE id = '" + idOrientacao + "'";

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                Orientacao orientacao = new Orientacao();

                orientacao.setId(rset.getInt("id"));
                orientacao.setTipo(rset.getString("tipo"));
                orientacao.setId_servidor(rset.getInt("servidor"));
                orientacao.setNomeAluno(rset.getString("aluno"));
                orientacao.setHorasSemanais(rset.getDouble("horas_semanais"));

                Date date = rset.getDate("dt_inicio");
                LocalDate dataAtualizada = date.toLocalDate();
                orientacao.setDtInicio(dataAtualizada);

                date = rset.getDate("dt_termino");
                dataAtualizada = date.toLocalDate();
                orientacao.setDtTermino(dataAtualizada);

                date = rset.getDate("cadastrado");
                dataAtualizada = date.toLocalDate();
                orientacao.setDtCriacao(dataAtualizada);

                date = rset.getDate("modificado");
                if (date != null) {
                    dataAtualizada = date.toLocalDate();
                    orientacao.setDtModificacao(dataAtualizada);
                }
                return orientacao;

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
