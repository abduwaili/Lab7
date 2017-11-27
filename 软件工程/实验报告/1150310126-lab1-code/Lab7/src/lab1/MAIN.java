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
 * @author ����
 * @ע�����ʴ������Ժ���밴���ֵ�ѭ������Ӧ��������;
 * lab7
 */
public class MAIN 
{
	
	 /**
	 * @��������string[] word_orginal;
	 * @���ã���Ŷ�ȡ�ı��еĵ���
	 * ��⣺
	 *	      ��ȡ�ı���ֻ�����ı��е�Ӣ�ĵ��ʲ�ȫ��ת��Сд���ڽ��䰴���ֵ�ѭ���ֳɵ��ʣ����η�������words_original�У�
	 *	      ���ǲ������ظ����ֵĵ��ʽ��д���������"a"���������Σ���ô�Ͱ��ճ��ֵ�ѭ��洢���Σ�����words_original�ĳ���
	 *	  �����ı����ʵ��ۺ�
	 */	
	public String[] words_original;	//��ʼ�����е�������,���������������еĴ������ν����²�ֳɵ���
	
	public Vector<String> edge=new Vector<>();//��������
	
	/**
	 * @�������� int vertex;
	 * @���ã� �������
	 * ��⣺
	 * 	  �䳤�ȵ����ı��е��ʵ����������
	 *   ����������edge_weight��edge_edge��vertex_number��number_vertex��edge_matrix�ĳ���ȫ������vextex
	 */
	public int vertex;
	
	
	/**
	 * @��������HashMap edge_weight
	 * @���ã�    �ߺ�Ȩ�ؼ�ֵ��
	 * ��⣺
	 * 	  �������A��B��·������ô�� key= "A -> B",value=Ȩֵ����"A -> B"���ֵĴ�����;
	 *   ���԰���ĳ����"A - >B"ȡ����Ȩֵ
	 */
	public Map<String, Integer> edge_weight=new HashMap<String,Integer>();
	
	
		//�����еı߶԰�˳�򱣴��������磺��A,B��� "A -> B"����edge_edge��(���԰�ѭ�������)
	/**
	 * @��������Vector edge_edge
	 * @���ã�    ��ű�
	 * ��⣺      
	 * 	  �����б߰������ı��г��ֵĴ������edge_edge�У���ĳ���� "A -> B"�Ⱥ�������Σ���ô�����λ���Ե�һ�γ��ֵ�Ϊ׼
	 */
	public Vector<String> edge_edge=new Vector<String>();
	

	/**
	 * @��������Map vertex_number
	 * @���ã� ���ݶ�������ȡ�����Ӧ�����ӣ��������ÿ��������Ψһ��
	 * ��⣺
	 * 	  ���ı��е�ÿһ�������������ֵĴ����Ӧһ������ֵ����Ϊ�ڽӾ���������������ʾ�ߣ���������ǰ��ն������ı��г��ֵĴ����������ģ�
	 *   ���綥��A��B��·��������A��ӦΨһһ������i������B��ӦΨһһ������j,��ô�ڽӾ���edge_matrix[i][j]����"A -> B"Ȩֵ;
	 *   number_vertix�ɰ������ֶ�ȡ��Ӧ���ʣ�vertex_number�ɰ��յ��ʶ�����Ӧ������,���߿��Զ���ʹ�ã��ǵ��ٽ����Ͷ����ϵ�����
	 * 
	 */
	public Map<String, Integer> vertex_number=new HashMap<String,Integer>();
	
	
	/**
	 * @��������Map number_vertex
	 * @���ã� ����ĳ������ֵ����ȥȡ�����Ӧ�Ķ������ƣ���vertex_number�����෴��
	 * ��⣺
	 * 	   ��vertex_number���Ѿ���ÿ���������һ�����֣������ö�������ȡ����Ӧ�����֣���ônumber_vextex���Ը��ݶ�����ȡ����������
	 *   ����edge_matrix[i][j]��ʾ��i�����㵽��j�������·������ô��ʱֻҪ�ҳ�����i,j��Ӧ�Ķ������Ƽ���֪���������ߴ���·��
	 */
	public Map<Integer,String> number_vertex=new HashMap<Integer,String>();
	
