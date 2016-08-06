package com.betterebay.api;

/**
 * Represents a Register class with its name, email, password and status
 *
 * @author LiYang
 *
 */
public class Register {
  private String name;
  private String email;
  private String password;
  private String status;

  /**
   * Creates an instance of Register class given name, email, password and status
   *
   * @param name The name of the Register object
   * @param email The email of the Register object
   * @param password The password of the Register object
   * @param status The status of the Register object
   */
  public Register(String name, String email, String password, String status) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.status = status;
  }

  /**
   * @return the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return this.email;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * @return the status
   */
  public String getStatus() {
    return this.status;
  }

  /**
   * @param status the status to set
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((this.email == null) ? 0 : this.email.hashCode());
    result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
    result = (prime * result) + ((this.password == null) ? 0 : this.password.hashCode());
    result = (prime * result) + ((this.status == null) ? 0 : this.status.hashCode());
    return result;
  }

  /**
   * {@inheritDoc}
   */
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
    Register other = (Register) obj;
    if (this.email == null) {
      if (other.email != null) {
        return false;
      }
    } else if (!this.email.equals(other.email)) {
      return false;
    }
    if (this.name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!this.name.equals(other.name)) {
      return false;
    }
    if (this.password == null) {
      if (other.password != null) {
        return false;
      }
    } else if (!this.password.equals(other.password)) {
      return false;
    }
    if (this.status == null) {
      if (other.status != null) {
        return false;
      }
    } else if (!this.status.equals(other.status)) {
      return false;
    }
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Register [name=" + this.name + ", email=" + this.email + ", password=" + this.password
        + ", status=" + this.status + "]";
  }



}
