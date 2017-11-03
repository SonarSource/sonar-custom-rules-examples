<?php

foo();                  // Noncompliant {{Remove the usage of this forbidden function.}}
foo(1);                 // Noncompliant

bar();                  // Noncompliant
bar(1);                 // Noncompliant

class Obj {
    function foo() {
        return "foo";
    }

    function bar() {
        return "bar";
    }
};

$myObj = new Obj();

$myObj->foo();            // OK
$myObj->bar();            // OK
