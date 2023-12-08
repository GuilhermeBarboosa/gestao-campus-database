/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import factory.ConnectionFactory;
import static factory.ConnectionFactory.createConnectionToMySql;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Campus;

/**
 *
 * @author Gui
 */
public class CampusDAO {

    public void create(Campus campus) throws Exception {
        String sql = "INSERT INTO campus(nome, abreviacao, duracao_aula, dt_criacao_camp, cidade, bairro, rua, cep, cadastrado) VALUES (?,?,?,?,?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setString(1, campus.getNome());
            pstm.setString(2, campus.getAbreviacao());
            pstm.setDouble(3, campus.getDuracaoAula());

            Date date = Date.valueOf(campus.getDtCriacaoCamp());
            pstm.setDate(4, date);

            pstm.setString(5, campus.getCidade());
            pstm.setString(6, campus.getBairro());
            pstm.setString(7, campus.getRua());
            pstm.setString(8, campus.getCep());

            date = Date.valueOf(campus.getDtCriacao());
            pstm.setDate(9, date);

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

    public List<Campus> read() throws Exception {
        String sql = "SELECT * FROM campus as c";

        List<Campus> vetResult = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                Campus campus = new Campus();

                campus.setId(rset.getInt("c.id"));
                campus.setNome(rset.getString("c.nome"));
                campus.setAbreviacao(rset.getString("c.abreviacao"));
                campus.setDuracaoAula(rset.getDouble("c.duracao_aula"));

                Date date = rset.getDate("c.dt_criacao_camp");
                LocalDate dataAtualizada = date.toLocalDate();
                campus.setDtCriacaoCamp(dataAtualizada);

                campus.setCidade(rset.getString("c.cidade"));
                campus.setBairro(rset.getString("c.bairro"));
                campus.setRua(rset.getString("c.rua"));
                campus.setCep(rset.getString("c.cep"));

                date = rset.getDate("cadastrado");
                dataAtualizada = date.toLocalDate();
                campus.setDtCriacao(dataAtualizada);

                date = rset.getDate("cadastrado");
                if (dataAtualizada != null) {
                    dataAtualizada = date.toLocalDate();
                    campus.setDtModificacao(dataAtualizada);
                }

                vetResult.add(campus);
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

    public void update(Campus campus) throws Exception {;
        String sql = "UPDATE campus SET nome=?, abreviacao=?, duracao_aula=?, "
                + "dt_criacao_camp=?, cidade=?, bairro=?, rua=?, cep=?, modificado=?"
                + "where id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setString(1, campus.getNome());
            pstm.setString(2, campus.getAbreviacao());
            pstm.setDouble(3, campus.getDuracaoAula());

            Date date = Date.valueOf(campus.getDtCriacaoCamp());
            pstm.setDate(4, date);

            pstm.setString(5, campus.getCidade());
            pstm.setString(6, campus.getBairro());
            pstm.setString(7, campus.getRua());
            pstm.setString(8, campus.getCep());

            date = Date.valueOf(campus.getDtModificacao());
            pstm.setDate(9, date);

            pstm.setInt(10, campus.getId());
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
        String sql = "DELETE FROM campus WHERE id = ?";
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

    public Campus getById(int id) throws Exception {
        String sql = "SELECT * FROM campus WHERE id = '" + id + "'";

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                Campus campus = new Campus();

                campus.setId(rset.getInt("id"));
                campus.setNome(rset.getString("nome"));
                campus.setAbreviacao(rset.getString("abreviacao"));
                campus.setDuracaoAula(rset.getDouble("duracao_aula"));

                Date date = rset.getDate("dt_criacao_camp");
                LocalDate dataAtualizada = date.toLocalDate();
                campus.setDtCriacaoCamp(dataAtualizada);

                campus.setCidade(rset.getString("cidade"));
                campus.setBairro(rset.getString("bairro"));
                campus.setRua(rset.getString("rua"));
                campus.setCep(rset.getString("cep"));

                date = rset.getDate("cadastrado");
                dataAtualizada = date.toLocalDate();
                campus.setDtCriacao(dataAtualizada);

                date = rset.getDate("modificado");
                if (date != null) {
                    dataAtualizada = date.toLocalDate();
                    campus.setDtModificacao(dataAtualizada);
                }
                return campus;

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
