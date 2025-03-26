package com.security.demo.service;

import com.security.demo.model.dto.EmailDTO;
import com.security.demo.model.entity.Ong;
import com.security.demo.model.entity.ResetSenhaOng;
import com.security.demo.model.entity.ResetSenhaUser;
import com.security.demo.model.entity.User;
import com.security.demo.repository.OngRepository;
import com.security.demo.repository.ResetSenhaRepositoryOng;
import com.security.demo.repository.ResetSenhaRepositoryUser;
import com.security.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ResetSenhaService {

    private static final Logger logger = LoggerFactory.getLogger(ResetSenhaService.class);

    private static final Logger log = LoggerFactory.getLogger(ResetSenhaService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OngRepository ongRepository;

    @Autowired
    private ResetSenhaRepositoryUser resetSenhaRepositoryUser;

    @Autowired
    private ResetSenhaRepositoryOng resetSenhaRepositoryOng;

    @Autowired
    private PasswordEncoder passwordEncoder;


    //    @Autowired
//    private JavaMailSender javaMailSender;

    @Transactional
    public void solicitarResetSenhaUser(EmailDTO email) {
        User user = userRepository.findByEmail(email.getEmail()).orElse(null);
        if (user != null) {
            String codigo = UUID.randomUUID().toString();
            ResetSenhaUser resetSenhaUser = new ResetSenhaUser();
            resetSenhaUser.setUser(user);
            resetSenhaUser.setCodigoUser(codigo);
            resetSenhaUser.setExpirationUser(LocalDateTime.now().plusHours(1));
            resetSenhaRepositoryUser.save(resetSenhaUser);

            logger.info("Seu código: " + resetSenhaUser.getCodigoUser());
//            enviarEmailResetSenha(user.getEmail(), codigo);
        }
    }

    @Transactional
    public void solicitarResetSenhaOng(EmailDTO email) {
        Ong ong = ongRepository.findByEmail(email.getEmail()).orElse(null);
        if (ong != null) {
            String codigo = UUID.randomUUID().toString();
            ResetSenhaOng resetSenhaOng = new ResetSenhaOng();
            resetSenhaOng.setOng(ong);
            resetSenhaOng.setCodigoOng(codigo);
            resetSenhaOng.setExpirationOng(LocalDateTime.now().plusHours(1));
            resetSenhaRepositoryOng.save(resetSenhaOng);

            logger.info("Seu código: " + resetSenhaOng.getCodigoOng());
//            enviarEmailResetSenha(user.getEmail(), codigo);
        }
    }

    public void resetSenhaUser(String codigo, String novaSenha) {
        ResetSenhaUser resetSenhaUser = resetSenhaRepositoryUser.findByCodigoUser(codigo).orElse(null);
        if (resetSenhaUser != null && resetSenhaUser.getExpirationUser().isAfter(LocalDateTime.now())) {
            User usuario = resetSenhaUser.getUser();
            usuario.setSenha(passwordEncoder.encode(novaSenha));
            userRepository.save(usuario);
            resetSenhaRepositoryUser.delete(resetSenhaUser);
        }
    }

    public void resetSenhaOrgao(String codigo, String novaSenha) {
        ResetSenhaOng resetSenhaOng = resetSenhaRepositoryOng.findByCodigoOng(codigo).orElse(null);
        if (resetSenhaOng != null && resetSenhaOng.getExpirationOng().isAfter(LocalDateTime.now())) {
            Ong ong = resetSenhaOng.getOng();
            ong.setSenha(passwordEncoder.encode(novaSenha));
            ongRepository.save(ong);
            resetSenhaRepositoryOng.delete(resetSenhaOng);
        }
    }

//    private void enviarEmailResetSenha(String email, String codigo){
//        SimpleMailMessage mensagem = new SimpleMailMessage();
//        mensagem.setTo(email);
//        mensagem.setSubject("Redefinição de Senha");
//        mensagem.setText("Seu código de redefinição de senha é: " + codigo);
//        javaMailSender.send(mensagem);
//    }
}
