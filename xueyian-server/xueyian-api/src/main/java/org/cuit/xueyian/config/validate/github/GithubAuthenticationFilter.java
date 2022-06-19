package org.cuit.xueyian.config.validate.github;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthGithubRequest;
import me.zhyd.oauth.request.AuthRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wr1sw
 * @version 1.0.0
 * @description
 */
public class GithubAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    public static final String SPRING_SECURITY_FORM_ACCESS_TOKEN_KEY = "access_token";


    @Value("${github.clientId}")
    private String clientId;

    @Value("${github.clientSecret}")
    private String clientSecret;

    @Value("${github.redirectUri}")
    private String redirectUri;

    private String accessTokenParameter = SPRING_SECURITY_FORM_ACCESS_TOKEN_KEY;


    private boolean postOnly = true;

    public GithubAuthenticationFilter() {
        super(new AntPathRequestMatcher("/github/login", null));
    }

    private AuthRequest getAuthRequest() {
        return new AuthGithubRequest(AuthConfig.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .redirectUri(redirectUri)
                .build());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("GET")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        GithubCallback githubCallback = new GithubCallback();
        githubCallback.setCode(request.getParameter("code"));
        githubCallback.setState(request.getParameter("state"));
        AuthRequest authRequest1 = getAuthRequest();
        AuthResponse login = authRequest1.login(githubCallback);
        AuthUser authUser = (AuthUser) login.getData();
        System.out.println("authUser = " + authUser);
        String accessToken = authUser.getToken().getAccessToken();

//        String accessToken = obtainAccessToken(request);
        System.out.println("accessToken = " + accessToken);
        accessToken = (accessToken != null) ? accessToken : "";

        GithubAuthenticationToken authRequest = new GithubAuthenticationToken(accessToken);
        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }


    @Nullable
    protected String obtainAccessToken(HttpServletRequest request) {
        return request.getParameter(this.accessTokenParameter);
    }


    protected void setDetails(HttpServletRequest request, GithubAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public final String getMobileParameter() {
        return this.accessTokenParameter;
    }

    public void setMobileParameter(String mobileParameter) {
        Assert.hasText(mobileParameter, "Mobile parameter must not be empty or null");
        this.accessTokenParameter = mobileParameter;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }
}
