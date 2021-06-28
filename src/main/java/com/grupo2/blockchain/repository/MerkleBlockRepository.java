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
    private static final String FILENAME = "/merkleBlockchain.json";
    private static final String PENDING_TRANSACTIONS_FILENAME = "/pendingTransactions.json";
    private static final String LOGGER_HEADER = "[MerkleBlockRepository] - ";

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

        try {
	        InputStream is = new FileInputStream(FileUtils.getFile(FILENAME));
	        List<MerkleBlock<HasheableTransaction>> merkleBlockList = mapper.readValue(is,typeReference);
	        return merkleBlockList;
	    } catch (IOException e) {
	    	e.printStackTrace();
			System.out.println(LOGGER_HEADER + "La cadena merkle está vacía.");
		}
        
        return null;
    }

    public static void save(List<MerkleBlock<HasheableTransaction>> blockChain) throws IOException {
        FileUtils.checkFile(FILENAME);
        ObjectMapper mapper = new ObjectMapper();
        BufferedWriter out;

        out = new BufferedWriter(FileUtils.getFileWriter(FILENAME));
        mapper.writeValue(out, blockChain);
        out.close();
    }

	public static List<HasheableTransaction> getPendingTransactions() throws IOException {
		FileUtils.checkFile(PENDING_TRANSACTIONS_FILENAME);
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<HasheableTransaction>> typeReference = new TypeReference<List<HasheableTransaction>>() {};

        try {
        	InputStream is = new FileInputStream(FileUtils.getFile(PENDING_TRANSACTIONS_FILENAME));
        	List<HasheableTransaction> pendingTransactionsList = mapper.readValue(is,typeReference);
        	return pendingTransactionsList;
		} catch (IOException e) {
			System.out.println(LOGGER_HEADER + "No hay transacciones pendientes.");
		}

        return null;
	}

	public static void savePendingTransactions(List<HasheableTransaction> pendingTransactions) throws IOException {
		FileUtils.checkFile(PENDING_TRANSACTIONS_FILENAME);
        ObjectMapper mapper = new ObjectMapper();
        BufferedWriter out;

        out = new BufferedWriter(FileUtils.getFileWriter(PENDING_TRANSACTIONS_FILENAME));
        mapper.writeValue(out, pendingTransactions);
        out.close();
	}

}
