----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 01/05/2022 08:16:38 PM
-- Design Name: 
-- Module Name: proc_RISC_tb - Behavioral
-- Project Name: 
-- Target Devices: 
-- Tool Versions: 
-- Description: 
-- 
-- Dependencies: 
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
-- 
----------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity proc_RISC_tb is
--  Port ( );
end proc_RISC_tb;

architecture Behavioral of proc_RISC_tb is
signal Clk      : STD_LOGIC;
signal Rst      : STD_LOGIC;
signal AdrInstr : STD_LOGIC_VECTOR (31 downto 0);
signal Instr    : STD_LOGIC_VECTOR (31 downto 0);
signal Data     : STD_LOGIC_VECTOR (31 downto 0);
signal RA       : STD_LOGIC_VECTOR (31 downto 0);
signal RB       : STD_LOGIC_VECTOR (31 downto 0);
signal F        : STD_LOGIC_VECTOR (31 downto 0);
signal ZF       : STD_LOGIC;
signal CF       : STD_LOGIC;

constant clk_period : time := 20  ns;
begin

clk_process :process
   begin
		clk <= '0';
		wait for clk_period/2;
		clk <= '1';
		wait for clk_period/2;
end process;
procesor: entity WORK.proc_RISC port map(Clk,Rst,AdrInstr,Instr,Data,RA,RB,F,ZF,CF);

proc:process
begin

Rst<='1';
wait for clk_period;

Rst<='0';
wait for 1000 ns;

end process proc;


end Behavioral;
