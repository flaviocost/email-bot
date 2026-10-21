package org.example;

import jakarta.mail.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EmailReader {
    private EmailConfig config;

    public EmailReader(EmailConfig config){
        this.config = config;
    }

    private static final int LIMITE = 50;

    public List<Email> buscarEmails(){
        List<Email> emails = new ArrayList<>();

        try {
            Store store = conectar();
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            int total = inbox.getMessageCount();
            int inicio = Math.max(1, total - LIMITE+ 1);

            if (total > 0) {
                for (Message mensagem : inbox.getMessages(inicio, total)) {
                    String remetente = "";
                    if (mensagem.getFrom() != null && mensagem.getFrom().length > 0) {
                        remetente = mensagem.getFrom()[0].toString();
                    }

                    String assunto = mensagem.getSubject();
                    if (assunto == null) {
                        assunto = "";
                    }

                    emails.add(new Email(mensagem.getMessageNumber(), remetente,
                            this.config.getUsuario(), assunto, ""));
                }
            }

            inbox.close(false);
            store.close();
        } catch (Exception e) {
            System.out.println("Erro ao ler emails: " + e.getMessage());
        }

        return emails;
    }

    private Store conectar() throws MessagingException {
        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");

        Session session = Session.getInstance(props);
        Store store = session.getStore("imaps");
        store.connect(this.config.getHost(), this.config.getPorta(),
                this.config.getUsuario(), this.config.getSenha());
        return store;
    }

    public void moverParaLixeira(List<Email> paraMover){
        if (paraMover.isEmpty()) {
            return;
        }

        try {
            Store store = conectar();
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);
            Folder lixeira = store.getFolder("[Gmail]/Lixeira");

            Message[] mensagens = new Message[paraMover.size()];
            for (int i = 0; i < paraMover.size(); i++) {
                mensagens[i] = inbox.getMessage(paraMover.get(i).getNumero());
            }

            inbox.copyMessages(mensagens, lixeira);
            inbox.setFlags(mensagens, new Flags(Flags.Flag.DELETED), true);
            inbox.expunge();

            inbox.close(false);
            store.close();
        } catch (Exception e) {
            System.out.println("Erro ao mover para a lixeira: " + e.getMessage());
        }
    }
}