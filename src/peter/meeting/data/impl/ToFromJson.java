package peter.meeting.data.impl;

import com.ibm.json.java.JSONObject;

public interface ToFromJson {
  public void loadFrom( JSONObject json);
  
  public void saveTo( JSONObject json);
}
