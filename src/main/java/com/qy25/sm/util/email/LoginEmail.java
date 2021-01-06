package com.qy25.sm.util.email;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
@Setter
@ConfigurationProperties(prefix = "spring.mail")  //需要setter方法才能生效
@Async
public class LoginEmail {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private FreeMarkerConfigurer markerConfigurer;
    private String from;

    /**
     * 发送验证码
     * @param email
     */
    public void sendCodeSimpleEmail(String email){
        int i = (int) (Math.random()*(8999+1)+1000);
        redisTemplate.opsForValue().set(email, String.valueOf(i),5, TimeUnit.SECONDS);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("验证码");
        simpleMailMessage.setText(String.valueOf(i));
    }

    /**
     * 发送激活码
     */
    public void sendActiveEmail(String email) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("激活码");
        String s = getTemplate(email);
        mimeMessageHelper.setText(s,true);
        mailSender.send(mimeMessage);
    }

    /**
     * 发送附件
     * @param email
     * @param fileName
     * @param file
     * @throws MessagingException
     */
    public void sendFileEmail(String email, String fileName, File file) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("文件");
//        mimeMessageHelper.setText();
        mimeMessageHelper.addAttachment(fileName,file);
        mailSender.send(mimeMessage);
    }

    /**
     * 发送内联文件
     * @param email
     * @param file
     * @throws MessagingException
     */
    public void sendInLineEmail(String email,File file) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("图片");
        mimeMessageHelper.setText("<img src='cid:images'>",true);
        mimeMessageHelper.addInline("images",file);
        mailSender.send(mimeMessage);
    }


    /**
     * freemarker激活模板
     * @param email
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    private String getTemplate(String email) throws IOException, TemplateException {
        //模板
        Map map = new HashMap<>();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        redisTemplate.opsForValue().set(uuid,email,5,TimeUnit.SECONDS);
        String activeLink = "http://localhost:8080/active?uuid="+uuid;
        map.put("userEmail",email);
        map.put("activeLink", activeLink);
        Configuration configuration = markerConfigurer.getConfiguration();
        Template template = configuration.getTemplate("activeEmail.ftl","UTF-8");
        StringWriter stringWriter = new StringWriter();
        template.process(map,stringWriter);
        stringWriter.flush();
        String s = stringWriter.toString();
        return s;
    }


//    private void setEmailSender(String to,String subject) throws MessagingException, IOException, TemplateException {
//        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
//        mimeMessageHelper.setFrom(from);
//        mimeMessageHelper.setTo(to);
//        switch (subject){
//            case "激活码":
//                mimeMessageHelper.setSubject("激活码");
//                String s = getTemplate(to);
//                mimeMessageHelper.setText(s,true);
//                break;
//            case "验证码":
//                int i = (int) (Math.random()*(8999+1)+1000);
//                redisTemplate.opsForValue().set(to, String.valueOf(i),5, TimeUnit.SECONDS);
//                mimeMessageHelper.setSubject("验证码");
//                mimeMessageHelper.setText(String.valueOf(i));
//        }
//        mailSender.send(mimeMessage);
//    }

}
