/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myEmu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;
import myEmu.OpcodeEnum;
import static myEmu.OpcodeEnum.*;
import myEmu.RegRefEnum;
import static myEmu.RegRefEnum.*;
import myEmu.AddModEnum;
import static myEmu.AddModEnum.*;
import jdk.nashorn.internal.codegen.CompilerConstants;

/**
 *
 * @author a0120086r
 */
public class Emulator {

    
    public Emulator() {

    }
    
    
    
    
    void op_execute(String filename){
        
       int i = 0;
       String dest_reg;
       String Imm = null,Imm1 = null,Imm2 = null;
       String mod=null,reg=null,rmV=null;
       OpcodeEnum instr = null;
       
       Stack<String> opcodes = readfile(filename);
        
       Map<String, OpcodeEnum[]> opMap = Opcode.getmOpcode();
       Map<String, AddModEnum> moMap = Opcode.getmAddMod();
       Map<String, RegRefEnum> rgMap = Opcode.getmRegRef();
       
       while(!opcodes.empty())
       {
           String val = opcodes.get(i);
           switch("0x"+val)
           {
               
               case "0x8d":
                   
                   if(opMap.containsKey("0x8d"))
                    {
                        //System.out.println(opMap.get("0x8d")+"  "+opcodes.get(i));
                        opcodes.remove(i);
                        String[] modrmVal = hexToBinary(opcodes.get(i)).split("");
                        String[] tmV = null;
                        
                        mod = modrmVal[0]+modrmVal[1];
                        reg = modrmVal[2]+modrmVal[3]+modrmVal[4];
                        rmV = modrmVal[5]+modrmVal[6]+modrmVal[7];
                        
                       opcodes.remove(i);
                        
                        if(moMap.get(mod) == AddModEnum.IAM)
                        {
                            Imm = rgMap.get(reg).toString();
                            Imm1 = rgMap.get(rmV).toString();
                            
                            System.out.println(Imm+","+Imm1);
                        }
                        else if(moMap.get(mod) == AddModEnum.IAM8bitDisp)
                        {
                            Imm = rgMap.get(reg).toString();
                            Imm1 = rgMap.get(rmV).toString();
                            
                            if(rmV.equalsIgnoreCase("100"))
                            {
                            dest_reg = Imm;
                            String[] SIB = hexToBinary(opcodes.get(i)).split("");
                            
                            String scale = SIB[0]+SIB[1];
                            String index = SIB[2]+SIB[3]+SIB[4];
                            String base = SIB[5]+SIB[6]+SIB[7];
                            
                            opcodes.remove(i);
                            tmV = opcodes.get(i).split("");
                            }
                            instr = opMap.get("0x8d")[0];
                            
                            System.out.println(instr+" "+Imm+",["+Imm1+"+"+tmV[0]+"x"+tmV[1]+"]");
                        opcodes.remove(i);
                        }
                        
                    }
                    break;
                   
               case "0x83":
                   
                   if(opMap.containsKey("0x83"))
                    {   
                        opcodes.remove(i);
                        
                        String[] modrmVal = hexToBinary(opcodes.get(i)).split("");
                        
                        mod = modrmVal[0]+modrmVal[1];
                        reg = modrmVal[2]+modrmVal[3]+modrmVal[4];
                        rmV = modrmVal[5]+modrmVal[6]+modrmVal[7];
                        
                        opcodes.remove(i);
                        
                        if(reg.equalsIgnoreCase("100")){
                            //and
                            Imm = rgMap.get(reg).toString();
                            Imm1 = "0xffffff"+opcodes.get(i+1);
                            
                            instr = opMap.get("0x83")[0];
                            
                            opcodes.remove(i);
                            
                            System.out.println(instr+" "+Imm+","+Imm1);
                        }
                        else if(rmV.equalsIgnoreCase("100"))
                        {   //sub
                            Imm = rgMap.get(rmV).toString();
                            Imm1 = opcodes.get(i);
                            String tmp[] = Imm1.split("");
                            opcodes.remove(i);
                            
                            instr = opMap.get("0x83")[1];
                            
                            System.out.println(instr+" "+Imm+","+tmp[0]+"x"+tmp[1]);
                        }
                     
                    }   
                    break;
                   
               case "0x50":
                   if(opMap.containsKey("0x50"))
                    {
                        opcodes.remove(i);
                        
                        String[] modrmVal = hexToBinary(opcodes.get(i)).split("");
                        
                        mod = modrmVal[0]+modrmVal[1];
                        reg = modrmVal[2]+modrmVal[3]+modrmVal[4];
                        rmV = modrmVal[5]+modrmVal[6]+modrmVal[7];
                        
                        String register = rgMap.get(reg).toString();
                                                
                        instr = opMap.get("0x50")[0];
                        
                        System.out.println(instr+" "+register);
                    
                    }
                    break;
                   
               case "0xc1":
                   
                   if(opMap.containsKey("0xc1"))
                    {
                        opcodes.remove(i);
                        
                        String[] modrmVal = hexToBinary(opcodes.get(i)).split("");
                        
                        mod = modrmVal[0]+modrmVal[1];
                        reg = modrmVal[2]+modrmVal[3]+modrmVal[4];
                        rmV = modrmVal[5]+modrmVal[6]+modrmVal[7];
                                         
                        opcodes.remove(i);
                        
                        if(moMap.get(mod) == AddModEnum.IAM)
                        {
                            Imm = rgMap.get(reg).toString();
                            Imm1 = rgMap.get(rmV).toString();
                            
                            System.out.println(Imm+","+Imm1);
                        }
                        else if(moMap.get(mod) == AddModEnum.DAM)
                        {
                            Imm = rgMap.get(rmV).toString();
                            Imm1 = opcodes.get(i);
                            String[] reg2val = Imm1.split("");
                            
                            opcodes.remove(i);
                            
                            instr = opMap.get("0xc1")[0];
                            
                            System.out.println(instr+" "+Imm+","+reg2val[0]+"x"+reg2val[1]);
                            
                        }
                   
                    }
                    break;
                   
               case "0x34":
                   if(opMap.containsKey("0x34"))
                    {
                        opcodes.remove(i);
                        
                        String[] modrmVal = hexToBinary(opcodes.get(i)).split("");
                        
                        mod = modrmVal[0]+modrmVal[1];
                        reg = modrmVal[2]+modrmVal[3]+modrmVal[4];
                        rmV = modrmVal[5]+modrmVal[6]+modrmVal[7];
                       
                        Imm = RegRefEnum.AL.toString();
                        Imm1 = opcodes.get(i);
                        
                        opcodes.remove(i);
                        
                        instr = opMap.get("0x34")[0];
                        
                        System.out.println(instr+" "+Imm+","+"0x"+Imm1);
                   
                    }
                    break;
                   
               case "0x45":
                   if(opMap.containsKey("0x45"))
                    {
                                               
                        String[] modrmVal = hexToBinary(opcodes.get(i)).split("");
                        
                        mod = modrmVal[0]+modrmVal[1];
                        reg = modrmVal[2]+modrmVal[3]+modrmVal[4];
                        rmV = modrmVal[5]+modrmVal[6]+modrmVal[7];
                        
                        String register = rgMap.get(rmV).toString();
                                                
                        instr = opMap.get("0x45")[0];
                        
                        opcodes.remove(i);
                        
                        System.out.println(instr+" "+register);
                  
                    }
                    break;
               case "0x80":
                   if(opMap.containsKey("0x80"))
                    {
                        opcodes.remove(i);
                        
                        if(opcodes.get(i).equalsIgnoreCase("7c")){
                            Imm = "BYTE PTR";
                        }
                        
                        opcodes.remove(i);
                        
                        String[] modrmVal = hexToBinary(opcodes.get(i)).split("");
                        
                        mod = modrmVal[0]+modrmVal[1];
                        reg = modrmVal[2]+modrmVal[3]+modrmVal[4];
                        rmV = modrmVal[5]+modrmVal[6]+modrmVal[7];
                        
                        opcodes.remove(i);
                        
                        if(moMap.get(mod) == AddModEnum.IAM)
                        {
                            Imm = rgMap.get(reg).toString();
                            Imm1 = rgMap.get(rmV).toString();
                            
                            System.out.println(Imm+","+Imm1);
                        }
                        else if(moMap.get(mod) == AddModEnum.IAM8bitDisp)
                        {
                            //Imm = rgMap.get(reg).toString();
                            Imm1 = rgMap.get(rmV).toString();
                            
                            if(rmV.equalsIgnoreCase("000"))
                            {
                            dest_reg = Imm;
                            String[] SIB = hexToBinary(opcodes.get(i)).split("");
                                                                                
                            String scale = SIB[0]+SIB[1];
                            String index = SIB[2]+SIB[3]+SIB[4];
                            String base = SIB[5]+SIB[6]+SIB[7];
                            
                            opcodes.remove(i);
                            }
                                                   
                        }
                        
                        String tmp[] = opcodes.get(i).split("");
                        opcodes.remove(i);
                        Imm2 = "0x"+opcodes.get(i);                           
                        opcodes.remove(i);
                        instr = opMap.get("0x80")[0];
                                                
                        System.out.println(instr+" "+Imm+" ["+Imm1+"+"+tmp[0]+"x"+tmp[1]+"],"+Imm2);
                        
                    }
                    break;
                   
               case "0x5d":
                   if(opMap.containsKey("0x5d"))
                    {
                        //opcodes.remove(i);
                        
                        String[] modrmVal = hexToBinary(opcodes.get(i)).split("");
                        
                        mod = modrmVal[0]+modrmVal[1];
                        reg = modrmVal[2]+modrmVal[3]+modrmVal[4];
                        rmV = modrmVal[5]+modrmVal[6]+modrmVal[7];
                        
                        String register = rgMap.get(rmV).toString();
                        
                        opcodes.remove(i);
                        
                        instr = opMap.get("0x5d")[0];
                        
                        System.out.println(instr+" "+register);
                  
                    }
                    break;
               case "0x89":
                   if(opMap.containsKey("0x89"))
                    {
                        opcodes.remove(i);
                        
                        String[] modrmVal = hexToBinary(opcodes.get(i)).split("");
                        
                        mod = modrmVal[0]+modrmVal[1];
                        reg = modrmVal[2]+modrmVal[3]+modrmVal[4];
                        rmV = modrmVal[5]+modrmVal[6]+modrmVal[7];
                        
                        Imm = rgMap.get(rmV).toString();
                        Imm1 = rgMap.get(reg).toString();
                        
                        opcodes.remove(i);
                        
                        instr = opMap.get("0x89")[0];
                        
                        System.out.println(opMap.get("0x89")[0]+" "+Imm+","+Imm1);
                
                    
                    }
                    break;
           }
       }
        
        
    }
    
    int process_operation(String filename) throws FileNotFoundException{
        int j,b;
        int ret;
        int size = readfile(filename).size();
        for(j=0;j<size;j++)
        {
            String c = readfile(filename).get(j);
            String bin = hexToBinary(c);
            System.out.println("0x"+c+"---"+bin);
        }
    return 0;
    }
    
    static String hexToBinary(String hex) 
    {
    int i = Integer.parseInt(hex,16);
    String bin = String.format("%8s",Integer.toBinaryString(i)).replace(' ', '0');
    return bin;
    }
    
    public static Stack<String> readfile(String filename)
    {
        BufferedReader br = null;
        Stack<String> st = new Stack<>();
        
        try
        {
            String temp;
            String[] str = null;
              
            br = new BufferedReader(new FileReader(filename));
            
            while((temp = br.readLine())!=null)
            {
                str = temp.split(" ");           
            }
            for(int i = 0;i<str.length;i++){
                st.add(str[i]);
            }
            int i=0;
            for(String a:st)
            {
                i = i+1;
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (br != null)
                br.close();
            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }
           
        }
        return st;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        
        Emulator emu = new Emulator();
        //ff.process_operation(filename);
        emu.op_execute("sample-input.txt");
    }
    
}
