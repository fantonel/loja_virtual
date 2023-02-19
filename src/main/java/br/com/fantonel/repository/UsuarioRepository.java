package br.com.fantonel.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import br.com.fantonel.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
	@Procedure("func_remove_jpa_constraint_error")
	boolean removeJpaConstraintError();

	boolean existsByLogin(String login);

	@Query("SELECT u FROM Usuario u WHERE u.pessoaJuridica.id=?1")
	public Optional<Usuario> findByPessoaJuridica(Long id);
	
	@Query("SELECT u FROM Usuario u WHERE u.pessoaFisica.id=?1")
	public Optional<Usuario> findByPessoaFisica(Long id);
	
	public Optional<Usuario> findByLogin(String login);	
		
	@Query(nativeQuery = true, value = "SELECT a.* FROM usuario_acessos ua INNER JOIN acesso a ON (a.id = ua.acesso_id) WHERE ua.usuario_id=?1")
	public List<?> findUsuarioAcessosById(Long id);
	
	@Query("SELECT u FROM Usuario u WHERE u.dataAtualizacao < (CURRENT_DATE-90)")
	public List<Usuario> buscaUsuarioSenhaExpirada();
}