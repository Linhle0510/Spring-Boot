package spring.exercise.product_manager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "detail")
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Detail(String name, String value, Product product) {
        this.name = name;
        this.value = value;
        this.product = product;
    }

    @Override
    public String toString() {
        return name + " " + value;
    }

    public Detail(Long id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

}