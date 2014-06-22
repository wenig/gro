package org.battlehack.gro.services;

import com.braintreegateway.*;
import org.battlehack.gro.domain.entities.Exchange;
import org.battlehack.gro.domain.entities.UserData;
import org.battlehack.gro.domain.repositories.ExchangeRepository;
import org.battlehack.gro.domain.repositories.ItemRepository;
import org.battlehack.gro.tos.CreateOfferTO;
import org.battlehack.gro.tos.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by aakhmerov on 22/06/14.
 *
 * Service that performs logical operations behind  exchange of users
 */
@Component
public class ExchangeService {

    private static final String SUCCESS_STATUS = "200 OK";
    private static BraintreeGateway gateway = new BraintreeGateway(
            Environment.SANDBOX,
            "hkw58gnp9wzywhd2",
            "f5x5rmhfk4hvhpv8",
            "31a636220e53e8336a9975672d2e9573"
    );

    @Autowired
    private ExchangeRepository exchangeRepository;

    @Autowired
    private ItemRepository itemRepository;

    /**
     *
     * @param createOfferTO
     * @return
     */
    public Exchange createOffer(CreateOfferTO createOfferTO) {
        Exchange toCreate = this.composeExchange(createOfferTO);
        return exchangeRepository.save(toCreate);
    }

    private Exchange composeExchange(CreateOfferTO createOfferTO) {
        Exchange result  = new Exchange();

        result.setDestinationItem(itemRepository.findWithUser(createOfferTO.getDestinationItem()));
        result.setSourceItem(itemRepository.findWithUser(createOfferTO.getSourceItem()));
        result.setDiffAmount(createOfferTO.getDiff());

        return result;
    }

    /**
     * find all offers for specified user
     * @param id
     * @return
     */
    public Collection<Exchange> findUserOffers(Integer id) {
        return exchangeRepository.findForDestinationUser(Long.valueOf(id));
    }

    /**
     * Confirm exchange offer and perform transaction
     * @param id
     * @return
     */
    public Status confirmOffer (Integer id) {
        Status result = new Status();
        Exchange toConfirm = exchangeRepository.findOneWithItemsAndUsers(Long.valueOf(id));

        UserData sourceUser = toConfirm.getSourceItem().getUserData();
        
        BigDecimal amount = new BigDecimal(toConfirm.getDiffAmount());

        TransactionRequest debitTransaction = new TransactionRequest()
                .amount(amount)
                .creditCard()
                .number(sourceUser.getEconomicalData().getNumber())
                .cvv(sourceUser.getEconomicalData().getCvv())
                .expirationMonth(sourceUser.getEconomicalData().getExpiryMonth())
                .expirationYear(sourceUser.getEconomicalData().getExpiryYear())
                .done()
                .options()
                .submitForSettlement(true)
                .done();

        Result<Transaction> debitTransactionResult = gateway.transaction().sale(debitTransaction);

        if (debitTransactionResult.isSuccess()) {
            result.setStatus(SUCCESS_STATUS);

            UserData destinationUser = toConfirm.getDestinationItem().getUserData();
            TransactionRequest creditTransaction = new TransactionRequest()
                    .amount(amount)
                    .creditCard()
                    .number(destinationUser.getEconomicalData().getNumber())
                    .cvv(destinationUser.getEconomicalData().getCvv())
                    .expirationDate(destinationUser.getEconomicalData().getExpiryMonth() + "/" + destinationUser.getEconomicalData().getExpiryYear())
                    .done();

            Result<Transaction> creditTransactionResult = gateway.transaction().credit(creditTransaction);

            if (creditTransactionResult.isSuccess()) {
                result.setStatus(SUCCESS_STATUS);
            } else {
                result.setStatus(creditTransactionResult.getMessage());
            }

        } else {
            result.setStatus(debitTransactionResult.getMessage());
        }

        return result;
    }
}
