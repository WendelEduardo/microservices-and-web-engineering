package br.com.fiap.msprodutos.dto;

public class DadosTokenJwtDTO {

    private String token;

    public DadosTokenJwtDTO() {
    }
    public DadosTokenJwtDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "DadosTokenJwtDTO{" +
                "token='" + token + '\'' +
                '}';
    }
}
