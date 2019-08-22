package com.ssharma.Components;

import com.ssharma.Exceptions.InvalidSignalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ALUControl {
    private static final Logger logger = LoggerFactory.getLogger(ALUControl.class);
    private final int relevantALUControlBits = 4;
    private int aLUOp;
    private int functField;

    public ALUControl(){
        logger.info("ALUControl initialized");
    }

    public void setInput(int aLUOp, int functField){
        this.aLUOp = aLUOp;
        this.functField = functField;
    }
    public int getALUControlInput(){
        switch (aLUOp & 0b11) {
            case 0b00:
                //Instruction OpCode: LW
                //Instruction operation: load word

                //Instruction OpCode: SW
                //Instruction operation: store word
                return 0b0010;// Desired ALU action: add

            case 0b01:
                //Instruction OpCode: Branch Equal
                return 0b110;// Desired ALU action: subtract

            case 0b10: //Instruction opcode: R-type
                switch (functField & 0b111111){
                    case 0b100000: //Instruction operation: add
                        return 0b0010;// Desired ALU action: add

                    case 0b100010: //Instruction operation: subtract
                        return 0b0110;// Desired ALU action: subtract

                    case 0b100100: //Instruction operation: AND
                        return 0b0000; //Desired ALU option: AND

                    case 0b100101: //Instruction operation: OR
                        return 0b0001; //Desired ALU option: OR

                    case 0b101010: //Instruction operation: set or less than
                        return 0b0111; //Desired ALU option: set or less than

                    default: //Error
                        String message = "Invalid functField "+ functField + " encountered";
                        logger.error(message);
                        throw new InvalidSignalException(message);
                }
            default: //Error
                String message = "Invalid aLUOp "+ aLUOp + " encountered";
                logger.error(message);
                throw new InvalidSignalException(message);
        }
    }
}
