package com.sos_fauna.denuncias.Services;

import com.sos_fauna.denuncias.dto.DenunciaDto;
import com.sos_fauna.denuncias.model.Denuncia;
import com.sos_fauna.denuncias.model.StatusDenuncia;
import com.sos_fauna.denuncias.repository.DenunciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class DenunciasService {

    private final DenunciaRepository denunciaRepository;
    private final ImageAcessService imageAcessService;

    @Autowired
    public DenunciasService(DenunciaRepository denunciaRepository, ImageAcessService imageAcessService) {
        this.denunciaRepository = denunciaRepository;
        this.imageAcessService = imageAcessService;
    }

    // Lembrete:(Alterei aqui)
    public Denuncia criarDenuncia(Denuncia denuncia) {
        return denunciaRepository.save(denuncia);
    }


    public DenunciaDto buscarDenunciaPorId(Integer id) {
        Denuncia denuncia = denunciaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Denúncia não encontrada com o ID: " + id));


        String imagemUrl = imageAcessService.generateSignedUrl(denuncia.getPublicId());


        return new DenunciaDto(
                denuncia.getId(),
                denuncia.getAnimal(),
                denuncia.getDenunciado(),
                denuncia.getDescricao(),
                denuncia.getDataOcorrido(),
                denuncia.getHoraOcorrido(),
                denuncia.getBairro(),
                denuncia.getRua(),
                denuncia.getStatusDenuncia(),
                denuncia.getDataCriacao(),
                imagemUrl
        );
    }


    public List<DenunciaDto> buscarTodasDenuncias() {
        return denunciaRepository.findAll()
                .stream()
                .map(this::converterParaDto)
                .collect(Collectors.toList());
    }


    public Denuncia atualizarDenuncia(Integer id, Denuncia denunciaAtualizada) {
        return denunciaRepository.findById(id)
                .map(denuncia -> {
                    denuncia.setAnimal(denunciaAtualizada.getAnimal());
                    denuncia.setDenunciado(denunciaAtualizada.getDenunciado());
                    denuncia.setDescricao(denunciaAtualizada.getDescricao());
                    denuncia.setDataOcorrido(denunciaAtualizada.getDataOcorrido());
                    denuncia.setHoraOcorrido(denunciaAtualizada.getHoraOcorrido());
                    denuncia.setBairro(denunciaAtualizada.getBairro());
                    denuncia.setNumero(denunciaAtualizada.getNumero());
                    denuncia.setRua(denunciaAtualizada.getRua());
                    denuncia.setCep(denunciaAtualizada.getCep());
                    denuncia.setStatusDenuncia(denunciaAtualizada.getStatusDenuncia());
                    return denunciaRepository.save(denuncia);
                })
                .orElseThrow(() -> new EntityNotFoundException("Denúncia não encontrada com o ID: " + id));
    }


    public void deletarDenuncia(Integer id) {
        if (!denunciaRepository.existsById(id)) {
            throw new EntityNotFoundException("Denúncia não encontrada com o ID: " + id);
        }
        denunciaRepository.deleteById(id);
    }


    private DenunciaDto converterParaDto(Denuncia denuncia) {
        String imagemUrl = (denuncia.getPublicId() != null)
                ? imageAcessService.generateSignedUrl(denuncia.getPublicId())
                : null;

        return new DenunciaDto(
                denuncia.getId(),
                denuncia.getAnimal(),
                denuncia.getDenunciado(),
                denuncia.getDescricao(),
                denuncia.getDataOcorrido(),
                denuncia.getHoraOcorrido(),
                denuncia.getBairro(),
                denuncia.getRua(),
                denuncia.getStatusDenuncia(),
                denuncia.getDataCriacao(),
                imagemUrl
        );
    }



}
