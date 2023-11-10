package com.bku.training.quizfe.service.impl;

import com.bku.training.quizfe.entities.DeactivateDTO;
import com.bku.training.quizfe.entities.LoginResponse;
import com.bku.training.quizfe.entities.MemberDTO;
import com.bku.training.quizfe.entities.PasswordDTO;
import com.bku.training.quizfe.service.MemberService;
import com.bku.training.quizfe.utils.HeaderUtils;
import com.bku.training.quizfe.utils.ServerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * @author Nam Tran
* @project Quiz
 **/
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Update information of member
     */
    @Override
    public MemberDTO updateMember(String api, MemberDTO inputMemberDTO, LoginResponse response) {
        HttpEntity<MemberDTO> entity = HeaderUtils.getHeader(inputMemberDTO, response);
        ResponseEntity<MemberDTO> responseEntity = restTemplate.exchange(
                ServerUtils.DOMAIN + api, HttpMethod.PUT, entity, MemberDTO.class);
        return responseEntity.getBody();

    }

    /**
     * updatePassword of user's
     */
    @Override
    public String updatePassword(String api, PasswordDTO passwordDTO, LoginResponse response) {
        HttpEntity<PasswordDTO> entity = HeaderUtils.getHeader(passwordDTO, response);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                ServerUtils.DOMAIN + api, HttpMethod.PUT, entity, String.class);
        return responseEntity.getBody();
    }

    /**
     * deactivate account of member
     */
    @Override
    public String deactivateMember(String api, DeactivateDTO deactivateDTO, LoginResponse response) {
        HttpEntity<DeactivateDTO> entity = HeaderUtils.getHeader(deactivateDTO, response);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                ServerUtils.DOMAIN + api, HttpMethod.PUT, entity, String.class);
        return responseEntity.getBody();
    }

}
