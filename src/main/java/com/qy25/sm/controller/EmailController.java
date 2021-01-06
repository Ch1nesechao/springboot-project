//package com.qy25.sm.controller;
//
//import com.qy25.sm.common.http.AjaxResult;
//import com.qy25.sm.util.email.LoginEmail;
//import freemarker.template.TemplateException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.mail.MessagingException;
//import java.io.IOException;
//
//@RestController
//@RequestMapping("email")
//public class EmailController {
//
//    @Autowired
//    private LoginEmail loginEmail;
//
//    @GetMapping
//    public AjaxResult<Void> sendSimple(@RequestParam String email){
//        try {
//            loginEmail.sendActiveEmail(email);
//            return AjaxResult.success();
//        } catch (MessagingException | IOException | TemplateException e) {
//            return AjaxResult.error();
//        }
//    }
//}
