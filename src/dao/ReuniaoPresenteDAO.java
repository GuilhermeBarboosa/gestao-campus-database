/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import factory.ConnectionFactory;
import model.Comissao;
import model.ReuniaoPresente;
import model.Servidor;
import service.ComissaoService;
import service.ServidorService;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gui
 */
public class ReuniaoPresenteDAO {

    private final ServidorService servidorService = new ServidorService();
    private final ComissaoService comissaoService = new ComissaoService();

    public void create(ReuniaoPresente reuniaoPresente) throws Exception {
        String sql = "INSERT INTO reuniao_presente (comissao, ata_reuniao, servidor, cadastrado) VALUES (?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, reuniaoPresente.getComissao().getId());
            pstm.setString(2, reuniaoPresente.getAtaReuniao());
            pstm.setInt(3, reuniaoPresente.getServidor().getId());

            Date date = Date.valueOf(reuniaoPresente.getDtCriacao());
            pstm.setDate(4, date);

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

    public List<ReuniaoPresente> read() throws Exception {

        String sql = "SELECT *  FROM reuniao_presente";

        List<ReuniaoPresente> vetResult = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                ReuniaoPresente reuniaoPresente = new ReuniaoPresente();

                reuniaoPresente.setId(rset.getInt("id"));

                Comissao comissao = comissaoService.getById(rset.getInt("comissao"));
                reuniaoPresente.setComissao(comissao);

                Servidor servidor = servidorService.getById(rset.getInt("servidor"));
                reuniaoPresente.setServidor(servidor);

                reuniaoPresente.setAtaReuniao(rset.getString("ata_reuniao"));

                Date date = rset.getDate("cadastrado");
                LocalDate dataAtualizada = date.toLocalDate();
                reuniaoPresente.setDtCriacao(dataAtualizada);

                date = rset.getDate("modificado");
                if (date != null) {
                    dataAtualizada = date.toLocalDate();
                    reuniaoPresente.setDtModificacao(dataAtualizada);
                }

                vetResult.add(reuniaoPresente);
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

    public void update(ReuniaoPresente reuniaoPresente) throws Exception {
        ;
        String sql = "UPDATE reuniao_presente SET comissao=?, ata_reuniao=?, servidor=?, modificado=?"
                + "where id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, reuniaoPresente.getComissao().getId());
            pstm.setString(2, reuniaoPresente.getAtaReuniao());
            pstm.setInt(3, reuniaoPresente.getServidor().getId());

            Date date = Date.valueOf(reuniaoPresente.getDtModificacao());
            pstm.setDate(4, date);

            pstm.setInt(5, reuniaoPresente.getId());

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
        String sql = "DELETE FROM reuniao_presente WHERE id = ?";
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

    public ReuniaoPresente getById(int id) throws Exception {
        String sql = "SELECT * FROM reuniao_presente WHERE id = '" + id + "'";

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                ReuniaoPresente reuniaoPresente = new ReuniaoPresente();

                reuniaoPresente.setId(rset.getInt("id"));
                Comissao comissao = comissaoService.getById(rset.getInt("comissao"));
                reuniaoPresente.setComissao(comissao);

                Servidor servidor = servidorService.getById(rset.getInt("servidor"));
                reuniaoPresente.setServidor(servidor);

                reuniaoPresente.setAtaReuniao(rset.getString("ata_reuniao"));

                Date date = rset.getDate("cadastrado");
                LocalDate dataAtualizada = date.toLocalDate();
                reuniaoPresente.setDtCriacao(dataAtualizada);

                date = rset.getDate("modificado");
                if (date != null) {
                    dataAtualizada = date.toLocalDate();
                    reuniaoPresente.setDtModificacao(dataAtualizada);
                }
                return reuniaoPresente;

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
