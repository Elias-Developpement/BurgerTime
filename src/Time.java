import java.util.Date;

public class Time {
  private long startTime;
  private long elapsedTime = 0L;

  public Time() {
    startTime = System.currentTimeMillis();
  }

  public long getElapasedTime() {
    elapsedTime = (new Date()).getTime() - startTime;
    return elapsedTime;
  }

  public void reset() {
    startTime = System.currentTimeMillis();
    elapsedTime = 0L;
  }
}
