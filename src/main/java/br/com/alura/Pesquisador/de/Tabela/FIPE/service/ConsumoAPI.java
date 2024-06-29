package br.com.alura.Pesquisador.de.Tabela.FIPE.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//Classe exclusiva para chamar QUALQUER API
public class ConsumoAPI {

    //Leitura: O método vai retornar um String (que vai ser o JSON), e para chamar o método precisa colocar uma String que vai ser o endereço de uma API, dessa forma da pra chamar qualquer API
    public String obterDados(String endereco) {

        //HTTP Cliente,Request e Response para puxar os dados do que vier em endereço
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endereco)).build();
        HttpResponse<String> response = null;

        //Tratando excessões, tentar pegar o resultado do request, caso dê algum daqueles erros ali (IOExcepetion ou InterrupetdExpetion) irá lançar o erro na tela avisando
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //O que puxar da API, colocar na variavel json e retornar esse conteúdo para a aplicação que chamou o método
        String json = response.body();
        return json;
    }
}