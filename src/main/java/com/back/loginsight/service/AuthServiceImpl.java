package com.back.loginsight.service;

import com.back.loginsight.config.JwtTokenConfig;
import com.back.loginsight.dto.request.UserLoginRequest;
import com.back.loginsight.dto.response.LoginResponse;
import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtTokenConfig jwtTokenConfig;
    private final RedisTemplate<String, String> redisTemplate;

    public AuthServiceImpl(JwtTokenConfig jwtTokenConfig,
                           RedisTemplate<String, String> redisTemplate) {
        this.jwtTokenConfig = jwtTokenConfig;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public LoginResponse reissue(UserLoginRequest userLoginRequest) {
        String loginId = userLoginRequest.getLoginId();
        String refreshToken = redisTemplate.opsForValue().get("refresh:" + loginId);

        if (refreshToken == null || !jwtTokenConfig.validateToken(refreshToken)) {
            throw new IllegalArgumentException("유효한 리프레시 토큰이 없습니다.");
        }

        String newAccessToken = jwtTokenConfig.createAccessToken(loginId);
        String newRefreshToken = jwtTokenConfig.createRefreshToken(loginId);

        redisTemplate.opsForValue().set(
                "refresh:" + loginId,
                newRefreshToken,
                jwtTokenConfig.getRefreshTokenExpiration(),
                TimeUnit.MILLISECONDS
        );

        return new LoginResponse(newAccessToken, newRefreshToken);
    }
}
