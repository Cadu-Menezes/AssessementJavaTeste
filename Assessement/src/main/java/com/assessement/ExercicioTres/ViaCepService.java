package com.assessement.ExercicioTres;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ViaCepService {
    
    private static final String URL_BASE = "https://viacep.com.br/ws/";
    
    public static String buscarCep(String cep) throws Exception {
        String urlCompleta = URL_BASE + cep + "/json/";
        
        URL url = new URL(urlCompleta);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod("GET");
        conexao.setConnectTimeout(5000);
        conexao.setReadTimeout(5000);
        
        int codigoResposta = conexao.getResponseCode();
        
        if (codigoResposta == 200) {
            BufferedReader leitor = new BufferedReader(
                new InputStreamReader(conexao.getInputStream())
            );
            
            StringBuilder response = new StringBuilder();
            String linha;
            
            while ((linha = leitor.readLine()) != null) {
                response.append(linha);
            }
            
            leitor.close();
            return response.toString();
        } else {
            throw new Exception("Erro na requisição: " + codigoResposta);
        }
    }
    
    public static String buscarEndereco(String uf, String cidade, String logradouro) throws Exception {
        String urlCompleta = URL_BASE + uf + "/" + cidade + "/" + logradouro + "/json/";
        
        URL url = new URL(urlCompleta);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod("GET");
        conexao.setConnectTimeout(5000);
        conexao.setReadTimeout(5000);
        
        int codigoResposta = conexao.getResponseCode();
        
        if (codigoResposta == 200) {
            BufferedReader leitor = new BufferedReader(
                new InputStreamReader(conexao.getInputStream())
            );
            
            StringBuilder response = new StringBuilder();
            String linha;
            
            while ((linha = leitor.readLine()) != null) {
                response.append(linha);
            }
            
            leitor.close();
            return response.toString();
        } else {
            throw new Exception("Erro na requisição: " + codigoResposta);
        }
    }
}
