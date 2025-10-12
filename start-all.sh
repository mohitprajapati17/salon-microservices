#!/bin/bash

echo "Starting all microservices..."

# Start Eureka Server
echo "Starting Eureka Server..."
cd eureka-server
./mvnw spring-boot:run > ../logs/eureka.log 2>&1 &
EUREKA_PID=$!
cd ..

# Wait for Eureka to start
echo "Waiting 30 seconds for Eureka to start..."
sleep 30

# Start User Service
echo "Starting User Service (port 5001)..."
cd userService
./mvnw spring-boot:run > ../logs/user-service.log 2>&1 &
USER_PID=$!
cd ..

# Start Salon Service
echo "Starting Salon Service (port 5002)..."
cd salon-service
./mvnw spring-boot:run > ../logs/salon-service.log 2>&1 &
SALON_PID=$!
cd ..

# Start Category Service
echo "Starting Category Service (port 5003)..."
cd catergory-Service
./mvnw spring-boot:run > ../logs/category-service.log 2>&1 &
CATEGORY_PID=$!
cd ..

# Start Service Offering
echo "Starting Service Offering (port 5004)..."
cd service-offering
./mvnw spring-boot:run > ../logs/service-offering.log 2>&1 &
OFFERING_PID=$!
cd ..

# Start Booking Service
echo "Starting Booking Service (port 5005)..."
cd Booking-Service
./mvnw spring-boot:run > ../logs/booking-service.log 2>&1 &
BOOKING_PID=$!
cd ..

# Start Payment Service
echo "Starting Payment Service (port 5006)..."
cd Payment-Service
./mvnw spring-boot:run > ../logs/payment-service.log 2>&1 &
PAYMENT_PID=$!
cd ..

# Start Notification Service
echo "Starting Notification Service (port 5007)..."
cd notification-Service
./mvnw spring-boot:run > ../logs/notification-service.log 2>&1 &
NOTIFICATION_PID=$!
cd ..

# Start Review Service
echo "Starting Review Service (port 5008)..."
cd review-service
./mvnw spring-boot:run > ../logs/review-service.log 2>&1 &
REVIEW_PID=$!
cd ..

# Start Gateway
echo "Starting Gateway Server (port 8085)..."
cd Gateway-Server
./mvnw spring-boot:run > ../logs/gateway.log 2>&1 &
GATEWAY_PID=$!
cd ..

echo ""
echo "========================================="
echo "All services started!"
echo "========================================="
echo "Eureka Dashboard: http://localhost:8070"
echo "Gateway:          http://localhost:8085"
echo ""
echo "Services:"
echo "  - User Service:         http://localhost:5001"
echo "  - Salon Service:        http://localhost:5002"
echo "  - Category Service:     http://localhost:5003"
echo "  - Service Offering:     http://localhost:5004"
echo "  - Booking Service:      http://localhost:5005"
echo "  - Payment Service:      http://localhost:5006"
echo "  - Notification Service: http://localhost:5007"
echo "  - Review Service:       http://localhost:5008"
echo ""
echo "Via Gateway:"
echo "  - Users:         http://localhost:8085/api/users"
echo "  - Salons:        http://localhost:8085/api/salons"
echo "  - Categories:    http://localhost:8085/api/categories"
echo "  - Services:      http://localhost:8085/api/services"
echo "  - Bookings:      http://localhost:8085/api/booking"
echo "  - Payments:      http://localhost:8085/api/payments"
echo "  - Notifications: http://localhost:8085/api/notification"
echo "  - Reviews:       http://localhost:8085/api/review"
echo ""
echo "PIDs: Eureka=$EUREKA_PID User=$USER_PID Salon=$SALON_PID Category=$CATEGORY_PID"
echo "      Offering=$OFFERING_PID Booking=$BOOKING_PID Payment=$PAYMENT_PID"
echo "      Notification=$NOTIFICATION_PID Review=$REVIEW_PID Gateway=$GATEWAY_PID"
echo ""
echo "To stop all services, run:"
echo "  kill $EUREKA_PID $USER_PID $SALON_PID $CATEGORY_PID $OFFERING_PID $BOOKING_PID $PAYMENT_PID $NOTIFICATION_PID $REVIEW_PID $GATEWAY_PID"
