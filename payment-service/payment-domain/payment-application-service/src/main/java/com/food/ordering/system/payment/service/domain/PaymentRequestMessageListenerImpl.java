package com.food.ordering.system.payment.service.domain;

import com.food.ordering.system.payment.service.domain.entity.Payment;
import com.food.ordering.system.payment.service.domain.ports.input.message.listener.PaymentRequestMessageListener;

public class PaymentRequestMessageListenerImpl implements PaymentRequestMessageListener {

    @Override
    public void completePayment(Payment payment) {

    }

    @Override
    public void cancelPayment(Payment payment) {

    }
}
