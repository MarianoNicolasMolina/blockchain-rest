package com.grupo2.blockchain.controller;

import com.grupo2.blockchain.structure.MerkleBlock;
import com.grupo2.blockchain.transactions.HasheableTransaction;
import com.grupo2.blockchain.transactions.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merkleblocks")
public class MerkleBlockController {


    @GetMapping("/{hash}")
    public ResponseEntity getBlockByHash(@PathVariable String hash){
        MerkleBlock<HasheableTransaction> merkleBlock = new MerkleBlock<HasheableTransaction>();

        return new ResponseEntity(merkleBlock,HttpStatus.OK);
    }

    @PostMapping("/transaction")
    public ResponseEntity createTransaction(@RequestBody HasheableTransaction transaction){

        return new ResponseEntity(null,HttpStatus.OK);
    }



}
