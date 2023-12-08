/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import factory.ConnectionFactory;
import model.Curso;
import model.Disciplina;
import model.Oferta;
import model.Servidor;
import service.CursoService;
import service.DisciplinaService;
import service.ServidorService;

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
public class OfertaDAO {

    private final ServidorService servidorService = new ServidorService();
    private final CursoService cursoService = new CursoService();
    private final DisciplinaService disciplinaService = new DisciplinaService();

    public void create(Oferta oferta) throws Exception {
        String sql = "INSERT INTO ofertas (curso, disciplina, servidor, ano, semestre, aula_semanal, cadastrado) VALUES (?,?,?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, oferta.getCurso().getId());
            pstm.setInt(2, oferta.getDisciplina().getId());
            pstm.setInt(3, oferta.getServidor().getId());
            pstm.setInt(4, oferta.getAno());
            pstm.setInt(5, oferta.getSemestre());
            pstm.setInt(6, oferta.getAulaSemanais());

            Date date = Date.valueOf(oferta.getDtCriacao());
            pstm.setDate(7, date);

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

    public List<Oferta> read() throws Exception {

        String sql = "SELECT *, s.nome, d.disciplina, c.curso FROM ofertas AS o INNER JOIN servidores AS s ON o.servidor = s.id "
                + "INNER JOIN disciplinas AS d ON o.disciplina = d.id INNER JOIN cursos AS c ON o.curso = c.id;";

        List<Oferta> vetResult = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                Oferta oferta = new Oferta();

                oferta.setId(rset.getInt("o.id"));

                Servidor servidor = servidorService.getById(rset.getInt("o.servidor"));
                oferta.setServidor(servidor);

                Disciplina disciplina = disciplinaService.getById(rset.getInt("o.disciplina"));
                oferta.setDisciplina(disciplina);

                Curso curso = cursoService.getById(rset.getInt("o.curso"));
                oferta.setCurso(curso);

                oferta.setAno(rset.getInt("o.ano"));
                oferta.setSemestre(rset.getInt("o.semestre"));
                oferta.setAulaSemanais(rset.getInt("o.aula_semanal"));

                Date date = rset.getDate("cadastrado");
                LocalDate dataAtualizada = date.toLocalDate();
                oferta.setDtCriacao(dataAtualizada);

                date = rset.getDate("cadastrado");
                if (dataAtualizada != null) {
                    dataAtualizada = date.toLocalDate();
                    oferta.setDtMoficacao(dataAtualizada);
                }

                vetResult.add(oferta);
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

    public void update(Oferta altOferta) throws Exception {
        ;
        String sql = "UPDATE ofertas SET curso=?, disciplina=?, servidor=?, "
                + "ano=?, semestre=?, aula_semanal=?, modificado=?"
                + "where id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, altOferta.getCurso().getId());
            pstm.setInt(2, altOferta.getDisciplina().getId());
            pstm.setInt(3, altOferta.getServidor().getId());
            pstm.setInt(4, altOferta.getAno());
            pstm.setInt(5, altOferta.getSemestre());
            pstm.setInt(6, altOferta.getAulaSemanais());

            Date date = Date.valueOf(altOferta.getDtMoficacao());
            pstm.setDate(7, date);

            pstm.setInt(8, altOferta.getId());

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
        String sql = "DELETE FROM ofertas WHERE id = ?";
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

    public Oferta getById(int id) throws Exception {
        String sql = "SELECT * FROM ofertas WHERE id = '" + id + "'";

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                Oferta oferta = new Oferta();

                oferta.setId(rset.getInt("id"));
                Servidor servidor = servidorService.getById(rset.getInt("servidor"));
                oferta.setServidor(servidor);

                Disciplina disciplina = disciplinaService.getById(rset.getInt("disciplina"));
                oferta.setDisciplina(disciplina);

                Curso curso = cursoService.getById(rset.getInt("curso"));
                oferta.setCurso(curso);
                oferta.setAno(rset.getInt("ano"));
                oferta.setSemestre(rset.getInt("semestre"));
                oferta.setAulaSemanais(rset.getInt("aula_semanal"));

                Date date = rset.getDate("cadastrado");
                LocalDate dataAtualizada = date.toLocalDate();
                oferta.setDtCriacao(dataAtualizada);

                date = rset.getDate("modificado");
                if (date != null) {
                    dataAtualizada = date.toLocalDate();
                    oferta.setDtMoficacao(dataAtualizada);
                }
                return oferta;

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
