package com.ssharma;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ALUControlTest extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */

    public ALUControlTest() {
        super();
    }

    public ALUControlTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(ALUControlTest.class);
    }

    /**
     * Rigourous Test :-)
     */

    public void testApp() {
        int aluOp0 = 0, aluOp1 = 1, aluOp2 = 2;
        int functField1 = 32, functField2 = 34, functField3 = 36, functField4 = 37, functField5 = 42;

        ALUControl aluControl = new ALUControl();

        int lw = aluControl.getALUControlInput(aluOp0, Integer.MIN_VALUE);
        int sw = aluControl.getALUControlInput(aluOp0, Integer.MIN_VALUE);
        int be = aluControl.getALUControlInput(aluOp1, Integer.MIN_VALUE);
        int rTypeAdd = aluControl.getALUControlInput(aluOp2, functField1);
        int rTypeSub = aluControl.getALUControlInput(aluOp2, functField2);
        int rTypeAnd = aluControl.getALUControlInput(aluOp2, functField3);
        int rTypeOr = aluControl.getALUControlInput(aluOp2, functField4);
        int rTypeSetOnLess = aluControl.getALUControlInput(aluOp2, functField5);


        //Assertion test for Load Word operations
        assertEquals(lw, 0b0010);
        assertFalse(lw == 0b0011);

        //Assertion test for Store Word operations
        assertEquals(sw, 0b0010);
        assertFalse(sw == 0b0011);

        //Assertion test for branch equal operations
        assertEquals(be, 0b0110);
        assertFalse(be == 0b0011);

        //Assertion test for add operations
        assertEquals(rTypeAdd, 0b0010);
        assertFalse(rTypeAdd == 0b0011);

        //Assertion test for subtract operations
        assertEquals(rTypeSub, 0b0110);
        assertFalse(rTypeSub == 0b0011);

        //Assertion test for  AND operations
        assertEquals(rTypeAnd, 0b0000);
        assertFalse(rTypeAnd == 0b0011);

        //Assertion test for OR operations
        assertEquals(rTypeOr, 0b0001);
        assertFalse(rTypeOr == 0b0011);

        //Assertion test for set on less than operations
        assertEquals(rTypeSetOnLess, 0b0111);
        assertFalse(rTypeSetOnLess == 0b0011);

        //Uncomment this to test invalid aluOpcode provided.
        //int invalidOpcode = aluControl.getALUControlInput(3, Integer.MIN_VALUE);

        //Uncomment this to test invalid funcCode provided.
        //int invalidFuncCode = aluControl.getALUControlInput(2, Integer.MIN_VALUE);

    }
}
