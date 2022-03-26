
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity UC_mips is
     Port ( instr: in std_logic_vector(2 downto 0);
     
            br_gtz : out std_logic;
            regdst : out std_logic;
            extop : out std_logic;
            alusrc : out std_logic;
            branch : out std_logic;
            jump : out std_logic;
            aluop : out std_logic_vector( 1 downto 0);
            memwrite : out std_logic;
            memtoreg : out std_logic;
            regwrite : out std_logic
         );
end UC_mips;

architecture Behavioral of UC_mips is

begin

process(instr)

begin

            
    case instr is
        when "000" =>  -- tip R
            br_gtz  <='0';
            regdst  <='1';
            extop   <='0';
            alusrc  <='0';
            branch  <='0';
            jump  <='0';
            aluop <="00";
            memwrite <='0';
            memtoreg <='0';
            regwrite <='1';
        when "001" =>--LW
            br_gtz  <='0';
            regdst  <='0';
            extop   <='1';
            alusrc  <='1';
            branch  <='0';
            jump  <='0';
            aluop <="01";-- +
            memwrite <='0';
            memtoreg <='1';
            regwrite <='1';
        when "010" =>--BEQ
            br_gtz  <='0';
            regdst  <='0';
            extop   <='1';
            alusrc  <='0';
            branch  <='1';
            jump  <='0';
            aluop <="10";-- -
            memwrite <='0';
            memtoreg <='0';
            regwrite <='0';
        when "011" =>--ADDI
            br_gtz  <='0';
            regdst  <='0';
            extop   <='1';
            alusrc  <='1';
            branch  <='0';
            jump  <='0';
            aluop <="01";-- +
            memwrite <='0';
            memtoreg <='0';
            regwrite <='1';
        when "100" =>--SW
            br_gtz  <='0';
            regdst  <='0';
            extop   <='1';
            alusrc  <='1';
            branch  <='0';
            jump  <='0';
            aluop <="01";-- +
            memwrite <='1';
            memtoreg <='0';
            regwrite <='0';
        when "101" =>--BGTZ
            br_gtz  <='1';
            regdst  <='0';
            extop   <='1';
            alusrc  <='0';
            branch  <='0';
            jump  <='0';
            aluop <="10";-- -
            memwrite <='0';
            memtoreg <='0';
            regwrite <='0';      
        when "110" =>--SLTI
            br_gtz  <='0';
            regdst  <='0';
            extop   <='1';
            alusrc  <='1';
            branch  <='0';
            jump  <='0';
            aluop <="11";-- <
            memwrite <='0';
            memtoreg <='0';
            regwrite <='1';
        when "111" => --J
        br_gtz  <='0';
            regdst  <='0';
            extop   <='0';
            alusrc  <='0';
            branch  <='0';
            jump  <='1';
            aluop <="00";
            memwrite <='0';
            memtoreg <='0';
            regwrite <='0';
              
               
    end case;


end process;


end Behavioral;
