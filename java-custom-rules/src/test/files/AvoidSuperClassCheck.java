/*
 * Creation : 20 avr. 2015
 */
package org.sonar.samples.java;

import org.slf4j.Logger;

/**
 * A class with extends another class outside the JVM but in classpath
 */
public class AvoidSuperClassCheck extends Logger { // Noncompliant {{The usage of super class org.slf4j.Logger is forbidden}}

  protected AvoidSuperClassCheck(String name) {
    super(name);
  }

}
