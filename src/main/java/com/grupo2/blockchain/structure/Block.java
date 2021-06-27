package com.grupo2.blockchain.structure;

import java.util.Date;

import com.grupo2.blockchain.transactions.Transaction;

public class Block {
    private String hash;
    private String prevHash;
    private long timeStamp;

    private Transaction data;

    public Block(){}

    public Block(final String prevHash, final Transaction data){
        this.prevHash = prevHash;
        this.data = data;
        Date today = new Date();
        this.timeStamp = today.getTime();
        this.hash = recalculateHash();

    }

    public String getHash() {
        return hash;
    }

    public void setHash(final String hash) {
        this.hash = hash;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public void setPrevHash(final String prevHash) {
        this.prevHash = prevHash;
    }

    public Transaction getData() {
        return data;
    }

    public void setData(final Transaction data) {
        this.data = data;
        this.hash = Hasher.hashSHA256Hex(getPrevHash() + data.toString() + getTimeStamp());
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String recalculateHash() {
        return Hasher.hashSHA256Hex(getPrevHash() + getData().toString() + getTimeStamp());
    }
}
