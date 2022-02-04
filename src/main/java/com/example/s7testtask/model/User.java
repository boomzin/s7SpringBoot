package com.example.s7testtask.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Persistable;
import org.springframework.data.util.ProxyUtils;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Access(AccessType.FIELD)
public class User implements Persistable<Integer> {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Override
    public Integer getId() {
        return this.id;
    }

    @JsonIgnore
    @Override
    public boolean isNew() {
        return id == null;
    }

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "has_photo", nullable = false, columnDefinition = "bool default false")
    private boolean hasPhoto;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinTable(name = "friendship",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "friend_id"}, name = "uk_user_friend")}
    )
    private List<User> friends;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(ProxyUtils.getUserClass(o))) {
            return false;
        }
        User that = (User) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public boolean isHasPhoto() {
        return hasPhoto;
    }

    public Gender getGender() {
        return gender;
    }

    public Status getStatus() {
        return status;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setHasPhoto(boolean hasPhoto) {
        this.hasPhoto = hasPhoto;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public boolean addOrRemoveFriend(User friend) {
        return friends.contains(friend) ? friends.remove(friend) : friends.add(friend);
    }
}
