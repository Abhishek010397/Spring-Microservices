package labcerebrone.com.multipledb.admissions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="admissions") //table name for the database
@Profile("admissions")
public class Admissions {
    @Id
    private int id;
    private String description;


}

