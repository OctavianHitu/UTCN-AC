
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;


entity test_env is
    Port ( clk : in STD_LOGIC;
           btn : in STD_LOGIC_VECTOR (4 downto 0);
           sw : in STD_LOGIC_VECTOR (15 downto 0);
           led : out STD_LOGIC_VECTOR (15 downto 0);
           an : out STD_LOGIC_VECTOR (3 downto 0);
           cat : out STD_LOGIC_VECTOR (6 downto 0));
end test_env;


 
 

 

 
 architecture testare_if of test_env is
 component MPG is
    Port ( en : out STD_LOGIC;
           input : in STD_LOGIC;
           clock : in STD_LOGIC);
end component;
component SSD is
    Port ( clk : in STD_LOGIC;
           digits : in STD_LOGIC_VECTOR (15 downto 0);
           an : out STD_LOGIC_VECTOR (3 downto 0);
           cat : out STD_LOGIC_VECTOR (6 downto 0));
end component;
component ifmips is
    Port ( clk : in STD_LOGIC;
           jump : in STD_LOGIC;
           jumpadr : in STD_LOGIC_VECTOR (15 downto 0);
           branchadr : in STD_LOGIC_VECTOR (15 downto 0);
           pcsrc : in STD_LOGIC;
           enable : in STD_LOGIC;
           rst : in STD_LOGIC;
           pcfinal : out std_logic_vector (15 downto 0);
            instruction :out std_logic_vector (15 downto 0)
            );
            
end component;

signal en1 : std_logic :='1';
signal en2 : std_logic :='1';
signal instr :std_logic_vector (15 downto 0);
signal pcfin :std_logic_vector(15 downto 0);
signal isrmux: std_logic_vector (15 downto 0);

 begin
 mpg1 :MPG port map(en1,btn(0),clk);
 mpg2 :MPG port map(en2,btn(1),clk);
 ifprot : ifmips port map(clk,sw(0),X"0011",X"0000",sw(1),en1,en2,pcfin,instr);
 ssdport : SSD port map(clk,isrmux,an,cat);
 
 isrmux <= pcfin when (sw(7)='1') else instr;
 
 
 end architecture;
 
 
 architecture testare_ID_UC of test_env is
 
component ifmips is
    Port ( clk : in STD_LOGIC;
           jump : in STD_LOGIC;
           jumpadr : in STD_LOGIC_VECTOR (15 downto 0);
           branchadr : in STD_LOGIC_VECTOR (15 downto 0);
           pcsrc : in STD_LOGIC;
           enable : in STD_LOGIC;
           rst : in STD_LOGIC;
           pcfinal : out std_logic_vector (15 downto 0);
            instruction :out std_logic_vector (15 downto 0)
            );
            
end component;

component UC_mips is
     Port ( instr: in std_logic_vector(2 downto 0);
     
            br_gtz : out std_logic;
            regdst : out std_logic;
            extop : out std_logic;
            alusrc : out std_logic;
            branch : out std_logic;
            jump : out std_logic;
            aluop : out std_logic_vector( 1 downto 0);
            memwrite : out std_logic;
            memtoreg : out std_logic;
            regwrite : out std_logic
         );
end component;
component ID_mips is

    Port(clk: in std_logic;
         regwr :in STD_LOGIC;
          instr :in std_logic_vector(15 downto 0);
          regdst :in std_logic;
          en:in std_logic;
          extop :in std_logic;
          
          rd1 :out std_logic_vector(15 downto 0);
          rd2 :out std_logic_vector(15 downto 0);
          
          wd :in std_logic_vector(15 downto 0);
          
          extimm :out std_logic_vector(15 downto 0);
          func :out std_logic_vector(2 downto 0);
          sa :out std_logic
          );


end component;
component MPG is
    Port ( en : out STD_LOGIC;
           input : in STD_LOGIC;
           clock : in STD_LOGIC);
end component;
component SSD is
    Port ( clk : in STD_LOGIC;
           digits : in STD_LOGIC_VECTOR (15 downto 0);
           an : out STD_LOGIC_VECTOR (3 downto 0);
           cat : out STD_LOGIC_VECTOR (6 downto 0));
