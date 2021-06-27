package com.grupo2.blockchain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.grupo2.blockchain.repository.BlockRepository;
import com.grupo2.blockchain.structure.Block;
import com.grupo2.blockchain.structure.Hasher;

@Service
public class BlockService<T> implements IBlockService<T> {

	@Override
	public List<Block<?>> getAll() {
		return BlockRepository.getAll();
	}

	@Override
	public Block<?> getByHash(String hash) {
		return BlockRepository.getByHash(hash);
	}

	@Override
	public void save(Block<T> block) {
		List<Block<?>> blockChain = getAll();
		
		if(blockChain != null) {
			Block<?> lastBlock = blockChain.get(blockChain.size() - 1);
			block.setPrevHash(lastBlock.getHash());
		} else {
			block.setPrevHash(BlockRepository.GENESIS_HASH);
			blockChain = new ArrayList<>();
		}
		
		if(Hasher.isValidChain(blockChain)) {
			blockChain.add(block);
			BlockRepository.save(blockChain);
		}
	}

	@Override
	public Integer getNewId() {
		// TODO Auto-generated method stub
		return null;
	}

}
