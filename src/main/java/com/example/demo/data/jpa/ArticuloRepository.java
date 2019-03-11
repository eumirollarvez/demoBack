/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.data.jpa;

import com.example.demo.data.entity.Articulo;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloRepository extends MongoRepository<Articulo, String>{
    
    public Articulo findByTitleAndAuthor(@Param("title") String title, @Param("author") String author);
    public List<Articulo> findByStatus(@Param("status") boolean status, Sort sort);
}
