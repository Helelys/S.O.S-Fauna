package com.sosfauna.orgaos.Service;

import com.sosfauna.orgaos.Mappers.OrgaoMapper;
import com.sosfauna.orgaos.model.dto.OrgaoDto;
import com.sosfauna.orgaos.model.entity.Orgao;
import com.sosfauna.orgaos.repository.OrgaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrgaoService {
    private final OrgaoRepository orgaoRepository;
    private final OrgaoMapper orgaoMapper;

    public OrgaoDto CadastrarOrgao(OrgaoDto dto) {
        orgaoRepository.findByCnpj(dto.cnpj()).ifPresent(orgao -> {
            throw new IllegalArgumentException("CNPJ já cadastrado");
        });
        Orgao orgao = orgaoMapper.toOrgao(dto);
        return orgaoMapper.toOrgaoDto(orgaoRepository.save(orgao));

    }

    public List<OrgaoDto> buscarTodos(){
        List<Orgao> list = orgaoRepository.findAll();
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Não existe nenhum orgao");
        }
        return list.stream().map(orgaoMapper::toOrgaoDto).collect(Collectors.toList());
    }

    public OrgaoDto buscarOrgaoPorCnpj(String cnpj) {
        Orgao cnpjExistente = orgaoRepository.findByCnpj(cnpj).orElseThrow(() -> new IllegalArgumentException("Orgão não encontrado."));
        return orgaoMapper.toOrgaoDto(cnpjExistente);

    }


    public OrgaoDto atualizarOrgao(int id, OrgaoDto dto){
        Orgao orgaoExistente = orgaoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Orgão não encontrado."));
        Orgao orgao = orgaoMapper.toOrgao(dto);
        orgao.setId(orgaoExistente.getId());
        return orgaoMapper.toOrgaoDto(orgaoRepository.save(orgao));
    }
    public void deletarOrgao(Integer id) {
        orgaoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Orgão não encontrado."));
        orgaoRepository.deleteById(id);
    }

}






