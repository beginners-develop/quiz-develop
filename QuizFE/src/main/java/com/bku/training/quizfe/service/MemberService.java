package com.bku.training.quizfe.service;

import com.bku.training.quizfe.entities.DeactivateDTO;
import com.bku.training.quizfe.entities.LoginResponse;
import com.bku.training.quizfe.entities.MemberDTO;
import com.bku.training.quizfe.entities.PasswordDTO;


/**
 * @author Nam Tran
* @project Quiz
 **/
public interface MemberService {

    MemberDTO updateMember(String api, MemberDTO inputMemberDTO, LoginResponse response);
    String updatePassword(String api, PasswordDTO passwordDTO, LoginResponse response);
    String deactivateMember(String api, DeactivateDTO deactivateDTO, LoginResponse response);

}
