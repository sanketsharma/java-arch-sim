package com.ssharma;

import com.ssharma.Exceptions.InvalidAddressException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Memory {
    private static Logger logger = LoggerFactory.getLogger(Memory.class);
    private int[] cells;
    private int size;

    public Memory(int size) {
        this.size = size;
        this.cells = new int[size];
        logger.debug("Memory Initialized");
    }

    public int getMemoryLocationValue(int address){
        if (address < 0 || address > size - 1){
            String errorMessage = "Invalid memory address "+ address + " encountered";
            logger.error(errorMessage);
            throw new InvalidAddressException(errorMessage);
        }
        return cells[address];
    }

    public void setMemoryLocationValue(int address, int value){
        if (address < 0 || address > size - 1){
            String errorMessage = "Invalid memory address "+ address + " encountered";
            logger.error(errorMessage);
            throw new InvalidAddressException(errorMessage);
        }
        cells[address] = value;
    }
}
