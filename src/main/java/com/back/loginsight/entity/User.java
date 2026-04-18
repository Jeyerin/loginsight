package com.back.loginsight.entity;

import com.back.loginsight.dto.request.UserJoinRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * [User Entity]
 *
 * 회원 엔티티
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
@Entity
@Builder
@Table(name = "users", schema = "public")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @Column(name = "login_id", nullable = false, unique = true)
    private String loginId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "reg_date", nullable = false)
    private String regDate;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email")
    private String email;

    public static User from(UserJoinRequest request, PasswordEncoder passwordEncoder) {
        return User.builder()
                .userId(UUID.randomUUID().toString())
                .loginId(request.getLoginId())
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .regDate(LocalDateTime.now().toString())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .build();
    }
}
