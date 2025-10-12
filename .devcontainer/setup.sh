#!/bin/bash

echo "🚀 Setting up Salon Microservices Environment..."

# Update package list
sudo apt-get update -y

# Install PostgreSQL client tools
echo "📦 Installing PostgreSQL..."
sudo apt-get install -y postgresql-client

# Start PostgreSQL service
echo "🔧 Starting PostgreSQL..."
sudo service postgresql start

# Wait for PostgreSQL to be ready
sleep 5

# Create databases
echo "💾 Creating databases..."
sudo -u postgres psql -c "ALTER USER postgres PASSWORD '2006';"
sudo -u postgres psql -c "CREATE DATABASE \"Salon_User_db\";" 2>/dev/null || true
sudo -u postgres psql -c "CREATE DATABASE \"Salon_Service_db\";" 2>/dev/null || true
sudo -u postgres psql -c "CREATE DATABASE \"Catergory_Service_db\";" 2>/dev/null || true
sudo -u postgres psql -c "CREATE DATABASE \"Service_offering_db\";" 2>/dev/null || true
sudo -u postgres psql -c "CREATE DATABASE \"Booking_Services_db\";" 2>/dev/null || true
sudo -u postgres psql -c "CREATE DATABASE \"Payment_Service_db\";" 2>/dev/null || true
sudo -u postgres psql -c "CREATE DATABASE \"Notification_Services_db\";" 2>/dev/null || true
sudo -u postgres psql -c "CREATE DATABASE \"Review_Service_db\";" 2>/dev/null || true

echo "✅ All 8 databases created!"

# Install Keycloak using Docker
echo "🔐 Setting up Keycloak..."
docker pull quay.io/keycloak/keycloak:latest
docker run -d \
  --name keycloak \
  -p 8080:8080 \
  -e KEYCLOAK_ADMIN=admin \
  -e KEYCLOAK_ADMIN_PASSWORD=admin \
  quay.io/keycloak/keycloak:latest \
  start-dev

echo "⏳ Waiting for Keycloak to start (30 seconds)..."
sleep 30

echo ""
echo "✅ ✅ ✅  SETUP COMPLETE! ✅ ✅ ✅"
echo ""
echo "📋 Next Steps:"
echo "1. PostgreSQL is running on port 5432"
echo "2. Keycloak is running on port 8080 (admin/admin)"
echo "3. Access Keycloak at: http://localhost:8080"
echo "4. Configure Keycloak realm and client"
echo "5. Start your microservices!"
echo ""
echo "🚀 To start services, run:"
echo "   cd eureka-server && ./mvnw spring-boot:run"
echo ""
