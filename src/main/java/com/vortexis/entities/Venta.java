package com.vortexis.entities;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vortexis.enums.MetodoPago;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ventas")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;

    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "venta",
            cascade = CascadeType.ALL
    )
    private List<DetalleVenta> detalles;
}
