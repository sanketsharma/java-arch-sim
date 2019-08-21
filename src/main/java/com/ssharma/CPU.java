package com.ssharma;

import com.ssharma.Components.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CPU {
    private static final Logger logger = LoggerFactory.getLogger(CPU.class);
    private boolean halted;

    private Register programCounter;
    private RegisterFile registerFile;
    private Memory instructionMemory;
    private Memory dataMemory;
    private ControlUnit controlUnit;
    private SignExtend signExtend;
    private ALU alu;


    public CPU(){
        halted = false;

        programCounter = new Register(); //TODO: What is the initial value?
        programCounter.setValue(0x0000);
        registerFile = new RegisterFile(32);
        instructionMemory = new Memory(0xFFFF);
        dataMemory = new Memory(0xFFFF);
        controlUnit = new ControlUnit();
        signExtend = new SignExtend();
        alu = new ALU();

        logger.info("CPU created");
    }

    private int getInstruction(Register programCounter){
        return instructionMemory.getMemoryLocationValue(programCounter.getValue());
    }

    private int getOp0(int instruction){
        int address = (instruction >>> 21) & 0b11_111;
        return registerFile.getRegisterValue(address);
    }

    private int getOp1(int instruction){
        int address = (instruction >>> 16) & 0b11_111;
        return registerFile.getRegisterValue(address);
    }

    private void updateProgramCounter(){

    }

    public void run(){
        while(!halted){
            int instruction = getInstruction(programCounter);
            controlUnit.setInput((instruction >>> 26) & 0x111_111);

            int op0 = getOp0(instruction);
            int op1 = getOp1(instruction);


            updateProgramCounter();
        }

    }

}
