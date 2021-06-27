package com.grupo2.blockchain.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.swing.JFileChooser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo2.blockchain.structure.Block;

public class BlockRepository<T> {
	
	private static String FILENAME = "/blocks.json"; 
	private static String path;
	
	public static Block<?> getBlock(Integer id) {
		getPath();
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<Block<?>> typeReference = new TypeReference<Block<?>>(){};
		try {
			InputStream is = new FileInputStream(new File(path));
			Block<?> b = mapper.readValue(is,typeReference);
			return b;
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return null;
	}
	
	public static List<Block<?>> getAll() {
		getPath();
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Block<?>>> typeReference = new TypeReference<List<Block<?>>>(){};
		
		try {
			InputStream is = new FileInputStream(new File(path));
			List<Block<?>> b = mapper.readValue(is,typeReference);
			return b;
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return null;
	}

	public static void save(Block<?> block) {
		List<Block<?>> blocks = getAll();
		
		blocks.add(block);
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
