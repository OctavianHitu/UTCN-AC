

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;


entity ID_mips is

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
          sa :out std_logic);


end ID_mips;

architecture Behavioral of ID_mips is

component reg_file_mips is
 Port ( clk : in STD_LOGIC;
           ra1 : in STD_LOGIC_VECTOR (2 downto 0);
           ra2 : in STD_LOGIC_VECTOR (2 downto 0);
           wa : in STD_LOGIC_VECTOR (2 downto 0);
           wd : in STD_LOGIC_VECTOR (15 downto 0);
           wen : in STD_LOGIC;
           rwr : in STD_LOGIC;
           
           rd1 : out STD_LOGIC_VECTOR (15 downto 0);
           rd2 : out STD_LOGIC_VECTOR (15 downto 0));
end component;

signal mux : std_logic_vector(2 downto 0);

begin

regport :reg_file_mips port map (clk,instr(12 downto 10),instr(9 downto 7),
mux,wd,en,regwr,rd1,rd2);

 mux <= instr(6 downto 4) when (regdst='1') else instr(9 downto 7);
 
 func<= instr(2 downto 0);
 sa <= instr(3);
 
 extimm <= "000000000"&instr(6 downto 0) when(extop ='0') else instr(15)&instr(15)&instr(15)&instr(15)&instr(15)&instr(15)&instr(15)&instr(15)&instr(15)&instr(6 downto 0);
 

end Behavioral;
