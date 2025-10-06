package calories_control.features.sendgrid;

import com.sendgrid.Response;

public interface IEmailService {
    public Response sendEmail(String to, String subject, String contentText);
}
