import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Mainfunction {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean[][][] target;
		boolean[][][] result1;
		int score=0;
		double accuracy=0;
		int Misclassification=0;
		int i=0;
		int j=0;
		try{
					FileWriter fw=new FileWriter("Accuracy.csv");
					int num_of_set;
					int num_of_rand;
					num_of_rand=Integer.parseInt(args[0]);
					num_of_set=Integer.parseInt(args[1]);
					target = new boolean[num_of_set][num_of_rand][6];
					result1 = new boolean[num_of_set][num_of_rand][6];
					for(int w=0;w<num_of_set;w++){
						File csv1=new File("output"+(w+1)+".csv");
					
						BufferedReader br1=new BufferedReader(new FileReader(csv1));
						String line1="";
						while((line1=br1.readLine())!=null){
							String[] token1 =line1.split(",",-1);
						
							for(String output:token1){
							
								result1[w][j][i]=Boolean.parseBoolean(output);
								//System.out.println("result"+"["+w+"]["+j+"]["+i+"]="+result1[w][j][i]);
								i++;
								
							}
							i=0;
							j++;
						}
						br1.close();
						
						j=0;
						w++;
					}
					
					i=0;
					j=0;
				
					for(int w=0;w<num_of_set;w++){
						File csv2=new File("target_selected"+(w+1)+".csv");
				
						BufferedReader br2 = new BufferedReader(new FileReader(csv2));
						String line2="";
						while((line2=br2.readLine())!=null){
							String[] token2 = line2.split(",",-1);
						
							for(String output:token2){
							
								target[w][j][i]=Boolean.parseBoolean(output);
								//System.out.println("target"+"["+w+"]["+j+"]["+i+"]="+target[w][j][i]);
								i++;
							}
							i=0;
							j++;
						}
						br2.close();
						j=0;
						w++;
					}
		////////////////////////////////////////////////////
					for(int x=0;x<num_of_set;x++){
					for(int w=0;w<num_of_rand;w++)
					{
					
						boolean diffChecker = false;
						for(int s=0;s<6;s++)
						{
							if (target[x][w][s]!=result1[x][w][s]){
								diffChecker = true;
								break;
							}
						}
						if (!diffChecker){
							score++;
						}
					}
					accuracy=(100.0*score)/(num_of_rand);
					Misclassification=num_of_rand-score;
					double accuracy_round;
					accuracy_round=Double.parseDouble(String.format("%.2f", accuracy));
					//System.out.println(accuracy_round);
					//System.out.println(Misclassification);
		
					fw.append("Accuracy of testcase"+(x+1)+":");
					fw.append(',');
					fw.append(Double.toString(accuracy_round));
					fw.append(',');
					fw.append("Misclassification in testcase"+(x+1)+":");
					fw.append(',');
					fw.append(Integer.toString(Misclassification));
					fw.append("\n");			
					accuracy=0;
					Misclassification=0;
					score=0;
					}
					
					fw.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();}
}
}
