package com.atl.map.filter;

import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.atl.map.entity.UserEntity;
import com.atl.map.provider.JwtProvider;
import com.atl.map.repository.UserRepository;
import org.springframework.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

// 이 필터는 요청마다 한 번씩 실행, JWT 검증용
@Component
@RequiredArgsConstructor // final이나 @NonNull인 필드 받는 생성자
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    // UserRepository와 JwtProvider는 자동으로 주입
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // 요청 헤더에서 토큰을 뽑아내기
            String token = parseBearerToken(request);
            
            if(token == null) { // 토큰이 없으면 다음 필터로
                filterChain.doFilter(request, response);
                return;
            }

            String userEmail = jwtProvider.validate(token);
            if(userEmail == null) {  // 토큰에서 유저 이메일을 검증하고 이메일이 없으면 다음 필터로
                filterChain.doFilter(request, response);
                return;
            }

            // 유저 이메일로 유저 정보를 찾고, 그 안에 있는 유저의 역할(권한)을 확인
            UserEntity userEntity = userRepository.findByUserEmail(userEmail);
            String role = userEntity.getRole(); // 예: ROLE_USER, ROLE_ADMIN

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        // 모든 검증을 마치면, 다음 필터로 요청을 넘겨 처리
        filterChain.doFilter(request, response);
    }

    // 'Authorization' 헤더에서 'Bearer '로 시작하는 토큰을 뽑아내기
    private String parseBearerToken(HttpServletRequest request) {
        // 'Authorization' 헤더 값
        String authorization = request.getHeader("Authorization");
        
        // 헤더 값이 없거나 빈 문자열이면 null을 반환
        if(!StringUtils.hasText(authorization)) return null;
        
        // 'Bearer '로 시작하지 않으면 null을 반환
        if (!authorization.startsWith("Bearer ")) return null;
        
        // 'Bearer ' 다음부터가 진짜 토큰이니까 그 부분만 잘라내 반환
        return authorization.substring(7);
    }
}
