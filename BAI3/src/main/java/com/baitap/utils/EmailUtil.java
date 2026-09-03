package com.baitap.utils;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailUtil {

    private static final String FROM_EMAIL = "EMAIL_CUA_BAN";
    private static final String APP_PASSWORD = "ipio aucd cprn grcp\r\n";

    public static void sendOTP(String toEmail, String otp) throws Exception {

        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        FROM_EMAIL,
                        APP_PASSWORD
                );
            }
        });

        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress(FROM_EMAIL));

        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(toEmail)
        );

        message.setSubject("Mã OTP kích hoạt tài khoản");

        message.setText(
                "Xin chào,\n\n"
                + "Mã OTP để kích hoạt tài khoản của bạn là: "
                + otp
                + "\n\n"
                + "Mã OTP có hiệu lực trong 5 phút.\n"
                + "Nếu bạn không thực hiện đăng ký, hãy bỏ qua email này."
        );

        Transport.send(message);
    }
}