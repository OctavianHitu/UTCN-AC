 library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.all;
use IEEE.STD_LOGIC_ARITH.all;


entity NUMARATOR_2 is
	port( clk,res: in STD_LOGIC;
	urca:in STD_LOGIC;
	etout: in STD_LOGIC_VECTOR(3 downto 0);
	isr: out STD_LOGIC:='0'; 
	etout2: out STD_LOGIC_VECTOR(3 downto 0));
end NUMARATOR_2;  

architecture num2 of NUMARATOR_2 is   

begin 
	
	process(clk,res,etout,urca) 
	variable V: STD_LOGIC_VECTOR(3 downto 0);
	begin
		  
		if res = '1' then V:="0000"; 
		else 
			
			if clk'EVENT and clk='1' then 
				if urca='1' then
				if V<etout then V:=V+"0001"; 
				else isr<='1'; end if; 	 
				else if V>etout then V:=V-"0001";
				else isr<='1';
				end if;
				end if;
				end if;
				end if;
		etout2<=V;
		end process;
		end	num2;