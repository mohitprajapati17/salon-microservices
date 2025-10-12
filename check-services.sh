#!/bin/bash

# Colors
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m'

echo "🔍 Checking Service Status..."
echo "================================================"

check_service() {
    local port=$1
    local name=$2
    local eureka_name=$3
    
    # Check if port is listening
    if lsof -i :$port > /dev/null 2>&1; then
        # Check Eureka registration
        if [ ! -z "$eureka_name" ]; then
            if curl -s http://localhost:8070/eureka/apps/$eureka_name 2>/dev/null | grep -q "<status>UP</status>"; then
                echo -e "${GREEN}✅ $name (port $port) - RUNNING & REGISTERED${NC}"
            else
                echo -e "${YELLOW}⚠️  $name (port $port) - RUNNING but NOT REGISTERED in Eureka${NC}"
            fi
        else
            echo -e "${GREEN}✅ $name (port $port) - RUNNING${NC}"
        fi
    else
        echo -e "${RED}❌ $name (port $port) - NOT RUNNING${NC}"
    fi
}

# Check each service
check_service 8070 "Eureka Server" ""
check_service 5001 "User Service" "USERSERVICE"
check_service 5002 "Salon Service" "SALON-SERVICE"
check_service 5003 "Category Service" "CATERGORY-SERVICE"
check_service 5004 "Service Offering" "SERVICE-OFFERING"
check_service 5005 "Booking Service" "BOOKING-SERVICE"
check_service 5006 "Payment Service" "PAYMENT-SERVICE"
check_service 5007 "Notification Service" "NOTIFICATION-SERVICE"
check_service 5008 "Review Service" "REVIEW-SERVICE"
check_service 8085 "Gateway Server" "GATEWAY-SERVER"

echo ""
echo "================================================"
echo "📊 Summary"
echo "================================================"

TOTAL=10
RUNNING=$(lsof -i :8070,:5001,:5002,:5003,:5004,:5005,:5006,:5007,:5008,:8085 2>/dev/null | grep LISTEN | wc -l)

echo "Running: $RUNNING / $TOTAL services"
echo ""

if [ $RUNNING -eq $TOTAL ]; then
    echo -e "${GREEN}🎉 All services are running!${NC}"
    echo ""
    echo "View Eureka Dashboard: http://localhost:8070"
    echo "Test via Gateway: http://localhost:8085/api/users"
elif [ $RUNNING -gt 0 ]; then
    echo -e "${YELLOW}⚠️  Some services are not running${NC}"
    echo ""
    echo "Check logs in ./logs/ directory"
    echo "Example: tail -f logs/user-service.log"
else
    echo -e "${RED}❌ No services are running${NC}"
    echo ""
    echo "Start services with: ./start-services-codespaces.sh"
fi

echo ""
