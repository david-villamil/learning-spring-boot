package com.example.taco.cloud.repository;

import com.example.taco.cloud.domain.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {}
