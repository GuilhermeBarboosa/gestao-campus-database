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
import model.Reuniao;

/**
 *
 * @author Gui
 */
public class ReuniaoDAO implements Default{

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

                vetResult.add("Id: " + rset.getString("r.id") + "\n"
                        + "Comissao: " + rset.getString("c.comissao") + "\n"
                        + "Servidor: " + rset.getString("s.nome") + "\n"
                        + "Conteudo da ata: " + rset.getString("r.conteudo_ata") + "\n"
                        + "Data da reunião: " + rset.getString("r.dt_reuniao") + "\n"
                        + "Cadastrado: " + rset.getString("r.cadastrado") + "\n"
                        + "Modificado: " + rset.getString("r.modificado") + "\n"
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
                        + "Id: " + rset.getString("r.id") + "\n"
                        + "Comissao: " + rset.getString("c.comissao") + "\n"
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

    
}
