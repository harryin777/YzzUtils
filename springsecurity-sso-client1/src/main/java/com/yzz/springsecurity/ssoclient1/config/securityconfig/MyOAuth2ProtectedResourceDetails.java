package com.yzz.springsecurity.ssoclient1.config.securityconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

import java.util.List;

/**
 * @ClassName MyOAuth2ProtectedResourceDetails
 * @Author yky
 * @Date 2021/3/11
 * @Version 1.0
 */
@Configuration
public class MyOAuth2ProtectedResourceDetails implements OAuth2ProtectedResourceDetails {
	@Override
	public String getId() {
		return null;
	}
	
	@Override
	public String getClientId() {
		return null;
	}
	
	@Override
	public String getAccessTokenUri() {
		return null;
	}
	
	@Override
	public boolean isScoped() {
		return false;
	}
	
	@Override
	public List<String> getScope() {
		return null;
	}
	
	@Override
	public boolean isAuthenticationRequired() {
		return false;
	}
	
	@Override
	public String getClientSecret() {
		return null;
	}
	
	@Override
	public AuthenticationScheme getClientAuthenticationScheme() {
		return null;
	}
	
	@Override
	public String getGrantType() {
		return null;
	}
	
	@Override
	public AuthenticationScheme getAuthenticationScheme() {
		return null;
	}
	
	@Override
	public String getTokenName() {
		return null;
	}
	
	@Override
	public boolean isClientOnly() {
		return false;
	}
}
