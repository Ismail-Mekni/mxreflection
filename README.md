# MXReflection  
A Java math framework based on [mXparser library](http://mathparser.org/) capabilities. 

You can calculate complex mathematical operations and functions with Java, just by using class related fields, MXReflection reads values from the assigned fields and injects the results in the `@MXFormula` annotated fields:  

    class Example1 {
    
        @Variable("f1")
        String field1;
    
        @Variable("f2")
        int field2;
    
        @MXFormula("")
        double field3;
    
    }
    
    public static void main(String[] args) {
        
        Example1 example1 = new Example1();
        example1.field1 = "2.2";
        example1.field2 = 5;
        
        Calculator<Example1> calculator = MXFactory.createCalculator(Example1.class);
        calculator.calculate(example1);
        System.out.println("Field 3 result: " + example1.field3);
    }

The result:

    Field 3 result: 

## Supported math collection

MXReflection supports all the [math collection](http://mathparser.org/mxparser-math-collection/) available in mXparser math library:

 - [Operators](http://mathparser.org/mxparser-math-collection/operators/)
- [Binary Relations](http://mathparser.org/mxparser-math-collection/binary-relations/)
- [Boolean Operators](http://mathparser.org/mxparser-math-collection/boolean-operators/)
- [Bitwise Operators](http://mathparser.org/mxparser-math-collection/bitwise-operators/)
- [Unary Functions](http://mathparser.org/mxparser-math-collection/unary-functions/)
- [Binary Functions](http://mathparser.org/mxparser-math-collection/binary-functions/)
- [3-args Functions](http://mathparser.org/mxparser-math-collection/3-args-functions/)
- [Variadic Functions](http://mathparser.org/mxparser-math-collection/variadic-functions/)
- [Iterated Operators](http://mathparser.org/mxparser-math-collection/iterated-operators/)
- [Calculus Operators](http://mathparser.org/mxparser-math-collection/calculus-operators/)
- [Math Constants](http://mathparser.org/mxparser-math-collection/constants/)
- [Physical Constants](http://mathparser.org/mxparser-math-collection/physical-constants/)
- [Astronomical Constants](http://mathparser.org/mxparser-math-collection/astronomical-constants/)
- [Random Variables](http://mathparser.org/mxparser-math-collection/random-variables/)
- [Metric prefixes](http://mathparser.org/mxparser-math-collection/metric-prefixes/)
- [Units](http://mathparser.org/mxparser-math-collection/units/)
- [Parser Symbols](http://mathparser.org/mxparser-math-collection/parser-symbols/)
- [Scientific number types](http://mathparser.org/mxparser-math-collection/number-formats/)

## MXReflection parsing
### Argument parsing

MXReflection supports all field data types with numeric content as argument. You can use all java types with `toString` implementations that returns numeric results.

### Result parsing

Supported result field java types:

 - Double
 - double
 - Long
 - long
 - String
 - BigInteger

 **You should note that for long, Long, and BigInteger, MXReflection uses `Math.round` to parse the final result before injecting it. It is recommanded to be sure that the formula returns and integer type.**
  
## Result reuse

The field result :    

     public class JavaBean {          
         @Variable("f1")  
         public String field1;          
     
         @Variable("f2")  
         public int field2;          
     
         @MXFormula("f1 - f2")  
         public String field3;          
     
         @MXFormula("f1 * f2")  
         public double field5;          
     
         @MXFormula("sin(f2)")  
         public Double field6;          
     
         @MXFormula("f1 - f2")  
         public long field9;          
     
         @MXFormula("f1 - f2")  
         public Long field10;          
     
         @MXFormula("f1 - f2")  
         public BigInteger field15; 
    }

  

The variables annotated with `@MXFormula` are going to be calculated using the functions written as annotation value. `@Variable` is optional, it is used to customize the field name that will be used in the formula description.  
  
As you can see above, MathReflection can help supports several data type parsing.  
  
You can launch the calculation with two simple steps, first you create the calculator, juste one calculator per data type, and then you use the object to be calculated as a parameter:  
  
 calculator = MXFactory.createCalculator(JavaBean.class); JavaBean javaBean = new JavaBean(); javaBean.field1="5.5"; javaBean.field2=2; calculator.calculate(javaBean); // javaBean is an instance of JavaBean class  
Now, the magic happens, all the fields annotated with `@MXFormula` are injected with the required values.  
Of course the same features are available for even if you hava used private fields.