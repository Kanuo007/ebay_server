package core;

import com.google.common.base.Optional;

public class Template {
  private String content;
  private String defaultGuest;

  public Template(String content, String defaultGuest) {
    this.content = content;
    this.defaultGuest = defaultGuest;
  }

  public String formatContent(Optional<String> username) {
    return String.format(this.content, username.or(this.defaultGuest));
  }
}
