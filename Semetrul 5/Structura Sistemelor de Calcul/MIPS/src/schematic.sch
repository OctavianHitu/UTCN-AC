# File saved with Nlview 7.0r6  2020-01-29 bk=1.5227 VDI=41 GEI=36 GUI=JA:10.0 non-TLS-threadsafe
# 
# non-default properties - (restore without -noprops)
property attrcolor #000000
property attrfontsize 8
property autobundle 1
property backgroundcolor #ffffff
property boxcolor0 #000000
property boxcolor1 #000000
property boxcolor2 #000000
property boxinstcolor #000000
property boxpincolor #000000
property buscolor #008000
property closeenough 5
property createnetattrdsp 2048
property decorate 1
property elidetext 40
property fillcolor1 #ffffcc
property fillcolor2 #dfebf8
property fillcolor3 #f0f0f0
property gatecellname 2
property instattrmax 30
property instdrag 15
property instorder 1
property marksize 12
property maxfontsize 12
property maxzoom 5
property netcolor #19b400
property objecthighlight0 #ff00ff
property objecthighlight1 #ffff00
property objecthighlight2 #00ff00
property objecthighlight3 #ff6666
property objecthighlight4 #0000ff
property objecthighlight5 #ffc800
property objecthighlight7 #00ffff
property objecthighlight8 #ff00ff
property objecthighlight9 #ccccff
property objecthighlight10 #0ead00
property objecthighlight11 #cefc00
property objecthighlight12 #9e2dbe
property objecthighlight13 #ba6a29
property objecthighlight14 #fc0188
property objecthighlight15 #02f990
property objecthighlight16 #f1b0fb
property objecthighlight17 #fec004
property objecthighlight18 #149bff
property objecthighlight19 #eb591b
property overlapcolor #19b400
property pbuscolor #000000
property pbusnamecolor #000000
property pinattrmax 20
property pinorder 2
property pinpermute 0
property portcolor #000000
property portnamecolor #000000
property ripindexfontsize 8
property rippercolor #000000
property rubberbandcolor #000000
property rubberbandfontsize 12
property selectattr 0
property selectionappearance 2
property selectioncolor #0000ff
property sheetheight 44
property sheetwidth 68
property showmarks 1
property shownetname 0
property showpagenumbers 1
property showripindex 4
property timelimit 1
#
module new test_env work:test_env:NOFILE -nosplit
load symbol MPG work:MPG:NOFILE HIERBOX pin clock input.left pin en output.right pin input input.left boxcolor 1 fillcolor 2 minwidth 13%
load symbol MPG work:abstract:NOFILE HIERBOX pin clock input.left pin en output.right pin input input.left boxcolor 1 fillcolor 2 minwidth 13%
load symbol RTL_MUX10 work MUX pinBus I0 input.left [15:0] pinBus I1 input.left [15:0] pinBus I2 input.left [15:0] pinBus I3 input.left [15:0] pinBus I4 input.left [15:0] pinBus I5 input.left [15:0] pinBus I6 input.left [15:0] pinBus I7 input.left [15:0] pinBus O output.right [15:0] pinBus S input.bot [2:0] fillcolor 1
load symbol RTL_AND work AND pin I0 input pin I1 input pin O output fillcolor 1
load symbol RTL_OR0 work OR pin I0 input pin I1 input pin O output fillcolor 1
load symbol ex_mips work:ex_mips:NOFILE HIERBOX pin alusrc input.left pin gt output.right pin sa input.left pin zero output.right pinBus aluop input.left [1:0] pinBus alures output.right [15:0] pinBus branchaddr output.right [15:0] pinBus extimm input.left [15:0] pinBus func input.left [2:0] pinBus pc1 input.left [15:0] pinBus rd1 input.left [15:0] pinBus rd2 input.left [15:0] boxcolor 1 fillcolor 2 minwidth 13%
load symbol ID_mips work:ID_mips:NOFILE HIERBOX pin clk input.left pin en input.left pin extop input.left pin regdst input.left pin regwr input.left pin sa output.right pinBus extimm output.right [15:0] pinBus func output.right [2:0] pinBus instr input.left [15:0] pinBus rd1 output.right [15:0] pinBus rd2 output.right [15:0] pinBus wd input.left [15:0] boxcolor 1 fillcolor 2 minwidth 13%
load symbol ifmips work:ifmips:NOFILE HIERBOX pin clk input.left pin enable input.left pin jump input.left pin pcsrc input.left pin rst input.left pinBus branchadr input.left [15:0] pinBus instruction output.right [15:0] pinBus jumpadr input.left [15:0] pinBus pcfinal output.right [15:0] boxcolor 1 fillcolor 2 minwidth 13%
load symbol MEM_mips work:MEM_mips:NOFILE HIERBOX pin clk input.left pin en input.left pin memwrite input.left pinBus aluresin input.left [15:0] pinBus aluresout output.right [15:0] pinBus memdata output.right [15:0] pinBus rd2 input.left [15:0] boxcolor 1 fillcolor 2 minwidth 13%
load symbol SSD work:SSD:NOFILE HIERBOX pin clk input.left pinBus an output.right [3:0] pinBus cat output.right [6:0] pinBus digits input.left [15:0] boxcolor 1 fillcolor 2 minwidth 13%
load symbol UC_mips work:UC_mips:NOFILE HIERBOX pin alusrc output.right pin br_gtz output.right pin branch output.right pin extop output.right pin jump output.right pin memtoreg output.right pin memwrite output.right pin regdst output.right pin regwrite output.right pinBus aluop output.right [1:0] pinBus instr input.left [2:0] boxcolor 1 fillcolor 2 minwidth 13%
load symbol RTL_MUX0 work MUX pin S input.bot pinBus I0 input.left [15:0] pinBus I1 input.left [15:0] pinBus O output.right [15:0] fillcolor 1
load port clk input -pg 1 -lvl 0 -x 0 -y 410
load portBus an output [3:0] -attr @name an[3:0] -pg 1 -lvl 11 -x 3130 -y 160
load portBus btn input [4:0] -attr @name btn[4:0] -pg 1 -lvl 0 -x 0 -y 490
load portBus cat output [6:0] -attr @name cat[6:0] -pg 1 -lvl 11 -x 3130 -y 180
load portBus led output [15:0] -attr @name led[15:0] -pg 1 -lvl 11 -x 3130 -y 340
load portBus sw input [15:0] -attr @name sw[15:0] -pg 1 -lvl 0 -x 0 -y 390
load inst mpg1port MPG work:MPG:NOFILE -autohide -attr @cell(#000000) MPG -pg 1 -lvl 1 -x 80 -y 460
load inst mpg2port MPG work:abstract:NOFILE -autohide -attr @cell(#000000) MPG -pg 1 -lvl 7 -x 1890 -y 560
load inst mux2_i RTL_MUX10 work -attr @cell(#000000) RTL_MUX -pinBusAttr I0 @name I0[15:0] -pinBusAttr I0 @attr S=3'b000 -pinBusAttr I1 @name I1[15:0] -pinBusAttr I1 @attr S=3'b001 -pinBusAttr I2 @name I2[15:0] -pinBusAttr I2 @attr S=3'b010 -pinBusAttr I3 @name I3[15:0] -pinBusAttr I3 @attr S=3'b011 -pinBusAttr I4 @name I4[15:0] -pinBusAttr I4 @attr S=3'b100 -pinBusAttr I5 @name I5[15:0] -pinBusAttr I5 @attr S=3'b101 -pinBusAttr I6 @name I6[15:0] -pinBusAttr I6 @attr S=3'b110 -pinBusAttr I7 @name I7[15:0] -pinBusAttr I7 @attr S=3'b111 -pinBusAttr O @name O[15:0] -pinBusAttr S @name S[2:0] -pg 1 -lvl 9 -x 2730 -y 180
load inst pcsrc0_i RTL_AND work -attr @cell(#000000) RTL_AND -pg 1 -lvl 6 -x 1690 -y 120
load inst pcsrc0_i__0 RTL_AND work -attr @cell(#000000) RTL_AND -pg 1 -lvl 6 -x 1690 -y 280
load inst pcsrc_i RTL_OR0 work -attr @cell(#000000) RTL_OR -pg 1 -lvl 7 -x 1890 -y 280
load inst port_ex ex_mips work:ex_mips:NOFILE -autohide -attr @cell(#000000) ex_mips -pinBusAttr aluop @name aluop[1:0] -pinBusAttr alures @name alures[15:0] -pinBusAttr branchaddr @name branchaddr[15:0] -pinBusAttr extimm @name extimm[15:0] -pinBusAttr func @name func[2:0] -pinBusAttr pc1 @name pc1[15:0] -pinBusAttr rd1 @name rd1[15:0] -pinBusAttr rd2 @name rd2[15:0] -pg 1 -lvl 5 -x 1330 -y 80
load inst port_id ID_mips work:ID_mips:NOFILE -autohide -attr @cell(#000000) ID_mips -pinBusAttr extimm @name extimm[15:0] -pinBusAttr func @name func[2:0] -pinBusAttr instr @name instr[15:0] -pinBusAttr rd1 @name rd1[15:0] -pinBusAttr rd2 @name rd2[15:0] -pinBusAttr wd @name wd[15:0] -pg 1 -lvl 4 -x 990 -y 120
load inst port_if ifmips work:ifmips:NOFILE -autohide -attr @cell(#000000) ifmips -pinBusAttr branchadr @name branchadr[15:0] -pinBusAttr instruction @name instruction[15:0] -pinBusAttr jumpadr @name jumpadr[15:0] -pinBusAttr pcfinal @name pcfinal[15:0] -pg 1 -lvl 8 -x 2300 -y 430
load inst port_mem MEM_mips work:MEM_mips:NOFILE -autohide -attr @cell(#000000) MEM_mips -pinBusAttr aluresin @name aluresin[15:0] -pinBusAttr aluresout @name aluresout[15:0] -pinBusAttr memdata @name memdata[15:0] -pinBusAttr rd2 @name rd2[15:0] -pg 1 -lvl 2 -x 340 -y 240
load inst port_ssd SSD work:SSD:NOFILE -autohide -attr @cell(#000000) SSD -pinBusAttr an @name an[3:0] -pinBusAttr cat @name cat[6:0] -pinBusAttr digits @name digits[15:0] -pg 1 -lvl 10 -x 2960 -y 150
load inst port_uc UC_mips work:UC_mips:NOFILE -autohide -attr @cell(#000000) UC_mips -pinBusAttr aluop @name aluop[1:0] -pinBusAttr instr @name instr[2:0] -pg 1 -lvl 10 -x 2960 -y 390
load inst wd_mux_i RTL_MUX0 work -attr @cell(#000000) RTL_MUX -pinBusAttr I0 @name I0[15:0] -pinBusAttr I0 @attr S=1'b1 -pinBusAttr I1 @name I1[15:0] -pinBusAttr I1 @attr S=default -pinBusAttr O @name O[15:0] -pg 1 -lvl 3 -x 670 -y 260
load net alures[0] -attr @rip alures[0] -pin mux2_i I5[0] -pin port_ex alures[0] -pin port_mem aluresin[0]
load net alures[10] -attr @rip alures[10] -pin mux2_i I5[10] -pin port_ex alures[10] -pin port_mem aluresin[10]
load net alures[11] -attr @rip alures[11] -pin mux2_i I5[11] -pin port_ex alures[11] -pin port_mem aluresin[11]
load net alures[12] -attr @rip alures[12] -pin mux2_i I5[12] -pin port_ex alures[12] -pin port_mem aluresin[12]
load net alures[13] -attr @rip alures[13] -pin mux2_i I5[13] -pin port_ex alures[13] -pin port_mem aluresin[13]
load net alures[14] -attr @rip alures[14] -pin mux2_i I5[14] -pin port_ex alures[14] -pin port_mem aluresin[14]
load net alures[15] -attr @rip alures[15] -pin mux2_i I5[15] -pin port_ex alures[15] -pin port_mem aluresin[15]
load net alures[1] -attr @rip alures[1] -pin mux2_i I5[1] -pin port_ex alures[1] -pin port_mem aluresin[1]
load net alures[2] -attr @rip alures[2] -pin mux2_i I5[2] -pin port_ex alures[2] -pin port_mem aluresin[2]
load net alures[3] -attr @rip alures[3] -pin mux2_i I5[3] -pin port_ex alures[3] -pin port_mem aluresin[3]
load net alures[4] -attr @rip alures[4] -pin mux2_i I5[4] -pin port_ex alures[4] -pin port_mem aluresin[4]
load net alures[5] -attr @rip alures[5] -pin mux2_i I5[5] -pin port_ex alures[5] -pin port_mem aluresin[5]
load net alures[6] -attr @rip alures[6] -pin mux2_i I5[6] -pin port_ex alures[6] -pin port_mem aluresin[6]
load net alures[7] -attr @rip alures[7] -pin mux2_i I5[7] -pin port_ex alures[7] -pin port_mem aluresin[7]
load net alures[8] -attr @rip alures[8] -pin mux2_i I5[8] -pin port_ex alures[8] -pin port_mem aluresin[8]
load net alures[9] -attr @rip alures[9] -pin mux2_i I5[9] -pin port_ex alures[9] -pin port_mem aluresin[9]
load net aluresout[0] -attr @rip aluresout[0] -pin port_mem aluresout[0] -pin wd_mux_i I1[0]
load net aluresout[10] -attr @rip aluresout[10] -pin port_mem aluresout[10] -pin wd_mux_i I1[10]
load net aluresout[11] -attr @rip aluresout[11] -pin port_mem aluresout[11] -pin wd_mux_i I1[11]
load net aluresout[12] -attr @rip aluresout[12] -pin port_mem aluresout[12] -pin wd_mux_i I1[12]
load net aluresout[13] -attr @rip aluresout[13] -pin port_mem aluresout[13] -pin wd_mux_i I1[13]
load net aluresout[14] -attr @rip aluresout[14] -pin port_mem aluresout[14] -pin wd_mux_i I1[14]
load net aluresout[15] -attr @rip aluresout[15] -pin port_mem aluresout[15] -pin wd_mux_i I1[15]
load net aluresout[1] -attr @rip aluresout[1] -pin port_mem aluresout[1] -pin wd_mux_i I1[1]
load net aluresout[2] -attr @rip aluresout[2] -pin port_mem aluresout[2] -pin wd_mux_i I1[2]
load net aluresout[3] -attr @rip aluresout[3] -pin port_mem aluresout[3] -pin wd_mux_i I1[3]
load net aluresout[4] -attr @rip aluresout[4] -pin port_mem aluresout[4] -pin wd_mux_i I1[4]
load net aluresout[5] -attr @rip aluresout[5] -pin port_mem aluresout[5] -pin wd_mux_i I1[5]
load net aluresout[6] -attr @rip aluresout[6] -pin port_mem aluresout[6] -pin wd_mux_i I1[6]
load net aluresout[7] -attr @rip aluresout[7] -pin port_mem aluresout[7] -pin wd_mux_i I1[7]
load net aluresout[8] -attr @rip aluresout[8] -pin port_mem aluresout[8] -pin wd_mux_i I1[8]
load net aluresout[9] -attr @rip aluresout[9] -pin port_mem aluresout[9] -pin wd_mux_i I1[9]
load net an[0] -attr @rip an[0] -port an[0] -pin port_ssd an[0]
load net an[1] -attr @rip an[1] -port an[1] -pin port_ssd an[1]
load net an[2] -attr @rip an[2] -port an[2] -pin port_ssd an[2]
load net an[3] -attr @rip an[3] -port an[3] -pin port_ssd an[3]
load net branchadr[0] -attr @rip branchaddr[0] -pin port_ex branchaddr[0] -pin port_if branchadr[0]
load net branchadr[10] -attr @rip branchaddr[10] -pin port_ex branchaddr[10] -pin port_if branchadr[10]
load net branchadr[11] -attr @rip branchaddr[11] -pin port_ex branchaddr[11] -pin port_if branchadr[11]
load net branchadr[12] -attr @rip branchaddr[12] -pin port_ex branchaddr[12] -pin port_if branchadr[12]
load net branchadr[13] -attr @rip branchaddr[13] -pin port_ex branchaddr[13] -pin port_if branchadr[13]
load net branchadr[14] -attr @rip branchaddr[14] -pin port_ex branchaddr[14] -pin port_if branchadr[14]
load net branchadr[15] -attr @rip branchaddr[15] -pin port_ex branchaddr[15] -pin port_if branchadr[15]
load net branchadr[1] -attr @rip branchaddr[1] -pin port_ex branchaddr[1] -pin port_if branchadr[1]
load net branchadr[2] -attr @rip branchaddr[2] -pin port_ex branchaddr[2] -pin port_if branchadr[2]
load net branchadr[3] -attr @rip branchaddr[3] -pin port_ex branchaddr[3] -pin port_if branchadr[3]
load net branchadr[4] -attr @rip branchaddr[4] -pin port_ex branchaddr[4] -pin port_if branchadr[4]
load net branchadr[5] -attr @rip branchaddr[5] -pin port_ex branchaddr[5] -pin port_if branchadr[5]
load net branchadr[6] -attr @rip branchaddr[6] -pin port_ex branchaddr[6] -pin port_if branchadr[6]
load net branchadr[7] -attr @rip branchaddr[7] -pin port_ex branchaddr[7] -pin port_if branchadr[7]
load net branchadr[8] -attr @rip branchaddr[8] -pin port_ex branchaddr[8] -pin port_if branchadr[8]
load net branchadr[9] -attr @rip branchaddr[9] -pin port_ex branchaddr[9] -pin port_if branchadr[9]
load net btn[0] -attr @rip btn[0] -port btn[0] -pin mpg1port input
load net btn[1] -attr @rip btn[1] -port btn[1] -pin mpg2port input
load net cat[0] -attr @rip cat[0] -port cat[0] -pin port_ssd cat[0]
load net cat[1] -attr @rip cat[1] -port cat[1] -pin port_ssd cat[1]
load net cat[2] -attr @rip cat[2] -port cat[2] -pin port_ssd cat[2]
load net cat[3] -attr @rip cat[3] -port cat[3] -pin port_ssd cat[3]
load net cat[4] -attr @rip cat[4] -port cat[4] -pin port_ssd cat[4]
load net cat[5] -attr @rip cat[5] -port cat[5] -pin port_ssd cat[5]
load net cat[6] -attr @rip cat[6] -port cat[6] -pin port_ssd cat[6]
load net clk -port clk -pin mpg1port clock -pin mpg2port clock -pin port_id clk -pin port_if clk -pin port_mem clk -pin port_ssd clk
netloc clk 1 0 10 20 410 190 370 NJ 370 840 330 NJ 330 NJ 330 1830 430 2160 320 NJ 320 2860
load net digits[0] -attr @rip O[0] -pin mux2_i O[0] -pin port_ssd digits[0]
load net digits[10] -attr @rip O[10] -pin mux2_i O[10] -pin port_ssd digits[10]
load net digits[11] -attr @rip O[11] -pin mux2_i O[11] -pin port_ssd digits[11]
load net digits[12] -attr @rip O[12] -pin mux2_i O[12] -pin port_ssd digits[12]
load net digits[13] -attr @rip O[13] -pin mux2_i O[13] -pin port_ssd digits[13]
load net digits[14] -attr @rip O[14] -pin mux2_i O[14] -pin port_ssd digits[14]
load net digits[15] -attr @rip O[15] -pin mux2_i O[15] -pin port_ssd digits[15]
load net digits[1] -attr @rip O[1] -pin mux2_i O[1] -pin port_ssd digits[1]
load net digits[2] -attr @rip O[2] -pin mux2_i O[2] -pin port_ssd digits[2]
load net digits[3] -attr @rip O[3] -pin mux2_i O[3] -pin port_ssd digits[3]
load net digits[4] -attr @rip O[4] -pin mux2_i O[4] -pin port_ssd digits[4]
load net digits[5] -attr @rip O[5] -pin mux2_i O[5] -pin port_ssd digits[5]
load net digits[6] -attr @rip O[6] -pin mux2_i O[6] -pin port_ssd digits[6]
load net digits[7] -attr @rip O[7] -pin mux2_i O[7] -pin port_ssd digits[7]
load net digits[8] -attr @rip O[8] -pin mux2_i O[8] -pin port_ssd digits[8]
load net digits[9] -attr @rip O[9] -pin mux2_i O[9] -pin port_ssd digits[9]
load net en1 -pin mpg1port en -pin port_id en -pin port_if enable -pin port_mem en
netloc en1 1 1 7 210 430 NJ 430 860 450 NJ 450 NJ 450 NJ 450 2140J
load net en2 -pin mpg2port en -pin port_if rst
netloc en2 1 7 1 2100 560n
load net extimm[0] -attr @rip extimm[0] -pin mux2_i I4[0] -pin port_ex extimm[0] -pin port_id extimm[0]
load net extimm[10] -attr @rip extimm[10] -pin mux2_i I4[10] -pin port_ex extimm[10] -pin port_id extimm[10]
load net extimm[11] -attr @rip extimm[11] -pin mux2_i I4[11] -pin port_ex extimm[11] -pin port_id extimm[11]
load net extimm[12] -attr @rip extimm[12] -pin mux2_i I4[12] -pin port_ex extimm[12] -pin port_id extimm[12]
load net extimm[13] -attr @rip extimm[13] -pin mux2_i I4[13] -pin port_ex extimm[13] -pin port_id extimm[13]
load net extimm[14] -attr @rip extimm[14] -pin mux2_i I4[14] -pin port_ex extimm[14] -pin port_id extimm[14]
load net extimm[15] -attr @rip extimm[15] -pin mux2_i I4[15] -pin port_ex extimm[15] -pin port_id extimm[15]
load net extimm[1] -attr @rip extimm[1] -pin mux2_i I4[1] -pin port_ex extimm[1] -pin port_id extimm[1]
load net extimm[2] -attr @rip extimm[2] -pin mux2_i I4[2] -pin port_ex extimm[2] -pin port_id extimm[2]
load net extimm[3] -attr @rip extimm[3] -pin mux2_i I4[3] -pin port_ex extimm[3] -pin port_id extimm[3]
load net extimm[4] -attr @rip extimm[4] -pin mux2_i I4[4] -pin port_ex extimm[4] -pin port_id extimm[4]
load net extimm[5] -attr @rip extimm[5] -pin mux2_i I4[5] -pin port_ex extimm[5] -pin port_id extimm[5]
load net extimm[6] -attr @rip extimm[6] -pin mux2_i I4[6] -pin port_ex extimm[6] -pin port_id extimm[6]
load net extimm[7] -attr @rip extimm[7] -pin mux2_i I4[7] -pin port_ex extimm[7] -pin port_id extimm[7]
load net extimm[8] -attr @rip extimm[8] -pin mux2_i I4[8] -pin port_ex extimm[8] -pin port_id extimm[8]
load net extimm[9] -attr @rip extimm[9] -pin mux2_i I4[9] -pin port_ex extimm[9] -pin port_id extimm[9]
load net func[0] -attr @rip func[0] -pin port_ex func[0] -pin port_id func[0]
load net func[1] -attr @rip func[1] -pin port_ex func[1] -pin port_id func[1]
load net func[2] -attr @rip func[2] -pin port_ex func[2] -pin port_id func[2]
load net gt -pin pcsrc0_i__0 I1 -pin port_ex gt
netloc gt 1 5 1 1560 170n
load net instr[0] -attr @rip instruction[0] -pin mux2_i I0[0] -pin port_id instr[0] -pin port_if instruction[0] -pin port_if jumpadr[0]
load net instr[10] -attr @rip instruction[10] -pin mux2_i I0[10] -pin port_id instr[10] -pin port_if instruction[10] -pin port_if jumpadr[10]
load net instr[11] -attr @rip instruction[11] -pin mux2_i I0[11] -pin port_id instr[11] -pin port_if instruction[11] -pin port_if jumpadr[11]
load net instr[12] -attr @rip instruction[12] -pin mux2_i I0[12] -pin port_id instr[12] -pin port_if instruction[12] -pin port_if jumpadr[12]
load net instr[13] -attr @rip instruction[13] -pin mux2_i I0[13] -pin port_id instr[13] -pin port_if instruction[13] -pin port_uc instr[0]
load net instr[14] -attr @rip instruction[14] -pin mux2_i I0[14] -pin port_id instr[14] -pin port_if instruction[14] -pin port_uc instr[1]
load net instr[15] -attr @rip instruction[15] -pin mux2_i I0[15] -pin port_id instr[15] -pin port_if instruction[15] -pin port_uc instr[2]
load net instr[1] -attr @rip instruction[1] -pin mux2_i I0[1] -pin port_id instr[1] -pin port_if instruction[1] -pin port_if jumpadr[1]
load net instr[2] -attr @rip instruction[2] -pin mux2_i I0[2] -pin port_id instr[2] -pin port_if instruction[2] -pin port_if jumpadr[2]
load net instr[3] -attr @rip instruction[3] -pin mux2_i I0[3] -pin port_id instr[3] -pin port_if instruction[3] -pin port_if jumpadr[3]
load net instr[4] -attr @rip instruction[4] -pin mux2_i I0[4] -pin port_id instr[4] -pin port_if instruction[4] -pin port_if jumpadr[4]
load net instr[5] -attr @rip instruction[5] -pin mux2_i I0[5] -pin port_id instr[5] -pin port_if instruction[5] -pin port_if jumpadr[5]
load net instr[6] -attr @rip instruction[6] -pin mux2_i I0[6] -pin port_id instr[6] -pin port_if instruction[6] -pin port_if jumpadr[6]
load net instr[7] -attr @rip instruction[7] -pin mux2_i I0[7] -pin port_id instr[7] -pin port_if instruction[7] -pin port_if jumpadr[7]
load net instr[8] -attr @rip instruction[8] -pin mux2_i I0[8] -pin port_id instr[8] -pin port_if instruction[8] -pin port_if jumpadr[8]
load net instr[9] -attr @rip instruction[9] -pin mux2_i I0[9] -pin port_id instr[9] -pin port_if instruction[9] -pin port_if jumpadr[9]
load net led[0] -attr @rip 0 -port led[0] -pin port_id regwr -pin port_uc regwrite
load net led[10] -attr @rip aluop[1] -port led[10] -pin port_ex aluop[1] -pin port_uc aluop[1]
load net led[1] -attr @rip 1 -port led[1] -pin port_uc memtoreg -pin wd_mux_i S
load net led[2] -attr @rip 2 -port led[2] -pin port_mem memwrite -pin port_uc memwrite
load net led[3] -attr @rip 3 -port led[3] -pin port_if jump -pin port_uc jump
load net led[4] -attr @rip 4 -port led[4] -pin pcsrc0_i I0 -pin port_uc branch
load net led[5] -attr @rip 5 -port led[5] -pin port_ex alusrc -pin port_uc alusrc
load net led[6] -attr @rip 6 -port led[6] -pin port_id extop -pin port_uc extop
load net led[7] -attr @rip 7 -port led[7] -pin port_id regdst -pin port_uc regdst
load net led[8] -attr @rip 8 -port led[8] -pin pcsrc0_i__0 I0 -pin port_uc br_gtz
load net led[9] -attr @rip aluop[0] -port led[9] -pin port_ex aluop[0] -pin port_uc aluop[0]
load net memdata[0] -attr @rip memdata[0] -pin mux2_i I6[0] -pin port_mem memdata[0] -pin wd_mux_i I0[0]
load net memdata[10] -attr @rip memdata[10] -pin mux2_i I6[10] -pin port_mem memdata[10] -pin wd_mux_i I0[10]
load net memdata[11] -attr @rip memdata[11] -pin mux2_i I6[11] -pin port_mem memdata[11] -pin wd_mux_i I0[11]
load net memdata[12] -attr @rip memdata[12] -pin mux2_i I6[12] -pin port_mem memdata[12] -pin wd_mux_i I0[12]
load net memdata[13] -attr @rip memdata[13] -pin mux2_i I6[13] -pin port_mem memdata[13] -pin wd_mux_i I0[13]
load net memdata[14] -attr @rip memdata[14] -pin mux2_i I6[14] -pin port_mem memdata[14] -pin wd_mux_i I0[14]
load net memdata[15] -attr @rip memdata[15] -pin mux2_i I6[15] -pin port_mem memdata[15] -pin wd_mux_i I0[15]
load net memdata[1] -attr @rip memdata[1] -pin mux2_i I6[1] -pin port_mem memdata[1] -pin wd_mux_i I0[1]
load net memdata[2] -attr @rip memdata[2] -pin mux2_i I6[2] -pin port_mem memdata[2] -pin wd_mux_i I0[2]
load net memdata[3] -attr @rip memdata[3] -pin mux2_i I6[3] -pin port_mem memdata[3] -pin wd_mux_i I0[3]
load net memdata[4] -attr @rip memdata[4] -pin mux2_i I6[4] -pin port_mem memdata[4] -pin wd_mux_i I0[4]
load net memdata[5] -attr @rip memdata[5] -pin mux2_i I6[5] -pin port_mem memdata[5] -pin wd_mux_i I0[5]
load net memdata[6] -attr @rip memdata[6] -pin mux2_i I6[6] -pin port_mem memdata[6] -pin wd_mux_i I0[6]
load net memdata[7] -attr @rip memdata[7] -pin mux2_i I6[7] -pin port_mem memdata[7] -pin wd_mux_i I0[7]
load net memdata[8] -attr @rip memdata[8] -pin mux2_i I6[8] -pin port_mem memdata[8] -pin wd_mux_i I0[8]
load net memdata[9] -attr @rip memdata[9] -pin mux2_i I6[9] -pin port_mem memdata[9] -pin wd_mux_i I0[9]
load net pcfin[0] -attr @rip pcfinal[0] -pin mux2_i I1[0] -pin port_ex pc1[0] -pin port_if pcfinal[0]
load net pcfin[10] -attr @rip pcfinal[10] -pin mux2_i I1[10] -pin port_ex pc1[10] -pin port_if pcfinal[10]
load net pcfin[11] -attr @rip pcfinal[11] -pin mux2_i I1[11] -pin port_ex pc1[11] -pin port_if pcfinal[11]
load net pcfin[12] -attr @rip pcfinal[12] -pin mux2_i I1[12] -pin port_ex pc1[12] -pin port_if pcfinal[12]
load net pcfin[13] -attr @rip pcfinal[13] -pin mux2_i I1[13] -pin port_ex pc1[13] -pin port_if jumpadr[13] -pin port_if pcfinal[13]
load net pcfin[14] -attr @rip pcfinal[14] -pin mux2_i I1[14] -pin port_ex pc1[14] -pin port_if jumpadr[14] -pin port_if pcfinal[14]
load net pcfin[15] -attr @rip pcfinal[15] -pin mux2_i I1[15] -pin port_ex pc1[15] -pin port_if jumpadr[15] -pin port_if pcfinal[15]
load net pcfin[1] -attr @rip pcfinal[1] -pin mux2_i I1[1] -pin port_ex pc1[1] -pin port_if pcfinal[1]
load net pcfin[2] -attr @rip pcfinal[2] -pin mux2_i I1[2] -pin port_ex pc1[2] -pin port_if pcfinal[2]
load net pcfin[3] -attr @rip pcfinal[3] -pin mux2_i I1[3] -pin port_ex pc1[3] -pin port_if pcfinal[3]
load net pcfin[4] -attr @rip pcfinal[4] -pin mux2_i I1[4] -pin port_ex pc1[4] -pin port_if pcfinal[4]
load net pcfin[5] -attr @rip pcfinal[5] -pin mux2_i I1[5] -pin port_ex pc1[5] -pin port_if pcfinal[5]
load net pcfin[6] -attr @rip pcfinal[6] -pin mux2_i I1[6] -pin port_ex pc1[6] -pin port_if pcfinal[6]
load net pcfin[7] -attr @rip pcfinal[7] -pin mux2_i I1[7] -pin port_ex pc1[7] -pin port_if pcfinal[7]
load net pcfin[8] -attr @rip pcfinal[8] -pin mux2_i I1[8] -pin port_ex pc1[8] -pin port_if pcfinal[8]
load net pcfin[9] -attr @rip pcfinal[9] -pin mux2_i I1[9] -pin port_ex pc1[9] -pin port_if pcfinal[9]
load net pcsrc -pin pcsrc_i O -pin port_if pcsrc
netloc pcsrc 1 7 1 2020 280n
load net pcsrc0 -pin pcsrc0_i O -pin pcsrc_i I0
netloc pcsrc0 1 6 1 1830 120n
load net pcsrc0_i__0_n_0 -pin pcsrc0_i__0 O -pin pcsrc_i I1
netloc pcsrc0_i__0_n_0 1 6 1 1810 280n
load net rd1[0] -attr @rip rd1[0] -pin mux2_i I2[0] -pin port_ex rd1[0] -pin port_id rd1[0]
load net rd1[10] -attr @rip rd1[10] -pin mux2_i I2[10] -pin port_ex rd1[10] -pin port_id rd1[10]
load net rd1[11] -attr @rip rd1[11] -pin mux2_i I2[11] -pin port_ex rd1[11] -pin port_id rd1[11]
load net rd1[12] -attr @rip rd1[12] -pin mux2_i I2[12] -pin port_ex rd1[12] -pin port_id rd1[12]
load net rd1[13] -attr @rip rd1[13] -pin mux2_i I2[13] -pin port_ex rd1[13] -pin port_id rd1[13]
load net rd1[14] -attr @rip rd1[14] -pin mux2_i I2[14] -pin port_ex rd1[14] -pin port_id rd1[14]
load net rd1[15] -attr @rip rd1[15] -pin mux2_i I2[15] -pin port_ex rd1[15] -pin port_id rd1[15]
load net rd1[1] -attr @rip rd1[1] -pin mux2_i I2[1] -pin port_ex rd1[1] -pin port_id rd1[1]
load net rd1[2] -attr @rip rd1[2] -pin mux2_i I2[2] -pin port_ex rd1[2] -pin port_id rd1[2]
load net rd1[3] -attr @rip rd1[3] -pin mux2_i I2[3] -pin port_ex rd1[3] -pin port_id rd1[3]
load net rd1[4] -attr @rip rd1[4] -pin mux2_i I2[4] -pin port_ex rd1[4] -pin port_id rd1[4]
load net rd1[5] -attr @rip rd1[5] -pin mux2_i I2[5] -pin port_ex rd1[5] -pin port_id rd1[5]
load net rd1[6] -attr @rip rd1[6] -pin mux2_i I2[6] -pin port_ex rd1[6] -pin port_id rd1[6]
load net rd1[7] -attr @rip rd1[7] -pin mux2_i I2[7] -pin port_ex rd1[7] -pin port_id rd1[7]
load net rd1[8] -attr @rip rd1[8] -pin mux2_i I2[8] -pin port_ex rd1[8] -pin port_id rd1[8]
load net rd1[9] -attr @rip rd1[9] -pin mux2_i I2[9] -pin port_ex rd1[9] -pin port_id rd1[9]
load net rd2[0] -attr @rip rd2[0] -pin mux2_i I3[0] -pin port_ex rd2[0] -pin port_id rd2[0] -pin port_mem rd2[0]
load net rd2[10] -attr @rip rd2[10] -pin mux2_i I3[10] -pin port_ex rd2[10] -pin port_id rd2[10] -pin port_mem rd2[10]
load net rd2[11] -attr @rip rd2[11] -pin mux2_i I3[11] -pin port_ex rd2[11] -pin port_id rd2[11] -pin port_mem rd2[11]
load net rd2[12] -attr @rip rd2[12] -pin mux2_i I3[12] -pin port_ex rd2[12] -pin port_id rd2[12] -pin port_mem rd2[12]
load net rd2[13] -attr @rip rd2[13] -pin mux2_i I3[13] -pin port_ex rd2[13] -pin port_id rd2[13] -pin port_mem rd2[13]
load net rd2[14] -attr @rip rd2[14] -pin mux2_i I3[14] -pin port_ex rd2[14] -pin port_id rd2[14] -pin port_mem rd2[14]
load net rd2[15] -attr @rip rd2[15] -pin mux2_i I3[15] -pin port_ex rd2[15] -pin port_id rd2[15] -pin port_mem rd2[15]
load net rd2[1] -attr @rip rd2[1] -pin mux2_i I3[1] -pin port_ex rd2[1] -pin port_id rd2[1] -pin port_mem rd2[1]
load net rd2[2] -attr @rip rd2[2] -pin mux2_i I3[2] -pin port_ex rd2[2] -pin port_id rd2[2] -pin port_mem rd2[2]
load net rd2[3] -attr @rip rd2[3] -pin mux2_i I3[3] -pin port_ex rd2[3] -pin port_id rd2[3] -pin port_mem rd2[3]
load net rd2[4] -attr @rip rd2[4] -pin mux2_i I3[4] -pin port_ex rd2[4] -pin port_id rd2[4] -pin port_mem rd2[4]
load net rd2[5] -attr @rip rd2[5] -pin mux2_i I3[5] -pin port_ex rd2[5] -pin port_id rd2[5] -pin port_mem rd2[5]
load net rd2[6] -attr @rip rd2[6] -pin mux2_i I3[6] -pin port_ex rd2[6] -pin port_id rd2[6] -pin port_mem rd2[6]
load net rd2[7] -attr @rip rd2[7] -pin mux2_i I3[7] -pin port_ex rd2[7] -pin port_id rd2[7] -pin port_mem rd2[7]
load net rd2[8] -attr @rip rd2[8] -pin mux2_i I3[8] -pin port_ex rd2[8] -pin port_id rd2[8] -pin port_mem rd2[8]
load net rd2[9] -attr @rip rd2[9] -pin mux2_i I3[9] -pin port_ex rd2[9] -pin port_id rd2[9] -pin port_mem rd2[9]
load net sa -pin port_ex sa -pin port_id sa
netloc sa 1 4 1 N 230
load net sw[5] -attr @rip sw[5] -pin mux2_i S[0] -port sw[5]
load net sw[6] -attr @rip sw[6] -pin mux2_i S[1] -port sw[6]
load net sw[7] -attr @rip sw[7] -pin mux2_i S[2] -port sw[7]
load net wd[0] -attr @rip O[0] -pin mux2_i I7[0] -pin port_id wd[0] -pin wd_mux_i O[0]
load net wd[10] -attr @rip O[10] -pin mux2_i I7[10] -pin port_id wd[10] -pin wd_mux_i O[10]
load net wd[11] -attr @rip O[11] -pin mux2_i I7[11] -pin port_id wd[11] -pin wd_mux_i O[11]
load net wd[12] -attr @rip O[12] -pin mux2_i I7[12] -pin port_id wd[12] -pin wd_mux_i O[12]
load net wd[13] -attr @rip O[13] -pin mux2_i I7[13] -pin port_id wd[13] -pin wd_mux_i O[13]
load net wd[14] -attr @rip O[14] -pin mux2_i I7[14] -pin port_id wd[14] -pin wd_mux_i O[14]
load net wd[15] -attr @rip O[15] -pin mux2_i I7[15] -pin port_id wd[15] -pin wd_mux_i O[15]
load net wd[1] -attr @rip O[1] -pin mux2_i I7[1] -pin port_id wd[1] -pin wd_mux_i O[1]
load net wd[2] -attr @rip O[2] -pin mux2_i I7[2] -pin port_id wd[2] -pin wd_mux_i O[2]
load net wd[3] -attr @rip O[3] -pin mux2_i I7[3] -pin port_id wd[3] -pin wd_mux_i O[3]
load net wd[4] -attr @rip O[4] -pin mux2_i I7[4] -pin port_id wd[4] -pin wd_mux_i O[4]
load net wd[5] -attr @rip O[5] -pin mux2_i I7[5] -pin port_id wd[5] -pin wd_mux_i O[5]
load net wd[6] -attr @rip O[6] -pin mux2_i I7[6] -pin port_id wd[6] -pin wd_mux_i O[6]
load net wd[7] -attr @rip O[7] -pin mux2_i I7[7] -pin port_id wd[7] -pin wd_mux_i O[7]
load net wd[8] -attr @rip O[8] -pin mux2_i I7[8] -pin port_id wd[8] -pin wd_mux_i O[8]
load net wd[9] -attr @rip O[9] -pin mux2_i I7[9] -pin port_id wd[9] -pin wd_mux_i O[9]
load net zero -pin pcsrc0_i I1 -pin port_ex zero
netloc zero 1 5 1 1600 130n
load netBundle @btn 2 btn[1] btn[0] -autobundled
netbloc @btn 1 0 7 20 590 NJ 590 NJ 590 NJ 590 NJ 590 NJ 590 1810J
load netBundle @sw 3 sw[7] sw[6] sw[5] -autobundled
netbloc @sw 1 0 9 NJ 390 NJ 390 NJ 390 NJ 390 NJ 390 NJ 390 NJ 390 2060J 300 NJ
load netBundle @an 4 an[3] an[2] an[1] an[0] -autobundled
netbloc @an 1 10 1 NJ 160
load netBundle @cat 7 cat[6] cat[5] cat[4] cat[3] cat[2] cat[1] cat[0] -autobundled
netbloc @cat 1 10 1 NJ 180
load netBundle @led 11 led[10] led[9] led[8] led[7] led[6] led[5] led[4] led[3] led[2] led[1] led[0] -autobundled
netbloc @led 1 1 10 230 190 520J 320N 820 310 1200 310 1620 470 NJ 470 2120 340 NJ 340 NJ 340 3110
load netBundle @digits 16 digits[15] digits[14] digits[13] digits[12] digits[11] digits[10] digits[9] digits[8] digits[7] digits[6] digits[5] digits[4] digits[3] digits[2] digits[1] digits[0] -autobundled
netbloc @digits 1 9 1 N 180
load netBundle @alures 16 alures[15] alures[14] alures[13] alures[12] alures[11] alures[10] alures[9] alures[8] alures[7] alures[6] alures[5] alures[4] alures[3] alures[2] alures[1] alures[0] -autobundled
netbloc @alures 1 1 8 190 30 NJ 30 NJ 30 NJ 30 1520 190 NJ 190 NJ 190 2500J
load netBundle @branchadr 16 branchadr[15] branchadr[14] branchadr[13] branchadr[12] branchadr[11] branchadr[10] branchadr[9] branchadr[8] branchadr[7] branchadr[6] branchadr[5] branchadr[4] branchadr[3] branchadr[2] branchadr[1] branchadr[0] -autobundled
netbloc @branchadr 1 5 3 1580 410 NJ 410 2180J
load netBundle @extimm 16 extimm[15] extimm[14] extimm[13] extimm[12] extimm[11] extimm[10] extimm[9] extimm[8] extimm[7] extimm[6] extimm[5] extimm[4] extimm[3] extimm[2] extimm[1] extimm[0] -autobundled
netbloc @extimm 1 4 5 1160 10 1640J 170 NJ 170 NJ 170 2560J
load netBundle @func 3 func[2] func[1] func[0] -autobundled
netbloc @func 1 4 1 1180 150n
load netBundle @rd1 16 rd1[15] rd1[14] rd1[13] rd1[12] rd1[11] rd1[10] rd1[9] rd1[8] rd1[7] rd1[6] rd1[5] rd1[4] rd1[3] rd1[2] rd1[1] rd1[0] -autobundled
netbloc @rd1 1 4 5 1180 270 1520J 210 NJ 210 NJ 210 2480J
load netBundle @rd2 16 rd2[15] rd2[14] rd2[13] rd2[12] rd2[11] rd2[10] rd2[9] rd2[8] rd2[7] rd2[6] rd2[5] rd2[4] rd2[3] rd2[2] rd2[1] rd2[0] -autobundled
netbloc @rd2 1 1 8 230 410 NJ 410 NJ 410 1160 350 NJ 350 NJ 350 2000J 260 2580J
load netBundle @instr 16 instr[15] instr[14] instr[13] instr[12] instr[11] instr[10] instr[9] instr[8] instr[7] instr[6] instr[5] instr[4] instr[3] instr[2] instr[1] instr[0] -autobundled
netbloc @instr 1 3 7 900 490 NJ 490 NJ 490 NJ 490 2100 360 2520 480 2860J
load netBundle @pcfin 16 pcfin[15] pcfin[14] pcfin[13] pcfin[12] pcfin[11] pcfin[10] pcfin[9] pcfin[8] pcfin[7] pcfin[6] pcfin[5] pcfin[4] pcfin[3] pcfin[2] pcfin[1] pcfin[0] -autobundled
netbloc @pcfin 1 4 5 1220 510 NJ 510 NJ 510 2080 380 2540
load netBundle @aluresout 16 aluresout[15] aluresout[14] aluresout[13] aluresout[12] aluresout[11] aluresout[10] aluresout[9] aluresout[8] aluresout[7] aluresout[6] aluresout[5] aluresout[4] aluresout[3] aluresout[2] aluresout[1] aluresout[0] -autobundled
netbloc @aluresout 1 2 1 N 270
load netBundle @memdata 16 memdata[15] memdata[14] memdata[13] memdata[12] memdata[11] memdata[10] memdata[9] memdata[8] memdata[7] memdata[6] memdata[5] memdata[4] memdata[3] memdata[2] memdata[1] memdata[0] -autobundled
netbloc @memdata 1 2 7 540 200 800J 290 NJ 290 1540J 230 NJ 230 NJ 230 N
load netBundle @wd 16 wd[15] wd[14] wd[13] wd[12] wd[11] wd[10] wd[9] wd[8] wd[7] wd[6] wd[5] wd[4] wd[3] wd[2] wd[1] wd[0] -autobundled
netbloc @wd 1 3 6 880 370 NJ 370 NJ 370 NJ 370 2040J 280 2600
levelinfo -pg 1 0 80 340 670 990 1330 1690 1890 2300 2730 2960 3130
pagesize -pg 1 -db -bbox -sgen -110 0 3240 630
show
zoom 0.366306
scrollpos -36 -182
#
# initialize ictrl to current module test_env work:test_env:NOFILE
ictrl init topinfo |
