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
import model.Comissao;
import model.Servidor;
import view.ComissaoView;

/**
 *
 * @author Gui
 */
public class ComissaoDAO {

    public void create(Comissao comissao) throws Exception {
        String sql = "INSERT INTO comissoes (comissao, horas_semanais, dt_inicio, dt_termino, estado, cadastrado) VALUES (?,?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setString(1, comissao.getNameComissao());
            pstm.setDouble(2, comissao.getHorasSemanais());

            Date date = Date.valueOf(comissao.getDtInicio());
            pstm.setDate(3, date);

            date = Date.valueOf(comissao.getDtTermino());
            pstm.setDate(4, date);

            pstm.setString(5, comissao.getEstado());

            date = Date.valueOf(comissao.getDtCriacao());
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

        String sql = "SELECT * FROM comissoes";

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
                        + "Comissao: \t" + rset.getString(2) + "\n"
                        + "Horas semanais: \t" + rset.getString(3) + "\n"
                        + "Data de inicio: \t" + rset.getString(4) + "\n"
                        + "Data de termino: \t" + rset.getString(5) + "\n"
                        + "Estado: \t" + rset.getString(6) + "\n"
                        + "Cadastrado: \t" + rset.getString(7) + "\n"
                        + "Modificado: \t" + rset.getString(8) + "\n"
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

        String sql = "SELECT * FROM comissoes";

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
                        + "Comissao: " + rset.getString(2) + "\n"
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

    public void update(Comissao altComissao) throws Exception {;
        String sql = "UPDATE comissoes SET comissao=?, horas_semanais=?, dt_inicio=?, "
                + "dt_termino=?, estado=?, modificado=?"
                + "where id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setString(1, altComissao.getNameComissao());
            pstm.setDouble(2, altComissao.getHorasSemanais());

            Date date = Date.valueOf(altComissao.getDtInicio());
            pstm.setDate(3, date);

            date = Date.valueOf(altComissao.getDtTermino());
            pstm.setDate(4, date);

            pstm.setString(5, altComissao.getEstado());

            date = Date.valueOf(altComissao.getDtModificacao());
            pstm.setDate(6, date);

            pstm.setInt(7, altComissao.getId());

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

    public void delete(int idComissao) throws Exception {
        String sql = "DELETE FROM comissoes WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, idComissao);

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

    public Comissao find(int idComissao) throws Exception {
        String sql = "SELECT * FROM comissoes WHERE id = '" + idComissao + "'";

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                Comissao comissao = new Comissao();

                comissao.setId(rset.getInt("id"));
                comissao.setNameComissao(rset.getString("comissao"));
                comissao.setHorasSemanais(rset.getDouble("horas_semanais"));
                
                Date date = rset.getDate("dt_inicio");
                LocalDate dataAtualizada = date.toLocalDate();
                comissao.setDtInicio(dataAtualizada);
                
                date = rset.getDate("dt_termino");
                dataAtualizada = date.toLocalDate();
                comissao.setDtTermino(dataAtualizada);
                
                comissao.setEstado(rset.getString("estado"));
    
                date = rset.getDate("cadastrado");
                dataAtualizada = date.toLocalDate();
                comissao.setDtCriacao(dataAtualizada);

                date = rset.getDate("modificado");
                if (date != null) {
                    dataAtualizada = date.toLocalDate();
                    comissao.setDtModificacao(dataAtualizada);
                }
                return comissao;

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

    public void modificarEncerradomento(Comissao encerrarComissao) throws SQLException {
       String sql = "UPDATE comissoes SET estado=?, modificado=?"
                + "where id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setString(1, "Inativo");
            
            Date date = Date.valueOf(LocalDate.now());
            pstm.setDate(2, date);

            pstm.setInt(3, encerrarComissao.getId());

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
