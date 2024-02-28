package com.github.matkubiak.tqs;

import java.io.IOException;

public interface ISimpleHttpClient {
     String doHttpGet(String uri) throws IOException;
}
