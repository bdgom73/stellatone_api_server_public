package com.stellive.music.domain.api.domain.music.service;

import com.stellive.music.domain.api.domain.common.service.DefaultAuthService;
import com.stellive.music.domain.api.domain.music.CreateUtil;
import com.stellive.music.domain.api.global.auth.JwtTokenProvider;
import com.stellive.music.domain.api.global.auth.oauth.dto.Token;
import com.stellive.music.domain.api.global.exception.BizException;
import com.stellive.music.domain.api.global.exception.ErrorResult;
import com.stellive.music.domain.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("인증 테스트")
public class AuthServiceUnitTest {

    @InjectMocks
    private DefaultAuthService authService;
    @Mock
    private JwtTokenProvider jwtTokenProvider;
    @Mock
    private MockHttpServletRequest httpServletRequest;

    @Test
    @DisplayName("[성공] 토큰 재발급")
    public void reissueTest() {
        // given
        var token = "token";
        var id = 1L;
        Member member = CreateUtil.createMember(id);
        given( jwtTokenProvider.resolveToken() )
                .willReturn(token);

        given( jwtTokenProvider.getMemberByToken(token) )
                .willReturn(Optional.ofNullable(member));

        given( jwtTokenProvider.validateToken(token) )
                .willReturn(Boolean.TRUE);

        given( jwtTokenProvider.createToken(id) )
                .willReturn("accessToken");

        given( jwtTokenProvider.createRefreshToken(id) )
                .willReturn("refreshToken");

        // when
        Token result = authService.reissue();

        // then
        assertThat(result.accessToken()).isEqualTo("accessToken");
        assertThat(result.refreshToken()).isEqualTo("refreshToken");

        verify( jwtTokenProvider, times(1) ).resolveToken();
        verify( jwtTokenProvider, times(1) ).validateToken(token);
        verify( jwtTokenProvider, times(1) ).getMemberByToken(token);
        verify( jwtTokenProvider, times(1) ).createToken(id);
        verify( jwtTokenProvider, times(1) ).createRefreshToken(id);

    }


    @Test
    @DisplayName("[실패] 토큰 재발급 (유효 하지 않은 토큰)")
    public void reissueFailTest() {
        // given
        var token = "token";
        var id = 1L;
        Member member = CreateUtil.createMember(id);
        member.changeRefreshToken("refreshToken");
        given( jwtTokenProvider.resolveToken() )
                .willReturn(token);

        given( jwtTokenProvider.getMemberByToken(token) )
                .willReturn(Optional.of(member));

        given( jwtTokenProvider.validateToken(anyString()) )
                .willReturn(Boolean.FALSE);

        // when then
        assertThatThrownBy(() -> authService.reissue())
                .isInstanceOf(BizException.class)
                .hasMessage(ErrorResult.INVALID_TOKEN.getMessage());

        verify( jwtTokenProvider, times(1) ).resolveToken();
        verify( jwtTokenProvider, times(1) ).getMemberByToken(token);
        verify( jwtTokenProvider ).validateToken(token);
        verify( jwtTokenProvider, times(0) ).createToken(id);
        verify( jwtTokenProvider, times(0) ).createRefreshToken(id);

    }

    @Test
    @DisplayName("[실패] 토큰 재발급 (잘못된 토큰)")
    public void reissueFailTest2() {
        // given
        var token = "token";
        var id = 1L;
        Member member = CreateUtil.createMember(id);
        member.changeRefreshToken("refreshToken");
        given( jwtTokenProvider.resolveToken() )
                .willReturn(token);

        given( jwtTokenProvider.getMemberByToken(token) )
                .willReturn(Optional.empty());


        // when then
        assertThatThrownBy(() -> authService.reissue())
                .isInstanceOf(BizException.class)
                .hasMessage(ErrorResult.INVALID_TOKEN.getMessage());

        verify( jwtTokenProvider, times(1) ).resolveToken();
        verify( jwtTokenProvider, times(1) ).getMemberByToken(token);
        verify( jwtTokenProvider, never() ).validateToken(token);
        verify( jwtTokenProvider, never() ).createToken(id);
        verify( jwtTokenProvider, never() ).createRefreshToken(id);

    }

}
