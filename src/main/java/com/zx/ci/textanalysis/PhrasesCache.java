package com.zx.ci.textanalysis;

import java.io.IOException;

public interface PhrasesCache {
  public boolean isValidPhrase(String text) throws IOException;
}
