#############################################
# Sets
#############################################
set L; #��·����						
set F; #���˼���						
set C{f in F};	#ѡ����������ģʽ����		              
set G{f in F, c in C[f]}; #��ѡ�����˺�ģʽ�µĹ�������󼯺�       

#set NG{f in F, c in C[f]}; #��ѡ�����˺�ģʽ�µĹ�������󼯺�                                                                       
set NR;		        #�ڵ�Լ��ϣ�һ���ڵ�Զ�Ӧһ��ҵ�񣬷���һ�·������һ��			
set P{r in NR};		#·����������·����			
set TW{r in NR};	#ʱ϶������		
set TS{r in NR, w in TW[r]}; #ʱ϶����ʱ϶����

############################################# 
# Params
#############################################
param SChNum{f in F, c in C[f]} integer; #����������
param d{r in NR} >0 integer;	#�ڵ����������Ƶ϶						            
param Nodenum integer;          #�ڵ����
param W integer;			    #����������Ƶ϶��
param omiga integer;			#�ܵ�ʱ϶��							                                                        
param afa;			#Ȩ������							
param M;			#����ֵ							
param e;			#��Сֵ							
param NRnumber integer;	#�ڵ�Ը�����ҵ�������
param Rr{ f in F, c in C[f],  r in NR, l in P[r]} integer; #�ڵ��ҵ�񾭹��������ʵ������Ƶ�ף��Ƿ�Ҫ��t��
param GG{f in F, c in C[f]}; #��ѡ�����˺�ģʽ�µĹ�������󼯺�     
#############################################
# Vars
#############################################
var S{f in F, c in C[f],i in G[f,c],r in NR,l in P[r]} >=1 integer;			  #ҵ������·l�й�����i��Ƶ϶the starting index
var E{f in F, c in C[f],i in G[f,c], r in NR,l in P[r]} >=1 integer;			  #ҵ������·l�й�����i��Ƶ϶the ending index
var rou{f in F, c in C[f],  i in G[f,c], r1 in NR, r2 in NR} binary;	#����ڵ��r2��·������ʼ	Ƶ϶����ֵ���ڽڵ��r1��·��Ԥ������ʼƵ϶����ֵ����Ϊ1������0         
var XX{f in F, c in C[f],  r in NR, w in TW[r]}  binary;		  #�ڵ��r��ͨ�����������ʱ϶��w����Ϊ1������0		
var O{f in F, c in C[f], l in L, i in G[f,c], r in NR, t in 0..omiga} binary;	#����ڵ��r��ʱ϶tʱ��ѡ����·l�Ĺ�����i������ͨ����Ϊ1��						                                       
var K{f in F, c in C[f], l in L, i in G[f,c], r in NR, w in TW[r]} binary;
var Trnumber{f in F, c in C[f]} >=0 integer;	#������������������������й�
var Fmax{f in F, c in C[f],  i in G[f,c],  t in 0..omiga} >=0 integer;    #���Ƶ϶��

var SChchoose{r in NR, f in F, c in C[f]} binary;
var Fchoose{r in NR, f in F} binary;
var gchoose{r in NR, f in F, c in C[f], i in G[f,c],l in P[r]} binary;
var NT{f in F, c in C[f],  i in G[f,c], t in 0..omiga} >=0 integer;
var cita{f in F ,c in C[f], i in G[f,c] , l in L , k in 1..W ,t in 0..omiga} binary;
var beta{f in F ,c in C[f], r in NR, k in 0..W} binary;
var gama{f in F ,c in C[f], r in NR, k in 0..W} binary;
var ga{f in F ,c in C[f], i in G[f,c], r in NR, l in P[r]} binary;
var gc{f in F ,c in C[f], i in G[f,c], r in NR, l in P[r]} binary;
var gp{f in F ,c in C[f], i in G[f,c], r in NR, l in P[r]} >=0 integer;
var OP integer;

var RE{f in F, c in C[f],  i in G[f,c], t in 0..omiga} >=0 integer; 
#############################################  
# Objective Ŀ�꺯������Ҫ����
#############################################
minimize aim:
  OP + sum{f in F,c in C[f], r in NR, i in G[f,c], t in 0..omiga}NT[f,c,i,t];
  
  
subject to Cons0_31 {r in NR}:
  sum{f in F}Fchoose[r,f] = 1 ;


subject to Cons0_1 {r in NR}:
  sum{f in F} sum{c in C[f]}SChchoose[r,f,c]<=sum{f in F}Fchoose[r,f] ;
  
subject to Cons0_2 {r in NR}:
  sum{f in F}sum{l in P[r]}sum{c in C[f]}sum{i in G[f,c]}gchoose[r,f,c,i,l] <=  sum{f in F} sum{c in C[f]}SChchoose[r,f,c];

  
 #subject to Cons0_31 {r in NR,f in F, c in C[f,c], i in G[f,c,i]}:
 # sum{g in GG[f,c,i,g]}Fchoose[r,f,i,g] = 1 ;
  
subject to Cons0_3 {f in F, c in C[f]}:
  Trnumber[f,c] = f/SChNum[f,c] ;
  

subject to Cons0_4 {f in F, c in C[f], i in G[f,c], r in NR, l in P[r] }: #是否有问题
    ga[f,c,i,r,l]*gp[f,c,i,r,l] = Rr[f,c,r,l];
     		
# (1)ʱ�䴰ѡ��Լ��
subject to Cons1{f in F, c in C[f], r in NR}: #ȷ��ÿ���ڵ���ڽ����ƻ�ҵ���ʱ������ֻ��һ��ʱ϶���ᱻѡ��
	sum{w in TW[r]}XX[f,c,r,w]=1;
	
			
