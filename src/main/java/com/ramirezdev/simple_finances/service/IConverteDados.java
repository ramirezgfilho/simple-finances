package com.ramirezdev.simple_finances.service;

public interface IConverteDados {

    <T> T obterDados(String json, Class<T> classe);

}
