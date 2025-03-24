package com.sos_fauna.denuncias.controller;
import com.sos_fauna.denuncias.Services.CloudinaryService;
import com.sos_fauna.denuncias.Services.DenunciasService;
import com.sos_fauna.denuncias.Services.ImageAcessService;
import com.sos_fauna.denuncias.dto.DenunciaDto;
import com.sos_fauna.denuncias.model.Denuncia;
import com.sos_fauna.denuncias.model.StatusDenuncia;
import com.sos_fauna.denuncias.repository.DenunciaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/denuncias")
public class DenunciasController {
    private final DenunciasService denunciasService;
    private final CloudinaryService cloudinaryService;
    private final ImageAcessService imageAcessService;
    private final DenunciaRepository denunciaRepository;

    public DenunciasController(DenunciasService denunciasService, CloudinaryService cloudinaryService, ImageAcessService imageAcessService, DenunciaRepository denunciaRepository) {
        this.denunciasService = denunciasService;
        this.cloudinaryService = cloudinaryService;
        this.imageAcessService = imageAcessService;
        this.denunciaRepository = denunciaRepository;
    }


    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<DenunciaDto> criarDenuncia(
            @RequestParam("animal") String animal,
            @RequestParam("denunciado") String denunciado,
            @RequestParam("descricao") String descricao,
            @RequestParam("dataOcorrido") String dataOcorrido,
            @RequestParam("horaOcorrido") String horaOcorrido,
            @RequestParam("bairro") String bairro,
            @RequestParam("rua") String rua,
            @RequestParam("statusDenuncia") String statusDenuncia,
            @RequestParam(value = "imagem", required = false) MultipartFile imagem
    ) {
        try {

            Denuncia denuncia = new Denuncia();
            denuncia.setAnimal(animal);
            denuncia.setDenunciado(denunciado);
            denuncia.setDescricao(descricao);
            denuncia.setDataOcorrido(java.time.LocalDate.parse(dataOcorrido));
            denuncia.setHoraOcorrido(java.time.LocalTime.parse(horaOcorrido));
            denuncia.setBairro(bairro);
            denuncia.setRua(rua);
            denuncia.setStatusDenuncia(com.sos_fauna.denuncias.model.StatusDenuncia.valueOf(statusDenuncia));


            if (imagem != null && !imagem.isEmpty()) {
                String publicId = cloudinaryService.uploadImage(imagem);
                denuncia.setPublicId(publicId);
            }


            Denuncia novaDenuncia = denunciasService.criarDenuncia(denuncia);


            DenunciaDto dto = new DenunciaDto(
                    novaDenuncia.getId(),
                    novaDenuncia.getAnimal(),
                    novaDenuncia.getDenunciado(),
                    novaDenuncia.getDescricao(),
                    novaDenuncia.getDataOcorrido(),
                    novaDenuncia.getHoraOcorrido(),
                    novaDenuncia.getBairro(),
                    novaDenuncia.getRua(),
                    novaDenuncia.getStatusDenuncia(),
                    novaDenuncia.getDataCriacao(),
                    novaDenuncia.getPublicId()
            );

            return ResponseEntity.ok(dto);

        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }


    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping
    public ResponseEntity<List<DenunciaDto>> buscarTodasDenuncias() {
        List<DenunciaDto> denuncias = denunciasService.buscarTodasDenuncias().stream().map(denuncia -> {
            // Se houver publicId, gera a URL assinada
            String signedUrl = (denuncia.getPublicId() != null) ? imageAcessService.generateSignedUrl(denuncia.getPublicId()) : null;

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
                    signedUrl
            );
        }).collect(Collectors.toList());

        return ResponseEntity.ok(denuncias);
    }



    @GetMapping("/{id}")
    public ResponseEntity<DenunciaDto> buscarDenunciaPorId(@PathVariable Integer id) {
        try {
            DenunciaDto denunciaDto = denunciasService.buscarDenunciaPorId(id);


            if (denunciaDto.getPublicId() != null) {
                String signedUrl = imageAcessService.generateSignedUrl(denunciaDto.getPublicId());
                denunciaDto.setPublicId(signedUrl);
            }

            return ResponseEntity.ok(denunciaDto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<DenunciaDto> atualizarDenuncia(@PathVariable Integer id, @RequestBody Denuncia denunciaAtualizada) {
        try {
            Denuncia denuncia = denunciasService.atualizarDenuncia(id, denunciaAtualizada);


            String imagemUrl = (denuncia.getPublicId() != null) ? imageAcessService.generateSignedUrl(denuncia.getPublicId()) : null;


            DenunciaDto dto = new DenunciaDto(
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

            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}/status")
    public ResponseEntity<Denuncia> atualizarStatus(@PathVariable Integer id, @RequestBody DenunciaDto dto) {
        Optional<Denuncia> denunciaOpt = denunciaRepository.findById(id);

        if (denunciaOpt.isPresent()) {
            Denuncia denuncia = denunciaOpt.get();
            denuncia.setStatusDenuncia(dto.getStatusDenuncia());
            denunciaRepository.save(denuncia);
            return ResponseEntity.ok(denuncia);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDenuncia(@PathVariable Integer id) {
        try {
            denunciasService.deletarDenuncia(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
