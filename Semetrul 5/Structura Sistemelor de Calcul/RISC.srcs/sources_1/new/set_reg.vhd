library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity set_reg is
    Port ( Clk   : in  STD_LOGIC;
           Rst   : in  STD_LOGIC;
           WE    : in  STD_LOGIC;
           AdrA  : in  STD_LOGIC_VECTOR (3 downto 0);
           AdrB  : in  STD_LOGIC_VECTOR (3 downto 0);
           AdrD  : in  STD_LOGIC_VECTOR (3 downto 0);
           Din   : in  STD_LOGIC_VECTOR (31 downto 0);
           DoutA : out STD_LOGIC_VECTOR (31 downto 0);
           DoutB : out STD_LOGIC_VECTOR (31 downto 0));
end set_reg;

architecture Behavioral of set_reg is
    type R_TYPE is array (0 to 15) of STD_LOGIC_VECTOR (31 downto 0);
    signal R : R_TYPE := (others => (others => '0'));
begin
    process (Clk)
    begin
        if RISING_EDGE (Clk) then
            if (Rst = '1') then
                DoutA <= (others => '0');
                DoutB <= (others => '0');
            else
                if (WE = '1') then
                    R (CONV_INTEGER (AdrD)) <= Din;
                end if;
                DoutA <= R (CONV_INTEGER (AdrA));
                DoutB <= R (CONV_INTEGER (AdrB));
            end if;
        end if;
    end process;
end Behavioral;
