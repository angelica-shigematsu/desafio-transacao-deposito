package br.com.tgid.javajunior.controllers;

import br.com.tgid.javajunior.dtos.ReceiveDataTransition;
import br.com.tgid.javajunior.dtos.ReceiveTransition;
import br.com.tgid.javajunior.models.Transaction;
import br.com.tgid.javajunior.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="api/transition")
public class TransitionController {

    @Autowired
    private TransactionService transitionService;

    @PostMapping(value="/add")
    public ResponseEntity<Transaction> createJoinCompanyClient(@RequestBody ReceiveDataTransition data) {
        Transaction createdTransation = transitionService.create(data);

        return new ResponseEntity<>(createdTransation, HttpStatus.CREATED);
    }

    @PostMapping(value="/withdraw")
    public ResponseEntity<Double> receiveWithdraw (@RequestBody ReceiveTransition transition) {
        double value = transitionService.getWithdraw(transition.getCpf(), transition.getCnpj(), transition.getValue());

        return new ResponseEntity<>(value, HttpStatus.OK);
    }

    @PostMapping(value="/deposit")
    public ResponseEntity<Object> receiveDeposit (@RequestBody ReceiveTransition transition) {
        try{
            double value = transitionService.setDeposit(transition.getCpf(), transition.getCnpj(), transition.getValue());

            return new ResponseEntity<>(value, HttpStatus.OK);
            }catch(Exception error) {
            return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
