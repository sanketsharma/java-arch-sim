package com.ssharma.Components;

import com.ssharma.Exceptions.InvalidSignalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignExtend {
    public static final Logger logger = LoggerFactory.getLogger(SignExtend.class);

    public SignExtend(){
        logger.info("16 bit to 32 bit sign extend created");
    }

    public int extend16To32(int input){
        if((input >>> 16) != 0){
            String message = "Invalid input "+ input + " encountered";
            logger.error(message);
            throw new InvalidSignalException(message);
        }
        int msb =  (input >>> 15) & 0b1;
        if (msb == 0b0){
            return input;
        } else {
            return input | 0xFFFF0000;
        }
    }
}
