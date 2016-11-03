import org.apache.commons.collections4.list.UnmodifiableList;
import java.util.ArrayList;

import java.util.ArrayList;

class A {
  void foo() {
    UnmodifiableList myList = new UnmodifiableList(new ArrayList<>()); // Noncompliant {{Avoid using UnmodifiableList}}
    // Noncompliant@+1
    MyList myOtherList = new MyList(); // as MyList extends the UnmodifiableList, we expect an issue here
  }
}

class MyList extends UnmodifiableList {
  public MyList() {
  }
}
