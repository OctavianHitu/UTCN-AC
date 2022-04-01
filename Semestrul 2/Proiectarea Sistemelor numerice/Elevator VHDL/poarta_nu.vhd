library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.all;
use IEEE.STD_LOGIC_ARITH.all;

entity poartanu is 
	port(a: in STD_LOGIC;
	y: out STD_LOGIC);
end poartanu;

architecture poarta of poartanu is
begin 
	y<=not(a);
end poarta;
