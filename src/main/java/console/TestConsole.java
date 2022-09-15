package console;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestConsole implements Console{
  private final Iterator<String> it;
  private final List<String> output = new ArrayList<>();

  public TestConsole(Iterator<String> it) {
    this.it = it;
  }

  public TestConsole(Iterable<String> iterable){
    this(iterable.iterator());
  }

  @Override
  public String nextLine() {
    return it.next();
  }

  @Override
  public void printLine(String line) {
    output.add(line);
  }

  public List<String> getOutput() {
    return output;
  }
}
