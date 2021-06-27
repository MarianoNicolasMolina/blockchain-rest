package com.grupo2.blockchain.repository;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo2.blockchain.structure.Block;
import com.grupo2.blockchain.utils.FileUtils;

public class BlockRepository<T> {
	
	private static final String FILENAME = "/blockChain.json";
	public static final String GENESIS_HASH = "0";

	public static Block<?> getBlock(Integer id) {
		List<Block<?>> blocks = getAll();
		
		return blocks != null ? blocks.get(0) : null;
	}
	
	public static List<Block<?>> getAll() {
		FileUtils.checkFile(FILENAME);
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Block<?>>> typeReference = new TypeReference<List<Block<?>>>(){};
		
		try {
			InputStream is = new FileInputStream(FileUtils.getFile(FILENAME));
			List<Block<?>> b = mapper.readValue(is,typeReference);
			return b;
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return null;
	}

	public static void save(List<Block<?>> blockChain) {
		FileUtils.checkFile(FILENAME);
		ObjectMapper mapper = new ObjectMapper();
		BufferedWriter out;
		
		try {
			out = new BufferedWriter(FileUtils.getFileWriter(FILENAME));
			mapper.writeValue(out, blockChain);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
