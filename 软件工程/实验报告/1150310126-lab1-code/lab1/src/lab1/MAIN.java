package lab1;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

/**
 * @since  2017/9/05
 * @author 阿杜
 * @注：单词处理完以后必须按出现的循序放入对应的容器中;
 * lab7
 */
public class MAIN 
{
	
	 /**
	 * @变量名：string[] word_orginal;
	 * @作用：存放读取文本中的单词
	 * 详解：
	 *	      读取文本后，只留下文本中的英文单词并全部转换小写，在将其按出现的循序拆分成单词，依次放入数组words_original中，
	 *	      但是并不对重复出现的单词进行处理，即单词"a"出现了三次，那么就按照出现的循序存储三次，所以words_original的长度
	 *	  等于文本单词的综合
	 */	
	public String[] words_original;	//初始的所有单词数组,按读进来的数据中的次序依次将文章拆分成单词
	
	public Vector<String> edge=new Vector<>();//顶点数组
	
	/**
	 * @变量名： int vertex;
	 * @作用： 顶点个数
	 * 详解：
	 * 	  其长度等于文本中单词的种类个数；
	 *   以下容器：edge_weight，edge_edge，vertex_number，number_vertex，edge_matrix的长度全部等于vextex
	 */
	public int vertex;
	
	
	/**
	 * @变量名：HashMap edge_weight
	 * @作用：    边和权重键值对
	 * 详解：
	 * 	  如果单词A到B有路径，那么将 key= "A -> B",value=权值（即"A -> B"出现的次数）;
	 *   可以按照某个边"A - >B"取出其权值
	 */
	public Map<String, Integer> edge_weight=new HashMap<String,Integer>();
	
	
		//将所有的边对按顺序保存下来；如：边A,B变成 "A -> B"放入edge_edge中(可以按循序读出来)
	/**
	 * @变量名：Vector edge_edge
	 * @作用：    存放边
	 * 详解：      
	 * 	  将所有边按照在文本中出现的次序放入edge_edge中，若某条边 "A -> B"先后出现两次，那么起出现位置以第一次出现的为准
	 */
	public Vector<String> edge_edge=new Vector<String>();
	

	/**
	 * @变量名：Map vertex_number
	 * @作用： 根据顶点名可取出起对应的数子，这个数字每个顶点是唯一的
	 * 详解：
	 * 	  对文本中的每一个顶点根据起出现的次序对应一个数字值，因为邻接矩阵中用数字来表示边；这个数字是按照顶点在文本中出现的次序从零递增的；
	 *   比如顶点A和B有路径，顶点A对应唯一一个数字i，顶点B对应唯一一个数字j,那么邻接矩阵edge_matrix[i][j]等于"A -> B"权值;
	 *   number_vertix可按照数字读取对应单词；vertex_number可按照单词读出对应的数字,两者可以对照使用，是的临界矩阵和顶点关系更灵活
	 * 
	 */
	public Map<String, Integer> vertex_number=new HashMap<String,Integer>();
	
	
	/**
	 * @变量名：Map number_vertex
	 * @作用： 根据某个数字值，可去取出其对应的顶点名称（与vertex_number正好相反）
	 * 详解：
	 * 	   在vertex_number中已经给每个顶点对照一个数字，即可用顶点名称取出对应的数字，那么number_vextex可以根据顶点编号取出顶点名称
	 *   比如edge_matrix[i][j]表示第i个顶点到第j个顶点的路径，那么此时只要找出数字i,j对应的顶点名称即可知道那两条边存在路径
	 */
	public Map<Integer,String> number_vertex=new HashMap<Integer,String>();
	
	/**
	 * @int[][] edge_matrix;
	 * @作用： 有向图邻接矩阵
	 * @初始值：10000
	 * 详解：
	 * 	      因为在vertex_number和number_vertex中已经将每个顶点与唯一的编号对应，那么可用邻接矩阵表示所有编号之间的边关系；
	 *    （因为编号和顶点名称相互对应，所有可知道每个顶点之间的路径关系）
	 *    
	 */
	public int [][] edge_matrix=null;
	
