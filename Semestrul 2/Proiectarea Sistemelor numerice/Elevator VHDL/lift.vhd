library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.all;
use IEEE.STD_LOGIC_ARITH.all;


entity LIFT is 		   
	port( intrare: in STD_LOGIC_VECTOR(3 downto 0);
	control: in STD_LOGIC_VECTOR(3 downto 0);
	ui: in STD_LOGIC;
	--ud: inout STD_LOGIC;
	ok,res,clk:in STD_LOGIC;
	SENZOR: in STD_LOGIC;
	--SENZOR_O: out STD_LOGIC;
	CATOD: out STD_LOGIC_VECTOR(6 downto 0));
end LIFT;

architecture automat of lift is  

component poartasi is
	port( a,b,c : in STD_LOGIC;
	x: out STD_LOGIC);
end component poartasi;

component poartanu is 
	port(a: in STD_LOGIC;
	y: out STD_LOGIC);
end component poartanu;

component memram is
	port(  e1,e2 : in STD_LOGIC_VECTOR(3 downto 0);
	clke,res: in STD_LOGIC;
    ej: in STD_LOGIC_VECTOR(3 downto 0);
	a: out STD_LOGIC_VECTOR(3 downto 0));
	end component memram;

component unit is
	port( control : in STD_LOGIC_VECTOR(3 downto 0);
	--OUT_NUM: in STD_LOGIC_VECTOR(3 downto 0);
	  urca: in STD_LOGIC;
	clk_num: out STD_LOGIC);
end component unit;


component  num1 is
	port(
		res:in STD_LOgic;
	clk: in STD_LOGIC;
	urca: out STD_LOGIC;
	etout: out STD_LOGIC_VECTOR(3 downto 0));
end component num1;

component NUMARATOR_2 is
	port( clk,res: in STD_LOGIC;
	urca:in STD_LOGIC;
	etout: in STD_LOGIC_VECTOR(3 downto 0);
	isr: out STD_LOGIC:='0'; 
	etout2: out STD_LOGIC_VECTOR(3 downto 0));
end component NUMARATOR_2; 

component num3 is 
	port(clk: in STD_LOGIC;
	res: in STD_LOGIC;
	urca: in STD_LOGIC;
	UI: in STD_LOGIC;
	O: out STD_LOGIC_VECTOR(3 downto 0);
	CD,CU: out STD_LOGIC);
end component num3;	

component num4 is 
	port( cu,cd,res: in STD_LOGIC;
	O: out STD_LOGIC_VECTOR(3 downto 0));
end component num4;

component afisor is
    port( clk : in STD_LOGIC;
	     res:in std_logic;
		  D1: in STD_LOGIC_VECTOR(3 downto 0); 
		  D2: in STD_LOGIC_VECTOR(3 downto 0);
          x : out STD_LOGIC_VECTOR (3 downto 0);
          y : out STD_LOGIC_VECTOR (6 downto 0));
  end component afisor ;

signal OUT_NUM:STD_LOGIC_VECTOR(3 downto 0);
signal OUT_NUM2:STD_LOGIC_VECTOR(3 downto 0);
signal OUT_NUM3 : STD_LOGIC_VECTOR(3 downto 0);
signal OUT_NUM4:STD_LOGIC_VECTOR(3 downto 0);
signal ANOD: STD_LOGIC_VECTOR(3 downto 0);
signal CL: STD_LOGIC;
signal CU,CD: STD_LOGIC;
signal c:STD_LOGIC;
signal urca:STD_LOGIC;
signal clk0:STD_LOGIC;
signal clk1:STD_LOGIC;
signal clk2:STD_LOGIC;
signal controly: STD_LOGIC_VECTOR(3 downto 0);	 

begin

	P1: memram port map ( intrare,OUT_NUM,ok,res,control,controly); 
	P2: unit port map(controly,urca,clk0);
	P3: poartasi port map(clk0,UI,SENZOR,CL);
	P4: poartasi port map(CL,c,'1',clk1);		
    P5: num1 port map(res,CL,urca,OUT_NUM); 
    --P6: poartanu port map(c,UD); 					 
	P7: poartanu port map(clk0,clk2);
    P8: NUMARATOR_2 port map(clk2,res,urca,OUT_NUM,c,OUT_NUM2);
	P9: num3 port map(clk2,res,urca,UI,OUT_NUM3,CD,CU);
	P10: num4 port map( CU,CD,res,OUT_NUM4);
	P11: AFISOR port map(clk,res,OUT_NUM4,OUT_NUM3,ANOD,CATOD);
end automat;
