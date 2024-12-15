package com.example.demo.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private Long userId;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private String encodeUserId;

    public CustomUserDetails(String encodeUserId,Long userId, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.encodeUserId = encodeUserId;
        
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // Implement other methods of UserDetails interface as needed

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

	public String getEncodeUserId() {
		return encodeUserId;
	}

	public void setEncodeUserId(String encodeUserId) {
		this.encodeUserId = encodeUserId;
	}
    
}

