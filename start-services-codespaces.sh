#!/bin/bash

echo "🚀 Starting Salon Microservices in Codespaces..."
echo "================================================"

# Colors for output
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m' # No Color

# Function to check if port is in use
check_port() {
    lsof -i :$1 > /dev/null 2>&1
    return $?
}

# Function to wait for service to be ready
wait_for_service() {
    local port=$1
    local name=$2
    local max_wait=120
    local count=0
    
    echo -e "${YELLOW}⏳ Waiting for $name on port $port...${NC}"
    while ! check_port $port; do
        sleep 2
        count=$((count + 2))
        if [ $count -gt $max_wait ]; then
            echo -e "${RED}❌ Timeout waiting for $name${NC}"
            return 1
        fi
    done
    echo -e "${GREEN}✅ $name is ready!${NC}"
    return 0
}

# Function to wait for Eureka registration
wait_for_eureka_service() {
    local service_name=$1
    local max_wait=60
    local count=0
    
    echo -e "${YELLOW}⏳ Waiting for $service_name to register with Eureka...${NC}"
    while ! curl -s http://localhost:8070/eureka/apps/$service_name | grep -q "<status>UP</status>"; do
        sleep 3
        count=$((count + 3))
        if [ $count -gt $max_wait ]; then
            echo -e "${YELLOW}⚠️  $service_name not fully registered yet (continuing anyway)${NC}"
            return 0
        fi
    done
    echo -e "${GREEN}✅ $service_name registered with Eureka!${NC}"
    return 0
}

# Create logs directory
mkdir -p logs

echo ""
echo "================================================"
echo "STEP 1: Starting Eureka Server"
echo "================================================"
cd eureka-server
./mvnw spring-boot:run > ../logs/eureka.log 2>&1 &
EUREKA_PID=$!
cd ..
wait_for_service 8070 "Eureka Server"
sleep 10  # Give Eureka extra time to fully initialize

echo ""
echo "================================================"
echo "STEP 2: Starting Core Services (User, Salon)"
echo "================================================"

# User Service
echo -e "${YELLOW}Starting User Service...${NC}"
cd userService
./mvnw spring-boot:run > ../logs/user-service.log 2>&1 &
USER_PID=$!
cd ..
sleep 5

# Salon Service
echo -e "${YELLOW}Starting Salon Service...${NC}"
cd salon-service
./mvnw spring-boot:run > ../logs/salon-service.log 2>&1 &
SALON_PID=$!
cd ..

wait_for_service 5001 "User Service"
wait_for_service 5002 "Salon Service"
sleep 5

echo ""
echo "================================================"
echo "STEP 3: Starting Category & Service Offering"
echo "================================================"

# Category Service
echo -e "${YELLOW}Starting Category Service...${NC}"
cd catergory-Service
./mvnw spring-boot:run > ../logs/category-service.log 2>&1 &
CATEGORY_PID=$!
cd ..
sleep 5

# Service Offering
echo -e "${YELLOW}Starting Service Offering...${NC}"
cd service-offering
./mvnw spring-boot:run > ../logs/service-offering.log 2>&1 &
OFFERING_PID=$!
cd ..

wait_for_service 5003 "Category Service"
wait_for_service 5004 "Service Offering"
sleep 5

echo ""
echo "================================================"
echo "STEP 4: Starting Booking & Payment Services"
echo "================================================"

# Booking Service
echo -e "${YELLOW}Starting Booking Service...${NC}"
cd Booking-Service
./mvnw spring-boot:run > ../logs/booking-service.log 2>&1 &
BOOKING_PID=$!
cd ..
sleep 5

# Payment Service
echo -e "${YELLOW}Starting Payment Service...${NC}"
cd Payment-Service
./mvnw spring-boot:run > ../logs/payment-service.log 2>&1 &
PAYMENT_PID=$!
cd ..

wait_for_service 5005 "Booking Service"
wait_for_service 5006 "Payment Service"
sleep 5

echo ""
echo "================================================"
echo "STEP 5: Starting Notification & Review Services"
echo "================================================"

# Notification Service
echo -e "${YELLOW}Starting Notification Service...${NC}"
cd notification-Service
./mvnw spring-boot:run > ../logs/notification-service.log 2>&1 &
NOTIFICATION_PID=$!
cd ..
sleep 5

# Review Service
echo -e "${YELLOW}Starting Review Service...${NC}"
cd review-service
./mvnw spring-boot:run > ../logs/review-service.log 2>&1 &
REVIEW_PID=$!
cd ..

wait_for_service 5007 "Notification Service"
wait_for_service 5008 "Review Service"
sleep 5

echo ""
echo "================================================"
echo "STEP 6: Starting API Gateway"
echo "================================================"

cd Gateway-Server
./mvnw spring-boot:run > ../logs/gateway.log 2>&1 &
GATEWAY_PID=$!
cd ..

wait_for_service 8085 "Gateway Server"

echo ""
echo -e "${GREEN}================================================${NC}"
echo -e "${GREEN}✅ ✅ ✅  ALL SERVICES STARTED! ✅ ✅ ✅${NC}"
echo -e "${GREEN}================================================${NC}"
echo ""
echo "📊 Service Status:"
echo "  ✅ Eureka Dashboard: http://localhost:8070"
echo "  ✅ Gateway:          http://localhost:8085"
echo ""
echo "📍 Direct Services:"
echo "  - User Service:         http://localhost:5001"
echo "  - Salon Service:        http://localhost:5002"
echo "  - Category Service:     http://localhost:5003"
echo "  - Service Offering:     http://localhost:5004"
echo "  - Booking Service:      http://localhost:5005"
echo "  - Payment Service:      http://localhost:5006"
echo "  - Notification Service: http://localhost:5007"
echo "  - Review Service:       http://localhost:5008"
echo ""
echo "🌐 Via Gateway (Recommended):"
echo "  - Users:         http://localhost:8085/api/users"
echo "  - Salons:        http://localhost:8085/api/salons"
echo "  - Categories:    http://localhost:8085/api/categories"
echo "  - Services:      http://localhost:8085/api/services"
echo "  - Bookings:      http://localhost:8085/api/booking"
echo "  - Payments:      http://localhost:8085/api/payments"
echo "  - Notifications: http://localhost:8085/api/notification"
echo "  - Reviews:       http://localhost:8085/api/review"
echo ""
echo "📋 Process IDs:"
echo "  Eureka=$EUREKA_PID User=$USER_PID Salon=$SALON_PID Category=$CATEGORY_PID"
echo "  Offering=$OFFERING_PID Booking=$BOOKING_PID Payment=$PAYMENT_PID"
echo "  Notification=$NOTIFICATION_PID Review=$REVIEW_PID Gateway=$GATEWAY_PID"
echo ""
echo "🛑 To stop all services:"
echo "  kill $EUREKA_PID $USER_PID $SALON_PID $CATEGORY_PID $OFFERING_PID $BOOKING_PID $PAYMENT_PID $NOTIFICATION_PID $REVIEW_PID $GATEWAY_PID"
echo ""
echo "📝 View logs:"
echo "  tail -f logs/eureka.log"
echo "  tail -f logs/user-service.log"
echo "  tail -f logs/booking-service.log"
echo ""
echo -e "${GREEN}🎉 Ready to test your APIs!${NC}"
