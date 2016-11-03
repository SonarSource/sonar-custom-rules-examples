package company.demo;

/**
 *This file is the sample code against we run our unit test.
 *It is placed src/test/files in order to not be part of the maven compilation.
 **/
class BusinessClassDelegate implements MySecurityInterface, SecondInterface {

  int aField;

  @MySecurityAnnotation
  public void aMethod() {

  }

  public void aMethod() { // Noncompliant {{Mandatory Annotation not set @MySecurityAnnotation}}

  }

}
