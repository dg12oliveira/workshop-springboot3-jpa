package com.projeto.projeto1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.projeto1.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
