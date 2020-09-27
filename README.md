# MathReflection
A Java math framework based on mXparser features.  Simple to use with Plain Old Java Object classes. MathReflection supports all functions available from mXparser math library.
MathReflection helps you calculating fields with complex functions starting from other fields as variables:




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

    calculator = MXFactory.createCalculator(JavaBean.class);
    JavaBean javaBean = new JavaBean();
    javaBean.field1="5.5";
    javaBean.field2=2;
    calculator.calculate(javaBean); // javaBean is an instance of JavaBean class

Now, the magic happens, all the fields annotated with `@MXFormula` are injected with the required values.
Of course the same features are available for even if you hava used private fields.