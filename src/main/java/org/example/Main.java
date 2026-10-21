package org.example;

import org.example.EmailBot;
import org.example.EmailConfig;
import org.example.EmailReader;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EmailConfig configLeitura = new EmailConfig("imap.gmail.com", 993,  System.getenv("GMAIL_USUARIO"), System.getenv("GMAIL_SENHA"));
        EmailReader leitor = new EmailReader(configLeitura);

        List<String> palavras = new ArrayList<>();
        palavras.add("black friday");
        palavras.add("pré-venda");

        EmailBot bot = new EmailBot(leitor, palavras);
        bot.executar();
    }
}