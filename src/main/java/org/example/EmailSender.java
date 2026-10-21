package org.example;
import java.util.Properties;
import jakarta.mail.Session;
import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.Message;
import jakarta.mail.Transport;

public class EmailSender {
    private EmailConfig config;


    public  EmailSender(EmailConfig config){

        this.config = config;
    }

    public void enviar(Email email){

        try {
        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", this.config.getHost());
        props.put("mail.smtp.port", String.valueOf(this.config.getPorta()));

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(config.getUsuario(), config.getSenha());
            }
        });

        MimeMessage mensagem = new MimeMessage(session);
        mensagem.setFrom(new InternetAddress(email.getRemetente()));
        mensagem.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getDestinatario()));
        mensagem.setSubject(email.getAssunto());
        mensagem.setText(email.getCorpo());
        Transport.send(mensagem);
        }catch (Exception e) {
            System.out.println("Erro ao enviar email: " + e.getMessage());
        }
    }
}
