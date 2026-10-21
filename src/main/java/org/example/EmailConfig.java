package org.example;

public class EmailConfig {
    private String host;
    private int porta;
    private String usuario;
    private  String senha;

    public EmailConfig(){
    }
    public EmailConfig(String host, int porta, String usuario, String senha){
        this.host = host;
        this.porta = porta;
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getHost(){
        return host;
    }

    public int getPorta(){
        return porta;
    }
    public String getUsuario(){
        return usuario;
    }

    public String getSenha() {
        return senha;
    }
}
