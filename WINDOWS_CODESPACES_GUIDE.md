# 🪟 Complete Guide: Running Salon Microservices from Windows using GitHub Codespaces

## 🎯 Why GitHub Codespaces?
- ✅ **No local installation needed** - Works entirely in the cloud
- ✅ **Access from Windows browser** - No Mac required
- ✅ **Pre-configured environment** - Java, Maven, PostgreSQL auto-installed
- ✅ **Public URLs** - Test APIs from Windows using Postman/Thunder Client
- ✅ **Free 60 hours/month** - Perfect for development and testing

---

## 📝 Step 1: Push Code to GitHub

From your Mac terminal:

```bash
cd /Users/mohit/salon_microservices

# Check git status
git status

# Add all changes
git add .

# Commit
git commit -m "Added notification and review services with Codespaces config"

# Push to GitHub (if repo already exists)
git push origin main

# OR create new repo on GitHub first, then:
# git remote add origin https://github.com/YOUR_USERNAME/salon-microservices.git
# git branch -M main
# git push -u origin main
```

---

## 🚀 Step 2: Create Codespace from Windows

1. **Open Windows browser** (Chrome, Edge, Firefox)
2. Go to: `https://github.com/YOUR_USERNAME/salon-microservices`
3. Click the green **"Code"** button
4. Click **"Codespaces"** tab
5. Click **"Create codespace on main"**
6. Wait **~5 minutes** for automatic setup

### What Gets Auto-Installed:
- ✅ Java 17
- ✅ Maven  
- ✅ PostgreSQL 15 (with 8 databases)
- ✅ Docker + Keycloak
- ✅ VS Code extensions

---

## ✅ Step 3: Verify Setup (in Codespace browser)

Once Codespace opens in your Windows browser:

```bash
# Check Java
java -version

# Check Maven
mvn -version

# Check PostgreSQL (should show "online")
sudo service postgresql status

# Check Docker & Keycloak
docker ps
```

---

## 🎮 Step 4: Start All Microservices

### Option A: Automated Start (Recommended)

```bash
chmod +x start-all.sh
./start-all.sh
```

This starts all 10 services:
1. Eureka Server (8070)
2. User Service (5001)
3. Salon Service (5002)
4. Category Service (5003)
5. Service Offering (5004)
6. Booking Service (5005)
7. Payment Service (5006)
8. Notification Service (5007)
9. Review Service (5008)
10. Gateway Server (8085)

### Option B: Manual Start (for debugging)

Open **9 terminals** in Codespace (click + icon):

```bash
# Terminal 1 - Eureka
cd eureka-server && ./mvnw spring-boot:run

# Terminal 2 - User Service  
cd userService && ./mvnw spring-boot:run

# Terminal 3 - Salon Service
cd salon-service && ./mvnw spring-boot:run

# Terminal 4 - Category Service
cd catergory-Service && ./mvnw spring-boot:run

# Terminal 5 - Service Offering
cd service-offering && ./mvnw spring-boot:run

# Terminal 6 - Booking Service
cd Booking-Service && ./mvnw spring-boot:run

# Terminal 7 - Payment Service
cd Payment-Service && ./mvnw spring-boot:run

# Terminal 8 - Notification Service
cd notification-Service && ./mvnw spring-boot:run

# Terminal 9 - Review Service
cd review-service && ./mvnw spring-boot:run

# Terminal 10 - Gateway (after all services are up)
cd Gateway-Server && ./mvnw spring-boot:run
```

---

## 🌐 Step 5: Get Public URLs (Access from Windows)

1. Click **"PORTS"** tab at bottom of Codespace
2. All ports auto-forward with public URLs

### Copy Your URLs:

| Service | Port | Example URL |
|---------|------|-------------|
| Eureka Dashboard | 8070 | `https://YOUR_CODESPACE-8070.githubpreview.dev` |
| Gateway | 8085 | `https://YOUR_CODESPACE-8085.githubpreview.dev` |
| User Service | 5001 | `https://YOUR_CODESPACE-5001.githubpreview.dev` |
| Salon Service | 5002 | `https://YOUR_CODESPACE-5002.githubpreview.dev` |
| Category Service | 5003 | `https://YOUR_CODESPACE-5003.githubpreview.dev` |
| Service Offering | 5004 | `https://YOUR_CODESPACE-5004.githubpreview.dev` |
| Booking Service | 5005 | `https://YOUR_CODESPACE-5005.githubpreview.dev` |
| Payment Service | 5006 | `https://YOUR_CODESPACE-5006.githubpreview.dev` |
| Notification | 5007 | `https://YOUR_CODESPACE-5007.githubpreview.dev` |
| Review Service | 5008 | `https://YOUR_CODESPACE-5008.githubpreview.dev` |
| Keycloak | 8080 | `https://YOUR_CODESPACE-8080.githubpreview.dev` |

💡 **Tip**: Click the **globe icon** (🌐) next to each port to open in new tab

---

## 🧪 Step 6: Test from Windows using Postman

### 1. Download Postman on Windows
- Download from: https://www.postman.com/downloads/
- Or use Thunder Client extension in VS Code

### 2. Test User Registration

```http
POST https://YOUR_CODESPACE-5001.githubpreview.dev/auth/signup

Content-Type: application/json

{
  "fullName": "Test User",
  "email": "test@example.com",
  "password": "password123"
}
```

