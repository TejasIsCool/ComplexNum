# ComplexNum
A Simple Java Complex Numbers Library



## Usage

- Creating a complex number:
```java
//Instantiates to 0,0
ComplexNum a = new ComplexNum();

// Stores the number 3+4i
ComplexNum b = new ComplexNum(3,4);

// Stores the number 4+4i (Only accept i as imaginary unit for string based input)
ComplexNum c = new ComplexNum("4+4i");

// Creates a ComplexNum from its polar coordinates (r = 1, angle = pi/2 below)
ComplexNum f = ComplexNum.fromPolar(1 , Math.PI/2);
```

- Basic operations
```java
a.add(b); // Adds the value of b into a itself
ComplexNum c = ComplexNum.add(a,b); // Makes a new variable that is the sum of a and b

// Every function below has its static equivalent, like above
a.sub(b); // Subtraction 
a.mul(b); // Multiplication
a.div(b); // Division
a.pow(2); // Squares the number (Raises it to a power of 2)
a.pow(b); // Complex power: Returns the principal value
```

- Complex properties
```java
// Every function below has its static equivalent, like above
System.out.println(a.abs()); // Returns the absolue value/mod of the complex number
System.out.printlb(a.dist(b)); // Returns the distance between the complex numbers
System.out.println(a.conj()); // Returns the conjugate of a
System.out.print(a.toPolar()); // Returns a double array containing its magnitude and angle

```
