# 🚀 GitHub Codespaces Setup Guide

## 📋 What Gets Auto-Installed:

When you open this repo in Codespaces, it will automatically install:
- ✅ Java 17 (OpenJDK)
- ✅ Maven
- ✅ PostgreSQL 15
- ✅ Docker (for Keycloak)
- ✅ Keycloak (via Docker)
- ✅ VS Code Extensions (Java, Spring Boot)

**Setup time:** ~5 minutes (automatic)

---

## 🎯 Step-by-Step Setup:

### 1️⃣ Push Code to GitHub

```bash
cd /Users/mohit/salon_microservices

# Initialize git if not already done
git init
git add .
git commit -m "Microservices with Feign clients and devcontainer"

# Create repo on GitHub first, then:
git remote add origin https://github.com/YOUR_USERNAME/salon-microservices.git
git branch -M main
git push -u origin main
```

### 2️⃣ Create Codespace

1. Go to your GitHub repository
2. Click green **"Code"** button
3. Click **"Codespaces"** tab
4. Click **"Create codespace on main"**
5. Wait 3-5 minutes for setup to complete

### 3️⃣ Verify Installation

After Codespaces opens, check everything is installed:

```bash
# Check Java
java -version
# Should show: OpenJDK 17

# Check Maven
mvn -version

# Check PostgreSQL
sudo service postgresql status

# Check Docker
docker ps
# Should show: keycloak container running
```

### 4️⃣ Configure Keycloak

1. Access Keycloak:
   - Find the forwarded port 8080 in Ports panel
   - Click the globe icon to open in browser
   - Or use: `https://your-codespace-8080.githubpreview.dev`

2. Login:
   - Username: `admin`
   - Password: `admin`

3. Create Realm:
   - Click dropdown near "Master" → "Create Realm"
   - Name: `salon-realm`
   - Click "Create"

4. Create Client:
   - Go to "Clients" → "Create client"
   - Client ID: `salon-client`
   - Client authentication: ON
   - Save
   - Go to "Credentials" tab
   - Copy the "Client Secret"

5. Update Your Code:
   - Update `application.yml` files with Keycloak URLs
   - Use forwarded port URL for Keycloak

### 5️⃣ Start Services

**Option A: Run All in Background**

```bash
cd eureka-server && ./mvnw spring-boot:run > /tmp/eureka.log 2>&1 &
cd ../userService && ./mvnw spring-boot:run > /tmp/user.log 2>&1 &
cd ../salon-service && ./mvnw spring-boot:run > /tmp/salon.log 2>&1 &
cd ../catergory-Service && ./mvnw spring-boot:run > /tmp/category.log 2>&1 &
cd ../service-offering && ./mvnw spring-boot:run > /tmp/offering.log 2>&1 &
cd ../Booking-Service && ./mvnw spring-boot:run > /tmp/booking.log 2>&1 &
cd ../Payment-Service && ./mvnw spring-boot:run > /tmp/payment.log 2>&1 &
cd ../Gateway-Server && ./mvnw spring-boot:run > /tmp/gateway.log 2>&1 &
```

**Option B: Use Multiple Terminals**

In VS Code, open 8 terminals (click + icon) and run each service:

```bash
# Terminal 1
cd eureka-server && ./mvnw spring-boot:run

# Terminal 2
cd userService && ./mvnw spring-boot:run

# Terminal 3
cd salon-service && ./mvnw spring-boot:run

# ... etc for all 8 services
```

### 6️⃣ Test with Postman

1. **Find Forwarded URLs:**
   - Click "PORTS" tab at bottom of VS Code
   - Find port 5001 (User Service)
   - Click globe icon to get public URL
   - Example: `https://your-codespace-5001.githubpreview.dev`

2. **Test Login:**
   ```
   POST https://your-codespace-5001.githubpreview.dev/auth/login
   
   Body:
   {
     "email": "villa65@gmail.com",
     "password": "12345678"
   }
   ```

3. **Use Token for Other Requests:**
   - Copy JWT from response
   - Use in Authorization header: `Bearer <token>`

---

## 📊 Service Ports:

| Service | Port | Forwarded URL |
|---------|------|---------------|
| Eureka | 8070 | `https://...-8070.githubpreview.dev` |
| User Service | 5001 | `https://...-5001.githubpreview.dev` |
| Salon Service | 5002 | `https://...-5002.githubpreview.dev` |
| Category Service | 5003 | `https://...-5003.githubpreview.dev` |
| Service Offering | 5004 | `https://...-5004.githubpreview.dev` |
| Booking Service | 5005 | `https://...-5005.githubpreview.dev` |
| Payment Service | 5006 | `https://...-5006.githubpreview.dev` |
| Gateway | 8085 | `https://...-8085.githubpreview.dev` |
| Keycloak | 8080 | `https://...-8080.githubpreview.dev` |

---

## 🐛 Troubleshooting:

### PostgreSQL not running:
```bash
sudo service postgresql start
```

### Keycloak not running:
```bash
docker start keycloak
```

### Check service logs:
```bash
tail -f /tmp/user.log
```

### Restart a service:
```bash
# Kill the process
pkill -f "userService"

# Start again
cd userService && ./mvnw spring-boot:run
```

---

## 💡 Tips:

1. **Save Your Work:**
   - Codespaces auto-saves changes
   - Push to GitHub frequently: `git add . && git commit -m "..." && git push`

2. **Stop Services:**
   - Close terminal or press `Ctrl+C`
   - Or: `pkill -f spring-boot:run`

3. **View All Ports:**
   - Click PORTS tab in VS Code
   - All services will auto-forward
   - Click globe icon to access

4. **Free Tier Limits:**
   - 60 hours/month (Core hours)
   - Stop Codespace when not using
   - GitHub → Codespaces → Stop

---

## ✅ Advantages of Codespaces:

- ✅ No macOS socket issues!
- ✅ Linux environment (production-like)
- ✅ Access from any device (iPad, phone, etc.)
- ✅ Automatic port forwarding
- ✅ Pre-configured environment
- ✅ Free 60 hours/month

---

**Ready to test your microservices without any socket issues!** 🚀
