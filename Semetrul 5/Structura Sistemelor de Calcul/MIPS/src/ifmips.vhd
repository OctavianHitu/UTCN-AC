


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;


entity ifmips is
    Port ( clk : in STD_LOGIC;
           jump : in STD_LOGIC;
           jumpadr : in STD_LOGIC_VECTOR (15 downto 0);
           branchadr : in STD_LOGIC_VECTOR (15 downto 0);
           pcsrc : in STD_LOGIC;
           enable : in STD_LOGIC;
           rst : in STD_LOGIC;
           pcfinal : out std_logic_vector (15 downto 0);
            instruction :out std_logic_vector (15 downto 0)
            );
            
end ifmips;

architecture Behavioral of ifmips is

signal pc: std_logic_vector (15 downto 0);
signal mux1: std_logic_vector ( 15 downto 0);
signal mux2 : std_logic_vector ( 15 downto 0);
signal s1 : std_logic_vector (15 downto 0);
signal d :std_logic_vector(15 downto 0) ;

type t_mem is array (0 to 15) of std_logic_vector(15 downto 0);
signal mem: t_mem :=(
B"000_000_000_001_0_000", --x”0010”--add$1,$0,$0 #i=0,contorul buclei
B"011_000_100_0000110",--x”6206”--addi $4,$0,6 #se salveaza nr de iteratii=6
B"000_000_000_010_0_000",--X"0020"--add $2,$0,$0 #initializarea indexului locatiei de memorie
B"000_000_000_101_0_000",--X"0050"--add$5,$0,$0 #sum=0
--begin loop
B"010_001_100_0000111",--x"4607"--beq $1,$4,6 #s-au facut 6 iteratii>daca da sare inafara buclei
B"001_010_011_0001111",--X"298F"-- lw $3,a_addr($2) #in $3se aduce elemntul curent din sir a_addr16
B"011_011_011_0000011",--X"6D83"-- addi $3,$3,+3 # $3=$3+3
B"100_010_011_0001111",--X"898F"-sw $3,a_addr($2) #salvarea noii valori $3 in elementul din sirul curent
B"000_101_011_101_0_000",--X"15D0"--add $5,$5,$3 #se aduna la suma partiala din $5 elementul curent
B"011_010_010_0000100",--X"6904"--addi $2,$2,4 #indexul uramtorului element din sir
B"011_001_001_0000001",--X"6481"--addi $2,$2,1 #i=i+1\
B"111_0000000000100",--X"E004"--j begin loop #salt inceput bucla-- am pus 4 pentru a sari la linia 4
B"100_101_000_0001101",--X"940D"-- end_loop sw $5,sum_addr($0) #salvare sumei in mem la sum_addr se salveaza la 1d
others=>X"0000") ;


begin

d<=mux2;

process(clk,d)
begin
    if clk'event and clk='1' then
        if rst='1' then 
            pc <= X"0000";
        elsif enable='1' then
            pc<=d;
end if;
end if;
end process;

s1<= pc+'1';
pcfinal <=pc +'1';

mux1 <= branchadr when( pcsrc ='1') else s1;
mux2 <= jumpadr when (jump ='1') else mux1;

instruction<=mem(conv_integer(pc));
end Behavioral;
