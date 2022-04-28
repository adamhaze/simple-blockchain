package io.collective.basic;

import java.security.NoSuchAlgorithmException;

public class Blockchain {
    private int chain_size = 0;
    private Block[] blocks = new Block[10];

    public boolean isEmpty() {
        if (chain_size > 0){ return false; }
        else { return true; }

    }

    public void add(Block block) {
        blocks[chain_size] = block;
        chain_size++;
    }

    public int size() {
        return chain_size;
    }

    public boolean isValid() throws NoSuchAlgorithmException {
        for (int i=0; i < chain_size; i++) {

            if (!isMined(blocks[i])){ return false;}
            if (i > 0 && !blocks[i].getPreviousHash().equals(blocks[i-1].getHash())){ return false; }


        }

        // todo - check an empty chain

        // todo - check a chain of one

        // todo - check a chain of many

        return true;
    }

    /// Supporting functions that you'll need.

    public static Block mine(Block block) throws NoSuchAlgorithmException {
        Block mined = new Block(block.getPreviousHash(), block.getTimestamp(), block.getNonce());

        while (!isMined(mined)) {
            mined = new Block(mined.getPreviousHash(), mined.getTimestamp(), mined.getNonce() + 1);
        }
        return mined;
    }

    public static boolean isMined(Block minedBlock) throws NoSuchAlgorithmException {
        return minedBlock.getHash().startsWith("00") && minedBlock.getHash().equals(minedBlock.calculatedHash());
    }
}