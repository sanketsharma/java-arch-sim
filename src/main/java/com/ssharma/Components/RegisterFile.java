package com.ssharma.Components;

import com.ssharma.Exceptions.InvalidAddressException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class RegisterFile {
    private static final Logger logger = LoggerFactory.getLogger(RegisterFile.class);
    private final List<Register> registers;

    public RegisterFile(int size) {
        this.registers = new ArrayList<>();
        for(int i = 0; i < size; i++){
            registers.add(new Register());
        }
        logger.debug("Register file initialized");
    }

    public int getRegisterValue(int i){
        if (i < 0 || i > registers.size() - 1){
            String errorMessage = "Invalid register "+ i + " encountered";
            logger.error(errorMessage);
            throw new InvalidAddressException(errorMessage);
        }
        return registers.get(i).getValue();
    }

    public void setRegisterValue(int i, int value){
        if (i < 0 || i > registers.size() - 1){
            String errorMessage = "Invalid register "+ i + " encountered";
            logger.error(errorMessage);
            throw new InvalidAddressException(errorMessage);
        }
        registers.get(i).setValue(value);
    }
}
