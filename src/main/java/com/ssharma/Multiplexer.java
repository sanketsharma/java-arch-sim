package com.ssharma;

import com.ssharma.Exceptions.InvalidSignalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Multiplexer {
    public static final Logger logger = LoggerFactory.getLogger(Multiplexer.class);

    public Multiplexer(){
        logger.info("2 to 1 multiplexer created");
    }

    public int select(int input0, int input1, int control){
        switch (control){
            case 0:
                return input0;

            case 1:
                return input1;

            default: //Error
                String message = "Invalid control "+ control + " encountered";
                logger.error(message);
                throw new InvalidSignalException(message);
        }
    }
}
