library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.all;
use IEEE.STD_LOGIC_ARITH.all;
entity memram is
	port(  e1,e2 : in STD_LOGIC_VECTOR(3 downto 0);
	clke,res: in STD_LOGIC;
    ej: in STD_LOGIC_VECTOR(3 downto 0);
	a: out STD_LOGIC_VECTOR(3 downto 0)
	);
end memram;	 




architecture ram of memram is	 
type matrice is array (0 to 15) of STD_LOGIC_VECTOR (3 downto 0);  
	
begin 	 			
	process(clke,e1,e2,res,ej)  
	 variable et: matrice;
	begin  
		if res='1' then
			et(0):= "0000";	 
			et(1):= "0000";
			et(2):= "0000";
			et(3):= "0000";
			et(4):= "0000";
			et(5):= "0000";
			et(6):= "0000";
			et(7):= "0000";
			et(8):= "0000";
			et(9):= "0000";
			et(10):= "0000";
			et(11):= "0000";
			et(12):= "0000";
			et(13):= "0000";
			et(14):= "0000";
			et(15):= "0000";	
		else
		if (clke = '1') then 
			if (e1 = "0000") then et(0):=ej;
			elsif (e1 = "0001") then et(1):=ej;
			elsif (e1 = "0010") then et(2):=ej;
			elsif (e1 = "0011") then et(3):=ej;
			elsif (e1 = "0100") then et(4):=ej;
			elsif (e1 = "0101") then et(5):=ej;
			elsif (e1 = "0110") then et(6):=ej;
			elsif (e1 = "0111") then et(7):=ej;
			elsif (e1 = "1000") then et(8):=ej;
			elsif (e1 = "1001") then et(9):=ej;
			elsif (e1 = "1010") then et(10):=ej;
			elsif (e1 = "1011") then et(11):=ej;
			elsif (e1 = "1100") then et(12):=ej; 
			else et(15):="0000"; 
			end if;
		else 
			if(e2="0000")then a<=et(0);
            elsif (e2="0001")then a<=et(1);
            elsif (e2="0010")then a<=et(2);
            elsif (e2="0011")then a<=et(3);
            elsif (e2="0100")then a<=et(4);
            elsif (e2="0101")then a<=et(5);
            elsif (e2="0110")then a<=et(6);
            elsif (e2="0111")then a<=et(7);
            elsif (e2="1000")then a<=et(8);
            elsif (e2="1001")then a<=et(9);
            elsif (e2="1010")then a<=et(10);
            elsif (e2="1011")then a<=et(11);
            elsif (e2="1100")then a<=et(12);
                else a<="0000";
            end if;
		end if;	
		end if;	
			end process;
end ram;