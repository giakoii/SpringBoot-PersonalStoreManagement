package project.personal.personalstoremanagementproject.controllers.v1.registerscreen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import project.personal.personalstoremanagementproject.entities.UserAccount;
import project.personal.personalstoremanagementproject.repositories.EmailTemplateRepository;
import project.personal.personalstoremanagementproject.utils.StringUtil;

@Component
public class RegisterScreenSendMailResult {

    private final EmailTemplateRepository emailTemplateRepository;

    private final JavaMailSender javaMailSender;

    private final StringUtil stringUtil;

    @Value("${frontend.url[0]}")
    private String url;

    @Value("${spring.mail.username}")
    private String mailFrom;

    public RegisterScreenSendMailResult(EmailTemplateRepository emailTemplateRepository, JavaMailSender javaMailSender, StringUtil stringUtil) {
        this.emailTemplateRepository = emailTemplateRepository;
        this.javaMailSender = javaMailSender;
        this.stringUtil = stringUtil;
    }

    /**
     * Send mail to user
     * @param screenName
     * @param user
     * @param <T>
     * @throws Exception
     */
    public <T> void sendMail(String screenName, UserAccount user) throws Exception {
        var userName = user.getUsername();
        // Find email template by screen name

        var emailTemplate = emailTemplateRepository.findByScreenName(screenName);
        var verificationLink = url + "/verify?token=" + stringUtil.encrypt(user.getUserId(), userName);

        // If email template is present
        if (emailTemplate.isPresent()) {
            // Replace placeholders in email template
            String bodyTemplate = emailTemplate.get().getMailBody();
            bodyTemplate = bodyTemplate.replace("${userName}", userName);
            bodyTemplate = bodyTemplate.replace("${verificationLink}", verificationLink);

            // Send mail
            var message = javaMailSender.createMimeMessage();
            var helper = new MimeMessageHelper(message);
            helper.setTo(user.getEmail());
            helper.setFrom(mailFrom);
            helper.setSubject(emailTemplate.get().getMailTitle());
            helper.setText(bodyTemplate);

            javaMailSender.send(message);
        }
    }
}
