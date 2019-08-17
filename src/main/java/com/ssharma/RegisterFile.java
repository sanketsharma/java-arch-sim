package com.ssharma;

import com.ssharma.Exceptions.InvalidAddressException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class RegisterFile {
    private static final Logger logger = LoggerFactory.getLogger(RegisterFile.class);
    private final List<Register> registers;

    public RegisterFile() {
        this.registers = new ArrayList<>();
        for(int i = 0; i < 32; i++){
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
        return registers.get(i).getData();
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
