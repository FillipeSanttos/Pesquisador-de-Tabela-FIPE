package br.com.alura.Pesquisador.de.Tabela.FIPE.principal;

import br.com.alura.Pesquisador.de.Tabela.FIPE.model.Dados;
import br.com.alura.Pesquisador.de.Tabela.FIPE.model.Modelos;
import br.com.alura.Pesquisador.de.Tabela.FIPE.model.Veiculo;
import br.com.alura.Pesquisador.de.Tabela.FIPE.service.ConsumoAPI;
import br.com.alura.Pesquisador.de.Tabela.FIPE.service.ConverterDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverterDados conversor = new ConverterDados();

    Scanner leitorDeDados = new Scanner(System.in);

    public void exibeMenu() {
        var menu = """
                ***OPÇÕES***
                Carro
                Moto
                Caminhão
                                
                Digite uma das opções para consultar:
                """;

        //PRIMEIRA ETAPA
        System.out.println(menu);
        var opcaoDigitada = leitorDeDados.nextLine();
        String endereco;

        if (opcaoDigitada.toLowerCase().contains("carr")) {
            endereco = URL_BASE + "carros/marcas";
        } else if (opcaoDigitada.toLowerCase().contains("mot")) {
            endereco = URL_BASE + "motos/marcas";
        } else {
            endereco = URL_BASE + "caminhoes/marcas";
        }

        var json = consumoAPI.obterDados(endereco);
        System.out.println(json);
        var marcas = conversor.obterLista(json, Dados.class);

        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        //SEGUNDA ETAPA
        System.out.println("Informe o código da marca para consulta:");
        var codigoMarca = leitorDeDados.nextLine();

        endereco = endereco + "/" + codigoMarca + "/modelos";
        json = consumoAPI.obterDados(endereco);

        var modeloLista = conversor.obterDados(json, Modelos.class);

        System.out.println("\nModelos dessa marca: ");

        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        //TERCEIRA ETAPA
        System.out.println("\nDigite um trecho do carro a ser buscado:");
        var nomeVeiculo = leitorDeDados.nextLine();
        List<Dados> modelosFiltrados = modeloLista.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("\nModelos Filtrados: ");
        modelosFiltrados.forEach(System.out::println);

        System.out.println("Digite o código do modelo para buscar valores");
        var codigoModelo = leitorDeDados.nextLine();

        endereco = endereco + "/" + codigoModelo + "/anos";
        json = consumoAPI.obterDados(endereco);
        List<Dados> anos = conversor.obterLista(json, Dados.class);
        List<Veiculo> veiculos = new ArrayList<>();

        for (int i = 0; i < anos.size(); i++) {
            var enderecoAnos = endereco + "/" + anos.get(i).codigo();
            json = consumoAPI.obterDados(enderecoAnos);
            Veiculo veiculo = conversor.obterDados(json, Veiculo.class);
            veiculos.add(veiculo);
        }

        System.out.println("\nTodos os véiculos filtrados com avaliação por ano: ");
        veiculos.forEach(System.out::println);
    }
}



