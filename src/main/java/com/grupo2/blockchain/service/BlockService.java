package com.grupo2.blockchain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.grupo2.blockchain.repository.BlockRepository;
import com.grupo2.blockchain.structure.Block;

@Service
public class BlockService<T> implements IBlockService<T> {

	@Override
	public List<Block<?>> getAll() {
		return BlockRepository.getAll();
	}

	@Override
	public Block<?> getById(Integer id) {
		return BlockRepository.getBlock(id);
	}

	@Override
	public void save(Block<T> block) {
		BlockRepository.save(block);
	}

	@Override
	public Integer getNewId() {
		// TODO Auto-generated method stub
		return null;
	}

}
