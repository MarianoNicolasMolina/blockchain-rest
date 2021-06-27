package com.grupo2.blockchain.service;

import com.grupo2.blockchain.repository.MerkleBlockRepository;
import com.grupo2.blockchain.structure.MerkleBlock;
import com.grupo2.blockchain.transactions.HasheableTransaction;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MerkleBlockService implements IMerkleBlockService{


    @Override
    public List<MerkleBlock<HasheableTransaction>> getAll() throws IOException {
        return MerkleBlockRepository.getAll();
    }

    @Override
    public MerkleBlock<HasheableTransaction> getByHash(String hash) throws IOException {
        return MerkleBlockRepository.getBlock(hash);
    }

    @Override
    public void save(MerkleBlock<HasheableTransaction> block) throws IOException {
        //MerkleBlockRepository.save(block);
    }
}
