package com.ssharma.Components;

import com.ssharma.Exceptions.InvalidSignalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ALU {
    private static final Logger logger = LoggerFactory.getLogger(ALU.class);
    private static final int mask = 0x00_00_00_0F;
    private int control;
    private int zero = 0;

    /* Function                            ALU control lines */
    private static final int AND =              0b00_00;
    private static final int OR =               0b00_01;
    private static final int ADD =              0b00_10;
    private static final int SUBTRACT =         0b01_10;
    private static final int SET_ON_LESS_THAN = 0b01_11;
    private static final int NOR =              0b11_00;

    public void setControl(int control) {
        this.control = control;
    }

    public int compute(int op0, int op1){
        if(op0 == op1){
            zero = 1;
        } else {
            zero = 0;
        }
        switch(control & mask){
            case AND:
                return op0 & op1;

            case OR:
                return op0 | op1;

            case ADD:
                return op0 + op1;

            case SUBTRACT:
                return op0 - op1;

            case SET_ON_LESS_THAN:
                if(op0 < op1){
                    return 1;
                } else {
                    return 0;
                }

            default:
                String message = "Unexpected control " + String.format("0x%08X", control) + " encountered";
                logger.error(message);
                throw new InvalidSignalException(message);
        }
    }

    public int getZero(){
        return zero;
    }
}
