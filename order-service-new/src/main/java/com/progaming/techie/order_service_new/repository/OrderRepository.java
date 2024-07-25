package com.progaming.techie.order_service_new.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.progaming.techie.order_service_new.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
