package com.projeto.projeto1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.projeto1.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
