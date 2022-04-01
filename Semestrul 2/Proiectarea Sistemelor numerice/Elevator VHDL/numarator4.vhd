library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.all;
use IEEE.STD_LOGIC_ARITH.all; 

entity num4 is 
	port( cu,cd,res: in STD_LOGIC;
	O: out STD_LOGIC_VECTOR(3 downto 0));
end num4;

architecture numarator of num4 is
signal Y: STD_LOGIC_VECTOR(3 downto 0);
signal W: STD_LOGIC_VECTOR(3 downto 0);
begin 
	process(cu,res)
	variable V: STD_LOGIC_VECTOR(3 downto 0);
	begin
	
	if res='1' then V:="0000"; 
	else 
	if cu'EVENT and cu='1' then V:=V+"0001"; end if;
   
	 end if;
	 
		 Y<=V;
end process;
   process(cd,res)
	variable E: STD_LOGIC_VECTOR(3 downto 0);
	begin 
	 if res='1' then E:="0000";
	    else if cd'EVENT and cd='1' then E:=E-"0001";
		       end if;
		end if;
		 W<=E;
end process;
process(cu,cd)
begin
if cu='1' then	  
O<=Y;
else if cd='1' then O<=W;end if;
end if;
end process;


end numarator;
