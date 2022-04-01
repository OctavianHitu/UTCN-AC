library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.all;
use IEEE.STD_LOGIC_ARITH.all;

entity num1 is
	port(
		res:in STD_LOgic;
	clk: in STD_LOGIC;
	urca: out STD_LOGIC;
	etout: out STD_LOGIC_VECTOR(3 downto 0));
end num1;

architecture numarator of num1 is
begin
	
	process(res,clk) 
	variable v: STD_LOGIC_VECTOR(3 downto 0);	  
	variable sens: STD_LOGIC:='1';
	begin 	
	
			 	
		   if v="0000" then urca<='1';sens:='1'; end if;	         
		if v="1100" then urca<='0'; sens:='0'; end if;  
	if res='1' then V:="0000"; 
	else 
	 						   
		if clk'event and clk='1' then	
			 
			
			if sens='1' then 
				v:=v+"0001";
			end if;
			if sens='0' then
				v:=v-"0001";	
				end if;
			end if;	  
		end if;
		etout<=v;
			end process;
end numarator;
