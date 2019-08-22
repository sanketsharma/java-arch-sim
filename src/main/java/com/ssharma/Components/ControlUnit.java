package com.ssharma.Components;

import com.ssharma.Exceptions.InvalidSignalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TODO: Implement jump instruction
public class ControlUnit {
    private static final Logger logger = LoggerFactory.getLogger(ControlUnit.class);
    private final int rFormat = 0;
    private final int lw = 0b100_011;
    private final int sw = 0b101_011;
    private final int beq = 0b000_100;
    private int input;

    public ControlUnit() {
        logger.info("ControlUnit initialized");
    }

    public void setInput(int input) {
        this.input = input;
    }

    public int getRegDst(){
        switch (input){
            case rFormat:
                return 1;

            case lw:
                return 0;

            case sw:
                return 0; //We don't care

            case beq:
                return 0; //We don't care

            default: //Error
                String message = "Invalid input " + input + " encountered";
                logger.error(message);
                throw new InvalidSignalException(message);
        }
    }

    public int getALUSrc(){
        switch (input){
            case rFormat:
                return 0;

            case lw:
                return 1;

            case sw:
                return 1;

            case beq:
                return 0;

            default: //Error
                String message = "Invalid input " + input + " encountered";
                logger.error(message);
                throw new InvalidSignalException(message);
        }
    }

    public int getMemtoReg(){
        switch (input){
            case rFormat:
                return 0;

            case lw:
                return 1;

            case sw:
                return 0; //We don't care

            case beq:
                return 0; //We don't care

            default: //Error
                String message = "Invalid input " + input + " encountered";
                logger.error(message);
                throw new InvalidSignalException(message);
        }
    }

    public int getRegWrite(){
        switch (input){
            case rFormat:
                return 1;

            case lw:
                return 1;

            case sw:
                return 0;

            case beq:
                return 0;

            default: //Error
                String message = "Invalid input " + input + " encountered";
                logger.error(message);
                throw new InvalidSignalException(message);
        }
    }

    public int getMemRead(){
        switch (input){
            case rFormat:
                return 0;

            case lw:
                return 1;

            case sw:
                return 0;

            case beq:
                return 0;

            default: //Error
                String message = "Invalid input " + input + " encountered";
                logger.error(message);
                throw new InvalidSignalException(message);
        }
    }

    public int getMemWrite(){
        switch (input){
            case rFormat:
                return 0;

            case lw:
                return 0;

            case sw:
                return 1;

            case beq:
                return 0;

            default: //Error
                String message = "Invalid input " + input + " encountered";
                logger.error(message);
                throw new InvalidSignalException(message);
        }
    }

    public int getBranch(){
        switch (input){
            case rFormat:
                return 0;

            case lw:
                return 0;

            case sw:
                return 0;

            case beq:
                return 1;

            default: //Error
                String message = "Invalid input " + input + " encountered";
                logger.error(message);
                throw new InvalidSignalException(message);
        }
    }

    public int getALUOp1(){
        switch (input){
            case rFormat:
                return 1;

            case lw:
                return 0;

            case sw:
                return 0;

            case beq:
                return 0;

            default: //Error
                String message = "Invalid input " + input + " encountered";
                logger.error(message);
                throw new InvalidSignalException(message);
        }
    }

    public int getALUOp0(){
        switch (input){
            case rFormat:
                return 0;

            case lw:
                return 0;

            case sw:
                return 0;

            case beq:
                return 1;

            default: //Error
                String message = "Invalid input " + input + " encountered";
                logger.error(message);
                throw new InvalidSignalException(message);
        }
    }
}
