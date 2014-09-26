package nl.dvberkel.util;

import java.io.IOException;
import java.io.InputStream;

public class AlwaysOne extends InputStream {

    @Override
    public int read() throws IOException {
        return 1;
    }
}
