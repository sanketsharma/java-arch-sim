package com.ssharma;

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
        return registers.get(i).getData();
    }

    public void setRegisterValue(int i, int value){
        registers.get(i).setValue(value);
    }
}
