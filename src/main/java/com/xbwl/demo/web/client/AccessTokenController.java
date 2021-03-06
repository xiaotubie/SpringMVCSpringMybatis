package com.xbwl.demo.web.client;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.oltu.oauth2.common.utils.JSONUtils;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xbwl.demo.service.client.OAuthService;
import com.xbwl.demo.utils.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.URISyntaxException;
import java.util.Map;

/**
 * 访问 令牌 控制器
 * */
@RestController
public class AccessTokenController {

    @Autowired
    private OAuthService oAuthService;
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    @RequestMapping("/accessToken")
    public HttpEntity token(HttpServletRequest request) throws URISyntaxException, OAuthSystemException {
        try {
            //构建 OAuth 请求
            OAuthTokenRequest oauthRequest = new OAuthTokenRequest(request);
            //检查提交的客户端 id 是否正确
            if (!oAuthService.checkClientId(oauthRequest.getClientId())) {
                OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST).setError(OAuthError.TokenResponse.INVALID_CLIENT)
                                                        .setErrorDescription(Constants.INVALID_CLIENT_DESCRIPTION).buildJSONMessage();
                return new ResponseEntity(formatBody(response.getBody()), HttpStatus.valueOf(response.getResponseStatus()));
            }
            
            // 检查客户端安全 KEY 是否正确
            if (!oAuthService.checkClientSecret(oauthRequest.getClientSecret())) {
                OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_UNAUTHORIZED).setError(OAuthError.TokenResponse.UNAUTHORIZED_CLIENT)
                                                        .setErrorDescription(Constants.INVALID_CLIENT_DESCRIPTION).buildJSONMessage();
                return new ResponseEntity(formatBody(response.getBody()), HttpStatus.valueOf(response.getResponseStatus()));
            }
            
            String authCode = oauthRequest.getParam(OAuth.OAUTH_CODE);
            // 检查验证类型，此处只检查 AUTHORIZATION_CODE 类型，其他的还有PASSWORD 或 REFRESH_TOKEN
            if (!oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE).equals(GrantType.AUTHORIZATION_CODE.toString())) {
                if (!oAuthService.checkAuthCode(authCode)) {
                    OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST).setError(OAuthError.TokenResponse.INVALID_GRANT)
                                                            .setErrorDescription("错误的授权码").buildJSONMessage();
                    return new ResponseEntity(formatBody(response.getBody()), HttpStatus.valueOf(response.getResponseStatus()));
                }
            }
            
            //生成 Access Token
            OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
            final String accessToken = oauthIssuerImpl.accessToken();
            oAuthService.addAccessToken(accessToken, oAuthService.getUsernameByAuthCode(authCode));
            
            //生成 OAuth 响应
            OAuthResponse response = OAuthASResponse.tokenResponse(HttpServletResponse.SC_OK).setAccessToken(accessToken)
                                                    .setExpiresIn(String.valueOf(oAuthService.getExpireIn())).buildJSONMessage();
           
            //根据 OAuthResponse 生成 ResponseEntity
            return new ResponseEntity(formatBody(response.getBody()), HttpStatus.valueOf(response.getResponseStatus()));
        } catch (OAuthProblemException e) {
            //构建错误响应
            OAuthResponse res = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST).error(e).buildJSONMessage();
            return new ResponseEntity(formatBody(res.getBody()), HttpStatus.valueOf(res.getResponseStatus()));
        }
        
    }
    
    
    public Map<String, Object> formatBody(String body){
        Map<String, Object> bodyJson;
        try {
            bodyJson = JSONUtils.parseJSON(body);
            return bodyJson;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