	/**
	 * @int[][] edge_matrix;
	 * @���ã� ����ͼ�ڽӾ���
	 * @��ʼֵ��10000
	 * ��⣺
	 * 	      ��Ϊ��vertex_number��number_vertex���Ѿ���ÿ��������Ψһ�ı�Ŷ�Ӧ����ô�����ڽӾ����ʾ���б��֮��ı߹�ϵ��
	 *    ����Ϊ��źͶ��������໥��Ӧ�����п�֪��ÿ������֮���·����ϵ��
	 *    
	 */
	public int [][] edge_matrix=null;
	
	//��ʼ���ڽӾ����ֵ�����ȨֵΪ10000����û��·��
	int max_weight=100000;
	
	
	//һ���㵽���е��·��
	public GraphViz gv_1 = new GraphViz();
	
	
	/*************************************************************************
	 * չʾ����ͼ
	 *************************************************************************/
	//public void showDirectedGraph(Vector<String> edge_vec
	public void showDirectedGraph()
	{
		GraphViz gv = new GraphViz();
	     gv.addln(gv.start_graph());
	     
	     /**
	      * ��ѭ�����������������ȡ��ÿ���ߺʹ˱ߵ�Ȩ�أ�Ȩ����Ϊ·����label;
	      */
		for(int i=0;i<this.edge_edge.size();i++)
		{

			String add_edge=this.edge_edge.elementAt(i);//һ�Ա�
			String strweight=this.edge_weight.get(add_edge).toString();//�ߵ�Ȩֵ
			String style=add_edge+"[ label="+strweight+"]"+";";
			gv.add(style);
		}
		
		gv.addln(gv.end_graph()); //�����бߴ���dot�ļ�
	    String type = "jpg";
	    File out = new File("out." + type);   
	    
	    /**
	     * gv.getDotSource()--��ͼƬת�����ַ�����ʽ
	     * gv.getGraph( gv.getDotSource(), type ) --���ַ���ת�����ֽ�����
	     * writeGraphToFile --��ͼƬ������ļ�out��
	     */
	    gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
	    
	    try {
			Desktop.getDesktop().open(new File("OUT.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/*************************************************************************
	 * ��ѯ�Ŵ�
	 * @param word1
	 * @param word2
	 * @return �����һ������bridgewords�򷵻�bridgewords;
	 * 		      ���ĳ�����ʲ���ͼ���򷵻أ�"No "+word1+" or "+word2+ " in the graph!";
	 * 		    �����������֮����·��������û��bridgeword���أ�"N0 bridge words from "+word1+" to "+word2+"!"
	 **************************************************************************/
	public String queryBridgeWords(String word1, String word2)
	{
		
		String bridgewords="";	//���ص��ַ�������¼bridgewords
		
		String temp=word1+" -> "+word2; //����������ɵı�����
		
		//�������word1��word2������·������������֮��û��bridgeword
		if(this.edge_edge.contains(temp)==true)
		{
			return ("N0 bridge words from "+word1+" to "+word2+"!");
		}
		//����������ʲ����ڣ��鿴�����������ڲ��ڶ��㼯��
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
			//���һ��bridgeword��û��
			if(bridgewords=="")
			{
				return ("No bridge words from "+word1+" to "+word2+"!");
			}
			//����ҵ���
			bridgewords="The bridge words from "+word1+" to "+word2+" are: "+bridgewords;
		}

		//word1����word2���ڶ��㼯��
		else if(this.vertex_number.containsKey(word1)==false || this.vertex_number.containsKey(word2)==false)
		{
			bridgewords=bridgewords+"No "+word1+" or "+word2+ " in the graph!";
			return bridgewords;
		}
		//���������ı��У�����û��bridge word������������֮����·��
		else {
			return ("No bridge words from "+word1+" to "+word2+"!");
		}
		return bridgewords;
	}
	
	
	
	/**************************************************************************
	 * �����Ŵ��γ��µ��ı�
	 * @param inputText
	 * @return
	 ************************************************************************/
	public String generateNewText(String inputText)
	{
		//��inputText���տո��ֲ�ֳɵ��ʣ����η����ַ���������,inputText��������
		String strtemp=inputText.replaceAll("[^a-zA-Z]+", " ").toLowerCase();
		String[] inputWords=strtemp.split("[\\s]");
	    String result="";
	    
		for(int i=0;i<inputWords.length-1;i++)
		{
			//vector.add(inputWords[i]);
			result=result+inputWords[i]+" ";//���������ı��ĵ�һ������
			Vector<String> vectemp=new Vector<>();//��������Ŵʣ�������������±�ѡȡ����һ��
			

			//����������ʶ���ԭ�����ı���
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
							if(this.edge_matrix[wordnum1][wordnum2]==this.max_weight)//��������֮�䲻����·��
							{
								vectemp.add(this.number_vertex.get(j));
							}
						}
					}
				}
			}
			
			//���vectemp���գ���������bridge word,���������ѡȡһ��
			if(vectemp.isEmpty()==false)
			{
				Random r=new Random();
				int index=r.nextInt(vectemp.size());//����vectemp����֮�ڵ������
				result=result+vectemp.get(index)+" ";
				vectemp.clear();
			}
		}
		result=result+inputWords[inputWords.length-1];//���������ı����һ������
		return result;
	}
	
	
	
	
	
	/************************************************************************
	 * @param word1
	 * @param word2
	 * @return ������֮������·��
	 ***********************************************************************/
	public String calcShortestPath(String word1, String word2)
	{
		Vector<String> min_pass_edge=new Vector<>();//��¼������·���������������㣩
		String returnstring="";//��¼������·�����������㣩
		//��¼��������֮������·���������ıߺͶ�Ӧ��Ȩֵ
		Map<String, Integer> min_edge_weigth=new HashMap<String, Integer>();//�������·�������ıߺ�Ȩֵ
		
		//���ж������������Ƿ��ڶ��㼯��
		if(this.vertex_number.containsKey(word1)==false || this.vertex_number.containsKey(word2)==false)
		{
			return  "No "+word1+" or "+word2+ "in the graph!";
		}
		
		//String resultstring="";//����ֵ
		int[][] D=new int[this.vertex][this.vertex];
		int[][] P=new int[this.vertex][this.vertex];//��ʾ���Ϊi,j��֮������·��
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
		
		
		//����������ʲ��ɴ�
		if(min_pass_edge.size()==2 && min_pass_edge.get(0)==word1&&min_pass_edge.get(1)==word2&&this.edge_edge.contains(word1+" -> "+word2)==false)
		{
			return "No pass from "+word1+" to "+word2+ " !";
		}
		
		
		//��·���ϵĶ������returnresult��
		for(int k=0;k<min_pass_edge.size()-1;k++)
		{
			returnstring=returnstring+min_pass_edge.get(k)+" -> ";
		}
		returnstring=returnstring+min_pass_edge.get(min_pass_edge.size()-1)+" -> ";//�������һ������
		 
		//չʾ���·��ͼ
		 
		 //�����������·�������ıߺ�Ȩֵ����min_edge_weight��,
		 for(int k=0;k<min_pass_edge.size()-1;k++)
		 {
			 String frist=min_pass_edge.get(k);
			 String next=min_pass_edge.get(k+1);
			 String tempstring=frist+" -> "+next;//��
			 int fristnum=this.vertex_number.get(frist);	//��������ı��
			 int nextnumm=this.vertex_number.get(next);
			 int tempweigt=this.edge_matrix[fristnum][nextnumm];//Ȩֵ
			 min_edge_weigth.put(tempstring, tempweigt);
		 }
		 
		 //	���Ʊߵ�ʱ���������min_edge_weight����͹�Ե���ɫ��ʶ������Ĭ����ɫ
		GraphViz gv = new GraphViz();
	    gv.addln(gv.start_graph());	
	    for(int k=0;k<this.edge_edge.size();k++)
		{			
			String add_edge=this.edge_edge.elementAt(k);//һ�Ա�
			String strweight=this.edge_weight.get(add_edge).toString();//�ߵ�Ȩֵ
			String style=null;	
				
			//�����add_edge��min_edge_weight��,����ɫΪ��ɫ
			if(min_edge_weigth.containsKey(add_edge))
			{
				 style=add_edge+"[ color=red,label="+strweight+"]"+";";
			}
		
			//����ΪĬ��
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
	
	
	
	
	
	
	
	/**�������*******************************************************************************
	 *@author ����
	 *@���̣����ȳ����������һ�����������Χ֮�ڵ����֣��Դ�����Ϊ��㡣���µ�һ�������A��ѡ����A���ڵ����ж������һ��vectemp�У�
	 *		�ٴ��������ѡ��һ������B�����γ��ַ���"A - >B"����һ��vecresult�У����vecresult���Ѿ��д˱ߣ������������
	 *		ѡ��һ���µĶ��㲻���ڳ������������ǰ������г��ֵĶ��㽵��resultstring�в����أ�
	 *		ÿ��ѡ����һ���ߣ���˳�Ϊstrat_number���ظ�����Ĳ���
	 * 
	 *******************************************************************************************/
	public String randomWalk()
	{
		//������ߵ�·��
		String strresult="";
		
		//��¼���ֹ��ıߣ�����ظ����������
		Vector<String> vecresult=new Vector<>();
		
		//�����������vertex��Χ֮�ڵ�һ������,�������ֶ�Ӧ�Ķ�����Ϊ��ʼλ��
		Random r=new Random();
		
		//��һ�������Ӧ�ı��
		int frist_number=r.nextInt(vertex);
		
		//String strat_edge=this.number_vertex.get(frist_number);//��ʵ����
		//����һ���߼���strresult��
		strresult=strresult+this.number_vertex.get(frist_number)+" ";
		
		Scanner in=new Scanner(System.in);
		System.out.print("�Ƿ����(Y/N): ");
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
		    	//����frist_number��·���Ķ������Ʒ���vectemp��
		    	if(edge_matrix[frist_number][j]!=this.max_weight)
		    	{	
		    		vectemp.add(this.number_vertex.get(j));
		    	}
		    }
		    
		    //���vectemp�գ���frist_number����û�г�·�������
		    if(vectemp.isEmpty())
		    {
		    	break;
		    }
		    //vectemp����������һ����frist_number��·�������ж���
		    if(vectemp.isEmpty()==false)//��frist_number��Ӧ�ı�����һ����
		    {
		    	//��vectemp����ѡ��һ������
		    	Random r1=new Random();
				int next_number=r1.nextInt(vectemp.size());
				//����� "strat_number -> next "û���ֹ��������������������ؽ��,��vectemp��ȡ���漴��vectemp.get(next_number)
				String temp=this.number_vertex.get(frist_number)+" -> "+vectemp.get(next_number);
				
				//����ı��ǵ�һ���ߣ������
				//if(vecresult.contains(temp)==false)
				
				if(vecresult.isEmpty()==true)
				{
					vecresult.add(temp);
					strresult=strresult+vectemp.get(next_number)+" ";
					//next_number��Ӧ�ı߳�Ϊ�µ���ʼ��frist_number��
					frist_number=this.vertex_number.get(vectemp.get(next_number));
					
					 System.out.print("�Ƿ����(Y/N): ");
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
				
				//���ñ�temp��vecresutl���ֹ������
			
				 if(vecresult.get(0).equals(temp)==false)
				{
					//û�г��ֹ��򽫣���Ա�temp����vecresult��
					vecresult.add(temp);
					strresult=strresult+vectemp.get(next_number)+" ";
					//next_number��Ӧ�ı߳�Ϊ�µ���ʼ��frist_number��
					frist_number=this.vertex_number.get(vectemp.get(next_number));
				}	
				 else 
				{
					strresult=strresult+vectemp.get(next_number)+" ";
					break;
				}
		    }
		    
		    System.out.print("�Ƿ����(Y/N): ");
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
		 * @author ����
		 * @param args
		 **************************************************************************/
	public static void main(String[] args) 
	{
			MAIN obj1=new MAIN();
			String str=new String();
			Scanner cin=new Scanner(System.in);
			

			while(true)
			{
				System.out.println("1- ���ļ���ȡ����! 2-�ֶ�д������!");
				System.out.print("������ѡ��:");
				String choose=cin.nextLine();
				if(choose.equals("1"))
				{
					/**
					 * ���ļ��������ݣ���������д���������е��ʰ��ı��е�ѭ���ָ��Ƹ�obj1.words_original
					 */
					str="";
					System.out.print("�������ļ�·��:");
					
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
								str=str+temp;//��һ��ǰ�治�ӿո�
							}
							else {
								str=str+" "+temp;//ÿ��һ�м�һ���ո�
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
					System.out.print("�������ı�:");
					str=cin.nextLine();
					break;
				}
			}
			
			
			String str1=str.replaceAll("[^a-zA-Z]+", " ");
			String str2=str1.toLowerCase();
			obj1.words_original=str2.split("[\\s]");
			
		
			
			/**	
			 * ��ʼ��edge_edge  ;  edge_weight
			* ����ͼ   ,��ÿ����ת�����ַ����Լ���map edge_weight �У�����key�Ǳߣ�value��Ȩֵ���߳��ֵĴ�����,����ÿ���߳��ֵĸ���
			* ͬʱ ��ÿһ��"A -> B"�߰����ֵĴ������ vector edge_edge��(��Ϊ�˽��߰�ѭ�������)
			*/
			for(int i=0;i<obj1.words_original.length-1;i++)
			{
				String temp=obj1.words_original[i]+" -> "+obj1.words_original[i+1];
				
				//��һ�μ���ʱȨֵΪ1
				if(obj1.edge_weight.containsKey(temp)==false)
				{
					obj1.edge_edge.add(temp);//���µ�һ�Ա߷Ž�vector edge_dege����(���ظ��������յ�һ�β����Ϊ׼
					obj1.edge_weight.put(temp, 1);
				}
				
				//����Ѿ����˱ߺ�Ȩֵ����Ȩֵ��һ
				else {
					int nutemp=obj1.edge_weight.get(temp);
					nutemp+=1;
					obj1.edge_weight.put(temp, nutemp);
				}
			}
			
			
			/**	
			 * ��ʼ�� vertex_number   ; number_vertex
			 * ��ͷ��β����ԭ���ĵ��ʱ���ÿһ�����ʰ����ı��г��ֵĴ�������0....��ֵ�������ظ����ֵĵ��ʣ����Ե�һ�θ����ֵΪ׼
			 * ��ÿ�����㸳��һ��ֵ������ʾÿ���������ڽӾ���ı�ʾ������
			 */
			int number=-1;//ÿ���߶�Ӧ�����֣����㿪ʼ
			for(int i=0;i<obj1.words_original.length;i++)
			{
				//vertex_number��number_vertexÿ��key��value�෴
				if( obj1.vertex_number.containsKey(obj1.words_original[i])==false)
				{
					number+=1;
					obj1.vertex_number.put(obj1.words_original[i], number);
					obj1.number_vertex.put(number,obj1.words_original[i]);
					obj1.edge.add(obj1.words_original[i]);
				}
			}
			obj1.vertex=obj1.number_vertex.size();
	
			//�ڽӾ����ʼ����ȫ��Ϊmax_weight
			obj1.edge_matrix=new int[obj1.vertex][obj1.vertex];
			for(int i=0;i<obj1.vertex;i++)
			{
				for(int j=0;j<obj1.vertex;j++)
				{
					obj1.edge_matrix[i][j]=obj1.max_weight;
				}
			}
			
			// ��������ͼ,�����ڽӾ���
			for(int i=0;i<obj1.vertex;i++)
			{
				for(int j=0;j<obj1.vertex;j++)
				{
					String edgei=obj1.number_vertex.get(i);
					String edgej=obj1.number_vertex.get(j);
					//�����������֮����·��
					if(obj1.edge_weight.containsKey(edgei+" -> "+edgej))
					{
						int weight=obj1.edge_weight.get(edgei+" -> "+edgej);
						obj1.edge_matrix[i][j]=weight;
					}
				}
			}
			
				
	
			//չʾ����ͼ
			obj1.showDirectedGraph();
	
			
			// ��ѯ�Ŵ�
			System.out.println("******************* ��ѯ��������֮����Ŵ�******************* :");
			System.out.print("please input word 1 :");
			String word1=cin.nextLine();
			System.out.print("please input word 2 :");
			String word2=cin.nextLine();	
			String BridgeWords=obj1.queryBridgeWords(word1, word2);
			System.out.println(BridgeWords);
			
	
			//�����Ŵ��γ������ı�
			System.out.println(" \n******************* �����Ŵ��γ������ı�*******************  :");
			System.out.print("�������ı� :");
			String NewTex=cin.nextLine();
			System.out.println(obj1.generateNewText(NewTex));
			
		
	  		//�����·��
			System.out.println(" \n ******************* ��������֮������·��:******************* ");
			System.out.print("please input word 1 :");
			 word1=cin.nextLine();
			System.out.print("please input word 2 :");
			 word2=cin.nextLine();
	  		 String minpass=obj1.calcShortestPath(word1, word2);
			 System.out.println("���������·���ǣ� "+minpass);
			 
			 //һ���㵽���е�����·��
			 System.out.println(" \n ******************* һ�����ʵ����е���֮������·��:******************* ");
			 obj1.gv_1.addln(obj1.gv_1.start_graph());	
			 System.out.print("������һ������:");
			 String word3=cin.nextLine();
			 obj1.calcShortestPath(word3);
			
			//�������
			 System.out.println(" \n *******************�������*******************");
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
	 * showDirectedGraph();�ĸ�����������¼·��
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
						strcolor=strcolor+temp;//��һ��ǰ�治�ӿո�
					}
					else {
						strcolor=strcolor+" "+temp;//ÿ��һ�м�һ���ո�
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
					String add_edge=this.edge_edge.elementAt(k);//һ�Ա�
					String strweight=this.edge_weight.get(add_edge).toString();//�ߵ�Ȩֵ
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
		 * @return ������֮������·��
		 ***********************************************************************/
		public void calcShortestPath(String word1, String word2,String cl)
		{
			Vector<String> min_pass_edge=new Vector<>();//��¼������·���������������㣩			
			//String resultstring="";//����ֵ
			int[][] D=new int[this.vertex][this.vertex];
			int[][] P=new int[this.vertex][this.vertex];//��ʾ���Ϊi,j��֮������·��
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
			 
			 //�����������·�������ıߺ�Ȩֵ����min_edge_weight��,
			 for(int k=0;k<min_pass_edge.size()-1;k++)
			 {
				 String frist=min_pass_edge.get(k);
				 String next=min_pass_edge.get(k+1);
				 String tempstring=frist+" -> "+next;//��
	
				 String style=tempstring+"[ color="+cl+"]"+";";
				 this.gv_1.add(style); 
			 }
		}
}



