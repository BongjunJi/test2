import java.text.SimpleDateFormat;
import java.util.Date;

public class PredictionModule {

	double[][] InputVar;
	double[][] weight1;
	double[][] weight2;
	double[] b1;
	double[] b2;
	double[] prehiddenlayer;
	double[] hiddenlayer;
	double[] outputdata;
	
	int RowCount;
	int ColCount;
	int b1count;
	int ij=0;
	int ik=0;
	int ji=0;
	int jk=0;
	
	boolean[] BREAKDOWN_FLAG_CURRENT;
	boolean[] BREAKDOWN_FLAG_FUTURE;
	Date BREAKDOWN_START_TIME= new Date();
	
	double activatefunction(double n){
		return 2/(1+Math.exp(-2*n))-1;
	};
	
	/*boolean GetBREAKDOWN_FLAG_CURRENT(){
		for(int i=7;i<18;i++)
		{
			if(InputVar[RowCount-1][i]!=0)
			{
				System.out.println(InputVar[RowCount-1][i]);
				BREAKDOWN_FLAG_CURRENT =true;
				return true;
			}
		}
		BREAKDOWN_FLAG_CURRENT =false;
		return false;
	};*/
	/*
	Date GetBREAKDOWN_START_TIME(boolean BFF){
		
		if(BFF==true)
		{
		BREAKDOWN_START_TIME=InputVar[RowCount-1][0];
		return BREAKDOWN_START_TIME;
		}
		else
		{
			System.out.println("Normal State");
		}
	};
	*/
	
	void InputProcessingFunction(double[][] Input)
	{
		for(int i=0;i<RowCount;i++)
		{
			Input[i][0]=Input[i][0]*0.0689655172413793-1;
			Input[i][1]=Input[i][1]*0.027027027027027-1;
			Input[i][2]=Input[i][2]*2-1;
			Input[i][3]=Input[i][3]*2-1;
			Input[i][4]=Input[i][4]*2-1;
			Input[i][5]=Input[i][5]*2-1;
			Input[i][6]=Input[i][6]*2-1;
			Input[i][7]=Input[i][7]*2-1;
			Input[i][8]=Input[i][8]*2-1;
			Input[i][9]=Input[i][9]*2-1;
			Input[i][10]=Input[i][10]*2-1;
			Input[i][11]=Input[i][11]*2-1;
			Input[i][12]=Input[i][12]*2-1;
			Input[i][13]=Input[i][13]*2-1;
			Input[i][14]=Input[i][14]*2-1;
			Input[i][15]=Input[i][15]*2-1;
			Input[i][16]=Input[i][16]*0.00573065902578797-1;
			Input[i][17]=Input[i][17]*0.00573065902578797-1;
			Input[i][18]=Input[i][18]*0.0540540540540541-1;
			Input[i][19]=Input[i][19]*0.0740740740740741-1;
			Input[i][20]=Input[i][20]*0.0425531914893617-1;
			Input[i][21]=Input[i][21]*0.0833333333333333-1;
			Input[i][22]=Input[i][22]*0.0198019801980198-1;
			Input[i][23]=Input[i][23]*0.00557103064066852-1;
			Input[i][24]=Input[i][24]*0.0392156862745098-1;
			Input[i][25]=Input[i][25]*0.0392156862745098-1;
			
		}
	}
	
	boolean[] GetBREAKDOWN_FLAG_FUTURE(){
		InputProcessingFunction(InputVar);
		for(int i=0;i<b1count;i++){
			prehiddenlayer[i]=b1[i];
				for(int j=0;j<26*RowCount;j++)
					{
							prehiddenlayer[i]+=weight1[i][j]*InputVar[ij][ik];
							ik++;
							if(ik==26)
							{
								ij++;
								ik=0;
							}
					}
				hiddenlayer[i]=activatefunction(prehiddenlayer[i]);
				ij=0;
			}
		
		for(int i=0;i<6;i++){
				outputdata[i]=b2[i];
		}
		
		for(int i=0;i<6;i++){
			for (int j=0;j<2;j++)
			{
				outputdata[i]+=weight2[i][j]*hiddenlayer[ji];
				ji++;
				if(ji==2)
				{
					ji=0;
				}
		
			}
		}
		for(int i=0;i<6;i++)
		{
			outputdata[i]=outputdata[i]+1;
			outputdata[i]=outputdata[i]/2;
				if(outputdata[i]>0.5)
				{
					BREAKDOWN_FLAG_FUTURE[i]=true;
									}
			else{
				BREAKDOWN_FLAG_FUTURE[i]=false;
								}
		}
		return BREAKDOWN_FLAG_FUTURE;
	};
	
	PredictionModule(double[][] Input, int SizeofRowofInput, int SizeofColumnofInput, double[][] weightfirst,int SizeofRowofWf, int SizeofColumnofWf, double[][] weightsecond, double[] bfirst, int Sizeofbfirst, double[] bsecond, int Sizeofbsecond)
	{
		InputVar=new double[SizeofRowofInput][SizeofColumnofInput];
		weight1=new double[SizeofRowofWf][SizeofColumnofWf];
		weight2=new double[6][Sizeofbfirst];
		RowCount=SizeofRowofInput;
		ColCount=SizeofColumnofInput;
		for(int i=0;i<SizeofRowofInput;i++)
		{
			for(int j=0;j<SizeofColumnofInput;j++)
			{
				InputVar[i][j]=Input[i][j];
			}
		}
		for(int i=0;i<SizeofRowofWf;i++)
		{
			for(int j=0;j<SizeofColumnofWf;j++)
			{
				weight1[i][j]=weightfirst[i][j];
			}
		}
		for(int i=0;i<6;i++)
		{
			for(int j=0;j<Sizeofbfirst;j++)
			{
				weight2[i][j]=weightsecond[i][j];
		
			}
		}
		b1count=Sizeofbfirst;
		b1=new double[Sizeofbfirst];
			for(int i=0;i<Sizeofbfirst;i++)
			{
				b1[i]=bfirst[i];
			}
		prehiddenlayer=new double[b1count];
		hiddenlayer=new double[b1count];
		outputdata=new double[6];
		BREAKDOWN_FLAG_FUTURE=new boolean[6];
		b2=new double[Sizeofbsecond];
			for(int i=0;i<Sizeofbsecond;i++)
			{
				b2[i]=bsecond[i];
			}
	}
	

	
}
