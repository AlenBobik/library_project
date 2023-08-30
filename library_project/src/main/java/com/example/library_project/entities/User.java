package com.example.library_project.entities;

import com.example.library_project.enums.Roles;
import com.example.library_project.enums.Status;
import java.util.List;
import javax.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @SequenceGenerator(
            name = "users_id_sequence",
            sequenceName = "users_id_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_sequence")
    @Setter(AccessLevel.PRIVATE)
    @Column(name = "users_id", nullable = false)
    private long userId;

    @Column(name = "users_uporabnisko_ime", unique = true, nullable = false)
    private String username;

    @Column(name = "users_geslo", nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "users_status")
    private Status userStatus;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Roles role;

    @Column(name = "account_non_expired", nullable = false)
    private boolean accountNonExpired;

    @Column(name = "account_non_locked", nullable = false)
    private boolean accountNonLocked;

    @Column(name = "credentials_non_expired", nullable = false)
    private boolean credentialsNonExpired;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Izposoja> izposoja;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToStringExclude
    @JoinColumn(
            name = "oseba_id",
            referencedColumnName = "oseba_id",
            nullable = false,
            unique = true)
    private Oseba oseba;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToStringExclude
    @JoinColumn(name = "obvestilo_id", referencedColumnName = "obvestilo_id")
    private Obvestilo obvestilo;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToStringExclude
    @JoinColumn(name = "zaposlen_id", referencedColumnName = "zaposlen_id")
    private Zaposlen zaposlen;
}
