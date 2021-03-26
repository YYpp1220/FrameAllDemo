package com.djh.securitytest.config;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取用户登录时携带的额外信息
 *
 * @author MyMrDiao
 * @date 2020/10/28
 */
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {
    private static final long serialVersionUTD = 6975601077710753878L;
    private final String verifyCode;

    /**
     * Records the remote address and will also set the session Id if a session already
     * exists (it won't create one).
     *
     * @param request that the authentication request was received from
     */
    CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        // verifyCode为页面中验证码的name
        verifyCode = request.getParameter("verifyCode");
    }

    private String getVerifyCode() {
        return this.verifyCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("；VerifyCode：").append(this.getVerifyCode());
        return sb.toString();
    }
}
