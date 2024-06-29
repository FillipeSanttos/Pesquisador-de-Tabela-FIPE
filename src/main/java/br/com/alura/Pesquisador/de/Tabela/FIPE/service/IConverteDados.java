package br.com.alura.Pesquisador.de.Tabela.FIPE.service;


import java.util.List;

//Vai receber um String JSON, vai receber uma Classe e transforma um em outro
public interface IConverteDados {

    <T> T obterDados(String json, Class<T> classe);

    <T> List<T> obterLista(String json, Class<T> classe);

}