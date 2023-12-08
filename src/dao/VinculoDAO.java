/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import factory.ConnectionFactory;
import model.Comissao;
import model.Servidor;
import model.Vinculo;
import service.ComissaoService;
import service.ServidorService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gui
 */
public class VinculoDAO {

    private final ServidorService servidorService = new ServidorService();
    private final ComissaoService comissaoService = new ComissaoService();

    public void create(Vinculo vinculo) throws Exception {
        String sql = "INSERT INTO vinculos (servidor, comissao, papel, dt_entrada, dt_saida, cadastrado) VALUES (?,?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, vinculo.getServidor().getId());
            pstm.setInt(2, vinculo.getComissao().getId());
            pstm.setString(3, vinculo.getPapel());

            Date date = Date.valueOf(vinculo.getDtEntrada());
            pstm.setDate(4, date);

            date = Date.valueOf(vinculo.getDtSaida());
            pstm.setDate(5, date);

            date = Date.valueOf(vinculo.getDtCriacao());
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

    public List<Vinculo> read() throws Exception {

        String sql = "SELECT *, s.nome, c.comissao FROM vinculos AS v INNER JOIN servidores AS s ON v.servidor = s.id INNER JOIN comissoes AS c ON v.comissao = c.id;";

        List<Vinculo> vetResult = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                Vinculo vinculo = new Vinculo();

                vinculo.setId(rset.getInt("v.id"));

                Comissao comissao = comissaoService.getById(rset.getInt("v.comissao"));
                vinculo.setComissao(comissao);

                Servidor servidor = servidorService.getById(rset.getInt("v.servidor"));
                vinculo.setServidor(servidor);

                vinculo.setPapel(rset.getString("v.papel"));

                Date date = rset.getDate("v.dt_entrada");
                LocalDate dataAtualizada = date.toLocalDate();
                vinculo.setDtEntrada(dataAtualizada);

                date = rset.getDate("v.dt_saida");
                dataAtualizada = date.toLocalDate();
                vinculo.setDtSaida(dataAtualizada);

                date = rset.getDate("v.cadastrado");
                dataAtualizada = date.toLocalDate();
                vinculo.setDtCriacao(dataAtualizada);

                date = rset.getDate("v.cadastrado");
                if (dataAtualizada != null) {
                    dataAtualizada = date.toLocalDate();
                    vinculo.setDtModificacao(dataAtualizada);
                }

                vetResult.add(vinculo);
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

    public void update(Vinculo vinculo) throws Exception {
        ;
        String sql = "UPDATE vinculos SET servidor=?, comissao=?, papel=?, "
                + "dt_entrada=?, dt_saida=?, modificado=?"
                + "where id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, vinculo.getServidor().getId());
            pstm.setInt(2, vinculo.getComissao().getId());
            pstm.setString(3, vinculo.getPapel());

            Date date = Date.valueOf(vinculo.getDtEntrada());
            pstm.setDate(4, date);

            date = Date.valueOf(vinculo.getDtSaida());
            pstm.setDate(5, date);

            date = Date.valueOf(vinculo.getDtModificacao());
            pstm.setDate(6, date);

            pstm.setInt(7, vinculo.getId());

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
        String sql = "DELETE FROM vinculos WHERE id = ?";
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

    public Vinculo getById(int id) throws Exception {
        String sql = "SELECT * FROM vinculos WHERE id = '" + id + "'";

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                Vinculo vinculo = new Vinculo();

                vinculo.setId(rset.getInt("id"));
                Comissao comissao = comissaoService.getById(rset.getInt("comissao"));
                vinculo.setComissao(comissao);

                Servidor servidor = servidorService.getById(rset.getInt("servidor"));
                vinculo.setServidor(servidor);
                vinculo.setPapel(rset.getString("papel"));

                Date date = rset.getDate("dt_entrada");
                LocalDate dataAtualizada = date.toLocalDate();
                vinculo.setDtEntrada(dataAtualizada);

                date = rset.getDate("dt_saida");
                dataAtualizada = date.toLocalDate();
                vinculo.setDtSaida(dataAtualizada);

                date = rset.getDate("cadastrado");
                dataAtualizada = date.toLocalDate();
                vinculo.setDtCriacao(dataAtualizada);

                date = rset.getDate("modificado");
                if (date != null) {
                    dataAtualizada = date.toLocalDate();
                    vinculo.setDtModificacao(dataAtualizada);
                }
                return vinculo;

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

    public void encerrarVinculos(Comissao comAux) throws SQLException {
        String sql = "DELETE FROM vinculos WHERE comissao = ?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, comAux.getId());

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
}
