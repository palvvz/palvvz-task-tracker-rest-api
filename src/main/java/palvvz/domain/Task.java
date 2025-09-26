package palvvz.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "tasks",
       indexes = {
           @Index(name = "idx_tasks_title", columnList = "title")
       })
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false)
    private String title;

    @Column(length = 1024)
    private String description;

    @Column
    private Instant modifiedAt;

    @Column(nullable = false)
    private Boolean isDone = false;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false,
        foreignKey = @ForeignKey(name = "fk_tasks_user"))
    private User user;
}
