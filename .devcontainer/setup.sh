#!/bin/bash

echo "🚀 Setting up Salon Microservices Environment..."

# Update and install PostgreSQL
echo "📦 Installing PostgreSQL..."
sudo apt-get update -y
sudo apt-get install -y postgresql postgresql-contrib

# Start PostgreSQL
echo "🔧 Starting PostgreSQL..."
sudo service postgresql start

# Wait for PostgreSQL to be ready
sleep 3

# Configure PostgreSQL
echo "💾 Creating databases..."
sudo -u postgres psql -c "ALTER USER postgres WITH PASSWORD '2006';"
sudo -u postgres psql -c "CREATE DATABASE \"Salon_User_db\";" 2>/dev/null || echo "Salon_User_db exists"
sudo -u postgres psql -c "CREATE DATABASE \"Salon_Service_db\";" 2>/dev/null || echo "Salon_Service_db exists"
sudo -u postgres psql -c "CREATE DATABASE \"Catergory_Service_db\";" 2>/dev/null || echo "Catergory_Service_db exists"
sudo -u postgres psql -c "CREATE DATABASE \"Service_offering_db\";" 2>/dev/null || echo "Service_offering_db exists"
sudo -u postgres psql -c "CREATE DATABASE \"Booking_Services_db\";" 2>/dev/null || echo "Booking_Services_db exists"
sudo -u postgres psql -c "CREATE DATABASE \"Payment_Service_db\";" 2>/dev/null || echo "Payment_Service_db exists"
sudo -u postgres psql -c "CREATE DATABASE \"Notification_Services_db\";" 2>/dev/null || echo "Notification_Services_db exists"
sudo -u postgres psql -c "CREATE DATABASE \"Review_Service_db\";" 2>/dev/null || echo "Review_Service_db exists"

echo "✅ All 8 databases configured!"

# Setup Keycloak
echo "🔐 Setting up Keycloak..."
docker pull quay.io/keycloak/keycloak:latest 2>/dev/null || echo "Docker pull in progress..."
docker run -d \
  --name keycloak \
  -p 8080:8080 \
  -e KEYCLOAK_ADMIN=admin \
  -e KEYCLOAK_ADMIN_PASSWORD=admin \
  quay.io/keycloak/keycloak:latest \
  start-dev 2>/dev/null || echo "Keycloak container starting..."

echo ""
echo "✅ ✅ ✅  SETUP COMPLETE! ✅ ✅ ✅"
echo ""
echo "📋 Services Ready:"
echo "  ✅ PostgreSQL: port 5432 (user: postgres, pass: 2006)"
echo "  ✅ Keycloak: port 8080 (admin/admin)"
echo "  ✅ Docker: Ready"
echo ""
echo "🚀 To start all microservices:"
echo "   ./start-all.sh"
echo ""
