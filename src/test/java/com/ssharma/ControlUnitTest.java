package com.ssharma;

import com.ssharma.Components.ControlUnit;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ControlUnitTest extends TestCase {

    /**
     * Create the test case
     */

    public ControlUnitTest() {
        super();
    }

    public ControlUnitTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(ControlUnitTest.class);
    }

    /**
     * Rigourous Test :-)
     */

    public void testApp() {

        ControlUnit controlUnit = new ControlUnit();

        //Test Cases for Register Destination (RegDst)
        int rFormatRegDest = controlUnit.getRegDst(0x000_000_00);
        int lwRegDest = controlUnit.getRegDst(0x8C0_000_00);
        int swRegDest = controlUnit.getRegDst(0xAC0_000_00);
        int beqRegDest = controlUnit.getRegDst(0x100_000_00);

        assertEquals(1, rFormatRegDest);
        assertEquals(0, lwRegDest);
        assertEquals(0, swRegDest); //Handling 0 as don't care condition
        assertEquals(0, beqRegDest); //Handling 0 as don't care condition


        //Test Cases for ALU Source (ALUSrc)
        int rFormatAluSrc = controlUnit.getALUSrc(0x000_000_00);
        int lwAluSrc = controlUnit.getALUSrc(0x8C0_000_00);
        int swAluSrc = controlUnit.getALUSrc(0xAC0_000_00);
        int beqAluSrc = controlUnit.getALUSrc(0x100_000_00);

        assertEquals(0, rFormatAluSrc);
        assertEquals(1, lwAluSrc);
        assertEquals(1, swAluSrc);
        assertEquals(0, beqAluSrc);

        //Test Cases for Memory To Reg (MemtoReg)
        int rFormatMemtoReg = controlUnit.getMemtoReg(0x000_000_00);
        int lwMemtoReg = controlUnit.getMemtoReg(0x8C0_000_00);
        int swMemtoReg = controlUnit.getMemtoReg(0xAC0_000_00);
        int beqMemtoReg = controlUnit.getMemtoReg(0x100_000_00);

        assertEquals(0, rFormatMemtoReg);
        assertEquals(1, lwMemtoReg);
        assertEquals(0, swMemtoReg);  //Handling 0 as don't care condition
        assertEquals(0, beqMemtoReg);  //Handling 0 as don't care condition

        //Test Cases for Register Write (RegWrite)
        int rFormatRegWrite = controlUnit.getRegWrite(0x000_000_00);
        int lwRegWrite = controlUnit.getRegWrite(0x8C0_000_00);
        int swRegWrite = controlUnit.getRegWrite(0xAC0_000_00);
        int beqRegWrite = controlUnit.getRegWrite(0x100_000_00);

        assertEquals(1, rFormatRegWrite);
        assertEquals(1, lwRegWrite);
        assertEquals(0, swRegWrite);
        assertEquals(0, beqRegWrite);

        //Test Cases for Memory Read (MemRead)
        int rFormatMemRead = controlUnit.getMemRead(0x000_000_00);
        int lwMemRead = controlUnit.getMemRead(0x8C0_000_00);
        int swMemRead = controlUnit.getMemRead(0xAC0_000_00);
        int beqMemRead = controlUnit.getMemRead(0x100_000_00);

        assertEquals(0, rFormatMemRead);
        assertEquals(1, lwMemRead);
        assertEquals(0, swMemRead);
        assertEquals(0, beqMemRead);

        //Test Cases for Memory Write (MemWrite)
        int rFormatMemWrite = controlUnit.getMemWrite(0x000_000_00);
        int lwMemWrite = controlUnit.getMemWrite(0x8C0_000_00);
        int swMemWrite = controlUnit.getMemWrite(0xAC0_000_00);
        int beqMemWrite = controlUnit.getMemWrite(0x100_000_00);

        assertEquals(0, rFormatMemWrite);
        assertEquals(0, lwMemWrite);
        assertEquals(1, swMemWrite);
        assertEquals(0, beqMemWrite);

        //Test Cases for Branch (Branch)
        int rFormatBranch = controlUnit.getBranch(0x000_000_00);
        int lwBranch = controlUnit.getBranch(0x8C0_000_00);
        int swBranch = controlUnit.getBranch(0xAC0_000_00);
        int beqBranch = controlUnit.getBranch(0x100_000_00);

        assertEquals(0, rFormatBranch);
        assertEquals(0, lwBranch);
        assertEquals(0, swBranch);
        assertEquals(1, beqBranch);

        //Test Cases for ALUOp1 (ALUOp1)
        int rFormatALUOp1 = controlUnit.getALUOp1(0x000_000_00);
        int lwALUOp1 = controlUnit.getALUOp1(0x8C0_000_00);
        int swALUOp1 = controlUnit.getALUOp1(0xAC0_000_00);
        int beqALUOp1 = controlUnit.getALUOp1(0x100_000_00);

        assertEquals(1, rFormatALUOp1);
        assertEquals(0, lwALUOp1);
        assertEquals(0, swALUOp1);
        assertEquals(0, beqALUOp1);

        //Test Cases for ALUOp0 (ALUOp0)
        int rFormatALUOp0 = controlUnit.getALUOp0(0x000_000_00);
        int lwALUOp0 = controlUnit.getALUOp0(0x8C0_000_00);
        int swALUOp0 = controlUnit.getALUOp0(0xAC0_000_00);
        int beqALUOp0 = controlUnit.getALUOp0(0x100_000_00);

        assertEquals(0, rFormatALUOp0);
        assertEquals(0, lwALUOp0);
        assertEquals(0, swALUOp0);
        assertEquals(1, beqALUOp0);
    }
}
