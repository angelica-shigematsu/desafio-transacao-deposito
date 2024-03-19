package br.com.tgid.javajunior.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="transition")
@Getter
@Setter
@NoArgsConstructor
public class Transition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true)
    private long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private Client client;

    @ManyToOne
    @JoinColumn(name="company_id", nullable=false)
    private Company company;
}
