package com.example.taco.cloud.repository;

import com.example.taco.cloud.domain.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
