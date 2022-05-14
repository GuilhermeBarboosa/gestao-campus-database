/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.PreparedStatement;
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
import model.Curso;
import view.CursoView;

/**
 *
 * @author Usuario
 */
public class CursoDAO {

    public void create(Curso curso) throws Exception {
        String sql = "INSERT INTO curso(campus, nome_curso, estado, ano_inicio, ano_termino, cadastrado) VALUES (?,?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, curso.getId_campus());
            pstm.setString(2, curso.getNome());
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

    public List<String> read() throws Exception {
        String sql = "SELECT *, cam.nome FROM curso as c INNER JOIN campus AS cam ON c.campus = cam.id;";

        List<String> vetResult = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();;;

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                vetResult.add("Id: \t\t" + rset.getString(1) + "\n"
                        + "Nome do Curso: \t\t" + rset.getString(3) + "\n"
                        + "Campus: \t\t" + rset.getString(10) + "\n"
                        + "Estado: \t" + rset.getString(4) + "\n"
                        + "Ano de inicio: \t" + rset.getString(5) + "\n"
                        + "Ano de termino: \t\t" + rset.getString(6) + "\n"
                        + "Cadastrado: \t\t" + rset.getString(7) + "\n"
                        + "Modificado: \t\t" + rset.getString(8) + "\n"
                        + "----------------------------------------------------------");
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
        String sql = "SELECT *, cam.nome FROM curso as c INNER JOIN campus AS cam ON c.campus = cam.id;";

        List<String> vetResult = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();;;

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                vetResult.add("Id: \t\t" + rset.getString(1) + "\n"
                        + "Nome do Curso: \t\t" + rset.getString(3) + "\n"
                        + "Campus: \t\t" + rset.getString(10) + "\n"
                        + "----------------------------------------------------------");
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
        String sql = "UPDATE curso SET campus=?, nome_curso=?, estado=?, "
                + "ano_inicio=?, ano_termino=?, cadastrado=?, modificado=?"
                + "where id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, altCurso.getId_campus());
            pstm.setString(2, altCurso.getNome());
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
        String sql = "DELETE FROM curso WHERE id = ?";
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
        String sql = "SELECT * FROM curso WHERE id = '" + idCurso + "'";

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
                curso.setId_campus(rset.getInt("campus"));
                curso.setNome(rset.getString("nome_curso"));
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
