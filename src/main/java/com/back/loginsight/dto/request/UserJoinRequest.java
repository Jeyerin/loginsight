package com.back.loginsight.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.DateTimeException;
import java.util.Date;

/**
 * [UserJoinRequest DTO]
 *
 * 회원가입 요청 DTO
 *
 * @author 예린
 * @since 2026-04-03
 *
 * =======================
 * [변경 이력]
 * 2026-04-03 | 제예린 | 최초 생성
 * =======================
 */

@Getter
@Setter
public class UserJoinRequest {
    // 사용자 고유아이디
    private String loginId;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;

}
