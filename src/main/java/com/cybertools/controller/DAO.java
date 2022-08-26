package com.cybertools.controller;

//@author jpjar

import java.util.List;

 
public interface DAO <T>{
    
    List<T> read();
    boolean create(T t);
    boolean update(T t);
    boolean delete(T t);

}
