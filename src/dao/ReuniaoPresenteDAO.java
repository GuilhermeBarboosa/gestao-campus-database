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
import model.ReuniaoPresente;

/**
 *
 * @author Gui
 */
public class ReuniaoPresenteDAO {

    public void create(ReuniaoPresente reuniaoPresente) throws Exception {
        String sql = "INSERT INTO reuniao_presente (comissao, ata_reuniao, servidor, cadastrado) VALUES (?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            
            pstm.setInt(1, reuniaoPresente.getId_comissao());
            pstm.setString(2, reuniaoPresente.getAtaReuniao());
            pstm.setInt(3, reuniaoPresente.getId_servidor());
       
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

    public List<String> read() throws Exception {

        String sql = "SELECT *, s.nome, c.comissao FROM reuniao_presente AS rp INNER JOIN servidores AS s ON rp.servidor = s.id INNER JOIN comissoes AS c ON rp.comissao = c.id";

        List<String> vetResult = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                vetResult.add( "Id: " + rset.getString("rp.id") + "\n"
                        + "Comissao: " + rset.getString("c.comissao") + "\n"
                        + "Servidor: " + rset.getString("s.nome") + "\n"
                        + "Ata de reunião: " + rset.getString("rp.ata_reuniao") + "\n"
                        + "Cadastrado: " + rset.getString("rp.cadastrado") + "\n"
                        + "Modificado: " + rset.getString("rp.modificado") + "\n"
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

        String sql = "SELECT *, s.nome, c.comissao FROM reuniao_presente AS rp INNER JOIN servidores AS s ON rp.servidor = s.id INNER JOIN comissoes AS c ON rp.comissao = c.id";

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
                        + "Id: " + rset.getString("rp.id") + "\n"
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

    public void update(ReuniaoPresente altReuniaoPresente) throws Exception {;
        String sql = "UPDATE reuniao_presente SET comissao=?, ata_reuniao=?, servidor=?, modificado=?"
                + "where id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);
            
            pstm.setInt(1, altReuniaoPresente.getId_comissao());
            pstm.setString(2, altReuniaoPresente.getAtaReuniao());
            pstm.setInt(3, altReuniaoPresente.getId_servidor());

            Date date = Date.valueOf(altReuniaoPresente.getDtModificacao());
            pstm.setDate(4, date);

            pstm.setInt(5, altReuniaoPresente.getId());

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

    public void delete(int idReuniaoPresente) throws Exception {
        String sql = "DELETE FROM reuniao_presente WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, idReuniaoPresente);

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

    public ReuniaoPresente find(int idReuniaoPresente) throws Exception {
        String sql = "SELECT * FROM reuniao_presente WHERE id = '" + idReuniaoPresente + "'";

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                ReuniaoPresente reuniaoPresetente = new ReuniaoPresente();

                reuniaoPresetente.setId(rset.getInt("id"));
                reuniaoPresetente.setId_comissao(rset.getInt("comissao"));
                reuniaoPresetente.setAtaReuniao(rset.getString("ata_reuniao"));
                reuniaoPresetente.setId_servidor(rset.getInt("servidor"));
    

                Date date = rset.getDate("cadastrado");
                LocalDate dataAtualizada = date.toLocalDate();
                reuniaoPresetente.setDtCriacao(dataAtualizada);

                date = rset.getDate("modificado");
                if (date != null) {
                    dataAtualizada = date.toLocalDate();
                    reuniaoPresetente.setDtModificacao(dataAtualizada);
                }
                return reuniaoPresetente;

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
