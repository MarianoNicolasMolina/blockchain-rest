package com.grupo2.blockchain.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.grupo2.blockchain.structure.Block;

public interface IBlockService<T> {

	List<Block<?>> getAll() throws IOException;
	Block<?> getByHash(String hash) throws UnsupportedEncodingException;
	void save(Block<T> block) throws IOException;
	Integer getNewId();
}
