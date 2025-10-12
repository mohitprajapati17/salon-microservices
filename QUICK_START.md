# ⚡ Quick Start: Run on Windows with Codespaces

## 🎯 What You Have

**10 Microservices Ready:**
- ✅ Eureka Server (Service Discovery) - Port 8070
- ✅ User Service - Port 5001  
- ✅ Salon Service - Port 5002
- ✅ Category Service - Port 5003
- ✅ Service Offering - Port 5004
- ✅ Booking Service - Port 5005
- ✅ Payment Service - Port 5006
- ✅ Notification Service - Port 5007
- ✅ Review Service - Port 5008
- ✅ API Gateway - Port 8085

**Auto-Setup Included:**
- ✅ PostgreSQL databases (8 databases)
- ✅ Keycloak for authentication
- ✅ Docker support
- ✅ Devcontainer configuration

---

## 🚀 5-Minute Setup (From Windows)

### Step 1: Push to GitHub (From Mac - One Time)
```bash
cd /Users/mohit/salon_microservices
git add .
git commit -m "Complete microservices with Codespaces support"
git push origin main
```

### Step 2: Open on Windows (In Browser)
1. Go to: https://github.com/YOUR_USERNAME/salon-microservices
2. Click **Code** → **Codespaces** → **Create codespace on main**
3. Wait 5 minutes (auto-installs everything)

### Step 3: Start All Services (In Codespace)
```bash
chmod +x start-all.sh
./start-all.sh
```

### Step 4: Get Public URLs (Click PORTS tab)
- All services have public URLs
- Click globe icon 🌐 to open
- Use these URLs in Postman on Windows!

### Step 5: Test (From Windows Postman)
```http
POST https://YOUR_CODESPACE-5001.githubpreview.dev/auth/login
Body: {"email": "test@example.com", "password": "pass123"}
```

---

## 📚 Full Documentation

- **Windows Guide:** [WINDOWS_CODESPACES_GUIDE.md](./WINDOWS_CODESPACES_GUIDE.md)
- **Codespaces Setup:** [CODESPACES_SETUP.md](./CODESPACES_SETUP.md)

---

## 🎯 Port Reference

| Service | Port | Path |
|---------|------|------|
| User Service | 5001 | `/auth/*`, `/api/users/*` |
| Salon Service | 5002 | `/api/salon/*` |
| Category Service | 5003 | `/api/category/*` |
| Service Offering | 5004 | `/api/services/*` |
| Booking Service | 5005 | `/api/booking/*` |
| Payment Service | 5006 | `/api/payments/*` |
| Notification Service | 5007 | `/api/notification/*` |
| Review Service | 5008 | `/api/review/*` |
| Gateway (All) | 8085 | `/api/*` |
| Eureka Dashboard | 8070 | `/` |

---

## 💡 Key Commands

```bash
# Start all services
./start-all.sh

# Stop all services
pkill -f spring-boot:run

# Check service status
tail -f logs/user-service.log

# Check all running services
ps aux | grep spring-boot

# Restart PostgreSQL
sudo service postgresql restart
```

---

## ✅ Ready to Go!

Your salon microservices are now cloud-ready and accessible from any Windows device!

🎉 **No more Mac socket issues!** 🎉