### 3. Test User Login

```http
POST https://YOUR_CODESPACE-5001.githubpreview.dev/auth/login

Content-Type: application/json

{
  "email": "test@example.com",
  "password": "password123"
}
```

**Copy the JWT token from response!**

### 4. Test Protected Endpoints

```http
GET https://YOUR_CODESPACE-5002.githubpreview.dev/api/salons

Headers:
Authorization: Bearer YOUR_JWT_TOKEN_HERE
```

### 5. Test via Gateway (Recommended)

```http
# User endpoints
GET https://YOUR_CODESPACE-8085.githubpreview.dev/api/users/profile
Headers: Authorization: Bearer YOUR_JWT_TOKEN

# Salon endpoints  
GET https://YOUR_CODESPACE-8085.githubpreview.dev/api/salons
Headers: Authorization: Bearer YOUR_JWT_TOKEN

# Booking endpoints
POST https://YOUR_CODESPACE-8085.githubpreview.dev/api/booking
Headers: Authorization: Bearer YOUR_JWT_TOKEN
Body: { your booking data }

# Review endpoints
POST https://YOUR_CODESPACE-8085.githubpreview.dev/api/review
Headers: Authorization: Bearer YOUR_JWT_TOKEN
Body: { "text": "Great service!", "rating": 5.0 }
```

---

## 📊 Step 7: Monitor Services

### Check Eureka Dashboard
Open: `https://YOUR_CODESPACE-8070.githubpreview.dev`

You should see all 8 services registered:
- USERSERVICE
- SALON-SERVICE
- CATEGORY-SERVICE
- SERVICE-OFFERING
- BOOKING-SERVICE
- PAYMENT-SERVICE
- NOTIFICATION-SERVICE
- REVIEW-SERVICE

### Check Service Logs

```bash
# View logs
tail -f logs/user-service.log
tail -f logs/booking-service.log
tail -f logs/review-service.log

# Or with start-all.sh
tail -f /tmp/eureka.log
tail -f /tmp/user.log
```

---

## 🛑 Step 8: Stop Services

### If using start-all.sh:
```bash
# The script shows PIDs at the end, use:
kill PID1 PID2 PID3 ...

# Or kill all Spring Boot processes:
pkill -f spring-boot:run
```

### If using manual terminals:
- Press `Ctrl+C` in each terminal

---

## 🐛 Troubleshooting

### Service fails to start:
```bash
# Check if PostgreSQL is running
sudo service postgresql status
sudo service postgresql start

# Check database exists
psql -U postgres -l

# Create missing database
psql -U postgres -c "CREATE DATABASE \"Review_Service_db\";"
```

### Port already in use:
```bash
# Find process using port
lsof -i :5001

# Kill it
kill -9 PID
```

### Keycloak not running:
```bash
docker ps
docker start keycloak
```

### Can't access service from Windows:
1. Check PORTS tab - port should show "green" status
2. Make sure visibility is **Public** (click lock icon if needed)
3. Click globe icon to get correct URL
4. Wait 1-2 minutes after service starts

---

## 💾 Step 9: Save Your Work

Codespace auto-saves, but push to GitHub regularly:

```bash
git add .
git commit -m "Your changes"
git push origin main
```

---

## 💰 Managing Free Tier (60 hours/month)

### Stop Codespace when not using:
1. Go to: https://github.com/codespaces
2. Click ••• menu next to your Codespace
3. Click **"Stop codespace"**

### Resume later:
1. Go back to your repo
2. Click Code → Codespaces
3. Click on your existing Codespace

### Delete when done:
- Only if you're completely finished
- Can always create new one

---

## 🎯 Complete Testing Workflow

### From Windows:

1. **Start Codespace** (5 min setup first time)
2. **Start services** (`./start-all.sh`)
3. **Wait 2-3 minutes** for all to register with Eureka
4. **Get URLs** from PORTS tab
5. **Test with Postman** on Windows
6. **View logs** in Codespace terminal
7. **Make code changes** in Codespace browser
8. **Restart specific service** to test changes
9. **Stop Codespace** when done

---

## 🚀 Advantages for Windows Users

✅ **No Java/Maven installation** on Windows  
✅ **No PostgreSQL setup** on Windows  
✅ **Public URLs** work from any device  
✅ **Linux environment** (production-like)  
✅ **No port conflicts** with local services  
✅ **Share URLs** with team for testing  
✅ **Access from iPad/phone** if needed  

---

## 🎓 Pro Tips

1. **Bookmark your Codespace URL** for quick access
2. **Use Thunder Client** (built-in VS Code extension) instead of Postman
3. **Split terminal view** to monitor multiple logs
4. **Use Ctrl+K Ctrl+O** to open Ports tab quickly
5. **Pin important ports** by right-clicking in Ports tab
6. **Create aliases** in bash for frequently used commands:
   ```bash
   echo 'alias startall="cd ~/salon_microservices && ./start-all.sh"' >> ~/.bashrc
   ```

---

## ✅ You're Ready!

Your complete microservices architecture is now accessible from Windows!

**Next Steps:**
1. Push code to GitHub from Mac ✓
2. Create Codespace from Windows ✓
3. Start all services ✓
4. Get public URLs ✓
5. Test with Postman on Windows ✓

**Happy Testing! 🎉**
