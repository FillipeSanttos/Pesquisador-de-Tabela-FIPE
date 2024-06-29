package br.com.alura.Pesquisador.de.Tabela.FIPE;

import br.com.alura.Pesquisador.de.Tabela.FIPE.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

@SpringBootApplication
public class PesquisadorDeTabelaFipeApplication implements CommandLineRunner {
	public static void main(String[] args) {SpringApplication.run(PesquisadorDeTabelaFipeApplication.class, args);}
	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal();

		principal.exibeMenu();





	}
}
