//This is the complex class


/** */
public class ComplexNum {
	public double re,im;
	
	
	//When creating without any instance
	public ComplexNum(){
		this.re = 0;
		this.im = 0;
	}
	//When entering the number with real and imaginary parts
	public ComplexNum(double re,double im){
		this.im = im;
		this.re = re;
	}
	
	//Only entered real part
	public ComplexNum(double re){
		this.re = re;
		this.im = 0;
	}
	
	public ComplexNum(String num){
		if(num.contains("i")){
			if(num.contains("+")||num.contains("-")){
				int ind = Math.max(num.lastIndexOf("-"),num.lastIndexOf("+"));
				if(ind == 0){
				    if (num.equals("-i")||num.equals("+i")||num.equals("i")){
				    	this.im = Double.parseDouble(num.replace("i","1"));
				    	this.re = 0;
				    	return;
				    }
				    this.im = Double.parseDouble(num.replace("i",""));
				}else{
				String ah = num.substring(0,ind);
				String be = num.substring(ind);
				if(ah.contains("i")&&be.contains("i")){
				     throw new NumberFormatException("Cannot have both parts be Imaginary!");
				}else if(ah.contains("i")){
					if (ah.equals("-i")||ah.equals("+i")||ah.equals("i")){
						this.im = Double.parseDouble(ah.replace("i","1"));
						this.re = Double.parseDouble(be);
						return;
					}
					this.im = Double.parseDouble(ah.replace("i",""));
					this.re = Double.parseDouble(be);
				}else{
					this.re = Double.parseDouble(ah);
					if (be.equals("-i")||be.equals("+i")){
						this.im = Double.parseDouble(be.replace("i","1"));
						return;
					}
					this.im = Double.parseDouble(be.replace("i",""));
				}
			}
			}else{
				this.re = 0;
				if(num.indexOf("i") == 0 || num.indexOf("i") == num.length()-1){
					if(num.split("i").length>2){
						throw new NumberFormatException("Multiple imaginary constants found in the String");
					}else{
						if (num.equals("i")){
							this.im = 1;
							return;
						}
						this.im = Double.parseDouble(num.replace("i",""));
					}
				}else{
					throw new NumberFormatException("Imaginary constant in middle of the number. Please seperate the numbers with a plus or minus sign!");
				}
			}
		}else{
			this.re = Double.parseDouble(num);
			this.im = 0;
		}
	}
	
	//Checks if two complex numbers are equal
	public boolean equals(ComplexNum a){
		return this.re == a.re && this.im == a.im;
	}
	
	public static boolean equals(ComplexNum a, ComplexNum b){
		return a.re == b.re && a.im == b.im;
	}
	
	
	public static ComplexNum valueOf(String num){
			return new ComplexNum(num);
	}
	
	public static ComplexNum valueOf(double num){
		return new ComplexNum(num);
	}
	
	public static ComplexNum valueOf(double real,double ima){
		return new ComplexNum(real,ima);
	}
	
	
	//Constants
	public static final ComplexNum ONE = valueOf(1);
	
	public static final ComplexNum TWO = valueOf(2);
	
	public static final ComplexNum ZERO = valueOf(0);
	
	public static final ComplexNum NEGATIVE_ONE = valueOf(-1);
	
	public static final ComplexNum IOTA = valueOf("i");
	
	
	
	
	
	
	//Adding a complex number to the current number
	public ComplexNum add(ComplexNum num){
		this.re += num.re;
		this.im+=num.im;
		return this;
	}
	
	//Returns a new complex number which is sum of the two given complex numbers
	public static ComplexNum add(ComplexNum a,ComplexNum b){
		 return new ComplexNum(a.re+b.re,a.im+b.im);
	}
	
		//Subtracting a complex number to the current number
	public ComplexNum sub(ComplexNum num){
		this.re -= num.re;
		this.im-=num.im;
		return this;
	}
	
	//Returns a new complex number which is difference of the two given complex numbers(a - b)
	public static ComplexNum sub(ComplexNum a,ComplexNum b){
		 return new ComplexNum(a.re-b.re,a.im-b.im);
	}
	
	//Multiply with a scalar number
	public ComplexNum mulscl(double scl){
		this.re*=scl;
		this.im*=scl;
		return this;
	}
	
	public static ComplexNum mulscl(ComplexNum a,double scl){
		return new ComplexNum(a.re*scl,a.im*scl);
	}
	
	
	
	//Multiplies a complex number to this complex number
	public ComplexNum mul(ComplexNum num){
		double finre = (this.re*num.re)-(this.im*num.im);
		double finim = (this.re*num.im)+(this.im*num.re);
		this.re=finre;
		this.im=finim;
		return this;		
	}
	
	public static ComplexNum mul(ComplexNum a,ComplexNum b){
		double finre = (a.re*b.re)-(a.im*b.im);
		double finim = (a.re*b.im)+(a.im*b.re);
		return new ComplexNum(finre,finim);		
	}
	
	//Division of complex mumbers
	public ComplexNum div(ComplexNum num){
		double finre = ((this.re*num.re)+(this.im*num.im))/(num.re*num.re + num.im*num.im);
		double finim = ((this.im*num.re)-(this.re*num.im))/(num.re*num.re + num.im*num.im);
		this.re=finre;
		this.im=finim;
		return this;
	}
	
