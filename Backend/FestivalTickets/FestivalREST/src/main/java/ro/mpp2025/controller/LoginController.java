package ro.mpp2025.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.mpp2025.Domain.Angajat;
import ro.mpp2025.Repository.AngajatDbRepo;
import ro.mpp2025.Repository.IAngajatRepo;
import ro.mpp2025.Repository.SpectacolDbRepo;
import ro.mpp2025.service.IService;
import ro.mpp2025.model.JwtUtil;
import ro.mpp2025.model.AuthRequest;
import ro.mpp2025.model.AuthResponse;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AngajatDbRepo angajatRepo;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            Angajat angajat = angajatRepo.findByCredentials(request.getUsername(), request.getPassword());
            if (angajat != null) {
                String token = JwtUtil.generateToken(angajat.getUsername());
                return ResponseEntity.ok(new AuthResponse(token));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Authentication failed");
        }
    }
}
