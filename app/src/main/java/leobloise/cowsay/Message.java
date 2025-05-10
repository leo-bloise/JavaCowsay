package leobloise.cowsay;

import java.util.ArrayList;
import java.util.List;

public class Message {
    private String data;
    public Message(String data) {
        this.data = data;
    }
    public String[] get() {
        if (data == null) return new String[] { "Hello World" };
        int lineSize = 110;
        if(data.length() < lineSize) {
            return new String[] { data.trim() };
        }
        List<String> lines = new ArrayList<>(data.length());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            sb.append(data.charAt(i));
            if ((i % lineSize) != 0 || i == 0) continue;
            lines.add(sb.toString());
            sb.setLength(0);
        }
        if(!sb.isEmpty()) {
            lines.add(sb.toString());
        }
        return lines.toArray(new String[0]);
    }
}
