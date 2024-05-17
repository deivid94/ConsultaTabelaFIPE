package com.example.ConsultaFIPE.service;

import java.util.List;

public interface iConverteDados {

    <T> T obterDados (String json, Class<T> classe);
    <T> List<T> obterlista (String json, Class<T> classe );
}
