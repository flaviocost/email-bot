package org.example;

import java.util.ArrayList;
import java.util.List;

public class EmailBot {
    private EmailReader leitor;
    private List<String> palavras;


    public EmailBot(EmailReader nomeDoLeitor,List<String> nomeDasPalavras){
        this.palavras = nomeDasPalavras;
        this.leitor = nomeDoLeitor;
    }

    public List<Email> filtrarPromocoes(List<Email> emails) {
        List<Email> promocoes = new ArrayList<>();

        for(Email e : emails){
            for (String palavra : this.palavras){
                if(e.contemPalavraChave(palavra)){
                    promocoes.add(e);
                    break;
                }
            }
        }
        return  promocoes;
    }

    public void executar() {
        List<Email> emails = this.leitor.buscarEmails();
        List<Email> promocoes = filtrarPromocoes(emails);

        System.out.println("Promoções encontradas: " + promocoes.size());
        for (Email e : promocoes) {
            System.out.println(e.getRemetente() + " | " + e.getAssunto());
        }
        //this.leitor.moverParaLixeira(promocoes);
    }
}
