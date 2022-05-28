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
import model.Disciplina;

/**
 *
 * @author Gui
 */
public class DisciplinaDAO {

    public void create(Disciplina disc) throws Exception {
        String sql = "INSERT INTO disciplinas (disciplina, curso, carga_horaria, periodo, cadastrado) VALUES (?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setString(1, disc.getNome());
            pstm.setInt(2, disc.getId_curso());
            pstm.setDouble(3, disc.getCargaHoraria());
            pstm.setInt(4, disc.getPeriodo());
            Date date = Date.valueOf(disc.getDtCriacao());
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

        String sql = "SELECT *, c.curso FROM disciplinas as d INNER JOIN cursos AS c ON d.curso = c.id;";

        List<String> vetResult = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                vetResult.add("Id: " + rset.getString("d.id") + "\n"
                        + "Disciplina: " + rset.getString("d.disciplina") + "\n"
                        + "Curso: " + rset.getString("c.curso") + "\n"
                        + "Carga Horaria: " + rset.getString("d.carga_horaria") + "\n"
                        + "Periodo: " + rset.getString("d.periodo") + "\n"
                        + "Cadstrado: " + rset.getString("d.cadastrado") + "\n"
                        + "Modificado: " + rset.getString("d.modificado") + "\n"
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

        String sql = "SELECT *, c.curso FROM disciplinas as d INNER JOIN cursos AS c ON d.curso = c.id;";

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
                        + "Id: " + rset.getString("d.id") + "\n"
                        + "Nome: " + rset.getString("d.disciplina") + "\n"
                        + "Curso: " + rset.getString("c.curso") + "\n"
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

    public void update(Disciplina altDisciplina) throws Exception {
        String sql = "UPDATE disciplinas SET disciplina=?, curso=?, carga_horaria=?, "
                + "periodo=?, modificado=?"
                + "where id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setString(1, altDisciplina.getNome());
            pstm.setInt(2, altDisciplina.getId_curso());
            pstm.setDouble(3, altDisciplina.getCargaHoraria());
            pstm.setInt(4, altDisciplina.getPeriodo());

            Date date = Date.valueOf(altDisciplina.getDtModificacao());
            pstm.setDate(5, date);

            pstm.setInt(6, altDisciplina.getId());

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

    public void delete(int idDisciplina) throws Exception {
        String sql = "DELETE FROM disciplinas WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, idDisciplina);

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

    public Disciplina find(int idDisciplina) throws Exception {
        String sql = "SELECT * FROM disciplinas WHERE id = '" + idDisciplina + "'";

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
                disciplina.setId_curso(rset.getInt("curso"));
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
