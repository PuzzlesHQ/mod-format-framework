import dev.puzzleshq.mod.ModFormats;
import dev.puzzleshq.mod.info.ModInfo;

import java.io.IOException;
import java.io.InputStream;

public class TestMain {

    public static void main(String[] args) throws IOException {
        ModFormats.initDefaultFormats();

        InputStream stream = TestMain.class.getResourceAsStream("/puzzle.mod.json");
        byte[] bytes = stream.readAllBytes();
        stream.close();

        String str = new String(bytes);

        ModInfo info = ModInfo.readFromString(str);
        System.out.println(info.getDisplayName());
    }

}
