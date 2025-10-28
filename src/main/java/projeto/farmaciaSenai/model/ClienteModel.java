package projeto.farmaciaSenai.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TBCLIENTE")
public class ClienteModel{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idusuario_id_usuario")
    private UsuarioModel Idusuario;

    @Column(name = "TIPO_PLANO_SAUDE", length = 30)
    private String tipoPlanoSaude;

    @ManyToMany
    @JoinTable(
            name = "TB_HISTORICO_COMPRAS",
            joinColumns = @JoinColumn(name = "ID_CLIENTE"),
            inverseJoinColumns = @JoinColumn(name = "ID_PRODUTO")
    )
    private List<ProdutoModel> historicoCompras;
}