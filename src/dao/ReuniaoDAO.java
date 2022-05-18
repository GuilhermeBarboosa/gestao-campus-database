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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Reuniao;
import model.Servidor;
import view.ReuniaoView;

/**
 *
 * @author Gui
 */
public class ReuniaoDAO {

    public void create(Reuniao reuniao) throws Exception {
        String sql = "INSERT INTO reunioes (comissao, servidor_secre, conteudo_ata, dt_reuniao, cadastrado) VALUES (?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, reuniao.getId_comissao());
            pstm.setInt(2, reuniao.getId_servidorSecre());
            pstm.setString(3, reuniao.getConteudoAta());

            Date date = Date.valueOf(reuniao.getDtReuniao());
            pstm.setDate(4, date);

            date = Date.valueOf(reuniao.getDtCriacao());
            pstm.setDate(5, date);

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

        String sql = "SELECT *, s.nome, c.comissao FROM reunioes AS r INNER JOIN servidores AS s ON r.servidor_secre = s.id INNER JOIN comissoes AS c ON r.comissao = c.id";

        List<String> vetResult = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                vetResult.add("Id: " + rset.getString(1) + "\n"
                        + "Comissao: " + rset.getString(20) + "\n"
                        + "Servidor: " + rset.getString(9) + "\n"
                        + "Conteudo da ata: " + rset.getString(4) + "\n"
                        + "Data da reunião: " + rset.getString(5) + "\n"
                        + "Cadastrado: " + rset.getString(6) + "\n"
                        + "Modificado: " + rset.getString(7) + "\n"
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

        String sql = "SELECT *, s.nome, c.comissao FROM reunioes AS r INNER JOIN servidores AS s ON r.servidor_secre = s.id INNER JOIN comissoes AS c ON r.comissao = c.id";

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
                        + "Comissao: " + rset.getString(20) + "\n"
                        + "Servidor: " + rset.getString(9) + "\n"
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

    public void update(Reuniao altReuniao) throws Exception {;
        String sql = "UPDATE reunioes SET comissao=?, servidor_secre=?, conteudo_ata=?, "
                + "dt_reuniao=?, modificado=?"
                + "where id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, altReuniao.getId_comissao());
            pstm.setInt(2, altReuniao.getId_servidorSecre());
            pstm.setString(3, altReuniao.getConteudoAta());

            Date date = Date.valueOf(altReuniao.getDtReuniao());
            pstm.setDate(4, date);

            date = Date.valueOf(altReuniao.getDtModificacao());
            pstm.setDate(5, date);

            pstm.setInt(6, altReuniao.getId());

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

    public void delete(int idReuniao) throws Exception {
        String sql = "DELETE FROM reunioes WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, idReuniao);

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

    public Reuniao find(int idReuniao) throws Exception {
        String sql = "SELECT * FROM reunioes WHERE id = '" + idReuniao + "'";

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                Reuniao reuniao = new Reuniao();

                reuniao.setId(rset.getInt("id"));
                reuniao.setId_comissao(rset.getInt("comissao"));
                reuniao.setId_servidorSecre(rset.getInt("servidor_secre"));
                reuniao.setConteudoAta(rset.getString("conteudo_ata"));

                Date date = rset.getDate("dt_reuniao");
                LocalDate dataAtualizada = date.toLocalDate();
                reuniao.setDtReuniao(dataAtualizada);

                date = rset.getDate("cadastrado");
                dataAtualizada = date.toLocalDate();
                reuniao.setDtCriacao(dataAtualizada);

                date = rset.getDate("modificado");
                if (date != null) {
                    dataAtualizada = date.toLocalDate();
                    reuniao.setDtModificacao(dataAtualizada);
                }
                return reuniao;

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

    public List<String> relatorioData(LocalDate dtIncial, LocalDate dtFinal) throws SQLException {
        String sql = "SELECT *, s.nome, c.comissao FROM reunioes AS r INNER JOIN servidores AS s ON r.servidor_secre = s.id INNER JOIN comissoes AS c ON r.comissao = c.id WHERE r.dt_reuniao BETWEEN ? AND ?;";

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        List<String> vetResult = new ArrayList<>();
        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            Date date = Date.valueOf(dtIncial);

            pstm.setDate(1, date);
            date = Date.valueOf(dtFinal);

            pstm.setDate(2, date);
            pstm.execute();

            rset = pstm.executeQuery();

            while (rset.next()) {

                vetResult.add("Id: " + rset.getString(1) + "\n"
                        + "Comissao: " + rset.getString(20) + "\n"
                        + "Servidor: " + rset.getString(9) + "\n"
                        + "Conteudo da ata: " + rset.getString(4) + "\n"
                        + "Data da reuniao: " + rset.getString(5));

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
}
