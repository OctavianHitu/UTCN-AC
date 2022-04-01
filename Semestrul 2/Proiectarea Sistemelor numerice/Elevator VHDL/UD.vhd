library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.all;
use IEEE.STD_LOGIC_ARITH.all;
entity unit is
	port( control : in STD_LOGIC_VECTOR(3 downto 0);
	  urca: in STD_LOGIC;
	CLK_NUM: out STD_LOGIC);
end unit;

architecture comp of unit is 
begin 
	process(control)
	variable varclk_num:std_logic;
	begin 
		if control(3)='0' and control(2)='0'  then varCLK_NUM:='1';
		elsif control(3)='1' then varCLK_NUM:='0';
		elsif urca='0' then if control(0)='1' then varCLK_NUM:='0';
		                   else varCLK_NUM:='1';
		   end if;
		else if control(1)='1' then varCLK_NUM:='0';
		else varCLK_NUM:='1' ;
		
		end if;
		end if;	
		clk_num<=varclk_num, '0' after 5 ns;
		end process; 
		 
	end comp;                                                                         
