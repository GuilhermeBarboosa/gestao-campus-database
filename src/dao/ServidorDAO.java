/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import factory.ConnectionFactory;
import model.Campus;
import model.Servidor;
import service.CampusService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aluno
 */
public class ServidorDAO {

    private CampusService campusService = new CampusService();

    public void create(Servidor servidor) throws Exception {
        String sql = "INSERT INTO servidores (nome, campus, email, cargo, login, senha, perfil, horas_totais, cadastrado) VALUES (?,?,?,?,?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setString(1, servidor.getNome());
            pstm.setInt(2, servidor.getCampus().getId());
            pstm.setString(3, servidor.getEmail());
            pstm.setString(4, servidor.getCargo());
            pstm.setString(5, servidor.getLogin());
            pstm.setString(6, servidor.getSenha());
            pstm.setInt(7, servidor.getPerfil());
            pstm.setDouble(8, servidor.getHorasTotais());
            Date date = Date.valueOf(servidor.getDtCriacao());
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

    public List<Servidor> read() throws Exception {

        String sql = "SELECT * FROM servidores";

        List<Servidor> vetResult = new ArrayList();

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {
                Servidor servidor = new Servidor();

                servidor.setId(rset.getInt("id"));
                servidor.setNome(rset.getString("nome"));

                Campus campus = campusService.getById(rset.getInt("campus"));
                servidor.setCampus(campus);

                servidor.setEmail(rset.getString("email"));
                servidor.setCargo(rset.getString("cargo"));
                servidor.setLogin(rset.getString("login"));
                servidor.setSenha(rset.getString("senha"));
                servidor.setPerfil(rset.getInt("perfil"));
                servidor.setHorasTotais(rset.getDouble("horas_totais"));

                Date date = rset.getDate("cadastrado");
                LocalDate dataAtualizada = date.toLocalDate();
                servidor.setDtCriacao(dataAtualizada);

                date = rset.getDate("modificado");
                if (date != null) {
                    dataAtualizada = date.toLocalDate();
                    servidor.setDtModificacao(dataAtualizada);
                }

                vetResult.add(servidor);
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

    public void update(Servidor servidor) throws Exception {
        ;
        String sql = "UPDATE servidores SET nome=?, campus=?, email=?, "
                + "cargo=?, login=?, senha=?, perfil=?, horas_totais=?, modificado=?"
                + "where id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setString(1, servidor.getNome());
            pstm.setInt(2, servidor.getCampus().getId());
            pstm.setString(3, servidor.getEmail());
            pstm.setString(4, servidor.getCargo());
            pstm.setString(5, servidor.getLogin());
            pstm.setString(6, servidor.getSenha());
            pstm.setInt(7, servidor.getPerfil());
            pstm.setDouble(8, servidor.getHorasTotais());

            Date date = Date.valueOf(servidor.getDtModificacao());
            pstm.setDate(9, date);

            pstm.setInt(10, servidor.getId());

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
        String sql = "DELETE FROM servidores WHERE id = ?";
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

    public Servidor getById(int id) throws Exception {
        String sql = "SELECT * FROM servidores WHERE id = '" + id + "'";

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                Servidor servidor = new Servidor();

                servidor.setId(rset.getInt("id"));
                servidor.setNome(rset.getString("nome"));

                Campus campus = campusService.getById(rset.getInt("campus"));
                servidor.setCampus(campus);

                servidor.setEmail(rset.getString("email"));
                servidor.setCargo(rset.getString("cargo"));
                servidor.setLogin(rset.getString("login"));
                servidor.setSenha(rset.getString("senha"));
                servidor.setPerfil(rset.getInt("perfil"));
                servidor.setHorasTotais(rset.getDouble("horas_totais"));

                Date date = rset.getDate("cadastrado");
                LocalDate dataAtualizada = date.toLocalDate();
                servidor.setDtCriacao(dataAtualizada);

                date = rset.getDate("modificado");
                if (date != null) {
                    dataAtualizada = date.toLocalDate();
                    servidor.setDtModificacao(dataAtualizada);
                }
                return servidor;

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

    public void updateHours(Servidor servidor, double horasSemanais, int id) throws SQLException {
        String sql = "UPDATE servidores SET horas_totais=?"
                + "where id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setDouble(1, (servidor.getHorasTotais() + horasSemanais));
            pstm.setInt(2, id);

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

    public void removeHours(Servidor servidor, double horasSemanais, int id) throws SQLException {
        String sql = "UPDATE servidores SET horas_totais=?"
                + "where id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setDouble(1, (servidor.getHorasTotais() - horasSemanais));
            pstm.setInt(2, id);

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
