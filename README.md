# omspring
Spring Security with jwt token 

This project demonstrates how to implement authentication and authorization in a Spring Boot application using Spring Security and JWT (JSON Web Token).

**JWT Flow**
1. User signs up using /signup.
2. User logs in using /login and receives a JWT token.
3. Client includes token in Authorization: Bearer <token> header for secured APIs.
4. Server validates the token via a custom filter before allowing access.
