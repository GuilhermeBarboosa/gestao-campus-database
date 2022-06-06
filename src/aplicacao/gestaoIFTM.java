/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao;

import view.Gui;

import model.Servidor;
import model.Default;

public class gestaoIFTM implements Default {

    int opcCrud;
    boolean sair = false;
    int auxLoc;
    int locServ;
    Servidor servidorLogin;
    Gui GUI = new Gui();

    public static void main(String[] args) throws Exception {
        new gestaoIFTM();
    }

    private gestaoIFTM() throws Exception {
        principal();
    }

    private void principal() throws Exception {
//        try {
//            do {
//                String[] loginSenha = GUI.login();
//                servidorLogin = login(loginSenha[0], loginSenha[1]);
//                if (servidorLogin != null) {
//                    if (servidorLogin.getPerfil() == 1) {
//                        sistemaAdm();
//                    } else {
//                        sistemaComum();
//                    }
//                } else {
//                    System.err.println("Usuario não encontrado");
//                }
//            } while (servidorLogin == null);
//        } catch (Exception e) {
//            System.err.println(e);
//        }

        //RETIRAR ISSO
        sistemaAdm();
        //----------
    }

    public void sistemaComum() throws Exception {
        userComController.menu();
        principal();
    }

    public void sistemaAdm() throws Exception {
        int opc = 0;
        do {
            opc = GUI.escolherCRUD();
            switch (opc) {
                case 1:
                    campusController.menu();
                    break;
                case 2:
                    servidorController.menu();
                    break;
                case 3:
                    cursoController.menu();
                    break;
                case 4:
                    disciplinaController.menu();
                    break;
                case 5:
                    ofertaDisciplinaController.menu();
                    break;
                case 6:
                    orientacaoController.menu();
                    break;
                case 7:
                    atividadeController.menu();
                    break;
                case 8:
                    comissaoController.menu();
                    break;
                case 9:
                    vinculoServiComi.menu();
                    break;
                case 10:
                    reuniaoController.menu();
                    break;
                case 11:
                    reuniaoPresenteController.menu();
                    break;
                case 12:
                    relatorioController.gerarRelat();
                    break;
                case 13:
                    encerrarComissaoController.menu();
                    break;
                default:
                    System.out.println("SAINDO DO MENU...");
            }
        } while (opc != 14);
        principal();
    }

//    public Servidor login(String login, String senha) throws SQLException, Exception {
//        Servidor responseLogin = new Servidor();
//
//        List<Servidor> vetServidor = servidorDAO.read();
//
//   
//        for (Servidor servidor : vetServidor) {
//            if (servidor.getLogin().equals(login) && servidor.getSenha().equals(senha)) {
//                responseLogin = servidor;
//                return responseLogin;
//            }
//        }
//
//        return responseLogin;
//    }
    
    
}
