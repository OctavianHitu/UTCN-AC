 library IEEE;
  use IEEE.STD_LOGIC_1164.ALL;

 entity reg is
 port ( clk,res : in STD_LOGIC;
 	   Y : out STD_LOGIC_VECTOR (3 downto 0));
 end reg;
             



architecture registru of reg is
  signal anod: STD_LOGIC_VECTOR(3 downto 0);
    begin
      Y<=anod;
         process(clk)
		 variable k:integer range 0 to 3;
		
                begin
 	               if(clk'event and clk='1') then
 	                   if(res='1') then
 	                         anod<="1111";
 	                         k:=0;
 	                      else
 	                            anod<="1111";
 	                            anod(k)<='0';
 	                            if k=3 then k:=0  ;
									 else k:=k +1;	end if;
 	                      end if;
 	                  end if;
					   
           end process;
end registru;