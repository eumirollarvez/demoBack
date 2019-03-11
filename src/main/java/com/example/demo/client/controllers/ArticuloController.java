/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.client.controllers;

import com.example.demo.bussiness.services.ArticuloService;
import com.example.demo.data.entity.Articulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value="/articulos")
public class ArticuloController {
    
    @Autowired
    private ArticuloService articuloService;

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.GET})
    public ResponseEntity<?> findAll(){
        return ResponseEntity.status(200).body(this.articuloService.findAll());
    }
    
    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.DELETE})
    public ResponseEntity<?> delete(@PathVariable String id){
        Articulo articulo = this.articuloService.findById(id);
        if(!articulo.equals(null)){
            this.articuloService.delete(articulo);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(403).build();
    }
}
