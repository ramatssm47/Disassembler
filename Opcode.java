/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myEmu;

import java.util.HashMap;
import java.util.Map;
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
public class Opcode {
    
    private static Map<String, OpcodeEnum[]> mOpcode;
    private static Map<String, AddModEnum> mAddMod;
    private static Map<String, RegRefEnum> mRegRef;
    
    public static OpcodeEnum[] lookupOP(int op)
    {
        return mOpcode.get(op);
    }

    public static Map<String, OpcodeEnum[]> getmOpcode() {
        return mOpcode;
    }
    
    public static AddModEnum lookupMD(int op)
    {
        return mAddMod.get(op);
    }

    public static Map<String, AddModEnum> getmAddMod() {
        return mAddMod;
    }
    
    public static RegRefEnum lookupRR(int op)
    {
        return mRegRef.get(op);
    }

    public static Map<String, RegRefEnum> getmRegRef() {
        return mRegRef;
    }
    
    
    static {
        mOpcode = new HashMap<String, OpcodeEnum[]>();
        
        OpcodeEnum[] opArray = {OpcodeEnum.UNDEFINED};
        mOpcode.put("0x00",opArray);
        OpcodeEnum[] opArray2 = {OpcodeEnum.LEA};
        mOpcode.put("0x8d", opArray2);
        OpcodeEnum[] opArray3 = {OpcodeEnum.AND,OpcodeEnum.SUB};
        mOpcode.put("0x83", opArray3);
        OpcodeEnum[] opArray4 = {OpcodeEnum.PUSH};
        mOpcode.put("0x50", opArray4);
        OpcodeEnum[] opArray5 = {OpcodeEnum.SAR};
        mOpcode.put("0xc1", opArray5);
        OpcodeEnum[] opArray6 = {OpcodeEnum.XOR};
        mOpcode.put("0x34", opArray6);
        OpcodeEnum[] opArray7 = {OpcodeEnum.INC};
        mOpcode.put("0x45", opArray7);
        OpcodeEnum[] opArray8 = {OpcodeEnum.CMP};
        mOpcode.put("0x80", opArray8);
        OpcodeEnum[] opArray9 = {OpcodeEnum.POP};
        mOpcode.put("0x5d", opArray9);
        OpcodeEnum[] opArray10 = {OpcodeEnum.MOV};
        mOpcode.put("0x89", opArray10);
    }
    
    static {
        mAddMod = new HashMap<String, AddModEnum>();
        
        mAddMod.put("00", AddModEnum.IAM);
        mAddMod.put("01", AddModEnum.IAM8bitDisp);
        mAddMod.put("10", AddModEnum.IAM32bitDisp);
        mAddMod.put("11", AddModEnum.DAM);
    }
    
    static {
        mRegRef = new HashMap<String, RegRefEnum>();
        
        mRegRef.put("000", RegRefEnum.EAX);
        mRegRef.put("001", RegRefEnum.ECX);
        mRegRef.put("010", RegRefEnum.EDX);
        mRegRef.put("011", RegRefEnum.EBX);
        mRegRef.put("100", RegRefEnum.ESP);
        mRegRef.put("101", RegRefEnum.EBP);
        mRegRef.put("110", RegRefEnum.ESI);
        mRegRef.put("111", RegRefEnum.EDI);
    }
    
    
    
}
