package com.grupo2.blockchain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping("{id}")
	public Block<?> get(@PathVariable("id") int id) {
		return blockService.getById(id);
	}
	
	@GetMapping
	public List<Block<?>> getAll() {
		return blockService.getAll();
	}
	
	@PostMapping("/transaction")
	public void save(@RequestBody Transaction t) {
		
		Block<Transaction> block = new Block<Transaction>();
		block.setData(t);
		blockService.save(block);
	}
}
