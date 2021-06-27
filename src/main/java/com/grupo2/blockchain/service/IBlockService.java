package com.grupo2.blockchain.service;

import java.util.List;

import com.grupo2.blockchain.structure.Block;

public interface IBlockService<T> {

	List<Block<?>> getAll();
	Block<?> getById(Integer id);
	void save(Block<T> block);
	Integer getNewId();
}
