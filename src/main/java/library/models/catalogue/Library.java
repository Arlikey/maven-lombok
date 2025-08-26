package library.models.catalogue;

import library.models.editions.Publication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Library {
    private HashSet<Publication> publications = new HashSet<>();
}
