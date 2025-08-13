package demo.com.example.omspring.model;

import java.util.Date;

public class LoginResponse {
	private final String token;
	private final Date createdAt;
	private final Date expiresAt;

	public LoginResponse(String token, Date createdAt, Date expiresAt) {
		super();
		this.token = token;
		this.createdAt = createdAt;
		this.expiresAt = expiresAt;
	}

	public String getToken() {
		return token;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getExpiresAt() {
		return expiresAt;
	}

}
