package utilities;

import java.io.FileWriter;
import java.io.IOException;

public class WriteDataToTXT {
    public void writeData(String[] datas) {
        FileWriter fileWriter= null;
        try {
            fileWriter = new FileWriter("src/test/resources/Data.txt",true);
            for(int i=0;i< datas.length;i++){
                fileWriter.write(datas[i]+"\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