	//初始化邻接矩阵的值，如果权值为10000，则没有路径
	int max_weight=100000;
	
	
	//一个点到所有点的路径
	public GraphViz gv_1 = new GraphViz();
	
	
	/*************************************************************************
	 * 展示有向图
	 *************************************************************************/
	//public void showDirectedGraph(Vector<String> edge_vec
	public void showDirectedGraph()
	{
		GraphViz gv = new GraphViz();
	     gv.addln(gv.start_graph());
	     
	     /**
	      * 按循序遍历边容器，依次取出每条边和此边的权重，权重作为路径的label;
	      */
		for(int i=0;i<this.edge_edge.size();i++)
		{

			String add_edge=this.edge_edge.elementAt(i);//一对边
			String strweight=this.edge_weight.get(add_edge).toString();//边的权值
			String style=add_edge+"[ label="+strweight+"]"+";";
			gv.add(style);
		}
		
		gv.addln(gv.end_graph()); //将所有边传给dot文件
	    String type = "jpg";
	    File out = new File("out." + type);   
	    
	    /**
	     * gv.getDotSource()--将图片转换成字符串格式
	     * gv.getGraph( gv.getDotSource(), type ) --将字符串转换成字节数组
	     * writeGraphToFile --将图片输出到文件out中
	     */
	    gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
	    
	    try {
			Desktop.getDesktop().open(new File("OUT.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/*************************************************************************
	 * 查询桥词
	 * @param word1
	 * @param word2
	 * @return 如果有一个或多个bridgewords则返回bridgewords;
	 * 		      如果某个单词不在图中则返回："No "+word1+" or "+word2+ " in the graph!";
	 * 		    如果两个单词之间有路径，或者没有bridgeword返回："N0 bridge words from "+word1+" to "+word2+"!"
	 **************************************************************************/
	public String queryBridgeWords(String word1, String word2)
	{
		
		String bridgewords="";	//返回的字符串，记录bridgewords
		
		String temp=word1+" -> "+word2; //连个单词组成的边名称
		
		//如果顶点word1到word2有相邻路径则两个单词之间没有bridgeword
		if(this.edge_edge.contains(temp)==true)
		{
			return ("N0 bridge words from "+word1+" to "+word2+"!");
		}
		//如果两个单词不相邻，查看这两个单词在不在顶点集中
		else if(this.vertex_number.containsKey(word1) && this.vertex_number.containsKey(word2))
		{
			int wordnum1=this.vertex_number.get(word1);
			int wordnum2=this.vertex_number.get(word2);
		
			for(int i=0;i<this.vertex;i++)
			{
				if(this.edge_matrix[wordnum1][i]!=this.max_weight)
				{
					if(this.edge_matrix[i][wordnum2]!=this.max_weight)
					{
						bridgewords+=this.number_vertex.get(i)+", ";			
					}
				}
			}
			//如果一个bridgeword都没有
			if(bridgewords=="")
			{
				return ("No bridge words from "+word1+" to "+word2+"!");
			}
			//如果找到了
			bridgewords="The bridge words from "+word1+" to "+word2+" are: "+bridgewords;
		}

		//word1或者word2不在顶点集中
		else if(this.vertex_number.containsKey(word1)==false || this.vertex_number.containsKey(word2)==false)
		{
			bridgewords=bridgewords+"No "+word1+" or "+word2+ " in the graph!";
			return bridgewords;
		}
		//若单词在文本中，但是没有bridge word或者两个单词之间有路径
		else {
			return ("No bridge words from "+word1+" to "+word2+"!");
		}
		return bridgewords;
	}
	
	
	
	/**************************************************************************
	 * 根据桥词形成新的文本
	 * @param inputText
	 * @return
	 ************************************************************************/
	public String generateNewText(String inputText)
	{
		//将inputText按照空格拆分拆分成单词，依次放入字符串数组中,inputText本身并不变
		String strtemp=inputText.replaceAll("[^a-zA-Z]+", " ").toLowerCase();
		String[] inputWords=strtemp.split("[\\s]");
	    String result="";
	    
		for(int i=0;i<inputWords.length-1;i++)
		{
			//vector.add(inputWords[i]);
			result=result+inputWords[i]+" ";//加入输入文本的第一个单词
			Vector<String> vectemp=new Vector<>();//存放所有桥词，后面随机产生下表选取其中一个
			

			//如果两个单词都在原来的文本中
			if(this.vertex_number.containsKey(inputWords[i]) && this.vertex_number.containsKey(inputWords[i+1]))
			{
				int wordnum1=this.vertex_number.get(inputWords[i]);
				int wordnum2=this.vertex_number.get(inputWords[i+1]);
				for(int j=0;j<this.vertex;j++)
				{
					if(this.edge_matrix[wordnum1][j]!=this.max_weight)
					{
						if(this.edge_matrix[j][wordnum2]!=this.max_weight)
						{
							if(this.edge_matrix[wordnum1][wordnum2]==this.max_weight)//两个单词之间不能有路径
							{
								vectemp.add(this.number_vertex.get(j));
							}
						}
					}
				}
			}
			
			//如果vectemp不空，即里面有bridge word,从其中随机选取一个
			if(vectemp.isEmpty()==false)
			{
				Random r=new Random();
				int index=r.nextInt(vectemp.size());//产生vectemp长度之内的随机数
				result=result+vectemp.get(index)+" ";
				vectemp.clear();
			}
		}
		result=result+inputWords[inputWords.length-1];//加上输入文本最后一个单词
		return result;
	}
	
	
	
	
	
	/************************************************************************
	 * @param word1
	 * @param word2
	 * @return 两个点之间的最短路径
	 ***********************************************************************/
	public String calcShortestPath(String word1, String word2)
	{
		Vector<String> min_pass_edge=new Vector<>();//记录两点间的路径（包括这俩个点）
		String returnstring="";//记录亮点间的路径（包括亮点）
		//记录两个顶点之间的最短路径所经过的边和对应的权值
		Map<String, Integer> min_edge_weigth=new HashMap<String, Integer>();//亮点最短路径经过的边和权值
		
		//先判断这两个单词是否在顶点集中
		if(this.vertex_number.containsKey(word1)==false || this.vertex_number.containsKey(word2)==false)
		{
			return  "No "+word1+" or "+word2+ "in the graph!";
		}
		
		//String resultstring="";//返回值
		int[][] D=new int[this.vertex][this.vertex];
		int[][] P=new int[this.vertex][this.vertex];//表示编号为i,j边之间的最短路径
		for(int i=0;i<this.vertex;i++)
		{
			for(int j=0;j<this.vertex;j++)
			{
				D[i][j]=this.edge_matrix[i][j];
				P[i][j]=-1;
			}
		}

		for(int k=0;k<this.vertex;k++)
		{
			for(int i=0;i<this.vertex;i++)
			{
				for(int j=0;j<this.vertex;j++)
				{
					if(D[i][k]+D[k][j] <D[i][j])
					{
						D[i][j]=D[i][k]+D[k][j];
						P[i][j]=k;
					}
				}
			}
		}
		 
		int i=this.vertex_number.get(word1);
		int j=this.vertex_number.get(word2);
		 min_pass_edge.add(word1);
		if(D[i][j]!=this.max_weight &&i!=j)
        { 
            print_minpass(P, i, j,min_pass_edge);
        }
		min_pass_edge.add(word2);
		
		
		//如果两个单词不可达
		if(min_pass_edge.size()==2 && min_pass_edge.get(0)==word1&&min_pass_edge.get(1)==word2&&this.edge_edge.contains(word1+" -> "+word2)==false)
		{
			return "No pass from "+word1+" to "+word2+ " !";
		}
		
		
		//将路径上的顶点加入returnresult中
		for(int k=0;k<min_pass_edge.size()-1;k++)
		{
			returnstring=returnstring+min_pass_edge.get(k)+" -> ";
		}
		returnstring=returnstring+min_pass_edge.get(min_pass_edge.size()-1)+" -> ";//加入最后一个单词
		 
		//展示最短路径图
		 
		 //将亮点间的最短路径经过的边和权值加入min_edge_weight中,
		 for(int k=0;k<min_pass_edge.size()-1;k++)
		 {
			 String frist=min_pass_edge.get(k);
			 String next=min_pass_edge.get(k+1);
			 String tempstring=frist+" -> "+next;//边
			 int fristnum=this.vertex_number.get(frist);	//两个顶点的编号
			 int nextnumm=this.vertex_number.get(next);
			 int tempweigt=this.edge_matrix[fristnum][nextnumm];//权值
			 min_edge_weigth.put(tempstring, tempweigt);
		 }
		 
		 //	绘制边的时候，如果边在min_edge_weight中用凸显的颜色标识否则用默认颜色
		GraphViz gv = new GraphViz();
	    gv.addln(gv.start_graph());	
	    for(int k=0;k<this.edge_edge.size();k++)
		{			
			String add_edge=this.edge_edge.elementAt(k);//一对边
			String strweight=this.edge_weight.get(add_edge).toString();//边的权值
			String style=null;	
				
			//如果边add_edge在min_edge_weight中,则颜色为红色
			if(min_edge_weigth.containsKey(add_edge))
			{
				 style=add_edge+"[ color=red,label="+strweight+"]"+";";
			}
		
			//否则为默认
			else {
				style=add_edge+"[ label="+strweight+"]"+";";
			}				
		
			gv.add(style);
		}
			
		gv.addln(gv.end_graph());
        //System.out.println(gv.getDotSource());  
		String type = "gif";
	    File out = new File("minpass." + type);    
	    gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );		 
	    try {
			Desktop.getDesktop().open(new File("minpass.GIF"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnstring;
	}
	
	
	
	
	
	
	
	/**随机游走*******************************************************************************
	 *@author 阿杜
	 *@流程：首先程序随机生成一个顶点个数范围之内的数字，以此数字为起点。如新第一个起点是A则选出与A相邻的所有顶点放入一个vectemp中，
	 *		再从其中随机选出一个顶点B，并形成字符串"A - >B"放入一个vecresult中，如果vecresult中已经有此边，则结束。或者
	 *		选到一个新的顶点不存在出边则结束，将前面的所有出现的顶点降入resultstring中并返回！
	 *		每次选出下一条边，则此成为strat_number，重复上面的操作
	 * 
	 *******************************************************************************************/
	public String randomWalk()
	{
		//随机游走的路径
		String strresult="";
		
		//记录出现过的边，如果重复出现则结束
		Vector<String> vecresult=new Vector<>();
		
		//产生顶点个数vertex范围之内的一个数字,将该数字对应的顶点作为起始位置
		Random r=new Random();
		
		//第一个顶点对应的编号
		int frist_number=r.nextInt(vertex);
		
		//String strat_edge=this.number_vertex.get(frist_number);//其实顶点
		//将第一个边加入strresult中
		strresult=strresult+this.number_vertex.get(frist_number)+" ";
		
		Scanner in=new Scanner(System.in);
		System.out.print("是否继续(Y/N): ");
		String choose1=in.next();
		if(choose1.equals("N") ||choose1.equals("n"))
		{
			in.close();
			return strresult;
		}
		if(choose1.equals("Y") ||choose1.equals("y"))
		{
			System.out.println(strresult);
		}
		
		while(true)
		{		
				
			Vector<String> vectemp=new Vector<>();
		    for(int j=0;j<vertex;j++)
		    {
		    	//将于frist_number有路径的顶点名称放入vectemp中
		    	if(edge_matrix[frist_number][j]!=this.max_weight)
		    	{	
		    		vectemp.add(this.number_vertex.get(j));
		    	}
		    }
		    
		    //如果vectemp空，即frist_number顶点没有出路，则结束
		    if(vectemp.isEmpty())
		    {
		    	break;
		    }
		    //vectemp里面是遇上一个边frist_number有路径的所有顶点
		    if(vectemp.isEmpty()==false)//如frist_number对应的边有下一条边
		    {
		    	//从vectemp中随选出一个顶点
		    	Random r1=new Random();
				int next_number=r1.nextInt(vectemp.size());
				//如果边 "strat_number -> next "没出现过则继续，否则结束并返回结果,从vectemp中取出随即边vectemp.get(next_number)
				String temp=this.number_vertex.get(frist_number)+" -> "+vectemp.get(next_number);
				
				//如果改变是第一条边，则结束
				//if(vecresult.contains(temp)==false)
				
				if(vecresult.isEmpty()==true)
				{
					vecresult.add(temp);
					strresult=strresult+vectemp.get(next_number)+" ";
					//next_number对应的边成为新的起始点frist_number，
					frist_number=this.vertex_number.get(vectemp.get(next_number));
					
					 System.out.print("是否继续(Y/N): ");
					 String choose=in.next();
					if(choose.equals("Y")||choose.equals("y"))
					{
						System.out.println(strresult);
						continue;
					}
					else {
						break;
					}
					
				}
				
				//若该边temp在vecresutl出现过则结束
			
				 if(vecresult.get(0).equals(temp)==false)
				{
					//没有出现过则将，这对边temp放入vecresult中
					vecresult.add(temp);
					strresult=strresult+vectemp.get(next_number)+" ";
					//next_number对应的边成为新的起始点frist_number，
					frist_number=this.vertex_number.get(vectemp.get(next_number));
				}	
				 else 
				{
					strresult=strresult+vectemp.get(next_number)+" ";
					break;
				}
		    }
		    
		    System.out.print("是否继续(Y/N): ");
			String choose=in.next();
			if(choose.equals("Y")||choose.equals("y"))
			{
				System.out.println(strresult);
				continue;
			}
			else {
				break;
			}
		}
		in.close();
		return  strresult;
	}
	
	
	
	

	
		
		/*************************************************************************
		 * @author 阿杜
		 * @param args
		 **************************************************************************/
	public static void main(String[] args) 
	{
			MAIN obj1=new MAIN();
			String str=new String();
			Scanner cin=new Scanner(System.in);
			

			while(true)
			{
				System.out.println("1- 从文件读取数据! 2-手动写入数据!");
				System.out.print("请输入选择:");
				String choose=cin.nextLine();
				if(choose.equals("1"))
				{
					/**
					 * 从文件读入数据，并对其进行处理，最后将所有单词按文本中的循序拆分复制给obj1.words_original
					 */
					str="";
					System.out.print("请输入文件路径:");
					
					String StrFilename=cin.nextLine();
					// StrFilename="input.txt";
					try {
						File file1=new File(StrFilename);
						FileReader fr=new FileReader(file1);
						BufferedReader br=new BufferedReader(fr);
						
						String temp=null;
						int i=1;
						while((temp=br.readLine()) != null)
						{
							if(i==1)
							{
								str=str+temp;//第一行前面不加空格
							}
							else {
								str=str+" "+temp;//每读一行加一个空格
							}
							i++;
						}
						br.close();
					}catch (Exception e) {
						e.printStackTrace();
					}
					
					break;
				}
				if(choose.equals("2"))
				{
					System.out.print("请输入文本:");
					str=cin.nextLine();
					break;
				}
			}
			
			
			String str1=str.replaceAll("[^a-zA-Z]+", " ");
			String str2=str1.toLowerCase();
			obj1.words_original=str2.split("[\\s]");
			
		
			
			/**	
			 * 初始化edge_edge  ;  edge_weight
			* 生成图   ,将每条边转换成字符串对加入map edge_weight 中；其中key是边，value是权值（边出现的次数）,计算每条边出现的个数
			* 同时 将每一条"A -> B"边按出现的次数存放 vector edge_edge中(是为了将边按循序读出来)
			*/
			for(int i=0;i<obj1.words_original.length-1;i++)
			{
				String temp=obj1.words_original[i]+" -> "+obj1.words_original[i+1];
				
				//第一次加入时权值为1
				if(obj1.edge_weight.containsKey(temp)==false)
				{
					obj1.edge_edge.add(temp);//将新的一对边放进vector edge_dege后面(若重复出现则按照第一次插入的为准
					obj1.edge_weight.put(temp, 1);
				}
				
				//如果已经有了边和权值，则权值加一
				else {
					int nutemp=obj1.edge_weight.get(temp);
					nutemp+=1;
					obj1.edge_weight.put(temp, nutemp);
				}
			}
			
			
			/**	
			 * 初始化 vertex_number   ; number_vertex
			 * 从头到尾遍历原来的单词表，对每一个单词按照文本中出现的次数给予0....的值，若有重复出现的单词，则以第一次给予的值为准
			 * 给每个定点赋给一个值，来表示每个定点在邻接矩阵的表示的数字
			 */
			int number=-1;//每条边对应的数字，从零开始
			for(int i=0;i<obj1.words_original.length;i++)
			{
				//vertex_number，number_vertex每个key和value相反
				if( obj1.vertex_number.containsKey(obj1.words_original[i])==false)
				{
					number+=1;
					obj1.vertex_number.put(obj1.words_original[i], number);
					obj1.number_vertex.put(number,obj1.words_original[i]);
					obj1.edge.add(obj1.words_original[i]);
				}
			}
			obj1.vertex=obj1.number_vertex.size();
	
			//邻接矩阵初始化，全部为max_weight
			obj1.edge_matrix=new int[obj1.vertex][obj1.vertex];
			for(int i=0;i<obj1.vertex;i++)
			{
				for(int j=0;j<obj1.vertex;j++)
				{
					obj1.edge_matrix[i][j]=obj1.max_weight;
				}
			}
			
			// 创建有向图,创建邻接矩阵；
			for(int i=0;i<obj1.vertex;i++)
			{
				for(int j=0;j<obj1.vertex;j++)
				{
					String edgei=obj1.number_vertex.get(i);
					String edgej=obj1.number_vertex.get(j);
					//如果这两个边之间有路径
					if(obj1.edge_weight.containsKey(edgei+" -> "+edgej))
					{
						int weight=obj1.edge_weight.get(edgei+" -> "+edgej);
						obj1.edge_matrix[i][j]=weight;
					}
				}
			}
			
				
	
			//展示有向图
			obj1.showDirectedGraph();
	
			
			// 查询桥词
			System.out.println("******************* 查询两个单词之间的桥词******************* :");
			System.out.print("please input word 1 :");
			String word1=cin.nextLine();
			System.out.print("please input word 2 :");
			String word2=cin.nextLine();	
			String BridgeWords=obj1.queryBridgeWords(word1, word2);
			System.out.println(BridgeWords);
			
	
			//根据桥词形成新新文本
			System.out.println(" \n******************* 根据桥词形成新新文本*******************  :");
			System.out.print("请输入文本 :");
			String NewTex=cin.nextLine();
			System.out.println(obj1.generateNewText(NewTex));
			
		
	  		//求最短路径
			System.out.println(" \n ******************* 两个单词之间的最短路径:******************* ");
			System.out.print("please input word 1 :");
			 word1=cin.nextLine();
			System.out.print("please input word 2 :");
			 word2=cin.nextLine();
	  		 String minpass=obj1.calcShortestPath(word1, word2);
			 System.out.println("两点间的最短路径是： "+minpass);
			 
			 //一个点到所有点的最短路径
			 System.out.println(" \n ******************* 一个单词到所有单词之间的最短路径:******************* ");
			 obj1.gv_1.addln(obj1.gv_1.start_graph());	
			 System.out.print("请输入一个单词:");
			 String word3=cin.nextLine();
			 obj1.calcShortestPath(word3);
			
			//随机游走
			 System.out.println(" \n *******************随机游走*******************");
	        String ResutlRanWalk=obj1.randomWalk();
	        System.out.print(ResutlRanWalk);
    		cin.close();	
 
			try {
				File file1=new File("outrandwalk.txt");
				FileWriter fr=new FileWriter(file1);
				fr.write(ResutlRanWalk);
				fr.close();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		    try {
				Desktop.getDesktop().open(new File("outrandwalk.txt"));
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
		
	
	/**
	 * showDirectedGraph();的辅助函数，记录路径
	 */
	public void print_minpass(int p[][],int i,int j,Vector<String> min_pass_edge)
	{
		int k=p[i][j];
		if(k != -1)
		{
			print_minpass(p, i, k,min_pass_edge);
			min_pass_edge.addElement(this.number_vertex.get(k));
			print_minpass(p, k, j,min_pass_edge);	
		}
	}
	
	
	 public void calcShortestPath(String word1)
	 {
		 	String strcolor="";
			try {
				File file1=new File("color.txt");
				FileReader fr=new FileReader(file1);
				BufferedReader br=new BufferedReader(fr);
				
				String temp=null;
				int i=1;
				while((temp=br.readLine()) != null)
				{
					if(i==1)
					{
						strcolor=strcolor+temp;//第一行前面不加空格
					}
					else {
						strcolor=strcolor+" "+temp;//每读一行加一个空格
					}
					i++;
				}
				br.close();
			}catch (Exception e) {
				e.printStackTrace();}
			String str1=strcolor.replaceAll("[^a-zA-Z]+", " ");
			String str2=str1.toLowerCase();
			String[] colors=str2.split("[\\s]");
			
	
			String c;
			 for(int i=0;i<this.edge.size();i++)
			 {
				 if(colors.length-1==i)
				 {
					  c=colors[0];
				 }
				else {
					 c=colors[i];
				}
				 if(this.edge.get(i).equals(word1) ==false&&this.edge.contains(word1))
				 {
					 this.calcShortestPath(word1, this.edge.get(i),c);
				 }
			 }
			 
			    for(int k=0;k<this.edge_edge.size();k++)
				{			
					String add_edge=this.edge_edge.elementAt(k);//一对边
					String strweight=this.edge_weight.get(add_edge).toString();//边的权值
					this.gv_1.add(add_edge+"[ label="+strweight+"]"+";");
				}
			    
			    this.gv_1.addln(this.gv_1.end_graph());
		        //System.out.println(gv.getDotSource());  
				String type = "gif";
			    File out = new File("minpass_1." + type);    
			    this.gv_1.writeGraphToFile( this.gv_1.getGraph( this.gv_1.getDotSource(), type ), out );
			    
			    try {
					Desktop.getDesktop().open(new File("minpass_1." + type));
				} catch (IOException e) {
					e.printStackTrace();
				}
	 }
	 
	 
	 
		/************************************************************************
		 * @param word1
		 * @param word2
		 * @return 两个点之间的最短路径
		 ***********************************************************************/
		public void calcShortestPath(String word1, String word2,String cl)
		{
			Vector<String> min_pass_edge=new Vector<>();//记录两点间的路径（包括这俩个点）			
			//String resultstring="";//返回值
			int[][] D=new int[this.vertex][this.vertex];
			int[][] P=new int[this.vertex][this.vertex];//表示编号为i,j边之间的最短路径
			for(int i=0;i<this.vertex;i++)
			{
				for(int j=0;j<this.vertex;j++)
				{
					D[i][j]=this.edge_matrix[i][j];
					P[i][j]=-1;
				}
			}

			for(int k=0;k<this.vertex;k++)
			{
				for(int i=0;i<this.vertex;i++)
				{
					for(int j=0;j<this.vertex;j++)
					{
						if(D[i][k]+D[k][j] <D[i][j])
						{
							D[i][j]=D[i][k]+D[k][j];
							P[i][j]=k;
						}
					}
				}
			}
			 
			int i=this.vertex_number.get(word1);
			int j=this.vertex_number.get(word2);
			 min_pass_edge.add(word1);
			if(D[i][j]!=this.max_weight &&i!=j)
	        { 
	            print_minpass(P, i, j,min_pass_edge);
	        }
			min_pass_edge.add(word2);
			 
			 //将亮点间的最短路径经过的边和权值加入min_edge_weight中,
			 for(int k=0;k<min_pass_edge.size()-1;k++)
			 {
				 String frist=min_pass_edge.get(k);
				 String next=min_pass_edge.get(k+1);
				 String tempstring=frist+" -> "+next;//边
	
				 String style=tempstring+"[ color="+cl+"]"+";";
				 this.gv_1.add(style); 
			 }
		}
}