	public static ComplexNum div(ComplexNum a,ComplexNum b){
		double finre = ((a.re*b.re)+(a.im*b.im))/(b.re*b.re + b.im*b.im);
		double finim = ((a.im*b.re)-(a.re*b.im))/(b.re*b.re + b.im*b.im);
		return new ComplexNum(finre,finim);		
	}
	
	
	
	public ComplexNum pow(int a){
		ComplexNum temp = this.copy();
		for(int i = 1;i<a;i++){
			this.mul(temp);
		}
		return this;
	}
	
	public static ComplexNum pow(ComplexNum a, int b){
		ComplexNum temp = a.copy();
		for(int i = 1;i<b;i++){
			temp.mul(a);
		}
		return temp;
	}
	
	
	
	//Complex Power
	/**
	 * 
	 * @param a
	 * @return
	 * 
	 * 
	 * Two numbers a and b, their polar: r1*e^it1 and c+di
	 * real part = e^(c*ln(r)-d*t1)*cos(c*t1+d*ln(r))
	 * Imaginary path: e^(c*ln(r)-d*t1)*sin(c*t1+d*ln(r))
	 */

	public ComplexNum pow(ComplexNum a){
		double r = this.toPolar()[0];
		double t = this.toPolar()[1];
		double power_part = (a.re*Math.log(r))-a.im*t;
		double trig_part = (a.im*Math.log(r))+a.re*t;
		
		this.re = Math.exp(power_part)*Math.cos(trig_part);
		this.im = Math.exp(power_part)*Math.sin(trig_part);
		
		return this;		
	}

	public static ComplexNum pow(ComplexNum a, ComplexNum b){
		// Convert a to polar
		double r = a.toPolar()[0];
		double t = a.toPolar()[1];
		double power_part = (b.re*Math.log(r))-b.im*t;
		double trig_part = (b.im*Math.log(r))+b.re*t;

		ComplexNum temp = new ComplexNum(0,0);
		
		temp.re = Math.exp(power_part)*Math.cos(trig_part);
		temp.im = Math.exp(power_part)*Math.sin(trig_part);
		
		return temp;		
	}
	
	
	//Conjugate
	public ComplexNum conj(){
		return new ComplexNum(this.re,-this.im);
	}
	
	public static ComplexNum conj(ComplexNum a){
		return new ComplexNum(a.re,-a.im);
	}
	
	
	
	//Distance between the two complex numbers in a Complex grid
	public double dist(ComplexNum num){
		return ComplexNum.dist(this,num);
	}
	
	public static double dist(ComplexNum a, ComplexNum b){
		return Math.sqrt((b.re-a.re)*(b.re-a.re)+(b.im-a.im)*(b.im-a.im));
	}
	
	
	
	//modulus of complex num is same as absolute
	public double mod(){
		return this.abs();
	}
	public static double mod(ComplexNum num){
		return ComplexNum.abs(num);
	}
	
	public double abs(){
		return Math.sqrt(this.re*this.re + this.im*this.im);
	}
	
	public static double abs(ComplexNum num){
		return Math.sqrt(num.re*num.re + num.im*num.im);
	}
	
	//Polar to complex graph
	public static ComplexNum fromPolar(double r, double t){
		return new ComplexNum(r*Math.cos(t),r*Math.sin(t));
	}
		
	//Cartesian to polar
	public double[] toPolar(){
		if(this.re == 0&&this.im==0){
			return new double[]{0,0};
		}
		double theta = 0;
		if (this.re > 0){
			theta = Math.atan(this.im/this.re);
		}else if (this.re<0){
			theta = Math.atan(this.im/this.re) + Math.PI;
		}else{
			theta = Math.PI/2 * (this.im/Math.abs(this.im));
		}
		return new double[]{Math.sqrt(this.re*this.re + this.im*this.im),theta};
	}
	
	//Cartesian to polad
	public static double[] toPolar(ComplexNum num){
		if(num.re == 0&&num.im==0){
			return new double[]{0,0};
		}
		double theta = 0;
		if (num.re > 0){
			theta = Math.atan(num.im/num.re);
		}else if (num.re<0){
			theta = Math.atan(num.im/num.re) + Math.PI;
		}else{
			//The num.im part is to get the negative or positive angle of theta
			theta = Math.PI/2 * (num.im/Math.abs(num.im));
		}
		return new double[]{Math.sqrt(num.re*num.re + num.im*num.im),theta};
	}
	
	
	
	//Printing the number
	public void printnum(){
		System.out.println(this.getNum());
	}
	
	//Printing the number with the specified imaginsry constant, maybe something like
	//a + bj instead of a+bi
	public void printnum(String imconstant){
		System.out.println(this.getNum(imconstant));
	}
	
	//Same thing as the above but with char
	public void printnum(char imconstant){
		System.out.println(this.getNum(imconstant));
	}
	
	public static void printnum(ComplexNum a){
		System.out.println(ComplexNum.getNum(a));
	}
		
	public String getNum(){
		return re+(im >= 0 ? "+" : "")+(im+0.0)+"i";
	}
	public String getNum(String imconstant){
		return re+(im >= 0 ? "+" : "")+im+imconstant;
	}
	
	public String getNum(char imconstant){
		return re+(im >= 0 ? "+" : "")+im+""+imconstant;
	}
	
	public static String getNum(ComplexNum a){
		return a.re+(a.im >= 0 ? "+" : "")+a.im+"i";
	}
	
	public ComplexNum copy(){
		return new ComplexNum(this.re,this.im);
	}
	
	public String toString(){
		return re+(im+0.0 >= 0 ? "+" : "")+(im+0.0)+"i";
	}
	
}