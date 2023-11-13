package br.com.fiap.msprodutos.controller;

import br.com.fiap.msprodutos.dto.DadosTokenJwtDTO;
import br.com.fiap.msprodutos.dto.UserDTO;
import br.com.fiap.msprodutos.model.User;
import br.com.fiap.msprodutos.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class UserLoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    @PostMapping
    public ResponseEntity<DadosTokenJwtDTO> login(@RequestBody UserDTO userDTO){

        var authenticationToken = new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword());

        var authentication = authenticationManager.authenticate(authenticationToken);

        var tokenJWT = tokenService.getToken((User) authentication.getPrincipal());

        var dadosToken = new DadosTokenJwtDTO(tokenJWT);

        return ResponseEntity.ok(dadosToken);

    }

}
