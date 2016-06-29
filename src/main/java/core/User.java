package core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.security.Principal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="user")
@NamedQueries({
        @NamedQuery(
                name = "core.user.findAll",
                query = "SELECT u FROM User u"
        ),
        @NamedQuery(
                name = "core.user.findUeserByName",
                query = "SELECT u FROM User u where u.name = :name"
        ),
        @NamedQuery(
            name = "core.user.findUeserByEmail",
            query = "SELECT u FROM User u where u.email = :email"
        ),
        @NamedQuery(
            name = "core.user.findUeserByEmail",
            query = "SELECT u FROM User u where u.password = :password"
        ),
})
public class User implements Principal {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
          generator = "user_id_seq_name")
  @SequenceGenerator(name = "user_id_seq_name",
          sequenceName = "user_id_seq",
          allocationSize = 1)
  @JsonProperty
  private long id;

  @Column(name="name", nullable=false)
  @JsonProperty
  private String name;

  @Column(name="password", nullable=false)
  @JsonProperty
  private String password;

  @Column(name="email", nullable=false)
  @JsonProperty
  private String email;

  public long getId() {return this.id;}

  public void setId(long id) {this.id = id;}

  @Override
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((this.email == null) ? 0 : this.email.hashCode());
    result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
    result = (prime * result) + ((this.password == null) ? 0 : this.password.hashCode());
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
    return true;
  }

}
