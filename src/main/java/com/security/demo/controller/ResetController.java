package com.security.demo.controller;

import com.security.demo.model.dto.EmailDTO;
import com.security.demo.model.dto.ResetSenhaDTO;
import com.security.demo.service.ResetSenhaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reset")
@AllArgsConstructor
public class ResetController {

    private ResetSenhaService resetSenhaService;

//    public ResetController(ResetSenhaService resetSenhaService) {
//        this.resetSenhaService = resetSenhaService;
//    }

    @PostMapping("/solicitar/user")
    public String solicitarResetSenhaUser(@RequestBody EmailDTO email) {
        resetSenhaService.solicitarResetSenhaUser(email);
        return "Token enviado";
    }

    @PostMapping("/executar/user")
    public String resetSenhaUser(@RequestBody ResetSenhaDTO resetSenhaDTO) {
        resetSenhaService.resetSenhaUser(resetSenhaDTO.getCodigo(), resetSenhaDTO.getNovaSenha());
        return "Senha alterada";
    }

    @PostMapping("/solicitar/ong")
    public String solicitarResetSenhaOng(@RequestBody EmailDTO email) {
        resetSenhaService.solicitarResetSenhaOng(email);
        return "Token enviado";
    }

    @PostMapping("/executar/ong")
    public String resetSenhaOng(@RequestBody ResetSenhaDTO resetSenhaDTO) {
        resetSenhaService.resetSenhaOrgao(resetSenhaDTO.getCodigo(), resetSenhaDTO.getNovaSenha());
        return "Senha alterada";
    }
}
