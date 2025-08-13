package demo.com.example.omspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.com.example.omspring.config.JwtUtil;
import demo.com.example.omspring.model.AuthRequest;
import demo.com.example.omspring.model.LoginResponse;
import demo.com.example.omspring.model.UserModel;
import demo.com.example.omspring.service.UserService;

@RestController
@RequestMapping("/auth")
public class UserController {

	@Autowired
	UserService userService;

	private final AuthenticationManager authManager;
	private final JwtUtil jwtUtil;

	public UserController(AuthenticationManager authManager, JwtUtil jwtUtil) {
		this.authManager = authManager;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/login")
	public LoginResponse login(@RequestBody AuthRequest request) {
		Authentication auth = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.username, request.password));
		
		if (!auth.isAuthenticated()) {
            throw new UsernameNotFoundException("Invalid UserId or Password ");
        }

		UserDetails userDetails = userService.loadUserByUsername(request.username);
		String token = jwtUtil.generateToken(userDetails.getUsername());

		// return ResponseEntity.ok(userDetails);
		return new LoginResponse(token, jwtUtil.extractCreatedAt(token), jwtUtil.extractExpirationDate(token));
	}

	@PostMapping("/signup")
	public ResponseEntity<String> signupUser(@RequestBody UserModel userModel) {
		System.out.println("aaaa" + userModel);
		userService.saveUser(userModel);
		return ResponseEntity.ok("Success");
	}
}
