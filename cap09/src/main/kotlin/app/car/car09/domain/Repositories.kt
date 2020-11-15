package app.car.car09.domain

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>

interface TravelRequestRepository : JpaRepository<TravelRequest, Long> {
    fun findByStatus(status: TravelRequestStatus): List<TravelRequest>
}

interface PassengerRepository : JpaRepository<Passenger, Long>

interface DriverRepository : JpaRepository<Driver, Long>