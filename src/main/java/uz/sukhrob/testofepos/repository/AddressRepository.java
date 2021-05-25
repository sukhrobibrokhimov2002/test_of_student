package uz.sukhrob.testofepos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.sukhrob.testofepos.entity.Address;
import uz.sukhrob.testofepos.entity.University;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
