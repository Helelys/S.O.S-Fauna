package com.sosfauna.orgaos.controller;

import com.sosfauna.orgaos.Service.OrgaoService;
import com.sosfauna.orgaos.model.dto.OrgaoDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("orgao")
public class OrgaoController {


    private final OrgaoService orgaoService;


    @GetMapping("findAll")
    public ResponseEntity<List<OrgaoDto>> buscarTodos() {
        return ResponseEntity.ok().body(orgaoService.buscarTodos());
    }

    @GetMapping("findByCnpj/{cnpj}")
    public ResponseEntity<OrgaoDto> buscarPorCnpj(@PathVariable String cnpj) {
        return ResponseEntity.ok().body(orgaoService.buscarOrgaoPorCnpj(cnpj));

    }

    @PutMapping("updateById/{id}")
    public ResponseEntity<OrgaoDto> atualizarOrgao(@PathVariable int id, @RequestBody @Valid OrgaoDto dto ) {
        return ResponseEntity.status(HttpStatus.OK).body(orgaoService.atualizarOrgao(id,dto));
    }

    @PostMapping("/create")
    public ResponseEntity<OrgaoDto> cadastrar(@Valid @RequestBody OrgaoDto dto) {

        OrgaoDto orgao = orgaoService.CadastrarOrgao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(orgao);
    }

    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<Void> deletarOrgao(@PathVariable Integer id) {
        orgaoService.deletarOrgao(id);
        return ResponseEntity.noContent().build();
    }

}
