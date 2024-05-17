package com.example.ConsultaFIPE.principal;

import com.example.ConsultaFIPE.model.DadosMarcas;
import com.example.ConsultaFIPE.model.DadosModelo;
import com.example.ConsultaFIPE.service.ConsumoApi;
import com.example.ConsultaFIPE.service.ConverteDados;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private final String  ENDERECO = "https://parallelum.com.br/fipe/api/v1/";
    private final String MARCAS = "/marcas";
    private  Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumindoApi = new ConsumoApi();
    private ConverteDados converteDadosParaJson = new ConverteDados();


    public void exibeMenu(){

        var menu  = """
                   **OPCOES**
                   -------------
                   carros
                   motos
                   caminhoes
                   --------------
                   DIGITE UMA DAS OPCOES PARA CONSULTAR
                   \n
                """;
        System.out.println(menu);
        var opcaoDigitadaPeloUsuario = leitura.nextLine().toLowerCase();


        var json = consumindoApi.obterDados(ENDERECO+opcaoDigitadaPeloUsuario+MARCAS);
        var marcasVeiculos  = converteDadosParaJson.obterlista(json, DadosMarcas.class);

        marcasVeiculos.stream()
                .sorted (Comparator.comparing(DadosMarcas::codigo))
                .forEach(System.out::println);


        System.out.println("\n Escolha um codigo da marca que deseja visualizar alguem modelo: ");
        var usuarioEscolheModelo = leitura.nextLine().toLowerCase();
        System.out.println(usuarioEscolheModelo);

        var consultaModeloNaApi = consumindoApi.obterDados(ENDERECO+opcaoDigitadaPeloUsuario+MARCAS+"/"+usuarioEscolheModelo+"/modelos");
        //https://parallelum.com.br/fipe/api/v1/carros/marcas/7/modelos
        System.out.println(consultaModeloNaApi);

        var modeloLista = converteDadosParaJson.obterDados(consultaModeloNaApi, DadosModelo.class);
        System.out.println(modeloLista);

    }


}
