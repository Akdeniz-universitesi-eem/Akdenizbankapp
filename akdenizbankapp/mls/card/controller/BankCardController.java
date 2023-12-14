package com.akdenizbank.mls.card.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akdenizbank.mls.card.BankCard;
import com.akdenizbank.mls.card.service.BankCardService;
import com.akdenizbank.mls.generic.rest.GenericApiResponse;

@RestController
@RequestMapping("/api/v1/bank-cards")
public class BankCardController {

    @Autowired
    private BankCardService bankCardService;

    //TODO : CREATE BANK CARD METHOD -completed
    //TODO : UPDATE BANK CARD METHOD -completed
    public GenericApiResponse createBankCard(@RequestBody CreateBankCard newAcc){
        BankCard bankCard=new BankCard();
        bankCard.setNameoncard(newAcc.getNameoncard());
        bankCard.setCvc(newAcc.getCvc());
        bankCard.setCardnumber(newAcc.getCardnumber());
        bankCard.setExpiredate(newAcc.getExpiredate());
        return new GenericApiResponse(200,"Success","12345678",bankCard);

    @PatchMapping

    public GenericApiResponse updateBankCard(@RequestBody UpdateBankCard updAcc){
        BankCard bankCardInDB=this.bankCardService.getById(id);
           if (bankCardInDB == null) {
                throw new RuntimeException("No such bank card");
            }
        bankCardInDB.setNameoncard(updAcc.getNameoncard());
        bankCardInDB.setCvc(updAcc.getCvc());
        bankCardInDB.setExpiredate(updAcc.getExpiredate());
        bankCardInDB.setCardnumber(updAcc.getCardnumber());
        bankCardInDB=bankCardService.create(bankCardInDB);
        return new GenericApiResponse(200,"Success","12345678",bankCardInDB);
    @GetMapping

    public GenericApiResponse getAlBankCards(Pageable pageable) {
        Page<BankCard> bankCardsPage = this.bankCardService.getAll(pageable);
        return new GenericApiResponse(200, "Success", "345732945213", bankCardsPage);
    }

    @GetMapping("/{id}")
    public GenericApiResponse getById(@PathVariable String id) {
        BankCard bankCardInDB = this.bankCardService.getById(id);
        if (bankCardInDB == null) {
            throw new RuntimeException("No Such Bank Card");
        }
        return new GenericApiResponse(200, "Success", "23097452893", bankCardInDB);
    }

    @DeleteMapping("/{id}")
    public GenericApiResponse deleteCard(@PathVariable String id) {
        this.bankCardService.delete(id);
        return new GenericApiResponse(200, "Success", "34265782");
    }

}
