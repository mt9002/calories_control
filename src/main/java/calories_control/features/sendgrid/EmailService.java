package calories_control.features.sendgrid;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {

    // private final SendGridProperties properties;

    public EmailService() {

    }

    /**
     * to: Dirección de correo del destinatario
     * subject: Asunto del correo
     * contentText: Contenido del correo (texto plano o HTML)
     */

    @Override
    public Response sendEmail(String to, String subject, String contentText) {
        Request request = new Request();
        // Client sendgrid
        // SendGrid sg = new SendGrid(properties.getApiKey());
        Response response = new Response();
        try {
            Email from = new Email("liammtdev@gmail.com");
            Email toEmail = new Email(to);

            Content content = new Content("text/plain", contentText);

            Mail mail = new Mail(from, subject, toEmail, content);

            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            // response = sg.api(request);
        } catch (Exception e) {

        }

        return response;
    }
}
