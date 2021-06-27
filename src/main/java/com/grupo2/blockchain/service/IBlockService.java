package com.grupo2.blockchain.service;

import java.util.List;

import com.grupo2.blockchain.structure.Block;

public interface IBlockService {

	List<Block> getAll();
	Block getById(Integer id);
	void save(Block block);
	Integer getNewId();
}
