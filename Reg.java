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

class Reg
{
    String regVal;

    public Reg(String regVal) {
        this.regVal = regVal;
    }
    
    static Reg 
            EAX = new Reg("0xbf8db144"),
            EBX = new Reg("0xae5ff4"),
            ECX = new Reg("0x88c5cffb"),
            EDX = new Reg("0x1"),
            EBP = new Reg("0xbf8db118"),
            ESP = new Reg("0xbf8db0bc"),
            ESI = new Reg("0x9a0ca0"),
            EDI = new Reg("0x0"),
            EIP = new Reg("0x8048354"),
            EFLAGS = new Reg("0x246"),
            CS = new Reg("0x73"),
            SS = new Reg("0x7b"),
            ES = new Reg("0x7b"),
            DS = new Reg("0x7b"),
            FS = new Reg("0x0"),
            GS = new Reg("0x33"),
            AL = new Reg(null);
            ;
}
