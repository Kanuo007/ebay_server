package core;


import java.security.Principal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
public class User implements Principal {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_name", nullable = false)
  @JsonProperty
  private String user_name;

  @Column(name = "user_password", nullable = false)
  @JsonProperty
  private String user_password;

  @Column(name = "user_email", nullable = false)
  @JsonProperty
  private String user_email;

  public User() {}

  public User(@JsonProperty("user_name") String user_name,
      @JsonProperty("user_password") String user_password,
      @JsonProperty("user_email") String user_email) {
    this.user_name = user_name;
    this.user_password = user_password;
    this.user_email = user_email;
  }

  @Override
  @JsonIgnore
  public String getName() {
    return this.getUser_name();
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUser_name() {
    return this.user_name;
  }

  public void setUser_name(String user_name) {
    this.user_name = user_name;
  }

  public String getUser_password() {
    return this.user_password;
  }

  public void setUser_password(String user_password) {
    this.user_password = user_password;
  }

  public String getUser_email() {
    return this.user_email;
  }

  public void setUser_email(String user_email) {
    this.user_email = user_email;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
    result = (prime * result) + ((this.user_email == null) ? 0 : this.user_email.hashCode());
    result = (prime * result) + ((this.user_name == null) ? 0 : this.user_name.hashCode());
    result = (prime * result) + ((this.user_password == null) ? 0 : this.user_password.hashCode());
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
    User other = (User) obj;
    if (this.id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!this.id.equals(other.id)) {
      return false;
    }
    if (this.user_email == null) {
      if (other.user_email != null) {
        return false;
      }
    } else if (!this.user_email.equals(other.user_email)) {
      return false;
    }
    if (this.user_name == null) {
      if (other.user_name != null) {
        return false;
      }
    } else if (!this.user_name.equals(other.user_name)) {
      return false;
    }
    if (this.user_password == null) {
      if (other.user_password != null) {
        return false;
      }
    } else if (!this.user_password.equals(other.user_password)) {
      return false;
    }
    return true;
  }
}
