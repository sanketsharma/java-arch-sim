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
    private Multiplexer multiplexer;
    private ALUControl aLUControl;
    private ALU aLU;


    public CPU(){
        halted = false;

        programCounter = new Register();
        programCounter.setValue(0x0000);
        registerFile = new RegisterFile(32);
        instructionMemory = new Memory(0xFFFF);
        dataMemory = new Memory(0xFFFF);
        controlUnit = new ControlUnit();
        signExtend = new SignExtend();
        multiplexer = new Multiplexer();
        aLUControl = new ALUControl();
        aLU = new ALU();

        logger.info("CPU created");
    }

    private int fetchInstruction(){
        return instructionMemory.getMemoryLocationValue(programCounter.getValue());
    }

    private int extract26To31(int instruction){
        return (instruction >>> 26) & 0b11_111;
    }

    private int extract21To25(int instruction){
        return (instruction >>> 21) & 0b11_111;
    }

    private int extract16To20(int instruction){
        return (instruction >>> 16) & 0b11_111;
    }

    private int extract11To15(int instruction){
        return (instruction >>> 15) & 0b11_111;
    }

    private int getALUOp(){
        int aLUOp0 = controlUnit.getALUOp0();
        int aLUOp1 = controlUnit.getALUOp1();

        int aLUOp = aLUOp0 + (aLUOp1 << 1);
        return aLUOp;
    }

    private void updateProgramCounter(int offsetForBELShifted2){
        int aLUZero = aLU.getZero();
        int branch = controlUnit.getBranch();
        int muxSelectLine = branch & aLUZero;
        int nextInstructionAddress = multiplexer.select(offsetForBELShifted2 + programCounter.getValue() + 4,
                programCounter.getValue() + 4 , muxSelectLine);
        programCounter.setValue(nextInstructionAddress);
    }

    public void run(){
        while(!halted){
            int instruction = fetchInstruction();

            int ins26To31 = extract26To31(instruction);
            controlUnit.setInput(ins26To31);

            int ins21To25 = extract21To25(instruction);
            int ins16To20 = extract16To20(instruction);

            int readData0 = registerFile.getRegisterValue(ins21To25);
            int readData1 = registerFile.getRegisterValue(ins16To20);

            int offsetForBEL16 = instruction & 0xFFFF;
            int offsetForBEL32  = signExtend.extend16To32(offsetForBEL16);

            int operand0 = readData0;
            int aLUSrc = controlUnit.getALUSrc();
            int operand1 = multiplexer.select(readData1, offsetForBEL32, aLUSrc);

            int aLUOp = getALUOp();
            int functField = instruction & 0b111_111;
            aLUControl.setInput(aLUOp, functField);

            int aLUControlInput  = aLUControl.getALUControlInput();
            aLU.setControl(aLUControlInput);

            int aluResult = aLU.compute(operand0, operand1);
            logger.debug("ALU result: " + aluResult);
            int aLUZero = aLU.getZero();

            int dataMemoryAddress = aluResult;

            int memRead = controlUnit.getMemRead();
            int readData = 0;

            if(memRead == 1){
                readData = dataMemory.getMemoryLocationValue(dataMemoryAddress);
            }

            if(controlUnit.getMemWrite() == 1){
                dataMemory.setMemoryLocationValue(aluResult, readData1);
            }

            int writeData = multiplexer.select(readData, aluResult, controlUnit.getMemtoReg());
            int regDst = controlUnit.getRegDst();
            int ins11To15 = extract11To15(instruction);
            int writeRegisterAddress = multiplexer.select(ins11To15, ins16To20, regDst);

            if(controlUnit.getRegWrite() == 1){
                registerFile.setRegisterValue(writeRegisterAddress, writeData);
            }
            updateProgramCounter(offsetForBEL32 << 2);
        }

    }

}
