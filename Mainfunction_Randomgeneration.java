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
import java.util.Scanner;

public class Mainfunction {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double[][] Input=new double[110000][26];
		boolean[][] target=new boolean[110000][6];
		boolean[][] target_sel;
		Random generator = new Random();
		int[] num;
		double[][] testcase;
		String string1= "Testcase";
		String string2= ".csv";
		String fn_target_sel;
		String fn_testcase;
		
		//Date breaktime= new Date();
		//DateFormat sdFormat=new SimpleDateFormat("yyyyMMdd");
		//String tempdate;
		int i=0;
		int j=0;
		try{
				File csv6=new File("target.csv");
			
				BufferedReader br6 = new BufferedReader(new FileReader(csv6));
				String line6="";
				while((line6=br6.readLine())!=null){
					String[] token6 = line6.split(",",-1);
				
					for(String output:token6){
					
						target[j][i]=(Double.parseDouble(output)!=0);
						i++;
					}
					i=0;
					j++;
				}
				br6.close();

				i=0;
				j=0;
				
			i=0;

				File csv=new File("data.csv");
				
				BufferedReader br=new BufferedReader(new FileReader(csv));
				String line="";
				while((line=br.readLine())!=null){
					String[] token =line.split(",",-1);
					
					for(String output:token){
						
						Input[j][i]=Double.parseDouble(output);
						i++;
						}
						i=0;
						j++;
					}
				br.close();
				//Scanner scan = new Scanner(System.in);
				int num_of_rand;
				int num_of_set;
				num_of_rand=Integer.parseInt(args[0]);
				num_of_set=Integer.parseInt(args[1]);
				//System.out.println("한 파일 당 추출할 테스트 row의 갯수를 입력하세요. ");
				//num_of_rand=scan.nextInt();
				//System.out.println("몇개의 테스트셋을 생성할 지 개수를 입력하세요.");
				//num_of_set=scan.nextInt();
				num=new int[num_of_rand];
				target_sel=new boolean[num_of_rand][6];
				for(int r=0;r<num_of_set;r++){
					
					fn_target_sel="target_selected"+(r+1)+".csv";
					FileWriter fw2=new FileWriter(fn_target_sel);
					FileWriter fw=new FileWriter("testcase"+(r+1)+".csv");

					
		////////////////////////////////////////////////////
					for (int d=0; d<num_of_rand;d++)
					{
						num[d]=generator.nextInt(109753)+1;
					}
					
					testcase=new double[num_of_rand*10][26];
					
					for (int d=0;d<num_of_rand;d++){
						for(int w=0;w<10;w++){
							for(int s=0;s<26;s++){
								testcase[d*10+w][s]=Input[num[d]+w][s];
							}
						}
					}
	//////////////////////////////////////////////////////
					
					for(int w=0;w<num_of_rand;w++){
						for(int s=0;s<6;s++){
								target_sel[w][s]=target[(num[w]+11)][s];
								}
					}
		
		for(int x=0;x<num_of_rand;x++){
			for(int z=0;z<6;z++){
				fw2.append(Boolean.toString(target_sel[x][z]));
				//System.out.println(x);
				if(z!=5){
				fw2.append(',');
				}
				else{
				fw2.append("\n");
				}
			}
		}
		fw2.close();
		for(int x=0;x<num_of_rand*10;x++){
			for(int z=0;z<26;z++){
				fw.append(Double.toString(testcase[x][z]));
				if(z!=25){
				fw.append(',');
				}
				else{
				fw.append("\n");
				}
			}
		}
				fw.close();
				/*if(r==Integer.parseInt(args[1])-1){
					break;
				}*/
				}
				
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();}
}
}
