package api;

/**
 * Represents a Register class with its name, email and password
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
   * Creates an instance of Register class given name, email and password
   *
   * @param name
   * @param email
   * @param password
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


}
