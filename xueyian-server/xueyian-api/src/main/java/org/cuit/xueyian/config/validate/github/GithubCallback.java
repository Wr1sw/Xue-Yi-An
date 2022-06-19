package org.cuit.xueyian.config.validate.github;

import me.zhyd.oauth.model.AuthCallback;

/**
 * @author wr1sw
 * @version 1.0.0
 * @description
 */
public class GithubCallback extends AuthCallback {
    private String code;

    private String state;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
