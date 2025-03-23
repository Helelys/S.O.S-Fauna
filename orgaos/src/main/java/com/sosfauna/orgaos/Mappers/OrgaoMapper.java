package com.sosfauna.orgaos.Mappers;

import com.sosfauna.orgaos.model.dto.OrgaoDto;
import com.sosfauna.orgaos.model.entity.Orgao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrgaoMapper {
    Orgao toOrgao(OrgaoDto orgaoDto);
    OrgaoDto toOrgaoDto(Orgao orgao);

}
