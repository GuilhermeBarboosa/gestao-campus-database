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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Disciplina;
import model.Servidor;
import view.DisciplinaView;

/**
 *
 * @author Gui
 */
public class DisciplinaDAO {

    public void create(Disciplina disc) throws Exception {
        String sql = "INSERT INTO disciplina (nome_disciplina, curso, carga_horaria, periodo, cadastrado) VALUES (?,?,?,?,?)";

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

        String sql = "SELECT *, c.nome_curso FROM disciplina as d INNER JOIN curso AS c ON d.curso = c.id;";

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
                        + "Disciplina: \t" + rset.getString(2) + "\n"
                        + "Curso: \t" + rset.getString(10) + "\n"
                        + "Carga Horaria: \t" + rset.getString(4) + "\n"
                        + "Periodo: \t" + rset.getString(5) + "\n"
                        + "Cadstrado: \t" + rset.getString(6) + "\n"
                        + "Modificado: \t" + rset.getString(7) + "\n"
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

        String sql = "SELECT *, c.nome_curso FROM disciplina as d INNER JOIN curso AS c ON d.curso = c.id;";

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
                        + "Nome: " + rset.getString(2) + "\n"
                        + "Campus: " + rset.getString(10) + "\n"
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
        String sql = "UPDATE disciplina SET nome_disciplina=?, curso=?, carga_horaria=?, "
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
        String sql = "DELETE FROM disciplina WHERE id = ?";
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
        String sql = "SELECT * FROM disciplina WHERE id = '" + idDisciplina + "'";

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
                disciplina.setNome(rset.getString("nome_disciplina"));
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
