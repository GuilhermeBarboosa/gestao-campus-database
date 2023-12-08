package service;

import model.Servidor;

import java.util.List;

public class LoginService {

    ServidorService servidorService = new ServidorService();

    public Servidor login(String login, String senha) throws Exception {
        ;
        Servidor responseLogin = null;

        List<Servidor> vetServidor = servidorService.read();

        for (Servidor servidor : vetServidor) {
            if (servidor.getLogin().equals(login) && servidor.getSenha().equals(senha)) {
                responseLogin = servidor;
                return responseLogin;
            }
        }

        return null;
    }
}
