package com.grupo2.blockchain.repository;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo2.blockchain.structure.Block;

public class BlockRepository<T> {
	
	private static String FILENAME = "/repositories/blocks.json"; 
	
	public static Block<?> getBlock(Integer id) {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<Block<?>> typeReference = new TypeReference<Block<?>>(){};
		InputStream inputStream = TypeReference.class.getResourceAsStream(FILENAME);
		
		try {
			Block<?> b = mapper.readValue(inputStream,typeReference);
			System.out.println(b);
			return b;
		} catch (IOException e){
			e.printStackTrace();
		}

		return null;
	}
}
