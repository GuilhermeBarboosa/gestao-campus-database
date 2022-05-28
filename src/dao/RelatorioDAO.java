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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class RelatorioDAO {

    public List<String> relatorioData(LocalDate dtIncial, LocalDate dtFinal) throws SQLException {
        String sql = "SELECT *, s.nome, c.comissao FROM reunioes AS r INNER JOIN servidores AS s ON r.servidor_secre = s.id INNER JOIN comissoes AS c ON r.comissao = c.id WHERE r.dt_reuniao BETWEEN ? AND ?;";

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        List<String> vetResult = new ArrayList<>();
        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            Date date = Date.valueOf(dtIncial);

            pstm.setDate(1, date);
            date = Date.valueOf(dtFinal);

            pstm.setDate(2, date);
            pstm.execute();

            rset = pstm.executeQuery();

            while (rset.next()) {

                vetResult.add("=========================\n"
                        + "Id: " + rset.getString("r.id") + "\n"
                        + "Comissao: " + rset.getString("c.comissao") + "\n"
                        + "Servidor: " + rset.getString("s.nome") + "\n"
                        + "Conteudo da ata: " + rset.getString("r.conteudo_ata") + "\n"
                        + "Data da reuniao: " + rset.getString("r.dt_reuniao") + "\n"
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

    public List<String> relatorioCompleto() throws SQLException {

        String sql = "SELECT * FROM servidores AS s "
                + "INNER JOIN ofertas AS of ON s.id = of.servidor "
                + "INNER JOIN disciplinas AS d ON d.id = of.disciplina "
                + "INNER JOIN atividades AS ati ON s.id = ati.servidor "
                + "INNER JOIN orientacoes AS ori ON s.id = ori.servidor "
                + "INNER JOIN vinculos AS vinc ON s.id = vinc.servidor "
                + "INNER JOIN comissoes AS com ON com.id = vinc.comissao GROUP BY s.id";

        List<String> vetResult = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {
      
                vetResult.add("Id: " + rset.getString("s.id") + "\n"
                        + "Nome: " + rset.getString("s.nome") + "\n"
                        + "Disciplina: " + rset.getString("d.disciplina") + " - " + rset.getString("d.carga_horaria") + " horas" + "\n"
                        + "Atividade: " + rset.getString("ati.atividade") + " - " + rset.getString("ati.horas_semanais") + " horas" + "\n"
                        + "Orientação: " + rset.getString("ori.tipo") + " - " + rset.getString("ori.horas_semanais") + " horas" + "\n"
                        + "Comissao: " + rset.getString("com.comissao") + " - " + rset.getString("com.horas_semanais") + " horas" + "\n");

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

    public List<String> relatorioAulas(int idCampus) throws SQLException {
        String sql = "SELECT * FROM ofertas AS of INNER JOIN cursos AS c ON of.curso = c.id INNER JOIN disciplinas AS d ON of.disciplina = d.id "
                + "INNER JOIN servidores AS s ON of.servidor = s.id INNER JOIN campus AS camp ON c.campus = camp.id WHERE camp.id = '" + idCampus + "'";

        List<String> vetResult = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                vetResult.add("Campus: " + rset.getString("camp.nome") + "\n"
                        + "Curso: " + rset.getString("c.curso") + "\n"
                        + "Disciplina: " + rset.getString("d.disciplina") + "\n"
                        + "Servidor: " + rset.getString("s.nome") + "\n"
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
}
