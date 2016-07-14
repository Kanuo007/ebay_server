package core;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.security.Principal;

import io.dropwizard.jackson.JsonSnakeCase;

@Entity
@Table(name = "registered_user")
@NamedQueries({@NamedQuery(name = "core.user.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "core.user.findUserByName",
        query = "SELECT u FROM User u WHERE u.user_name = :name"),
    @NamedQuery(name = "core.user.findUserByEmail",
        query = "SELECT u FROM User u WHERE u.user_email = :email"),
    @NamedQuery(name = "core.user.findUserByPassword",
        query = "SELECT u FROM User u WHERE u.user_password = :password"),})
@JsonSnakeCase
public class User implements Principal{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "user_name", nullable = false)
  @JsonProperty
  private String user_name;

  @Column(name = "user_password", nullable = false)
  @JsonProperty
  private String user_password;

  @Column(name = "user_email", nullable = false)
  @JsonProperty
  private String user_email;


  public User(@JsonProperty("user_name") String user_name, @JsonProperty("user_password") String user_password,
              @JsonProperty("user_email") String user_email) {
    this.user_name = user_name;
    this.user_password = user_password;
    this.user_email = user_email;
  }

  @Override
  public String getName(){
    return this.getUser_name();
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUser_name() {
    return user_name;
  }

  public void setUser_name(String user_name) {
    this.user_name = user_name;
  }

  public String getUser_password() {
    return user_password;
  }

  public void setUser_password(String user_password) {
    this.user_password = user_password;
  }

  public String getUser_email() {
    return user_email;
  }

  public void setUser_email(String user_email) {
    this.user_email = user_email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;

    if (id != user.id) return false;
    if (user_name != null ? !user_name.equals(user.user_name) : user.user_name != null)
      return false;
    if (user_password != null ? !user_password.equals(user.user_password) : user.user_password != null)
      return false;
    return user_email != null ? user_email.equals(user.user_email) : user.user_email == null;

  }

  @Override
  public int hashCode() {
    int result = (int) (id ^ (id >>> 32));
    result = 31 * result + (user_name != null ? user_name.hashCode() : 0);
    result = 31 * result + (user_password != null ? user_password.hashCode() : 0);
    result = 31 * result + (user_email != null ? user_email.hashCode() : 0);
    return result;
  }
}
