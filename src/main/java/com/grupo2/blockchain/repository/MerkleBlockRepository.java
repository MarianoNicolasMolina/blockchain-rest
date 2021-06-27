package com.grupo2.blockchain.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo2.blockchain.structure.MerkleBlock;
import com.grupo2.blockchain.transactions.HasheableTransaction;
import com.grupo2.blockchain.utils.FileUtils;


import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MerkleBlockRepository {
    private static String FILENAME = "/merkleBlockchain.json";

    public static MerkleBlock<HasheableTransaction> getBlock(String hash) throws IOException {

        List<MerkleBlock<HasheableTransaction>> allBlocks = getAll();

        for(MerkleBlock<HasheableTransaction> block : allBlocks){
            if(block.getHash().equals(hash))
                return block;
        }

        return null;
    }

    public static List<MerkleBlock<HasheableTransaction>> getAll() throws IOException {
        FileUtils.checkFile(FILENAME);
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<MerkleBlock<HasheableTransaction>>> typeReference = new TypeReference<List<MerkleBlock<HasheableTransaction>>>() {};

        InputStream is = new FileInputStream(FileUtils.getFile(FILENAME));
        List<MerkleBlock<HasheableTransaction>> merkleBlockList = mapper.readValue(is,typeReference);

        return merkleBlockList;
    }

    public static void save(List<MerkleBlock<HasheableTransaction>> blockChain) throws IOException {
        FileUtils.checkFile(FILENAME);
        ObjectMapper mapper = new ObjectMapper();
        BufferedWriter out;

        out = new BufferedWriter(FileUtils.getFileWriter(FILENAME));
        mapper.writeValue(out, blockChain);
        out.close();
    }

}
