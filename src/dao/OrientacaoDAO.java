/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import factory.ConnectionFactory;
import model.Orientacao;
import model.Servidor;
import service.ServidorService;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gui
 */
public class OrientacaoDAO {

    private final ServidorService servidorService = new ServidorService();

    public void create(Orientacao orientacao) throws Exception {
        String sql = "INSERT INTO orientacoes (tipo, servidor, aluno, horas_semanais, dt_inicio, dt_termino, cadastrado) VALUES (?,?,?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setString(1, orientacao.getTipo());
            pstm.setInt(2, orientacao.getServidor().getId());
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

    public List<Orientacao> read() throws Exception {

        String sql = "SELECT * FROM orientacoes ";

        List<Orientacao> vetResult = new ArrayList<>();

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

                Servidor servidor = servidorService.getById(rset.getInt("servidor"));
                orientacao.setServidor(servidor);

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

                vetResult.add(orientacao);
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
            pstm.setInt(2, altOrientacao.getServidor().getId());
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

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM orientacoes WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, id);

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

    public Orientacao getById(int id) throws Exception {
        String sql = "SELECT * FROM orientacoes WHERE id = '" + id + "'";

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

                Servidor servidor = servidorService.getById(rset.getInt("servidor"));
                orientacao.setServidor(servidor);

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
