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
import model.Campus;
import model.Curso;

/**
 *
 * @author Usuario
 */
public class CursoDAO implements Default {

    public void create(Curso curso) throws Exception {
        String sql = "INSERT INTO cursos(curso, campus, estado, ano_inicio, ano_termino, cadastrado) VALUES (?,?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setString(1, curso.getNome());
            pstm.setInt(2, curso.getCampus().getId());
            pstm.setString(3, curso.getEstado());
            pstm.setInt(4, curso.getAnoInicio());
            pstm.setInt(5, curso.getAnoTermino());

            Date date = Date.valueOf(curso.getDtCriacao());
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

    public List<Curso> read() throws Exception {
        String sql = "SELECT *, cam.nome FROM cursos as c INNER JOIN campus AS cam ON c.campus = cam.id;";

        List<Curso> vetResult = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();;;

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                Curso curso = new Curso();

                curso.setId(rset.getInt("c.id"));
                curso.setNome(rset.getString("c.curso"));

                Campus campus = campusDAO.find(rset.getInt("c.campus"));
                curso.setCampus(campus);

                curso.setEstado(rset.getString("c.estado"));
                curso.setAnoInicio(rset.getInt("c.ano_inicio"));
                curso.setAnoTermino(rset.getInt("c.ano_termino"));

                Date date = rset.getDate("cadastrado");
                LocalDate dataAtualizada = date.toLocalDate();
                curso.setDtCriacao(dataAtualizada);

                date = rset.getDate("cadastrado");
                if (dataAtualizada != null) {
                dataAtualizada = date.toLocalDate();
                    curso.setDtModificacao(dataAtualizada);
                }

                vetResult.add(curso);

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

    public void update(Curso altCurso) throws Exception {;
        String sql = "UPDATE cursos SET curso=?, campus=?, estado=?, "
                + "ano_inicio=?, ano_termino=?, cadastrado=?, modificado=?"
                + "where id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setString(1, altCurso.getNome());
            pstm.setInt(2, altCurso.getCampus().getId());
            pstm.setString(3, altCurso.getEstado());

            pstm.setInt(4, altCurso.getAnoInicio());
            pstm.setInt(5, altCurso.getAnoTermino());

            Date date = Date.valueOf(altCurso.getDtCriacao());
            pstm.setDate(6, date);

            date = Date.valueOf(altCurso.getDtModificacao());
            pstm.setDate(7, date);

            pstm.setInt(8, altCurso.getId());
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

    public void delete(int idCurso) throws Exception {
        String sql = "DELETE FROM cursos WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, idCurso);

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

    public Curso find(int idCurso) throws Exception {
        String sql = "SELECT * FROM cursos WHERE id = '" + idCurso + "'";

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                Curso curso = new Curso();

                curso.setId(rset.getInt("id"));
                curso.setNome(rset.getString("curso"));
                
                Campus campus = campusDAO.find(rset.getInt("c.campus"));
                curso.setCampus(campus);
   
                curso.setEstado(rset.getString("estado"));
                curso.setAnoInicio(rset.getInt("ano_inicio"));
                curso.setAnoTermino(rset.getInt("ano_termino"));

                Date date = rset.getDate("cadastrado");
                LocalDate dataAtualizada = date.toLocalDate();
                curso.setDtCriacao(dataAtualizada);

                date = rset.getDate("modificado");
                if (date != null) {
                    dataAtualizada = date.toLocalDate();
                    curso.setDtModificacao(dataAtualizada);
                }

                return curso;

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
