package com.ssharma;

import com.ssharma.Exceptions.InvalidBusException;
import com.ssharma.Exceptions.InvalidBusSizeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ALUControl {
    private static final Logger logger = LoggerFactory.getLogger(ALUControl.class);
    private final int relevantALUControlBits = 4;

    public ALUControl(){
        logger.info("ALUControl initialized");
    }

    public Bus getALUControlInput(Bus aLUOp, Bus functField) throws InvalidBusSizeException{
        if(aLUOp.getRelevantLSBs() != 3 || functField.getRelevantLSBs() != 6){
            String errorMessage = "Unexpected bus size encountered";
            logger.error(errorMessage);
            throw new InvalidBusSizeException(errorMessage);
        }
        Bus aLUControlInput = new Bus();
        aLUControlInput.setRelevantLSBs(relevantALUControlBits);
        switch (aLUOp.getBits() & 0b111) {
            case 0b00:
                //Instruction OpCode: LW
                //Instruction operation: load word

                //Instruction OpCode: SW
                //Instruction operation: store word

                aLUControlInput.setBits(0b0010);// Desired ALU action: add
                break;

            case 0b01:
                //Instruction OpCode: Branch Equal
                aLUControlInput.setBits(0b110);// Desired ALU action: subtract
                break;

            case 0b10: //Instruction opcode: R-type
                switch (functField.getBits() & 0b111111){
                    case 0b100000: //Instruction operation: add
                        aLUControlInput.setBits(0b0010);// Desired ALU action: add
                        break;

                    case 0b100010: //Instruction operation: subtract
                        aLUControlInput.setBits(0b0110);// Desired ALU action: subtract
                        break;

                    case 0b100100: //Instruction operation: AND
                        aLUControlInput.setBits(0b0000); //Desired ALU option: AND
                        break;

                    case 0b100101: //Instruction operation: OR
                        aLUControlInput.setBits(0b0001); //Desired ALU option: OR
                        break;

                    case 0b101010: //Instruction operation: set or less than
                        aLUControlInput.setBits(0b0111); //Desired ALU option: set or less than
                        break;

                    default: //Error
                        String message = "Invalid functField "+ functField + " encountered";
                        logger.error(message);
                        throw new InvalidBusException(message);
                }
                break;
            default: //Error
                String message = "Invalid aLUOp "+ aLUOp + " encountered";
                logger.error(message);
                throw new InvalidBusException(message);
        }
        return aLUControlInput;
    }
}
