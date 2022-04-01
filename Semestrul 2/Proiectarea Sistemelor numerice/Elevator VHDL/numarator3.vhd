library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.all;
use IEEE.STD_LOGIC_ARITH.all;

entity num3 is 
	port(clk: in STD_LOGIC;
	res: in STD_LOGIC;
	urca: in STD_LOGIC;
	UI :in STD_LOGIC;
	O: out STD_LOGIC_VECTOR(3 downto 0);
	CD,CU: out STD_LOGIC);
end num3;


---------------------------------------
architecture numarator of num3 is 
begin 
	process(clk,res,urca,UI) 
	variable V: STD_LOGIC_VECTOR(3 downto 0);
	begin    
		if res='1' then V:="0000";
		else
		if UI='1' then
			if clk'event and clk='1' then 
				if urca='1' then 
					if V<"1001" then V:=V+"0001"; 
						
					                       
					else V:="0000";
						CU<='1','0' after 1 ns;
						end if;
			    else 
					if V>"0000" then V:=V-"0001";
					
					else V:="1001";
					CD<='1','0' after 1 ns;
					end if;
		end if;
		end if;
			   end if;
				end if;
			   O<=V;
				end process;
end numarator;
