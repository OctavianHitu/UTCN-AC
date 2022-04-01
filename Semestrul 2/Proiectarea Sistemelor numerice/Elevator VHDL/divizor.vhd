library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.all;
use IEEE.STD_LOGIC_ARITH.all;

entity div is
	port( clk: in STD_LOGIC;	 
	C: out STD_LOGIC);
	
	
end div;


architecture num of div is

begin 
	process (clk)	
	variable V: STD_LOGIC_VECTOR(25 downto 0):="00000000000000000000000000";	  
	begin
	if clk'event and clk='1' then V:=V+1; 				 
	end if;
	C<=V(25);
   end process;
 end architecture num;
