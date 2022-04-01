library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.all;
use IEEE.STD_LOGIC_ARITH.all; 


entity poartasi is
	port( a,b,c : in STD_LOGIC;
	x: out STD_LOGIC);
end poartasi;

architecture poarta of poartasi is
begin 
	x<= a and b and c;
end poarta;
