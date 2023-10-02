package br.com.fiap.mspagamentos.repository;

import br.com.fiap.mspagamentos.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
