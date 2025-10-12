#!/bin/bash

echo "🚀 Starting All Microservices..."
echo ""

# Create logs directory
mkdir -p logs

# Start services in background
echo "▶️  Starting Eureka Server (8070)..."
cd eureka-server && ./mvnw spring-boot:run > ../logs/eureka.log 2>&1 &
EUREKA_PID=$!
cd ..

sleep 10

echo "▶️  Starting User Service (5001)..."
cd userService && ./mvnw spring-boot:run > ../logs/user.log 2>&1 &
USER_PID=$!
cd ..

echo "▶️  Starting Salon Service (5002)..."
cd salon-service && ./mvnw spring-boot:run > ../logs/salon.log 2>&1 &
SALON_PID=$!
cd ..

echo "▶️  Starting Category Service (5003)..."
cd catergory-Service && ./mvnw spring-boot:run > ../logs/category.log 2>&1 &
CATEGORY_PID=$!
cd ..

echo "▶️  Starting Service Offering (5004)..."
cd service-offering && ./mvnw spring-boot:run > ../logs/offering.log 2>&1 &
OFFERING_PID=$!
cd ..

echo "▶️  Starting Booking Service (5005)..."
cd Booking-Service && ./mvnw spring-boot:run > ../logs/booking.log 2>&1 &
BOOKING_PID=$!
cd ..

echo "▶️  Starting Payment Service (5006)..."
cd Payment-Service && ./mvnw spring-boot:run > ../logs/payment.log 2>&1 &
PAYMENT_PID=$!
cd ..

echo "▶️  Starting Gateway Server (8085)..."
cd Gateway-Server && ./mvnw spring-boot:run > ../logs/gateway.log 2>&1 &
GATEWAY_PID=$!
cd ..

echo ""
echo "⏳ Waiting for services to start (60 seconds)..."
sleep 60

echo ""
echo "================================================"
echo "          ✅ ALL SERVICES STARTED!"
echo "================================================"
echo ""
echo "🔍 Check Status:"
echo "   Eureka:           http://localhost:8070"
echo "   User Service:     http://localhost:5001"
echo "   Salon Service:    http://localhost:5002"
echo "   Category:         http://localhost:5003"
echo "   Service Offering: http://localhost:5004"
echo "   Booking:          http://localhost:5005"
echo "   Payment:          http://localhost:5006"
echo "   Gateway:          http://localhost:8085"
echo ""
echo "📋 View Logs:"
echo "   tail -f logs/user.log"
echo "   tail -f logs/eureka.log"
echo ""
echo "🛑 Stop All Services:"
echo "   pkill -f spring-boot:run"
echo ""
echo "================================================"