# (2)Ƶ�׷���Լ��
subject to Cons2_1{f in F, c in C[f], i in G[f,c], r in NR,l in P[r]}: # ȷ��Ƶ϶������
	E[f,c,i,r,l]-S[f,c,i,r,l]-ga[f,c,i,r,l]*gp[f,c,i,r,l] +1 <= M*( 1 - ga[f,c,i,r,l] );
	
	
subject to Cons2_111{f in F, c in C[f], i in G[f,c], r in NR, l in P[r]}: 
	E[f,c,i,r,l]-S[f,c,i,r,l]-ga[f,c,i,r,l]*gp[f,c,i,r,l] +1 >= - M*(1 - ga[f,c,i,r,l] );
	
	
subject to Cons2_2{f in F, c in C[f], r in NR, l in P[r], i in G[f,c]}: # Ƶ϶����ָ��С����Ƶ϶��
	E[f,c,i,r,l]<=W;

  subject to Cons2_333{f in F, c in C[f], r in NR,l in P[r], i in G[f,c]}: # Ƶ϶����ָ��С����Ƶ϶��
	E[f,c,i,r,l]>= S[f,c,i,r,l];
		
subject to Cons22_3{f in F, c in C[f], r in NR, i in G[f,c], j in G[f,c],l1 in P[r], l2 in P[r]: !(l1==l2)}:
	E[f,c,i,r,l1]=E[f,c,j,r,l2];

subject to Cons22_4{f in F, c in C[f], r in NR, i in G[f,c], j in G[f,c],l1 in P[r], l2 in P[r]: !(l1==l2) }:
	S[f,c,i,r,l1]=S[f,c,j,r,l2];

			
subject to Cons2_3{f in F, c in C[f], r1 in NR, i in G[f,c], r2 in NR : !(r1==r2)}:
	rou[f,c,i,r2,r1] + rou[f,c,i,r1,r2] = 1;  #ȷ������·����ʼƵ϶ָ�겻ͬ

 subject to Cons2_4{f in F, c in C[f], i in G[f,c], r1 in NR,  r2 in NR,  w1 in TW[r1], w2 in TW[r2], t in TS[r1,w1] intersect TS[r2,w2], l in P[r1] intersect P[r2] : !(r1==r2)}:
	E[f,c,i,r2,l]-S[f,c,i,r1,l] <= M*(rou[f,c,i,r2,r1]+1-O[f,c,l,i,r1,t]+1-O[f,c,l,i,r2,t]+1-XX[f,c,r1,w1]+1-XX[f,c,r2,w2])-1;

#�����ͬҵ��ѡ����ͬ�������ʱ����Ƶ϶���غ�


	# (3)�ռ���Լ��
#subject to Cons3_3{f in F,c in C[f], i1 in G[f,c], i2 in G[f,c], r in NR, w in TW[r], t in TS[r,w], l in P[r]}:
#	O[f,c,l,i1,r,t]+O[f,c,l,i2,r,t]=1; #һ���ƻ�ҵ����ĳ��ʱ϶���������ù�ͨ������


subject to Cons3_31{f in F,c in C[f], r in NR, i in G[f,c], w in TW[r], l in P[r]}:
	K[f,c,l,i,r,w]-1  <= M*(1-ga[f,c,i,r,l]+1-XX[f,c,r,w]); #һ���ƻ�ҵ����ĳ��ʱ϶���������ù�ͨ����������ÿһ����·�ϣ����ҽ���һ�����˷������ڽ����ù�ͨ����
	                                                                                                                                                                                            
subject to Cons3_32{f in F,c in C[f], r in NR, i in G[f,c], w in TW[r],  l in P[r]}:
	K[f,c,l,i,r,w]-1 >= -M*(1-ga[f,c,i,r,l]+1-XX[f,c,r,w]);   #һ��ĳ��·��������ͨ������·������������·����ֻ��һ��������ᱻ���ڽ�����ͨ����
	


#subject to Cons3_5{f in F,c in C[f], r in NR, w in TW[r], t in TS[r,w], l in P[r]}:
#	sum{i in G[f,c]}K[f,c,l,i,r,w] = 1;   #һ��ĳ��·��������ͨ������·������������·����ֻ��һ��������ᱻ���ڽ�����ͨ����


subject to Cons3_3{f in F,c in C[f], i in G[f,c], r in NR, w in TW[r], t in TS[r,w],  l in P[r]}:
	O[f,c,l,i,r,t]-1 <= M*(1-K[f,c,l,i,r,w]); #һ�gc��ƻ�ҵ����ĳ��ʱ϶���������ù�ͨ������
	

subject to Cons3_4{f in F,c in C[f], i in G[f,c],  r in NR, w in TW[r], t in TS[r,w], l in P[r]}:
	O[f,c,l,i,r,t]-1 >= -M*(1-K[f,c,l,i,r,w]); #һ���ƻ�ҵ����ĳ��ʱ϶���������ù�ͨ������
	


# (4)Ƶ϶ʹ��Լ����������ʽ��ͬ�ж���·��������ʱ϶t�ڵ�k��Ƶ϶�Ƿ�ʹ��

subject to Cons3_5{f in F,c in C[f], r in NR, l in P[r], i in G[f,c], t in 0..omiga}:
    NT[f,c,i,t] >= O[f,c,l,i,r,t]*E[f,c,i,r,l];
    

    
subject to Cons3_522{f in F,c in C[f], r in NR, i in G[f,c], t in 0..omiga}:
  	 OP >= NT[f,c,i,t];


subject to Cons3_55{f in F,c in C[f], r in NR, l in P[r], i in G[f,c], t in 0..omiga}:
    O[f,c,l,i,r,t]*E[f,c,i,r,l] <= RE[f,c,i,t];
    





	
	
     
     
	
	
	

	 