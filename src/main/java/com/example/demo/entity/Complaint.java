@Entity
@Table(name = "complaints")
public class Complaint{
    @Id
    @GenerateValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String category;
    private int priorityScore;
    @Enumerated(EnumType.STRING)
    private ComplaintStatus status;
    private LocalDateTime submittedOn;
    @ManyToOne
    private 
}