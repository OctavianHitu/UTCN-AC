

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity reg_file_mips is
 Port ( clk : in STD_LOGIC;
           ra1 : in STD_LOGIC_VECTOR (2 downto 0);
           ra2 : in STD_LOGIC_VECTOR (2 downto 0);
           wa : in STD_LOGIC_VECTOR (2 downto 0);
           wd : in STD_LOGIC_VECTOR (15 downto 0);
           wen : in STD_LOGIC;
           rwr : in STD_LOGIC;
           rd1 : out STD_LOGIC_VECTOR (15 downto 0);
           rd2 : out STD_LOGIC_VECTOR (15 downto 0));
end reg_file_mips;

architecture Behavioral of reg_file_mips is

type reg_array is array (0 to 15) of std_logic_vector (15 downto 0);
signal reg_file :reg_array ;

begin
process(clk)
begin
    if clk'event and clk='1' then
        if wen='1' and rwr ='1' then
            reg_file(conv_integer(wa)) <= wd;
          end if;
       end if;
       
end process;
rd1<= reg_file(conv_integer(ra1));
rd2<= reg_file(conv_integer(ra2));

end Behavioral;
