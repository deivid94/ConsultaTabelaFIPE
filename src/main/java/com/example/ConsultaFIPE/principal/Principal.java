package com.example.ConsultaFIPE.principal;

import com.example.ConsultaFIPE.model.DadosMarcas;
import com.example.ConsultaFIPE.model.DadosModelo;
import com.example.ConsultaFIPE.service.ConsumoApi;
import com.example.ConsultaFIPE.service.ConverteDados;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private final String  ENDERECO = "https://parallelum.com.br/fipe/api/v1/";

    private  Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumindoApi = new ConsumoApi();
    private ConverteDados converteDadosParaJson = new ConverteDados();


    public void exibeMenu(){
        int contador = 0;
        while (contador != 4) {
            var menu = """
                       ***OPCOES DE VEICULOS ***
                       -------------
                       1-CARROS
                       2-MOTOS
                       3-CAMINHOES
                       4- SAIR
                       --------------
                       QUAL TIPO DE VEICULO VOCE DESEJA CONSULTAR ?
                       \n
                    """;
            System.out.println(menu);

            var opcaoDeVeiculo = leitura.nextLine();

            switch (opcaoDeVeiculo) {
                case "1":
                    opcaoDeVeiculo = "carros/marcas";
                    break;
                case "2":
                    opcaoDeVeiculo = "motos/marcas";
                    break;

                case "3":
                    opcaoDeVeiculo = "caminhoes/marcas";
                    break;
                case "4":
                    System.out.println("CONSULTAR ENCERRADA PELO USUARIO");
                default:
                    System.out.println("OPCAO INVALIDA. ESOCLHA AS OPCOES 1 A 4");
                    break;
            };

                var json = consumindoApi.obterDados(ENDERECO + opcaoDeVeiculo);
                var marcasVeiculos = converteDadosParaJson.obterlista(json, DadosMarcas.class);

                marcasVeiculos.stream()
                        .sorted(Comparator.comparing(DadosMarcas::codigo))
                        .forEach(System.out::println);





            System.out.println("\n Escolha um codigo da marca que deseja visualizar alguem modelo: ");
            var usuarioEscolheModelo = leitura.nextLine().toLowerCase();

            System.out.println("\n MODELOS DA MARCA ESCOLHIDA: ");
            var consultaModeloNaApi = consumindoApi.obterDados(ENDERECO + opcaoDeVeiculo + "/" + usuarioEscolheModelo + "/modelos");
            //https://parallelum.com.br/fipe/api/v1/carros/marcas/7/modelos
            System.out.println(consultaModeloNaApi);

            var modeloLista = converteDadosParaJson.obterDados(consultaModeloNaApi, DadosModelo.class);
            modeloLista.modelos().stream()
                    .sorted(Comparator.comparing(DadosMarcas::nome))
                    .forEach(System.out::println);

            System.out.println("\n INFORME  NOME DO VEICULO");
            var nomeDoVeiculo = leitura.nextLine();
            List<DadosMarcas> listaComTodosModelos = modeloLista.modelos().stream()
                    .filter(m -> m.nome().toLowerCase().contains(nomeDoVeiculo.toLowerCase()))
                    .collect(Collectors.toList());


            System.out.println("\nMODELOS FILTRADOS");
            listaComTodosModelos.forEach(System.out::println);
            //https://parallelum.com.br/fipe/api/v1/carros/marcas/7/modelos/8314/anos

        }





    }


}