end component;
 
 signal en1 :std_logic;
 signal en2 : std_logic;
 signal jump :std_logic;
 signal pcfinal : std_logic_vector(15 downto 0);
 signal instr : std_logic_vector (15 downto 0);
 signal regwrite : std_logic;
 signal regdst :std_logic;
 signal extop :std_logic;
 signal rd1: std_logic_vector(15 downto 0);
 signal rd2 :std_logic_vector(15 downto 0);
 signal extimm : std_logic_vector(15 downto 0);
 signal func : std_logic_vector(2 downto 0);
 signal sa : std_logic;
 signal isrplus :std_logic_vector(15 downto 0);
 
 
signal brgtz : std_logic;
signal alusrc :  std_logic;
signal branch :  std_logic;
 signal aluop :  std_logic_vector( 1 downto 0);
signal memwrite :  std_logic;
signal  memtoreg :  std_logic;

signal isr_mux_mare :std_logic_vector(15 downto 0);

signal funcmare : std_logic_vector(15 downto 0);
signal samare :std_logic_vector (15 downto 0);

 
 begin
 
 mpg1port : MPG port map (en1,btn(0),clk);
 mpg2port : MPG port map (en2,btn(1),clk);
 ifport :ifmips port map(clk,jump,x"0000",x"0011",
 sw(1),en1,en2,pcfinal,instr);
 idport :ID_mips port map (clk,regwrite,instr,regdst,en1,
 extop,rd1,rd2,isrplus,extimm,func,sa);
 ucport : UC_mips port map(instr(15 downto 13),brgtz,regdst,extop,alusrc,branch,
 jump,aluop,memwrite,memtoreg,regwrite);

 led(10 downto 9) <= aluop;
 led(8)<=brgtz;
 led(7) <=regdst;
 led(6) <=extop;
 led(5) <=alusrc;
 led(4) <=branch;
 led(3) <=jump;
 led(2) <=memwrite;
 led(1) <=memtoreg;
 led(0) <=regwrite;
 
 isrplus <=rd1 +rd2;
 
 funcmare <="0000000000000"&func;
 samare <="000000000000000"&sa;
 


with sw(7 downto 5) select
    isr_mux_mare <= instr when "000",
                    pcfinal when "001",
                    rd1 when "010",
                    rd2 when "011",
                    isrplus when "100",
                    extimm when "101",
                    funcmare when "110",
                    samare when "111",
                    (others => 'X' ) when others;
 
 ssdport : SSD port map(clk,isr_mux_mare,an,cat);

 
 end architecture;
 
 
 architecture testare_mips of test_env is
 
component ifmips is
    Port ( clk : in STD_LOGIC;
           jump : in STD_LOGIC;
           jumpadr : in STD_LOGIC_VECTOR (15 downto 0);
           branchadr : in STD_LOGIC_VECTOR (15 downto 0);
           pcsrc : in STD_LOGIC;
           enable : in STD_LOGIC;
           rst : in STD_LOGIC;
           pcfinal : out std_logic_vector (15 downto 0);
            instruction :out std_logic_vector (15 downto 0)
            );
            
end component;

component UC_mips is
     Port ( instr: in std_logic_vector(2 downto 0);
     
            br_gtz : out std_logic;
            regdst : out std_logic;
            extop : out std_logic;
            alusrc : out std_logic;
            branch : out std_logic;
            jump : out std_logic;
            aluop : out std_logic_vector( 1 downto 0);
            memwrite : out std_logic;
            memtoreg : out std_logic;
            regwrite : out std_logic
         );
end component;
component ID_mips is

    Port(clk: in std_logic;
         regwr :in STD_LOGIC;
          instr :in std_logic_vector(15 downto 0);
          regdst :in std_logic;
          en:in std_logic;
          extop :in std_logic;
          
          rd1 :out std_logic_vector(15 downto 0);
          rd2 :out std_logic_vector(15 downto 0);
          
          wd :in std_logic_vector(15 downto 0);
          
          extimm :out std_logic_vector(15 downto 0);
          func :out std_logic_vector(2 downto 0);
          sa :out std_logic
          );


end component;
component MPG is
    Port ( en : out STD_LOGIC;
           input : in STD_LOGIC;
           clock : in STD_LOGIC);
end component;
component SSD is
    Port ( clk : in STD_LOGIC;
           digits : in STD_LOGIC_VECTOR (15 downto 0);
           an : out STD_LOGIC_VECTOR (3 downto 0);
           cat : out STD_LOGIC_VECTOR (6 downto 0));
end component;

