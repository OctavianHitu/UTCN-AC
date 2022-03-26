
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
--use IEEE.numeric_std.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;



entity ex_mips is
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
end ex_mips;

architecture Behavioral of ex_mips is

signal isrmux: std_logic_vector(15 downto 0);
signal aluctrl : std_logic_vector ( 3 downto 0);
signal zro :std_logic;
signal aluresaux :  STD_LOGIC_VECTOR (15 downto 0);

begin

isrmux <= extimm when (alusrc= '1') else rd2;
branchaddr <= pc1 + extimm;


ALUControl: process(aluop,func)
begin 
    case aluop is
        when "00" =>
            case func is
                when "000" =>aluctrl<= "0000";
                when "001" =>aluctrl<= "0001";
                when "010" =>aluctrl<= "0010";
                when "011" =>aluctrl<= "0011";
                when "100" =>aluctrl<= "0100";
                when "101" =>aluctrl<= "0101";
                when "110" =>aluctrl<= "0110";
                when "111" =>aluctrl<= "0111";
            end case;        
        when "01"=>aluctrl<="0000";
        when "10"=>aluctrl<="0001";
        when "11"=>aluctrl<="1000";
        when others => aluctrl <=(others => 'X');
    end case;
end process;

ALU :process (rd1,isrmux,aluctrl,sa)
begin 
case aluctrl is
when "0000" => aluresaux <=rd1 + isrmux;
when "0001" => aluresaux <=rd1 - isrmux;
when "0010" => aluresaux <=rd1 and isrmux;
when "0011" => aluresaux <=rd1 or isrmux;
when "0100" => aluresaux <=rd1 xor isrmux;
when "0101" => aluresaux <= sa & isrmux(15 downto 1);--slr
when "0110" => aluresaux <= isrmux(14 downto 0) & sa;--sll
when "0111" =>case rd1 is
        when x"0000"=> aluresaux <= isrmux;
        when x"0001"=> aluresaux <= isrmux(14 downto 0)&"0";
        when x"0010"=> aluresaux <= isrmux(13 downto 0)&"00";
        when x"0011"=> aluresaux <= isrmux(12 downto 0)&"000";
        when x"0100"=> aluresaux <= isrmux(11 downto 0)&"0000";
        when x"0101"=>aluresaux <=isrmux(10 downto 0)&"00000";
        when x"0110"=> aluresaux <= isrmux(9 downto 0)&"000000";
        when x"0111"=> aluresaux <= isrmux(8 downto 0)&"0000000";
        when x"1000"=>aluresaux <=isrmux(7 downto 0)&"00000000";
        when others =>aluresaux <=(others => 'X');
end case;
when "1000" => if signed(rd1) <signed(isrmux) then aluresaux<=X"0001";
              else aluresaux <=X"0000";
              end if;
 when others =>aluresaux <=(others => 'X');
end case;



end process;

process(aluresaux) 
begin
    
    if aluresaux = x"0000" then
    zro<='1';
     else 
    zro<='0';
     end if;


end process;

gt <= not(zro) and not(aluresaux(15));
zero<=zro;
alures<=aluresaux;




end Behavioral;
