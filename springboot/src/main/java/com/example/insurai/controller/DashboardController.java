package com.example.insurai.controller;

import com.example.insurai.entity.User;
import com.example.insurai.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = {"http://localhost:5173"})
public class DashboardController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{email}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getUserDashboard(@PathVariable String email) {
        try {
            User user = userRepository.findByEmail(email).orElse(null);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }

            Map<String, Object> dashboardData = new HashMap<>();

            // Policy Summary
            Map<String, Object> policySummary = new HashMap<>();
            policySummary.put("activePolicies", 1);
            policySummary.put("nextExpiry", "2024-12-31");
            policySummary.put("coverage", "Health Insurance");
            dashboardData.put("policySummary", policySummary);

            // Upcoming Payments
            dashboardData.put("upcomingPayments", new Object[]{
                Map.of("type", "Premium", "dueDate", "2024-01-15", "amount", "$150")
            });

            // Claims Status
            dashboardData.put("claimsStatus", new Object[]{});

            // Payment History
            dashboardData.put("paymentHistory", new Object[]{
                Map.of("date", "2023-12-01", "description", "Monthly Premium", "amount", "$150", "status", "Paid")
            });

            return ResponseEntity.ok(dashboardData);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error fetching dashboard data: " + e.getMessage());
        }
    }
}
