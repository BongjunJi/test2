import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class Mainfunction {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[][] weight1=new double[2][260];
		double[][] weight2=new double[6][2];
		double[] b1=new double[2];
		double[] b2=new double[6];
		double[][] target =new double[110000][26];
		double[][][] Input;
		double[][] NNInput=new double[10][26];
		boolean[] result=new boolean[6];
		boolean[] target_sel=new boolean[6];
		Random generator = new Random();
		int[] num = new int[300];
		//Date breaktime= new Date();
		//DateFormat sdFormat=new SimpleDateFormat("yyyyMMdd");
		//String tempdate;
		int i=0;
		int j=0;
		try{
			File csv2=new File("w1_1.csv");
					
					BufferedReader br2=new BufferedReader(new FileReader(csv2));
					String line2="";
					while((line2=br2.readLine())!=null){
						String[] token2 =line2.split(",",-1);
						
						for(String output:token2){
							
							weight1[j][i]=Double.parseDouble(output);
						i++;
						}
						i=0;
						j++;
					}
					br2.close();
				
					i=0;
					j=0;
				
					File csv3=new File("w2_1.csv");
				
					BufferedReader br3 = new BufferedReader(new FileReader(csv3));
					String line3="";
					while((line3=br3.readLine())!=null){
						String[] token3 = line3.split(",",-1);
						
						for(String output:token3){
							
							weight2[j][i]=Double.parseDouble(output);
						i++;
						}
						i=0;
						j++;
					}
					br3.close();
					
					i=0;
					j=0;
									
					
					File csv4=new File("b1.csv");
					
					BufferedReader br4=new BufferedReader(new FileReader(csv4));
					String line4="";
					while((line4=br4.readLine())!=null){
						String[] token4 =line4.split(",",-1);
						
						for(String output:token4){
							
							b1[i]=Double.parseDouble(output);
						i++;
						}
						

					}
					br4.close();
					
					i=0;
					File csv5=new File("b2.csv");
					
					BufferedReader br5=new BufferedReader(new FileReader(csv5));
					String line5="";
					while((line5=br5.readLine())!=null){
						String[] token5 =line5.split(",",-1);
						
						for(String output:token5){
							
							b2[i]=Double.parseDouble(output);
							i++;
						}
						
											}
					br5.close();
					
					int num_of_rand;
					int num_of_set;
					num_of_rand=Integer.parseInt(args[0]);
					num_of_set=Integer.parseInt(args[1]);
					
					Input = new double[num_of_set][num_of_rand*10][26];
					
					i=0;
					j=0;
					
			
				for (int w=0;w<num_of_set;w++){
					i=0;
					j=0;
					File csv=new File("testcase"+(w+1)+".csv");
					
					BufferedReader br=new BufferedReader(new FileReader(csv));
					String line="";
					while((line=br.readLine())!=null){
						String[] token =line.split(",",-1);
						
						for(String output:token){
							
							Input[w][j][i]=Double.parseDouble(output);
							i++;
							//System.out.println("input"+w+j+i);
							}
							i=0;
							j++;
							
						}
					
					br.close();
				}
				
		////////////////////////////////////////////////////
					
		for (int x=0;x<num_of_set;x++){
			FileWriter fw=new FileWriter("output"+(x+1)+".csv");
			for(int w=0;w<(num_of_rand);w++){
				for(int y=0;y<10;y++){
					for(int s=0;s<26;s++){
						NNInput[y][s]=Input[x][10*w+y][s];
				//////////////////////////////////////////////////////
	
							}	
						}
				PredictionModule A= new PredictionModule(NNInput, 10, 26, weight1, 2,260,weight2,b1,2,b2,6);//객체 생성(Input데이터, Input데이터의 행 크기, Input데이터의 열 크기, weight1, weight1의 행 크기, weight1의 열크기,weight2, b1, b1의 크기,b2)
//System.out.println(A.GetBREAKDOWN_FLAG_CURRENT());
//fw.append(Boolean.toString(A.GetBREAKDOWN_FLAG_CURRENT()));//현재 설비 고장 진단<-

				result=A.GetBREAKDOWN_FLAG_FUTURE();// 설비 고장 예측
				//System.out.println(Arrays.toString(result));
				
				for(int z=0;z<6;z++){		
					fw.append(Boolean.toString(result[z]));
					if(z!=5){
						fw.append(',');
					}
					else{
						fw.append("\n");		
			}
			
		}
		
		}
			fw.close();
		}
			
				
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();}
}
}
