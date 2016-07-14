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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((this.content == null) ? 0 : this.content.hashCode());
    result = (prime * result) + ((this.defaultGuest == null) ? 0 : this.defaultGuest.hashCode());
    return result;
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Template other = (Template) obj;
    if (this.content == null) {
      if (other.content != null) {
        return false;
      }
    } else if (!this.content.equals(other.content)) {
      return false;
    }
    if (this.defaultGuest == null) {
      if (other.defaultGuest != null) {
        return false;
      }
    } else if (!this.defaultGuest.equals(other.defaultGuest)) {
      return false;
    }
    return true;
  }

}
