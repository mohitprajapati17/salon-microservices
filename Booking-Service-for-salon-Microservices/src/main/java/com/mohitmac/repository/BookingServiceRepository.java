

package main.java.com.mohitmac.repository;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface BookingServiceRepository extends JpaRepository<Booking, Long>  {

    List<Booking> findByCustomerId(Long customerId);

    List<Booking> findBySalonIds(Long salonId);
    
}
