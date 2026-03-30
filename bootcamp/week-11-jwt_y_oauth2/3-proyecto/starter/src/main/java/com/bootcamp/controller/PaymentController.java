package com.bootcamp.controller;

import com.bootcamp.dto.PaymentCreateRequest;
import com.bootcamp.dto.PaymentResponse;
import com.bootcamp.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Listar pagos — el usuario ve los suyos; el admin ve todos.
     *
     * TODO: Implementar
     * - Obtener el username con userDetails.getUsername()
     * - Verificar si es admin: userDetails.getAuthorities() contiene ROLE_ADMIN
     * - Llamar paymentService.findPayments(username, isAdmin, pageable)
     * - Retornar 200 OK
     */
    @GetMapping
    public ResponseEntity<Page<PaymentResponse>> findPayments(
            @AuthenticationPrincipal UserDetails userDetails,
            Pageable pageable) {
        // TODO: Implementar
        return ResponseEntity.ok(Page.empty());
    }

    /**
     * Obtener pago por ID.
     *
     * TODO: Implementar
     * - Obtener username e isAdmin del userDetails
     * - Llamar paymentService.findById(id, username, isAdmin)
     * - Retornar 200 OK con PaymentResponse
     */
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> findById(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        // TODO: Implementar
        return ResponseEntity.ok().build();
    }

    /**
     * Crear un nuevo pago.
     *
     * TODO: Implementar
     * - Obtener ownerUsername del userDetails
     * - Llamar paymentService.create(request, ownerUsername)
     * - Retornar 201 CREATED con PaymentResponse
     */
    @PostMapping
    public ResponseEntity<PaymentResponse> create(
            @Valid @RequestBody PaymentCreateRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        // TODO: Implementar
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Cancelar un pago en estado PENDING.
     *
     * TODO: Implementar
     * - Obtener username e isAdmin del userDetails
     * - Llamar paymentService.cancel(id, username, isAdmin)
     * - Retornar 200 OK con PaymentResponse actualizado
     */
    @PatchMapping("/{id}/cancel")
    public ResponseEntity<PaymentResponse> cancel(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        // TODO: Implementar
        return ResponseEntity.ok().build();
    }
}
