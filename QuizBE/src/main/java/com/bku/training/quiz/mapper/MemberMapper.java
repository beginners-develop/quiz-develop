package com.bku.training.quiz.mapper;

import com.bku.training.quiz.dto.RegisterDTO;
import com.bku.training.quiz.dto.MemberDTO;
import com.bku.training.quiz.dto.OwnerDTO;
import com.bku.training.quiz.entities.Member;
import com.bku.training.quiz.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Nam Tran
 * @project QuizBE
 **/
@Mapper(componentModel = "spring")
public interface MemberMapper {

    @Mapping(target = "roles", source = "roles")
    MemberDTO entityToDto(Member member);
    RegisterDTO entityToRegisterDTO(Member member);
    OwnerDTO entityToOwnerDTO(Member member);
    default String roleToString(Role role) {
        return role == null ? null : role.getRoleName();
    }

    @Mapping(target = "roles", source = "roles")
    Member dtoToEntity(MemberDTO memberDTO);
    Member registerDTOTOEntity(RegisterDTO registerDTO);
    Member ownerDTOToEntity(OwnerDTO ownerDTO);
    default Role stringToRole(String role) {
        return role == null ? null : new Role(role);
    }



}