component ex_mips is
    Port ( rd1 : in STD_LOGIC_VECTOR (15 downto 0);
           alusrc : in STD_LOGIC;
           rd2 : in STD_LOGIC_VECTOR (15 downto 0);
           extimm : in STD_LOGIC_VECTOR (15 downto 0);
           sa : in STD_LOGIC;
           func : in STD_LOGIC_VECTOR (2 downto 0);
           aluop : in STD_LOGIC_VECTOR (1 downto 0);
           pc1 : in STD_LOGIC_VECTOR (15 downto 0);
           
           
           gt : out STD_LOGIC;
           zero : out STD_LOGIC;
           alures : out STD_LOGIC_VECTOR (15 downto 0);
           branchaddr : out STD_LOGIC_VECTOR(15 downto 0));
end component;

component MEM_mips is
  Port ( memwrite :in std_logic;
         aluresin : in std_logic_vector(15 downto 0);
         aluresout : out std_logic_vector(15 downto 0);
         rd2: in std_logic_vector(15 downto 0);
         clk :in std_logic;
         en :in std_logic ;
         memdata :out std_logic_vector(15 downto 0)
           );
end component;

 
 signal en1 :std_logic;
 signal en2 : std_logic;
 signal jump :std_logic;
 signal jumpadr:std_logic_vector(15 downto 0);
 signal pcfinal : std_logic_vector(15 downto 0);
 signal branchaddr :std_logic_vector(15 downto 0);
 signal pcsrc :std_logic;
 signal pcfin:std_logic_vector(15 downto 0);
signal instr :std_logic_vector(15 downto 0);
signal regwr :std_logic;
signal regdst :std_logic;
signal extop :std_logic;
signal rd1:std_logic_vector(15 downto 0);
signal rd2:std_logic_vector(15 downto 0);
signal wd_mux:std_logic_vector(15 downto 0);
signal extimm:std_logic_vector(15 downto 0);
signal func:std_logic_vector(2 downto 0);
signal sa:std_logic;
signal alusrc:std_logic;
signal aluop:std_logic_vector (1 downto 0);
signal gt:std_logic;
signal zero :  STD_LOGIC;
signal alures :  STD_LOGIC_VECTOR (15 downto 0);
signal memwrite:std_logic;
signal aluresout :  STD_LOGIC_VECTOR (15 downto 0);
signal memdata :  STD_LOGIC_VECTOR (15 downto 0);
signal brgtz:std_logic;
signal branch:std_logic;
signal memtoreg:std_logic;
signal mux2:  STD_LOGIC_VECTOR (15 downto 0);

 


 
 begin
 
 mpg1port : MPG port map (en1,btn(0),clk);
 mpg2port : MPG port map (en2,btn(1),clk);
port_if :ifmips port map (clk,jump,jumpadr,branchaddr,pcsrc,en1,en2,pcfin,instr);
port_id:ID_mips port map (clk,regwr,instr,regdst,en1,extop,rd1,rd2,wd_mux,extimm,func,sa);
port_ex:ex_mips port map(rd1,alusrc,rd2,extimm,sa,func,aluop,pcfin,gt,zero,alures,branchaddr);
port_mem: MEM_mips port map (memwrite,alures,aluresout,rd2,clk,en1,memdata);
port_uc: UC_mips port map(instr(15 downto 13),brgtz,regdst,extop,alusrc,branch,jump,aluop,memwrite,memtoreg,regwr);

jumpadr <= pcfin(15 downto 13) &instr(12 downto 0); 
wd_mux <= memdata when (memtoreg='1') else aluresout;
pcsrc <= (branch and zero) or(brgtz and gt);

 led(10 downto 9) <= aluop;
 led(8)<=brgtz;
 led(7) <=regdst;
 led(6) <=extop;
 led(5) <=alusrc;
 led(4) <=branch;
 led(3) <=jump;
 led(2) <=memwrite;
 led(1) <=memtoreg;
 led(0) <=regwr;
 
 process (sw(7 downto 5),instr,pcfin,rd1,rd2,extimm,alures,memdata,wd_mux) is
 begin
    case sw(7 downto 5) is
 
        when "000" => mux2 <= instr;
        when "001" => mux2 <= pcfin;
        when "010" => mux2 <= rd1;
        when "011" => mux2 <= rd2;
        when "100" => mux2 <= extimm;
        when "101" => mux2 <= alures;
        when "110" => mux2 <= memdata;
        when "111" => mux2 <= wd_mux;
     end case;
 end process;
 
 port_ssd: SSD port map(clk,mux2,an,cat);
 

 
 end architecture;
 
 

