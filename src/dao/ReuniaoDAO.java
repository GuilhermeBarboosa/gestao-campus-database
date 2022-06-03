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
import model.Comissao;
import model.Reuniao;
import model.Servidor;

/**
 *
 * @author Gui
 */
public class ReuniaoDAO implements Default {

    public void create(Reuniao reuniao) throws Exception {
        String sql = "INSERT INTO reunioes (comissao, servidor_secre, conteudo_ata, dt_reuniao, cadastrado) VALUES (?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, reuniao.getComissao().getId());
            pstm.setInt(2, reuniao.getServidorSecre().getId());
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

    public List<Reuniao> read() throws Exception {

        String sql = "SELECT *, s.nome, c.comissao FROM reunioes AS r INNER JOIN servidores AS s ON r.servidor_secre = s.id INNER JOIN comissoes AS c ON r.comissao = c.id";

        List<Reuniao> vetResult = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                Reuniao reuniao = new Reuniao();

                reuniao.setId(0);

                Comissao comissao = comissaoDAO.find(rset.getInt("r.comissao"));
                reuniao.setComissao(comissao);

                Servidor servidor = servidorDAO.find(rset.getInt("r.servidor_secre"));
                reuniao.setServidorSecre(servidor);

                reuniao.setConteudoAta(rset.getString("r.conteudo_ata"));

                Date date = rset.getDate("r.dt_reuniao");
                LocalDate dataAtualizada = date.toLocalDate();
                reuniao.setDtReuniao(dataAtualizada);

                date = rset.getDate("r.cadastrado");
                dataAtualizada = date.toLocalDate();
                reuniao.setDtCriacao(dataAtualizada);
                reuniao.setId(0);

                date = rset.getDate("r.modificado");
                if (date != null) {
                    dataAtualizada = date.toLocalDate();
                    reuniao.setDtModificacao(dataAtualizada);
                }

                vetResult.add(reuniao);
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

            pstm.setInt(1, altReuniao.getComissao().getId());
            pstm.setInt(2, altReuniao.getServidorSecre().getId());
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
                Comissao comissao = comissaoDAO.find(rset.getInt("r.comissao"));
                reuniao.setComissao(comissao);

                Servidor servidor = servidorDAO.find(rset.getInt("r.servidor_secre"));
                reuniao.setServidorSecre(servidor);

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
