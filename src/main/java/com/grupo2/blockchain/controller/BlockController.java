package com.grupo2.blockchain.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.blockchain.service.IBlockService;
import com.grupo2.blockchain.structure.Block;
import com.grupo2.blockchain.transactions.Transaction;

@RestController
@RequestMapping("/blocks")
public class BlockController {

	@Autowired
	private IBlockService<Transaction> blockService;
	
	@GetMapping("{hash}")
	public ResponseEntity get(@PathVariable("hash") String hash) throws UnsupportedEncodingException {
		return new ResponseEntity(blockService.getByHash(hash), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity getAll() throws IOException {
		return new ResponseEntity(blockService.getAll(), HttpStatus.OK);
	}
	
	@PostMapping("/transaction")
	public void save(@RequestBody Transaction t) throws IOException {
		
		Block<Transaction> block = new Block<Transaction>();
		block.setData(t);
		blockService.save(block);
	}
}
