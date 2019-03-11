/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.bussiness.services;

import com.example.demo.data.entity.Articulo;
import com.example.demo.data.jpa.ArticuloRepository;
import java.util.List;
import java.util.Optional;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ArticuloService {
    
    @Autowired
    private ArticuloRepository articuloRepository;
      
    public void saveArticulo(JSONObject articuloJson){
        Articulo articulo = new Articulo();
        articulo.setAuthor(articuloJson.getString("author"));
        articulo.setComment(!articuloJson.get("comment_text").equals(null) ? articuloJson.getString("comment_text") : "");
        articulo.setCreated_at(articuloJson.getString("created_at"));
        articulo.setTitle(!articuloJson.get("title").equals(null) ? articuloJson.getString("title") : articuloJson.getString("story_title"));
        articulo.setStatus(true);
        if (this.articuloRepository.findByTitleAndAuthor(articulo.getTitle(), articulo.getAuthor()) == null) {
            this.articuloRepository.save(articulo);
        }
    }
    
    public List<Articulo> findAll() {
        Sort sort = new Sort(Sort.Direction.DESC, "created_at");
        return this.articuloRepository.findByStatus(true, sort);
    }
    
    public Articulo findById(String id){
        Optional<Articulo> articuloOptinal = this.articuloRepository.findById(id);
        if (articuloOptinal.isPresent()) {
            return articuloOptinal.get();
        }
        return null;
    }
    
    public void delete(Articulo articulo) {
        articulo.setStatus(false);
        this.articuloRepository.save(articulo);
    }
}
