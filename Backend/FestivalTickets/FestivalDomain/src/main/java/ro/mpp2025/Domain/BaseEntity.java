package ro.mpp2025.Domain;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
public interface BaseEntity <ID>{
    ID getId();
    void setId(ID id);
}
