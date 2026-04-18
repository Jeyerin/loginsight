package com.back.loginsight.service;

import com.back.loginsight.common.ApiResponse;
import com.back.loginsight.config.JwtTokenConfig;
import com.back.loginsight.dto.request.UserInfoRequest;
import com.back.loginsight.dto.request.UserJoinRequest;
import com.back.loginsight.dto.request.UserLoginRequest;
import com.back.loginsight.dto.response.LoginResponse;
import com.back.loginsight.dto.response.UserInfoResponse;
import com.back.loginsight.entity.User;
import com.back.loginsight.repository.UserRepository;
import java.util.concurrent.TimeUnit;

import jakarta.transaction.Transactional;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenConfig jwtTokenConfig;
    private final RedisTemplate<String, String> redisTemplate;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenConfig jwtTokenConfig,
                           RedisTemplate<String, String> redisTemplate) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenConfig = jwtTokenConfig;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public ApiResponse<String> signUp(UserJoinRequest userJoinRequest) {


        if (userRepository.existsByLoginId(userJoinRequest.getLoginId())) {
            return ApiResponse.fail("이미 사용 중인 로그인 아이디입니다.");
        }

        userRepository.save(User.from(userJoinRequest,passwordEncoder));
        return ApiResponse.success("회원가입이 완료되었습니다.", userJoinRequest.getLoginId());
    }

    @Override
    public ApiResponse<LoginResponse> login(UserLoginRequest request) {
        User user = userRepository.findByLoginId(request.getLoginId())
                .orElseThrow(() -> new IllegalArgumentException("사용자가 없습니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtTokenConfig.createAccessToken(user.getLoginId());
        String refreshToken = jwtTokenConfig.createRefreshToken(user.getLoginId());

        redisTemplate.opsForValue().set(
                "refresh:" + user.getLoginId(),
                refreshToken,
                jwtTokenConfig.getRefreshTokenExpiration(),
                TimeUnit.MILLISECONDS
        );

        return ApiResponse.success("로그인 성공", new LoginResponse(accessToken, refreshToken));
    }

    @Override
    public ApiResponse<String> logout(String loginId) {
        redisTemplate.delete("refresh:" + loginId);
        return ApiResponse.success("로그아웃 완료", loginId);
    }

    @Override
    @Transactional
    public ApiResponse<String> updateUser(String loginId, UserInfoRequest request) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 없습니다."));

        user.updateInfo(request);
        return ApiResponse.success("회원정보 수정 완료", loginId);
    }

    @Override
    public ApiResponse<UserInfoResponse> getUserInfo(String loginId) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 없습니다."));

        UserInfoResponse response = new UserInfoResponse(
                user.getName(),
                user.getEmail(),
                user.getPhoneNumber()
        );

        return ApiResponse.success("사용자 정보 조회 완료", response);
    }

}
