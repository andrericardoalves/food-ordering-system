package com.food.ordering.system.order.service.dataaccess.customer.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders_customer_m_views", schema = "customer")
@Entity
public class CustomerEntity {

    @Id
    private UUID id;
}
