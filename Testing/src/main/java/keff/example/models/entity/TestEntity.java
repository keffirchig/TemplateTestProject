package keff.example.models.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Entity
@Table(name = "test")
@Data
@RequiredArgsConstructor
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;



    @ManyToOne
    @JoinColumn(name = "inner_test_entity_id")
    public InnerTestEntity innerTestEntity;
}
