package com.food.ordering.system.payment.service.domain.ports.input.message.listener;

import com.food.ordering.system.payment.service.domain.entity.Payment;

public interface PaymentRequestMessageListener {

    void completePayment(Payment payment);

    void cancelPayment(Payment payment);
}
