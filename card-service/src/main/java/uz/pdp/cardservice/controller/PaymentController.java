package uz.pdp.cardservice.controller;

import com.stripe.Stripe;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cardservice.model.Card;
import uz.pdp.cardservice.model.Status;
import uz.pdp.cardservice.repository.CardRepository;
import uz.pdp.cardservice.service.PaymentService;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PaymentController extends Thread {

    @Autowired
    PaymentService paymentService;
    @Autowired
    CardRepository cardRepository;




    @SneakyThrows
    @PostMapping("/webhook")
    public void handle(@RequestBody String payload, @RequestHeader(name = "Stripe-Signature") String signHeader, HttpServletResponse response) {
        String endpointSecret = "whsec_9d3fa95ded35d69e4b0c61d55739c534f9158550397f11a6bdabcf576c70941d";
        Stripe.apiKey = "sk_test_51Kiz3rEhoGDMoOBR40fygRgoRauXuzCNiAQjVjT62m8VunOcTYCmk7SVBQ07AaYeoe7jXZiRw0D17kUviPfAdbYr00hNGzkbE8";
//      to activate:  stripe listen --forward-to localhost:8080/webhook
        Event event = Webhook.constructEvent(payload, signHeader, endpointSecret);

        System.out.println("Order fulfilled");
        if ("checkout.session.completed".equals(event.getType())) {
            Session session = (Session) event.getDataObjectDeserializer().getObject().get();

            Card card = cardRepository.findAllByUserIdAndStatus(1, Status.NEW);
            if (card!=null) {
                paymentService.fulfillOrder(session);
            }

        }

    }





    @GetMapping("/charge")
    public HttpEntity<?> createStripeSession() {

        Stripe.apiKey = "sk_test_51Kiz3rEhoGDMoOBR40fygRgoRauXuzCNiAQjVjT62m8VunOcTYCmk7SVBQ07AaYeoe7jXZiRw0D17kUviPfAdbYr00hNGzkbE8";

        List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();


        Card card = cardRepository.findByUserId(1);


        return paymentService.getStripeSession(1, lineItems, card);
    }







}

