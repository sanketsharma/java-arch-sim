package com.ssharma;

import com.ssharma.Exceptions.InvalidSignalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ControlUnit {
    private static final Logger logger = LoggerFactory.getLogger(ControlUnit.class);
    private final int  mask = 0xFC_000_000; //0b11111100000000000000000000000000
    private final int rFormat = 0;
    private final int lw = 0b100_011;
    private final int sw = 0b101_011;
    private final int beq = 0b000_100;

    public ControlUnit() {
        logger.info("ControlUnit initialized");
    }

    public int getRegDst(int instruction){
        int op = (instruction & mask) >>> 26;
        switch (op){
            case rFormat:
                return 1;

            case lw:
                return 0;

            case sw:
                return 0; //We don't care

            case beq:
                return 0; //We don't care

            default: //Error
                String message = "Invalid instruction "+ instruction + " encountered";
                logger.error(message);
                throw new InvalidSignalException(message);
        }
    }

    public int getALUSrc(int instruction){
        int op = (instruction & mask) >>> 26;
        switch (op){
            case rFormat:
                return 0;

            case lw:
                return 1;

            case sw:
                return 1;

            case beq:
                return 0;

            default: //Error
                String message = "Invalid instruction "+ instruction + " encountered";
                logger.error(message);
                throw new InvalidSignalException(message);
        }
    }

    public int getMemtoReg(int instruction){
        int op = (instruction & mask) >>> 26;
        switch (op){
            case rFormat:
                return 0;

            case lw:
                return 1;

            case sw:
                return 0; //We don't care

            case beq:
                return 0; //We don't care

            default: //Error
                String message = "Invalid instruction "+ instruction + " encountered";
                logger.error(message);
                throw new InvalidSignalException(message);
        }
    }

    public int getRegWrite(int instruction){
        int op = (instruction & mask) >>> 26;
        switch (op){
            case rFormat:
                return 1;

            case lw:
                return 1;

            case sw:
                return 0;

            case beq:
                return 0;

            default: //Error
                String message = "Invalid instruction "+ instruction + " encountered";
                logger.error(message);
                throw new InvalidSignalException(message);
        }
    }

    public int getMemRead(int instruction){
        int op = (instruction & mask) >>> 26;
        switch (op){
            case rFormat:
                return 0;

            case lw:
                return 1;

            case sw:
                return 0;

            case beq:
                return 0;

            default: //Error
                String message = "Invalid instruction "+ instruction + " encountered";
                logger.error(message);
                throw new InvalidSignalException(message);
        }
    }

    public int getMemWrite(int instruction){
        int op = (instruction & mask) >>> 26;
        switch (op){
            case rFormat:
                return 0;

            case lw:
                return 0;

            case sw:
                return 1;

            case beq:
                return 0;

            default: //Error
                String message = "Invalid instruction "+ instruction + " encountered";
                logger.error(message);
                throw new InvalidSignalException(message);
        }
    }

    public int getBranch(int instruction){
        int op = (instruction & mask) >>> 26;
        switch (op){
            case rFormat:
                return 0;

            case lw:
                return 0;

            case sw:
                return 0;

            case beq:
                return 1;

            default: //Error
                String message = "Invalid instruction "+ instruction + " encountered";
                logger.error(message);
                throw new InvalidSignalException(message);
        }
    }

    public int getALUOp1(int instruction){
        int op = (instruction & mask) >>> 26;
        switch (op){
            case rFormat:
                return 1;

            case lw:
                return 0;

            case sw:
                return 0;

            case beq:
                return 0;

            default: //Error
                String message = "Invalid instruction "+ instruction + " encountered";
                logger.error(message);
                throw new InvalidSignalException(message);
        }
    }

    public int getALUOp0(int instruction){
        int op = (instruction & mask) >>> 26;
        switch (op){
            case rFormat:
                return 0;

            case lw:
                return 0;

            case sw:
                return 0;

            case beq:
                return 1;

            default: //Error
                String message = "Invalid instruction "+ instruction + " encountered";
                logger.error(message);
                throw new InvalidSignalException(message);
        }
    }
}
