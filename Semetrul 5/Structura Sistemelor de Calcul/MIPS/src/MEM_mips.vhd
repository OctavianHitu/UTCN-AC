

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.numeric_std.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;


entity MEM_mips is
  Port ( memwrite :in std_logic;
         aluresin : in std_logic_vector(15 downto 0);
         aluresout : out std_logic_vector(15 downto 0);
         rd2: in std_logic_vector(15 downto 0);
         clk :in std_logic;
         en :in std_logic ;
         memdata :out std_logic_vector(15 downto 0)
           );
end MEM_mips;

architecture Behavioral of MEM_mips is

type t_mem is array (0 to 31) of std_logic_vector(15 to 0);
signal mem: t_mem ;

begin

process(clk)
begin
    if rising_edge(clk) then
        if en='1' and   memwrite = '1' then
            mem(conv_integer(aluresin(4 downto 0)))<=rd2;
        end if;
    end if;
end process;


aluresout<=aluresin;
memdata <= mem(conv_integer(aluresin(4 downto 0)));
end Behavioral;
