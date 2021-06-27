package com.grupo2.blockchain.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo2.blockchain.structure.MerkleBlock;
import com.grupo2.blockchain.transactions.HasheableTransaction;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

public class MerkleBlockRepository {
    private static String FILENAME = "/merkleBlockchain.json";
    private static String path;

    public static MerkleBlock<HasheableTransaction> getBlock(String hash) throws IOException {
        List<MerkleBlock<HasheableTransaction>> allBlocks = getAll();

        for(MerkleBlock<HasheableTransaction> block : allBlocks){
            if(block.getHash().equals(hash))
                return block;
        }

        return null;
    }

    public static List<MerkleBlock<HasheableTransaction>> getAll() throws IOException {
        getPath();
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<MerkleBlock<HasheableTransaction>>> typeReference = new TypeReference<List<MerkleBlock<HasheableTransaction>>>() {};

        InputStream is = new FileInputStream(path);
        List<MerkleBlock<HasheableTransaction>> merkleBlockList = mapper.readValue(is,typeReference);

        return merkleBlockList;
    }

    public static void save(MerkleBlock<HasheableTransaction> block) throws IOException {
        List<MerkleBlock<HasheableTransaction>> blockChain = getAll();
        blockChain.add(block);


    }

    public static void getPath() {
        path = new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "/blockChain";
        try {
            path = URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        File fi = new File(path);
        if(!fi.exists()) {
            fi.mkdir();
        }

        path += FILENAME;
        try {
            path = URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        createFile(path);
    }

    public static void createFile(String decodedPath) {
        File fi = new File(decodedPath);

        if(!fi.exists()) {
            try {
                fi.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
