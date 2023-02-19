package br.com.fantonel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fantonel.model.Acesso;
import br.com.fantonel.model.Usuario;
import br.com.fantonel.repository.UsuarioRepository;
@Service
public class UsuarioService implements UserDetailsService{
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public UsuarioService() {}
	
	public boolean existsByLogin(String login) {
		return usuarioRepository.existsByLogin(login);
	}
	
	public Optional<Usuario> findByPessoaJuridica(Long id){
		return usuarioRepository.findByPessoaJuridica(id);
	}
	
	public Optional<Usuario> findByPessoaFisica(Long id){
		return usuarioRepository.findByPessoaFisica(id);		
	}
	
	public Optional<Usuario> findByLogin(String login){
		return usuarioRepository.findByLogin(login);
	}
	
	public Usuario save(Usuario bean){
		return usuarioRepository.save(bean);
	}

	@SuppressWarnings("unchecked")
	public List<Acesso> findUsuarioAcessosById(Long id){
		return (List<Acesso>) usuarioRepository.findUsuarioAcessosById(id);
	}
	
	public boolean removeJpaConstraintError() {
		return usuarioRepository.removeJpaConstraintError();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userAuth = usuarioRepository.findByLogin(username).orElseThrow( () -> new UsernameNotFoundException("Usuário não encontrado") );
		//System.out.println(obj.getUsername()+"-"+obj.getPassword());
		return userAuth;
	}
	
	public List<Usuario> buscaUsuarioSenhaExpirada(){
		return usuarioRepository.buscaUsuarioSenhaExpirada();		 
	}
}