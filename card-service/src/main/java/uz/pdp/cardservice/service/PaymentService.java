package uz.pdp.cardservice.service;

//Asilbek Fayzullayev 19.05.2022 15:53   

import com.netflix.discovery.converters.Auto;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.cardservice.model.Card;
import uz.pdp.cardservice.model.Status;
import uz.pdp.cardservice.model.TransactionHistory;
import uz.pdp.cardservice.repository.CardRepository;
import uz.pdp.cardservice.repository.TransactionHistoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    TransactionHistoryRepository transactionHistoryRepository;

    @Autowired
    CardRepository cardRepository;

    @Transactional
    public void addTransactionHistory(Integer userId, String paymentIntent) {
        Card card =
                cardRepository.findAllByUserIdAndStatus(userId, Status.NEW);

        double totalAmount = card.getPrice()*card.getQuantity();


        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setProductId(card.getProductId());
        transactionHistory.setTotalAmount(totalAmount);
        transactionHistory.setPaymentIntent(paymentIntent);
      //  transactionHistory.setRefunded(false);

        transactionHistoryRepository.save(transactionHistory);
    }

    public boolean changeCardStatusToPurchase(Integer userId,Status firstCardStatus,Status secondCardStatus) {

        try {
            Card card = cardRepository.findAllByUserIdAndStatus(userId, firstCardStatus);

                card.setStatus(secondCardStatus);

            cardRepository.save(card);

            return true;
        } catch (Exception e) {
            e.printStackTrace();


        }
        return false;
    }

    public void fulfillOrder(Session session) {
        addTransactionHistory(Integer.valueOf(session.getClientReferenceId()), session.getPaymentIntent());
        changeCardStatusToPurchase(Integer.valueOf(session.getClientReferenceId()),Status.NEW,Status.PURCHASED);

    }


    public HttpEntity<?> getStripeSession(Integer userId, List<SessionCreateParams.LineItem> lineItems, Card card) {

            double productPrice = (card.getPrice()*100+30)/(1-2.9/100);
            SessionCreateParams.LineItem.PriceData.ProductData productData = SessionCreateParams.LineItem.PriceData.ProductData
                    .builder()
                    .setName(card.getProductId().toString())
                    .build();

            SessionCreateParams.LineItem.PriceData priceData = SessionCreateParams.LineItem.PriceData
                    .builder()
                    .setProductData(productData)
                    .setCurrency("usd")
                    .setUnitAmount((long) (productPrice))
                    .build();

            SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem
                    .builder()
                    .setPriceData(priceData)
                    .setQuantity(1L)
                    .build();


            lineItems.add(lineItem);



        SessionCreateParams params = SessionCreateParams
                .builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl("http://localhost:8080/failed")
                .setSuccessUrl("http://localhost:8080/success")
                .setClientReferenceId(card.getUserId().toString())
                .addAllLineItem(lineItems)
                .build();
        try {
            Session session = Session.create(params);
            String checkoutUrl = session.getUrl();

            return ResponseEntity.ok(checkoutUrl);

        } catch (StripeException e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();

    }
}
