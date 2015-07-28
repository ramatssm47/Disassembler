/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myEmu;
import myEmu.OpcodeEnum;
import static myEmu.OpcodeEnum.*;
import myEmu.RegRefEnum;
import static myEmu.RegRefEnum.*;
import myEmu.AddModEnum;
import static myEmu.AddModEnum.*;
/**
 *
 * @author a0120086r
 */
public class Operand {
    
    public enum OperandType {
        None, Reg, Imm, Imm1, Imm2, Disp, Smem, Mem, Pc, ptr
    }
    
    private int mType;
    private int mIndex;
    private int mSize;
    
    public OperandType getType()
    {
        return OperandType.values()[mType];
    }
    
    public int getIndex()
    {
        return mIndex;
    }
    
    public int getSize()
    {
        return mSize;
    }
    
}
