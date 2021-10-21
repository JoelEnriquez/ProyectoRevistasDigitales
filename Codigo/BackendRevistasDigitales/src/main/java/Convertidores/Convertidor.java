/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Convertidores;

import com.google.gson.Gson;
import java.lang.reflect.Type;

/**
 *
 * @author joel
 */
public abstract class Convertidor<T> {
    
    private Gson gson;
    private Class<T> claseConvertir;

    public Convertidor(Class<T> claseConvertir) {
        this.gson = new Gson();
        this.claseConvertir = claseConvertir;
    }
    
    public T fromJson(String json) {
        return gson.fromJson(json, claseConvertir);
    }
    
    public String toJson(T object) {
        return gson.toJson(object, claseConvertir);
    }
}
