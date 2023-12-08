/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import factory.ConnectionFactory;
import model.Curso;
import model.Disciplina;
import service.CursoService;

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
public class DisciplinaDAO {

    private final CursoService cursoService = new CursoService();

    public void create(Disciplina disciplina) throws Exception {
        String sql = "INSERT INTO disciplinas (disciplina, curso, carga_horaria, periodo, cadastrado) VALUES (?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setString(1, disciplina.getNome());
            pstm.setInt(2, disciplina.getCurso().getId());
            pstm.setDouble(3, disciplina.getCargaHoraria());
            pstm.setInt(4, disciplina.getPeriodo());
            Date date = Date.valueOf(disciplina.getDtCriacao());
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

    public List<Disciplina> read() throws Exception {

        String sql = "SELECT * FROM disciplinas as d";

        List<Disciplina> vetResult = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                Disciplina disciplina = new Disciplina();

                disciplina.setId(rset.getInt("id"));
                disciplina.setNome(rset.getString("disciplina"));

                Curso curso = cursoService.getById(rset.getInt("curso"));
                disciplina.setCurso(curso);

                disciplina.setCargaHoraria(rset.getDouble("carga_horaria"));
                disciplina.setPeriodo(rset.getInt("periodo"));

                Date date = rset.getDate("cadastrado");
                LocalDate dataAtualizada = date.toLocalDate();
                disciplina.setDtCriacao(dataAtualizada);

                date = rset.getDate("modificado");
                if (date != null) {
                    dataAtualizada = date.toLocalDate();
                    disciplina.setDtModificacao(dataAtualizada);
                }

                vetResult.add(disciplina);
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

    public void update(Disciplina disciplina) throws Exception {
        String sql = "UPDATE disciplinas SET disciplina=?, curso=?, carga_horaria=?, "
                + "periodo=?, modificado=?"
                + "where id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setString(1, disciplina.getNome());
            pstm.setInt(2, disciplina.getCurso().getId());
            pstm.setDouble(3, disciplina.getCargaHoraria());
            pstm.setInt(4, disciplina.getPeriodo());

            Date date = Date.valueOf(disciplina.getDtModificacao());
            pstm.setDate(5, date);

            pstm.setInt(6, disciplina.getId());

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
        String sql = "DELETE FROM disciplinas WHERE id = ?";
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

    public Disciplina getById(int id) throws Exception {
        String sql = "SELECT * FROM disciplinas WHERE id = '" + id + "'";

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                Disciplina disciplina = new Disciplina();

                disciplina.setId(rset.getInt("id"));
                disciplina.setNome(rset.getString("disciplina"));

                Curso curso = cursoService.getById(rset.getInt("curso"));
                disciplina.setCurso(curso);

                disciplina.setCargaHoraria(rset.getDouble("carga_horaria"));
                disciplina.setPeriodo(rset.getInt("periodo"));

                Date date = rset.getDate("cadastrado");
                LocalDate dataAtualizada = date.toLocalDate();
                disciplina.setDtCriacao(dataAtualizada);

                date = rset.getDate("modificado");
                if (date != null) {
                    dataAtualizada = date.toLocalDate();
                    disciplina.setDtModificacao(dataAtualizada);
                }

                return disciplina;
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
