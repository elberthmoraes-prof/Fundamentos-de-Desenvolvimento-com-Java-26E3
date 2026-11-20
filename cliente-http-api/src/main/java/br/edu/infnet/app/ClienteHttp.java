package br.edu.infnet.app;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ClienteHttp {

    private static final String BASE_URL = "http://localhost:8081/api/equipamentos";

    public static void main(String[] args) {

        System.out.println(BASE_URL);

        listarEquipamentos();

        consultarEquipamento(1);

        consultarEquipamento(10);

        filtrarEquipamentos("monitor", 2);

        cadastrarEquipamento();

        filtrarEquipamentos("impressora", 2);
    }

    private static void cadastrarEquipamento() {

        String json = "{"
                + "\"nome\": \"Impressora do Infnet\","
                + "\"tipo\": \"impressora\","
                + "\"disponivel\": true"
                + "}";

        executarRequisicao("POST", BASE_URL, json);
    }

    private static void filtrarEquipamentos(String tipo, int limite) {

        String tipoCodificado =
                URLEncoder.encode(
                    tipo,
                    StandardCharsets.UTF_8
                );

        String endereco = BASE_URL
                + "?tipo=" + tipoCodificado
                + "&limite=" + limite;

        executarRequisicao("GET", endereco, null);
    }

    private static void consultarEquipamento(int id) {

        executarRequisicao(
            "GET",
            BASE_URL + "/" + id,
            null
        );
    }

    private static void listarEquipamentos() {

        executarRequisicao(
            "GET",
            BASE_URL,
            null
        );
    }

    private static void executarRequisicao(String metodo, String endereco, String json) {

        HttpURLConnection conexao = null;

        try {

            // 1 - Abertura da conexão.
            conexao = (HttpURLConnection)URI.create(endereco).toURL().openConnection();

            // 2 - Configuração da requisição.
            conexao.setRequestMethod(metodo);
            conexao.setConnectTimeout(5000);
            conexao.setReadTimeout(5000);

            conexao.setRequestProperty("Accept", "application/json");

            // 3 - Envio do JSON, quando necessário.
            if (json != null) {

                conexao.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

                conexao.setDoOutput(true);

                byte[] dados = json.getBytes(StandardCharsets.UTF_8);

                try (DataOutputStream escrita = new DataOutputStream(conexao.getOutputStream())) {

                    escrita.write(dados);
                }
            }

            // 4 - Recuperação do status HTTP.
            int status = conexao.getResponseCode();

            System.out.println();
            System.out.println("==============================");

            System.out.println("MÉTODO: " + metodo);
            System.out.println("URL: " + endereco);
            System.out.println("STATUS HTTP: " + status);

            System.out.println("CONTENT TYPE: " + conexao.getContentType());

            System.out.println("LOCATION: " + conexao.getHeaderField("Location"));

            System.out.println("ALLOW: " + conexao.getHeaderField("Allow"));

            // 5 - Seleção do fluxo.
            InputStream fluxo = status >= 400
                    ? conexao.getErrorStream()
                    : conexao.getInputStream();

            // 6 - Leitura do conteúdo.
            if (fluxo != null) {

                try (BufferedReader leitura = new BufferedReader(new InputStreamReader(fluxo, StandardCharsets.UTF_8))) {

                    String linha = leitura.readLine();

                    while (linha != null) {

                        System.out.println(linha);

                        linha = leitura.readLine();
                    }
                }

            } else {

                System.out.println("Resposta sem conteúdo.");
            }

        } catch (IOException e) {

            System.out.println("Falha de comunicação: " + e.getMessage());

            e.printStackTrace();

        } finally {

            if (conexao != null) {
                conexao.disconnect();
            }

            System.out.println("Processo encerrado!!!");
        }
    }
}