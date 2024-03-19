package br.com.tgid.javajunior.controllers;

import br.com.tgid.javajunior.dtos.ReceiveDataTransition;
import br.com.tgid.javajunior.dtos.ReceiveTransition;
import br.com.tgid.javajunior.models.Transaction;
import br.com.tgid.javajunior.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="api/transaction")
public class TransitionController {

    @Autowired
    private TransactionService transitionService;

    @PostMapping(value="/add")
    public ResponseEntity<Object> createJoinCompanyClient(@RequestBody ReceiveDataTransition data) {
        try {
            Transaction createdTransation = transitionService.create(data);

            return new ResponseEntity<>(createdTransation, HttpStatus.CREATED);
        }catch(Exception error) {
            return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value="/withdraw")
    public ResponseEntity<Object> receiveWithdraw (@RequestBody ReceiveTransition transition) {
        try {
            double value = transitionService.getWithdraw(transition.getCpf(), transition.getCnpj(), transition.getValue());

            return new ResponseEntity<>(value, HttpStatus.OK);
        } catch (Exception error) {
            return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value="/deposit")
    public ResponseEntity<Object> receiveDeposit (@RequestBody ReceiveTransition transition) {
        try{
            double value = transitionService.setDeposit(transition.getCpf(), transition.getCnpj(), transition.getValue());

            return new ResponseEntity<>(value, HttpStatus.OK);
            }catch(Exception error) {
            return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
