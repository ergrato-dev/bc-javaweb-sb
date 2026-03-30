package com.bootcamp.service;

import com.bootcamp.domain.Payment;
import com.bootcamp.dto.PaymentCreateRequest;
import com.bootcamp.dto.PaymentResponse;
import com.bootcamp.exception.PaymentNotFoundException;
import com.bootcamp.repository.PaymentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    /**
     * Crea un nuevo pago para el usuario autenticado.
     *
     * TODO: Implementar
     * 1. Crear Payment con los datos del request y el ownerUsername
     * 2. Guardar con paymentRepository.save()
     * 3. Retornar toResponse()
     */
    public PaymentResponse create(PaymentCreateRequest request, String ownerUsername) {
        // TODO: Implementar
        return null;
    }

    /**
     * Lista los pagos del usuario autenticado (paginados).
     *
     * TODO: Implementar
     * - Si isAdmin → paymentRepository.findAll(pageable)
     * - Si no → paymentRepository.findByOwnerUsername(ownerUsername, pageable)
     * - Mapear con toResponse()
     */
    @Transactional(readOnly = true)
    public Page<PaymentResponse> findPayments(String ownerUsername,
                                              boolean isAdmin,
                                              Pageable pageable) {
        // TODO: Implementar
        return Page.empty();
    }

    /**
     * Obtiene un pago por ID.
     *
     * TODO: Implementar
     * - Si isAdmin → paymentRepository.findById(id)
     * - Si no → paymentRepository.findByIdAndOwnerUsername(id, ownerUsername)
     * - Si no existe → PaymentNotFoundException(id)
     * - Retornar toResponse()
     */
    @Transactional(readOnly = true)
    public PaymentResponse findById(Long id, String ownerUsername, boolean isAdmin) {
        // TODO: Implementar
        return null;
    }

    /**
     * Cancela un pago en estado PENDING.
     *
     * TODO: Implementar
     * 1. Buscar el pago (respetando ownership)
     * 2. Verificar que el estado sea PENDING
     * 3. Si no está en PENDING → lanzar IllegalStateException("Only PENDING payments can be cancelled")
     * 4. Cambiar estado a CANCELLED con payment.setStatus(PaymentStatus.CANCELLED)
     * 5. Guardar y retornar toResponse()
     */
    public PaymentResponse cancel(Long id, String ownerUsername, boolean isAdmin) {
        // TODO: Implementar
        return null;
    }

    private PaymentResponse toResponse(Payment payment) {
        return new PaymentResponse(
                payment.getId(),
                payment.getAmount(),
                payment.getCurrency(),
                payment.getRecipientAccount(),
                payment.getStatus(),
                payment.getOwnerUsername(),
                payment.getDescription(),
                payment.getCreatedAt()
        );
    }
}
